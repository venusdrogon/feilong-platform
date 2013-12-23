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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:28:59 PM
 */
public class IOReaderUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(IOReaderUtilTest.class);

	@Test
	public void testname(){
		String fileName = "F:\\Life 生活\\Job 工作\\淘宝开店\\商家编码.txt";
		String content = IOReaderUtil.getFileContent(fileName);
		// 将内容以换行符转成数组
		String[] rowsContents = content.split("\r\n");
		System.out.println(content);
		System.out.println(rowsContents.length);
	}

	@Test
	public void parseNginx(){
		String fileName = "C:\\Users\\feilong\\Documents\\AJ11\\AJ11\\1.txt";
		String content = IOReaderUtil.getFileContent(fileName);

		content.split("");

		// 将内容以换行符转成数组
		// String[] rowsContents = content.split("\r\n");
		System.out.println(content);
	}

	@Test
	public void getFileContent(){
		String propertiesPath = "I:/Ebook/book.properties";
		System.out.println(IOReaderUtil.getFileContent(propertiesPath));
	}
}
