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

import com.feilong.commons.core.enumeration.TimeInterval;

/**
 * The Class MemCachedManagerTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 21, 2012 7:25:35 PM
 */
@ContextConfiguration(locations = { "classpath:spring-memcached.xml" })
public class MemCachedManagerTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MemCachedManagerTest.class);

	/** The mem cached manager. */
	@Autowired
	private MemCachedManager	memCachedManager;

	/** The key. */
	private String				key	= "name";

	/**
	 * Test method for {@link com.feilong.tools.memcached.MemCachedManager#set(java.lang.String, int, java.lang.Object)}.
	 */
	@Test
	public final void testSet(){
		memCachedManager.set(key, TimeInterval.SECONDS_PER_MINUTE, "金鑫");
	}

	/**
	 * Test method for {@link com.feilong.tools.memcached.MemCachedManager#get(java.lang.String)}.
	 */
	@Test
	public final void testGet(){
		String value = memCachedManager.get(key);
		log.info(value);
	}

	/**
	 * 设置.
	 */
	@Test
	public void set(){
		memCachedManager.set(key, "5");
		log.info(memCachedManager.get(key) + "");
	}

	/**
	 * Test incr.
	 */
	@Test
	public void testIncr(){
		log.info(memCachedManager.get(key) + "");
		log.info(memCachedManager.incr(key, 1) + "");
	}

	/**
	 * Test decr.
	 */
	@Test
	public void testDecr(){
		log.info(memCachedManager.decr(key, 1) + "");
	}
}
