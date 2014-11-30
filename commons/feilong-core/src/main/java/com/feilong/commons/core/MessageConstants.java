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
 * @deprecated 每个类自己实现
 */
@Deprecated
public interface MessageConstants{

	/**
	 * feilong-core-message 路径,<br>
	 * 包名+名称,并去除后缀,默认classpath下面 <code>{@value}</code>
	 */
	String	$FEILONG_CORE_MESSAGE			= "messages/feilong-core-message";

	// ******************************************************************************************************************************************************************************************
	/** 星期. */
	String	DATE_WEEK						= getValue($FEILONG_CORE_MESSAGE, "date_week");

	/** 昨天. */
	String	DATE_YESTERDAY					= getValue($FEILONG_CORE_MESSAGE, "date_yesterday");

	/** 前天. */
	String	DATE_THEDAY_BEFORE_YESTERDAY	= getValue($FEILONG_CORE_MESSAGE, "date_theDayBeforeYesterday");

	/** 天. */
	String	DATE_DAY						= getValue($FEILONG_CORE_MESSAGE, "date_day");

	/** 小时. */
	String	DATE_HOUR						= getValue($FEILONG_CORE_MESSAGE, "date_hour");

	/** 分钟. */
	String	DATE_MINUTE						= getValue($FEILONG_CORE_MESSAGE, "date_minute");

	/** 秒. */
	String	DATE_SECOND						= getValue($FEILONG_CORE_MESSAGE, "date_second");

	/** 毫秒. */
	String	DATE_MILLISECOND				= getValue($FEILONG_CORE_MESSAGE, "date_millisecond");
}
