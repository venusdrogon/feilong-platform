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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

/**
 * slf4j util
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 30, 2013 2:24:01 AM
 */
public final class Slf4jUtil{

	private static final Logger	log	= LoggerFactory.getLogger(Slf4jUtil.class);

	/**
	 * 格式化字符串
	 * 
	 * @param messagePattern
	 * @param args
	 * @return
	 */
	public static String formatMessage(String messagePattern,Object...args){
		//FormattingTuple formattingTuple = MessageFormatter.format(messagePattern, args);
		FormattingTuple formattingTuple = MessageFormatter.arrayFormat(messagePattern, args);
		String formatMessage = formattingTuple.getMessage();
		return formatMessage;
	}
}
