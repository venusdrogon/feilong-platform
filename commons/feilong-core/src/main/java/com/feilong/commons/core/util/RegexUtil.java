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
package com.feilong.commons.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类.
 * 
 * @author 金鑫 2010-1-13 下午02:33:11
 * @see Pattern
 * @see Matcher
 * @see RegexPattern
 * @since 1.0.0
 */
public final class RegexUtil{

	/** Don't let anyone instantiate this class. */
	private RegexUtil(){}

	// ************************************************************************************
	/**
	 * Match.
	 * 
	 * @param regexPattern
	 *            正则表达式字符串
	 * @param str
	 *            要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	public static boolean match(String regexPattern,String str){
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	// /**
	// * 验证输入汉字.
	// *
	// * @param str
	// * 待验证的字符串
	// * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	// */
	// public static boolean isChinese(String str){
	// String regex = "^[\u4e00-\u9fa5],{0,}$";
	// return match(regex, str);
	// }

}
