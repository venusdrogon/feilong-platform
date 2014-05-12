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
package com.feilong.framework.netpay.payment.command;

import java.io.Serializable;
import java.util.Map;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;

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
	 * 完整的请求路径.
	 * 
	 * @return the full encoded url
	 */
	public String getFullEncodedUrl(){
		return URIUtil.getEncodedUrlByValueMap(action, hiddenParamMap, CharsetType.UTF8);
	}

	// ************************************************************

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
}