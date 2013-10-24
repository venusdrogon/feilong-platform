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
package com.feilong.commons.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;

/**
 * StringBuilderUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-11 下午5:05:56
 */
public class StringBuilderUtil{

	private static final Logger	log	= LoggerFactory.getLogger(StringBuilderUtil.class);

	/******************************************************************************************************/
	/**
	 * 将不定参数,拼接成string
	 * 
	 * @param strings
	 *            不定参数
	 * @return 使用stringBuilder.append来拼接
	 */
	public final static String append(Object...strings){
		if (Validator.isNotNullOrEmpty(strings)){
			StringBuilder stringBuilder = new StringBuilder();
			for (Object string : strings){
				stringBuilder.append(string);
			}
			return stringBuilder.toString();
		}
		return null;
	}

	/**
	 * append 并且换行
	 * 
	 * @param stringBuilder
	 *            stringBuilder
	 * @param text
	 *            需要append 的内容
	 */
	public final static void appendTextWithLn(StringBuilder stringBuilder,Object text){
		stringBuilder.append(text);
		stringBuilder.append(Constants.lineSeparator);
	}

	/**
	 * <code>
	 * stringBuilder.append(Constants.lineSeparator);
	 * </code>
	 * 
	 * @param stringBuilder
	 */
	public final static void appendLn(StringBuilder stringBuilder){
		stringBuilder.append(Constants.lineSeparator);
	}

	/**
	 * append 并且换行,键值对形式输出
	 * 
	 * @param stringBuilder
	 *            stringBuilder
	 * @param key
	 *            key
	 * @param value
	 *            value
	 */
	public final static void appendTextWithLn(StringBuilder stringBuilder,String key,Object value){
		stringBuilder.append(key);
		stringBuilder.append(":");
		stringBuilder.append(value);
		stringBuilder.append(Constants.lineSeparator);
	}

	/**
	 * 文件 以醒目字符分隔,一般用于 文本输出.例如:
	 * 
	 * <pre>
	 * *****************************************
	 * </pre>
	 * 
	 * @param stringBuilder
	 */
	public final static void appendTextWithSeparated(StringBuilder stringBuilder){
		appendTextWithSeparatedWithTitle(stringBuilder, null);
	}

	/**
	 * 文件 以醒目字符分隔,一般用于 文本输出 带标题的 <br>
	 * 例如:
	 * 
	 * <pre>
	 * ***************呵呵,下面是销售日报-库存部分*******************
	 * </pre>
	 * 
	 * @param stringBuilder
	 * @param title
	 */
	public final static void appendTextWithSeparatedWithTitle(StringBuilder stringBuilder,String title){
		stringBuilder.append(Constants.lineSeparator);
		stringBuilder.append("**************************");
		if (Validator.isNotNullOrEmpty(title)){
			stringBuilder.append(title);
		}
		stringBuilder.append("**************************");
		stringBuilder.append(Constants.lineSeparator);
	}

	/**
	 * 文件 以醒目字符分隔,一般用于 文本输出
	 * 
	 * @return stringBuilder
	 */
	public final static StringBuilder appendTextWithSeparated(){
		StringBuilder stringBuilder = new StringBuilder();
		appendTextWithSeparated(stringBuilder);
		return stringBuilder;
	}
}
