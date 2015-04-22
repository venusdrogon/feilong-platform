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
package com.feilong.framework.netpay.payment.adaptor.alipay.wap;

import java.util.Map;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 手机版alipay支付(网银在线).
 * 
 * @author 冯明雷
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2013-6-4 下午2:05:50
 * @version 1.0.5 2014-5-6 20:38 change name
 * @deprecated 待重构
 */
@Deprecated
public class AlipayWapNetpayAdaptor extends AlipayWapAdaptor{

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.netpay.adaptor.alipay.wap.AlipayWapAdaptor#validatorSpecialSignMap(java.util.Map)
     */
    @Override
    protected boolean validatorSpecialSignMap(Map<String, String> specialSignMap){
        if (Validator.isNullOrEmpty(specialSignMap)){
            throw new NullPointerException("the specialSignMap is null or empty!");
        }

        String bankCode = specialSignMap.get(PARAM_DEFAULT_BANK);
        if (Validator.isNullOrEmpty(bankCode)){
            throw new NullPointerException("the defaultbank param is null or empty!");
        }

        if (!isSupportBank(bankCode)){
            throw new IllegalArgumentException("bankCode:" + bankCode + " don't support,please see document");
        }

        return super.validatorSpecialSignMap(specialSignMap);
    }

    /**
     * 判断传入的 银行code 是否 支持.
     * 
     * @param defaultbank
     *            the defaultbank
     * @return true, if is support bank
     */
    private boolean isSupportBank(String defaultbank){
        return ArrayUtil.isContain(SUPPORT_BANKS, defaultbank);
    }

    /** 支持的银行code. */
    private static final String[] SUPPORT_BANKS = {

                                                // *******************************储蓄卡
            "DEBITCARD_ICBC",// 工行储蓄卡
            "DEBITCARD_ABC",// 农行储蓄卡
            "DEBITCARD_CCB",// 建行储蓄卡
            "DEBITCARD_PSBC",// 邮储储蓄卡
            "DEBITCARD_CMB",// 招行储蓄卡
            "DEBITCARD_SPDB",// 浦发银行储蓄卡
            "DEBITCARD_GDB",// 广发银行储蓄卡
            "DEBITCARD_CMBC",// 民生银行
            "DEBITCARD_COMM",// 交通银行储蓄卡
            "DEBITCARD_CITIC",// 中信银行储蓄卡
            "DEBITCARD_SHBANK",// 上海银行储蓄卡
            "DEBITCARD_NBBANK",// 宁波银行储蓄卡
            "DEBITCARD_SPABANK",// 平安银行储蓄卡
            "DEBITCARD"// 储蓄卡默认code，跳转到支付宝会有选择界面
                                                };
}
