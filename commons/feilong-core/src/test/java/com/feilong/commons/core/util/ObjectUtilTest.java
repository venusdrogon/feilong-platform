/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.util;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 4, 2013 1:58:05 PM
 */
public class ObjectUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(ObjectUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#equalsNotNull(java.lang.Object, java.lang.Object)}.
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
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#equals(java.lang.Object, java.lang.Object, boolean)}.
	 */
	@Test
	public final void testEqualsObjectObjectBoolean(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#equals(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public final void testEqualsObjectObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#isBoolean(java.lang.Object)}.
	 */
	@Test
	public final void testIsBoolean(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#isInteger(java.lang.Object)}.
	 */
	@Test
	public final void testIsInteger(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toBoolean(java.lang.Object)}.
	 */
	@Test
	public final void testToBoolean(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toIterator(java.lang.Object)}.
	 */
	@Test
	public final void testToIterator(){

		// *************************逗号分隔的数组********************************
		log.info("*************************逗号分隔的数组********************************");
		Iterator iterator = ObjectUtil.toIterator("1,2");
		printIterator(iterator);

		// ************************map*********************************
		log.info("************************map*********************************");
		Map<String, String> map = new HashMap<String, String>();

		map.put("a", "1");
		map.put("b", "2");

		iterator = ObjectUtil.toIterator(map);
		printIterator(iterator);

		// ***************************array******************************
		log.info("***************************array******************************");
		Object[] array = { "5", 8 };
		iterator = ObjectUtil.toIterator(array);
		printIterator(iterator);
		// ***************************collection******************************
		log.info("***************************collection******************************");
		Collection<String> collection = new ArrayList<String>();
		collection.add("aaaa");
		collection.add("nnnnn");

		iterator = ObjectUtil.toIterator(collection);
		printIterator(iterator);
		try{
			// ***************************enumeration******************************
			log.info("***************************enumeration******************************");
			Enumeration<Object> enumeration = new StringTokenizer("this is a test");

			iterator = ObjectUtil.toIterator(enumeration);
			printIterator(iterator);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param iterator
	 */
	private void printIterator(Iterator iterator){
		while (iterator.hasNext()){
			Object object = (Object) iterator.next();
			log.info(object.toString());
		}
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toInteger(java.lang.Object)}.
	 */
	@Test
	public final void testToInteger(){
		Assert.assertEquals(8, ObjectUtil.toInteger(8L).intValue());
		Assert.assertEquals(8, ObjectUtil.toInteger("8").intValue());
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toBigDecimal(java.lang.Object)}.
	 */
	@Test
	public final void testToBigDecimal(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toLong(java.lang.Object)}.
	 */
	@Test
	public final void testToLong(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toDouble(java.lang.Object)}.
	 */
	@Test
	public final void testToDouble(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toFloat(java.lang.Object)}.
	 */
	@Test
	public final void testToFloat(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toShort(java.lang.Object)}.
	 */
	@Test
	public final void testToShort(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toString(java.lang.Object)}.
	 */
	@Test
	public final void testToStringObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#toT(java.lang.Object, java.lang.Class)}.
	 */
	@Test
	public final void testToT(){
		log.info(ObjectUtil.toT(BigDecimal.ONE, Float.class) + "");
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.ObjectUtil#trim(java.lang.Object)}.
	 */
	@Test
	public final void testTrim(){
		fail("Not yet implemented"); // TODO
	}
}
