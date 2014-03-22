package com.feilong.netpay.adaptor.bca.keygenerator;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Brian
 */
final class AuthKey{

	private static final String	HEX_DIGITS	= "0123456789ABCDEF";

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
				if (hex1.length() == 1)
					hexStringencryptedHashKey.append('0');

				hexStringencryptedHashKey.append(hex1);
			}
			return hexStringencryptedHashKey.toString();

		}catch (NoSuchAlgorithmException nsae){}
		return "";
	}

	public static String KeyId(String clearKey){
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] bytes = clearKey.getBytes();
		String keyId = "";
		for (int cntr = 0; cntr < clearKey.length(); cntr++){
			keyId = keyId + hexArray[(bytes[cntr] & 0xFF) / 16] + hexArray[(bytes[cntr] & 0xFF) % 16];
		}
		return keyId;
	}

	public static String doHash(String firstval,String secondval){
		int keynum1 = getHash(firstval);
		int keynum2 = getHash(secondval);
		BigDecimal hash = new BigDecimal(keynum1).add(new BigDecimal(keynum2)).abs();
		return hash + "";
	}

	private static int getHash(String val){
		int h = 0;
		char[] vals = val.toCharArray();
		for (int i = 0; i < vals.length; i++)
			h = (h * 31) + vals[i];
		return h;
	}

	public static String toHexString(byte b[]){
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
}
