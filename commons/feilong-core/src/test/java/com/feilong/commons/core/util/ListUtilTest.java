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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;
import com.feilong.test.UserAddress;
import com.feilong.test.UserInfo;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-12 上午11:40:44
 * @since 1.0
 */
public class ListUtilTest{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(ListUtilTest.class);

	/** The a strings. */
	private String[]			aStrings	= { "a", "b" };

	/**
	 * Removes the.
	 */
	@Test
	public void remove(){
		List<String> list = new ArrayList<String>();
		list.add("xinge");
		list.add("feilong1");
		list.add("feilong2");
		list.add("feilong3");
		list.add("feilong4");
		list.add("feilong5");
		log.info(list.indexOf("xinge") + "");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
			String string = iterator.next();
			if (string.equals("feilong1")){
				iterator.remove();
			}
		}
		// for (int i = 0, j = list.size(); i < j; ++i){
		// // String string = list.get(i);
		//
		// }
		log.info("list:{}", JsonUtil.format(list));
	}

	/**
	 * Removes the duplicate.
	 */
	@Test
	public void removeDuplicate(){
		List<String> list = new ArrayList<String>();
		list.add("xinge");
		list.add("feilong5");
		list.add("feilong1");
		list.add("feilong2");
		list.add("feilong2");
		list.add("feilong3");
		list.add("feilong4");
		list.add("feilong4");
		list.add("feilong5");

		log.info("list:{}", JsonUtil.format(ListUtil.removeDuplicate(list)));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringReplaceBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringB(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

	/**
	 * Gets the first item.
	 * 
	 * @return the first item
	 */
	@Test
	public final void getFirstItem(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.getFirstItem(testList));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringRemoveBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringA(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringRemoveBrackets(testList));
	}

	/**
	 * To array.
	 */
	@Test
	public final void toArray(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");

		String[] array = ListUtil.toArray(testList);

		log.info(JsonUtil.format(array));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toString(java.util.List, boolean)} 的测试方法。
	 */
	@Test
	public final void testListToString(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toString(testList, true));
	}

	/**
	 * Convert list to string replace brackets.
	 */
	@Test
	public final void convertListToStringReplaceBrackets(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

	/**
	 * Convert list to string replace brackets.
	 */
	@Test
	public final void getFieldValueList(){
		List<User> testList = new ArrayList<User>();
		testList.add(new User(2L));
		testList.add(new User(5L));
		testList.add(new User(5L));

		List<String> fieldValueList = ListUtil.getFieldValueList(testList, "id");
		fieldValueList.add("7");
		fieldValueList.add("8");
		log.info(JsonUtil.format(fieldValueList));
	}

	@Test
	public final void getFieldValueList1(){
		List<User> testList = new ArrayList<User>();

		User user;
		UserInfo userInfo;

		//*******************************************************
		List<UserAddress> userAddresseList = new ArrayList<UserAddress>();
		UserAddress userAddress = new UserAddress();
		userAddress.setAddress("中南海");
		userAddresseList.add(userAddress);

		//*******************************************************
		Map<String, String> attrMap = new HashMap<String, String>();
		attrMap.put("蜀国", "赵子龙");
		attrMap.put("魏国", "张文远");
		attrMap.put("吴国", "甘兴霸");

		//*******************************************************
		String[] lovesStrings1 = { "sanguo1", "xiaoshuo1" };
		userInfo = new UserInfo();
		userInfo.setAge(28);

		user = new User(2L);
		user.setLoves(lovesStrings1);
		user.setUserInfo(userInfo);
		user.setUserAddresseList(userAddresseList);

		user.setAttrMap(attrMap);
		testList.add(user);

		//*****************************************************
		String[] lovesStrings2 = { "sanguo2", "xiaoshuo2" };
		userInfo = new UserInfo();
		userInfo.setAge(null);

		user = new User(3L);
		user.setLoves(lovesStrings2);
		user.setUserInfo(userInfo);
		user.setUserAddresseList(userAddresseList);
		user.setAttrMap(attrMap);
		testList.add(user);

		//数组
		List<String> fieldValueList1 = ListUtil.getFieldValueList(testList, "loves[1]");
		log.info(JsonUtil.format(fieldValueList1));

		//级联对象
		List<Integer> fieldValueList2 = ListUtil.getFieldValueList(testList, "userInfo.age");
		log.info(JsonUtil.format(fieldValueList2));

		//Map
		List<Integer> attrList = ListUtil.getFieldValueList(testList, "attrMap(蜀国)");
		log.info(JsonUtil.format(attrList));

		//集合
		List<String> addressList = ListUtil.getFieldValueList(testList, "userAddresseList[0]");
		log.info(JsonUtil.format(addressList));
	}
}