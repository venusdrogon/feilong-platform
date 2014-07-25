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
package com.feilong.commons.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * The Class CollectionUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-22 21:55:38
 */
public class CollectionsUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CollectionsUtilTest.class);

	/**
	 * TestCollectionsUtilTest.
	 */
	@Test
	public void testCollectionsUtilTest(){

		Set<String> set = new LinkedHashSet<String>();
		set.add("1");
		set.add("2");
		set.add("3");
		set.add("4");
		set.add("5");
		set.add("1");

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(set));
		}

		//assertEquals(expected, actual);
	}

	/**
	 * TestCollectionsUtilTest.
	 */
	@Test
	public void testCollectionsUtilTest2(){
		Stack<Object> stack = new Stack<>();

		stack.add("1");
		stack.add("2");
		stack.add("3");
		stack.add("4");

		if (log.isDebugEnabled()){
			log.debug("" + stack.firstElement());
			log.debug("" + stack.peek());
			log.debug("" + stack.pop());
			log.debug(JsonUtil.format(stack));
		}

		//assertEquals(expected, actual);
	}

	/**
	 * TestCollectionsUtilTest.
	 */
	@Test
	public void testCollectionsUtilTest33(){
		Queue<Object> queue = new PriorityQueue<Object>();

		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.add(6);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(queue));
			log.debug("" + queue.peek());

		}

		//assertEquals(expected, actual);
	}

	/**
	 * Test tree set.
	 */
	@Test
	public void testTreeSet(){
		TreeSet<Date> treeSet = new TreeSet<Date>();
		treeSet.add(DateUtil.string2Date("2012-05-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-03-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-01-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-01-02", DatePattern.onlyDate));
		log.info(treeSet + "");
	}

	/**
	 * Test map.
	 */
	@Test
	public void testMap(){
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put("jinxin", 1);
		map.put(2, 2);
		map.put("甲", 3);
		map.put(4, 4);
		map = null;
		StringBuilder builder = new StringBuilder();
		builder.append(map);
		log.info(builder.toString());
	}

	/**
	 * Test map to enumeration.
	 */
	public void testMapToEnumeration(){
		// Enumeration
		final Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put("jinxin", 1);
		map.put(2, 2);
		map.put("甲", 3);
		map.put(4, 4);
		map.put("jinxin1", 1);
		map.put(21, 2);
		map.put("甲1", 3);
		map.put(41, 4);
		Enumeration<Object> enumeration = CollectionsUtil.toEnumeration(map.keySet());
		while (enumeration.hasMoreElements()){
			log.info("" + enumeration.nextElement());
		}
	}

	/**
	 * 集合转成字符串.
	 */
	@Test
	public void testCollectionToString(){
		List<String> list = new ArrayList<String>();
		list.add("2548");
		list.add("");

		JoinStringEntity joinStringEntity = new JoinStringEntity(",");

		String string = CollectionsUtil.toString(list, joinStringEntity);
		log.info(string);
	}

	/**
	 * To list.
	 */
	@Test
	public void toList(){
		List<String> list = new ArrayList<String>();
		Collections.addAll(list, "a", "b");
		Enumeration<String> enumeration = CollectionsUtil.toEnumeration(list);
		List<String> list2 = CollectionsUtil.toList(enumeration);
		log.info(JsonUtil.format(list2));

		enumeration = null;
		list2 = CollectionsUtil.toList(enumeration);
		log.info(JsonUtil.format(list2));
	}

	/**
	 * Test.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void test(){
		try{
			Class clz = User.class;
			Collection collection = CollectionUtils.typedCollection(new ArrayList(), clz);
			collection.add(clz.newInstance());

			log.info(collection.size() + "");
			for (Object object : collection){
				User user = (User) object;
				log.info(user.getName());
			}

			log.info("hahahah");

			Collection<User> collection2 = collection;
			log.info(collection2.size() + "");
			for (Object object : collection2){
				User user = (User) object;
				log.info(user.getName());
			}

		}catch (InstantiationException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}
	}

}
