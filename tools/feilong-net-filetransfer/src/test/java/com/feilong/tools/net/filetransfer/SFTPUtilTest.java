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
package com.feilong.tools.net.filetransfer;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 3, 2013 3:21:51 PM
 */
public class SFTPUtilTest extends FileTransferTest{

	private static final Logger	log				= LoggerFactory.getLogger(SFTPUtilTest.class);

	@Autowired
	@Qualifier("nikeSFTPUtil")
	private FileTransfer		fileTransfer;

	private String				remoteDirectory	= "/home/appuser/test";

	@Test
	public void sendLocalFileToRemote() throws Exception{
		String singleLocalFileFullPath = "E:\\1.txt";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);

		singleLocalFileFullPath = "E:/hahaha.txt";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
	}

	@Test
	public void sendLocalFileToRemote_dir() throws Exception{
		String singleLocalFileFullPath = "F:\\2013-12-04-1938";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
	}

	@Test
	public void sendLocalFileToRemote_dirs() throws Exception{
		String[] batchLocalFileFullPaths = { "E:\\test", "E:\\1.txt", "E:\\test1" };
		fileTransfer.sendLocalFileToRemote(batchLocalFileFullPaths, remoteDirectory);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete()
	 */
	@Test
	public void delete() throws Exception{
		String remoteAbsolutePath = "/home/appuser/test/pg_ctl.conf";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_dir()
	 */
	@Test
	public void delete_dir() throws Exception{
		String remoteAbsolutePath = "/home/niketest/out/test/2011-07-07";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_dir_empty()
	 */
	@Test
	public void delete_dir_empty() throws Exception{
		String remoteAbsolutePath = "/home/appuser/test/2013-01-06";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_not_exist()
	 */
	@Test
	public void delete_not_exist() throws Exception{
		String remoteAbsolutePath = "/home/appuser/test/2011-07-051/";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/**
	 * Delete_not_exist1.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void delete_not_exist1() throws Exception{
		String remoteAbsolutePath = "/";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#sendLocalFileToRemote_dir_chinese()
	 */
	@Override
	public void sendLocalFileToRemote_dir_chinese() throws Exception{
	}

	@Test
	public void download_file() throws Exception{
		String remotePath = "/home/niketest/out/test/2011-07-07/nikeid_pix_cancel.csv";
		String localAbsoluteDirectoryPath = "E:\\test\\1";
		fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#download_dir()
	 */
	@Test
	public void download_dir() throws Exception{
		String remotePath = "/home/niketest/out/test";
		String localAbsoluteDirectoryPath = "E:\\test\\1";
		fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
	}

	@Test
	public void getFileEntityMap() throws Exception{
		String remoteAbsolutePath = "/home/appuser/test/2013-12-04-1938";
		String[] fileNames = { "SportActivity.dat", "SubCategory.dat", "aaa" };
		Map<String, FileInfoEntity> fileEntityMap = fileTransfer.getFileEntityMap(remoteAbsolutePath, fileNames);

		log.info(JsonUtil.format(fileEntityMap));
	}

}
