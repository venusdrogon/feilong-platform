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
package com.feilong.commons.core.date;

/**
 * 时间间隔(一般以秒为单位) <br>
 * Integer.MAX_VALUE:2147483647<br>
 * Integer.MIN_VALUE:-2147483648<br>
 * 一年数据为 31536000,所以 Integer 最大为 68.096259734906 年
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-18 下午2:57:14
 */
public final class TimeInterval{

	private TimeInterval(){}

	/**
	 * 1分钟 60s
	 */
	public static final Integer	SECONDS_PER_MINUTE	= 60;

	/**
	 * 1小时 60 * 60=3600
	 */
	public static final Integer	SECONDS_PER_HOUR	= SECONDS_PER_MINUTE * 60;

	/**
	 * 1天 60 * 60 * 24=86400
	 */
	public static final Integer	SECONDS_PER_DAY		= SECONDS_PER_HOUR * 24;

	/**
	 * 一个星期 60 * 60 * 24 * 7= 604 800
	 */
	public static final Integer	SECONDS_PER_WEEK	= SECONDS_PER_DAY * 7;

	/**
	 * 30天 一个月 60 * 60 * 24 * 30= 2592000<br>
	 * 估值,没有精确一个月28/29天 还是30 31天
	 */
	public static final Integer	SECONDS_PER_MONTH	= SECONDS_PER_DAY * 30;

	/**
	 * 365天 1年 60 * 60 * 24 * 365=31536000<br>
	 * Integer.MAX_VALUE:2147483647<br>
	 * Integer.MIN_VALUE-2147483648<br>
	 * 一年数据为 31536000,所以 Integer 最大为 68.096259734906 年
	 */
	public static final Integer	SECONDS_PER_YEAR	= SECONDS_PER_DAY * 365;
}
