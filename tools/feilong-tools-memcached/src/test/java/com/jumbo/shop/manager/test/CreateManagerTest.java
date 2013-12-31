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
package com.jumbo.shop.manager.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2013 6:56:10 PM
 */
@ContextConfiguration(locations = { "classpath*:spring/spring-memcached.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CreateManagerTest extends AbstractJUnit4SpringContextTests{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(CreateManagerTest.class);

	@Autowired
	private CreateManager		createManager;

	/**
	 * Test method for {@link com.feilong.manager.CreateManager#testCreate(java.lang.String)}.
	 */
	@Test
	public void testTestCreate(){
		String testCreate = createManager.testCreate("jinxin");

		log.info(testCreate);
	}

	/**
	 * Test method for {@link com.feilong.manager.CreateManager#publicSku(java.lang.String)}.
	 */
	@Test
	public void testPublicSku(){
		createManager.publicSku("asdasdasd");
	}
}
