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
package com.feilong.taglib.html;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.CookieUtil;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HtmlUtil;

/**
 * 自定义html标签 HtmlTextTag
 * 
 * @author 金鑫 2009-12-4下午05:43:21 2010-6-24 上午09:23:28
 * @deprecated
 */
@Deprecated
public class HtmlTextTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * name
	 */
	private String				name;

	/**
	 * value 原封不动赋值 p_nameValue进行FeiLongHTTP.decodeLuanMa_ISO8859处理
	 */
	private String				value;

	/**
	 * cookie值 原封不动
	 */
	private String				cookieName;

	@Override
	public String writeContent(){
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("<input");
		HtmlUtil.addAttribute(stringBuilder, "type", "text");
		// 添加公共的属性
		innerCommonAttribute(stringBuilder);
		if (Validator.isNotNullOrEmpty(name)){
			HtmlUtil.addAttribute(stringBuilder, "name", name);
		}
		HttpServletRequest request = getHttpServletRequest();
		/**
		 * 添加value属性
		 */
		innerValueAttribute(stringBuilder, request);
		stringBuilder.append("/>");
		return stringBuilder.toString();
	}

	/**
	 * 添加value属性
	 * 
	 * @param stringBuilder
	 * @param request
	 * @author 金鑫
	 * @version 1.0 2010-7-13 下午05:18:06
	 */
	private void innerValueAttribute(StringBuilder stringBuilder,HttpServletRequest request){
		String valueString = "";
		// 参数值
		String p_nameValue = request.getParameter(name);
		String cookie_Value = "";
		if (Validator.isNotNullOrEmpty(cookieName)){
			cookie_Value = CookieUtil.getCookieValue(request, cookieName);
		}
		// 是否有value属性
		boolean isHasValueAttribute = Validator.isNotNullOrEmpty(value);
		// url中是否带有这个参数
		boolean isHasParamAttribute = RequestUtil.isContainsParam(request, name);
		// 是否带有cookie属性值
		boolean isHasCookieAttribute = Validator.isNotNullOrEmpty(cookie_Value);
		if (isHasValueAttribute || isHasParamAttribute || isHasCookieAttribute){
			// 赋值
			if (isHasValueAttribute){
				valueString = value;
			}
			// 参数值
			else if (isHasParamAttribute){
				valueString = URIUtil.decodeLuanMa_ISO8859(p_nameValue, CharsetType.GB2312);
			}
			// cookie值
			else if (isHasCookieAttribute){
				valueString = cookie_Value;
			}
			HtmlUtil.addAttribute(stringBuilder, "value", valueString);
		}
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value){
		this.value = value;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @param cookieName
	 *            the cookieName to set
	 */
	public void setCookieName(String cookieName){
		this.cookieName = cookieName;
	}
}
