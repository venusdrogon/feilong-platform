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
package com.feilong.tools.security.oneway;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.tools.security.EncryptionException;

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
 * @version 1.0.7 2014-7-10 14:28 update javadoc and remove extends
 * @see OnewayEncryption
 * @see OnewayType
 * @see org.apache.commons.codec.digest.Sha2Crypt
 * @since 1.0.0
 */
public final class SHA1Util{

	/**
	 * Instantiates a new SH a1 util.
	 */
	private SHA1Util(){}

	/** The oneway type. */
	private static OnewayType	onewayType	= OnewayType.SHA1;

	/**
	 * 使用算法 单向加密字符串.
	 * 
	 * @param origin
	 *            原始字符串,将使用默认的 {@link String#getBytes()} 转成字节数组<br>
	 * @return 加密之后的转成小写的16进制字符串
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see OnewayEncryption#encode(OnewayType, String)
	 */
	public static String encode(String origin) throws EncryptionException{
		return OnewayEncryption.encode(onewayType, origin);
	}

	/**
	 * 使用算法 单向加密字符串.
	 * 
	 * @param origin
	 *            原始字符串,将使用默认的 value.getBytes() 转成字节数组<br>
	 *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(String, String)}
	 * @param charsetName
	 *            受支持的 {@link CharsetType} 名称,比如 utf-8
	 * @return 加密之后的转成小写的16进制字符串
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see CharsetType
	 * @see OnewayEncryption#encode(OnewayType, String, String)
	 */
	public static String encode(String origin,String charsetName) throws EncryptionException{
		return OnewayEncryption.encode(onewayType, origin, charsetName);
	}

	/**
	 * 计算文件的单向加密值.
	 * 
	 * @param filePath
	 *            文件路径 {@link java.io.File#File(String)}
	 * @return the string
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see OnewayEncryption#encodeFile(OnewayType, String)
	 */
	public static String encodeFile(String filePath) throws EncryptionException{
		return OnewayEncryption.encodeFile(onewayType, filePath);
	}
}