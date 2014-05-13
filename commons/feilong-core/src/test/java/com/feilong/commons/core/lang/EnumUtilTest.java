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
package com.feilong.commons.core.lang;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月12日 下午11:00:42
 * @since 1.0.6
 */
public class EnumUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(EnumUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.lang.EnumUtil#getEnumByField(java.lang.Enum, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testGetEnum(){
		if (log.isInfoEnabled()){
			log.info("" + EnumUtil.getEnumByField(HttpMethodType.class, "method", "get"));
		}
	}

	/**
	 * Test method for {@link com.feilong.commons.core.enumeration.HttpMethodType#getHttpMethodType(java.lang.String)}.
	 */
	@Test
	public final void testGetHttpMethodType(){
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByField(HttpMethodType.class, "method", "post"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByField(HttpMethodType.class, "method", "pOst"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByField(HttpMethodType.class, "method", "POST"));
		assertEquals(HttpMethodType.POST, EnumUtil.getEnumByField(HttpMethodType.class, "method", "posT"));

		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByField(HttpMethodType.class, "method", "get"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByField(HttpMethodType.class, "method", "gEt"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByField(HttpMethodType.class, "method", "geT"));
		assertEquals(HttpMethodType.GET, EnumUtil.getEnumByField(HttpMethodType.class, "method", "GET"));

		assertEquals(null, EnumUtil.getEnumByField(HttpMethodType.class, "method", "post111"));
		assertEquals(null, EnumUtil.getEnumByField(HttpMethodType.class, "method", ""));
		assertEquals(null, EnumUtil.getEnumByField(HttpMethodType.class, "method", null));
	}
}
