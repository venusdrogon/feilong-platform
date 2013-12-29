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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.TestConstants;

@ContextConfiguration(locations = { "classpath:feilong-security.xml" })
public class SymmetricEncryptionSpringTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(SymmetricEncryptionSpringTest.class);

	@Autowired(required = false)
	private SymmetricEncryption	symmetricEncryption;

	@Test
	public void base64String() throws SecurityException{
		String original = TestConstants.testString;
		String base64 = symmetricEncryption.encryptToBase64String(original);
		log.info(base64);
	}

	@Test
	public void decryptBase64String(){
		String hexString = "BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2";
		// log.info(blowfishUtil.encryptToHexString(original));
		// String keyString = blowfishUtil.getKeyString();
		// log.info(keyString);
		log.info(symmetricEncryption.decryptBase64String(hexString));
		// 3B37B7F90CBBD4EFD5502F50F9B407E3
		// 3B37B7F90CBBD4EFD5502F50F9B407E3
		// /x3JicoLOTnZO+Zs3Ha5pg==
		// /x3JicoLOTnZO+Zs3Ha5pg==
	}

	@Test
	public void encryptToHexString() throws SecurityException,NoSuchMethodException{
		String original = TestConstants.testString;
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
		 * 获取JAVA中有多少种加密方式 RSA, DES, Triple DES, AES, Blowfish, ARCFOUR, RC2, PBE, Diffie-Hellman, HMAC
		 */
		KeyGenerator kg = KeyGenerator.getInstance("Blowfish");
		// kg.init(561);
		Provider provider = kg.getProvider();
		System.out.println(provider.getInfo());
	}
}
