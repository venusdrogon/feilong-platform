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
package com.feilong.tools.net;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * The Class FTPUtilTest.
 */
public class FTPUtilTest extends FileTransferTest{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(FTPUtilTest.class);

	/** The file transfer. */
	@Autowired()
	@Qualifier("nikeFTPUtil")
	private FileTransfer		fileTransfer;

	/** The remote directory. */
	private String				remoteDirectory	= "/webstore/InlineSales_Test/2011-07-05/";

	/**
	 * Inits the.
	 */
	@Before
	public void init(){}

	/**
	 * 传送单个文件.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sendLocalFileToRemote() throws Exception{
		String singleLocalFileFullPath = "E:/nikestore_china_revenuesss.csv";
		// fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
		singleLocalFileFullPath = "E:/学车.xmind";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
	}

	/**
	 * 传送文件夹.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sendLocalFileToRemote_dir() throws Exception{
		String singleLocalFileFullPath = "C:\\Users\\feilong\\Documents\\360摄像头";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
	}

	/**
	 * 批量传文件.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sendLocalFileToRemote_dirs() throws Exception{
		String[] batchLocalFileFullPaths = { "E:\\test", "E:\\1.jpg" };
		fileTransfer.sendLocalFileToRemote(batchLocalFileFullPaths, remoteDirectory);

		String singleLocalFileFullPath = "E:\\config.jsp";
		fileTransfer.sendLocalFileToRemote(singleLocalFileFullPath, remoteDirectory);
	}

	/**
	 * 传送文件夹(中文目录).
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void sendLocalFileToRemote_dir_chinese() throws Exception{
		String localFileFullPath = "E:\\test - 副本";
		fileTransfer.sendLocalFileToRemote(localFileFullPath, remoteDirectory);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete()
	 */
	@Test
	public void delete() throws Exception{
		String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-05/1.jpg";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_dir()
	 */
	@Test
	public void delete_dir() throws Exception{
		String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-05/test";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_dir_empty()
	 */
	@Test
	public void delete_dir_empty() throws Exception{
		String remoteAbsolutePath = "/webstore/InlineSales_Test/a/";
		fileTransfer.delete(remoteAbsolutePath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#delete_not_exist()
	 */
	@Test
	public void delete_not_exist() throws Exception{
		String remoteAbsolutePath = "/webstore/InlineSales_Test/2011-07-051/";
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

	@Test
	public void isDirectory() throws Exception{

		fileTransfer.connect();
		// 文件夹
		log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07") + "");
		// 空的文件夹
		log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/1") + "");
		// 文件
		log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07/nikeid_pix_cancel.csv") + "");
		// 不存在的文件
		log.info(fileTransfer.isDirectory("/webstore/InlineSales_Test/2011-07-07/nikeid_pix_cancel1.csv") + "");

		fileTransfer.disconnect();
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#download()
	 */
	@Override
	@Test
	public void download_file() throws Exception{
		String remotePath = "/webstore/InlineSales_Test/nikestore_china_cancel20130103.csv";
		String localAbsoluteDirectoryPath = "E:\\test\\1";
		fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
	}

	/*
	 * (non-Javadoc)
	 * @see com.feilong.tools.net.FileTransferTest#download_dir()
	 */
	@Override
	@Test
	public void download_dir() throws Exception{
		String remotePath = "/webstore/InlineSales_Test/2011-07-07";
		String localAbsoluteDirectoryPath = "E:\\test\\1";
		fileTransfer.download(remotePath, localAbsoluteDirectoryPath);
	}
}
