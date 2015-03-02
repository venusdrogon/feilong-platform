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

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.crypto.KeyGenerator;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.test.TestConstants;
import com.feilong.tools.security.BaseSecurityTest;
import com.feilong.tools.security.EncryptionException;

/**
 * The Class SymmetricEncryptionTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-26 上午10:50:59
 */
@SuppressWarnings("all")
public class SymmetricEncryptionTest extends BaseSecurityTest{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(SymmetricEncryptionTest.class);

	/** The symmetric encryption. */
	private SymmetricEncryption	symmetricEncryption;

	/** The original. */
	private String				original	= TestConstants.testString;

	/** The key string. */
	private String				keyString	= "feilong";

	/**
	 * Base64 string.
	 * 
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void base64String() throws NullPointerException,EncryptionException{
		log.info("SymmetricType.AES:{}", new SymmetricEncryption(SymmetricType.AES, keyString).encryptBase64(original, CharsetType.UTF8));
		log.info(
				"SymmetricType.ARCFOUR:{}",
				new SymmetricEncryption(SymmetricType.ARCFOUR, keyString).encryptBase64(original, CharsetType.UTF8));
		log.info(
				"SymmetricType.Blowfish:{}",
				new SymmetricEncryption(SymmetricType.Blowfish, keyString).encryptBase64(original, CharsetType.UTF8));
		log.info("SymmetricType.DES:{}", new SymmetricEncryption(SymmetricType.DES, keyString).encryptBase64(original, CharsetType.UTF8));
		log.info(
				"SymmetricType.DESede:{}",
				new SymmetricEncryption(SymmetricType.DESede, keyString).encryptBase64(original, CharsetType.UTF8));

	}

	/**
	 * Des3.
	 * 
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void des3() throws NullPointerException,EncryptionException{
		String original = "F7468B69D12BB6CE76D6206419A6AC28";
		String keyString = "12345678901234561234567890123456";

		SymmetricType symmetricType = SymmetricType.DESede;
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, keyString);
		String encryptToHexString = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info(encryptToHexString.length() + "");
		log.info("BF81501C562D6FEA2FCB905D392D5851".length() + "");
		Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", encryptToHexString);
	}

	/**
	 * Decrypt base64 string.
	 * 
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void decryptBase64String() throws NullPointerException, EncryptionException {
		log.info("SymmetricType.AES:{}", new SymmetricEncryption(SymmetricType.AES, keyString).decryptBase64(
				"NvHLVz3ADOlx3K2dMa8TZjjP5fkAPus2ienTEkOdUX4=",
				CharsetType.UTF8));
	}

	/**
	 * Decrypt hex string.
	 * 
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void decryptHexString() throws EncryptionException{
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		log.info(symmetricEncryption.decryptHex(hexString, CharsetType.UTF8));
	}

	/**
	 * Name.
	 * 
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	@Test
	public void name() throws NoSuchAlgorithmException{
		/**
		 * 获取JAVA中有多少种加密方式 RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC<br>
		 * SunJCE Provider (implements RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC)
		 */
		// java.security.NoSuchAlgorithmException: RSA KeyGenerator not available
		KeyGenerator keyGenerator = KeyGenerator.getInstance("RSA");
		// kg.init(561);
		Provider provider = keyGenerator.getProvider();
		log.info(provider.getInfo());
	}
}
