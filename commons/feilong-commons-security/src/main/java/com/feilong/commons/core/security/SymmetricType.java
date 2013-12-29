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

/**
 * 对称加密的类型.<br>
 * where key is feilong,and string is 鑫哥爱feilong,encryptToBase64String
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-24 下午11:36:22
 */
public enum SymmetricType{
	/**
	 * LdCGo0dplVASWwJrvlHqpw==<br>
	 * key size must be equal to 56<br>
	 * 最常用的对称加密算法，安全性较差,<br>
	 * The Digital Encryption Standard as described in FIPS PUB 46-2.
	 */
	DES,

	/**
	 * sIVcl7DB9hzAsiGKGFVJ2g==<br>
	 * key size must be equal to 112 or 168.<br>
	 * Triple DES Encryption (DES-EDE),<br>
	 * 针对DES安全性的改进产生了能满足当前安全需要的TripleDES算法
	 */
	DESede,

	/**
	 * MKNbK/ieTaepCk8SefgPMw==<br>
	 * Advanced Encryption Standard as specified by NIST in a draft FIPS<br>
	 * Based on the Rijndael algorithm by Joan Daemen and Vincent Rijmen<br>
	 * AES is a 128-bit block cipher supporting keys of 128, 192, and 256 bits<br>
	 * 是一种替代DES算法的新算法，可提供很好的安全性
	 */
	AES,

	/**
	 * BVl2k0U5+qrX8Otcg/4NXQ==<br>
	 * The block cipher designed by Bruce Schneier,key size must be multiple of 8, and can only range from 32 to 448 (inclusive)<br>
	 * 密钥长度可达448位
	 */
	Blowfish,

	/**
	 * CyJ22S/ct5YAhv5wMCTFZQ==<br>
	 * key size must be between 40 and 1024 bits
	 */
	RC2,

	/**
	 * Jo5UARgjNRbDaL0VW77a<br>
	 * s key size must be between 40 and 1024 bits
	 */
	RC4,

	/** R1qRmIN8s4VY7OTRspIA */
	ARCFOUR,

	// java.security.NoSuchAlgorithmException: RSA KeyGenerator not available
	// java.security.InvalidKeyException: No installed provider supports this key: (null)
	// RSA,

	// java.security.NoSuchAlgorithmException: Cannot find any provider supporting RC5
	// RC5,

	// java.security.NoSuchAlgorithmException: Cannot find any provider supporting Serpent
	// Serpent,

	// Cannot find any provider supporting Twofish
	// Twofish,

	// PBEWithMD5AndDES KeyGenerator not available
	// PBEWithMD5AndDES,

	// ******************************************************************************************

	// Cannot find any provider supporting PBE
	// PBE,

	// Cannot find any provider supporting HMAC
	// HMAC,

	// Cannot find any provider supporting HmacMD5
	// HmacMD5,

	// Cannot find any provider supporting HmacSHA1
	// HmacSHA1
}
