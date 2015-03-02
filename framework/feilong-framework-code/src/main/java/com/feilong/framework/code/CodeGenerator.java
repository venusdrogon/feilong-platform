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
package com.feilong.framework.code;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.RandomUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 订单编号生成器(要确保每个订单不编码不重复)<br>
 * 不能100%的保证订单号唯一,各个商城按照需求 定制retry机制
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 4:52:16 PM
 * @version 1.1 2014-3-20 16:33 add {@link CodeGenerator#createOrderCode(Long, Long)}
 */
public class CodeGenerator{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(CodeGenerator.class);

	/** The debug flag. */
	private static boolean		debugFlag	= true;

	// ************************************************************
	/**
	 * 创建支付交易号码.
	 * 
	 * @param tradeId
	 *            所属交易
	 * @param countTradeNo
	 *            数据库中这笔交易存在的交易号码数量
	 * @return 需要创建的交易号码
	 */
	public static String createTradeNo(Long tradeId,Integer countTradeNo){
		Long a = 1000000 + tradeId;
		Integer b = countTradeNo + 1;
		return StringUtil.format("%08d", a) + "" + StringUtil.format("%04d", b);
	}

	// ************************************************************
	/**
	 * 创建订单号(不含shopid).
	 * 
	 * @param buyId
	 *            买家id
	 * @return 订单号码
	 * @deprecated
	 */
	@Deprecated
	public static String createOrderCode(Long buyId){
		Long sellerId = null;
		return createOrderCode(buyId, sellerId);
	}

	/**
	 * 创建订单号.
	 * 
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @return 订单号
	 */
	public static String createOrderCode(Long buyerId,Long sellerId){
		Date now = new Date();
		return createOrderCode(now, buyerId, sellerId);
	}

	/**
	 * 创建订单号.
	 * 
	 * @param date
	 *            the date
	 * @param buyerId
	 *            the member id
	 * @return the string
	 * @deprecated
	 */
	@Deprecated
	public static String createOrderCode(Date date,Long buyerId){
		Long sellerId = null;
		return createOrderCode(date, buyerId, sellerId);
	}

	/**
	 * 创建订单号.
	 * 
	 * @param date
	 *            the date
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @return the string
	 */
	public static String createOrderCode(Date date,Long buyerId,Long sellerId){
		CodeType codeType = CodeType.CREATEORDER;
		return _generatorCode(date, buyerId, sellerId, codeType);
	}

	// *****************************************************************************
	/**
	 * 创建退单编号
	 * <ul>
	 * <li>退单编号相比较订单而言,同一个人同时并发操作两笔退单的几率会小很多</li>
	 * <li></li>
	 * </ul>
	 * .
	 * 
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @return the string
	 */
	public static String createReturnOrderCode(Long buyerId,Long sellerId){
		Date date = new Date();
		CodeType codeType = CodeType.RETURNORDER;
		return _generatorCode(date, buyerId, sellerId, codeType);
	}

	// *****************************************************************************

	/**
	 * 底层生成code 方法.
	 * 
	 * @param date
	 *            the date
	 * @param buyerId
	 *            买家id
	 * @param sellerId
	 *            卖家id
	 * @param codeType
	 *            the code type
	 * @return the string
	 */
	private static String _generatorCode(Date date,Long buyerId,Long sellerId,CodeType codeType){

		boolean needShopId = Validator.isNotNullOrEmpty(sellerId);

		// int intervalDay = DateUtil.getIntervalDay(date, codeType.getStartDate());
		int intervalHour = DateUtil.getIntervalHour(date, codeType.getStartDate());

		// String dayOfYear = StringUtil.format("%03d", DateUtil.getDayOfYear(date));
		// String secondOfDay = StringUtil.format("%05d", DateUtil.getSecondOfDay(date));
		String intervalHourString = StringUtil.format("%05d", intervalHour);

		// **********************************************************************************

		String buyerIdFormat = "";
		String sellerIdFormat = "";

		// 时间戳
		String yy = DateUtil.date2String(date, DatePattern.yy);
		String mmss = DateUtil.date2String(date, DatePattern.mmss);

		String hourOfYear = StringUtil.format("%04d", DateUtil.getHourOfYear(date));

		// ****************两位随机数***************************************
		String randomString = getRandom(codeType);

		// **********************************************************
		buyerIdFormat = formatLastValue(buyerId, codeType.getBuyerIdLastLength());

		if (needShopId){
			sellerIdFormat = formatLastValue(sellerId, codeType.getShopIdLastLength());
		}

		// **********************************************************
		StringBuilder sb = new StringBuilder();

		switch (codeType) {

			case CREATEORDER:// 创建订单

				if (needShopId){
					sb.append(debugSeparator() + codeType.getPrefix());// 1
					sb.append(debugSeparator() + yy);// 2
					sb.append(debugSeparator() + hourOfYear);// 4
					sb.append(debugSeparator() + mmss);// 4
					sb.append(debugSeparator() + randomString);// randomNumberLength
					sb.append(debugSeparator() + sellerIdFormat);// shopIdLastLength
					sb.append(debugSeparator() + buyerIdFormat);// buyerIdLastLength
				}

				// 先不考虑不含shopid的故事
				// else{
				// sb.append(debugSeparator() + codeType.getPrefix());// 1
				// sb.append(debugSeparator() + yy);// 2
				// sb.append(debugSeparator() + hourOfYear);// 4
				// sb.append(debugSeparator() + mmss);// 4
				// sb.append(debugSeparator() + randomString);// randomNumberLength
				// sb.append(debugSeparator() + buyerIdFormat);// buyerIdLastLength
				// }
				break;

			case RETURNORDER:// 退单
				if (needShopId){
					sb.append(debugSeparator() + codeType.getPrefix());// 1
					sb.append(debugSeparator() + intervalHourString);// 5
					sb.append(debugSeparator() + mmss);// 4
					sb.append(debugSeparator() + randomString);// randomNumberLength
					sb.append(debugSeparator() + sellerIdFormat);// shopIdLastLength
					sb.append(debugSeparator() + buyerIdFormat);// buyerIdLastLength
				}
				// 先不考虑不含shopid的故事
				// else{
				// sb.append(debugSeparator() + codeType.getPrefix());// 1
				// sb.append(debugSeparator() + intervalHourString);// 5
				// sb.append(debugSeparator() + randomString);// randomNumberLength
				// sb.append(debugSeparator() + buyerIdFormat);// buyerIdLastLength
				// }
				break;
			default:
				throw new UnsupportedOperationException("codeType:" + codeType + " not support!");
		}

		String result = sb.toString() + debugLength(sb);

		if (log.isDebugEnabled()){
			log.debug(
					"{}-->{},buyerId:[{}],sellerId:[{}],codeType:[{}]",
					DateUtil.date2String(date, DatePattern.COMMON_DATE_AND_TIME_WITH_MILLISECOND),
					result,
					buyerId,
					sellerId,
					codeType);
		}

		return result;

	}

	/**
	 * 随机数
	 * 
	 * @param codeType
	 * @return
	 */
	private static String getRandom(CodeType codeType){
		int randomNumberLength = codeType.getRandomNumberLength();
		String randomString = RandomUtil.createRandomWithLength(randomNumberLength) + "";
		return randomString;
	}

	// ******************************************************************************************

	/**
	 * Debug.
	 * 
	 * @return the string
	 */
	private static String debugSeparator(){
		String aString = debugFlag ? " " : "";
		return aString;
	}

	/**
	 * 显示长度.
	 * 
	 * @param sb
	 *            the sb
	 * @return the string
	 */
	private static String debugLength(StringBuilder sb){
		return debugFlag ? " (" + (sb.length() - StringUtil.searchTimes(sb.toString(), " ")) + ")" : "";
	}

	// ******************************************************************************************
	/**
	 * 格式化值.
	 * 
	 * @param value
	 *            the value
	 * @param lastLength
	 *            the last length
	 * @return <ul>
	 *         <li>如果 value 的长度> lastLength,那么截取后lastLength 位数</li>
	 *         <li>如果 value 的长度= lastLength,那么直接返回value字符串格式</li>
	 *         <li>如果 value 的长度< lastLength,那么数字前面补0返回</li>
	 *         </ul>
	 */
	private static String formatLastValue(Long value,int lastLength){
		String valueFormat = "";

		String valueString = value.toString();
		int valueLength = valueString.length();

		if (valueLength > lastLength){
			valueFormat = StringUtil.substringLast(valueString, lastLength);
		}else if (valueLength == lastLength){
			valueFormat = valueString;
		}else if (valueLength < lastLength){
			valueFormat = StringUtil.format("%0" + lastLength + "d", value);
		}
		return valueFormat;
	}
}
