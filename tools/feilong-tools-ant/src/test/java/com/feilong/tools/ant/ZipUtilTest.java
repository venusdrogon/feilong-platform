/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.tools.ant;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateExtensionUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-27 下午04:14:11
 */
@SuppressWarnings("all")
public class ZipUtilTest{

	private static final Logger	log				= LoggerFactory.getLogger(ZipUtilTest.class);

	/**
	 * 需要被解压的zip文件
	 */
	String						unZipFileName	= "E:\\test.zip";

	/**
	 * 解压到哪个目录
	 */
	String						outputFileName	= "E:\\test" + DateUtil.date2String(new Date(), DatePattern.TIMESTAMP); // 解压到文件路径

	//@Test
	public void testZip() throws IOException{
		Date date1 = new Date();
		String inputFileName = "E:\\test"; // 你要压缩的文件夹
		String zipFileName = "E:\\test.zip"; // 压缩后的zip文件
		ZipUtil feiLongZip = new ZipUtil();
		feiLongZip.zip(inputFileName, zipFileName);
		Date date2 = new Date();
		log.info("耗时：" + DateExtensionUtil.getIntervalForView(date1, date2));
		// ,/select E:/Workspaces
		// Command.execFileOrDirectoryFocus("E:\\test,E:\\Project");
	}

	@Test
	public void testUnZip3(){
		Date date1 = new Date();
		outputFileName = outputFileName + "antUnzip3"; // 解压到文件路径
		ZipUtil feiLongZip = new ZipUtil();
		feiLongZip.unZip(unZipFileName, outputFileName);
		Date date2 = new Date();
		log.info("耗时：" + DateExtensionUtil.getIntervalForView(date1, date2));
	}
}
