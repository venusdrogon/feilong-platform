/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.netpay;

import java.io.Serializable;
import java.util.Map;

import com.feilong.commons.core.util.Validator;

/**
 * 支付表单entity.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 15, 2013 7:12:48 PM
 */
public class PaymentFormEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -8414890066925723419L;

	/** 表单 action 提交的url. */
	private String				action;

	/** form 的method 有get 和post. */
	private String				method;

	/** 一堆的隐藏域 map key 为 hidden name,value 为 hidden value. */
	private Map<String, String>	hiddenParamMap;

	/**
	 * Gets the action.
	 * 
	 * @return the action
	 */
	public String getAction(){
		return action;
	}

	/**
	 * Sets the action.
	 * 
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action){
		this.action = action;
	}

	/**
	 * Gets the method.
	 * 
	 * @return the method
	 */
	public String getMethod(){
		return method;
	}

	/**
	 * Sets the method.
	 * 
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method){
		this.method = method;
	}

	/**
	 * Gets the hidden param map.
	 * 
	 * @return the hiddenParamMap
	 */
	public Map<String, String> getHiddenParamMap(){
		return hiddenParamMap;
	}

	/**
	 * Sets the hidden param map.
	 * 
	 * @param hiddenParamMap
	 *            the hiddenParamMap to set
	 */
	public void setHiddenParamMap(Map<String, String> hiddenParamMap){
		this.hiddenParamMap = hiddenParamMap;
	}

	/**
	 * 完整的请求路径.
	 * 
	 * @return the fullRequestURL
	 */
	public String getFullRequestURL(){
		StringBuilder builder = new StringBuilder("");
		builder.append(action);
		// map 不是空 表示 有参数
		if (Validator.isNotNullOrEmpty(hiddenParamMap)){
			builder.append("?");
			for (Map.Entry<String, ?> entry : hiddenParamMap.entrySet()){
				builder.append(entry.getKey());
				builder.append("=");
				builder.append(entry.getValue());
				builder.append("&");
			}
			// 把最后一个多的& 截取掉
			return builder.toString().substring(0, builder.toString().length() - 1);
		}
		return builder.toString();

	}

}
