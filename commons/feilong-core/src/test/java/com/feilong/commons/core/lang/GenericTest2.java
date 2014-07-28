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

import java.util.ArrayList;
import java.util.Collection;

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
public class GenericTest2{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(GenericTest2.class);

	// 声明一个泛型方法，该泛型方法中带一个T类型形参，
	static <T> void fromArrayToCollection(T[] a,Collection<T> c){
		for (T o : a){
			c.add(o);
		}
	}

	/**
	 * TestGenericTest2.
	 */
	@Test
	public void testGenericTest2(){
		Integer[] ia = new Integer[100];
		Float[] fa = new Float[100];
		Number[] na = new Number[100];
		Collection<Number> cn = new ArrayList<>();
	

		// 下面代码中T代表Number类型
		fromArrayToCollection(ia, cn);
		// 下面代码中T代表Number类型
		fromArrayToCollection(fa, cn);
		// 下面代码中T代表Number类型
		fromArrayToCollection(na, cn);
		
		
//		Collection<Integer> cn1 = new ArrayList<Integer>();
//		fromArrayToCollection(na, cn1);
	}
}