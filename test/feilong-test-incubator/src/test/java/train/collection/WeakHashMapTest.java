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
package train.collection;

import java.util.Map;
import java.util.WeakHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.test.User;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月16日 下午2:55:37
 * @since 1.0.8
 */
public class WeakHashMapTest{

	private static final Logger	log	= LoggerFactory.getLogger(WeakHashMapTest.class);

	/**
	 * TestLinkedHashMapTest.
	 */
	@Test
	public void testLinkedHashMapTest(){
		Map<User, String> map = new WeakHashMap<User, String>();

		map.put(new User("张飞"), "矛");
		map.put(new User("关羽"), "刀");
		map.put(new User("刘备"), "剑");
		map.put(new User("赵云"), "枪");
		map.put(new User("张飞"), "矛1");
		System.gc();
		if (log.isInfoEnabled()){
			log.info("\n{}", map.get(new User("张飞")));
			log.info("\n{}", map.get(new User("关羽")));
		}

		//assertEquals(expected, actual);
	}

}
