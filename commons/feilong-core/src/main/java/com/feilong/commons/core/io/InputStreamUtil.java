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
package com.feilong.commons.core.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link java.io.InputStream} 工具类
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月6日 上午10:28:58
 * @see ReaderUtil
 * @since 1.0.9
 */
public final class InputStreamUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(InputStreamUtil.class);

    /** Don't let anyone instantiate this class. */
    private InputStreamUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 将 InputStream 转成string(该方法会将 inputStream 关闭)<br>
     * 使用 Charset.defaultCharset()
     *
     * @param inputStream
     *            the input stream
     * @return 将 InputStream 转成string，如果出现异常， 返回null<br>
     *         已经处理了 inputStream 的关闭
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see #inputStream2String(InputStream, String)
     */
    public static String inputStream2String(InputStream inputStream) throws UncheckedIOException{
        Charset defaultCharset = Charset.defaultCharset();
        String charsetName = defaultCharset.name();
        log.debug("the param defaultCharset:[{}]", charsetName);

        return inputStream2String(inputStream, charsetName);
    }

    /**
     * 将 InputStream 转成string(该方法会将 inputStream 关闭)<br>
     * 读取cmd命令结果时候， 有时候读取的是乱码，需要传递charset字符集.
     *
     * @param inputStream
     *            the input stream
     * @param charsetName
     *            指定受支持的 charset 的名称
     * @return 将 InputStream 转成string，如果出现异常， 返回null<br>
     *         已经处理了 inputStream 的关闭
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static String inputStream2String(InputStream inputStream,String charsetName) throws UncheckedIOException{
        BufferedReader bufferedReader = toBufferedReader(inputStream, charsetName);
        return ReaderUtil.toString(bufferedReader);
    }

    /**
     * 将inputStream 转成BufferedReader (bufferedReader 缓冲 高效读取).
     *
     * @param inputStream
     *            the input stream
     * @param charsetName
     *            the charset name
     * @return the buffered reader
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see java.io.BufferedReader
     */
    public static BufferedReader toBufferedReader(InputStream inputStream,String charsetName) throws UncheckedIOException{
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charsetName);

            // 缓冲 高效读取  bufferedReader 
            // 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和 InputStreamReader）.
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader;
        }catch (UnsupportedEncodingException e){
            throw new UncheckedIOException(e);
        }
    }
}