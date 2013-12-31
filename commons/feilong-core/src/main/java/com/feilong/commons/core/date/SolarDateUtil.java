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
package com.feilong.commons.core.date;

import java.util.Date;

/**
 * 阳历日期
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-2-8 下午04:59:48
 * @since 1.0
 */
public final class SolarDateUtil{

	/**
	 * 阳历日期转成农历<br>
	 * 如 SolarDateUtil.toLundar(2013, 1, 8) 返回20121126
	 * 
	 * @param date
	 * @return
	 */
	public static String toLundar(Date date){
		int year = DateUtil.getYear(date);
		int month = DateUtil.getMonth(date);
		int day = DateUtil.getDayOfMonth(date);
		return toLundar(year, month, day);

	}

	/**
	 * 阳历日期转成农历<br>
	 * 如 SolarDateUtil.toLundar(2013, 1, 8) 返回20121126
	 * 
	 * @param year
	 *            阳历年
	 * @param month
	 *            阳历月
	 * @param day
	 *            阳历日
	 * @return 农历时间
	 */
	public static String toLundar(int year,int month,int day){
		int iLDay;
		int iLMonth;
		int iLYear;
		int iOffsetDays = CalendarUtil.getDayOfYear(year, month, day);
		int iLeapMonth = LunarDateUtil._getLeapMonth(year);
		if (iOffsetDays < DateDictionary.solarAndLunarOffsetTable[year - 1901]){
			iLYear = year - 1;
			iOffsetDays = DateDictionary.solarAndLunarOffsetTable[year - 1901] - iOffsetDays;
			iLDay = iOffsetDays;
			for (iLMonth = 12; iOffsetDays > LunarDateUtil.getLunarMonthMaxDays(iLYear, iLMonth); --iLMonth){
				iLDay = iOffsetDays;
				iOffsetDays -= LunarDateUtil.getLunarMonthMaxDays(iLYear, iLMonth);
			}
			if (0 == iLDay){
				iLDay = 1;
			}else{
				iLDay = LunarDateUtil.getLunarMonthMaxDays(iLYear, iLMonth) - iOffsetDays + 1;
			}
		}else{
			iLYear = year;
			iOffsetDays -= DateDictionary.solarAndLunarOffsetTable[year - 1901];
			iLDay = iOffsetDays + 1;
			for (iLMonth = 1; iOffsetDays >= 0; ++iLMonth){
				iLDay = iOffsetDays + 1;
				iOffsetDays -= LunarDateUtil.getLunarMonthMaxDays(iLYear, iLMonth);
				if ((iLeapMonth == iLMonth) && (iOffsetDays > 0)){
					iLDay = iOffsetDays;
					iOffsetDays -= LunarDateUtil.getLunarMonthMaxDays(iLYear, iLMonth + 12);
					if (iOffsetDays <= 0){
						iLMonth += 12 + 1;
						break;
					}
				}
			}
			iLMonth--;
		}
		return "" + iLYear + //
				(iLMonth > 9 ? "" + iLMonth : "0" + iLMonth) + //
				(iLDay > 9 ? "" + iLDay : "0" + iLDay);
	}

}
