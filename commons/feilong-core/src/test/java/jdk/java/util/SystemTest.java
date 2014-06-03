/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package jdk.java.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemTest{

	private static final Logger	log	= LoggerFactory.getLogger(SystemTest.class);

	@Test
	public void getProperty(){
		log.info(System.getProperty("path"));
		System.getProperties().list(System.out);
	}

	@Test
	public void getenv(){
		Map<String, String> map = System.getenv();
		Object[] key = map.keySet().toArray();
		Arrays.sort(key);
		for (int i = 0; i < key.length; i++){
			log.info(key[i] + "======>" + map.get(key[i]));
		}
	}

	@Test
	public void path(){
		String path = System.getenv("Path");
		// log.info(path);
		String[] strings = path.split(";");
		Arrays.sort(strings);
		for (String p : strings){
			log.info(p);
		}
	}

	@Test
	public void currentTimeMillis(){
		log.info("" + System.currentTimeMillis());
	}

	@Test
	public void testSystem(){
		Properties properties = System.getProperties();
		Object[] key = properties.keySet().toArray();
		Arrays.sort(key);
		for (int i = 0; i < key.length; i++){
			log.info(key[i] + "======>" + properties.get(key[i]));
		}
		// OutputStream os = IOUtil.getFileOutputStream("E:/1.xml");
		// try{
		// properties.storeToXML(os, "ceshi");
		// }catch (IOException e){
		// e.printStackTrace();
		// }
		// FeiLongTestUtil.print(properties);
	}

	@Test
	public void test(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("aaaa", "aaaa");
		map.put("cccc", "cccc");
		map.put("bbbb", "bbbb");
		map.put("dddd", "dddd");
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()){
			Object key = iterator.next();
			Object obj = map.get(key);
			log.info("" + obj);
		}
		log.info("---------------------------");
		Object[] key = map.keySet().toArray();
		Arrays.sort(key);
		for (int i = 0; i < key.length; i++){
			log.info(map.get(key[i]));
		}
	}
}
