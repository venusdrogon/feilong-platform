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
package com.feilong.framework.bind.parse;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.lang.reflect.ConstructorUtil;
import com.feilong.commons.core.lang.reflect.TypeUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.bind.exception.BuildCommandException;
import com.feilong.tools.dom4j.Dom4jUtil;

/**
 * 通用的转换,定义了两个标准方法,也是核心步骤:
 * <ol>
 * <li>{@link #getVarNameAndValueMap(String)} 将xml转成map,<br>
 * 目前需要自定义来实现,后期可能会开发默认基本实现</li>
 * <li>{@link #buildCommand(Class, Map)} 将map转成javabean<br>
 * 目前有两个基本实现类:
 * <ul>
 * <li>{@link com.feilong.framework.bind.parse.base.AbstractBaseXmlParse} 原javabean只需要符合标准javaBean规范即可,不需要修改,内部使用
 * {@link com.feilong.commons.core.bean.BeanUtil} 来实现反射赋值</li>
 * <li>{@link com.feilong.framework.bind.parse.varcommand.AbstractVarCommandXmlParse},javabean需要在字段上添加
 * {@link com.feilong.framework.bind.parse.varcommand.VarName} 标识,可以来处理 XML中字段可能全部是大写 比如BANK,但是javabean 中的字段却是 bank,可以使用下面的代码来实现隐射
 * 
 * <pre>
 * 
 * 
 * &#064;VarName(name = &quot;BANK&quot;,sampleValue = &quot;BRI&quot;)
 * private String bank;
 * </pre>
 * 
 * </li>
 * </ul>
 * 在实际的开发过程中, 视需要来选择实现
 * 
 * </li>
 * </ol>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月18日 下午5:23:08
 * @param <T>
 *            任意的标准javaBean
 * @since 1.0.8
 */
public abstract class AbstractXmlParse<T> implements XmlParse<T>{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(AbstractXmlParse.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.parse.QueryResultXmlParse#parseXML(java.lang.String)
     */
    @Override
    public T parseXML(String xml){
        if (Validator.isNullOrEmpty(xml)){
            throw new IllegalArgumentException("xml can't be null/empty!");
        }

        if (log.isDebugEnabled()){
            // Writer writer = new StringWriter();
            // log.info(Dom4jUtil.format(wddxPacketXML, CharsetType.UTF8, writer));

            // Dom4jUtil.getElementText(element, "TRANSACTIONSTATUS");

            // XStream xstream = new XStream();
            // String xml = xstream.toXML(wddxPacketXML);
            // log.info(xml);

            // 不要和下面的log 合并, 下面的log 可能会有异常
            log.debug("input xml:[{}]", xml);

            Writer writer = new StringWriter();
            //TODO 异常的处理
            log.debug("formatXML:{}", Dom4jUtil.format(xml, CharsetType.UTF8, writer));
        }
        Class<T> modelClass = parseModelClass();
        // 解析 wddxPacketXML ,获得我们需要的 var name 和值.
        Map<String, String> varNameAndValueMap = getVarNameAndValueMap(xml);

        T t = buildCommand(modelClass, varNameAndValueMap);
        return t;
    }

    /**
     * 解析 获得 model bean.
     *
     * @return the class< t>
     */
    protected Class<T> parseModelClass(){
        Class<?> klass = this.getClass();

        Class<T> modelClass = (Class<T>) TypeUtil.getGenericClassArray(klass)[0];

        return modelClass;
    }

    /**
     * Builds the command.
     * 
     * @param modelClass
     *            the model class
     * @param varNameAndValueMap
     *            the var name and value map
     * @return the t
     * @throws BuildCommandException
     *             the build command exception
     * @since 1.1.1
     */
    protected T buildCommand(Class<T> modelClass,Map<String, String> varNameAndValueMap) throws BuildCommandException{
        try{
            T t = ConstructorUtil.newInstance(modelClass);
            t = buildCommand(modelClass, varNameAndValueMap, t);

            if (log.isInfoEnabled()){
                log.info("[{}]:{}", modelClass.getName(), JsonUtil.format(t));
            }
            return t;
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
            throw new BuildCommandException(e);
        }
    }

    /**
     * Builds the command.
     *
     * @param modelClass
     *            the model class
     * @param varNameAndValueMap
     *            the var name and value map
     * @param t
     *            the t
     * @return the t
     * @since 1.1.1
     */
    protected abstract T buildCommand(Class<T> modelClass,Map<String, String> varNameAndValueMap,T t);

    /**
     * Gets the var name and value map.
     * 
     * @param xml
     *            the xml
     * @return the var name and value map
     */
    protected abstract Map<String, String> getVarNameAndValueMap(String xml);
}
