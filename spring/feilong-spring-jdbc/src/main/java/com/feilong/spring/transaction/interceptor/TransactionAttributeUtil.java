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
package com.feilong.spring.transaction.interceptor;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.TransactionAttribute;

/**
 * The Class TransactionAttributeUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月6日 上午6:24:35
 * @see org.springframework.transaction.interceptor.TransactionAttribute
 * @see org.springframework.transaction.TransactionDefinition
 * @since 1.1.1
 */
public class TransactionAttributeUtil{

    /**
     * 获得 map for log.
     *
     * @param transactionAttribute
     *            the transaction attribute
     * @return the map for log
     */
    public final static Map<String, Object> getMapForLog(TransactionAttribute transactionAttribute){

        if (null == transactionAttribute){
            return null;
        }

        int isolationLevel = transactionAttribute.getIsolationLevel();
        String isolationLevelString = toIsolationLevelString(isolationLevel);

        int propagationBehavior = transactionAttribute.getPropagationBehavior();
        String propagationBehaviorString = toPropagationBehaviorString(propagationBehavior);

        Map<String, Object> object = new LinkedHashMap<String, Object>();
        object.put("getPropagationBehavior", propagationBehaviorString + "(" + propagationBehavior + ")");
        object.put("getIsolationLevel", isolationLevelString + "(" + isolationLevel + ")");
        object.put("isReadOnly", transactionAttribute.isReadOnly());
        object.put("getName", transactionAttribute.getName());
        object.put("getQualifier", transactionAttribute.getQualifier());
        object.put("getTimeout", transactionAttribute.getTimeout());

        return object;
    }

    /**
     * To isolation level string.
     *
     * @param isolationLevel
     *            the isolation level
     * @return the string
     * @since 1.1.1
     */
    private static String toIsolationLevelString(int isolationLevel){
        switch (isolationLevel) {
            case TransactionDefinition.ISOLATION_DEFAULT:
                return "default";

            case TransactionDefinition.ISOLATION_READ_COMMITTED:
                return "read_committed";

            case TransactionDefinition.ISOLATION_READ_UNCOMMITTED:
                return "read_uncommitted";

            case TransactionDefinition.ISOLATION_REPEATABLE_READ:
                return "repeatable_read";

            case TransactionDefinition.ISOLATION_SERIALIZABLE:
                return "serializable";

            default:
                throw new UnsupportedOperationException("isolationLevel:[" + isolationLevel + "] not support!");
        }
    }

    /**
     * To propagation behavior string.
     *
     * @param propagationBehavior
     *            the propagation behavior
     * @return the string
     * @since 1.1.1
     */
    private static String toPropagationBehaviorString(int propagationBehavior){
        String propagationBehaviorString = "";
        switch (propagationBehavior) {

        //默认的事务传播行为，表示必须有逻辑事务，否则新建一个事务
            case TransactionDefinition.PROPAGATION_REQUIRED:
                propagationBehaviorString = "required";
                break;

            //创建新的逻辑事务,表示每次都创建新的逻辑事务（物理事务也是不同的）,因此外部事务可以不受内部事务回滚状态的影响独立提交或者回滚。
            case TransactionDefinition.PROPAGATION_REQUIRES_NEW:
                propagationBehaviorString = "requires_new";
                break;

            //指如果当前存在逻辑事务，就加入到该逻辑事务， 如果当前没有逻辑事务，就以非事务方式执行。
            case TransactionDefinition.PROPAGATION_SUPPORTS:
                propagationBehaviorString = "supports";
                break;

            //不支持事务，如果当前存在事务则暂停该事务,如果当前存在逻辑事务，就把当前事务暂停，以非事务方式执行
            case TransactionDefinition.PROPAGATION_NOT_SUPPORTED:
                propagationBehaviorString = "not_supported";
                break;

            //如果当前有事务，使用当前事务执行，如果当前没有事务，则抛出异常（IllegalTransactionStateException）
            case TransactionDefinition.PROPAGATION_MANDATORY:
                propagationBehaviorString = "mandatory";
                break;

            //不支持事务，  如果当前存在是事务则抛出IllegalTransactionStateException异常，
            case TransactionDefinition.PROPAGATION_NEVER:
                propagationBehaviorString = "never";
                break;

            //嵌套事务支持
            case TransactionDefinition.PROPAGATION_NESTED:
                propagationBehaviorString = "nested";
                break;

            default:
                throw new UnsupportedOperationException("propagationBehavior:[" + propagationBehavior + "] not support!");
        }
        return propagationBehaviorString;
    }
}
