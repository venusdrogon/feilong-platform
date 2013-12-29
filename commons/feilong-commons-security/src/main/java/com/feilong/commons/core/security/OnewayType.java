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
 * 单向加密类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2012-3-25 上午7:19:44
 * @version 1.0.2 2013-1-14 20:27 增加 SHA-1、 SHA-256、SHA-384，和SHA-512
 */
public enum OnewayType{

	/**
	 * Message Digest algorithm 5，信息摘要算法<br>
	 * 将任意长度的"字节串"变换成一个128bit的大整数.
	 */
	MD5("MD5"),

	// *****SHA家族的五个算法，分别是SHA-1、SHA-224(java 不支持)、SHA-256、SHA-384，和SHA-512
	/**
	 * 值和 SHA1 其实是一样的
	 */
	SHA("SHA"),
	/**
	 * Secure Hash Algorithm，安全散列算法
	 * 
	 * <pre>
	 * SHA-1与MD5的比较
	 * 因为二者均由MD4导出，SHA-1和MD5彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：
	 * l 对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32 位。
	 * 		使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对MD5是2^128数量级的操作，而对SHA-1则是2^160数量级的操作。这样，SHA-1对强行攻击有更大的强度。
	 * 
	 * l 对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。
	 * 
	 * l 速度：在相同的硬件上，SHA-1的运行速度比MD5慢。
	 * </pre>
	 * 
	 * .
	 */
	// 在1993年，安全散列算法（SHA）由美国国家标准和技术协会（NIST)提出，并作为联邦信息处理标准（FIPS PUB 180）公布；
	// 1995年又发布了一个修订版FIPS PUB 180-1，通常称之为SHA-1。
	// SHA-1是基于MD4算法的，并且它的设计在很大程度上是模仿MD4的。现在已成为公认的最安全的散列算法之一，并被广泛使用。

	SHA1("SHA-1"),

	// java.lang.IllegalArgumentException: No such algorithm [SHA-2]
	// SHA2("SHA-2"),

	// No such algorithm [SHA-224] java不支持 java.lang.IllegalArgumentException:
	// SHA224("SHA-224"),

	/** The SH a256. */
	SHA256("SHA-256"),

	/** The SH a384. */
	SHA384("SHA-384"),

	/** The SH a512. */
	SHA512("SHA-512");

	// ****************************************************************************

	/** 算法. */
	private String	algorithm;

	/**
	 * Instantiates a new oneway type.
	 * 
	 * @param algorithm
	 *            the algorithm
	 */
	private OnewayType(String algorithm){
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