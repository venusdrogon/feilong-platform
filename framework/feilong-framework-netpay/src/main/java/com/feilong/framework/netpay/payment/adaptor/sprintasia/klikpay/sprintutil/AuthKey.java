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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.sprintutil;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Class AuthKey.
 * 
 * @author Brian
 */
public class AuthKey{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(AuthKey.class);

	/** The Constant HEX_DIGITS. */
	private static final String	HEX_DIGITS	= "0123456789ABCDEF";

	/**
	 * Do auth key.
	 * 
	 * @param smd5
	 *            the smd5
	 * @param sKey
	 *            the s key
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String doAuthKey(String smd5,String sKey) throws Exception{
		StringBuilder hexStringencryptedHashKey = new StringBuilder();
		try{
			byte byteHashKey[] = new byte[smd5.length() / 2];
			for (int k = 0; k < (smd5.length() / 2); k++){
				byteHashKey[k] = (byte) (Integer.parseInt(smd5.substring(2 * k, 2 * k + 2), 16));
			}
			byte byteKey[] = new byte[sKey.length() / 2];
			for (int j = 0; j < sKey.length() / 2; j++){
				byteKey[j] = (byte) (Integer.parseInt(sKey.substring(2 * j, 2 * j + 2), 16));
			}
			EnDeCipherDESTriple cipher = new EnDeCipherDESTriple(byteKey); // Class for 3DES ECB Encrypt
			byte[] byteEncryptedHashKey = cipher.encryptECB(byteHashKey);

			for (int a = 0; a < byteEncryptedHashKey.length; a++){
				String hex1 = Integer.toHexString(0xFF & byteEncryptedHashKey[a]);
				if (hex1.length() == 1){
					hexStringencryptedHashKey.append('0');
				}

				hexStringencryptedHashKey.append(hex1);
			}
			return hexStringencryptedHashKey.toString();

		}catch (NoSuchAlgorithmException nsae){}
		return "";
	}

	/**
	 * Key id.
	 * 
	 * @param clearKey
	 *            the clear key
	 * @return the string
	 */
	public static String KeyId(String clearKey){
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = clearKey.getBytes();
		String keyId = "";
		for (int cntr = 0; cntr < clearKey.length(); cntr++){
			keyId = keyId + hexArray[(bytes[cntr] & 0xFF) / 16] + hexArray[(bytes[cntr] & 0xFF) % 16];
		}
		return keyId;
	}

	/**
	 * Do hash.
	 * 
	 * @param firstval
	 *            the firstval
	 * @param secondval
	 *            the secondval
	 * @return the string
	 */
	public static String doHash(String firstval,String secondval){
		int keynum1 = getHash(firstval);
		log.debug("firstval:[{}]-->hash:[{}]", firstval, keynum1);

		int keynum2 = getHash(secondval);
		log.debug("secondval:[{}]-->hash:[{}]", secondval, keynum2);

		BigDecimal hash = new BigDecimal(keynum1).add(new BigDecimal(keynum2)).abs();

		return hash + "";
	}

	// public String getHash(String value){
	// Integer hash = 0;
	// for (int i = 0; i < value.length(); i++){
	// // (int)chars[i]); ascii( value[i] );
	// hash = (hash * 31) + (int) (value.charAt(i));
	// while (hash > Integer.MAX_VALUE){
	// hash = hash + Integer.MIN_VALUE - Integer.MAX_VALUE - 1;
	// }
	// while (hash < Integer.MIN_VALUE){
	// hash = hash + Integer.MAX_VALUE - Integer.MIN_VALUE + 1;
	// }
	// }
	// return "" + hash;
	// }

	/**
	 * 这个方法和上面注释的方法 值是相等的.
	 * 
	 * @param val
	 *            the val
	 * @return the hash
	 */
	public static int getHash(String val){
		int h = 0;
		char[] vals = val.toCharArray();
		for (int i = 0; i < vals.length; i++){
			h = (h * 31) + vals[i];
		}
		// log.debug("the param val:{},h:{}", val, h);
		return h;
	}

	/**
	 * To hex string.
	 * 
	 * @param b
	 *            the b
	 * @return the string
	 */
	public static String toHexString(byte b[]){
		if ((b == null) || (b.length == 0)){
			return "";
		}
		int off = 0;
		int len = b.length;
		StringBuilder s = new StringBuilder();
		for (int i = off; i < off + len; i++){
			s.append(HEX_DIGITS.charAt((b[i] & 0xff) >> 4));
			s.append(HEX_DIGITS.charAt(b[i] & 0xf));
		}
		return s.toString();
	}
}
