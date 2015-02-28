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

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

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
public class SpringDITest{

	/** The Constant log. */
	private static final Logger			log	= LoggerFactory.getLogger(SpringDITest.class);

	/** The file system context. */
	private static ApplicationContext	fileSystemContext;

	// @Value("#{testProperties['name']}")
	// private String aString;
	/**
	 * Before class.
	 */
	@BeforeClass
	public static void beforeClass(){
		fileSystemContext = new FileSystemXmlApplicationContext("classpath:spring-DI.xml", "classpath:spring-DI-Array.xml");
	}

	/**
	 * Test.
	 */
	@Test
	public void testUser(){
		DIUser diUser = (DIUser) fileSystemContext.getBean("feitian@");
		if (log.isInfoEnabled()){
			log.info(JsonUtil.format(diUser));
		}
		log.info(diUser.getUserName());
		// -----------------------------
		List<String> list = diUser.getList();
		log.info("list:{}", JsonUtil.format(list));
		// --------------------------------
		Map<String, Object> map = diUser.getMap();
		log.info("map:{}", JsonUtil.format(map));
		@SuppressWarnings("unchecked")
		List<String> list2 = (List<String>) map.get("五子良将");
		log.info("list2:{}", JsonUtil.format(list2));
		// -----------------------------------------
		@SuppressWarnings("unchecked")
		List<String> list3 = (List<String>) map.get("八虎骑");
		log.info("list3:{}", JsonUtil.format(list3));
		// -----------------------------------------
		Properties properties = diUser.getProperties();
		log.info("properties:{}", JsonUtil.format(properties));
		// *********************************
		Set<String> set = diUser.getSet();
		log.info("set:{}", JsonUtil.format(set));
	}

	/**
	 * Test util properties.
	 */
	@Test
	@Deprecated
	public void testUtilProperties(){
		Properties props = fileSystemContext.getBean("testProperties", Properties.class);
		// log.info(aString);
		log.info("props:{}", JsonUtil.format(props));

		// Locale locale = new Locale("zh", "CN");
		// log.info("================" + context.getMessage("name", null, locale));
	}
}