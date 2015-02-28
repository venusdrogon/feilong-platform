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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.RegexPattern;

/**
 * The Class RegexUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 29, 2013 6:54:30 PM
 */
public class RegexUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(RegexUtilTest.class);

	/**
	 * Group.
	 */
	@Test
	public final void group(){
		String regexPattern = "(.*?)@(.*?)";
		String email = "venusdrogon@163.com";
		log.info(RegexUtil.group(regexPattern, email, 1) + "");
		log.info(RegexUtil.group(regexPattern, email, 2) + "");
	}

	/**
	 * Group2.
	 */
	@Test
	public final void group2(){
		String regexPatternTable = "@Table.*name.*\"(.*?)\".*";
		//		regexPattern = "@Table[(]*\"(.*?)*[)]";
		//		regexPattern = "@Table(.*?)";
		String email = "@Table(name = \"T_MEM_MEMBER_ADDRESS\")";
		log.info(RegexUtil.group(regexPatternTable, email) + "");
	}

	/**
	 * Group1.
	 */
	@Test
	public final void group1(){
		String regexPattern = "(.*?)(?:@)(.*?)";
		regexPattern = "(.*?)@(.*?)";
		String email = "venusdrogon@163.com";
		RegexUtil.group(regexPattern, email);
	}

	/**
	 * Group22.
	 */
	@Test
	public final void group22(){
		String regexPatternColumn = ".*@Column.*name.*\"(.*?)\"((?:.*)|(.*length.*(\\d+).*))";
		//		REGEX_PATTERN_COLUMN = ".*@Column.*name.*\"(.*?)\".*length.*(\\d*).*";
		//		REGEX_PATTERN_COLUMN = ".*@Column.*(\\d+).*";
		regexPatternColumn = ".*@Column.*?name\\s*=\\s*\"(.*?)\"(?:.*?length\\s*=\\s*(\\d+))?";
		regexPatternColumn = ".*@Column.*name.*\"(.*?)\".*length\\s*=\\s*(\\d+).*";
		String email = "@Column(name = \"NAME\", length=80)";
		RegexUtil.group(regexPatternColumn, email);
	}

	/**
	 * Test is email.
	 */
	@Test
	public final void testIsEmail(){
		String email = "venusdrogon@163.com";
		log.info(RegexUtil.matches(RegexPattern.EMAIL, email) + "");
	}

	/**
	 * Test is ip.
	 */
	@Test
	public final void testIsIP(){
		log.info(RegexUtil.matches(RegexPattern.IP, "venusdrogon@163.com") + "");
		log.info(RegexUtil.matches(RegexPattern.IP, "127.0.0.1") + "");
		log.info(RegexUtil.matches(RegexPattern.IP, "127.0.0.*") + "");
		log.info(RegexUtil.matches(RegexPattern.IP, "327.0.0.1") + "");
	}

	/**
	 * Test match.
	 */
	@Test
	public final void testMatch(){
		String iphone = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7";

		String pattern = "^(MIDP)|(WAP)|(UP.Browser)|(Smartphone)|(Obigo)|(Mobile)|(AU.Browser)|(wxd.Mms)|(WxdB.Browser)|(CLDC)|(UP.Link)|(KM.Browser)|(UCWEB)|(SEMC\\-Browser)|(Mini)|(Symbian)|(Palm)|(Nokia)|(Panasonic)|(MOT\\-)|(SonyEricsson)|(NEC\\-)|(Alcatel)|(Ericsson)|(BENQ)|(BenQ)|(Amoisonic)|(Amoi\\-)|(Capitel)|(PHILIPS)|(SAMSUNG)|(Lenovo)|(Mitsu)|(Motorola)|(SHARP)|(WAPPER)|(LG\\-)|(LG/)|(EG900)|(CECT)|(Compal)|(kejian)|(Bird)|(BIRD)|(G900/V1.0)|(Arima)|(CTL)|(TDG)|(Daxian)|(DAXIAN)|(DBTEL)|(Eastcom)|(EASTCOM)|(PANTECH)|(Dopod)|(Haier)|(HAIER)|(KONKA)|(KEJIAN)|(LENOVO)|(Soutec)|(SOUTEC)|(SAGEM)|(SEC\\-)|(SED\\-)|(EMOL\\-)|(INNO55)|(ZTE)|(iPhone)|(Android)|(Windows CE)$";
		log.info(RegexUtil.matches(pattern, iphone) + "");
	}

	/**
	 * Test match1.
	 */
	@Test
	public final void testMatch1(){
		log.info(RegexUtil.matches(RegexPattern.DECIMAL_TWODIGIT, "2000阿.00") + "");
		log.info(RegexUtil.matches(RegexPattern.DECIMAL_TWODIGIT, "2000.0") + "");
		log.info(RegexUtil.matches(RegexPattern.NUMBER, "2000.0") + "");
		log.info(RegexUtil.matches(RegexPattern.NUMBER, "02125454") + "");
		log.info(RegexUtil.matches(RegexPattern.AN, "02125454") + "");
	}

	/**
	 * AN.
	 */
	@Test
	public final void testAN(){
		log.info(RegexUtil.matches(RegexPattern.AN, "0212545.4") + "");
		log.info(RegexUtil.matches(RegexPattern.AN, "aa02125454") + "");
	}

	/**
	 * ANS.
	 */
	@Test
	public final void testANS(){
		log.info(RegexUtil.matches(RegexPattern.ANS, "02125 454") + "");
	}
}
