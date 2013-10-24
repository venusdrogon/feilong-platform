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
