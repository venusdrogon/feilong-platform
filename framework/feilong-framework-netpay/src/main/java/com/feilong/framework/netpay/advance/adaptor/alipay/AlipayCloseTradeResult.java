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
package com.feilong.framework.netpay.advance.adaptor.alipay;

import java.io.Serializable;

/**
 * 支付宝关闭返回的结果.
 * 
 * <h3>正常输出：</h3>
 * 
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="utf-8"?>
 * 
 * <alipay> 
 *       <is_success>T</is_success> 
 * </alipay> 
 * }
 * </pre>
 * 
 * 
 * <h3>发生错误时输出：</h3>
 * 
 * <pre>
 * {@code
 * <?xml version="1.0" encoding="utf-8"?> 
 * <alipay> 
 *      <is_success>F</is_success> 
 *      <error>TRADE_STATUS_NOT_AVAILD</error> 
 * </alipay>
 * }
 * </pre>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月8日 下午3:21:16
 * @see "交易关闭接口(close_trade) v1.2.pdf"
 * @since 1.1.2
 */
public class AlipayCloseTradeResult implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long  serialVersionUID = 288232184048495608L;

    /** 关闭成功<code>{@value}</code> */
    public static final String SUCCESS          = "T";

    /** 关闭失败<code>{@value}</code> */
    public static final String FAIL             = "F";

    /** 原始返回结果. */
    private String             originalResult;

    /** 关闭交易操 作是否成功 返回 T 或者 F. */
    private String             is_success;

    /** 错误明细 ,比如 TRADE_NOT_EXIST. */
    private String             error;

    /**
     * 获得 是否成功 返回 T 或者 F.
     *
     * @return the is_success
     */
    public String getIs_success(){
        return is_success;
    }

    /**
     * 设置 是否成功 返回 T 或者 F.
     *
     * @param is_success
     *            the is_success to set
     */
    public void setIs_success(String is_success){
        this.is_success = is_success;
    }

    /**
     * 获得 错误明细 ,比如 TRADE_NOT_EXIST.
     *
     * @return the error
     */
    public String getError(){
        return error;
    }

    /**
     * 设置 错误明细 ,比如 TRADE_NOT_EXIST.
     *
     * @param error
     *            the error to set
     */
    public void setError(String error){
        this.error = error;
    }

    /**
     * 获得 原始返回结果.
     *
     * @return the originalResult
     */
    public String getOriginalResult(){
        return originalResult;
    }

    /**
     * 设置 原始返回结果.
     *
     * @param originalResult
     *            the originalResult to set
     */
    public void setOriginalResult(String originalResult){
        this.originalResult = originalResult;
    }
}
