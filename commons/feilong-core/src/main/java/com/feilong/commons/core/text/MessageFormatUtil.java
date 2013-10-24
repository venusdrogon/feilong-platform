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
package com.feilong.commons.core.text;

import java.text.Format;
import java.text.MessageFormat;

/**
 * MessageFormat 工具包,常用于国际化<br>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:19:22
 * @see MessageFormat
 * @see Format
 */
public final class MessageFormatUtil{

	/**
	 * 格式化 <br>
	 * 用法:
	 * 
	 * <pre>
	 * MessageFormatUtil.format(&quot;name=张三{0}a{1}&quot;, &quot;jin&quot;, &quot;xin&quot;)
	 * 
	 * 返回: name=张三jinaxin
	 * </pre>
	 * 
	 * @param pattern
	 *            占位符有三种方式书写方式：
	 *            <ul>
	 *            <li>{argumentIndex}: 0-9 之间的数字，表示要格式化对象数据在参数数组中的索引号</li>
	 *            <li>{argumentIndex,formatType}: 参数的格式化类型</li>
	 *            <li>{argumentIndex,formatType,FormatStyle}: 格式化的样式，它的值必须是与格式化类型相匹配的合法模式、或表示合法模式的字符串。</li>
	 *            </ul>
	 * @param arguments
	 *            动态参数
	 * @return
	 */
	public static String format(String pattern,Object...arguments){
		return MessageFormat.format(pattern, arguments);
	}
}
