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
package com.feilong.commons.core.lang.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 泛型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-22 下午03:45:36
 * @since 1.0
 */
public class GenericTest1{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(GenericTest1.class);

	static <T> void test(Collection<T> from,Collection<Object> to){
		for (T ele : from){
			to.add(ele);
		}
	}

	public static void main(String[] args){
		List<Object> as = new ArrayList<>();
		List<String> ao = new ArrayList<>();
		// 下面代码将产生编译错误，系统无法判断泛型参数T是Object还是String类型
//		test(as, ao);
	}
}