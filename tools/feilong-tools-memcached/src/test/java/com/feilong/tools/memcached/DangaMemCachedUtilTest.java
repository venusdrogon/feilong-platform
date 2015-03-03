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
package com.feilong.tools.memcached;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.danga.MemCached.MemCachedClient;

/**
 * The Class MemCachedUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:25:00
 */
@ContextConfiguration(locations = { "classpath:feilong-memcached.xml" })
public class DangaMemCachedUtilTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DangaMemCachedUtilTest.class);

	/** The mem cached util. */
	private DangaMemCachedUtil	memCachedUtil;

	/** The mem cached client. */
	@Autowired
	MemCachedClient				memCachedClient;

	// @Test
	// public void get(){
	// memCachedUtil.add("jinxin", "My name is jinxin ,feilong owner");
	// memCachedUtil.add("jinxin2", "My name is jinxin2 ,feilong owner");
	// log.debug(memCachedUtil.get("jinxin").toString());
	// }
	/**
	 * Name.
	 */
	@Test
	public void name(){
		log.debug("the param localVar:{}", memCachedClient.keyExists("user"));
	}

	/**
	 * Adds the.
	 */
	@Test
	public void add(){
		memCachedClient.add("user", "jinxin");
		// log.debug("the param localVar:{}", memCachedClient.keyExists("user"));
	}
}
