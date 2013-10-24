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
package com.feilong.netpay.adaptor;
import java.util.LinkedHashMap;
import java.util.Map;

import com.feilong.commons.core.util.Validator;

/**
 * alipay 版本(网银在线)
 */
public class AlipayNetPayAdaptor extends AlipayPayAdaptor{

	private String	defaultbank;

	public AlipayNetPayAdaptor(String defaultbank){
		this.defaultbank = defaultbank;
	}

	public AlipayNetPayAdaptor(){}

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#setOtherParamsMap()
	 */
	@Override
	protected Map<String, String> setOtherParamsMap(){
		// 默认
		if (Validator.isNullOrEmpty(defaultbank)){
			throw new NullPointerException("the defaultbank is null or empty!");
		}
		Map<String, String> otherParamsMap = new LinkedHashMap<String, String>();
		otherParamsMap.put("defaultbank", defaultbank);
		return otherParamsMap;
	}

	public void setDefaultbank(String defaultbank){
		this.defaultbank = defaultbank;
	}
}
