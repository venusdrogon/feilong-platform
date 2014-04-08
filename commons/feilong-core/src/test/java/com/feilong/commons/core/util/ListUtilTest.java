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
package com.feilong.commons.core.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.json.JsonUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-12 上午11:40:44
 * @since 1.0
 */
public class ListUtilTest{

	private static final Logger	log			= LoggerFactory.getLogger(ListUtilTest.class);

	private String[]			aStrings	= { "a", "b" };

	@Test
	public void remove(){
		List<String> list = new ArrayList<String>();
		list.add("xinge");
		list.add("feilong1");
		list.add("feilong2");
		list.add("feilong3");
		list.add("feilong4");
		list.add("feilong5");
		log.info(list.indexOf("xinge") + "");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
			String string = (String) iterator.next();
			if (string.equals("feilong1")){
				iterator.remove();
			}
		}
		// for (int i = 0, j = list.size(); i < j; ++i){
		// // String string = list.get(i);
		//
		// }
		log.info("list:{}", JsonUtil.format(list));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringReplaceBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringB(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

	@Test
	public final void getFirstItem(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.getFirstItem(testList));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringRemoveBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringA(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringRemoveBrackets(testList));
	}

	@Test
	public final void toArray(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");

		String[] array = ListUtil.toArray(testList);

		log.info(JsonUtil.format(array));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toString(java.util.List, boolean)} 的测试方法。
	 */
	@Test
	public final void testListToString(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toString(testList, true));
	}

	@Test
	public final void convertListToStringReplaceBrackets(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

}
