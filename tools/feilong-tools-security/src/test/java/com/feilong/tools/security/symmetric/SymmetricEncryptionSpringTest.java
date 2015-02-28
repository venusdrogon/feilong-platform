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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.test.TestConstants;
import com.feilong.tools.security.EncryptionException;

/**
 * The Class SymmetricEncryptionSpringTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-7-2 18:06:19
 */
@ContextConfiguration(locations = { "classpath:feilong-security.xml" })
@SuppressWarnings("all")
public class SymmetricEncryptionSpringTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(SymmetricEncryptionSpringTest.class);

	/** The symmetric encryption. */
	@Autowired(required = false)
	private SymmetricEncryption	symmetricEncryption;

	/**
	 * Base64 string.
	 * 
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void base64String() throws EncryptionException{
		String original = TestConstants.testString;
		String base64 = symmetricEncryption.encryptBase64(original, CharsetType.UTF8);
		log.info(base64);
	}

	/**
	 * Decrypt base64 string.
	 * 
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void decryptBase64String() throws EncryptionException{
		String hexString = "BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2";
		// log.info(blowfishUtil.encryptToHexString(original));
		// String keyString = blowfishUtil.getKeyString();
		// log.info(keyString);
		log.info(symmetricEncryption.decryptBase64(hexString, CharsetType.UTF8));
		// 3B37B7F90CBBD4EFD5502F50F9B407E3
		// 3B37B7F90CBBD4EFD5502F50F9B407E3
		// /x3JicoLOTnZO+Zs3Ha5pg==
		// /x3JicoLOTnZO+Zs3Ha5pg==
	}

	/**
	 * Encrypt to hex string.
	 * 
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void encryptToHexString() throws SecurityException,NoSuchMethodException,EncryptionException{
		String original = TestConstants.testString;
		String base64 = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info(base64);
	}

	/**
	 * Decrypt hex string.
	 * 
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws EncryptionException
	 *             the encryption exception
	 */
	@Test
	public void decryptHexString() throws SecurityException,NoSuchMethodException,EncryptionException{
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
		 * 获取JAVA中有多少种加密方式 RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC
		 */
		KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
		// kg.init(561);
		Provider provider = kg.getProvider();
		log.info(provider.getInfo());
	}
}
