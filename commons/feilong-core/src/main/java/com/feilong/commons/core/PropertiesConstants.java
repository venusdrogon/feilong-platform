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
package com.feilong.commons.core;

import static com.feilong.commons.core.configure.ResourceBundleUtil.getValue;

/**
 * 配置文件<br>
 * This class defines the common PropertiesConstants ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午05:58:59
 * @since 1.0
 */
public interface PropertiesConstants{

	/**
	 * feilong-core-message 路径,<br>
	 * 包名+名称,并去除后缀,默认classpath下面 <code>{@value}</code>
	 */
	String	$feilong_core_message						= "messages/feilong-core-message";

	// ******************************************************************************************************************************************************************************************
	/** 星期. */
	String	CONFIG_DATE_WEEK							= getValue($feilong_core_message, "config_date_week");

	/** 昨天. */
	String	config_date_yesterday						= getValue($feilong_core_message, "config_date_yesterday");

	/** 前天. */
	String	config_date_theDayBeforeYesterday			= getValue($feilong_core_message, "config_date_theDayBeforeYesterday");

	/** 天. */
	String	config_date_day								= getValue($feilong_core_message, "config_date_day");

	/** 小时. */
	String	config_date_hour							= getValue($feilong_core_message, "config_date_hour");

	/** 分钟. */
	String	config_date_minute							= getValue($feilong_core_message, "config_date_minute");

	/** 秒. */
	String	config_date_second							= getValue($feilong_core_message, "config_date_second");

	/** 毫秒. */
	String	config_date_millisecond						= getValue($feilong_core_message, "config_date_millisecond");

	// **************************************************************************************************************************************************************
	/** 数字和小写的字母. */
	String	config_validateCode_numbersAndLittleLetters	= getValue($feilong_core_message, "config_validateCode_numbersAndLittleLetters");

	/** 数字和大小写字母. */
	String	config_numbersAndAllLetters					= getValue($feilong_core_message, "config_numbersAndAllLetters");					;

	/** 所有的数字. */
	String	config_numbers								= getValue($feilong_core_message, "config_numbers");
}
