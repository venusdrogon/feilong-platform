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
package com.feilong.commons.core;

import static com.feilong.commons.core.configure.ResourceBundleUtil.getValue;

/**
 * 配置文件.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午05:58:59
 * @since 1.0
 */
public class PropertiesConstants{

	/**
	 * feilong-core-message 路径,<br>
	 * 包名+名称,并去除后缀,默认classpath下面 <code>{@value}</code>
	 */
	public final static String	$feilong_core_message						= "messages/feilong-core-message";

	// ******************************************************************************************************************************************************************************************
	/** 星期. */
	public final static String	config_date_week							= getValue($feilong_core_message, "config_date_week");

	/** 昨天. */
	public final static String	config_date_yesterday						= getValue($feilong_core_message, "config_date_yesterday");

	/** 前天. */
	public final static String	config_date_theDayBeforeYesterday			= getValue($feilong_core_message, "config_date_theDayBeforeYesterday");

	/** 天. */
	public final static String	config_date_day								= getValue($feilong_core_message, "config_date_day");

	/** 小时. */
	public final static String	config_date_hour							= getValue($feilong_core_message, "config_date_hour");

	/** 分钟. */
	public final static String	config_date_minute							= getValue($feilong_core_message, "config_date_minute");

	/** 秒. */
	public final static String	config_date_second							= getValue($feilong_core_message, "config_date_second");

	/** 毫秒. */
	public final static String	config_date_millisecond						= getValue($feilong_core_message, "config_date_millisecond");

	// **************************************************************************************************************************************************************
	/** 数字和小写的字母. */
	public final static String	config_validateCode_numbersAndLittleLetters	= getValue($feilong_core_message, "config_validateCode_numbersAndLittleLetters");

	/** 数字和大小写字母. */
	public final static String	config_numbersAndAllLetters					= getValue($feilong_core_message, "config_numbersAndAllLetters");					;

	/** 所有的数字. */
	public final static String	config_numbers								= getValue($feilong_core_message, "config_numbers");
}
