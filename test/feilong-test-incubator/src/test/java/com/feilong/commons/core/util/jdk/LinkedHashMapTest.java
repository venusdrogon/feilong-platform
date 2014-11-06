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
package com.feilong.commons.core.util.jdk;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月14日 下午3:01:22
 * @since 1.0.8
 */
public class LinkedHashMapTest{

	private static final Logger	log	= LoggerFactory.getLogger(LinkedHashMapTest.class);

	/**
	 * Test map.
	 */
	@Test
	public void testLinkedHashMap(){
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
}
