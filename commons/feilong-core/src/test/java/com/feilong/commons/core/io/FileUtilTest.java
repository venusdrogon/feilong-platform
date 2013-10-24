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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午5:04:22
 */
public class FileUtilTest{

	private static final Logger	log			= LoggerFactory.getLogger(FileUtilTest.class);

	private String				fileName1	= "F:/pie2.png";

	private String				fileName	= "E:\\Data\\Java\\Taglib\\Apache Commons 非常有用的工具包\\commons-net\\ftp";

	private String				fString		= "/home/webuser/nike_int/johnData/${date}/nikeid_pix_${typeName}.csv";

	@Test
	public void isEmptyDirectory(){
		// 不存在的文件
		try{
			FileUtil.isEmptyDirectory("E:\\test\\1\\2011-07-07\\test\\1\\2011-07-07");
			Assert.fail();
		}catch (IllegalArgumentException e){
			Assert.assertTrue(true);
		}

		// 文件
		try{
			FileUtil.isEmptyDirectory("E:\\1.txt");
			Assert.fail();
		}catch (IllegalArgumentException e){
			Assert.assertTrue(true);
		}

		// 非空目录
		Assert.assertEquals(false, FileUtil.isEmptyDirectory("E:\\Workspaces"));

		// 正确的 空目录
		Assert.assertEquals(true, FileUtil.isEmptyDirectory("E:\\empty"));

	}

	@Test
	public void createDirectory(){
		FileUtil.createDirectory("E:\\test\\1\\2011-07-07\\test\\1\\2011-07-07");
	}

	@Test
	public void getFileSizes() throws IOException{

		String _file = "C:\\Users\\feilong\\Desktop\\醉枕江山(万骑英勇！-第二百一十一章 万象神宫).txt";

		File file = new File(_file);

		long fileSizes = FileUtil.getFileSize(file);
		log.info(fileSizes + "");
		log.info(IOUtil.formatFileSize(fileSizes) + "");
		log.info(IOUtil.formatFileSize(file.length()) + "");
	}

	@Test
	@Ignore
	public void testDeleteFileOrDirectory(){
		FileUtil.deleteFileOrDirectory("E:\\test");
	}

	/**
	 * 获得后缀名 {@link com.feilong.commons.core.io.IOUtil#getFilePostfixName(java.lang.String)} 的测试方法。
	 */
	@Test
	@Ignore
	public void testGetFilePostfixName(){
		assertEquals("png", FileUtil.getFilePostfixName(fileName1));
		System.out.println(fileName1.substring(fileName1.lastIndexOf(".")));
		System.out.println(fileName1.substring(fileName1.lastIndexOf("\\") + 1));
	}

	@Test
	@Ignore
	public void testGetFilePreName(){
		assertEquals("F:/pie2", FileUtil.getFilePreName(fileName1));
	}

	@Test
	public void testGetFileName(){
		System.out.println(FileUtil.getFileName(fileName1));
	}

	@Test
	public void hasPostfixName(){
		fileName1 = "a";
		log.debug(FileUtil.hasPostfixName(fileName1) + "");
	}

	@Test
	public void getFilePostfixNameLowerCase(){
		fileName1 = "a.A";
		log.debug(FileUtil.getFilePostfixNameLowerCase(fileName1) + "");
	}
}
