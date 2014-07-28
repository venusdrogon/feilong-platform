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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-22 下午03:45:36
 * @since 1.0
 */
public class GenericTest{

	/** The Constant log. */
	private static final Logger	log		= LoggerFactory.getLogger(GenericTest.class);

	/** The list. */
	public List<String>			list	= new ArrayList<String>();

	/**
	 * Gets the value.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param a
	 *            the a
	 * @param clz
	 *            the clz
	 * @return the value
	 */
	public static <T> T getValue(String a,Class<?> clz){
		log.info("" + (clz == String.class));
		T aT = null;
		try{
			Method method = GenericTest.class.getMethod("getValue", String.class, Class.class);
			TypeVariable<?> typeVariable = (TypeVariable<?>) method.getGenericReturnType();
			log.info(typeVariable.toString());
			log.info(typeVariable.getName());
			log.info("" + typeVariable.getBounds()[0]);
			log.info(typeVariable.getGenericDeclaration().toString());
			log.info(method.toGenericString());
			log.info(method.toString());
		}catch (SecurityException e){
			e.printStackTrace();
		}catch (NoSuchMethodException e){
			e.printStackTrace();
		}
		return aT;
	}

	/**
	 * TestGenericTest1.
	 * 
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 * @throws SecurityException
	 *             the security exception
	 */
	@Test
	public void testGenericTest1() throws NoSuchFieldException,SecurityException{
		Field field = GenericTest.class.getField("list");
		ParameterizedType pt = (ParameterizedType) field.getGenericType();
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		log.info("" + actualTypeArguments.length);
		log.info("" + actualTypeArguments[0]);
		//assertEquals(expected, actual);
	}

	/**
	 * TestGenericTest.
	 */
	@Test
	public void testGenericTest(){
		String aString = getValue("jinxin", String.class);
		Integer b = getValue("jinxin", Integer.class);
		//assertEquals(expected, actual);
	}

}