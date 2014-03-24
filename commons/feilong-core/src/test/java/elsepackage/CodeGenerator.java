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
package elsepackage;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.RandomUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 订单编号生成器(要确保每个订单不编码不重复).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 4:52:16 PM
 * @version 1.1 2014-3-20 16:33 add {@link CodeGenerator#createOrderCode(Long, Long)}
 */
public class CodeGenerator{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(CodeGenerator.class);

	/** The start date. */
	private static Date			startDate			= DateUtil.string2Date("2014-01-01 00:00:00", DatePattern.commonWithTime);

	/** 截取用户id 后面位数. */
	private static int			buyerIdLastLength	= 3;

	/** 截取shop id 后面位数. */
	private static int			shopIdLastLength	= 3;

	/** 随机数. */
	private static int			randomNumberLength	= 2;

	/**
	 * 创建订单号(不含shopid)
	 * 
	 * @param buyId
	 *            买家id
	 * @return 订单号码
	 */
	public static String createOrderCode(Long buyId){
		Long shopId = null;
		return createOrderCode(buyId, shopId);
	}

	/**
	 * 创建订单号
	 * 
	 * @param buyerId
	 *            买家id
	 * @param shopId
	 *            店铺id
	 * @return 订单号
	 */
	public static String createOrderCode(Long buyerId,Long shopId){
		Date now = new Date();
		return createOrderCode(now, buyerId, shopId);
	}

	/**
	 * 创建订单号
	 * 
	 * @param date
	 *            the date
	 * @param buyerId
	 *            the member id
	 * @return the string
	 */
	public static String createOrderCode(Date date,Long buyerId){
		Long shopId = null;
		return createOrderCode(date, buyerId, shopId);
	}

	/**
	 * 创建订单号
	 * 
	 * @param date
	 *            the date
	 * @param buyerId
	 *            the member id
	 * @param shopId
	 *            店铺id
	 * @return the string
	 */
	public static String createOrderCode(Date date,Long buyerId,Long shopId){

		boolean needShopId = Validator.isNotNullOrEmpty(shopId);

		int intervalDay = DateUtil.getIntervalDay(date, startDate);
		int intervalHour = DateUtil.getIntervalHour(date, startDate);

		String dayOfYear = StringUtil.format("%03d", DateUtil.getDayOfYear(date));
		String secondOfDay = StringUtil.format("%05d", DateUtil.getSecondOfDay(date));

		// **********************************************************************************

		String buyerIdFormat = "";
		String shopIdFormat = "";

		// 时间戳
		String yy = DateUtil.date2String(date, DatePattern.yy);
		String mmss = DateUtil.date2String(date, DatePattern.mmss);

		String hourOfYear = StringUtil.format("%04d", DateUtil.getHourOfYear(date));

		// ****************两位随机数***************************************
		String randomString = RandomUtil.createRandomWithLength(randomNumberLength) + "";

		// **********************************************************
		buyerIdFormat = formatLastValue(buyerId, buyerIdLastLength);

		if (needShopId){
			shopIdFormat = formatLastValue(shopId, shopIdLastLength);
		}

		// **********************************************************
		StringBuilder sb = new StringBuilder();

		if (needShopId){
			sb.append(" " + yy);// 2
			sb.append(" " + hourOfYear);// 4
			//sb.append(" " + mmss);// 4
			sb.append(" " + randomString);// randomNumberLength
			sb.append(" " + shopIdFormat);// shopIdLastLength
			sb.append(" " + buyerIdFormat);// buyerIdLastLength
		}else{
			sb.append(" " + yy);// 2
			sb.append(" " + hourOfYear);// 4
			sb.append(" " + mmss);// 4
			sb.append(" " + randomString);// randomNumberLength
			sb.append(" " + buyerIdFormat);// buyerIdLastLength
		}

		return sb.toString() + " (" + (sb.length() - StringUtil.searchTimes(sb.toString(), " ")) + ")";
	}

	/**
	 * @param value
	 * @return
	 */
	private static String formatLastValue(Long value,int lastLength){
		// 最后一部分 规则为 取用户id后 四位数字, 位数不足 前置补零
		String idFormat = "";
		String idString = value.toString();

		if (idString.length() > lastLength){
			idFormat = StringUtil.substringLast(idString, lastLength);
		}else{
			idFormat = idString;
		}
		idFormat = StringUtil.format("%0" + lastLength + "d", Long.parseLong(idFormat));
		return idFormat;
	}
}
