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
package com.feilong.commons.security.oneway;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.security.BaseSecurityTest;
import com.feilong.commons.security.oneway.MD5Util;

/**
 * The Class MD5UtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-7 上午01:12:36
 */
@SuppressWarnings("all")
public class MD5UtilTest extends BaseSecurityTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MD5UtilTest.class);

	/**
	 * {@link com.feilong.commons.security.oneway.MD5Util#encodeFile(java.lang.String)} 的测试方法。
	 */
	@Test
	public void encodeFile(){
		log.debug(MD5Util.encodeFile("E:\\DataCommon\\Java\\JDK API 1.6.0 中文版.CHM"));
	}

	/**
	 * Encode.
	 */
	@Test
	public void encode(){
		Assert.assertEquals("e10adc3949ba59abbe56e057f20f883e", MD5Util.encode("123456"));
		Assert.assertEquals("e8dc4081b13434b45189a720b77b6818", MD5Util.encode("abcdefgh"));
		Assert.assertEquals("06800efdece5a6dfda22174730f24477", MD5Util.encode("www.e-lining.com"));
		Assert.assertEquals("6f7c5c877d2b4ae3384b4daad366c19b", MD5Util.encode("WWW.RICARD.CN"));
		Assert.assertEquals("efa0699c3f05371d32ec5023d9e55ac0", MD5Util.encode("liushuwen@ricard.cn"));
		Assert.assertEquals("5d4db27d92073e85bfb307cf42738308", MD5Util.encode("sfexpress!@#$"));
	}

	/**
	 * Encode11.
	 */
	@Test
	public void encode11(){
		log.debug(debugSecurityValue(MD5Util.encode("你好")));
		log.debug(debugSecurityValue(MD5Util.encode("521000")));
		log.debug(debugSecurityValue(MD5Util.encode("你好", CharsetType.UTF8)));
		log.debug(debugSecurityValue(MD5Util.encode("521000", CharsetType.UTF8)));
	}

	/**
	 * Encode12.
	 */
	@Test
	public void encode12(){
		Assert.assertEquals(
				"F7468B69D12BB6CE76D6206419A6AC28",
				MD5Util.encode("1230000000456AAAAAAAAAAAAAAAIDR1120/01/2010 01:01:0112345678901234561234567890123456").toUpperCase());
	}
}
