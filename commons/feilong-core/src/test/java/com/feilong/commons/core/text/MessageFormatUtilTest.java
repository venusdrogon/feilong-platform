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

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-27 上午1:36:55
 */
public class MessageFormatUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(MessageFormatUtilTest.class);

	@Test
	public final void format() throws ParseException{
		log.info(MessageFormatUtil.format("name=张三{0}a{1}", "jin", "xin"));
		log.info(MessageFormatUtil.format("name=张三{0,number}a{1}", 5, "xin"));
		log.info(MessageFormatUtil.format("name=张三{0,date}a{1}", 15, "xin"));
	}

	@Test
	public final void getValueWithArguments1() throws ParseException{
		MessageFormat mf = new MessageFormat("{0}, {0}, {0}");
		String forParsing = "x, y, z";
		Object[] objs = mf.parse(forParsing, new ParsePosition(0));
		// result now equals {new String("z")}
		log.info(objs.toString());
		log.info("objs:{}", JsonFormatUtil.format(objs));
		int planet = 7;
		String event = "a disturbance in the Force";
		String result = MessageFormat.format(
				"At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
				planet,
				new Date(),
				event);
		log.info(result);
		MessageFormat form = new MessageFormat("The disk \"{1}\" contains {0}.");
		double[] filelimits = { 0, 1, 2 };
		String[] filepart = { "no files", "one file", "{0,number} files" };
		ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
		form.setFormatByArgumentIndex(0, fileform);
		int fileCount = 0;
		String diskName = "MyDisk";
		Object[] testArgs = { new Long(fileCount), diskName };
		log.info(form.format(testArgs));
	}
}
