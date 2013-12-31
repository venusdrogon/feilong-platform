/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.netpay.adaptor;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * alipay 信用卡支付<br>
 * 见 快捷支付网银前置文档
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 16, 2013 10:18:24 PM
 */
public class AlipayCreditCardPayAdaptor extends AlipayPayAdaptor{

	/*
	 * (non-Javadoc)
	 * @see com.jumbo.brandstore.payment.adaptor.AlipayPayAdaptor#setOtherParamsMap()
	 */
	@Override
	protected Map<String, String> setOtherParamsMap(){
		Map<String, String> otherParamsMap = new LinkedHashMap<String, String>();
		// otherParamsMap.put("default_login", "Y");
		// 若要使用快捷支付网银前置，取值必须是 motoPay （快捷支付网银前置）。
		// otherParamsMap.put("paymethod", "motoPay");
		return otherParamsMap;
	}
}
