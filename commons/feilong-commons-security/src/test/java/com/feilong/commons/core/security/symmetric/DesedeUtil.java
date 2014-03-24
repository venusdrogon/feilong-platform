package com.feilong.commons.core.security.symmetric;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DesedeUtil{

	public static final String	MODEL_ECB				= "ECB";

	public static final String	PADDING_NOPADDING		= "NoPadding";

	public static final String	PADDING_PKCS5PADDING	= "PKCS5Padding";

	private static final String	DESEDE					= "DESede";

	// Input  F7468B69D12BB6CE76D6206419A6AC28
	// Encrypt by KeyId  12345678901234561234567890123456
	// authKey will become  BF81501C562D6FEA2FCB905D392D5851

	public static String Desede(String _key,String _data,String _model,String _padding,int length){

		byte[] key = getKey(_key);
		byte[] data = getData(_data);
		return Encrypt(key, data, _model, _padding, length);

	}

	private static byte[] getKey(String key){

		if (key == null || key.length() == 0){
			throw new NullPointerException("key is null");
		}

		byte keyByte[] = hexToBinary(key);

		if (keyByte.length == 16){
			byte bytes[] = new byte[24];
			System.arraycopy(keyByte, 0, bytes, 0, 16);
			System.arraycopy(keyByte, 0, bytes, 16, 8);
			return bytes;
		}else if (keyByte.length == 24){
			byte bytes[] = new byte[24];
			System.arraycopy(keyByte, 0, bytes, 0, 24);
			return bytes;
		}else{
			throw new RuntimeException("key length is not 16 or 24");
		}
	}

	private static byte[] getData(String data){

		if (data == null || data.length() == 0){
			throw new NullPointerException("data is null");
		}

		// if (data.length() != 16) {
		// throw new NullPointerException("data length is not 8");
		// }

		return hexToBinary(data);

	}

	private static String Encrypt(byte[] key,byte[] data,String model,String padding,int length){

		try{
			Cipher cipher = null;
			if (model == null || model.length() == 0 || padding == null || padding.length() == 0){
				cipher = Cipher.getInstance(DESEDE);
			}else{
				cipher = Cipher.getInstance(DESEDE + "/" + model + "/" + padding);
			}
			SecretKey secretKey = new SecretKeySpec(key, DESEDE);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return binaryToHex(length, cipher.doFinal(data));
		}catch (Throwable e){
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] hexToBinary(String hex){

		String string = "0123456789ABCDEF";

		char[] ch = hex.toCharArray();

		int len = ch.length / 2;

		byte byts[] = new byte[len];

		for (int i = 0; i < len; i++){

			byts[i] = (byte) ((((byte) string.indexOf(Character.toUpperCase(ch[i * 2])) & 0xFF) << 4)

			| ((byte) string.indexOf(Character.toUpperCase(ch[i * 2 + 1])) & 0xFF));

		}
		return byts;

	}

	public static String binaryToHex(int length,byte...byts){

		String str = "0123456789ABCDEF";

		char[] chars = new char[byts.length * 2];

		for (int i = 0; i < length; i++){

			int position = i * 2;

			// 高四位

			chars[position] = str.charAt((byts[i] & 0xF0) >>> 4);

			// 低四位

			chars[position + 1] = str.charAt((byts[i] & 0x0F));

		}

		return new String(chars);

	}

	public static byte[] reverseByte(byte...byts){

		for (int i = 0, j = byts.length; i < j; i++){
			byts[i] = (byte) (~byts[i] & 0xFF);
		}
		return byts;
	}

}
