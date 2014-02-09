/*
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.netpay.adaptor.mobile;

import java.util.Map;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 手机版alipay支付(网银在线)
 * 
 * @author 冯明雷
 * @time 2013-6-4 下午2:05:50
 */
public class AlipayNetPayMobileAdaptor extends AlipayPayMobileAdaptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#validatorSpecialSignMap
	 * (java.util.Map)
	 */
	@Override
	protected boolean validatorSpecialSignMap(Map<String, String> specialSignMap) {
		if (Validator.isNullOrEmpty(specialSignMap)) {
			throw new NullPointerException("the specialSignMap is null or empty!");
		}

		String bankCode = specialSignMap.get("defaultbank");
		if (Validator.isNullOrEmpty(bankCode)) {
			throw new NullPointerException("the defaultbank param is null or empty!");
		}

		if (!isSupportBank(bankCode)) {
			throw new IllegalArgumentException("bankCode:" + bankCode + " don't support,please see document");
		}

		return super.validatorSpecialSignMap(specialSignMap);
	}

	/**
	 * 判断传入的 银行code 是否 支持
	 * 
	 * @param defaultbank
	 * @return
	 */
	private boolean isSupportBank(String defaultbank) {
		return ArrayUtil.isContain(supportBank, defaultbank);
	}

	/**
	 * 支持的银行code
	 */
	private String[] supportBank = {

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
