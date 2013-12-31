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
package elsepackage;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.RandomUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * 订单编号 生成器(要确保每个订单不编码不重复)
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 4:52:16 PM
 */
public class SalesOrderCodeGenerator{

	private static final Logger	log					= LoggerFactory.getLogger(SalesOrderCodeGenerator.class);

	private static Date			startDate			= DateUtil.string2Date("2013-01-01 00:00:00", DatePattern.commonWithTime);

	/**
	 * 截取用户id 后面位数
	 */
	private static int			memberIdLastLength	= 3;

	/**
	 * 随机数
	 */
	private static int			randomWithLength	= 2;

	public static String create(Long memberId){// Long merchantId
		Date now = new Date();
		return create(now, memberId);
	}

	public static String create(Date date,Long memberId){

		int intervalDay = DateUtil.getIntervalDay(date, startDate);
		int intervalHour = DateUtil.getIntervalHour(date, startDate);

		String dayOfYear = StringUtil.format("%03d", DateUtil.getDayOfYear(date));
		String secondOfDay = StringUtil.format("%05d", DateUtil.getSecondOfDay(date));

		// 时间戳
		String yy = DateUtil.date2String(date, DatePattern.yy);
		String mmss = DateUtil.date2String(date, DatePattern.mmss);

		String hourOfYear = StringUtil.format("%04d", DateUtil.getHourOfYear(date));

		// ****************两位随机数***************************************
		String randomString = RandomUtil.createRandomWithLength(randomWithLength) + "";

		// **********************************************************
		// 最后一部分 规则为 取用户id后 四位数字, 位数不足 前置补零
		String memberIdFormat = "";
		String memberIdString = memberId.toString();

		if (memberIdString.length() > memberIdLastLength){
			memberIdFormat = StringUtil.substringLast(memberIdString, memberIdLastLength);
		}else{
			memberIdFormat = memberIdString;
		}
		memberIdFormat = StringUtil.format("%0" + memberIdLastLength + "d", Long.parseLong(memberIdFormat));

		// **********************************************************
		StringBuilder sb = new StringBuilder();
		// sb.append(merchantString);
		sb.append(" " + yy);
		sb.append(" " + hourOfYear);
		sb.append(" " + mmss);
		sb.append(" " + randomString);
		sb.append(" " + memberIdFormat);

		return sb.toString() + " (" + (sb.length() - StringUtil.searchTimes(sb.toString(), " ")) + ")";
	}
}
