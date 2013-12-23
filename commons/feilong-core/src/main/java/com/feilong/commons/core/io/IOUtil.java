/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一些流之间的转换
 * 
 * @author 金鑫 2009-10-26下午02:36:47
 */
public final class IOUtil{

	private final static Logger	log	= LoggerFactory.getLogger(IOUtil.class);

	/** Don't let anyone instantiate this class. */
	private IOUtil(){}

	/**
	 * 将 InputStream 转成string(该方法会将 inputStream 关闭)<br>
	 * 使用 Charset.defaultCharset()
	 * 
	 * @param inputStream
	 * @return 将 InputStream 转成string，如果出现异常， 返回null<br/>
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
	 * 读取cmd命令结果时候， 有时候读取的是乱码，需要传递charset字符集
	 * 
	 * @param inputStream
	 * @param charsetName
	 *            指定受支持的 charset 的名称
	 * @return 将 InputStream 转成string，如果出现异常， 返回null<br/>
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

	// ***********************************************************************
	//
	// 写文件
	//
	// *******************************************************************
	/**
	 * 将网络文件 下载到文件夹<br>
	 * 取到网络文件的文件名 原样下载到目标文件夹<br>
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
	 * 将文件转成ByteArray
	 * 
	 * @param file
	 *            file
	 * @return <pre>
	 * return byteArrayOutputStream.toByteArray();
	 * </pre>
	 */
	public final static byte[] convertFileToByteArray(File file){
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
	 * FileOutputStream 用于写入诸如图像数据之类的原始字节的流。要写入字符流，请考虑使用 FileWriter。
	 * 
	 * @param fileName
	 *            文件名称
	 * @return FileOutputStream
	 */
	public final static FileOutputStream getFileOutputStream(String fileName){
		FileOutputStream fileOutputStream = null;
		try{
			fileOutputStream = new FileOutputStream(fileName);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return fileOutputStream;
	}

	/**
	 * FileInputStream 从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。<br>
	 * FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。
	 * 
	 * @param fileName
	 *            该文件通过文件系统中的路径名 fileName 指定。
	 * @return FileInputStream
	 */
	public final static FileInputStream getFileInputStream(String fileName){
		File file = new File(fileName);
		return getFileInputStream(file);
	}

	/**
	 * FileInputStream 从文件系统中的某个文件中获得输入字节。哪些文件可用取决于主机环境。<br>
	 * FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。
	 * 
	 * @param file
	 *            为了进行读取而打开的文件。
	 * @return FileInputStream
	 */
	public final static FileInputStream getFileInputStream(File file){
		FileInputStream fileInputStream = null;
		try{
			fileInputStream = new FileInputStream(file);
		}catch (FileNotFoundException e){
			// 如果指定文件不存在，或者它是一个目录，而不是一个常规文件，抑或因为其他某些原因而无法打开进行读取，则抛出
			// FileNotFoundException。
			log.error(file + "找不到," + e.getMessage());
		}
		return fileInputStream;
	}
}
