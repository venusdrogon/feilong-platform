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

import org.junit.Test;

/**
 *注册表工具类 测试类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-7 下午01:35:53
 */
public class RegeditUtilTest{

	public static final String	EXCEL_PATH_CMD	= RegeditUtil.REGQUERY_UTIL + "\"" + RegeditUtil.HKEY_CURRENT_USER
														+ "\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v Personal";

	/**
	 * {@link com.feilong.commons.core.io.RegeditUtil#query()} 的测试方法。
	 */
	@Test
	public final void testGetExcelInstallRoot(){
		System.out.println(RegeditUtil.query(EXCEL_PATH_CMD));
		//	int p = result.indexOf(REGSTR_TOKEN);
		//	if (p == -1)
		//		return null;
		//	return result.substring(p + REGSTR_TOKEN.length()).trim();
	}
}
