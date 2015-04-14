/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.spring.jdbc.datasource;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

import com.feilong.commons.core.date.DateExtensionUtil;
import com.feilong.commons.core.lang.ThreadUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.spring.aop.AbstractAspect;
import com.feilong.spring.aop.ProceedingJoinPointUtil;
import com.feilong.spring.transaction.interceptor.TransactionAttributeUtil;

/**
 * 使用拦截器,确定使用那个组/类型数据库,以及那种(读还是写)的数据库.
 * 
 * <h3>可能的方式:</h3>
 * 
 * <blockquote>
 * <ol>
 * <li>
 * controller调用 AManager.a(); A是 adatabase</li>
 * <li></li>
 * <li></li>
 * <li>
 * 
 * <pre>
 * 可能方法 套方法  AManager.a(){ 调用 BManager.b();}  ,
 * A是 adatabase
 * B是 bdatabase
 * controller 调用A 的前, MultipleGroupReadWriteStatusHolder.setMultipleDataSourceGroupName(adatabase);
 * 当 A.a(){ 调用 B.b();}的时候
 * 调完之后,需要回去
 * </pre>
 * 
 * </li>
 * </ol>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月5日 下午6:59:04
 * @see com.feilong.spring.aop.AbstractAspect
 * @see org.aspectj.lang.annotation.Aspect
 * @see org.aspectj.lang.annotation.Around
 * @see org.springframework.transaction.interceptor.TransactionAttributeSource
 * @see org.springframework.core.Ordered
 * @see "loxia.aspect.ReadWriteDataSourceAspect"
 * @see org.springframework.transaction.annotation.Transactional
 * @since 1.1.1
 */
@Aspect
public class MultipleGroupReadWriteDataSourceAspect extends AbstractAspect{

    /** The Constant logger. */
    private static final Logger        log = LoggerFactory.getLogger(MultipleGroupReadWriteDataSourceAspect.class);

    /** The transaction attribute souce. */
    @Autowired(required = false)
    private TransactionAttributeSource transactionAttributeSouce;

    /**
     * Point.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @return the object
     * @throws Throwable
     *             the throwable
     */
    @Around("this(loxia.dao.ReadWriteSupport)")
    public Object point(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //事务
        TransactionAttribute transactionAttribute = null;
        if (null != transactionAttributeSouce){
            transactionAttribute = transactionAttributeSouce.getTransactionAttribute(methodSignature.getMethod(), proceedingJoinPoint
                            .getTarget().getClass());
        }

        MultipleGroupDataSource multipleGroupDataSourceAnnotation = getAnnotation(proceedingJoinPoint, MultipleGroupDataSource.class);
        //组名
        String groupName;
        //没有配置multipleGroupDataSourceAnnotation
        //没有配置 当然延续原来的 风格
        if (null == multipleGroupDataSourceAnnotation || Validator.isNullOrEmpty(multipleGroupDataSourceAnnotation.value())){
            //nothing to do
            groupName = null;
        }else{
            groupName = multipleGroupDataSourceAnnotation.value();
        }
        return this.proceed(proceedingJoinPoint, transactionAttribute, groupName);
    }

    /**
     * Proceed.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @param transactionAttribute
     *            the transaction attribute
     * @param groupName
     *            the group name
     * @return the object
     * @throws Throwable
     *             the throwable
     * @since 1.1.1
     */
    private Object proceed(ProceedingJoinPoint proceedingJoinPoint,TransactionAttribute transactionAttribute,String groupName)
                    throws Throwable{
        //当前的holder
        String previousDataSourceNameHolder = MultipleGroupReadWriteStatusHolder.getMultipleDataSourceGroupName();

        String currentThreadInfo = JsonUtil.format(ThreadUtil.getCurrentThreadMapForLog());
        if (log.isInfoEnabled()){

            Map<String, Object> mapForLog = new LinkedHashMap<String, Object>();
            mapForLog.put("groupName", groupName);
            mapForLog.put("previousDataSourceNameHolder", previousDataSourceNameHolder);
            mapForLog.put("transactionAttribute:", TransactionAttributeUtil.getMapForLog(transactionAttribute));

            log.info(
                            "before determine datasource :[{}],proceedingJoinPoint info:[{}],current thread info:[{}]",
                            JsonUtil.format(mapForLog),
                            getProceedingJoinPointJsonInfoExcludeJsonException(proceedingJoinPoint),
                            currentThreadInfo);
        }

        boolean isSetHolder = isSetHolder(transactionAttribute, groupName);

        //***************************************************************************
        if (isSetHolder){
            //read or write
            String readWriteSupport = this.getReadWriteSupport(transactionAttribute);

            String targetDataSourcesKey = MultipleGroupReadWriteUtil.getTargetDataSourcesKey(groupName, readWriteSupport);
            log.info("set targetDataSourcesKey:[{}],current thread info:[{}]", targetDataSourcesKey, currentThreadInfo);
            MultipleGroupReadWriteStatusHolder.setMultipleDataSourceGroupName(targetDataSourcesKey);
        }

        try{
            return this.proceed(proceedingJoinPoint);
        }catch (Throwable e){
            throw e;
        }finally{
            if (Validator.isNotNullOrEmpty(previousDataSourceNameHolder)){
                log.info(
                                "Back to previous Read/Write Status:[{}],current thread info:[{}]",
                                previousDataSourceNameHolder,
                                currentThreadInfo);
                //神来之笔,这样才能兼容 嵌套
                MultipleGroupReadWriteStatusHolder.setMultipleDataSourceGroupName(previousDataSourceNameHolder);
            }

            //TODO 可能还可以优化 现规则和loxia相同
            //不存在previousDataSourceNameHolder,则清空
            else{
                log.info(
                                "previousDataSourceNameHolder is NullOrEmpty,Clear Read/Write Status:[{}],current thread info:[{}]",
                                MultipleGroupReadWriteStatusHolder.getMultipleDataSourceGroupName(),
                                currentThreadInfo);
                MultipleGroupReadWriteStatusHolder.clearMultipleDataSourceGroupName();
            }
        }
    }

    /**
     * 判断是否要设置钩子.
     *
     * @param transactionAttribute
     *            the transaction attribute
     * @param groupName
     *            the group name
     * @return true, if checks if is set holder
     * @since 1.1.1
     */
    private boolean isSetHolder(TransactionAttribute transactionAttribute,String groupName){
        if (Validator.isNotNullOrEmpty(groupName)){
            return true;
        }else{

            if (null == transactionAttribute){
                return true;
            }

            int propagationBehavior = transactionAttribute.getPropagationBehavior();
            //TODO 可能还可以优化 现规则和loxia相同
            return propagationBehavior != TransactionDefinition.PROPAGATION_REQUIRES_NEW;
        }
    }

    /**
     * 判断数据库读写类型,<br>
     * 通过 {@link org.springframework.transaction.interceptor.TransactionAttribute}判断这次请求该操作读库还是写库.
     *
     * @param transactionAttribute
     *            the transaction attribute
     * @return the read write support
     * @see "org.postgresql.jdbc2.AbstractJdbc2Connection#setReadOnly(boolean)"
     * @since 1.1.1
     */
    private String getReadWriteSupport(TransactionAttribute transactionAttribute){

        String readWriteSupport = "";

        if (null == transactionAttribute){
            return loxia.dao.ReadWriteSupport.READ;
        }

        boolean mustWrite = false;

        int propagationBehavior = transactionAttribute.getPropagationBehavior();

        switch (propagationBehavior) {

        //默认的事务传播行为，表示必须有逻辑事务，否则新建一个事务
            case TransactionDefinition.PROPAGATION_REQUIRED:
                break;

            //创建新的逻辑事务,表示每次都创建新的逻辑事务（物理事务也是不同的）,因此外部事务可以不受内部事务回滚状态的影响独立提交或者回滚。
            case TransactionDefinition.PROPAGATION_REQUIRES_NEW:
                mustWrite = true;
                break;

            //指如果当前存在逻辑事务，就加入到该逻辑事务， 如果当前没有逻辑事务，就以非事务方式执行。
            case TransactionDefinition.PROPAGATION_SUPPORTS:
                break;

            //不支持事务，如果当前存在事务则暂停该事务,如果当前存在逻辑事务，就把当前事务暂停，以非事务方式执行
            case TransactionDefinition.PROPAGATION_NOT_SUPPORTED:
                break;

            //如果当前有事务，使用当前事务执行，如果当前没有事务，则抛出异常（IllegalTransactionStateException）
            case TransactionDefinition.PROPAGATION_MANDATORY:
                break;

            //不支持事务，  如果当前存在是事务则抛出IllegalTransactionStateException异常，
            case TransactionDefinition.PROPAGATION_NEVER:
                break;

            //嵌套事务支持
            case TransactionDefinition.PROPAGATION_NESTED:
                break;

            default:
                throw new UnsupportedOperationException("propagationBehavior:[" + propagationBehavior + "] not support!");
        }

        if (mustWrite){
            log.info("New writable connection is required for new transaction.");
            readWriteSupport = loxia.dao.ReadWriteSupport.WRITE;
        }else{
            //see "org.postgresql.jdbc2.AbstractJdbc2Connection#setReadOnly(boolean)"
            boolean readOnly = transactionAttribute.isReadOnly();
            readWriteSupport = readOnly ? loxia.dao.ReadWriteSupport.READ : loxia.dao.ReadWriteSupport.WRITE;
        }
        return readWriteSupport;
    }

    /**
     * Proceed.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @return the object
     * @throws Throwable
     *             the throwable
     * @since 1.1.1
     */
    private Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = proceedingJoinPoint.getArgs();
        String format = getProceedingJoinPointJsonInfoExcludeJsonException(proceedingJoinPoint);

        if (log.isInfoEnabled()){
            log.info(
                            "begin proceed ,ProceedingJoinPoint info:[{}],Thread info:{}",
                            format,
                            JsonUtil.format(ThreadUtil.getCurrentThreadMapForLog()));
        }
        Date beginDate = new Date();

        //***********************************************************

        Object returnValue = proceedingJoinPoint.proceed(args);

        //***********************************************************
        Date endDate = new Date();

        if (log.isInfoEnabled()){
            log.info(
                            "end proceed:[{}],thread info:[{}],time:{},return:[{}]",
                            format,
                            JsonUtil.format(ThreadUtil.getCurrentThreadMapForLog()),
                            DateExtensionUtil.getIntervalForView(beginDate, endDate),
                            returnValue);
        }
        return returnValue;
    }

    /**
     * 有些业务类可能不规范,把request这样的不能转成json的对象 当作参数传递, 如果不处理的话, 就会抛异常.
     *
     * @param proceedingJoinPoint
     *            the proceeding join point
     * @return the proceeding join point json info exclude json exception
     * @since 1.1.1
     */
    private String getProceedingJoinPointJsonInfoExcludeJsonException(ProceedingJoinPoint proceedingJoinPoint){
        String format = "";
        try{
            format = JsonUtil.format(ProceedingJoinPointUtil.getMapForLog(proceedingJoinPoint));
        }catch (JSONException e){
            format = e.getMessage();
            log.error("", e);
        }
        return format;
    }
}