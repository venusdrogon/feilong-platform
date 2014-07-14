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

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册表工具类 测试类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-7 下午01:35:53
 */
public class RegeditUtilTest{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(RegeditUtilTest.class);

	/** The Constant EXCEL_PATH_CMD. */
	public static final String	EXCEL_PATH_CMD	= RegeditUtil.REGQUERY_UTIL
														+ "\""
														+ RegeditUtil.HKEY_CURRENT_USER
														+ "\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v Personal";

	/**
	 * {@link com.feilong.commons.core.io.RegeditUtil#query()} 的测试方法。
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testGetExcelInstallRoot() throws IOException{
		log.info(RegeditUtil.query(EXCEL_PATH_CMD));
		//	int p = result.indexOf(REGSTR_TOKEN);
		//	if (p == -1)
		//		return null;
		//	return result.substring(p + REGSTR_TOKEN.length()).trim();
	}

	//
	// //http://sourceforge.net/projects/jregistrykey/
	// //import ca.beq.util.win32.registry.*;
	// //import java.util.*;
	// 1.打开键
	// RegistryKey r = new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,
	// "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders");
	// 2.添加键
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// r.create();
	// 9.写入字符串值
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// RegistryValue v = new RegistryValue("myVal", ValueType.REG_SZ, "data");
	// r.setValue(v);
	// 6.获取DWORD值
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// if(r.hasValue("myValue")) {
	// RegistryValue v = r.getValue("myValue");
	// v.setType(ValueType.REG_DWORD);
	// } // if

	//
	// 87.菜单勾选/取消开机自启动程序
	// //http://sourceforge.net/projects/jregistrykey/
	// //import ca.beq.util.win32.registry.*;
	// //import java.util.*;
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// RegistryValue v = new RegistryValue("run", ValueType.REG_SZ, "data");
	// r.setValue(v);
}
