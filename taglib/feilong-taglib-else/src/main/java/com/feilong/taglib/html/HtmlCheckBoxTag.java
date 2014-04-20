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

import javax.servlet.ServletRequest;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HtmlUtil;

/**
 * 自定义html标签 HtmlCheckBoxTag 作者:金鑫 时间:2009年12月12日 17:00:58
 * @deprecated
 */
public class HtmlCheckBoxTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * name
	 */
	private String				name;

	/**
	 * 值
	 */
	private String				value;

	@Override
	public String writeContent(){
		ServletRequest request = this.pageContext.getRequest();
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("<input");
		HtmlUtil.addAttribute(stringBuilder, "type", "checkbox");
		innerCommonAttribute(stringBuilder);
		if (Validator.isNotNullOrEmpty(name)){
			HtmlUtil.addAttribute(stringBuilder, "name", name);
		}
		// 赋值
		HtmlUtil.addAttribute(stringBuilder, "value", value);
		String p_nameValue = request.getParameter(name);
		if (Validator.isNotNullOrEmpty(p_nameValue)){
			// 选中
			if (p_nameValue.equals(value)){
				HtmlUtil.addAttribute(stringBuilder, "checked", "checked");
			}
		}
		stringBuilder.append("/>");
		return stringBuilder.toString();
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value){
		this.value = value;
	}
}
