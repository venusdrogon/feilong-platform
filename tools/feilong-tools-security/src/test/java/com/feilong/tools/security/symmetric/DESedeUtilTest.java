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
package com.feilong.tools.security.symmetric;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.test.TestConstants;
import com.feilong.tools.security.BaseSecurityTest;
import com.feilong.tools.security.EncryptionException;

/**
 * The Class DESedeUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 21, 2011 4:55:43 PM
 */
@SuppressWarnings("all")
public class DESedeUtilTest extends BaseSecurityTest{

	/** The Constant log. */
	private static final Logger	log								= LoggerFactory.getLogger(DESedeUtilTest.class);

	// ******************************************************************************************************
	/** des 默认加密解密的key. */
	public final static String	config_security_key_des_default	= "feilong";

	// ******************************************************************************************************
	/** #des 16进制加密解密. */
	public final static String	config_security_key_des_hex		= "jinxin";

	/** The symmetric type. */
	private SymmetricType		symmetricType					= SymmetricType.DESede;

	/**
	 * Test method for {@link com.feilong.commons.core.security.DESUtil#encrypBase64(java.lang.String)}.
	 * 
	 * @throws EncryptionException
	 */
	@Test
	public final void decryptBase64() throws EncryptionException{
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, config_security_key_des_default);
		String mingsString = symmetricEncryption.decryptBase64("LdCGo0dplVASWwJrvlHqpw==", CharsetType.UTF8);
		log.debug(mingsString);
		assertEquals(TestConstants.testString, mingsString);
	}

	/**
	 * Encryp base641.
	 * 
	 * @throws EncryptionException
	 * @throws NullPointerException
	 */
	@Test
	public final void encrypBase641() throws NullPointerException,EncryptionException{
		SymmetricEncryption dESUtil = new SymmetricEncryption(symmetricType, config_security_key_des_default);
		log.debug(dESUtil.encryptBase64("金鑫金鑫金鑫", CharsetType.UTF8));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.security.DESUtil#decryptBase64(java.lang.String)}.
	 * 
	 * @throws EncryptionException
	 * @throws NullPointerException
	 */
	@Test
	// @Ignore
	public final void encrypBase64() throws NullPointerException,EncryptionException{
		SymmetricEncryption dESUtil = new SymmetricEncryption(symmetricType, config_security_key_des_hex);
		//	assertEquals(expected, actual);
		// oKLAr5N7UK2VzL0kLwnKDA9BoaAU62rV
		//oKLAr5N7UK2VzL0kLwnKDPg/nQZBlrXn
		log.debug(dESUtil.encryptBase64(TestConstants.testString, CharsetType.UTF8));
	}

	/**
	 * Encrypt hex.
	 * 
	 * @throws EncryptionException
	 * @throws NullPointerException
	 */
	@Test
	public void encryptHex() throws NullPointerException,EncryptionException{
		String keyString = "jinxin";
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
