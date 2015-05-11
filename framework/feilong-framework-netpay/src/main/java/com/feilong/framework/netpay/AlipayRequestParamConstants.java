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
package com.feilong.framework.netpay;

/**
 * 支付宝请求参数常量
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月10日 下午10:38:11
 * @since 1.1.2
 */
public final class AlipayRequestParamConstants{

    /** <code>{@value}</code>. */
    public static final String PARTNER      = "partner";

    /** 签名参数 <code>{@value}</code>. */
    public static final String SIGN         = "sign";

    /** 签名类型 <code>{@value}</code>. */
    public static final String SIGN_TYPE    = "sign_type";

    /** 默认银行 <code>{@value}</code>. */
    public static final String DEFAULT_BANK = "defaultbank";

    /** Don't let anyone instantiate this class. */
    private AlipayRequestParamConstants(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}
