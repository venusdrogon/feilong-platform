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

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.test.User;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月25日 下午6:14:43
 * @since 1.0.8
 */
public class IdentityHashMapTest{

	private static final Logger	log	= LoggerFactory.getLogger(IdentityHashMapTest.class);

	/**
	 * TestMapUtilTest.
	 */
	@Test
	public void testMapUtilTest2(){

		Map<Integer, Object> object = new IdentityHashMap<Integer, Object>();

		object.put(0, "0");
		object.put(1, "1");
		object.put(2, "2");
		object.put(3, "3");
		object.put(0, "1");

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(object));
		}

		//assertEquals(expected, actual);
	}

	/**
	 * Testenclosing_type2.
	 */
	@Test
	public void testenclosing_type2(){
		Map<User, Object> object1 = new HashMap<User, Object>();
		object1.put(new User(1L), "0");
		object1.put(new User(1L), "1");
		object1.put(new User(1L), "2");
		object1.put(new User(1L), "3");

		if (log.isDebugEnabled()){
			log.debug("" + object1.size());
		}
		//assertEquals(expected, actual);
	}
}
