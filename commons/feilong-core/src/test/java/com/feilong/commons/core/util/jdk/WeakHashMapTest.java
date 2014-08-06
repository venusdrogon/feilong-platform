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

import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月25日 下午6:14:43
 * @since 1.0.8
 */
public class WeakHashMapTest{

	private static final Logger	log	= LoggerFactory.getLogger(WeakHashMapTest.class);

	/**
	 * TestMapUtilTest.
	 */
	@Test
	public void testMapUtilTest2(){
		Map<String, String> map = new WeakHashMap<String, String>();
		map.put(new String("mldn"), new String("www.mldn.cn"));
		map.put("zhinangtuan", new String("www.zhinangtuan. net.cn"));
		map.put("mldnjava", new String("www.mldnjava.cn"));
		//System.gc();// 进行垃圾收集  
		map.put("lxh", new String("lixinghua"));

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(map));// 一般只会剩下一个内容  

			log.debug(map.get("lxh"));
		}
	}
}
