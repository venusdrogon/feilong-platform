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
package com.feilong.commons.core.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 17, 2011 2:02:13 AM
 */
public class ThreadUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(ThreadUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.lang.ThreadUtil#getCurrentMethodName(java.lang.Thread)}.
	 */
	@Test
	public void testGetMethodName(){
		Thread currentThread = Thread.currentThread();
		log.info(ThreadUtil.getCurrentMethodName(currentThread));
	}

	@Test
	public void testGetMethodName1(){
		log.info("1");
		testGetMethodName2();
	}

	@Test
	public void testGetMethodName2(){
		log.info("2");
		testGetMethodName3();
	}

	@Test
	public void testGetMethodName3(){
		log.info("3");
		testGetMethodName();
	}
}
