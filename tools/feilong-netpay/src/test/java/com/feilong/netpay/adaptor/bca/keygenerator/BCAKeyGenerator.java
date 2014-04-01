/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feilong.netpay.adaptor.bca.keygenerator;

import java.math.BigDecimal;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.security.symmetric.SymmetricEncryption;
import com.feilong.commons.core.security.symmetric.SymmetricType;
import com.feilong.netpay.adaptor.bca.klikpay.KlikPayAdaptor;

/**
 * The Class BCAKeyGenerator.
 * 
 * @author Brian
 */
final public class BCAKeyGenerator{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(BCAKeyGenerator.class);

	/** The Constant HEX_DIGITS. */
	private static final String	HEX_DIGITS	= "0123456789ABCDEF";

	/**
	 * Signature.
	 * 
	 * @param klikPayCode
	 *            the klik pay code
	 * @param transactionDate
	 *            the transaction date
	 * @param transactionNo
	 *            the transaction no
	 * @param totalAmount
	 *            the total amount
	 * @param currency
	 *            the currency
	 * @param secretKey
	 *            the secret key
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String signature(
			String klikPayCode,
			String transactionDate,
			String transactionNo,
			String totalAmount,
			String currency,
			String secretKey) throws Exception{
		if (secretKey.length() == 16)
			secretKey = com.feilong.netpay.adaptor.bca.klikpay.KlikPayAdaptor.getKeyId(secretKey);
		else if (secretKey.length() != 32)
			throw new Exception("Invalid keyId or clearKey (" + secretKey + ")");
		if (!transactionDate.matches("^\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d$"))
			throw new Exception("Invalid transactionDate Format (" + transactionDate + ")");

		// First Value. Using klikPayCode, transactionNo, currency, secretKey.
		String tmpkey1 = "" + klikPayCode + transactionNo + currency + secretKey;

		// Second Value. Using transactionDate, totalAmount.
		String tmpkey2 = Integer.toString(Integer.parseInt(transactionDate.substring(0, 10).replaceAll("/", ""))
				+ new Double(totalAmount).intValue());
		return doHash(tmpkey1, tmpkey2);
	}

	/**
	 * Auth key.
	 * 
	 * @param klikPayCode
	 *            the klik pay code
	 * @param transactionNo
	 *            the transaction no
	 * @param currency
	 *            the currency
	 * @param transactionDate
	 *            the transaction date
	 * @param secretKey
	 *            the secret key
	 * @return the string
	 * @throws Exception
	 *             the exception
	 */
	public static String authKey(String klikPayCode,String transactionNo,String currency,String transactionDate,String secretKey)
			throws Exception{
		if (secretKey.length() == 16)
			secretKey = KlikPayAdaptor.getKeyId(secretKey);
		else if (secretKey.length() != 32)
			throw new Exception("Invalid keyId or clearKey (" + secretKey + ")");
		if (!transactionDate.matches("^\\d\\d/\\d\\d/\\d{4} \\d\\d:\\d\\d:\\d\\d$"))
			throw new Exception("Invalid transactionDate Format (" + transactionDate + ")");

		String padded = strpad(klikPayCode, 10, '0', false) + strpad(transactionNo, 18, 'A', false) + strpad(currency, 5, '1', false)
				+ strpad(transactionDate, 19, 'C', true) + secretKey;
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(padded.getBytes());
		byte[] hbyte = md.digest();
		String md5 = toHexString(hbyte);
		return doAuthKey(md5, secretKey).toUpperCase();
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
	private static String strpad(String sstring,Integer len,char padder,boolean PadRight){
		if (sstring.length() < 1)
			return "";
		char[] c = sstring.toCharArray();
		if (c.length > len)
			return sstring;
		String ret = "";
		if (PadRight){
			int indexlen = len - c.length;
			for (int i = 0; i < len; i++)
				ret += i < indexlen ? padder : c[i - indexlen];
		}else
			for (int i = 0; i < len; i++)
				ret += i < c.length ? c[i] : padder;
		return ret;
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
		int keynum2 = getHash(secondval);
		BigDecimal hash = new BigDecimal(keynum1).add(new BigDecimal(keynum2)).abs();
		return hash + "";
	}

	/**
	 * To hex string.
	 * 
	 * @param b
	 *            the b
	 * @return the string
	 */
	private static String toHexString(byte b[]){
		if ((b == null) || (b.length == 0)){
			return "";
		}else{
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

	/**
	 * Gets the hash.
	 * 
	 * @param val
	 *            the val
	 * @return the hash
	 */
	private static int getHash(String val){
		int h = 0;
		char[] vals = val.toCharArray();
		for (int i = 0; i < vals.length; i++)
			h = (h * 31) + vals[i];
		return h;
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
		SymmetricType symmetricType = SymmetricType.DESede;
		SymmetricEncryption symmetricEncryption = new SymmetricEncryption(symmetricType, sKey);
		String encryptToHexString = symmetricEncryption.encryptHex(smd5);

		log.debug("the param encryptToHexString:{}", encryptToHexString);

		EnDeCipherDESTriple cipher = new EnDeCipherDESTriple(byteKey); // Class for 3DES ECB Encrypt
		byte[] byteEncryptedHashKey = cipher.encryptECB(byteHashKey);

		for (int a = 0; a < byteEncryptedHashKey.length; a++){
			String hex1 = Integer.toHexString(0xFF & byteEncryptedHashKey[a]);
			if (hex1.length() == 1)
				hexStringencryptedHashKey.append('0');

			hexStringencryptedHashKey.append(hex1);
		}
		return hexStringencryptedHashKey.toString();
	}
}
