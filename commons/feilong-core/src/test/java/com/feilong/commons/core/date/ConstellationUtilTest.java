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

import java.util.Arrays;
import java.util.Collection;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试 星座.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-5 上午11:49:13
 */
@RunWith(Parameterized.class)
@SuppressWarnings("all")
public class ConstellationUtilTest extends TestCase{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ConstellationUtilTest.class);

	/** The f expected. */
	private ConstellationType	fExpected;

	/** The f input. */
	private String				birthday;

	/**
	 * The Constructor.
	 *
	 * @param expected
	 *            the expected
	 * @param input
	 *            the input
	 */
	public ConstellationUtilTest(ConstellationType expected, String birthday){
		birthday = birthday;
		fExpected = expected;
	}

	/**
	 * Data.
	 *
	 * @return the collection
	 */
	@Parameters
	public static Collection data(){
		return Arrays.asList(new Object[][] {
				{ ConstellationType.Capricorn, "1986-12-22" },
				{ ConstellationType.Capricorn, "1986-12-25" },
				{ ConstellationType.Aquarius, "1986-01-20" },
				{ ConstellationType.Aquarius, "1986-01-21" },
				{ ConstellationType.Aquarius, "1986-02-18" },
				{ ConstellationType.Pisces, "1986-02-19" },
				{ ConstellationType.Pisces, "1986-02-20" },
				{ ConstellationType.Pisces, "1986-02-26" },
				{ ConstellationType.Pisces, "1986-03-20" },
				{ ConstellationType.Aries, "1986-03-21" },
				{ ConstellationType.Aries, "1986-03-25" },
				{ ConstellationType.Taurus, "1986-04-20" },
				{ ConstellationType.Taurus, "1986-04-21" },
				{ ConstellationType.Taurus, "1986-05-10" },
				{ ConstellationType.Gemini, "1986-05-21" },
				{ ConstellationType.Gemini, "1986-05-22" },
				{ ConstellationType.Gemini, "1986-05-25" },
				{ ConstellationType.Gemini, "1986-06-21" },
				{ ConstellationType.Cancer, "1986-06-22" },
				{ ConstellationType.Cancer, "1986-06-28" },
				{ ConstellationType.Cancer, "1986-07-22" },
				{ ConstellationType.Leo, "1986-07-23" },
				{ ConstellationType.Leo, "1986-07-30" },
				{ ConstellationType.Virgo, "1986-08-23" },
				{ ConstellationType.Virgo, "1986-08-24" },
				{ ConstellationType.Virgo, "1986-09-21" },
				{ ConstellationType.Libra, "1986-09-23" },
				{ ConstellationType.Libra, "1986-09-24" },
				{ ConstellationType.Libra, "1986-10-21" },
				{ ConstellationType.Libra, "1986-10-23" },
				{ ConstellationType.Scorpio, "1986-10-24" },
				{ ConstellationType.Scorpio, "1986-11-21" },
				{ ConstellationType.Scorpio, "1986-11-22" },
				{ ConstellationType.Sagittarius, "1986-11-23" },
				{ ConstellationType.Sagittarius, "1986-11-28" },
				{ ConstellationType.Sagittarius, "1986-12-21" } });
	}

	/**
	 * Test get constellation type.
	 */
	@Test
	public void testGetConstellationType(){
		String birthday = "1984-07-25";
		ConstellationType constellationType = ConstellationUtil.getConstellationType(birthday);
		log.info(constellationType.getChineseName());
	}

	/**
	 * Test.
	 */
	@Test
	public void test(){
		assertEquals(fExpected, ConstellationUtil.getConstellationType(birthday));
	}

	/**
	 * Test calculate constellation.
	 */
	@Test
	@Ignore
	public void testCalculateConstellation(){
		assertEquals(ConstellationType.Capricorn, ConstellationUtil.getConstellationType("1986-12-22"));
		assertEquals(ConstellationType.Capricorn, ConstellationUtil.getConstellationType("1986-12-25"));
		assertEquals(ConstellationType.Aquarius, ConstellationUtil.getConstellationType("1986-01-20"));
		assertEquals(ConstellationType.Aquarius, ConstellationUtil.getConstellationType("1986-01-21"));
		assertEquals(ConstellationType.Aquarius, ConstellationUtil.getConstellationType("1986-02-18"));
		assertEquals(ConstellationType.Pisces, ConstellationUtil.getConstellationType("1986-02-19"));
		assertEquals(ConstellationType.Pisces, ConstellationUtil.getConstellationType("1986-02-20"));
		assertEquals(ConstellationType.Pisces, ConstellationUtil.getConstellationType("1986-02-26"));
		assertEquals(ConstellationType.Pisces, ConstellationUtil.getConstellationType("1986-03-20"));
		assertEquals(ConstellationType.Aries, ConstellationUtil.getConstellationType("1986-03-21"));
		assertEquals(ConstellationType.Aries, ConstellationUtil.getConstellationType("1986-03-25"));
		assertEquals(ConstellationType.Taurus, ConstellationUtil.getConstellationType("1986-04-20"));
		assertEquals(ConstellationType.Taurus, ConstellationUtil.getConstellationType("1986-04-21"));
		assertEquals(ConstellationType.Taurus, ConstellationUtil.getConstellationType("1986-05-10"));
		assertEquals(ConstellationType.Gemini, ConstellationUtil.getConstellationType("1986-05-21"));
		assertEquals(ConstellationType.Gemini, ConstellationUtil.getConstellationType("1986-05-22"));
		assertEquals(ConstellationType.Gemini, ConstellationUtil.getConstellationType("1986-05-25"));
		assertEquals(ConstellationType.Gemini, ConstellationUtil.getConstellationType("1986-06-21"));
		assertEquals(ConstellationType.Cancer, ConstellationUtil.getConstellationType("1986-06-22"));
		assertEquals(ConstellationType.Cancer, ConstellationUtil.getConstellationType("1986-06-28"));
		assertEquals(ConstellationType.Cancer, ConstellationUtil.getConstellationType("1986-07-22"));
		assertEquals(ConstellationType.Leo, ConstellationUtil.getConstellationType("1986-07-23"));
		assertEquals(ConstellationType.Leo, ConstellationUtil.getConstellationType("1986-07-30"));
		assertEquals(ConstellationType.Virgo, ConstellationUtil.getConstellationType("1986-08-23"));
		assertEquals(ConstellationType.Virgo, ConstellationUtil.getConstellationType("1986-08-24"));
		assertEquals(ConstellationType.Virgo, ConstellationUtil.getConstellationType("1986-09-21"));
		assertEquals(ConstellationType.Libra, ConstellationUtil.getConstellationType("1986-09-23"));
		assertEquals(ConstellationType.Libra, ConstellationUtil.getConstellationType("1986-09-24"));
		assertEquals(ConstellationType.Libra, ConstellationUtil.getConstellationType("1986-10-21"));
		assertEquals(ConstellationType.Libra, ConstellationUtil.getConstellationType("1986-10-23"));
		assertEquals(ConstellationType.Scorpio, ConstellationUtil.getConstellationType("1986-10-24"));
		assertEquals(ConstellationType.Scorpio, ConstellationUtil.getConstellationType("1986-11-21"));
		assertEquals(ConstellationType.Scorpio, ConstellationUtil.getConstellationType("1986-11-22"));
		assertEquals(ConstellationType.Sagittarius, ConstellationUtil.getConstellationType("1986-11-23"));
		assertEquals(ConstellationType.Sagittarius, ConstellationUtil.getConstellationType("1986-11-28"));
		assertEquals(ConstellationType.Sagittarius, ConstellationUtil.getConstellationType("1986-12-21"));
	}
}