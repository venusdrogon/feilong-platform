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
package com.feilong.netpay.adaptor.bca.klikpay.util;

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
