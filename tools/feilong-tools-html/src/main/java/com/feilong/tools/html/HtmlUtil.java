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
