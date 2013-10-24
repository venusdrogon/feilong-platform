package com.feilong.tools.html;

import com.feilong.commons.core.util.Validator;

/**
 * html工具类
 * 
 * @author 金鑫 2010-6-18 上午07:33:33
 */
public class HtmlUtil{

	/**
	 * 添加属性
	 * 
	 * @param stringBuilder
	 *            stringBuilder
	 * @param attributeName
	 *            属性名称
	 * @param attributeValue
	 *            属性值
	 */
	public static void addAttribute(StringBuilder stringBuilder,String attributeName,Object attributeValue){
		if (Validator.isNotNullOrEmpty(attributeValue)){
			stringBuilder.append(" " + attributeName + "=\"" + attributeValue + "\"");
		}else{
			if ("value".equals(attributeName)){
				stringBuilder.append(" " + attributeName + "=\"\"");
			}
		}
	}
}
