/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

import com.feilong.tools.scm.CVSPatchUtil;

/**
 * The Class PatchUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:50:42
 */
public class CVSPatchUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log					= LoggerFactory.getLogger(CVSPatchUtilTest.class);

	/**
	 * 过滤不想传的文件 采用 endWith 来 匹配<br>
	 */
	private String[]			excludeFileNames	= { "log4j.xml", "messages/interface-config.properties" };

	private CVSPatchUtil		cvsPatchUtil		= new CVSPatchUtil();

	/**
	 * 剪切板patch测试
	 */
	@Test
	public void printlnClipboardContent(){
		cvsPatchUtil.printlnClipboardContent(excludeFileNames);
	}

	/**
	 * 剪切板patch测试
	 */
	@Test
	public void printlnClipboardContent1(){
		cvsPatchUtil.printlnClipboardContent();
	}

	/**
	 * 文件patch测试
	 */
	@Test
	public void printlnFileContent(){
		String fileName = "F:/cvs-add update delete patch.txt";
		cvsPatchUtil.printlnFileContent(fileName, excludeFileNames);

	}
}
