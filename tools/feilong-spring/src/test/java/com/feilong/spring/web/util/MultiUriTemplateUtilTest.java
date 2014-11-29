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
package com.feilong.spring.web.util;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.spring.web.util.MultiUriTemplateUtil;

/**
 * The Class MultiUriTemplateUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 17, 2012 7:56:33 PM
 */
public class MultiUriTemplateUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MultiUriTemplateUtilTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.spring.web.util.MultiUriTemplateUtil#expandBestMatchingPattern(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testExpandBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.web.util.MultiUriTemplateUtil#expandWithMultiVariable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandWithMultiVariable(){
		String requestPath = "/s/c-m-c-s-k-s100-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "Lifestyle / Graphic";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.expandWithMultiVariable(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	/**
	 * Expand with multi variable map.
	 */
	@Test
	public void expandWithMultiVariableMap(){

		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		Map<String, String> map = new HashMap<String, String>();
		map.put("categoryCode", "2541");
		map.put("style", "100");

		String variableName = "style";

		String value = URIUtil.encode("Lifestyle / Graphic", CharsetType.UTF8);
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.expandWithMultiVariable(matchingPatternPath, map, variableName, value, valueSeparator));
	}

	/**
	 * Test expand with multi variable2.
	 */
	@Test
	public void testExpandWithMultiVariable2(){
		String requestPath = "/s/c-m-c-s-k-s100,200-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "Lifestyle / Graphic";
		String valueSeparator = "@";
		log.info(MultiUriTemplateUtil.expandWithMultiVariable(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	/**
	 * Removes the multi variable value.
	 */
	@Test
	public void removeMultiVariableValue(){
		String requestPath = "/s/c-m-c-s-k-s500,100,200,9000-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "200";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	/**
	 * Removes the multi variable value222.
	 */
	@Test
	public void removeMultiVariableValue222(){
		String requestPath = "/s/c-m-c-s-k-s-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "200";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	/**
	 * Removes the multi variable value2222.
	 */
	@Test
	public void removeMultiVariableValue2222(){
		String requestPath = "/s/c-m-c-s-k-s500,100,200,9000-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "20000";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}
}
