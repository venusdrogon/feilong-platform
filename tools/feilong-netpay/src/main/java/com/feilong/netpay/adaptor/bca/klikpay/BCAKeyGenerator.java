/**
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
package com.feilong.netpay.adaptor.bca.klikpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class BCAKeyGenerator(这是BCA提供的代码).
 * 
 * @author Brian
 */
final public class BCAKeyGenerator{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BCAKeyGenerator.class);

	/**
	 * Strpad.
	 * 
	 * @param sstring
	 *            the sstring
	 * @param len
	 *            the len
	 * @param padder
	 *            the padder
	 * @param PadRight
	 *            the pad right
	 * @return the string
	 */
	public static String strpad(String sstring,Integer len,char padder,boolean PadRight){
		if (sstring.length() < 1){
			return "";
		}
		char[] c = sstring.toCharArray();
		if (c.length > len)
			return sstring;
		String ret = "";
		if (PadRight){
			int indexlen = len - c.length;
			for (int i = 0; i < len; i++){
				ret += i < indexlen ? padder : c[i - indexlen];
			}
		}else
			for (int i = 0; i < len; i++){
				ret += i < c.length ? c[i] : padder;
			}
		return ret;
	}

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
	}

	// /**
	// * Auth key.
	// *
	// * @param klikPayCode
	// * the klik pay code
	// * @param transactionNo
	// * the transaction no
	// * @param currency
	// * the currency
	// * @param transactionDate
	// * the transaction date
	// * @param secretKey
	// * the secret key
	// * @return the string
	// * @throws Exception
	// * the exception
	// */
	// public static String authKey(String klikPayCode,String transactionNo,String currency,String transactionDate,String secretKey)
	// throws Exception{
	// if (secretKey.length() == 16){
	// secretKey = KlikPayAdaptor.getKeyId(secretKey);
	// }else if (secretKey.length() != 32){
	// throw new Exception("Invalid keyId or clearKey (" + secretKey + ")");
	// }
	// if (!transactionDate.matches("^\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d$")){
	// throw new Exception("Invalid transactionDate Format (" + transactionDate + ")");
	// }
	// String padded = strpad(klikPayCode, 10, '0', false);
	// padded += strpad(transactionNo, 18, 'A', false);
	// padded += strpad(currency, 5, '1', false);
	// padded += strpad(transactionDate, 19, 'C', true);
	// padded += secretKey;
	//
	// MessageDigest md = MessageDigest.getInstance("MD5");
	// md.update(padded.getBytes());
	// byte[] hbyte = md.digest();
	// String md5 = toHexString(hbyte);
	// return doAuthKey(md5, secretKey).toUpperCase();
	// }

	//
	// /**
	// * To hex string.
	// *
	// * @param b
	// * the b
	// * @return the string
	// */
	// private static String toHexString(byte b[]){
	// if ((b == null) || (b.length == 0)){
	// return "";
	// }else{
	// int off = 0;
	// int len = b.length;
	// StringBuilder s = new StringBuilder();
	// for (int i = off; i < off + len; i++){
	// s.append(HEX_DIGITS.charAt((b[i] & 0xff) >> 4));
	// s.append(HEX_DIGITS.charAt(b[i] & 0xf));
	// }
	// return s.toString();
	// }
	// }

	// /**
	// * Gets the hash.
	// *
	// * @param val
	// * the val
	// * @return the hash
	// */
	// private static int getHash(String val){
	// int h = 0;
	// char[] vals = val.toCharArray();
	// for (int i = 0; i < vals.length; i++){
	// h = (h * 31) + vals[i];
	// }
	// return h;
	// }

	// /** The Constant HEX_DIGITS. */
	// private static final String HEX_DIGITS = "0123456789ABCDEF";

	//
	// /**
	// * Signature.
	// *
	// * @param klikPayCode
	// * the klik pay code
	// * @param transactionDate
	// * the transaction date
	// * @param transactionNo
	// * the transaction no
	// * @param totalAmount
	// * the total amount
	// * @param currency
	// * the currency
	// * @param secretKey
	// * the secret key
	// * @return the string
	// * @throws Exception
	// * the exception
	// */
	// public static String signature(
	// String klikPayCode,
	// String transactionDate,
	// String transactionNo,
	// String totalAmount,
	// String currency,
	// String secretKey) throws Exception{
	// if (secretKey.length() == 16)
	// secretKey = KlikPayAdaptor.getKeyId(secretKey);
	// else if (secretKey.length() != 32)
	// throw new Exception("Invalid keyId or clearKey (" + secretKey + ")");
	// if (!transactionDate.matches("^\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d$"))
	// throw new Exception("Invalid transactionDate Format (" + transactionDate + ")");
	//
	// // First Value. Using klikPayCode, transactionNo, currency, secretKey.
	// String tmpkey1 = "" + klikPayCode + transactionNo + currency + secretKey;
	//
	// // Second Value. Using transactionDate, totalAmount.
	// String tmpkey2 = Integer.toString(Integer.parseInt(transactionDate.substring(0, 10).replaceAll("/", ""))
	// + new Double(totalAmount).intValue());
	// int keynum1 = getHash(tmpkey1);
	// int keynum2 = getHash(tmpkey2);
	// BigDecimal hash = new BigDecimal(keynum1).add(new BigDecimal(keynum2)).abs();
	// return hash + "";
	// }

}
