/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.spring.util;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.JsonFormatUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 17, 2012 7:37:41 PM
 */
public class UriTemplateUtilTest{

	private static final Logger	log				= LoggerFactory.getLogger(UriTemplateUtilTest.class);

	String						uriTemplatePath	= "/c{categoryCode}/m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#getVariableNames(java.lang.String)}.
	 */
	@Test
	public void testGetVariableNames(){
		List<String> list = UriTemplateUtil.getVariableNames(uriTemplatePath);

		log.info("list:{}", JsonFormatUtil.format(list));
	}

	@Test
	public void testExpandWithVariable(){
		String list = UriTemplateUtil.expandWithVariable(uriTemplatePath, "color", "a");
		log.info(list);
	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#getUriTemplateVariableValue(javax.servlet.http.HttpServletRequest, java.lang.String)}.
	 */
	@Test
	public void testGetUriTemplateVariableValue(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#hasPathVarName(javax.servlet.http.HttpServletRequest, java.lang.String)}.
	 */
	@Test
	public void testHasPathVarName(){

	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#getUriTemplateVariables(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGetUriTemplateVariables(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#getBestMatchingPattern(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGetBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.util.UriTemplateUtil#expandBestMatchingPattern(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.util.UriTemplateUtil#expandWithVariable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandWithVariable3(){
		String requestPath = "/s/c-m-c-s-k-s100-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "color";
		String value = "100";
		log.info(UriTemplateUtil.expandWithVariable(requestPath, matchingPatternPath, variableName, value));
	}

	@Test
	public void clearVariablesValue(){
		String requestPath = "/s/c500-m60-cred-s-k-s100-o6.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String[] variableNames = { "color", "style" };
		log.info(UriTemplateUtil.clearVariablesValue(requestPath, matchingPatternPath, variableNames));
	}

	@Test
	public void retainVariablesValue(){
		String requestPath = "/s/c500-m60-cred-s-k-s100-o6.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String[] variableNames = { "color", "style" };
		log.info(UriTemplateUtil.retainVariablesValue(requestPath, matchingPatternPath, variableNames));
	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#extractUriTemplateVariables(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExtractUriTemplateVariables(){
		String requestPath = "/c/m-caaa-s-k-s-o.htm";
		String matchingPatternPath = uriTemplatePath;
		Map<String, String> map = UriTemplateUtil.extractUriTemplateVariables(requestPath, matchingPatternPath);
		log.info("map:{}", JsonFormatUtil.format(map));
	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#expandWithVariable(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testExpandWithVariable2(){

		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "color";
		String value = "100";
		log.info(UriTemplateUtil.expandWithVariable(matchingPatternPath, variableName, value));

	}

	/**
	 * Test method for {@link com.feilong.spring.util.UriTemplateUtil#expand(java.lang.String, java.util.Map)}.
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
