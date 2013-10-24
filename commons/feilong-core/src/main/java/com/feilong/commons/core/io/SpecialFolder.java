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

import java.io.File;

import javax.swing.filechooser.FileSystemView;

/**
 *特殊文件夹
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-22 下午11:45:57
 */
public final class SpecialFolder{

	/**
	 * 获得操作系统临时文件夹
	 * <ul>
	 * <li>win7:C:\Users\VENUSD~1\AppData\Local\Temp\</li>
	 * </ul>
	 * 
	 * @return 操作系统临时文件夹
	 */
	public final static String getTemp(){
		String property = "java.io.tmpdir";
		String tempDir = System.getProperty(property);
		return tempDir;
	}

	/**
	 * 获得桌面<br>
	 * example:win7:C:\Users\venusdrogon\Desktop
	 * 
	 * @return 桌面地址
	 */
	public final static String getDesktop(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File file = fileSystemView.getHomeDirectory();
		//或者 通过环境变量 USERPROFILE======>C:\Users\venusdrogon   再拼接 Desktop 获得
		return file.getPath();
	}

	/**
	 * 获得我的文档,该文件可以通过360等工具更改
	 * <ul>
	 * <li>win7:D:\noMove\documents</li>
	 * </ul>
	 * 
	 * @return 我的文档地址
	 */
	public final static String getMyDocuments(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File file = fileSystemView.getDefaultDirectory();
		return file.getPath();
	}
}
