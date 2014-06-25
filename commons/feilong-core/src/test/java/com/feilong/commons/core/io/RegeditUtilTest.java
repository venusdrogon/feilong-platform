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

	@SuppressWarnings("javadoc")
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
