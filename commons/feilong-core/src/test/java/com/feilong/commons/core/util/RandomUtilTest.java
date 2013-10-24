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
package com.feilong.commons.core.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.PropertiesConstants;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-25 下午04:24:50
 * @since 1.0
 */
public class RandomUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(RandomUtilTest.class);

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#createRandom(double)} 的测试方法。
	 */
	@Test
	public final void testCreateRandom(){
		log.info(RandomUtil.createRandom(8) + "");
	}

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#createRandomWithLength(int)} 的测试方法。
	 */
	@Test
	public final void createRandomWithLength(){
		log.info(RandomUtil.createRandomWithLength(6) + "");
	}

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#getRandomPassword(int)} 的测试方法。
	 */
	@Test
	public final void testgetRandomFromString(){
		log.info(RandomUtil.createRandomFromString(5, PropertiesConstants.config_numbersAndAllLetters));
		log.info(RandomUtil.createRandomFromString(200, PropertiesConstants.config_numbers));
	}

	@Test
	public final void createRandomFromString(){
		log.info(RandomUtil.createRandomFromString(8, 20, PropertiesConstants.config_numbers));
	}

	@Test
	public final void createRandom(){
		log.info(RandomUtil.createRandom(10, 12) + "");
	}
}
