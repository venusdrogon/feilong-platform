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
package com.jumbo.shop.manager.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * The Class CreateManagerTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2013 6:56:10 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/spring-memcached.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CreateManagerTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CreateManagerTest.class);

	/** The create manager. */
	@Autowired
	private CreateManager		createManager;

	/**
	 * Test test create.
	 */
	@Test
	public void testTestCreate(){
		String testCreate = createManager.testCreate("jinxin");

		log.info(testCreate);
	}

	/**
	 * Test public sku.
	 */
	@Test
	public void testPublicSku(){
		createManager.publicSku("asdasdasd");
	}
}
