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
package com.feilong.tools.middleware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.middleware.email.EmailUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-18 下午11:51:03
 */
public class EmailUtilTest{

	private static final Logger	log		= LoggerFactory.getLogger(EmailUtilTest.class);

	String						email	= "venusdrogon@163.com";

	/**
	 * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailLoginHrefByEmail(java.lang.String)} 的测试方法。
	 */
	// @Test
	// public void testGetEmailLoginHrefByEmail(){
	// System.out.println(EmailUtil.getEmailLoginHrefByEmail(email));
	// }

	/**
	 * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailChineseName(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testGetEmailChineseName(){
		log.info(EmailUtil.getEmailChineseName(email));
	}

	/**
	 * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailType(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testGetFeiLongEmailType(){
		log.info(EmailUtil.getEmailType(email).toString());
	}

	/**
	 * {@link com.feilong.tools.middleware.email.EmailUtil#getEmailPostfix(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testGetEmailPostfix(){
		log.info(EmailUtil.getEmailPostfix(email));
	}
}
