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
package com.feilong.commons.core;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常量
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 21, 2011 12:14:12 PM
 */
public class CommonConstants{

	private static final Logger	log	= LoggerFactory.getLogger(TestConstants.class);

	@Test
	public void MAX_VALUE(){
		log.info(Integer.MAX_VALUE + "");
		log.info(Long.MAX_VALUE + "");
		log.info(Double.MAX_VALUE + "");
	}

	@Test
	public void IntegerAssertEquals(){
		int i = 760802109 + 1538284578;

		log.warn("the param i:{}", i);
		String signature1 = Math.abs(i) + "";
		log.debug("signature1 value:{}", signature1);

		BigDecimal add = new BigDecimal(760802109).add(new BigDecimal(1538284578));
		log.warn("the param add:{}", add);
		BigDecimal hash = add.abs();
		String signature2 = hash + "";
		log.debug("signature2 value:{}", signature2);
	}

	@Test
	public void length(){
		log.info("http://203.128.73.211/p/klikpayback/010000140002?s=30cbbe7a9bfcfc1131b018426e8560854bf507b3".length() + "");
	}

	@Test
	public void re(){
		log.info("alicea".replace("a", "<b>a</b>"));
	}

	@Test
	public void name12(){
		DecimalFormat df = new DecimalFormat("0000000000");
		int temp = 2015000;
		long currentTimeMillis = System.currentTimeMillis();
		log.info("currentTimeMillis:{}", currentTimeMillis);

		long t = currentTimeMillis % 100;
		log.info("t:{}", t);

		long l = temp + t;

		long longValue = Long.valueOf(l + "0" + t).longValue();

		if (t < 10){

		}else{
			longValue = Long.valueOf(l + "" + t).longValue();
		}

		log.info(df.format(longValue));

	}

}
