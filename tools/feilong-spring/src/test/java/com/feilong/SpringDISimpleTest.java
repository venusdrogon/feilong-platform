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
package com.feilong;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.entity.DIUser;

/**
 * The Class UserTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-16 1:09:06
 */
public class SpringDISimpleTest{

	/** The Constant log. */
	private static final Logger			log	= LoggerFactory.getLogger(SpringDISimpleTest.class);

	/** The file system context. */
	private static ApplicationContext	fileSystemContext;

	/**
	 * Before class.
	 */
	@BeforeClass
	public static void beforeClass(){
		fileSystemContext = new FileSystemXmlApplicationContext("classpath:spring-DI-Array.xml", "classpath:spring-DI-simple.xml");
	}

	/**
	 * Test.
	 */
	@Test
	public void testUser(){
		DIUser diUser = (DIUser) fileSystemContext.getBean("spring-DI-simple");
		if (log.isInfoEnabled()){
			log.info(JsonUtil.format(diUser));
		}
	}
}