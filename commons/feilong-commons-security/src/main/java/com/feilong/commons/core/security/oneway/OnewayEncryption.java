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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ByteUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 单向加密算法.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-25 上午7:19:18
 */
public abstract class OnewayEncryption{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(OnewayEncryption.class);

	/**
	 * 使用算法 单向加密字符串.
	 * 
	 * @param onewayType
	 *            单向加密
	 * @param origin
	 *            原始字符串,将使用默认的 value.getBytes() 转成字节数组<br>
	 *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(byte[])}
	 * @return 加密之后的转成小写的16进制字符串
	 */
	public static String encode(OnewayType onewayType,String origin){
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
	 *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(byte[])}
	 * @param charsetName
	 *            受支持的 charset 名称,比如 utf-8
	 * @return 加密之后的转成小写的16进制字符串
	 */
	public static String encode(OnewayType onewayType,String origin,String charsetName){
		byte[] inputBytes = StringUtil.toBytes(origin, charsetName);
		return encode(onewayType, inputBytes);
	}

	/**
	 * md5加密,byte[] 便于自定义编码
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
	 */
	public static String encode(OnewayType onewayType,byte[] inputBytes){
		MessageDigest messageDigest = getMessageDigest(onewayType);

		// 对于给定数量的更新数据，digest 方法只能被调用一次。在调用 digest 之后，MessageDigest 对象被重新设置成其初始状态。
		// 使用指定的 byte 数组对摘要进行最后更新，然后完成摘要计算。
		// 也就是说，此方法首先调用 update(input)，向 update 方法传递 input 数组，然后调用 digest()。

		// 和自己先写 update(input) ，再 调用 digest（）效果一样的
		byte[] bs = messageDigest.digest(inputBytes);

		return ByteUtil.bytesToHexStringLowerCase(bs);
	}

	/**
	 * 计算文件MD5值.
	 * 
	 * @param onewayType
	 *            the oneway type
	 * @param filePath
	 *            文件
	 * @return the string
	 */
	public static String encodeFile(OnewayType onewayType,String filePath){
		File file = new File(filePath);
		if (file.exists()){
			if (file.isFile()){
				try{
					FileInputStream fileInputStream = new FileInputStream(file);
					MessageDigest messageDigest = getMessageDigest(onewayType);
					byte buffer[] = new byte[1024];
					int len;
					while ((len = fileInputStream.read(buffer, 0, 1024)) != -1){
						messageDigest.update(buffer, 0, len);
					}
					fileInputStream.close();
					byte[] bs = messageDigest.digest();
					// 该数的正负号（-1 表示负，0 表示零，1 表示正）
					BigInteger bigInt = new BigInteger(1, bs);
					return bigInt.toString(16);
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}catch (IOException e){
					e.printStackTrace();
				}
			}else{
				throw new IllegalArgumentException(filePath + " is not a File!");
			}
		}else{
			throw new IllegalArgumentException("file:" + filePath + " don't exists!");
		}
		return null;
	}

	// ***********************************************************************************************

	/**
	 * 使用 onewayType 创建MessageDigest对象.
	 * 
	 * @param onewayType
	 *            the oneway type
	 * @return the message digest
	 */
	protected final static MessageDigest getMessageDigest(OnewayType onewayType){
		String algorithm = onewayType.getAlgorithm();
		return getMessageDigest(algorithm);
	}

	/**
	 * 使用MD5创建MessageDigest对象.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @return MessageDigest
	 */
	protected final static MessageDigest getMessageDigest(String algorithm){
		if (Validator.isNullOrEmpty(algorithm)){
			throw new NullPointerException("the algorithm is null or empty!");
		}
		try{
			// 使用MD5创建MessageDigest对象
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			return messageDigest;
		}catch (NoSuchAlgorithmException e){
			throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
		}
	}
}
