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
package com.feilong.framework.netpay.payment;

import com.feilong.framework.netpay.payment.exception.BankCodeNotSupportedException;

/**
 * 是否校验银行卡,有些支付方式需要校验传递过来的银行code 比如 {@link com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineCreditCardAdaptor},
 * {@link com.feilong.framework.netpay.payment.adaptor.alipay.pconline.AlipayOnlineNetpayAdaptor}.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年4月22日 下午1:24:33
 * @since 1.1.2
 */
public interface ValidateBankCodeable{

    /**
     * 检查银行code 是否是支持的银行.
     *
     * @param defaultbank
     *            the defaultbank
     * @return true, if checks if is support bank code
     * @throws BankCodeNotSupportedException
     *             the bank code not support exception
     */
    boolean validatorBankCode(String defaultbank) throws BankCodeNotSupportedException;

}
