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

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.PropertiesConstants;
import com.feilong.tools.json.JsonUtil;

/**
 * The Class ResourceBundleUtilTest.
 */
public class ResourceBundleUtilTest{

	/** The Constant log. */
	private final static Logger	log				= LoggerFactory.getLogger(ResourceBundleUtilTest.class);

	/** The base name. */
	private String				baseName		= "messages/feilong-core-test";

	/** The resource bundle. */
	private ResourceBundle		resourceBundle	= ResourceBundle.getBundle(baseName);

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	@Test
	// "/WEB-INF/classes/feilong.user.properties"
	public final void getValue(){
		String aString = ResourceBundleUtil.getValue(baseName, "config_test_array");
		log.debug(aString);
		log.debug(PropertiesConstants.CONFIG_DATE_DAY);
	}

	/**
	 * Gets the value with arguments.
	 * 
	 * @return the value with arguments
	 */
	@Test
	public final void getValueWithArguments(){
		String aString = ResourceBundleUtil.getValueWithArguments(resourceBundle, "test", "2", "22");
		log.debug(aString);
	}

	/**
	 * Read properties as array.
	 */
	@Test
	public final void readPropertiesAsArray(){
		String[] strings = ResourceBundleUtil.getArray(resourceBundle, "config_test_array", ",", String.class);
		log.info(JsonUtil.format(strings));
	}

	/**
	 * Read prefix as map.
	 */
	@Test
	public final void readPrefixAsMap(){

		Locale locale = Locale.CHINA;
		Map<String, String> map = ResourceBundleUtil.readPrefixAsMap(baseName, "FileType", "\\.", locale);
		log.info(JsonUtil.format(map));
	}

	/**
	 * Read all properties to map.
	 */
	@Test
	public final void readAllPropertiesToMap(){
		Locale locale = Locale.CHINA;
		baseName = "messages/feilong-core-message";
		Map<String, String> map = ResourceBundleUtil.readAllPropertiesToMap(baseName, locale);
		log.info(JsonUtil.format(map));
	}

	/**
	 * Read all properties to map.
	 */
	@Test
	public final void getValue1(){
		Locale locale = Locale.ENGLISH;
		baseName = "messages/feilong-core-message";
		log.info(ResourceBundleUtil.getValue(baseName, "config_date_hour", locale));
	}
}
