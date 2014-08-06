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
package com.feilong.tools.scm;

/**
 * The Interface ScmAntCopy.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月5日 下午5:27:59
 * @since 1.0.8
 */
public interface ScmAntCopy{

	/**
	 * 读取剪切板的信息.
	 */
	void printlnClipboardContent();

	/**
	 * 读取剪切板的信息.
	 *
	 * @param excludes
	 *            the excludes
	 */
	void printlnClipboardContent(String[] excludes);

	/**
	 * 解析patch 文件.
	 *
	 * @param fileName
	 *            the file name
	 */
	void printlnFileContent(String fileName);

	/**
	 * 解析patch 文件.
	 *
	 * @param fileName
	 *            the file name
	 * @param excludeFileNames
	 *            the exclude file names
	 */
	void printlnFileContent(String fileName,String[] excludeFileNames);
}
