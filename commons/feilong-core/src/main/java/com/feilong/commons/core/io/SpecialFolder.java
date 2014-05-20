/*
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
package com.feilong.commons.core.io;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

/**
 * 特殊文件夹.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-22 下午11:45:57
 */
public final class SpecialFolder{

	/**
	 * 获得操作系统临时文件夹.
	 * <ul>
	 * <li>win7:C:\Users\VENUSD~1\AppData\Local\Temp\</li>
	 * </ul>
	 * 
	 * @return 操作系统临时文件夹
	 */
	public final static String getTemp(){
		// XXX 提取 java.io.tmpdir 这些环境变量名称
		String property = "java.io.tmpdir";
		String tempDir = System.getProperty(property);
		return tempDir;
	}

	/**
	 * 获得桌面<br>
	 * example:win7:C:\Users\venusdrogon\Desktop.
	 * 
	 * @return 桌面地址
	 */
	public final static String getDesktop(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File file = fileSystemView.getHomeDirectory();
		// 或者 通过环境变量 USERPROFILE======>C:\Users\venusdrogon 再拼接 Desktop 获得
		return file.getPath();
	}

	/**
	 * 获得我的文档,该文件可以通过360等工具更改
	 * <ul>
	 * <li>win7:D:\noMove\documents</li>
	 * </ul>
	 * .
	 * 
	 * @return 我的文档地址
	 */
	public final static String getMyDocuments(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		File file = fileSystemView.getDefaultDirectory();
		return file.getPath();
	}
}
