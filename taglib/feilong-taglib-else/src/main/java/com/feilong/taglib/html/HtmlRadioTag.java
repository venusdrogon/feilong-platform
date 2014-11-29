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

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HtmlUtil;

/**
 * 自定义html标签 HtmlRadioTag 单选框
 * 
 * @author 金鑫 2010-3-1 下午05:06:03
 * @deprecated
 */
@Deprecated
public class HtmlRadioTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 生成的标签含有textValue自定义值
	 */
	// ***************************************************
	/**
	 * name
	 */
	private String				name;

	/**
	 * 默认选中,当不存在参数且没有value的时候 该选项选中
	 * 
	 * <pre>
	 * 优先级顺序:
	 * value paramNameValue defaultValue
	 * 有value 则使用value
	 * 没有value 使用paramNameValue
	 * 当paramName不存在 则使用defaultValue
	 * </pre>
	 */
	private Object				defaultValue;

	/**
	 * 值
	 * 
	 * <pre>
	 * 优先级顺序:
	 * value paramNameValue defaultValue
	 * 有value 则使用value
	 * 没有value 使用paramNameValue
	 * 当paramName不存在 则使用defaultValue
	 * 
	 * </pre>
	 */
	private String				value;

	/**
	 * 值和文字组成的键值对 以&拼接 如"1=男&0=女"
	 */
	private String				valueAndTexts;

	/**
	 * 作用域里面的值 jobYearList 默认request范围,valueAndTexts,scopeOptions必须要有一个
	 */
	private String				scopeOptions;

	/**
	 * 作用域里面的对象显示的字段
	 */
	private String				scopeLabel;

	/**
	 * 作用域里面的对象隐藏值
	 */
	private String				scopeValue;

	@Override
	public String writeContent(){
		String checkedString = " checked=\"checked\"";
		HttpServletRequest request = getHttpServletRequest();
		Map<String, String> map = null;
		// 自定义
		if (Validator.isNotNullOrEmpty(valueAndTexts)){
			// TODO
			// map = ParamUtil.convertParametersToMap(valueAndTexts, CharsetType.UTF8);
		}else if (Validator.isNotNullOrEmpty(scopeOptions)){// 作用域
			// map = RequestUtil.collectionToMap(map, scopeOptions, scopeLabel, scopeValue, request);
		}
		StringBuilder stringBuilder = new StringBuilder("");
		String nameValue = request.getParameter(name);
		for (Map.Entry<String, String> entry : map.entrySet()){
			stringBuilder.append("<input");
			HtmlUtil.addAttribute(stringBuilder, "type", "radio");
			innerCommonAttribute(stringBuilder);
			if (Validator.isNotNullOrEmpty(name)){
				HtmlUtil.addAttribute(stringBuilder, "name", name);
			}
			// 赋值
			HtmlUtil.addAttribute(stringBuilder, "value", entry.getKey());
			// 自定义文字值
			HtmlUtil.addAttribute(stringBuilder, "textValue", entry.getValue());
			if (Validator.isNotNullOrEmpty(value)){
				// 选中状态
				if (value.equals(entry.getKey())){
					stringBuilder.append(checkedString);
				}
			}else{
				// 带不带这个名字参数
				if (RequestUtil.isContainsParam(request, name)){
					if (Validator.isNotNullOrEmpty(nameValue)){
						// 默认选中参数值
						if (nameValue.equals(entry.getKey())){
							stringBuilder.append(checkedString);
						}
					}
				}else{
					if (Validator.isNotNullOrEmpty(defaultValue)){
						// 默认选中参数值
						if (defaultValue.equals(entry.getKey())){
							stringBuilder.append(checkedString);
						}
					}
				}
			}
			stringBuilder.append(" />");
			stringBuilder.append(entry.getValue());
		}
		// 没有选中,则默认选中第一个
		if (stringBuilder.indexOf(checkedString) == -1){
			stringBuilder.insert("<input ".length(), checkedString);
		}
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
	 * @param valueAndTexts
	 *            the valueAndTexts to set
	 */
	public void setValueAndTexts(String valueAndTexts){
		this.valueAndTexts = valueAndTexts;
	}

	/**
	 * @param scopeOptions
	 *            the scopeOptions to set
	 */
	public void setScopeOptions(String scopeOptions){
		this.scopeOptions = scopeOptions;
	}

	/**
	 * @param scopeLabel
	 *            the scopeLabel to set
	 */
	public void setScopeLabel(String scopeLabel){
		this.scopeLabel = scopeLabel;
	}

	/**
	 * @param scopeValue
	 *            the scopeValue to set
	 */
	public void setScopeValue(String scopeValue){
		this.scopeValue = scopeValue;
	}

	/**
	 * @param defaultValue
	 *            the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue){
		this.defaultValue = defaultValue;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value){
		this.value = value;
	}
}
