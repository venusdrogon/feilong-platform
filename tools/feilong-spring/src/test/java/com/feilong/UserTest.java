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

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.entity.User;

/**
 * The Class UserTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-16 1:09:06
 */
public class UserTest implements ApplicationContextAware{

	/** The Constant log. */
	private static final Logger			log			= LoggerFactory.getLogger(UserTest.class);

	/** The context. */
	private static ApplicationContext	context1	= null;

	private static ApplicationContext	fileSystemContext;

	// @Value("#{testProperties['name']}")
	// private String aString;
	@BeforeClass
	public static void beforeClass(){
		fileSystemContext = new FileSystemXmlApplicationContext("classpath:spring.xml");
	}

	/**
	 * Test.
	 */
	@Test
	public void testUser(){
		User user = (User) fileSystemContext.getBean("feitian@");
		if (log.isInfoEnabled()){
			log.info(JsonUtil.format(user));
		}
		log.info(user.getUserName());
		// -----------------------------
		List<String> list = user.getList();
		log.info("list:{}", JsonUtil.format(list));
		// --------------------------------
		Map<String, Object> map = user.getMap();
		log.info("map:{}", JsonUtil.format(map));
		List<String> list2 = (List<String>) map.get("五子良将");
		log.info("list2:{}", JsonUtil.format(list2));
		// -----------------------------------------
		List<String> list3 = (List<String>) map.get("八虎骑");
		log.info("list3:{}", JsonUtil.format(list3));
		// -----------------------------------------
		Properties properties = user.getProperties();
		log.info("properties:{}", JsonUtil.format(properties));
		// *********************************
		Set<String> set = user.getSet();
		set.add("1");
		set.add("1");
		log.info("set:{}", JsonUtil.format(set));
	}

	@Test
	public void testVector(){
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(1);
		vector.add(2222);
		vector.add(3333);
		vector.add(55555);
		log.info("vector:{}", JsonUtil.format(vector));
		log.info("" + vector.get(0));
	}

	@Test
	public void testHashtable(){
		Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		hashtable.put("a", "a");
		// hashtable.put("a", null);
		log.info("hashtable:{}", JsonUtil.format(hashtable));
	}

	/**
	* 
	*/
	@Test
	public void testUtilProperties(){
		Properties props = fileSystemContext.getBean("testProperties", Properties.class);
		// log.info(aString);
		log.info("props:{}", JsonUtil.format(props));

		// Locale locale = new Locale("zh", "CN");
		// log.info("================" + context.getMessage("name", null, locale));
	}

	/**
	 * Hash set.
	 */
	@Test
	public void hashSet(){
		// **********************************************************
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("今天");
		log.info("hashSet:{}", JsonUtil.format(hashSet));
		// **********************************************************
		HashSet<com.feilong.entity.user.User> hashSet1 = new HashSet<com.feilong.entity.user.User>();
		com.feilong.entity.user.User user = new com.feilong.entity.user.User();
		user.setId(555);
		hashSet1.add(user);
		log.info("hashSet1:{}", JsonUtil.format(hashSet1));
	}

	/**
	 * Test1.
	 */
	@Test
	public void test1(){
		User user = (User) context1.getBean("feitian@");
		log.info("user:{}", JsonUtil.format(user));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		context1 = applicationContext;
	}
}
