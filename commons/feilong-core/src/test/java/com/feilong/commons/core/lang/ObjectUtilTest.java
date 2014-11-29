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
package com.feilong.commons.core.lang;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * The Class ObjectUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 4, 2013 1:58:05 PM
 */
public class ObjectUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ObjectUtilTest.class);

	/**
	 * Name.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void name() throws IOException{
		//		 log.info("Size of Object: " + ObjectUtil.size(new Object()));
		log.info("Size of Calendar: " + ObjectUtil.size(Calendar.getInstance()));
		log.info("Size of HashMap: " + ObjectUtil.size(new HashMap<String, String>()));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#equalsNotNull(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testEqualsNotNull(){
		Assert.assertEquals(false, ObjectUtil.equalsNotNull(1, 2));
		Assert.assertEquals(false, ObjectUtil.equalsNotNull(1, null));
		Assert.assertEquals(false, ObjectUtil.equalsNotNull(null, 2));
		Assert.assertEquals(false, ObjectUtil.equalsNotNull(null, null));

		Assert.assertEquals(false, ObjectUtil.equalsNotNull(1, "1"));
		Assert.assertEquals(true, ObjectUtil.equalsNotNull(1, 1));
		Assert.assertEquals(true, ObjectUtil.equalsNotNull("1", "1"));
	}

	/**
	 * Assert equals.
	 */
	@Test
	@SuppressWarnings("all")
	public final void assertEquals(){

		Long a = new Long(1L);
		Long b = new Long(1L);

		log.info((1L == 1L) + "");
		log.info((a == b) + "");
		log.info(a.equals(b) + "");

		User user = new User(1L);
		List<User> list = new ArrayList<User>();

		list.add(user);
		list.add(new User(1L));
		list.add(new User(new Long(1L)));
		list.add(new User(new Long(1L)));
		list.add(new User(new Long(1L)));

		for (User user2 : list){
			log.info((user2.getId() == user.getId()) + "");
		}
	}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#equals(java.lang.Object, java.lang.Object, boolean)}.
	 */
	@Test
	public final void testEqualsObjectObjectBoolean(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#equals(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObjectObject(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#isBoolean(java.lang.Object)}.
	 */
	@Test
	public final void testIsBoolean(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#isInteger(java.lang.Object)}.
	 */
	@Test
	public final void testIsInteger(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toBoolean(java.lang.Object)}.
	 */
	@Test
	public final void testToBoolean(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toIterator(java.lang.Object)}.
	 */
	@Test
	public final void testToIterator(){

		// *************************逗号分隔的数组********************************
		log.info(StringUtils.center("逗号分隔的数组", 60, "*"));
		Iterator<?> iterator = ObjectUtil.toIterator("1,2");
		printIterator(iterator);

		// ************************map*********************************
		log.info(StringUtils.center("map", 60, "*"));
		Map<String, String> map = new HashMap<String, String>();

		map.put("a", "1");
		map.put("b", "2");

		iterator = ObjectUtil.toIterator(map);
		printIterator(iterator);

		// ***************************array******************************
		log.info(StringUtils.center("array", 60, "*"));
		Object[] array = { "5", 8 };
		iterator = ObjectUtil.toIterator(array);
		printIterator(iterator);
		// ***************************collection******************************
		log.info(StringUtils.center("collection", 60, "*"));
		Collection<String> collection = new ArrayList<String>();
		collection.add("aaaa");
		collection.add("nnnnn");

		iterator = ObjectUtil.toIterator(collection);
		printIterator(iterator);

		// **********************enumeration***********************************
		log.info(StringUtils.center("enumeration", 60, "*"));
		Enumeration<Object> enumeration = new StringTokenizer("this is a test");
		log.debug(JsonUtil.format(ObjectUtil.toIterator(enumeration)));
	}

	/**
	 * Prints the iterator.
	 * 
	 * @param iterator
	 *            the iterator
	 */
	private void printIterator(Iterator<?> iterator){
		while (iterator.hasNext()){
			Object object = iterator.next();
			log.info(object.toString());
		}
	}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toInteger(java.lang.Object)}.
	 */
	@Test
	public final void testToInteger(){
		Assert.assertEquals(8, ObjectUtil.toInteger(8L).intValue());
		Assert.assertEquals(8, ObjectUtil.toInteger("8").intValue());
	}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toBigDecimal(java.lang.Object)}.
	 */
	@Test
	public final void testToBigDecimal(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toLong(java.lang.Object)}.
	 */
	@Test
	public final void testToLong(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toDouble(java.lang.Object)}.
	 */
	@Test
	public final void testToDouble(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toFloat(java.lang.Object)}.
	 */
	@Test
	public final void testToFloat(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toShort(java.lang.Object)}.
	 */
	@Test
	public final void testToShort(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toString(java.lang.Object)}.
	 */
	@Test
	public final void testToStringObject(){}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#toT(java.lang.Object, java.lang.Class)}.
	 */
	@Test
	public final void testToT(){
		log.info(ObjectUtil.toT(BigDecimal.ONE, Float.class) + "");
	}

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ObjectUtil#trim(java.lang.Object)}.
	 */
	@Test
	public final void testTrim(){}
}
