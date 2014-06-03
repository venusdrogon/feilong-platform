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

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册表工具类 测试类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-7 下午01:35:53
 */
public class RegeditUtilTest{

	private static final Logger	log				= LoggerFactory.getLogger(RegeditUtilTest.class);

	public static final String	EXCEL_PATH_CMD	= RegeditUtil.REGQUERY_UTIL
														+ "\""
														+ RegeditUtil.HKEY_CURRENT_USER
														+ "\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v Personal";

	/**
	 * {@link com.feilong.commons.core.io.RegeditUtil#query()} 的测试方法。
	 * 
	 * @throws IOException
	 */
	@Test
	public final void testGetExcelInstallRoot() throws IOException{
		log.info(RegeditUtil.query(EXCEL_PATH_CMD));
		//	int p = result.indexOf(REGSTR_TOKEN);
		//	if (p == -1)
		//		return null;
		//	return result.substring(p + REGSTR_TOKEN.length()).trim();
	}
}
