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
package com.feilong.commons.core.security.symmetric;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ByteUtil;
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
 * 		log.info("SymmetricType.Blowfish:{}", symmetricEncryption.encryptHex(original,CharsetType.UTF8));
 * 		
 * 		返回:055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
 * 
 * 解密:
 * 		String keyString = "feilong";
 * 		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
 * 		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
 * 		log.info(symmetricEncryption.decryptHex(hexString,CharsetType.UTF8));
 * 		返回:鑫哥爱feilong
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-26 上午11:05:53
 * @version 1.0.1 2013-1-15 15:18 json log
 */
public final class SymmetricEncryption{

	/** The Constant log. */
	protected static final Logger	log	= LoggerFactory.getLogger(SymmetricEncryption.class);

	/** 对称加密key. */
	private Key						key;

	/** The key string. */
	private String					keyString;

	/** The symmetric type. */
	private SymmetricType			symmetricType;

	/**
	 * 构造函数(固定枚举支持范围,取消灵活性).
	 * 
	 * @param symmetricType
	 *            the symmetric type
	 * @param keyString
	 *            自定义密钥
	 */
	public SymmetricEncryption(SymmetricType symmetricType, String keyString){
		if (Validator.isNullOrEmpty(keyString)){
			throw new IllegalArgumentException("the keyString can't be null");
		}
		if (Validator.isNullOrEmpty(symmetricType)){
			throw new IllegalArgumentException("the symmetricType can't be null");
		}
		this.keyString = keyString;
		this.symmetricType = symmetricType;
		this.key = getKey(symmetricType.getAlgorithm(), keyString);

		if (log.isDebugEnabled()){
			log.debug(
					"symmetricType:[{}],getAlgorithm:[{}],getTransformation:[{}]",
					symmetricType.toString(),
					symmetricType.getAlgorithm(),
					symmetricType.getTransformation());
		}
	}

	/**
	 * des Base64加密
	 * 
	 * <pre>
	 * keyString=feilong
	 * encrypBase64("鑫哥爱feilong") ---->BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2
	 * </pre>
	 * 
	 * .
	 * 
	 * @param original
	 *            原字符串
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 加密之后的字符串
	 */
	public String encrypBase64(String original,String charsetName){
		try{
			byte[] bs1 = original.getBytes(charsetName);
			byte[] bs = opBytes(bs1, Cipher.ENCRYPT_MODE);

			BASE64Encoder base64Encoder = new BASE64Encoder();
			String encode = base64Encoder.encode(bs);

			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("encode", encode);

				log.debug(JsonUtil.format(map));
			}
			return encode;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonUtil.format(map));
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
	 * decryptBase64("BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2") ---->鑫哥爱feilong
	 * 
	 * </pre>
	 * 
	 * .
	 * 
	 * @param base64String
	 *            加密后的字符串
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 解密返回的原始密码
	 */
	public String decryptBase64(String base64String,String charsetName){
		try{
			BASE64Decoder base64Decoder = new BASE64Decoder();
			byte[] byteMi = base64Decoder.decodeBuffer(base64String);
			byte[] bs = opBytes(byteMi, Cipher.DECRYPT_MODE);
			String original = new String(bs, charsetName);

			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("base64String", base64String);

				log.debug(JsonUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, String> map = new HashMap<String, String>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("base64String", base64String);

				log.error(JsonUtil.format(map));
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
	 * encryptHex("鑫哥爱feilong")---->055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * </pre>
	 * 
	 * .
	 * 
	 * @param original
	 *            明文,原始内容
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 加密String明文输入,String密文输出
	 */
	public String encryptHex(Object original,String charsetName){
		try{
			byte[] bs = StringUtil.toBytes(original.toString(), charsetName);
			byte[] bs2 = opBytes(bs, Cipher.ENCRYPT_MODE);
			String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("hexStringUpperCase", hexStringUpperCase);

				log.debug(JsonUtil.format(map));
			}
			return hexStringUpperCase;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonUtil.format(map));
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
	 * decryptHex("055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6")---->"鑫哥爱feilong"
	 * </pre>
	 * 
	 * .
	 * 
	 * @param hexString
	 *            一串经过加密的16进制形式字符串,例如 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 解密 String明文输出
	 */
	public String decryptHex(String hexString,String charsetName){
		try{
			byte[] bs = ByteUtil.hexBytesToBytes(hexString.getBytes(charsetName));
			byte[] bs2 = opBytes(bs, Cipher.DECRYPT_MODE);
			String original = new String(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("hexString", hexString);

				log.debug(JsonUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isDebugEnabled()){
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("algorithm", symmetricType.getAlgorithm());
				map.put("keyString", keyString);
				map.put("hexString", hexString);

				log.error(JsonUtil.format(map));
			}
			e.printStackTrace();
		}
		return null;
	}

	// **********************************************************************
	/**
	 * 生成密钥.
	 * 
	 * @param algorithm
	 *            定义 加密算法 可用 DES,DESede,Blowfish
	 * @param keyString
	 *            自定义的密钥字符串
	 * @return Key
	 * @see <a href="http://blog.csdn.net/hbcui1984/article/details/5753083">解决Linux操作系统下AES解密失败的问题</a>
	 */
	private Key getKey(String algorithm,String keyString){
		try{
			// KeyGenerator 对象可重复使用，也就是说，在生成密钥后，可以重复使用同一个 KeyGenerator 对象来生成更多的密钥。
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

			// SHA1PRNG: It is just ensuring the random number generated is as close to "truly random" as possible.
			// Easily guessable random numbers break encryption.

			// 此类提供强加密随机数生成器 (RNG)。 创建一个可信任的随机数源
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

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
	 * 自定义一个key.
	 * 
	 * @param algorithm
	 *            the algorithm
	 * @param keyRule
	 *            the key rule
	 * @return the key2
	 */
	private Key getKey2(String algorithm,String keyRule){
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
	 * 操作.
	 * 
	 * @param bytes
	 *            the bytes
	 * @param opmode
	 *            模式
	 * @return the byte[]
	 * @throws Exception
	 *             the exception
	 */
	private byte[] opBytes(byte[] bytes,int opmode) throws Exception{
		// DESede/ECB/NoPadding
		// DESede/ECB/PKCS5Padding
		// DESede/ECB/ISO10126Padding
		String transformation = symmetricType.getTransformation();

		// 此类为加密和解密提供密码功能。它构成了 Java Cryptographic Extension (JCE) 框架的核心。
		// 转换transformation始终包括加密算法的名称（例如，DES），后面可能跟有一个反馈模式和填充方案。

		// 使用 CFB 和 OFB 之类的模式，Cipher 块可以加密单元中小于该 Cipher 的实际块大小的数据。
		// 请求这样一个模式时，可以指定一次处理的位数（可选）：将此数添加到模式名称中，正如 "DES/CFB8/NoPadding" 和 "DES/OFB32/PKCS5Padding" 转换所示。
		
		// 如果未指定该数，则将使用特定于提供者的默认值。（例如，SunJCE 提供者对 DES 使用默认的 64 位）。
		// 因此，通过使用如 CFB8 或 OFB8 的 8 位模式，Cipher 块可以被转换为面向字节的 Cipher 流。
		Cipher cipher = Cipher.getInstance(transformation);
		cipher.init(opmode, key);

		// 结束时，此方法将此 Cipher 对象重置为上一次调用 init 初始化得到的状态。
		// 即该对象被重置，并可用于加密或解密（具体取决于调用 init 时指定的操作模式）更多的数据。
		return cipher.doFinal(bytes);
	}
}
