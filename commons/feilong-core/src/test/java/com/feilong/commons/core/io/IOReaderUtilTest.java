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
package com.feilong.commons.core.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class IOReaderUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:28:59 PM
 */
public class IOReaderUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(IOReaderUtilTest.class);

	/**
	 * Testname.
	 */
	@Test
	public void testname(){
		String fileName = "F:\\Life 生活\\Job 工作\\淘宝开店\\商家编码.txt";
		String content = IOReaderUtil.getFileContent(fileName);
		// 将内容以换行符转成数组
		String[] rowsContents = content.split("\r\n");
		log.info(content);
		log.info("" + rowsContents.length);
	}

	/**
	 * Parses the nginx.
	 */
	@Test
	public void parseNginx(){
		String fileName = "C:\\Users\\feilong\\Documents\\AJ11\\AJ11\\1.txt";
		String content = IOReaderUtil.getFileContent(fileName);

		content.split("");

		// 将内容以换行符转成数组
		// String[] rowsContents = content.split("\r\n");
		log.info(content);
	}

	/**
	 * Gets the file content.
	 * 
	 */
	@Test
	public void testGetFileContent(){
		String propertiesPath = "I:/Ebook/book.properties";
		log.info(IOReaderUtil.getFileContent(propertiesPath));
	}
}
