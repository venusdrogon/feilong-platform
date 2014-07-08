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
package com.feilong.commons.security.oneway;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.ByteUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.security.EncryptionException;

/**
 * 单向加密算法
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年7月6日 上午12:11:03
 * @since 1.0.7
 */
public interface OnewayEncryption{

	/**
	 * 使用算法 单向加密字符串.
	 * 
	 * @param onewayType
	 *            单向加密
	 * @param origin
	 *            原始字符串,将使用默认的 {@link String#getBytes()} 转成字节数组<br>
	 *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(OnewayType, byte[])}
	 * @return 加密之后的转成小写的16进制字符串
	 * @throws NullPointerException
	 *             if Validator.isNullOrEmpty(onewayType) or Validator.isNullOrEmpty(algorithm)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see StringUtil#toBytes(String)
	 * @see #encode(OnewayType, byte[])
	 */
	String encode(String origin) throws EncryptionException;

	/**
	 * 使用算法 单向加密字符串.
	 * 
	 * @param onewayType
	 *            单向加密
	 * @param origin
	 *            原始字符串,将使用默认的 value.getBytes() 转成字节数组<br>
	 *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(OnewayType, byte[])}
	 * @param charsetName
	 *            受支持的 {@link CharsetType} 名称,比如 utf-8
	 * @return 加密之后的转成小写的16进制字符串
	 * @throws NullPointerException
	 *             if Validator.isNullOrEmpty(onewayType) or Validator.isNullOrEmpty(algorithm)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see CharsetType
	 * @see StringUtil#toBytes(String, String)
	 * @see #encode(OnewayType, byte[])
	 */
	String encode(String origin,String charsetName) throws EncryptionException;

	/**
	 * md5加密,byte[] 便于自定义编码,返回的值是小写的16进制字符串
	 * 
	 * <pre>
	 * 
	 * // 签名(utf-8编码)
	 * byte[] bytes = StringUtil.toBytes(sb.toString(), CharsetType.UTF8);
	 * return MD5Util.encode(bytes).toUpperCase();
	 * </pre>
	 * 
	 * @param onewayType
	 *            the oneway type
	 * @param inputBytes
	 *            the input bytes
	 * @return 加密之后的转成小写的16进制字符串
	 * @throws NullPointerException
	 *             if Validator.isNullOrEmpty(onewayType) or Validator.isNullOrEmpty(algorithm)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see #getMessageDigest(OnewayType)
	 * @see java.security.MessageDigest#digest(byte[])
	 * @see ByteUtil#bytesToHexStringLowerCase(byte[])
	 */
	String encode(byte[] inputBytes) throws EncryptionException;

	/**
	 * 计算文件的MD5值.
	 * 
	 * @param onewayType
	 *            the oneway type
	 * @param filePath
	 *            文件路径 {@link java.io.File#File(String)}
	 * @return the string
	 * @throws IllegalArgumentException
	 *             !file.exists() or !file.isFile()
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see java.io.File#File(String)
	 * @see #getMessageDigest(OnewayType)
	 * @see java.io.FileInputStream#read(byte[], int, int)
	 * @see java.security.MessageDigest#update(byte[], int, int)
	 * @see java.security.MessageDigest#digest()
	 */
	String encodeFile(String filePath) throws EncryptionException;
}
