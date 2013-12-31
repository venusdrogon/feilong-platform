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
package com.feilong.commons.core.configure;

import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.PropertiesConstants;
import com.feilong.commons.core.util.JsonFormatUtil;

public class ResourceBundleUtilTest{

	private final static Logger	log				= LoggerFactory.getLogger(ResourceBundleUtilTest.class);

	private String				baseName		= "messages/feilong-core-test";

	private ResourceBundle		resourceBundle	= ResourceBundle.getBundle(baseName);

	@Test
	// "/WEB-INF/classes/feilong.user.properties"
	public final void getValue(){
		String aString = ResourceBundleUtil.getValue(baseName, "config_test_array");
		log.debug(aString);
		log.debug(PropertiesConstants.config_date_day);
	}

	@Test
	public final void getValueWithArguments(){
		String aString = ResourceBundleUtil.getValueWithArguments(resourceBundle, "test", "2", "22");
		log.debug(aString);
	}

	@Test
	public final void testGetPropertiesValueWithResourceBundle1(){
		boolean aString = ResourceBundleUtil.getValue(resourceBundle, "config_test_boolean", false);
		log.debug(aString + "");
	}

	@Test
	public final void testGetPropertiesValueWithResourceBundle2(){
		int aString = ResourceBundleUtil.getValue(resourceBundle, "config_test_int", 9);
		log.debug(aString + "");
	}

	@Test
	public final void readAllPropertiesToMap(){
		Map<String, String> map = ResourceBundleUtil.readAllPropertiesToMap(baseName);
		log.info(JsonFormatUtil.format(map));
	}

	@Test
	public final void readPropertiesAsArray(){
		String[] strings = ResourceBundleUtil.getArray(resourceBundle, "config_test_array", ",", String.class);
		log.info(JsonFormatUtil.format(strings));
	}

	@Test
	public final void readPrefixAsMap(){
		Map<String, String> map = ResourceBundleUtil.readPrefixAsMap(baseName, "FileType", "\\.");
		log.info(JsonFormatUtil.format(map));
	}
}
