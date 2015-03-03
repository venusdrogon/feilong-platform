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

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.util.ByteUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.security.EncryptionException;

/**
 * 单向加密算法.
 * 
 * <h3>提供以下核心方法:</h3>
 * 
 * <blockquote>
 * <ul>
 * <li>{@link #encode(OnewayType, String)}</li>
 * <li>{@link #encode(OnewayType, String, String)}</li>
 * <li>{@link #encode(OnewayType, byte[])}</li>
 * <li>{@link #encodeFile(OnewayType, String)}</li>
 * </ul>
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2012-3-25 上午7:19:18
 * @version 1.0.7 2014-7-10 14:30 update class type is final
 * @see OnewayType
 */
//无访问控制符修饰的内容可以被同一个包中的类访问，
final class OnewayEncryption{

	private static final Logger	log	= LoggerFactory.getLogger(OnewayEncryption.class);

	/**
	 * Instantiates a new oneway encryption.
	 */
	private OnewayEncryption(){
	}

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
	public static String encode(OnewayType onewayType,String origin) throws NullPointerException,EncryptionException{
		byte[] inputBytes = StringUtil.toBytes(origin);
		return encode(onewayType, inputBytes);
	}

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
	public static String encode(OnewayType onewayType,String origin,String charsetName) throws NullPointerException,EncryptionException{
		byte[] inputBytes = StringUtil.toBytes(origin, charsetName);
		return encode(onewayType, inputBytes);
	}

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
	public static String encode(OnewayType onewayType,byte[] inputBytes) throws NullPointerException,EncryptionException{
		MessageDigest messageDigest = getMessageDigest(onewayType);

		// 对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
		// 使用指定的 byte 数组对摘要进行最后更新，然后完成摘要计算。
		// 也就是说，此方法首先调用 update(input)，向 update 方法传递 input 数组，然后调用 digest()。

		// 和自己先写 update(input) ，再 调用 digest（）效果一样的
		byte[] bs = messageDigest.digest(inputBytes);

		return ByteUtil.bytesToHexStringLowerCase(bs);
	}

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
	public static String encodeFile(OnewayType onewayType,String filePath) throws IllegalArgumentException,EncryptionException{
		File file = new File(filePath);

		if (!file.exists()){
			throw new IllegalArgumentException("file:" + filePath + " don't exists!");
		}

		if (!file.isFile()){
			throw new IllegalArgumentException(filePath + " is not a File!");
		}

		try{
			MessageDigest messageDigest = getMessageDigest(onewayType);
			FileInputStream fileInputStream = new FileInputStream(file);
			byte buffer[] = new byte[1024];
			int len;
			while ((len = fileInputStream.read(buffer, 0, 1024)) != -1){
				messageDigest.update(buffer, 0, len);
			}
			fileInputStream.close();
			byte[] bytes = messageDigest.digest();
			// 该数的正负号（-1 表示负，0 表示零，1 表示正）
			BigInteger bigInt = new BigInteger(1, bytes);
			return bigInt.toString(16);
		}catch (Exception e){
			log.error(e.getClass().getName(), e);
			throw new EncryptionException(e);
		}
	}

	// ***********************************************************************************************

	/**
	 * 使用 onewayType 创建MessageDigest对象.
	 * 
	 * @param onewayType
	 *            the oneway type
	 * @return {@link java.security.MessageDigest#getInstance(String)}
	 * @throws NullPointerException
	 *             if Validator.isNullOrEmpty(onewayType) or Validator.isNullOrEmpty(algorithm)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see java.security.MessageDigest#getInstance(String)
	 */
	private static final MessageDigest getMessageDigest(OnewayType onewayType) throws NullPointerException,EncryptionException{
		if (Validator.isNullOrEmpty(onewayType)){
			throw new NullPointerException("onewayType can't be null/empty!");
		}

		String algorithm = onewayType.getAlgorithm();
		if (Validator.isNullOrEmpty(algorithm)){
			throw new NullPointerException("the algorithm is null or empty!");
		}
		try{
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			return messageDigest;
		}catch (NoSuchAlgorithmException e){
			throw new EncryptionException("No such algorithm [" + algorithm + "]", e);
		}
	}
}