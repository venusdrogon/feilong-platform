/**
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
package com.feilong.netpay.adaptor.alipay;

import java.util.Map;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * alipay 版本(网银在线)
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 20, 2013 12:40:06 PM
 */
public class AlipayNetPayAdaptor extends AlipayPayAdaptor{

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#validatorSpecialSignMap(java.util.Map)
	 */
	@Override
	protected boolean validatorSpecialSignMap(Map<String, String> specialSignMap){
		if (Validator.isNullOrEmpty(specialSignMap)){
			throw new NullPointerException("the specialSignMap is null or empty!");
		}

		String defaultbank = specialSignMap.get("defaultbank");
		if (Validator.isNullOrEmpty(defaultbank)){
			throw new NullPointerException("the defaultbank param is null or empty!");
		}

		if (!isSupportBank(defaultbank)){
			throw new IllegalArgumentException("defaultbank:" + defaultbank + " don't support,please see document");
		}

		return super.validatorSpecialSignMap(specialSignMap);
	}

	/**
	 * 判断传入的 银行code 是否 支持
	 * 
	 * @param defaultbank
	 * @return
	 */
	private boolean isSupportBank(String defaultbank){
		return ArrayUtil.isContain(supportBank, defaultbank);
	}

	/**
	 * 支持的银行code
	 */
	private String[]	supportBank	= {
									// ******** B2B 代表企业银行***************************************
			"ICBCBTB",// 中国工商银行（B2B）
			"ABCBTB",// 中国农业银行（B2B）
			"CCBBTB",// 中国建设银行（B2B）
			"SPDBB2B",// 上海浦东发展银行（B2B）
			"BOCBTB",// 中国银行（B2B）
			"CMBBTB",// 招商银行（B2B）

			// ***********************************************
			"BOCB2C",// 中国银行
			"ICBCB2C", // 中国工商银行
			"CMB", // 招商银行
			"CCB", // 中国建设银行

			"ABC", // 中国农业银行
			"SPDB", // 上海浦东发展银行
			"CIB", // 兴业银行
			"GDB", // 广东发展银行

			"CMBC", // 中国民生银行
			"COMM", // 交通银行
			"CITIC", // 中信银行
			"HZCBB2C", // 杭州银行

			"CEBBANK", // 中国光大银行
			"SHBANK", // 上海银行
			"NBBANK", // 宁波银行
			"SPABANK", // 平安银行 由于深圳发展银行与平安银行合并，原深圳发展银行卡视为平安银行卡，银行简码统一为 SPABANK，传入SDB（深圳发展银行）将无法支付。

			"BJRCB", // 北京农村商业银行
			"FDB", // 富滇银行
			"POSTGC", // 中国邮政储蓄银行

			// ************** 下面两个 需要单独开通*******************************
			"abc1003", // visa EBANK_VISA_GW_RULE_NOT_OPEN 貌似需要开通
			"abc1004", // masters // 商户未签约外卡收单产品（或者签约到期）或者本次交易金额小于1元

			// *******借记卡************
			"CMB-DEBIT", // 招商银行
			"CCB-DEBIT", // 中国建设银行
			"ICBC-DEBIT", // 中国工商银行
			"COMM-DEBIT", // 交通银行
			"GDB-DEBIT", // 广东发展银行
			"BOC-DEBIT", // 中国银行
			"CEB-DEBIT", // 中国光大银行
			"SPDB-DEBIT", // 上海浦东发展银行
			"PSBC-DEBIT", // 中国邮政储蓄银行
			"BJBANK", // 北京银行
			"SHRCB", // 上海农商银行
			"WZCBB2C-DEBIT", // 温州银行
									};

}
