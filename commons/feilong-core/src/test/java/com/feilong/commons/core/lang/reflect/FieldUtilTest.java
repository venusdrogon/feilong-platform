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
package com.feilong.commons.core.lang.reflect;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月15日 下午1:23:59
 * @since 1.0.7
 */
public class FieldUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(FieldUtilTest.class);

	/**
	 * Creates the payment form.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@Test
	public final void testGetFieldValueMap() throws IllegalArgumentException,IllegalAccessException{

		User user = new User(12L);

		log.info(JsonUtil.format(FieldUtil.getFieldValueMap(user)));

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
