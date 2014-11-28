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
package com.feilong.tools;

import org.dragonfly.jsdocx.JsdocCreator;
import org.dragonfly.jsdocx.JsdocException;
import org.dragonfly.jsdocx.output.JsdocConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-21 上午10:23:28
 */
@SuppressWarnings("all")
public class JsDocxUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(JsDocxUtilTest.class);

	@Test
	public void test(){
		String sourceDir = "D:/feilong";
		String docDir = "D:/feilongDoc";
		String charset = CharsetType.GBK;
		boolean isClean = false;
		JsdocConfig jsdocConfig = new JsdocConfig();
		jsdocConfig.setSourceDir(sourceDir);
		jsdocConfig.setDocDir(docDir);
		jsdocConfig.setCharset(charset);
		jsdocConfig.setClean(isClean);
		JsdocCreator jsdocCreator = new JsdocCreator(jsdocConfig);
		try{
			jsdocCreator.generate();
		}catch (JsdocException e){
			log.error(e.getClass().getName(), e);
		}
	}
}
