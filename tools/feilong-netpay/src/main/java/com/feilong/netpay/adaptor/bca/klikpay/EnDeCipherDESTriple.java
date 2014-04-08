package com.feilong.netpay.adaptor.bca.klikpay;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * (这是BCA提供的代码)
 * 
 * @author Brian
 */
final class EnDeCipherDESTriple{

	private SecretKey	desKey;

	public EnDeCipherDESTriple(byte[] desKeyData) throws Exception{
		if (!(desKeyData.length == 16 || desKeyData.length == 24)){
			throw new Exception("Length not valid :" + desKeyData.length);
		}
		byte[] key = new byte[24];
		if (desKeyData.length == 16){
			System.arraycopy(desKeyData, 0, key, 0, 16);
			System.arraycopy(desKeyData, 0, key, 16, 8);
		}
		if (desKeyData.length == 24){
			System.arraycopy(desKeyData, 0, key, 0, 24);
		}

		DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		desKey = keyFactory.generateSecret(desKeySpec);
	}

	public byte[] encryptECB(byte[] cleartext) throws Exception{
		Cipher desCipher = Cipher.getInstance("DESede/ECB/NoPadding");
		desCipher.init(Cipher.ENCRYPT_MODE, desKey);
		byte[] ciphertext = desCipher.doFinal(cleartext);
		return ciphertext;
	}
}
