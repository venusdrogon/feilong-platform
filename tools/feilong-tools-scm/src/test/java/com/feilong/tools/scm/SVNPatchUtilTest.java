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
package com.feilong.tools.scm;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PatchUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:50:42
 */
public class SVNPatchUtilTest{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(SVNPatchUtilTest.class);

	/**
	 * 过滤不想传的文件 采用 endWith 来 匹配<br>
	 */
	private String[]			excludeFileNames	= { "log4j.xml", "messages/interface-config.properties" };

	private SVNPatchUtil		svnPatchUtil		= new SVNPatchUtil();

	/**
	 * 剪切板patch测试
	 */
	@Test
	public void printlnClipboardContent(){
		svnPatchUtil.printlnClipboardContent(excludeFileNames);
	}

	/**
	 * 剪切板patch测试
	 */
	@Test
	public void printlnClipboardContent1(){
		svnPatchUtil.printlnClipboardContent();
	}

	/**
	 * 文件patch测试
	 */
	@Test
	public void printlnFileContent(){
		String fileName = "F:/1.txt";
		svnPatchUtil.printlnFileContent(fileName, excludeFileNames);

	}
}
