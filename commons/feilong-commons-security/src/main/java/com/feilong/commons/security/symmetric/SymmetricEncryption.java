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

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ByteUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.commons.security.EncryptionException;

//}${person}{@code
//&#125;
//$&#123;person&#125; == ${person}
/**
 * 对称加密解密工具
 * 
 * <h4>特点:</h4> <blockquote>
 * <ul>
 * <li>支持spring 参数注入
 * 
 * <pre>
 * {@code
 * 	<bean id="blowfishForPassword" class="com.feilong.commons.core.security.symmetric.SymmetricEncryption" lazy-init="true">
 * 		<!-- 第1个参数是加密解密方式 -->
 * 		<constructor-arg index="0" value="Blowfish" />
 * 		<!-- 第2个参数是密钥字符串 -->
 * 		<constructor-arg index="1" value="feilong" />
 * 	</bean>
 * }
 * </pre>
 * 
 * </li>
 * <li>支持多种双向加密类型:{@link SymmetricType}</li>
 * </ul>
 * </blockquote>
 * 
 * 
 * <h4>支持的类型:</h4> <blockquote>
 * <ul>
 * <li>{@link SymmetricType#DES}</li>
 * <li>{@link SymmetricType#DESede}</li>
 * <li>{@link SymmetricType#AES}</li>
 * <li>{@link SymmetricType#Blowfish}</li>
 * <li>{@link SymmetricType#RC2}</li>
 * <li>{@link SymmetricType#RC4}</li>
 * <li>{@link SymmetricType#ARCFOUR}</li>
 * </ul>
 * </blockquote>
 * 
 * 
 * <h4>两种对称加密解密方式:</h4>
 * 
 * <blockquote>
 * <ul>
 * <li>{@link #encryptBase64(String, String)},{@link #decryptBase64(String, String)}<br>
 * 将加密之后的字节码,使用 Base64封装返回.</li>
 * <li>{@link #encryptHex(Object, String)},{@link #decryptHex(String, String)}<br>
 * 将加密之后的字节码,使用<b>大写的</b> Hex十六进制码形式封装返回<b>(推荐使用这种,生成的字符串不会有特殊字符比如=号,可用于url参数传递)</b></li>
 * </ul>
 * </blockquote>
 * 
 * 
 * <h4>使用示例:</h4>
 * 
 * <blockquote>
 * 
 * <pre>
 * {@code
 * Example 1,encryptHex加密:
 * 		String original = "鑫哥爱feilong";
 * 		String keyString = "feilong";
 * 
 * 		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
 * 		log.info(symmetricEncryption.encryptHex(original,CharsetType.UTF8));
 * 		
 * 		输出:055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
 * 
 * Example 2,decryptHex解密:
 * 		String keyString = "feilong";
 * 		String hexString = "055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6";
 * 
 * 		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(SymmetricType.Blowfish, keyString);
 * 		log.info(symmetricEncryption.decryptHex(hexString,CharsetType.UTF8));
 * 
 * 		输出:鑫哥爱feilong
 * }
 * </pre>
 * 
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2011-12-26 上午11:05:53
 * @version 1.0.1 2013-1-15 15:18 json log
 * @version 1.0.7 2014-6-5 16:26 丰富了javadoc
 * @see javax.crypto.Cipher
 * @see javax.crypto.Cipher#ENCRYPT_MODE
 * @see javax.crypto.Cipher#DECRYPT_MODE
 * @see javax.crypto.KeyGenerator
 * @see java.security.Key
 * @see org.apache.commons.codec.binary.Base64
 * @see SymmetricType
 * @see #encryptBase64(String, String)
 * @see #decryptBase64(String, String)
 * @see #encryptHex(Object, String)
 * @see #decryptHex(String, String)
 */
public final class SymmetricEncryption{

	/** The Constant log. */
	protected static final Logger	log	= LoggerFactory.getLogger(SymmetricEncryption.class);

	/** 对称加密key. */
	private Key						key;

	/** The key string. */
	private String					keyString;

	/** The algorithm. */
	private String					algorithm;

	/**
	 * 转换的名称，例如 DES/CBC/PKCS5Padding。<br>
	 * 有关标准转换名称的信息，请参见 Java Cryptography Architecture Reference Guide 的附录 A.
	 * 
	 * @see <a href="http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html">StandardNames</a>
	 */
	private String					transformation;

	/**
	 * 构造函数(固定枚举支持范围).
	 * 
	 * @param symmetricType
	 *            the symmetric type
	 * @param keyString
	 *            自定义密钥
	 * @throws NullPointerException
	 *             if isNullOrEmpty(symmetricType) or isNullOrEmpty(keyString)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see SymmetricType
	 * @see #SymmetricEncryption(SymmetricType, String, CipherMode, CipherPadding)
	 */
	public SymmetricEncryption(SymmetricType symmetricType, String keyString) throws NullPointerException,EncryptionException{
		this(symmetricType, keyString, null, null);
	}

	/**
	 * 构造函数(固定枚举支持范围).
	 * 
	 * @param symmetricType
	 *            the symmetric type
	 * @param keyString
	 *            the key string
	 * @param cipherMode
	 *            the cipher mode
	 * @param cipherPadding
	 *            the cipher padding
	 * @throws NullPointerException
	 *             if isNullOrEmpty(symmetricType) or isNullOrEmpty(keyString)
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see SymmetricType
	 * @see javax.crypto.Cipher#tokenizeTransformation(String)
	 * @since 1.0.7
	 */
	public SymmetricEncryption(SymmetricType symmetricType, String keyString, CipherMode cipherMode, CipherPadding cipherPadding)
			throws NullPointerException,EncryptionException{
		if (Validator.isNullOrEmpty(keyString)){
			throw new NullPointerException("the keyString can't be null");
		}
		if (Validator.isNullOrEmpty(symmetricType)){
			throw new NullPointerException("the symmetricType can't be null");
		}

		this.keyString = keyString;
		this.algorithm = symmetricType.getAlgorithm();

		if (null == cipherMode && null == cipherPadding){
			this.transformation = algorithm;
		}else{
			this.transformation = algorithm;
			if (null != cipherMode){
				transformation += "/" + cipherMode;
			}
			if (null != cipherPadding){
				transformation += "/" + cipherPadding;
			}
		}

		if (log.isDebugEnabled()){
			log.debug("algorithm:[{}],keyString:[{}],transformation:[{}]", algorithm, keyString, transformation);
		}

		//由于是固定的类型枚举,枚举里面的加密类型都经过测试过的,所以理论上来说不会再出现   NoSuchAlgorithmException
		try{
			this.key = getKey(keyString);
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
			throw new EncryptionException(e);
		}
	}

	/**
	 * 将加密之后的字节码,使用 Base64封装返回.
	 * 
	 * <pre>
	 * keyString=feilong
	 * encrypBase64("鑫哥爱feilong") ---->BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2
	 * </pre>
	 * 
	 * @param original
	 *            原字符串
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 加密之后的字符串
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see sun.misc.BASE64Encoder
	 * @see org.apache.commons.codec.binary.Base64
	 * @see com.feilong.commons.core.enumeration.CharsetType
	 */
	@SuppressWarnings("restriction")
	public String encryptBase64(String original,String charsetName) throws EncryptionException{
		try{
			byte[] bs1 = original.getBytes(charsetName);
			byte[] bs = opBytes(bs1, Cipher.ENCRYPT_MODE);

			String encode = new String(Base64.encodeBase64(bs));

			if (log.isDebugEnabled()){
				Map<String, String> map = new LinkedHashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("original", original);
				map.put("keyString", keyString);
				map.put("encrypBase64", encode);
				map.put("encrypBase64Length", "" + encode.length());

				log.debug(JsonUtil.format(map));
			}
			return encode;
		}catch (Exception e){
			if (log.isErrorEnabled()){
				Map<String, String> map = new LinkedHashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonUtil.format(map));
			}
			e.printStackTrace();

			//通过使用异常链，我们可以提高代码的可理解性、系统的可维护性和友好性。
			throw new EncryptionException(e);
		}
	}

	/**
	 * des Base64解密.
	 * 
	 * <pre>
	 * keyString=feilong
	 * decryptBase64("BVl2k0U5+qokOeI6ufFlVS8XnkwEwff2") ---->鑫哥爱feilong
	 * 
	 * </pre>
	 * 
	 * @param base64String
	 *            加密后的字符串
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 解密返回的原始密码
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see sun.misc.BASE64Decoder
	 * @see sun.misc.BASE64Decoder#decodeBuffer(String)
	 * @see org.apache.commons.codec.binary.Base64
	 * @see org.apache.commons.codec.binary.Base64#decodeBase64(byte[])
	 * @see CharsetType
	 */
	@SuppressWarnings("restriction")
	public String decryptBase64(String base64String,String charsetName) throws EncryptionException{
		try{
			byte[] byteMi = Base64.decodeBase64(base64String);
			byte[] bs = opBytes(byteMi, Cipher.DECRYPT_MODE);
			String original = new String(bs, charsetName);

			if (log.isDebugEnabled()){
				Map<String, String> map = new LinkedHashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("base64String", base64String);

				log.debug(JsonUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isErrorEnabled()){
				Map<String, String> map = new LinkedHashMap<String, String>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("base64String", base64String);

				log.error(JsonUtil.format(map));
			}
			e.printStackTrace();
			throw new EncryptionException(e);
		}
	}

	/**
	 * 将加密之后的字节码,使用大写的 Hex形式封装返回.
	 * 
	 * 
	 * <pre>
	 * 例如:key=feilong
	 * encryptHex("鑫哥爱feilong")---->055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * </pre>
	 * 
	 * @param original
	 *            明文,原始内容
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 加密String明文输入,String密文输出
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see StringUtil#toBytes(String, String)
	 * @see #opBytes(byte[], int)
	 * @see ByteUtil#bytesToHexStringUpperCase(byte[])
	 */
	public String encryptHex(Object original,String charsetName) throws EncryptionException{
		try{
			byte[] bs = StringUtil.toBytes(original.toString(), charsetName);
			byte[] bs2 = opBytes(bs, Cipher.ENCRYPT_MODE);
			String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new LinkedHashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);
				map.put("hexStringUpperCase", hexStringUpperCase);
				map.put("hexStringUpperCaseLength", hexStringUpperCase.length());

				log.debug(JsonUtil.format(map));
			}
			return hexStringUpperCase;
		}catch (Exception e){
			if (log.isErrorEnabled()){
				Map<String, Object> map = new LinkedHashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("original", original);

				log.error(JsonUtil.format(map));
			}
			e.printStackTrace();
			throw new EncryptionException(e);
		}
	}

	/**
	 * 16进制 des 解密,解密 以String密文输入,String明文输出.
	 * 
	 * <pre>
	 * 例如:key=feilong
	 * decryptHex("055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6")---->"鑫哥爱feilong"
	 * </pre>
	 * 
	 * @param hexString
	 *            一串经过加密的16进制形式字符串,例如 055976934539FAAA2439E23AB9F165552F179E4C04C1F7F6
	 * @param charsetName
	 *            编码集 {@link CharsetType}
	 * @return 解密 String明文输出
	 * @throws EncryptionException
	 *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
	 * @see #opBytes(byte[], int)
	 */
	public String decryptHex(String hexString,String charsetName) throws EncryptionException{
		try{
			byte[] bs = ByteUtil.hexBytesToBytes(hexString.getBytes(charsetName));

			byte[] bs2 = opBytes(bs, Cipher.DECRYPT_MODE);
			String original = new String(bs2);

			if (log.isDebugEnabled()){
				Map<String, Object> map = new LinkedHashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("hexString", hexString);
				map.put("original", original);

				log.debug(JsonUtil.format(map));
			}
			return original;
		}catch (Exception e){
			if (log.isErrorEnabled()){
				Map<String, Object> map = new LinkedHashMap<String, Object>();

				map.put("algorithm", algorithm);
				map.put("keyString", keyString);
				map.put("hexString", hexString);

				log.error(JsonUtil.format(map));
			}
			e.printStackTrace();
			throw new EncryptionException(e);
		}
	}

	// **********************************************************************
	/**
	 * 生成密钥.
	 * 
	 * @param _keyString
	 *            自定义的密钥字符串
	 * @return Key
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @see <a href="http://blog.csdn.net/hbcui1984/article/details/5753083">解决Linux操作系统下AES解密失败的问题</a>
	 * @see KeyGenerator
	 * @see SecureRandom
	 */
	private Key getKey(String _keyString) throws NoSuchAlgorithmException{
		// KeyGenerator 对象可重复使用，也就是说，在生成密钥后，可以重复使用同一个 KeyGenerator 对象来生成更多的密钥。
		KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

		// SHA1PRNG: It is just ensuring the random number generated is as close to "truly random" as possible.
		// Easily guessable random numbers break encryption.

		// 此类提供强加密随机数生成器 (RNG)。 创建一个可信任的随机数源
		//TODO
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

		// SecureRandom 实现完全隨操作系统本身的內部狀態，除非調用方在調用 getInstance 方法之後又調用了 setSeed 方法
		// 解决 :windows上加解密正常，linux上加密正常，解密时发生如下异常：
		// javax.crypto.BadPaddingException: Given final block not properly padded
		secureRandom.setSeed(_keyString.getBytes());

		keyGenerator.init(secureRandom);

		Key _key = keyGenerator.generateKey();
		keyGenerator = null;
		return _key;
	}

	/**
	 * 自定义一个key.
	 * 
	 * @param keyRule
	 *            the key rule
	 * @return the key2
	 */
	@SuppressWarnings("unused")
	private Key getKey2(String keyRule){
		byte[] keyByte = keyRule.getBytes();
		// 创建一个空的八位数组,默认情况下为0
		byte[] byteTemp = new byte[8];
		int keyByteLenth = keyByte.length;
		// 将用户指定的规则转换成八位数组
		for (int i = 0; i < byteTemp.length && i < keyByteLenth; ++i){
			byteTemp[i] = keyByte[i];
		}
		Key _key = new SecretKeySpec(byteTemp, algorithm);
		return _key;
	}

	/**
	 * 操作字节数组.
	 * 
	 * @param bytes
	 *            the bytes
	 * @param opmode
	 *            模式,{@link Cipher#ENCRYPT_MODE} or {@link Cipher#DECRYPT_MODE}
	 * @return the new buffer with the result
	 * 
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws NoSuchPaddingException
	 *             the no such padding exception
	 * @throws InvalidKeyException
	 *             the invalid key exception
	 * @throws IllegalBlockSizeException
	 *             the illegal block size exception
	 * @throws BadPaddingException
	 *             the bad padding exception
	 * @see Cipher
	 * @see Cipher#getInstance(String)
	 * @see Cipher#init(int, Key)
	 * @see Cipher#doFinal(byte[])
	 */
	private byte[] opBytes(byte[] bytes,int opmode) throws NoSuchAlgorithmException,NoSuchPaddingException,InvalidKeyException,
			IllegalBlockSizeException,BadPaddingException{

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