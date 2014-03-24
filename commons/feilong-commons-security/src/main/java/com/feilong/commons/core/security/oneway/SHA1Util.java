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
package com.feilong.commons.core.security.oneway;

/**
 * Secure Hash Algorithm，安全散列算法 （单向加密）
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
 * <pre>
 * 检验你的实现是否正确：
 * SHA1Util.encode(&quot;你好&quot;) = 440ee0853ad1e99f962b63e459ef992d7c211722
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2010年10月26日 17:13:58
 * @version 1.0.1 2011-10-18 16:49
 * @version 1.0.2 2013-7-18 17:01 修改了javadoc 和类关系
 * @since 1.0
 */
public final class SHA1Util{

	/** The oneway type. */
	private static OnewayType	onewayType	= OnewayType.SHA1;

	/**
	 * private.
	 */
	private SHA1Util(){}

	/**
	 * 加密字符串.
	 * 
	 * @param origin
	 *            原始字符串
	 * @return 加密之后的转成小写的16进制字符串
	 */
	public static String encode(String origin){
		return OnewayEncryption.encode(onewayType, origin);
	}

	/**
	 * 加密字符串.
	 * 
	 * @param origin
	 *            原始字符串
	 * @param charsetName
	 *            受支持的 charset 名称,比如 utf-8
	 * @return 加密之后的转成小写的16进制字符串
	 */

	public static String encode(String origin,String charsetName){
		return OnewayEncryption.encode(onewayType, origin, charsetName);
	}

	/**
	 * 加密,byte[] 便于自定义编码.
	 * 
	 * @param inputBytes
	 *            the input bytes
	 * @return 加密之后的转成小写的16进制字符串
	 */
	public static String encode(byte[] inputBytes){
		return OnewayEncryption.encode(onewayType, inputBytes);
	}

	/**
	 * 计算文件 algorithm值.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the string
	 */
	public static String encodeFile(String filePath){
		return OnewayEncryption.encodeFile(onewayType, filePath);
	}
}