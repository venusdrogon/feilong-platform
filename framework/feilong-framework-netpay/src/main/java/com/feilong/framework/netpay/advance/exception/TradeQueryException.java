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
package com.feilong.framework.netpay.advance.exception;

import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.framework.netpay.advance.PaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.command.QueryRequest;

/**
 * 如果调用查询接口 {@link PaymentAdvanceAdaptor#getQueryResult(QueryRequest)},出现异常,将会抛出这个异常.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014-9-29 12:06
 * @see RuntimeException
 * @since 1.0.8
 */
public class TradeQueryException extends RuntimeException{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7111227268129628604L;

    /**
     * The Constructor.
     */
    public TradeQueryException(){
        super();
    }

    /**
     * The Constructor.
     *
     * @param messagePattern
     *            the message pattern
     * @param args
     *            the args
     */
    public TradeQueryException(String messagePattern, Object...args){
        super(Slf4jUtil.formatMessage(messagePattern, args));
    }

    /**
     * The Constructor.
     *
     * @param message
     *            the message
     */
    public TradeQueryException(String message){
        super(message);
    }

    /**
     * The Constructor.
     *
     * @param cause
     *            the cause
     */
    public TradeQueryException(Throwable cause){
        super(cause);
    }

    /**
     * The Constructor.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public TradeQueryException(String message, Throwable cause){
        super(message, cause);
    }
}
