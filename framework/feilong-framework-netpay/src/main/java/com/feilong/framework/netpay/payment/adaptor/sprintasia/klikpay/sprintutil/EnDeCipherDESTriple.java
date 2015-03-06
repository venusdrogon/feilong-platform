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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * (这是BCA提供的代码).
 *
 * @author Brian
 */
final class EnDeCipherDESTriple{

    /** The des key. */
    private SecretKey desKey;

    // private static final String HEX_DIGITS = "0123456789ABCDEF";

    /**
     * The Constructor.
     *
     * @param key
     *            the key
     * @throws Exception
     *             the exception
     */
    public EnDeCipherDESTriple(byte[] key) throws Exception{
        createCipher(key);
    }

    /**
     * Creates the cipher.
     *
     * @param desKeyData
     *            the des key data
     * @throws Exception
     *             the exception
     */
    public final void createCipher(byte[] desKeyData) throws Exception{
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

        // byte[] key = desKeyData;
        DESedeKeySpec desKeySpec = new DESedeKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        desKey = keyFactory.generateSecret(desKeySpec);
    }

    /**
     * Encrypt ecb.
     *
     * @param cleartext
     *            the cleartext
     * @return the byte[]
     */
    public byte[] encryptECB(byte[] cleartext){
        byte[] ciphertext = null;
        try{
            Cipher desCipher;
            desCipher = Cipher.getInstance("DESede/ECB/NoPadding");
            desCipher.init(Cipher.ENCRYPT_MODE, desKey);
            ciphertext = desCipher.doFinal(cleartext);
        }catch (NoSuchAlgorithmException ex1){}catch (InvalidKeyException ex2){}catch (NoSuchPaddingException ex3){}catch (BadPaddingException ex4){}catch (IllegalBlockSizeException ex5){}catch (IllegalStateException ex6){}

        return ciphertext;
    }
}
