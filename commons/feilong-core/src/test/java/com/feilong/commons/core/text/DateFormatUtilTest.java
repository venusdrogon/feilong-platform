/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.text;

import java.util.Date;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-15 上午11:36:29
 */
public  class DateFormatUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(DateFormatUtilTest.class);

	@Test
	public void format(){
		Date now = new Date();
		log.info(now.toString());
		String pattern = DatePattern.onlyDate;
		pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
		String nowString = DateFormatUtil.format(now, pattern, Locale.ENGLISH);
		log.info(nowString);
		log.info(now.toString().equals(nowString) + "");
	}

	@Test
	public void parse(){
		Date now = new Date();
		log.info(now.toString());
		Date now1 = DateFormatUtil.parse(now.toString(), "EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		log.info(now1.toString());
	}
}
