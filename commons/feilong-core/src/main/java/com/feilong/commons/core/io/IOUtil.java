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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 流之间的转换.
 * 
 * @author 金鑫 2009-10-26下午02:36:47
 * @since 1.0.0
 */
public final class IOUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(IOUtil.class);

	/** Don't let anyone instantiate this class. */
	private IOUtil(){}

	/**
	 * 将 InputStream 转成string(该方法会将 inputStream 关闭)<br>
	 * 使用 Charset.defaultCharset()
	 * 
	 * @param inputStream
	 *            the input stream
	 * @return 将 InputStream 转成string，如果出现异常， 返回null<br>
	 *         已经处理了 inputStream 的关闭
	 * @since 1.0.2
	 */
	public static String inputStream2String(InputStream inputStream){
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
	 * @since 1.0.2
	 */
	public static String inputStream2String(InputStream inputStream,String charsetName){
		try{
			// bufferedReader 缓冲 高效读取 ;
			// 包装所有其 read() 操作可能开销很高的 Reader（如 FileReader 和 InputStreamReader）。
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charsetName));
			StringBuffer stringBuffer = new StringBuffer();
			String line = "";

			// 读取一个文本行。通过下列字符之一即可认为某行已终止：换行 ('\n')、回车 ('\r') 或回车后直接跟着换行。
			while ((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line);
			}
			return stringBuffer.toString();
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				inputStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 将网络文件 下载到文件夹<br>
	 * 取到网络文件的文件名 原样下载到目标文件夹.
	 * 
	 * @param url
	 *            网络任意文件<br>
	 *            url 不能带参数
	 * @param directoryName
	 *            目标文件夹
	 */
	public static void down(String url,String directoryName){
		log.info("begin down:" + url);
		try{
			URL _url = new URL(url);
			InputStream inputStream = _url.openStream();
			File file = new File(url);
			String fileName = file.getName();
			IOWriteUtil.write(inputStream, directoryName, fileName);
		}catch (Exception e){
			log.error("down url:" + url + " error,exception is " + e.getMessage());
		}
	}

	/**
	 * 将文件转成ByteArray.
	 * 
	 * @param file
	 *            file
	 * @return byteArrayOutputStream.toByteArray();
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static byte[] convertFileToByteArray(File file) throws IOException{
		InputStream inputStream = getFileInputStream(file);
		// *************************************************************************
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[10240];
		byte[] byteArray = null;
		int j;
		try{
			while ((j = bufferedInputStream.read(bytes)) != -1){
				byteArrayOutputStream.write(bytes, 0, j);
			}
			byteArrayOutputStream.flush();
			byteArray = byteArrayOutputStream.toByteArray();
		}catch (IOException e){
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{
			// 为避免内存泄漏，Stream的Close是必须的。即使中途发生了异常，也必须Close，
			// 因此请在finally Block中描述close()
			try{
				byteArrayOutputStream.close();
				bufferedInputStream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
		return byteArray;
	}

	/**
	 * 获得FileOutputStream文件输出流 FileOutputStream（或其他文件写入对象）打开文件进行写入 <br>
	 * FileOutputStream 用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用 FileWriter。.
	 * 
	 * @param fileName
	 *            文件名称
	 * @return FileOutputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static FileOutputStream getFileOutputStream(String fileName) throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream(fileName);
		return fileOutputStream;
	}

	/**
	 * FileInputStream 从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。<br>
	 * FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。.
	 * 
	 * @param fileName
	 *            该文件通过文件系统中的路径名 fileName 指定。
	 * @return FileInputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static FileInputStream getFileInputStream(String fileName) throws IOException{
		File file = new File(fileName);
		return getFileInputStream(file);
	}

	/**
	 * FileInputStream 从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。<br>
	 * FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。.
	 * 
	 * @param file
	 *            为了进行读取而打开的文件。
	 * @return FileInputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public final static FileInputStream getFileInputStream(File file) throws IOException{
		// 如果指定文件不存在，或者它是一个目录，而不是一个常规文件，抑或因为其他某些原因而无法打开进行读取，则抛出 FileNotFoundException。
		FileInputStream fileInputStream = new FileInputStream(file);
		return fileInputStream;
	}
}
