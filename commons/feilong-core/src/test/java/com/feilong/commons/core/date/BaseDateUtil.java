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
package com.feilong.commons.core.date;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class BaseDateUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月3日 下午11:37:43
 * @since 1.0.7
 */
abstract class BaseDateUtil{

	private static final Logger	log					= LoggerFactory.getLogger(BaseDateUtil.class);

	/** <code>{@value}</code> code. */
	static final String			fromString			= "2011-03-5 23:31:25.456";

	/** The to string. */
	static final String			toString			= "2011-03-10 01:30:24.895";

	/** The now. */
	static final Date			now					= new Date();

	/** The current year begin. */
	static final Date			currentYearBegin	= DateUtil.getFirstDateOfThisYear(now);

	/** The current year end. */
	static final Date			currentYearEnd		= DateUtil.getLastDateOfThisYear(now);

	/** The current year end. */
	static final Date			testDate			= DateUtil.string2Date("2014-12-31 01:30:24.895", DatePattern.commonWithMillisecond);

	/**
	 * Prints the.
	 * 
	 * @param date
	 *            the date
	 */
	protected void logDate(Date date){
		log.debug(DateUtil.date2String(date, DatePattern.commonWithMillisecond));
	}
}
