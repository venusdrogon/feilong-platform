/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.commons.core.security.symmetric;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.TestConstants;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.security.symmetric.SymmetricEncryption;
import com.feilong.commons.core.security.symmetric.SymmetricType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 21, 2011 4:55:43 PM
 */
public class DESUtilTest{

	private static final Logger	log								= LoggerFactory.getLogger(DESUtilTest.class);

	// ******************************************************************************************************
	/** des 默认加密解密的key. */
	public final static String	config_security_key_des_default	= "feilong";

	// ******************************************************************************************************
	/** #des 16进制加密解密. */
	public final static String	config_security_key_des_hex		= "jinxin";

	/**
	 * Test method for {@link com.feilong.commons.core.security.DESUtil#encrypBase64(java.lang.String)}.
	 */
	@Test
	public final void decryptBase64(){
		SymmetricType symmetricType = SymmetricType.DES;
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, config_security_key_des_default);
		String mingsString = symmetricEncryption.decryptBase64("LdCGo0dplVASWwJrvlHqpw==", CharsetType.UTF8);
		log.debug(mingsString);
		assertEquals(TestConstants.testString, mingsString);
	}

	@Test
	public final void encrypBase641(){
		String aString = "06123246FFFEFFEE";
		String key = "1234567890ABCDEF";
		SymmetricEncryption dESUtil = new SymmetricEncryption(SymmetricType.DES, key);
		log.debug(dESUtil.encrypBase64(aString, CharsetType.UTF8));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.security.DESUtil#decryptBase64(java.lang.String)}.
	 */
	@Test
	// @Ignore
	public final void encrypBase64(){
		SymmetricType des = SymmetricType.DES;
		SymmetricEncryption dESUtil = new SymmetricEncryption(des, config_security_key_des_hex);
		// LdCGo0dplVASWwJrvlHqpw==
		log.debug(dESUtil.encrypBase64(TestConstants.testString, CharsetType.UTF8));
	}

	@Test
	public void encryptHex(){
		String keyString = "jinxin";
		SymmetricType symmetricType = SymmetricType.DES;
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, keyString);
		String miString = symmetricEncryption.encryptHex(TestConstants.testString, CharsetType.UTF8);
		// String miString = dESUtil.encryptToHexString(TestConstants.testString);
		log.debug("encryptOriginalToHexString:{}", miString);
		String mingString = symmetricEncryption.decryptHex(miString, CharsetType.UTF8);
		log.debug(mingString);
		Assert.assertEquals(mingString, TestConstants.testString);

		log.info("055976934539FAAAD7F0EB5C83FE0D5D".length() + "");
	}
}
