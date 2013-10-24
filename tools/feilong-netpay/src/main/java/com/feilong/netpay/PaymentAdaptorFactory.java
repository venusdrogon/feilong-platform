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
package com.feilong.netpay;

import com.feilong.netpay.adaptor.AlipayCreditCardPayAdaptor;
import com.feilong.netpay.adaptor.AlipayNetPayAdaptor;
import com.feilong.netpay.adaptor.AlipayPayAdaptor;

public class PaymentAdaptorFactory{

	private static String	defaultbank;

	public static PaymentAdaptor getAdaptor(String paymentType){
		/**
		 * 全部 配置,测试环境不可以去付款
		 */
		// 3 网银在线
		if ("3".equals(paymentType)){
			return new AlipayNetPayAdaptor(defaultbank);
		}
		// 6 支付宝
		else if ("6".equals(paymentType)){
			return new AlipayPayAdaptor();
		}
		// 13 招商银行
		else if ("13".equals(paymentType)){
			//return new CmbPayAdaptor();
		}
		// 14 信用卡-支付宝
		else if ("14".equals(paymentType)){
			return new AlipayCreditCardPayAdaptor();
		}
		// 15 汇付天下
		else if ("15".equals(paymentType)){
			//return new ChinapnrPayAdaptor();
		}
		return null;
	}

	public String getDefaultbank(){
		return defaultbank;
	}

	public static void setDefaultbank(String defaultbank){
		PaymentAdaptorFactory.defaultbank = defaultbank;
	}
}