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
package com.feilong.commons.core.util;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.text.NumberFormatUtil;

/**
 * 处理int,Integer,long,BigDecimal 等数据类型.
 * 
 * @author 金鑫 2010-3-11 下午02:27:59
 * @since 1.0
 */
public final class NumberUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(NumberUtil.class);

	/** Don't let anyone instantiate this class. */
	private NumberUtil(){}

	/**
	 * 获得 除法结果one/two,四舍五入取整,不需要再次toZhengShu转换了
	 * <p>
	 * 当two 是空或者是0的时候,直接返回one<br>
	 * 否则返回除法结果one/two,四舍五入取整
	 * 
	 * @param one
	 *            除数
	 * @param two
	 *            被除数,自动转成BigDecimal做除法运算
	 * @return 当two 是空或者是0的时候,直接返回one<br>
	 *         否则返回除法结果one/two,四舍五入取整
	 */
	public final static BigDecimal getDivideValueToZhengShu(BigDecimal one,Object two){
		return getDivideValue(one, two, 0);
	}

	/**
	 * 小学学的 四舍五入的方式 设置小数点位数<br>
	 * 被舍入部分>=0.5向上 否则向下
	 * 
	 * @param number
	 *            number
	 * @param scale
	 *            小数点位数
	 * @return the big decimal
	 */
	public final static BigDecimal setHalfUpRounding(BigDecimal number,int scale){
		return number.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 获得 除法结果one/two,四舍五入,小数位数指定
	 * <p>
	 * 当two 是空或者是0的时候,直接返回one<br>
	 * 否则返回除法结果one/two,四舍五入,小数位数指定.
	 * 
	 * @param one
	 *            除数
	 * @param two
	 *            被除数,自动转成BigDecimal做除法运算
	 * @param scale
	 *            要返回的 BigDecimal 商的标度,小数的位数
	 * @return 当two 是空或者是0的时候,直接返回one<br>
	 *         否则返回除法结果one/two,四舍五入,小数位数指定
	 */
	public final static BigDecimal getDivideValue(BigDecimal one,Object two,int scale){
		if (!isSpecificNumber(two, "0")){
			// 不能直接one.divide(two) 避免 exception:Non-terminating decimal expansion; no exact representable decimal result
			// 应该指定scale和roundingMode，保证对于无限小数有足够的范围来表示结果。
			return one.divide(new BigDecimal(two.toString()), scale, BigDecimal.ROUND_HALF_UP);
		}
		return one;
	}

	/**
	 * 获得两个数的乘积.
	 * 
	 * @param one
	 *            乘数
	 * @param two
	 *            被乘数
	 * @param scale
	 *            标度,小数的位数,四舍五入
	 * @return 获得两个数的乘积
	 */
	public final static BigDecimal getMultiplyValue(BigDecimal one,Object two,int scale){
		if (Validator.isNotNullOrEmpty(two)){
			BigDecimal result = one.multiply(new BigDecimal(two.toString()));
			return setHalfUpRounding(result, scale);
		}
		return one;
	}

	/**
	 * 获得两个数的和,自动判断null的情况.
	 * 
	 * @param one
	 *            第一个数
	 * @param two
	 *            第二个数
	 * @return <ul>
	 *         <li>如果两个数都是 null,则返回null</li>
	 *         <li>第一个数是null,第二个数不是null,则,将第二个数转成BigDecimal 返回</li>
	 *         <li>第一个数不是null,第二个数是null,则直接返回第一个数</li>
	 *         <li>其他情况(两个数都不为空),返回 第一个数+第二个数</li>
	 *         </ul>
	 * @since 1.0
	 */
	public final static BigDecimal getAddValue(BigDecimal one,Object two){
		// 如果两个数都是 null,则返回null
		if (Validator.isNullOrEmpty(one) && Validator.isNullOrEmpty(two)){
			return null;
		}
		// 第一个数是null,第二个数不是null,则,将第二个数转成BigDecimal 返回
		if (Validator.isNullOrEmpty(one) && !Validator.isNullOrEmpty(two)){
			return new BigDecimal(two.toString());
		}
		// 第一个数不是null,第二个数是null,则直接返回第一个数
		else if (!Validator.isNullOrEmpty(one) && Validator.isNullOrEmpty(two)){
			return one;
		}else{
			// 其他情况其他情况(两个数都不为空),返回 第一个数+第二个数
			return one.add(new BigDecimal(two.toString()));
		}
	}

	/**
	 * 四舍五入,取整,无小数
	 * 
	 * <pre>
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.02));
	 * assertEquals(&quot;89&quot;, NumberUtil.toZhengShu(88.520));
	 * assertEquals(&quot;89&quot;, NumberUtil.toZhengShu(88.820f));
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.4999f));
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.4999d));
	 * assertEquals(null, NumberUtil.toZhengShu(null));
	 * </pre>
	 * 
	 * @param number
	 *            number,可以是字符串类型的数字,也可以是任一number类型
	 * @return 四舍五入,取整,无小数
	 */
	public final static String toZhengShu(Object number){
		if (Validator.isNotNullOrEmpty(number)){
			BigDecimal bigDecimal = new BigDecimal(number.toString());
			return toZhengShu(bigDecimal).toString();
		}
		return null;
	}

	/**
	 * 四舍五入,取整,无小数
	 * 
	 * <pre>
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.02));
	 * assertEquals(&quot;89&quot;, NumberUtil.toZhengShu(88.520));
	 * assertEquals(&quot;89&quot;, NumberUtil.toZhengShu(88.820f));
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.4999f));
	 * assertEquals(&quot;88&quot;, NumberUtil.toZhengShu(88.4999d));
	 * assertEquals(null, NumberUtil.toZhengShu(null));
	 * </pre>
	 * 
	 * @param number
	 *            number,可以是字符串类型的数字,也可以是任一number类型
	 * @return 四舍五入,取整,无小数
	 */
	public final static BigDecimal toZhengShu(BigDecimal number){
		if (Validator.isNotNullOrEmpty(number)){
			return setHalfUpRounding(number, 0);
		}
		return null;
	}

	/**
	 * 将数字转成百分数字符串,默认采用"00.00%"表达式,带有两位小数
	 * 
	 * <pre>
	 * 如 0.24转成24.00%
	 * </pre>
	 * 
	 * @param value
	 *            数字
	 * @return 百分数字符串,2位小数点
	 */
	public final static String toPercentWith2Point(Number value){
		return toString(value, NumberPattern.PERCENT_WITH_2POINT);
	}

	/**
	 * 将数字转成百分数字符串,不带小数点,如 0.24转成24%
	 * 
	 * @param value
	 *            数字
	 * @return 百分数字符串,不带小数点
	 */
	public final static String toPercentWithNoPoint(Number value){
		return toString(value, NumberPattern.PERCENT_WITH_NOPOINT);
	}

	/**
	 * 将数字转换成 小数点后以为为 0.0,0.5,1.0,1.5,2.0,2.5....... <br>
	 * 通常用于 评分制
	 * 
	 * @param value
	 *            数字
	 * @return 0.0,0.5,1.0,1.5,2.0,2.5.......
	 */
	public final static String toPointFive(Number value){
		long avgRankLong = Math.round(Double.parseDouble(value.toString()) * 2);
		BigDecimal avgBigDecimal = new BigDecimal((double) (avgRankLong) / 2);
		String avgRank = setHalfUpRounding(avgBigDecimal, 1).toString();
		return avgRank;
	}

	/**
	 * 将数字型转换成字符型. <br>
	 * 默认 使用 ########## 模式
	 * 
	 * @param value
	 *            the value
	 * @return String
	 */
	public final static String toString(Number value){
		return toString(value, "##########").trim();
	}

	/**
	 * 数字格式化.
	 * 
	 * @param value
	 *            值
	 * @param pattern
	 *            规则
	 * @return 格式化后的数字字符串
	 */
	public final static String toString(Number value,String pattern){
		return NumberFormatUtil.format(value, pattern);
	}

	// *****************************************************************************************************
	/**
	 * 将string 类型数据转成 Long 类型.
	 * 
	 * @param value
	 *            string 类型数据
	 * @return Long 类型
	 */
	public final static Long parseLong(String value){
		return Long.parseLong(value);
	}

	// *****************************************************************************************************
	/**
	 * int类型转换成16进制字符串.
	 * 
	 * @param i
	 *            int值
	 * @return int类型转换成16进制字符串
	 */
	public final static String intToHexString(int i){
		return Integer.toHexString(i);
	}

	/**
	 * 16进制字符串转成int类型.
	 * 
	 * @param hexString
	 *            16进制字符串
	 * @return int类型
	 */
	public final static int hexStringToInt(String hexString){
		return Integer.parseInt(hexString, 16);
	}

	/**
	 * 判断一个Object 类型的 value,是否是一个特定的数<br>
	 * 系统自动将value 装成BigDecimal,并将specificNumber 也装成BigDecimal ,两个BigDecimal 进行compareTo,<br>
	 * 如果是0 ,则返回true.
	 * 
	 * @param value
	 *            Object 类型的 value,类型必须是 Number 或者 String
	 * @param specificNumber
	 *            一个特定的数
	 * @return 系统自动将value 装成BigDecimal,并将specificNumber 也装成BigDecimal ,两个BigDecimal 进行compareTo,<br>
	 *         如果是0 ,则返回true
	 */
	public final static boolean isSpecificNumber(Object value,String specificNumber){
		boolean flag = false;
		if (Validator.isNotNullOrEmpty(value)){
			String valueString = value.toString();
			// Number /String
			if (value instanceof Number || value instanceof String){
				BigDecimal bigDecimal = new BigDecimal(valueString);
				int i = bigDecimal.compareTo(new BigDecimal(specificNumber));
				flag = (i == 0);
			}
		}
		if (!flag){
			if (log.isDebugEnabled()){
				// String format = StringUtil.format("value is :%s,but specificNumber is %s", value, specificNumber);
				// log.debug(format);
			}
		}
		return flag;
	}
}
