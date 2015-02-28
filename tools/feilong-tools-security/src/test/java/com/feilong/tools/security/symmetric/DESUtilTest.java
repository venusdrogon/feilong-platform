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

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.test.TestConstants;
import com.feilong.tools.security.BaseSecurityTest;
import com.feilong.tools.security.EncryptionException;

/**
 * The Class DESUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 21, 2011 4:55:43 PM
 */
@SuppressWarnings("all")
public class DESUtilTest extends BaseSecurityTest{

	/** The Constant log. */
	private static final Logger	log								= LoggerFactory.getLogger(DESUtilTest.class);

	// ******************************************************************************************************
	/** des 默认加密解密的key. */
	public final static String	config_security_key_des_default	= "feilong";

	// ******************************************************************************************************
	/** #des 16进制加密解密. */
	public final static String	config_security_key_des_hex		= "jinxin";

	/**
	 * Test method for {@link com.feilong.commons.core.security.DESUtil#encrypBase64(java.lang.String)}.
	 * 
	 * @throws EncryptionException
	 * @throws NullPointerException
	 */
	@Test
	public final void decryptBase64() throws NullPointerException,EncryptionException{
		assertEquals(TestConstants.testString, new SymmetricEncryption(SymmetricType.DES, config_security_key_des_default).decryptBase64(
				"LdCGo0dplVBHZP+lIOybBPy+v57iXIRX",
				CharsetType.UTF8));
	}

	/**
	 * Encryp base641.
	 * 
	 * @throws EncryptionException
	 * @throws NullPointerException
	 */
	@Test
	public final void encrypBase641() throws NullPointerException,EncryptionException{
		assertEquals(
				"LdCGo0dplVBHZP+lIOybBPy+v57iXIRX",
				new SymmetricEncryption(SymmetricType.DES, config_security_key_des_default).encryptBase64(
						TestConstants.testString,
						CharsetType.UTF8));
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
		SymmetricType des = SymmetricType.DES;
		SymmetricEncryption dESUtil = new SymmetricEncryption(des, config_security_key_des_hex);
		// LdCGo0dplVASWwJrvlHqpw==
		log.debug(dESUtil.encryptBase64(TestConstants.testString, CharsetType.UTF8));
	}

	/**
	 * Encrypt hex222.
	 * 
	 * @throws EncryptionException
	 */
	@Test
	// @Ignore
	public final void encryptHex222() throws EncryptionException{
		//		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.DES, "olaolaol");
		//		log.debug(symmetricEncryption
		//				.decryptHex(
		//						"2f9ad12c10a4e1dcb1dafa4177cfb7d71119e62033430ed5d90eea70097e0f6b4fc61d15f6c150b110f328197b8828b7b485ccced13eb58f1b445db54fa033cdb9df6ac21f1d8507",
		//						CharsetType.UTF8));
		//		log.debug(new SymmetricEncryption(SymmetricType.DES, "jiyoutua").encryptHex("金总，好喜欢你哟", CharsetType.UTF8));
		log.debug(new SymmetricEncryption(SymmetricType.DES, "jiyoutua", CipherMode.ECB, CipherPadding.NoPadding).encryptHex(
				"金总，好喜欢你哟",
				CharsetType.UTF8));
		//		log.debug(new SymmetricEncryption(SymmetricType.DES, "A1B2C3D4E5F60708").encryptHex("amigoxie", CharsetType.ISO_8859_1));
		//		log.debug(symmetricEncryption.decryptHex("9F4FB63737EAFD60EEA0B8FBD546C9752A0D1621A348341F0B6D3AC2E6672EF3", CharsetType.UTF8));

	}

	/**
	 * Encrypt hex.
	 * 
	 * @throws EncryptionException
	 */
	@Test
	public void encryptHex() throws EncryptionException{
		String keyString = "jinxin";
		SymmetricType symmetricType = SymmetricType.DES;
		//		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, keyString);
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(
				symmetricType,
				keyString,
				CipherMode.ECB,
				CipherPadding.ISO10126Padding);

		String miString = symmetricEncryption.encryptHex(TestConstants.testString, CharsetType.UTF8);

		String mingString = symmetricEncryption.decryptHex(miString, CharsetType.UTF8);
		
		Assert.assertEquals(mingString, TestConstants.testString);
	}
}
