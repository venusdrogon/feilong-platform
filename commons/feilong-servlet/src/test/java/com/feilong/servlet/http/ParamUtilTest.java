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
package com.feilong.servlet.http;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-15 下午3:48:51
 */
public class ParamUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(ParamUtilTest.class);

	private String				uri	= "http://www.feilong.com:8888/esprit-frontend/search.htm?keyword=%E6%81%A4&page=";

	@Test
	public void test(){
		String pageParamName = "page";
		Object prePageNo = "";
		String addParameter = ParamUtil.addParameter(uri, pageParamName, prePageNo, CharsetType.UTF8);
		log.info(addParameter);
	}

	@Test
	public void test1(){
		String pageParamName = "label";
		Object prePageNo = "2-5-8-12";
		String addParameter = ParamUtil.addParameter(uri, pageParamName, prePageNo, CharsetType.UTF8);
		log.info(addParameter);
	}

	@Test
	public void removeParameter(){
		uri = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
		String pageParamName = "label";
		String removeParameter = ParamUtil.removeParameter(uri, pageParamName, CharsetType.ISO_8859_1);
		log.info(removeParameter);
	}
}
