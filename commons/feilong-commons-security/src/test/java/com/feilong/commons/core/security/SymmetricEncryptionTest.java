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
package com.feilong.commons.core.security;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.crypto.KeyGenerator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.TestConstants;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-26 上午10:50:59
 */
public class SymmetricEncryptionTest{

	private static final Logger	log			= LoggerFactory.getLogger(SymmetricEncryptionTest.class);

	private SymmetricEncryption	symmetricEncryption;

	private String				original	= TestConstants.testString;

	private String				keyString	= "feilong";

	@Test
	public void base64String() throws SecurityException,NoSuchMethodException{

		log.info("SymmetricType.AES:{}", new SymmetricEncryption(SymmetricType.AES, keyString).encryptToBase64String(original));
		log.info("SymmetricType.ARCFOUR:{}", new SymmetricEncryption(SymmetricType.ARCFOUR, keyString).encryptToBase64String(original));
		log.info("SymmetricType.Blowfish:{}", new SymmetricEncryption(SymmetricType.Blowfish, keyString).encryptToBase64String(original));
		log.info("SymmetricType.DES:{}", new SymmetricEncryption(SymmetricType.DES, keyString).encryptToBase64String(original));
		log.info("SymmetricType.DESede:{}", new SymmetricEncryption(SymmetricType.DESede, keyString).encryptToBase64String(original));

	}

	@Test
	public void base64String1() throws SecurityException,NoSuchMethodException{
		String original = "鑫哥爱feilong";
		String keyString = "feilong";
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		log.info("SymmetricType.Blowfish:{}", symmetricEncryption.encryptToHexString(original));

		// 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6

	}

	@Test
	public void base64String221() throws SecurityException,NoSuchMethodException{
		String keyString = "feilong";
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		log.info(symmetricEncryption.decryptHexString(hexString));
		//

	}

	@Test
	public void decryptBase64String(){
		log.info(
				"SymmetricType.AES:{}",
				new SymmetricEncryption(SymmetricType.AES, keyString).decryptBase64String("NvHLVz3ADOlx3K2dMa8TZjjP5fkAPus2ienTEkOdUX4="));
	}

	@Test
	public void encryptToHexString() throws SecurityException,NoSuchMethodException{
		String original = TestConstants.testString;

		symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String base64 = symmetricEncryption.encryptToHexString(original);
		log.info(base64);
	}

	@Test
	public void decryptHexString() throws SecurityException,NoSuchMethodException{
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		log.info(symmetricEncryption.decryptHexString(hexString));
	}

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
		System.out.println(provider.getInfo());
	}
}
