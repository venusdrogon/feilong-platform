/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.commons.core;

import java.text.DecimalFormat;

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
	public void length(){
		log.info("5232181bc0d9888f5c9746e410b4740eb461706ba5dacfbc93587cecfc8d068bac7737e92870d6745b11a25e9cd78b55f4ffc706f73cfcae5345f1b53fb8f6b5"
				.length() + "");
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
