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
package com.feilong.tools.memcached;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.date.TimeInterval;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 21, 2012 7:25:35 PM
 */
@ContextConfiguration(locations = { "classpath:spring-memcached.xml" })
public class MemCachedManagerTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(MemCachedManagerTest.class);

	@Autowired
	private MemCachedManager	memCachedManager;

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
	 * Test method for {@link com.feilong.tools.memcached.MemCachedManager#delete(java.lang.String)}.
	 */
	@Test
	public final void testDelete(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.tools.memcached.MemCachedManager#shutDown()}.
	 */
	@Test
	public final void testShutDown(){
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void set(){
		memCachedManager.set(key, "5");
		log.info(memCachedManager.get(key) + "");
	}

	/**
	 * Test method for {@link com.jumbo.brandstore.manager.memcached.MemCachedManager#incr(java.lang.String, int)}.
	 */
	@Test
	public void testIncr(){
		log.info(memCachedManager.get(key) + "");
		log.info(memCachedManager.incr(key, 1) + "");
	}

	/**
	 * Test method for {@link com.jumbo.brandstore.manager.memcached.MemCachedManager#decr(java.lang.String, int)}.
	 */
	@Test
	public void testDecr(){
		log.info(memCachedManager.decr(key, 1) + "");
	}
}
