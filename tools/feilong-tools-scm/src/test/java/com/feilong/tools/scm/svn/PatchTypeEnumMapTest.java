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
package com.feilong.tools.scm.svn;

import java.util.EnumMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.tools.scm.command.PatchType;

/**
 * The Class PatchTypeEnumMapTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月6日 下午7:25:06
 * @since 1.0.8
 */
public class PatchTypeEnumMapTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PatchTypeEnumMapTest.class);

	/**
	 * Test patch type enum map test.
	 */
	@Test
	public void testPatchTypeEnumMapTest(){

		Map<PatchType, String> map = new EnumMap<PatchType, String>(PatchType.class);

		map.put(PatchType.ADD, "1");
		map.put(PatchType.UNKNOWN, "3");
		map.put(PatchType.DELETE, "2");
		map.put(PatchType.UPDATE, "4");

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(map));
		}
	}
}
