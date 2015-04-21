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
package com.feilong.tools.reference.ip;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Utils.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:26:52
 */
public class Utils{

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    /**
     * 从ip的字符串形式得到字节数组形式.
     * 
     * @param ip
     *            字符串形式的ip
     * @return 字节数组形式的ip
     */
    public static byte[] getIpByteArrayFromString(String ip){
        byte[] ret = new byte[4];
        StringTokenizer stringTokenizer = new StringTokenizer(ip, ".");
        try{
            ret[0] = (byte) (Integer.parseInt(stringTokenizer.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(stringTokenizer.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(stringTokenizer.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(stringTokenizer.nextToken()) & 0xFF);
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }
        return ret;
    }

    /**
     * 根据某种编码方式将字节数组转换成字符串.
     * 
     * @param b
     *            字节数组
     * @param offset
     *            要转换的起始位置
     * @param len
     *            要转换的长度
     * @param encoding
     *            编码方式
     * @return 如果encoding不支持，返回一个缺省编码的字符串
     */
    public static String getString(byte[] b,int offset,int len,String encoding){
        try{
            return new String(b, offset, len, encoding);
        }catch (UnsupportedEncodingException e){
            return new String(b, offset, len);
        }
    }

    /**
     * 字符串形式的ip.
     * 
     * @param ip
     *            ip的字节数组形式
     * @return 字符串形式的ip
     */
    public static String getIpStringFromBytes(byte[] ip){
        StringBuilder sb = new StringBuilder();
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }
}
