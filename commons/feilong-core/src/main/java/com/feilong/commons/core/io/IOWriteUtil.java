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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.Validator;

/**
 * 提供写文件操作
 * <p>
 * 
 * <ul>
 * <li>{@link #write(InputStream, OutputStream)} 写资源,速度最快的方法,速度比较请看 电脑资料 <<压缩解压性能探究>></li>
 * <li>{@link #write(String, String)} 将字符串写到文件中</li>
 * <li>{@link #write(InputStream, String, String)} 将inputStream 写到 某个文件夹,名字为fileName</li>
 * <li>{@link #write(String, String, String)} 将字符串/文字写到文件中</li>
 * <li>{@link #write(String, String, String, FileWriteMode)} 将字符串写到文件中</li>
 * </ul>
 * 
 * 如果需要覆盖写文件,可以调用 {@link #write(String, String, String, FileWriteMode)}.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:23:23 PM
 * @since 1.0.0
 */
public final class IOWriteUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(IOWriteUtil.class);

	/**
	 * 将inputStream 写到 某个文件夹,名字为fileName
	 * <p>
	 * 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建(支持级联创建 文件夹)
	 * 
	 * @param inputStream
	 *            上传得文件流
	 * @param directoryName
	 *            文件夹路径 最后不带"/"
	 * @param fileName
	 *            文件名称
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see com.feilong.commons.core.io.IOWriteUtil#write(InputStream, OutputStream)
	 */
	public static void write(InputStream inputStream,String directoryName,String fileName) throws IOException{
		String fileAllName = directoryName + "/" + fileName;
		// 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建
		File file = new File(fileAllName);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()){
			fileParent.mkdirs();
		}
		OutputStream outputStream = new FileOutputStream(fileAllName);
		write(inputStream, outputStream);
	}

	/**
	 * 写资源,速度最快的方法,速度比较请看 电脑资料 {@code  <<压缩解压性能探究>>}.
	 * 
	 * @param inputStream
	 *            inputStream
	 * @param outputStream
	 *            outputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	public static void write(InputStream inputStream,OutputStream outputStream) throws IOException{
		byte[] bytes = new byte[10240];
		int j;
		while ((j = inputStream.read(bytes)) != -1){
			outputStream.write(bytes, 0, j);
		}
		// 用完关闭流 是个好习惯,^_^
		inputStream.close();
	}

	// *******************************************************************************************

	/**
	 * 将字符串写到文件中
	 * 
	 * <p>
	 * <ul>
	 * <li>如果文件不存在,自动创建;包括其父文件夹(级联创建文件夹)</li>
	 * <li>如果文件存在,则覆盖旧文件 ,默认 以覆盖的模式 {@link FileWriteMode#COVER}内容.</li>
	 * <li>如果不设置encode,则默认使用 {@link CharsetType#GBK}编码</li>
	 * </ul>
	 * </p>
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            字符串内容
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>如果filePath文件存在,且isDirectory</li>
	 *             <li>如果filePath文件存在,且是!canWrite</li>
	 *             </ul>
	 * @see FileWriteMode
	 * @see CharsetType
	 */
	public static void write(String filePath,String content) throws IOException,IllegalArgumentException{
		write(filePath, content, null);
	}

	/**
	 * 将字符串/文字写到文件中.
	 * 
	 * <ul>
	 * <li>如果文件不存在,自动创建;包括其父文件夹(级联创建文件夹)</li>
	 * <li>如果文件存在,则覆盖旧文件 ,默认 以覆盖的模式 {@link FileWriteMode#COVER}内容.</li>
	 * </ul>
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            字符串内容
	 * @param encode
	 *            编码,如果isNullOrEmpty,则默认使用 {@link CharsetType#GBK}编码 {@link CharsetType}
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>如果filePath文件存在,且isDirectory</li>
	 *             <li>如果filePath文件存在,且是!canWrite</li>
	 *             </ul>
	 * @see FileWriteMode
	 * @see CharsetType
	 * @see #write(String, String, String, FileWriteMode)
	 */
	public static void write(String filePath,String content,String encode) throws IOException,IllegalArgumentException{
		write(filePath, content, encode, FileWriteMode.COVER);
	}

	/**
	 * 将字符串写到文件中.
	 * 
	 * <ul>
	 * <li>如果文件不存在,自动创建,包括其父文件夹 (支持级联创建 文件夹)</li>
	 * <li>如果文件存在则覆盖旧文件,可以通过 指定 {@link FileWriteMode#APPEND}来表示追加内容而非覆盖</li>
	 * </ul>
	 * 
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            字符串内容
	 * @param encode
	 *            编码,如果isNullOrEmpty,则默认使用 {@link CharsetType#GBK}编码 {@link CharsetType}
	 * @param fileWriteMode
	 *            写模式
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException
	 *             <ul>
	 *             <li>如果filePath文件存在,且isDirectory</li>
	 *             <li>如果filePath文件存在,且是!canWrite</li>
	 *             </ul>
	 * @see FileWriteMode
	 * @see CharsetType
	 */
	public static void write(String filePath,String content,String encode,FileWriteMode fileWriteMode) throws IOException,
			IllegalArgumentException{

		if (Validator.isNullOrEmpty(encode)){
			encode = CharsetType.GBK;
		}

		// **************************************************************************8
		File file = FileUtil.cascadeMkdirs(filePath);

		boolean append = (fileWriteMode == FileWriteMode.APPEND);

		OutputStream outputStream = new FileOutputStream(file, append);
		Writer outputStreamWriter = new OutputStreamWriter(outputStream, encode);

		Writer writer = new PrintWriter(outputStreamWriter);
		writer.write(content);
		writer.close();

		if (log.isInfoEnabled()){
			log.info(
					"fileWriteMode:[{}],contentLength:[{}],encode:[{}],fileSize:[{}],absolutePath:[{}]",
					fileWriteMode,
					content.length(),
					encode,
					FileUtil.getFileFormatSize(file),
					file.getAbsolutePath());
		}
	}

}