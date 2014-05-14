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
	public void listFiles() throws IOException{
		String localPath = "E:\\Workspaces\\baozun-else\\mp2-new\\mp2-configuration\\project\\mp2-web\\mp2-livechat\\dev";
		// 读取localPath目录下的全部properties文件
		File file = new File(localPath);
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++){
			System.out.println("File:" + files[i].getCanonicalPath());
		}
	}

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
		log.info(FileUtil.formatFileSize(fileSizes) + "");
		log.info(FileUtil.formatFileSize(file.length()) + "");
	}

	/**
	 * {@link com.feilong.commons.core.io.IOUtil#formatFileSize(long)} 的测试方法。
	 */
	@Test
	public final void formatFileSize(){
		// stats
		log.info(FileUtil.formatFileSize(8981528));
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
