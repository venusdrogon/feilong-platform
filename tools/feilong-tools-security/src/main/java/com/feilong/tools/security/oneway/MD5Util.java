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
package com.feilong.tools.security.oneway;

import com.feilong.commons.core.io.CharsetType;
import com.feilong.tools.security.EncryptionException;

/**
 * Message Digest algorithm 5，信息摘要算法 <br>
 * 将任意长度的"字节串"变换成一个128bit的大整数.
 * 
 * <pre>
 * 检验你的实现是否正确：
 * MD5Util.encode(&quot;&quot;) = d41d8cd98f00b204e9800998ecf8427e
 * MD5Util.encode(&quot;a&quot;) = 0cc175b9c0f1b6a831c399e269772661
 * MD5Util.encode(&quot;abc&quot;) = 900150983cd24fb0d6963f7d28e17f72
 * MD5Util.encode(&quot;message digest&quot;) = f96b697d7cb7938d525a2f31aaf161d0
 * MD5Util.encode(&quot;abcdefghijklmnopqrstuvwxyz&quot;) = c3fcd3d76192e4007dfb496cca67e13b
 * </pre>
 * 
 * @author 腾讯通
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0.0 2010年10月26日 17:13:58
 * @version 1.0.1 2011-10-18 16:49
 * @version 1.0.7 2014-7-10 14:28 update javadoc and remove extends
 * @see <a href="http://www.cmd5.com/">MD5解密网站</a>
 * @see com.feilong.tools.security.oneway.OnewayEncryption
 * @see com.feilong.tools.security.oneway.OnewayType
 * @see org.apache.commons.codec.digest.Md5Crypt#md5Crypt(byte[])
 * @see "org.springframework.util.DigestUtils"
 * @since 1.0.0
 */
public final class MD5Util{

    /**
     * Instantiates a new m d5 util.
     */
    private MD5Util(){
    }

    /** The oneway type. */
    private static OnewayType onewayType = OnewayType.MD5;

    /**
     * 使用算法 单向加密字符串.
     * 
     * @param origin
     *            原始字符串,将使用默认的 {@link String#getBytes()} 转成字节数组<br>
     * @return 加密之后的转成小写的16进制字符串
     * @throws EncryptionException
     *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
     * @see OnewayEncryption#encode(OnewayType, String)
     */
    public static String encode(String origin) throws EncryptionException{
        return OnewayEncryption.encode(onewayType, origin);
    }

    /**
     * 使用算法 单向加密字符串.
     * 
     * @param origin
     *            原始字符串,将使用默认的 value.getBytes() 转成字节数组<br>
     *            如果需要string 转码,请自行调用value.getBytes(string chartsetname),再调用{@link #encode(String, String)}
     * @param charsetName
     *            受支持的 {@link CharsetType} 名称,比如 utf-8
     * @return 加密之后的转成小写的16进制字符串
     * @throws EncryptionException
     *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
     * @see CharsetType
     * @see OnewayEncryption#encode(OnewayType, String, String)
     */
    public static String encode(String origin,String charsetName) throws EncryptionException{
        return OnewayEncryption.encode(onewayType, origin, charsetName);
    }

    /**
     * 计算文件的单向加密值.
     * 
     * @param filePath
     *            文件路径 {@link java.io.File#File(String)}
     * @return the string
     * @throws EncryptionException
     *             如果在加密解密的过程中发生了异常,会以EncryptionException形式抛出
     * @see OnewayEncryption#encodeFile(OnewayType, String)
     */
    public static String encodeFile(String filePath) throws EncryptionException{
        return OnewayEncryption.encodeFile(onewayType, filePath);
    }
}