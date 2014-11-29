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

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class NumberUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-24 下午02:54:39
 */
public class NumberUtilTest implements NumberPattern{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(NumberUtilTest.class);

	/**
	 * Gets the progress.
	 * 
	 */
	@Test
	public void getProgress(){
		assertEquals("100%", NumberUtil.getProgress(5, 5, PERCENT_WITH_NOPOINT));
		assertEquals("100.00%", NumberUtil.getProgress(5, 5, PERCENT_WITH_2POINT));
		assertEquals("100.0%", NumberUtil.getProgress(5, 5, PERCENT_WITH_1POINT));
		assertEquals("50%", NumberUtil.getProgress(5, 10, PERCENT_WITH_NOPOINT));
		assertEquals("50.00%", NumberUtil.getProgress(5, 10, PERCENT_WITH_2POINT));
		assertEquals("50.0%", NumberUtil.getProgress(5, 10, PERCENT_WITH_1POINT));
		assertEquals("30.0%", NumberUtil.getProgress(3, 10, PERCENT_WITH_1POINT));
		assertEquals("33.3%", NumberUtil.getProgress(1, 3, PERCENT_WITH_1POINT));
		assertEquals("66.7%", NumberUtil.getProgress(2, 3, PERCENT_WITH_1POINT));
		assertEquals("67%", NumberUtil.getProgress(2, 3));
	}

	/**
	 * Gets the progress1.
	 * 
	 */
	@Test()
	//expected = NullPointerException.class
	public void getProgress1(){
		NumberUtil.getProgress(null, 5, PERCENT_WITH_NOPOINT);
	}

	/**
	 * Gets the progress2.
	 * 
	 */
	@Test(expected = NullPointerException.class)
	public void getProgress2(){
		NumberUtil.getProgress(5, null, PERCENT_WITH_NOPOINT);
	}

	/**
	 * 获得 progress.
	 *
	 * @param current
	 *            the current
	 * @param total
	 *            the total
	 * @param numberPattern
	 *            the number pattern
	 * @return the progress
	 */
	public final static String getProgress(Number current,Number total,String numberPattern){
		if (null == current){
			return "0001";
		}
		if (null == total){
			return "0002";
		}

		if (current.intValue() <= 0){
			return "0003";
		}
		if (total.intValue() <= 0){
			return "0004";
		}

		if (current.doubleValue() > total.doubleValue()){
			return "0005";
		}
		// XXX
		int scale = 8;
		BigDecimal bigDecimalCurrent = new BigDecimal(current.toString());
		BigDecimal divideValue = NumberUtil.getDivideValue(bigDecimalCurrent, total, scale);
		return NumberUtil.toString(divideValue, numberPattern);
	}

	/**
	 * Gets the progress3.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getProgress3(){
		NumberUtil.getProgress(-5, 5, PERCENT_WITH_NOPOINT);
	}

	/**
	 * Gets the progress4.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getProgress4(){
		NumberUtil.getProgress(5, -5, PERCENT_WITH_NOPOINT);
	}

	/**
	 * Gets the progress5.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void getProgress5(){
		NumberUtil.getProgress(5, 4, PERCENT_WITH_NOPOINT);
	}

	/**
	 * Compare to.
	 */
	@Test
	public void compareTo(){
		BigDecimal totalFee = new BigDecimal(-0.01);
		boolean isLEZero = totalFee.compareTo(BigDecimal.ZERO) == -1 || totalFee.compareTo(BigDecimal.ZERO) == 0;
		assertEquals(true, isLEZero);
	}

	/**
	 * Testadd.
	 */
	@Test
	public void testadd(){
		BigDecimal a = new BigDecimal(19);
		BigDecimal b = new BigDecimal(20);

		BigDecimal temp = b;

		int j = 50;
		int scale = 8;
		for (int i = 0; i < j; ++i){
			temp = NumberUtil.getDivideValue(a.add(temp), 2, scale);
			log.info("{}次:{}", i, temp);
		}

	}

	/**
	 * Convert number to string2.
	 */
	@Test
	public void convertNumberToString2(){
		DecimalFormat decimalFormat = new DecimalFormat("00");
		BigDecimal number = new BigDecimal(88.50);
		decimalFormat.setRoundingMode(RoundingMode.CEILING);
		// decimalFormat.setMaximumFractionDigits(2);
		// decimalFormat.setMinimumFractionDigits(2);
		// maxFractionDigits
		number.setScale(2, BigDecimal.ROUND_HALF_UP);
		log.info(number.toString());
		log.info(decimalFormat.format(number));
	}

	/**
	 * Test convert number to string.
	 */
	@Test
	public void toString1(){
		String pattern = "#######.########";
		assertEquals("88.02", NumberUtil.toString(88.02, pattern));
		assertEquals("88.02", NumberUtil.toString(88.020, pattern));
		assertEquals("88.02002", NumberUtil.toString(88.02002, pattern));
		assertEquals("88", NumberUtil.toString(88, pattern));
		assertEquals("88.02000005", NumberUtil.toString(88.02000005, pattern));
		assertEquals("88.025", NumberUtil.toString(88.02500000, pattern));
		assertEquals("88.0200005", NumberUtil.toString(88.0200005, pattern));
		assertEquals("88.002", NumberUtil.toString(88.002, pattern));
		// //######0
		log.debug(NumberUtil.toString((double) 5 / 8 * 100, "#######.###"));
		DecimalFormat df = new DecimalFormat("######0");
		log.debug(df.format(((double) 5 / Integer.valueOf(8)) * 100));

		assertEquals("C00000008", NumberUtil.toString(8, "C00000000"));

		assertEquals("0%", NumberUtil.toString(0, PERCENT_WITH_NOPOINT));

		assertEquals("24%", NumberUtil.toString(0.24f, PERCENT_WITH_NOPOINT));
		assertEquals("24.00%", NumberUtil.toString(0.24f, PERCENT_WITH_2POINT));

		Integer a = 1;
		Long b = 400L;
		assertEquals("0.25%", NumberUtil.toString((double) a / b, PERCENT_WITH_2POINT));

		assertEquals("1", NumberUtil.toString(0.8, NO_SCALE));
		assertEquals("-1", NumberUtil.toString(-0.8, NO_SCALE));
		assertEquals("-2", NumberUtil.toString(-1.8, NO_SCALE));
		assertEquals("2", NumberUtil.toString(1.8, NO_SCALE));
		assertEquals("111112", NumberUtil.toString(111111.5, NO_SCALE));
		assertEquals("111113", NumberUtil.toString(111112.5, NO_SCALE));
		assertEquals("88888888", NumberUtil.toString(88888888, NO_SCALE));
	}

	/**
	 * To no scale.
	 */
	@Test
	public void toNoScale(){
		assertEquals(new BigDecimal(123), NumberUtil.toNoScale(new BigDecimal(123.02)));
		assertEquals(new BigDecimal(123), NumberUtil.toNoScale(new BigDecimal(123.49)));
		assertEquals(new BigDecimal(124), NumberUtil.toNoScale(new BigDecimal(123.51)));
		assertEquals(new BigDecimal(-123), NumberUtil.toNoScale(new BigDecimal(-123.51)));
	}

	/**
	 * To no scale2.
	 */
	@Test
	public void toNoScale2(){
		assertEquals("88", NumberUtil.toNoScale(88.02));
		assertEquals("89", NumberUtil.toNoScale(88.520));
		assertEquals("89", NumberUtil.toNoScale(88.820f));
		assertEquals("88", NumberUtil.toNoScale(88.4999f));
		assertEquals("88", NumberUtil.toNoScale(88.4999d));
		assertEquals("-88", NumberUtil.toNoScale(-88.5999d));
		// ***********************************************************************
		assertEquals("0", NumberUtil.toNoScale(0.1));
		assertEquals("1", NumberUtil.toNoScale(0.5));
		//
		assertEquals("-1", NumberUtil.toNoScale(-0.5));
		assertEquals("0", NumberUtil.toNoScale(-0.11111111));
		assertEquals(null, NumberUtil.toNoScale(null));
		assertEquals("123", NumberUtil.toNoScale(new BigDecimal(123) + ""));
	}

	/**
	 * Gets the divide no scale value.
	 * 
	 */
	@Test
	public void getDivideNoScaleValue(){
		assertEquals(6, NumberUtil.getDivideNoScaleValue(new BigDecimal(6), 0).intValue());
		assertEquals(0, NumberUtil.getDivideNoScaleValue(new BigDecimal(0), 0).intValue());
		assertEquals(0, NumberUtil.getDivideNoScaleValue(new BigDecimal(0), 2).intValue());
		assertEquals(2, NumberUtil.getDivideNoScaleValue(new BigDecimal(6), 4).intValue());
	}

	/**
	 * Test get multiply value.
	 */
	@Test
	public void testGetMultiplyValue(){
		log.debug("MultiplyValue:" + NumberUtil.getMultiplyValue(new BigDecimal(6.25), 1.17, 5));
	}

	/**
	 * Gets the divide value.
	 * 
	 */
	@Test
	public void getDivideValue(){
		assertEquals("3.33", NumberUtil.getDivideValue(new BigDecimal(10), 3, 2));
		assertEquals("1.67", NumberUtil.getDivideValue(new BigDecimal(5), 3, 2));
		assertEquals("5", NumberUtil.getDivideValue(new BigDecimal(5), "0", 2));
	}

	/**
	 * Test.
	 */
	@Test
	public void test(){
		BigDecimal a = new BigDecimal("1.000000");
		BigDecimal b = new BigDecimal(1);
		log.debug(a.compareTo(b) + "");
		log.debug(a.equals(b) + "");
	}

	/**
	 * Testis specific number.
	 */
	@Test
	public void testisSpecificNumber(){
		assertEquals(true, NumberUtil.isSpecificNumber(0, "0"));
		assertEquals(true, NumberUtil.isSpecificNumber(0.0000, "0"));
		assertEquals(false, NumberUtil.isSpecificNumber(-0.0001, "-0"));
		assertEquals(true, NumberUtil.isSpecificNumber("-0.0000", "0"));
		assertEquals(true, NumberUtil.isSpecificNumber("0.0001", "-0.0001"));
	}

	/**
	 * Gets the adds the value.
	 * 
	 */
	@Test
	public void getAddValue(){
		assertEquals(null, NumberUtil.getAddValue(null, null));
		assertEquals(5, NumberUtil.getAddValue(null, 5));
		assertEquals(6, NumberUtil.getAddValue(new BigDecimal(6), null));
		assertEquals(11, NumberUtil.getAddValue(new BigDecimal(6), 5));
	}
}
