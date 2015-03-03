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
package com.feilong.tools.scm.svn;

import org.junit.Test;

import com.feilong.tools.scm.ScmAntCopy;
import com.feilong.tools.scm.ScmAntCopyConfig;

/**
 * The Class PatchUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:50:42
 */
public class SvnPatchAntCopyTest{

	/** 过滤不想传的文件 采用 endWith 来 匹配. */
	private static final String[]	excludeFileNames	= { "log4j.xml", "messages/interface-config.properties" };

	/** The svn patch util. */
	private ScmAntCopy				scmAntCopy			= new SvnPatchAntCopy();

	/**
	 * 剪切板patch测试.
	 */
	@Test
	public void printlnClipboardContent(){
		scmAntCopy.printlnClipboardContent(excludeFileNames);
	}

	/**
	 * 剪切板patch测试.
	 */
	@Test
	public void printlnClipboardContent1(){
		scmAntCopy.printlnClipboardContent();
	}

	/**
	 * 文件patch测试.
	 */
	@Test
	public void printlnFileContent(){
		String fileName = "C:\\Users\\feilong\\Desktop\\jim code 20141231";

		ScmAntCopyConfig scmAntCopyConfig = new ScmAntCopyConfig();
		//		scmAntCopyConfig.setExcludeFileNames(excludeFileNames);
		scmAntCopyConfig.setIgnoreNotRuleFile(false);
		scmAntCopyConfig.setChangeJavaFileExtensionNameToClass(false);

		scmAntCopy.printlnFileContent(fileName, scmAntCopyConfig);
	}
}
