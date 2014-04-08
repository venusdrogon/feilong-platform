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
	DES("DES","DES"),

	/**
	 * sIVcl7DB9hzAsiGKGFVJ2g==<br>
	 * key size must be equal to 112 or 168.<br>
	 * Triple DES Encryption (DES-EDE),<br>
	 * 针对DES安全性的改进产生了能满足当前安全需要的TripleDES算法<br>
	 * 等于 TripleDES<br>
	 * <br>
	 * 3DES <br>
	 * 3DES（或称为Triple DES）是三重数据加密算法（TDEA，Triple Data Encryption
	 * Algorithm）块密码的通称。它相当于是对每个数据块应用三次DES加密算法。由于计算机运算能力的增强，原版DES密码的密钥长度变得容易被暴力破解；3D
	 * ES即是设计用来提供一种相对简单的方法，即通过增加DES的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法。
	 */
	// DESede/ECB/NoPadding
	DESede("DESede","DESede/ECB/NoPadding"),

	/**
	 * The Triple des.
	 * 
	 * @deprecated please use {@link #DESede}
	 */
	TripleDES("TripleDES","TripleDES"),

	/**
	 * MKNbK/ieTaepCk8SefgPMw==<br>
	 * Advanced Encryption Standard as specified by NIST in a draft FIPS<br>
	 * Based on the Rijndael algorithm by Joan Daemen and Vincent Rijmen<br>
	 * AES is a 128-bit block cipher supporting keys of 128, 192, and 256 bits<br>
	 * 是一种替代DES算法的新算法，可提供很好的安全性.
	 */
	AES("AES","AES"),

	/**
	 * BVl2k0U5+qrX8Otcg/4NXQ==<br>
	 * The block cipher designed by Bruce Schneier,key size must be multiple of 8, and can only range from 32 to 448 (inclusive)<br>
	 * 密钥长度可达448位.
	 */
	Blowfish("Blowfish","Blowfish"),

	/**
	 * CyJ22S/ct5YAhv5wMCTFZQ==<br>
	 * key size must be between 40 and 1024 bits.
	 */
	RC2("RC2","RC2"),

	/**
	 * Jo5UARgjNRbDaL0VW77a<br>
	 * s key size must be between 40 and 1024 bits.
	 */
	RC4("RC4","RC4"),

	/** R1qRmIN8s4VY7OTRspIA. */
	ARCFOUR("ARCFOUR","ARCFOUR");

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

	// ****************************************************************************

	/** 算法. */
	private String	algorithm;

	/**
	 * 转换的名称，例如 DES/CBC/PKCS5Padding。<br>
	 * 有关标准转换名称的信息，请参见 Java Cryptography Architecture Reference Guide 的附录 A.
	 */
	private String	transformation;

	/**
	 * Instantiates a new symmetric type.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @param transformation
	 *            the transformation
	 */
	private SymmetricType(String algorithm, String transformation){
		this.algorithm = algorithm;
		this.transformation = transformation;
	}

	/**
	 * Gets the 算法.
	 * 
	 * @return the algorithm
	 */
	public String getAlgorithm(){
		return algorithm;
	}

	/**
	 * Sets the 算法.
	 * 
	 * @param algorithm
	 *            the algorithm to set
	 */
	public void setAlgorithm(String algorithm){
		this.algorithm = algorithm;
	}

	/**
	 * Gets the 转换的名称，例如 DES/CBC/PKCS5Padding。<br>
	 * 有关标准转换名称的信息，请参见 Java Cryptography Architecture Reference Guide 的附录 A.
	 * 
	 * @return the transformation
	 */
	public String getTransformation(){
		return transformation;
	}

	/**
	 * Sets the 转换的名称，例如 DES/CBC/PKCS5Padding。<br>
	 * 有关标准转换名称的信息，请参见 Java Cryptography Architecture Reference Guide 的附录 A.
	 * 
	 * @param transformation
	 *            the transformation to set
	 */
	public void setTransformation(String transformation){
		this.transformation = transformation;
	}
}
