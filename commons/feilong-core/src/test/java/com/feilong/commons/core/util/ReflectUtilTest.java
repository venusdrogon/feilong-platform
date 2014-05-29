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

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.Person;
import com.feilong.test.User;

/**
 * The Class ReflectUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 11, 2014 10:53:27 PM
 */
public class ReflectUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(ReflectUtilTest.class);

	/**
	 * New instance.
	 * 
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 */
	@Test
	public final void newInstance() throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,
			InvocationTargetException{

		User user = ReflectUtil.newInstance("com.feilong.test.User");
		log.info(JsonUtil.format(user));

		User user1 = ReflectUtil.newInstance("com.feilong.test.User", 100L);
		log.info(JsonUtil.format(user1));
	}

	/**
	 * Creates the payment form.
	 * 
	 * @return the field value map
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public final void getFieldValueMap() throws IllegalArgumentException,IllegalAccessException{

		User user = new User(12L);

		log.info(JsonUtil.format(ReflectUtil.getFieldValueMap(user)));

		// BeanInfo beanInfo = Introspector.getBeanInfo(class1);
		//
		// PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		//
		// for (PropertyDescriptor propertyDescriptor : propertyDescriptors){
		// String name = propertyDescriptor.getName();
		// log.info(name);
		// if ("class".equals(name) && "classLoader".equals(name)){
		// // Ignore Class.getClassLoader() method - nobody needs to bind to that
		// continue;
		// }
		//
		// // Method readMethod = propertyDescriptor.getReadMethod();
		// // Object invoke = readMethod.invoke(class1);
		// // log.info(invoke.toString());
		// }
	}
}
