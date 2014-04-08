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
package com.feilong.commons.core.text;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.json.JsonUtil;

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
		log.info("objs:{}", JsonUtil.format(objs));
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
