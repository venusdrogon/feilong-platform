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
package com.feilong.commons.core.security.symmetric;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

import javax.crypto.KeyGenerator;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.TestConstants;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.security.BaseSecurityTest;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-26 上午10:50:59
 */
public class SymmetricEncryptionTest extends BaseSecurityTest{

	private static final Logger	log			= LoggerFactory.getLogger(SymmetricEncryptionTest.class);

	private SymmetricEncryption	symmetricEncryption;

	private String				original	= TestConstants.testString;

	private String				keyString	= "feilong";

	@Test
	public void base64String() throws SecurityException,NoSuchMethodException{

		log.info(
				"SymmetricType.AES:{}",
				debugSecurityValue(new SymmetricEncryption(SymmetricType.AES, keyString).encrypBase64(original, CharsetType.UTF8)));
		log.info(
				"SymmetricType.ARCFOUR:{}",
				debugSecurityValue(new SymmetricEncryption(SymmetricType.ARCFOUR, keyString).encrypBase64(original, CharsetType.UTF8)));
		log.info(
				"SymmetricType.Blowfish:{}",
				debugSecurityValue(new SymmetricEncryption(SymmetricType.Blowfish, keyString).encrypBase64(original, CharsetType.UTF8)));
		log.info(
				"SymmetricType.DES:{}",
				debugSecurityValue(new SymmetricEncryption(SymmetricType.DES, keyString).encrypBase64(original, CharsetType.UTF8)));
		log.info(
				"SymmetricType.DESede:{}",
				debugSecurityValue(new SymmetricEncryption(SymmetricType.DESede, keyString).encrypBase64(original, CharsetType.UTF8)));

	}

	@Test
	public void blowfish() throws SecurityException,NoSuchMethodException{
		String original = "鑫哥爱feilong";
		String keyString = "feilong";
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		log.info("SymmetricType.Blowfish:{}", symmetricEncryption.encryptHex(original, CharsetType.UTF8));
		// 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	}

	@Test
	public void des3() throws SecurityException{
		String original = "F7468B69D12BB6CE76D6206419A6AC28";
		String keyString = "12345678901234561234567890123456";

		SymmetricType symmetricType = SymmetricType.DESede;
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, keyString);
		String encryptToHexString = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info(encryptToHexString.length() + "");
		log.info("BF81501C562D6FEA2FCB905D392D5851".length() + "");
		Assert.assertEquals("BF81501C562D6FEA2FCB905D392D5851", encryptToHexString);
	}

	@Test
	public void base64String221() throws SecurityException,NoSuchMethodException{
		String keyString = "feilong";
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		log.info(symmetricEncryption.decryptHex(hexString, CharsetType.UTF8));
	}

	@Test
	public void decryptBase64String(){
		log.info("SymmetricType.AES:{}", new SymmetricEncryption(SymmetricType.AES, keyString).decryptBase64(
				"NvHLVz3ADOlx3K2dMa8TZjjP5fkAPus2ienTEkOdUX4=",
				CharsetType.UTF8));
	}

	@Test
	public void encryptToHexString() throws SecurityException,NoSuchMethodException{
		String original = TestConstants.testString;

		symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
		String base64 = symmetricEncryption.encryptHex(original, CharsetType.UTF8);
		log.info(base64);
	}

	@Test
	public void decryptHexString() throws SecurityException,NoSuchMethodException{
		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
		log.info(symmetricEncryption.decryptHex(hexString, CharsetType.UTF8));
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
		log.info(provider.getInfo());
	}
}
