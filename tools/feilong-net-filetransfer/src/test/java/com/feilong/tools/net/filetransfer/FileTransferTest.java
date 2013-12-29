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
package com.feilong.tools.net.filetransfer;

import java.io.IOException;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 通用的 FileTransferTest
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 6, 2013 1:52:48 AM
 */
@ContextConfiguration(value = { "classpath*:spring/spring-fileTransfer.xml" })
public abstract class FileTransferTest extends AbstractJUnit4SpringContextTests{

	/**
	 * 传送单个文件
	 * 
	 * @throws Exception
	 */
	public abstract void sendLocalFileToRemote() throws Exception;

	/**
	 * 传送文件夹
	 * 
	 * @throws Exception
	 */
	public abstract void sendLocalFileToRemote_dir() throws Exception;

	/**
	 * 批量传文件
	 * 
	 * @throws IOException
	 */
	public abstract void sendLocalFileToRemote_dirs() throws Exception;

	/**
	 * 传送文件夹(中文目录)
	 * 
	 * @throws Exception
	 */
	public abstract void sendLocalFileToRemote_dir_chinese() throws Exception;

	/**
	 * 删除一个普通文件/文件夹
	 * 
	 * @throws Exception
	 */
	public abstract void delete() throws Exception;

	/**
	 * 删除文件夹
	 * 
	 * @throws Exception
	 */
	public abstract void delete_dir() throws Exception;

	/**
	 * 删除 空的文件夹
	 * 
	 * @throws Exception
	 */
	public abstract void delete_dir_empty() throws Exception;

	/**
	 * 删除不存在的文件/文件夹
	 * 
	 * @throws Exception
	 */
	public abstract void delete_not_exist() throws Exception;

	public abstract void download_file() throws Exception;

	public abstract void download_dir() throws Exception;

	/**
	 * 获得某特定文件夹下面 指定文件名相关信息
	 */
	public abstract void getFileEntityMap() throws Exception;
	// E:\test\1
}
