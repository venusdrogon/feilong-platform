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
package com.feilong.commons.core.security;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.ByteUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 对称加密,支持的类型,详见 {@link SymmetricType}<br>
 * <ul>
 * <li>支持spring 注入</li>
 * <li>支持多种双向加密类型:@see SymmetricType
 * <ul>
 * <li>DES</li>
 * <li>DESede</li>
 * <li>AES</li>
 * <li>Blowfish</li>
 * <li>RC2</li>
 * <li>RC4</li>
 * <li>ARCFOUR</li>
 * </ul>
 * </li>
 * <li>支持两套 对称加密
 * <ul>
 * <li>encryptToBase64String,decryptBase64String,使用 Base64来加密解密</li>
 * <li>encryptToHexString,decryptHexString,使用大写的 十六进制码 加密解密,<b>(推荐使用这种,生成的字符串不会有特殊字符比如=号)</b></li>
 * </ul>
 * </li>
 * </ul>
 * 
 * <pre>
 * 使用示例:
 * 
 * 加密:
 * 		String original = "鑫哥爱feilong";
 * 		String keyString = "feilong";
 * 		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
 * 		log.info("SymmetricType.Blowfish:{}", symmetricEncryption.encryptToHexString(original));
 * 		
 * 		返回:055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
 * 
 * 解密:
 * 
 * 		String keyString = "feilong";
 * 		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
 * 		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
 * 		log.info(symmetricEncryption.decryptHexString(hexString));
 * 		返回:鑫哥爱feilong
 * 
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-26 上午11:05:53
 * @version 1.0.1 2013-1-15 15:18 json log
 */
public final class SymmetricEncryption{

	/** The Constant log. */
	protected static final Logger	log			= LoggerFactory.getLogger(SymmetricEncryption.class);

	/** 对称加密key. */
	private Key						key			= null;

	private String					keyString;

	/** 使用的算法如 DES,DESede,Blowfish. */
	private String					algorithm;

	private String					charsetName	= CharsetType.UTF8;

	/**
	 * 构造函数.
	 * 
	 * @param type
	 *            对称加密的类型
	 * @param keyString
	 *            自定义密钥
	 */
	public SymmetricEncryption(SymmetricType symmetricType, String keyString){
		if (Validator.isNullOrEmpty(keyString)){
			throw new IllegalArgumentException("the keyString can't be null");
		}
		this.algorithm = symmetricType.toString();
		log.debug("the param algorithm:[{}]", this.algorithm);
		log.debug("the param keyString:[{}]", keyString);
		setKey(keyString);
	}

	/**
	 * 生成密钥
	 * 
	 * @param keyString
	 *            自定义的密钥字符串
	 */
	protected void setKey(String keyString){
		this.keyString = keyString;
		this.key = getKey(algorithm, keyString);
	}

	/**
	 * des Base64加密
	 * 
	 * <pre>
	 * keyString=feilong
	 * desEncryptString("鑫哥爱feilong") ---->BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2
	 * 
	 * </pre>
	 * 
	 * @param original
	 *            原字符串
	 * @return 加密之后的字符串
	 */
	public String encryptToBase64String(String original){
		try{
			byte[] bs1 = original.getBytes(charsetName);
			byte[] bs = opBytes(algorithm, bs1, key, Cipher.ENCRYPT_MODE);

			BASE64Encoder base64Encoder = new BASE64Encoder();
			String encode = base64Encoder.encode(bs);

			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("encode", encode);

				log.debug(JsonFormatUtil.format(map));
			}
			return encode;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonFormatUtil.format(map));
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * des Base64解密
	 * 
	 * <pre>
	 * keyString=feilong
	 * decryptBase64String("BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2") ---->鑫哥爱feilong
	 * 
	 * </pre>
	 * 
	 * .
	 * 
	 * @param base64String
	 *            加密后的字符串
	 * @return 解密返回的原始密码
	 */
	public String decryptBase64String(String base64String){
		try{
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] byteMi = base64Decoder.decodeBuffer(base64String);
			byte[] bs = opBytes(algorithm, byteMi, key, Cipher.DECRYPT_MODE);
			String original = new String(bs, charsetName);

			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("base64String", base64String);

				log.debug(JsonFormatUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("base64String", base64String);

				log.error(JsonFormatUtil.format(map));
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 16进制 des加密,加密String明文输入,String密文输出 <br>
	 * 例如:key=feilong
	 * 
	 * <pre>
	 * encryptToHexString("鑫哥爱feilong")---->055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * </pre>
	 * 
	 * @param original
	 *            明文,原始内容
	 * @return 加密String明文输入,String密文输出
	 */
	public String encryptToHexString(Object original){
		byte[] bs = StringUtil.toBytes(original.toString());
		try{
			byte[] bs2 = opBytes(algorithm, bs, key, Cipher.ENCRYPT_MODE);
			String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("hexStringUpperCase", hexStringUpperCase);

				log.debug(JsonFormatUtil.format(map));
			}
			return hexStringUpperCase;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonFormatUtil.format(map));
			}
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 16进制 des 解密,解密 以String密文输入,String明文输出 <br>
	 * 例如:key=feilong
	 * 
	 * <pre>
	 * decryptHexString("055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6")---->"鑫哥爱feilong"
	 * </pre>
	 * 
	 * @param hexString
	 *            一串经过加密的16进制形式字符串,例如 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * @return 解密 String明文输出
	 */
	public String decryptHexString(String hexString){
		byte[] bs = ByteUtil.hexBytesToBytes(hexString.getBytes());
		try{
			byte[] bs2 = opBytes(algorithm, bs, key, Cipher.DECRYPT_MODE);
			String original = new String(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("hexString", hexString);

				log.debug(JsonFormatUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("hexString", hexString);

				log.error(JsonFormatUtil.format(map));
			}
			e.printStackTrace();
		}
		return null;
	}

	// **********************************************************************
	/**
	 * 生成密钥
	 * 
	 * @param algorithm
	 *            定义 加密算法 可用 DES,DESede,Blowfish
	 * @param keyString
	 *            自定义的密钥字符串
	 * @see <a href="http://blog.csdn.net/hbcui1984/article/details/5753083">解决Linux操作系统下AES解密失败的问题</a>
	 * @return Key
	 */
	private static Key getKey(String algorithm,String keyString){
		try{
			// KeyGenerator 对象可重复使用，也就是说，在生成密钥后，可以重复使用同一个 KeyGenerator 对象来生成更多的密钥。
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

			// 此类提供强加密随机数生成器 (RNG)。 创建一个可信任的随机数源
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");// SHA1PRNG: It is just ensuring the random number generated is as close to
																				// "truly random" as
																				// possible. Easily guessable random numbers break encryption.
			// SecureRandom 实现完全隨操作系统本身的內部狀態，除非調用方在調用 getInstance 方法之後又調用了 setSeed 方法
			// 解决 :windows上加解密正常，linux上加密正常，解密时发生如下异常：
			// javax.crypto.BadPaddingException: Given final block not properly padded
			secureRandom.setSeed(keyString.getBytes());

			keyGenerator.init(secureRandom);

			Key key = keyGenerator.generateKey();
			keyGenerator = null;
			return key;
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 自定义一个key
	 */
	private static Key getKey2(String algorithm,String keyRule){
		byte[] keyByte = keyRule.getBytes();
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[8];
		int keyByteLenth = keyByte.length;
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < byteTemp.length && i < keyByteLenth; ++i){
			byteTemp[i] = keyByte[i];
		}
		Key key = new SecretKeySpec(byteTemp, algorithm);
		return key;
	}

	/**
	 * 操作
	 * 
	 * @param algorithm
	 *            算法
	 * @param bytes
	 * @param key
	 *            key
	 * @param opmode
	 *            模式
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] opBytes(String algorithm,byte[] bytes,Key key,int opmode) throws Exception{
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(opmode, key);
		// 结束时，此方法将此 Cipher 对象重置为上一次调用 init 初始化得到的状态。即该对象被重置，并可用于加密或解密（具体取决于调用 init 时指定的操作模式）更多的数据。
		return cipher.doFinal(bytes);
	}

	public String getKeyString(){
		return this.keyString;
	}
}
