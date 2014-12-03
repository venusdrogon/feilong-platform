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
package com.feilong.commons.security.symmetric;

/**
 * 对称加密的类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-24 下午11:36:22
 * @see <a href="http://tripledes.online-domain-tools.com/">加解密在线测试网站</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/crypto/CryptoSpec.html#AppA">JCA Reference Guide</a>
 * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html">JCA Standard Algorithm Name
 *      Documentation</a>
 */
public enum SymmetricType{
	/**
	 * Data Encryption Standard，即数据加密算法<br>
	 * 它是IBM公司于1975年研究成功并公开发表的<br>
	 * <p>
	 * DES算法把64位的明文输入块变为64位的密文输出块，它所使用的密钥也是64位<br>
	 * DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	 * </p>
	 * LdCGo0dplVASWwJrvlHqpw==<br>
	 * key size must be equal to 56<br>
	 * 最常用的对称加密算法，安全性较差,<br>
	 * The Digital Encryption Standard as described in FIPS PUB 46-2.
	 */
	DES("DES"),

	/**
	 * sIVcl7DB9hzAsiGKGFVJ2g==<br>
	 * key size must be equal to 112 or 168.<br>
	 * Triple DES Encryption (DES-EDE),<br>
	 * 针对DES安全性的改进产生了能满足当前安全需要的TripleDES算法<br>
	 * 等于 TripleDES<br>
	 * <br>
	 * 3DES（或称为Triple DES）是三重数据加密算法（TDEA，Triple Data Encryption Algorithm）块密码的通称。<br>
	 * 它相当于是对每个数据块应用三次DES加密算法。由于计算机运算能力的增强，原版DES密码的密钥长度变得容易被暴力破解；<br>
	 * 3DES即是设计用来提供一种相对简单的方法，即通过增加DES的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法。
	 * 
	 * @see <a href="http://tripledes.online-domain-tools.com/">加解密在线测试网站</a>
	 */
	DESede("DESede"),

	/**
	 * The Triple des.
	 * 
	 * @deprecated please use {@link #DESede}
	 */
	@SuppressWarnings("dep-ann")
	TripleDES("TripleDES"),

	/**
	 * MKNbK/ieTaepCk8SefgPMw==<br>
	 * Advanced Encryption Standard as specified by NIST in a draft FIPS<br>
	 * Based on the Rijndael algorithm by Joan Daemen and Vincent Rijmen<br>
	 * AES is a 128-bit block cipher supporting keys of 128, 192, and 256 bits<br>
	 * 是一种替代DES算法的新算法，可提供很好的安全性.
	 */
	AES("AES"),

	/**
	 * BVl2k0U5+qrX8Otcg/4NXQ==<br>
	 * The block cipher designed by Bruce Schneier,key size must be multiple of 8, and can only range from 32 to 448 (inclusive)<br>
	 * 密钥长度可达448位.
	 */
	Blowfish("Blowfish"),

	/**
	 * CyJ22S/ct5YAhv5wMCTFZQ==<br>
	 * key size must be between 40 and 1024 bits.
	 */
	RC2("RC2"),

	/**
	 * Jo5UARgjNRbDaL0VW77a<br>
	 * s key size must be between 40 and 1024 bits.
	 */
	RC4("RC4"),

	/** R1qRmIN8s4VY7OTRspIA. */
	ARCFOUR("ARCFOUR");

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
	 * Instantiates a new symmetric type.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @param transformation
	 *            the transformation
	 */
	private SymmetricType(String algorithm){
		this.algorithm = algorithm;
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
}