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
package com.feilong.commons.core.io;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.Constants;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:29:11 PM
 */
public class IOWriteUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(IOWriteUtilTest.class);

	@Test
	public void write(){
		String url = "F:\\test.txt";
		String directoryName = SpecialFolder.getDesktop();
		IOWriteUtil.write(url, directoryName);
	}

	@Test
	public void testWrite(){
		String path = "/home/webuser/nike_int/expressdelivery/${yearMonth}/${expressDeliveryType}/vipQuery_${fileName}.log";
		Date date = new Date();
		path = path.replace("${yearMonth}", DateUtil.date2String(date, DatePattern.yearAndMonth));
		path = path.replace("${expressDeliveryType}", "sf");
		path = path.replace("${fileName}", DateUtil.date2String(date, DatePattern.timestamp));
		// **************************************************************
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("****************************************************" + Constants.lineSeparator);
		stringBuilder.append("2011-05-13 22:24:37调用,系统顺丰在途订单597件" + Constants.lineSeparator);
		stringBuilder.append("耗时:429020" + Constants.lineSeparator);
		stringBuilder.append("****************************************************" + Constants.lineSeparator);
		stringBuilder.append("派送成功订单495条" + Constants.lineSeparator);
		for (int i = 0; i < 1000; i++){
			stringBuilder.append("订单号:20850010运单号:102085592089\t寄件时间:2011-05-09 19:00:00\t签收人:张寄件时间:2011-05-10 14:49:00\t回单类型:1\n");
		}
		IOWriteUtil.write(path, stringBuilder.toString());
	}
}
