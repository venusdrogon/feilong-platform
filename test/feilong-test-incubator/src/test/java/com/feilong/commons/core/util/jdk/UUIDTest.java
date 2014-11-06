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
package com.feilong.commons.core.util.jdk;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class UUIDTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-28 上午09:37:50
 */
public class UUIDTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(UUIDTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test(){
		//82bcab61-a61e-451a-ae40-eeaa2ea54ba9
		//dc45acb3-ee78-4883-a4df-452c4064bc22
		log.info(UUID.randomUUID().toString());
	}
}
