/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 17, 2012 7:56:33 PM
 */
public class MultiUriTemplateUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(MultiUriTemplateUtilTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.spring.util.MultiUriTemplateUtil#expandBestMatchingPattern(javax.servlet.http.HttpServletRequest, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testExpandBestMatchingPattern(){
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.feilong.spring.util.MultiUriTemplateUtil#expandWithMultiVariable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
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

	@Test
	public void testExpandWithMultiVariable2(){
		String requestPath = "/s/c-m-c-s-k-s100,200-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "Lifestyle / Graphic";
		String valueSeparator = "@";
		log.info(MultiUriTemplateUtil.expandWithMultiVariable(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	@Test
	public void removeMultiVariableValue(){
		String requestPath = "/s/c-m-c-s-k-s500,100,200,9000-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "200";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

	@Test
	public void removeMultiVariableValue222(){
		String requestPath = "/s/c-m-c-s-k-s-o.htm";
		String matchingPatternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";
		String variableName = "style";
		String value = "200";
		String valueSeparator = ",";
		log.info(MultiUriTemplateUtil.removeMultiVariableValue(requestPath, matchingPatternPath, variableName, value, valueSeparator));
	}

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
