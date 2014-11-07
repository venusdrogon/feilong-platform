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
package common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.test.TestConstants;

/**
 * 常量.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 21, 2011 12:14:12 PM
 */
public class CommonTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TestConstants.class);

	/**
	 * Name.
	 */
	@Test
	public void name(){

		String[] orderNumberArray = { "2", "222" };
		Long[] orderIdArray = { 1L, 2L };

		if (log.isDebugEnabled()){
			log.debug("the 4444 param orderNumberArray:{},orderIdArray:{}", orderNumberArray, orderIdArray);
		}

	}

	/**
	 * Gets the hash code.
	 * 
	 */
	@Test
	public void testGetHashCode(){
		//		对于boolean值	value ? 1231 : 1237;
		log.info("" + getHashCode(true));
		log.info("" + getHashCode(false));
		log.info("" + getHashCode(Boolean.TRUE));
		log.info("" + getHashCode(Boolean.FALSE));

		//对于 interger 直接返回的是本身数值
		log.info("" + getHashCode(5));
		log.info("" + getHashCode(500));
		log.info("" + getHashCode(Integer.parseInt("200")));

		//对于 Long 直接返回的是  (int)(this.longValue()^(this.longValue()>>>32))
		log.info("" + getHashCode(Long.parseLong("200")));

		//对于 Float 直接返回的是 floatToIntBits
		log.info("" + getHashCode(Float.parseFloat("200")));

		//对于 Double  	long bits = doubleToLongBits(value);
		//		return (int)(bits ^ (bits >>> 32));
		log.info("" + getHashCode(Double.parseDouble("200")));

		//对于 String   s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
		log.info("" + getHashCode(String.valueOf("200")));
		log.info("" + getHashCode(Integer.MAX_VALUE));//2147483647
		log.info("" + (getHashCode(Integer.MAX_VALUE) + 5));//-2147483644

	}

	/**
	 * Gets the hash code.
	 * 
	 * @param obj
	 *            the obj
	 * @return the hash code
	 */
	private int getHashCode(Object obj){
		return (null == obj) ? 0 : obj.hashCode();
	}

	/**
	 * Gets the hash code.
	 * 
	 */
	@Test
	public void testGetHashCode2(){
		if (log.isDebugEnabled()){
			log.debug("" + Integer.parseInt("AA", 16));
		}
	}

	/**
	 * Integer assert equals.
	 */
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

	/**
	 * Re.
	 */
	@Test
	public void replace(){
		log.info("alicea".replace("a", "<b>a</b>"));
	}

	/**
	 * Name12.
	 */
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
