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
package com.feilong.commons.core.security.symmetric;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.TestConstants;
import com.feilong.commons.core.enumeration.CharsetType;

/**
 * The Class BlowfishTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月5日 下午3:55:06
 * @since 1.0.7
 */
public class BlowfishTest implements TestConstants{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BlowfishTest.class);

	/** The symmetric encryption. */
	private SymmetricEncryption	symmetricEncryption;

	/**
	 * Blowfish.
	 * 
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	@Test
	public void blowfishEncryptHex() throws SecurityException,NoSuchMethodException,NullPointerException,NoSuchAlgorithmException{
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String encryptHex = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info("SymmetricType.Blowfish:{}", encryptHex);
		assertEquals("055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6", encryptHex);
		// 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	}

	/**
	 * Base64 string221.
	 * 
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	@Test
	public void blowfishDecryptHex() throws SecurityException,NoSuchMethodException,NullPointerException,NoSuchAlgorithmException{
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		String decryptHex = symmetricEncryption.decryptHex(hexString, CharsetType.UTF8);
		log.info(decryptHex);
	}

	/**
	 * Encrypt to hex string.
	 * 
	 * @throws SecurityException
	 *             the security exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 * @throws NullPointerException
	 *             the null pointer exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	@Test
	public void encryptToHexString() throws SecurityException,NoSuchMethodException,NullPointerException,NoSuchAlgorithmException{
		String original = TestConstants.testString;

		symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String base64 = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info(base64);
	}
}
