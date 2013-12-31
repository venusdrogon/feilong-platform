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

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 12, 2013 10:05:17 PM
 */
public class JsonFormatUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(JsonFormatUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.lang.Object)}.
	 */
	@Test
	public final void testFormatObject(){
		log.info(JsonFormatUtil.format(1));
	}

	@Test
	public final void testFormatObjectArray(){
		Integer[] integers = { 1, 2 };
		log.info(JsonFormatUtil.format(integers));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Map)}.
	 */
	@Test
	public final void testFormatMapOfStringQextendsObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Map, java.lang.String)}.
	 */
	@Test
	public final void testFormatMapOfStringQextendsObjectString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Collection)}.
	 */
	@Test
	public final void testFormatCollectionOfQextendsObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public final void testFormatCollectionOfQextendsObjectString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public final void testFormatObjectString(){
		fail("Not yet implemented"); // TODO
	}
}
