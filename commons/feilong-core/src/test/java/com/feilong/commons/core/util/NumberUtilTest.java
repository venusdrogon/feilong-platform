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
package com.feilong.commons.core.util;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-24 下午02:54:39
 */
public class NumberUtilTest{

	private final static Logger	log	= LoggerFactory.getLogger(NumberUtilTest.class);

	@Test
	public void convertNumberToString(){
		assertEquals("C00000008", NumberUtil.toString(8, "C00000000"));
	}

	@Test
	public void compareTo(){
		BigDecimal totalFee = new BigDecimal(-0.01);
		boolean isLEZero = totalFee.compareTo(BigDecimal.ZERO) == -1 || totalFee.compareTo(BigDecimal.ZERO) == 0;
		assertEquals(true, isLEZero);
	}

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

	@Test
	public void testConvertNumberToString(){
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
	}

	@Test
	public void testConvertNumberToZhengShu(){
		assertEquals("88", NumberUtil.toZhengShu(88.02));
		assertEquals("89", NumberUtil.toZhengShu(88.520));
		assertEquals("89", NumberUtil.toZhengShu(88.820f));
		assertEquals("88", NumberUtil.toZhengShu(88.4999f));
		assertEquals("88", NumberUtil.toZhengShu(88.4999d));
		// ***********************************************************************
		assertEquals("0", NumberUtil.toZhengShu(0.1));
		assertEquals("1", NumberUtil.toZhengShu(0.5));
		//
		assertEquals("-1", NumberUtil.toZhengShu(-0.5));
		assertEquals("0", NumberUtil.toZhengShu(-0.11111111));
		assertEquals(null, NumberUtil.toZhengShu(null));
	}

	@Test
	public void testConvertNumberToZhengShu2(){
		log.debug(NumberUtil.toZhengShu(new BigDecimal(123)) + "");
	}

	@Test
	public void testGetDivideValueToZhengShu(){
		log.debug("DivideValueToZhengShu:" + NumberUtil.getDivideValueToZhengShu(new BigDecimal(6), 0));
	}

	@Test
	public void testGetMultiplyValue(){
		log.debug("MultiplyValue:" + NumberUtil.getMultiplyValue(new BigDecimal(6.25), 1.17, 5));
	}

	@Test
	public void getDivideValue(){
		assertEquals("3.33", NumberUtil.getDivideValue(new BigDecimal(10), 3, 2));
		assertEquals("1.67", NumberUtil.getDivideValue(new BigDecimal(5), 3, 2));
		assertEquals("5", NumberUtil.getDivideValue(new BigDecimal(5), "0", 2));
	}

	@Test
	public void test(){
		BigDecimal a = new BigDecimal("1.000000");
		BigDecimal b = new BigDecimal(1);
		log.debug(a.compareTo(b) + "");
		log.debug(a.equals(b) + "");
	}

	@Test
	public void toPercentWith2Point(){
		Integer a = 1;
		Long b = 400L;
		assertEquals("0.25%", NumberUtil.toPercentWith2Point((double) a / b));
	}

	@Test
	public void toPercentWithNoPoint(){
		assertEquals("0%", NumberUtil.toPercentWithNoPoint(0));
	}

	@Test
	public void testisSpecificNumber(){
		assertEquals(true, NumberUtil.isSpecificNumber(0, "0"));
		assertEquals(true, NumberUtil.isSpecificNumber(0.0000, "0"));
		assertEquals(false, NumberUtil.isSpecificNumber(-0.0001, "-0"));
		assertEquals(true, NumberUtil.isSpecificNumber("-0.0000", "0"));
		assertEquals(true, NumberUtil.isSpecificNumber("0.0001", "-0.0001"));
	}
}
