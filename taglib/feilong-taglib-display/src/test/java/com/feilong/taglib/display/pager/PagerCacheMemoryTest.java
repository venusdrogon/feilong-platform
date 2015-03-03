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
package com.feilong.taglib.display.pager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.test.User;

/**
 * The Class MapMemoryTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月24日 下午11:23:49
 * @since 1.0.7
 */
public class PagerCacheMemoryTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PagerCacheMemoryTest.class);

	/**
	 * Name.
	 */
	@Test
	public void name(){
		int j = 1000000;//43.42MB
		j = 100000000;//java.lang.OutOfMemoryError: Java heap space 
		j = 10000000;//java.lang.OutOfMemoryError: Java heap space 
		j = 8000000;//java.lang.OutOfMemoryError: Java heap space 
		j = 5000000;//232.2MB
		//		j = 6000000;//java.lang.OutOfMemoryError: Java heap space 
		//		j = 1;//84.35KB
		//		j = 100;//84.35KB

		j = 1000000;//228.38MB new User()
		j = 100000;//13.67MB new User()
		j = 500000;//135.10MB new User()
		j = 50000;//59.75KB new User()
		j = 50000;//59.75KB new User()

		Integer a = 1;

		// 先垃圾回收
		System.gc();
		long start = Runtime.getRuntime().freeMemory();
		Map map = new HashMap();

		for (int i = 0; i < j; i++){
			//map.put(i, a);
			map.put(
							new User(),
							"ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
		}
		// 快要计算的时,再清理一次
		System.gc();
		long end = Runtime.getRuntime().freeMemory();
		log.info("一个HashMap对象占内存:" + FileUtil.formatSize((end - start)));
	}
}
