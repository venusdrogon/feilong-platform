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

/**
 * 正则表达式格式 .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 2, 2014 6:04:23 PM
 * @version 1.0.5 2014-5-4 00:37 change to interface
 */
public interface RegexPattern{

	/** email 的正则表达式 <code>{@value}</code>. */
	String	EMAIL				= "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	/** IP 的正则表达式 <code>{@value}</code>. */
	String	IP					= "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

	/** 电话号码 <code>{@value}</code>. */
	String	TELEPHONE			= "^(\\d{3,4}-)?\\d{6,8}$";

	/** 手机号码 <code>{@value}</code>. */
	String	MOBILEPHONE			= "^[1]+[3,5]+\\d{9}$";

	/** 网址Url 链接 <code>{@value}</code>. */
	String	URLLINK				= "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

	/** 邮政编码 <code>{@value}</code>. */
	String	ZIPCODE				= "^\\d{6}$";

	/** 所有都是字母 <code>{@value}</code>. */
	String	LETTER				= "^[A-Za-z]+$";

	/** 小写字母 <code>{@value}</code>. */
	String	LOWERCASELETTER		= "^[a-z]+$";

	/** 大写字母 <code>{@value}</code>. */
	String	UPPERCASELETTER		= "^[A-Z]+$";

	/**
	 * 两位数小数 <code>{@value}</code>
	 * 
	 * <pre>
	 * 可以是200 也可以是200.00 不可以是 200.0
	 * </pre>
	 */
	String	DECIMAL_TWODIGIT	= "^[0-9]+(.[0-9]{2})?$";

	/** 纯数字 <code>{@value}</code>. */
	String	NUMBER				= "^[0-9]*$";

	// alpha numeric space
	/**
	 * 字母和数字 (alpha numeric) <code>{@value}</code>
	 */
	String	AN					= "^[0-9a-zA-Z]+$";

	/**
	 * 字母和数字和空格(alpha numeric space)<code>{@value}</code>
	 */
	String	ANS					= "^[0-9a-zA-Z ]+$";

	// /** 验证输入一个月的31天 <code>{@value}</code>. */
	// String Day = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

	// /** 验证输入一年的12个月 <code>{@value}</code>. */
	// String Month = "^(0?[[1-9]|1[0-2])$";

	// 严格验证时间格式的(匹配[2002-01-31], [1997-04-30], [2004-01-01])不匹配([2002-01-32], [2003-02-29], [04-01-01])
	// String regex =
	// "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((01,3-9])|(1[0-2]))-(29|30)))))$";
	// 没加时间验证的YYYY-MM-DD
	// String regex =
	// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";
	// 加了时间验证的YYYY-MM-DD 00:00:00
	// String Date =
	// "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

	// /** 非零的正整数 <code>{@value}</code>. */
	// String IntNumber = "^\\+?[1-9][0-9]*$";

}