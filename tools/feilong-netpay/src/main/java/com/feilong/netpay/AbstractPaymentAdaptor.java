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

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractPaymentAdaptor implements PaymentAdaptor{

	public String getReturnUrl(String soCode,HttpServletRequest request){
		return getServerRootWithContextPath(request) + "/paymentconfirm/" + soCode + ".htm";
	}

	public String getNotifyUrl(String paymentType,HttpServletRequest request){
		return getServerRootWithContextPath(request) + "/payment/" + paymentType + "/feedback";
	}

	protected String getServerRootWithContextPath(HttpServletRequest request){
		return "http://" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort() + request.getContextPath());
	}

	/**
	 * 每个实现类 都需要 实现 这个方法
	 * 
	 * @param code
	 * @param total_fee
	 * @param return_url
	 * @param notify_url
	 * @return
	 */
	public abstract boolean doValidator(String code,BigDecimal total_fee,String return_url,String notify_url);
}
