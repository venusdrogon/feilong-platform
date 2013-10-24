/*
 * Copyright (c) 2010 Jumbomart All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Jumbomart.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jumbo.
 *
 * JUMBOMART MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. JUMBOMART SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.feilong.netpay.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encrypt{

	public static final String	MD5_ENCODEING	= "utf-8";

	/**
	 * Used building output as Hex
	 */
	private static final char[]	DIGITS			= { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String getMD5ofStr(byte[] digest){
		String digestHexStr = "";
		for (int i = 0; i < 16; i++){
			digestHexStr += byteHEX(digest[i]);
		}
		return digestHexStr;
	}

	private static String byteHEX(byte ib){
		char[] ob = new char[2];
		ob[0] = DIGITS[(ib >>> 4) & 0X0F];
		ob[1] = DIGITS[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 对字符串进行MD5加密
	 * 
	 * @param text
	 *            明文
	 * @return 密文
	 */
	public static String md5(String text,String encoding){
		MessageDigest msgDigest = null;
		try{
			msgDigest = MessageDigest.getInstance("MD5");
		}catch (NoSuchAlgorithmException e){
			throw new IllegalStateException("System doesn't support MD5 algorithm.");
		}
		try{
			msgDigest.update(text.getBytes(encoding));
		}catch (UnsupportedEncodingException e){
			throw new IllegalStateException("System doesn't support your  EncodingException.");
		}
		return getMD5ofStr(msgDigest.digest());
	}
}
