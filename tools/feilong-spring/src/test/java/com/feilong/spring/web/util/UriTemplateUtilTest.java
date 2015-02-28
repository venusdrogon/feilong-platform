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
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class UriTemplateUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 17, 2012 7:37:41 PM
 */
public class UriTemplateUtilTest{

	/** The Constant log. */
	private static final Logger	log				= LoggerFactory.getLogger(UriTemplateUtilTest.class);

	/** The uri template path. */
	String						uriTemplatePath	= "/c{categoryCode}/m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#getVariableNames(java.lang.String)}.
	 */
	@Test
	public void testGetVariableNames(){
		List<String> list = UriTemplateUtil.getVariableNames(uriTemplatePath);

		log.info("list:{}", JsonUtil.format(list));
	}

	/**
	 * Test expand with variable.
	 */
	@Test
	public void testExpandWithVariable(){
		String list = UriTemplateUtil.expandWithVariable(uriTemplatePath, "color", "a");
		log.info(list);
	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#getUriTemplateVariableValue(javax.servlet.http.HttpServletRequest, java.lang.String)}.
	 */
	@Test
	public void testGetUriTemplateVariableValue(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#hasPathVarName(javax.servlet.http.HttpServletRequest, java.lang.String)}.
	 */
	@Test
	public void testHasPathVarName(){

	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#getUriTemplateVariables(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGetUriTemplateVariables(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#getBestMatchingPattern(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGetBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.web.util.UriTemplateUtil#expandBestMatchingPattern(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.web.util.UriTemplateUtil#expandWithVariable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandWithVariable3(){
		String requestPath = "/s/c-m-c-s-k-s100-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "color";
		String value = "100";
		log.info(UriTemplateUtil.expandWithVariable(requestPath, matchingPatternPath, variableName, value));
	}

	/**
	 * Clear variables value.
	 */
	@Test
	public void clearVariablesValue(){
		String requestPath = "/s/c500-m60-cred-s-k-s100-o6.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String[] variableNames = { "color", "style" };
		log.info(UriTemplateUtil.clearVariablesValue(requestPath, matchingPatternPath, variableNames));
	}

	/**
	 * Retain variables value.
	 */
	@Test
	public void retainVariablesValue(){
		String requestPath = "/s/c500-m60-cred-s-k-s100-o6.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String[] variableNames = { "color", "style" };
		log.info(UriTemplateUtil.retainVariablesValue(requestPath, matchingPatternPath, variableNames));
	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#extractUriTemplateVariables(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExtractUriTemplateVariables(){
		String requestPath = "/c/m-caaa-s-k-s-o.htm";
		String matchingPatternPath = uriTemplatePath;
		Map<String, String> map = UriTemplateUtil.extractUriTemplateVariables(requestPath, matchingPatternPath);
		log.info("map:{}", JsonUtil.format(map));
	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#expandWithVariable(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandWithVariable2(){

		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "color";
		String value = "100";
		log.info(UriTemplateUtil.expandWithVariable(matchingPatternPath, variableName, value));

	}

	/**
	 * Test method for {@link com.feilong.spring.web.util.UriTemplateUtil#expand(java.lang.String, java.util.Map)}.
	 */
	@Test
	public void testExpand(){
		String uriTemplatePath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		Map<String, String> map = new HashMap<String, String>();
		map.put("color", "100");
		map.put("size", "L");
		map.put("K", "aaaa");
		log.info(UriTemplateUtil.expand(uriTemplatePath, map));
	}
}
