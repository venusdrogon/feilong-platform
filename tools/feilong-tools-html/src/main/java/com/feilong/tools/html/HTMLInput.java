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

/**
 * input
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-10-27 下午03:09:44
 */
public class HTMLInput{

	/**
	 * 创建input标签
	 * 
	 * @param type
	 *            类型
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @return 创建input标签
	 */
	public static String createInputTag(String type,String name,String value){
		StringBuilder builder = new StringBuilder();
		builder.append("<input ");
		builder.append("type=\"" + type + "\" ");
		builder.append("name=\"" + name + "\" ");
		builder.append("value=\"" + value + "\"");
		builder.append("/>");
		return builder.toString();
	}
}
