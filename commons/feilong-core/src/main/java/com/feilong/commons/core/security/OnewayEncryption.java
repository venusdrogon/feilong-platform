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
 * 单向加密算法
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-25 上午7:19:18
 */
public abstract class OnewayEncryption{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(OnewayEncryption.class);

	/**
	 * 使用算法 单向加密字符串
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
	 * md5加密,byte[] 便于自定义编码
	 * 
	 * <pre>
	 * 
	 * // 签名(utf-8编码)
	 * byte[] bytes = StringUtil.toBytes(sb.toString(), CharsetType.UTF8);
	 * return MD5Util.encode(bytes).toUpperCase();
	 * </pre>
	 * 
	 * @param inputBytes
	 * @return 加密之后的转成小写的16进制字符串
	 */
	public static String encode(OnewayType onewayType,byte[] inputBytes){
		MessageDigest messageDigest = getMessageDigest(onewayType);
		byte[] bs = messageDigest.digest(inputBytes);
		return ByteUtil.bytesToHexStringLowerCase(bs);
	}

	/**
	 * 计算文件MD5值
	 * 
	 * @param filePath
	 *            文件
	 * @return
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

	/**
	 * 使用 onewayType 创建MessageDigest对象
	 * 
	 * @param onewayType
	 * @return
	 */
	protected final static MessageDigest getMessageDigest(OnewayType onewayType){
		String algorithm = onewayType.getAlgorithm();
		return getMessageDigest(algorithm);
	}

	/**
	 * 使用MD5创建MessageDigest对象
	 * 
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
