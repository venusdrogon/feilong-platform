/*
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
package com.feilong.commons.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * slf4j util.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 30, 2013 2:24:01 AM
 * @since 1.0.0
 */
public final class Slf4jUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Slf4jUtil.class);

	/**
	 * 格式化字符串,此方法就是抽取slf4j的核心方法<br>
	 * 
	 * <p>
	 * 在java中,常会拼接字符串生成新的字符串值,在字符串拼接过程中 容易写错或者位置写错<br>
	 * <br>
	 * slf4j的log支持格式化输出log,比如:<br>
	 * </p>
	 * <ul>
	 * <li>log.error("{}","feilong");</li>
	 * <li>log.info("{},{}","feilong","hello");</li>
	 * </ul>
	 * 这些写法非常简洁且有效,不易出错
	 * 
	 * <br>
	 * 因此,你可以在代码中出现这样的写法:
	 * 
	 * <pre>
	 * 比如
	 * throw new IllegalArgumentException(Slf4jUtil.formatMessage(
	 * 	"callbackUrl:[{}] ,length:[{}] can't {@code >}{}",
	 * 	callbackUrl,
	 * 	callbackUrlLength,
	 * 	callbackUrlMaxLength)
	 * 
	 * 又或者
	 * return Slf4jUtil.formatMessage(" {} [{}]", encode, encode.length());
	 * </pre>
	 * 
	 * @param messagePattern
	 *            message的格式,比如 callbackUrl:[{}] ,length:[{}]
	 * @param args
	 *            参数
	 * @return format之后的结果
	 * @see org.slf4j.helpers.FormattingTuple
	 * @see org.slf4j.helpers.MessageFormatter#arrayFormat(String, Object[])
	 * @see org.slf4j.helpers.MessageFormatter#arrayFormat(String, Object[])
	 * @see org.slf4j.helpers.FormattingTuple#getMessage()
	 */
	public static String formatMessage(String messagePattern,Object...args){
		FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messagePattern, args);
		String formatMessage = formattingTuple.getMessage();
		return formatMessage;
	}
}
