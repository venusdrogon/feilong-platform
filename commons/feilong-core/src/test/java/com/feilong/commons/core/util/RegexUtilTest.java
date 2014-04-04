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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 29, 2013 6:54:30 PM
 */
public class RegexUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(RegexUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.util.RegexUtil#isEmail(java.lang.String)}.
	 */
	@Test
	public final void testIsEmail(){
		String email = "venusdrogon@163.com";
		log.info(RegexUtil.match(RegexPattern.EMAIL, email) + "");
	}

	@Test
	public final void testIsIP(){
		log.info(RegexUtil.match(RegexPattern.IP, "venusdrogon@163.com") + "");
		log.info(RegexUtil.match(RegexPattern.IP, "127.0.0.1") + "");
		log.info(RegexUtil.match(RegexPattern.IP, "127.0.0.*") + "");
		log.info(RegexUtil.match(RegexPattern.IP, "327.0.0.1") + "");
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.RegexUtil#match(java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testMatch(){
		String iphone = "Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7";

		String pattern = "^(MIDP)|(WAP)|(UP.Browser)|(Smartphone)|(Obigo)|(Mobile)|(AU.Browser)|(wxd.Mms)|(WxdB.Browser)|(CLDC)|(UP.Link)|(KM.Browser)|(UCWEB)|(SEMC\\-Browser)|(Mini)|(Symbian)|(Palm)|(Nokia)|(Panasonic)|(MOT\\-)|(SonyEricsson)|(NEC\\-)|(Alcatel)|(Ericsson)|(BENQ)|(BenQ)|(Amoisonic)|(Amoi\\-)|(Capitel)|(PHILIPS)|(SAMSUNG)|(Lenovo)|(Mitsu)|(Motorola)|(SHARP)|(WAPPER)|(LG\\-)|(LG/)|(EG900)|(CECT)|(Compal)|(kejian)|(Bird)|(BIRD)|(G900/V1.0)|(Arima)|(CTL)|(TDG)|(Daxian)|(DAXIAN)|(DBTEL)|(Eastcom)|(EASTCOM)|(PANTECH)|(Dopod)|(Haier)|(HAIER)|(KONKA)|(KEJIAN)|(LENOVO)|(Soutec)|(SOUTEC)|(SAGEM)|(SEC\\-)|(SED\\-)|(EMOL\\-)|(INNO55)|(ZTE)|(iPhone)|(Android)|(Windows CE)$";
		log.info(RegexUtil.match(pattern, iphone) + "");

	}

	@Test
	public final void testMatch1(){
		log.info(RegexUtil.match(RegexPattern.DECIMAL_TWODIGIT, "2000阿.00") + "");
		log.info(RegexUtil.match(RegexPattern.DECIMAL_TWODIGIT, "2000.0") + "");
		log.info(RegexUtil.match(RegexPattern.NUMBER, "2000.0") + "");
		log.info(RegexUtil.match(RegexPattern.NUMBER, "02125454") + "");
		log.info(RegexUtil.match(RegexPattern.AN, "02125454") + "");

	}

	@Test
	public final void AN(){
		log.info(RegexUtil.match(RegexPattern.AN, "0212545.4") + "");
		log.info(RegexUtil.match(RegexPattern.AN, "aa02125454") + "");

	}

	@Test
	public final void ANS(){
		log.info(RegexUtil.match(RegexPattern.ANS, "02125 454") + "");

	}
}
