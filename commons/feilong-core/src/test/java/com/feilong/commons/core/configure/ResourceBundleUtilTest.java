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
