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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.taglib.display.pager.command.PagerConstants;
import com.feilong.taglib.display.pager.command.PagerParams;

/**
 * The Class PagerUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-23 15:58:19
 */
public class IndexAndHrefMapTest extends BasePagerTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(IndexAndHrefMapTest.class);

	/**
	 * Gets the all index and href map.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	@Test
	public void getAllIndexAndHrefMap() throws IllegalArgumentException,IllegalAccessException,InvocationTargetException,SecurityException,
			NoSuchMethodException{

		int z = 80000;

		Date beginDate = new Date();

		Method declaredMethod = PagerUtil.class.getDeclaredMethod("getAllIndexAndHrefMap", PagerParams.class, Set.class);
		declaredMethod.setAccessible(true);

		Set<Integer> set = new HashSet<Integer>();
		set.add(PagerConstants.DEFAULT_TEMPLATE_PAGE_NO);

		for (int i = 100; i < 120; ++i){
			set.add(i);
		}
		PagerParams pagerParams = getPagerParams();
		if (log.isInfoEnabled()){
			log.info(JsonUtil.format(pagerParams));
		}

		for (int j = 0; j < z; ++j){
			//@SuppressWarnings({ "unchecked", "unused" })
			//Map<Integer, String> invoke = (Map<Integer, String>) 
			declaredMethod.invoke(PagerUtil.class, pagerParams, set);
		}

		Date endDate = new Date();
		log.info("{},time:{}", z, DateUtil.getIntervalTime(beginDate, endDate));
	}
}