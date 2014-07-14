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
		File file = formatFile(filePath);

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

	/**
	 * 不同的操作系统 对系统文件名称有要求,此方法的作用就是处理这些文件名称.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the file
	 * @throws IllegalArgumentException
	 *             if Validator.isNullOrEmpty(filePath)
	 * 
	 * @see #getFormatFilePath(String)
	 * @since 1.0.7
	 */
	private static final File formatFile(final String filePath) throws IllegalArgumentException{
		if (Validator.isNullOrEmpty(filePath)){
			throw new IllegalArgumentException("filePath can't be null/empty!");
		}

		String formatFilePath = getFormatFilePath(filePath);

		File file = new File(formatFilePath);

		if (file.exists()){
			// 文件夹
			if (file.isDirectory()){
				log.error("File '" + file + "' exists but is a directory");
				throw new IllegalArgumentException("File '" + file + "' exists but is a directory");
			}
			// 不能写
			else if (!file.canWrite()){
				log.error("File '" + file + "' cannot be written to");
				throw new IllegalArgumentException("File '" + file + "' cannot be written to");
			}
		}
		// 文件不存在
		else{
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()){
				// 级联创建 父级文件夹
				if (parent.mkdirs() == false){
					log.error("File '" + file + "' could not be created");
					throw new IllegalArgumentException("File '" + file + "' could not be created");
				}
			}
		}
		return file;
	}

	/**
	 * 不同的操作系统 对系统文件名称有要求,此方法的作用就是处理这些文件名称.
	 * 
	 * @param filePath
	 *            the file path
	 * @return 可用的文件名称
	 * @see #MICROSOFT_PC
	 * @since 1.0.7
	 */
	private static String getFormatFilePath(final String filePath){
		String formatFilePath = filePath;

		for (int i = 0, j = MICROSOFT_PC.length; i < j; ++i){
			String[] array_element = MICROSOFT_PC[i];

			String oldChar = array_element[0];
			String newChar = array_element[1];
			if (formatFilePath.contains(oldChar)){
				log.warn("formatFilePath:[{}] contains oldChar:[{}],will replace newChar:[{}]", formatFilePath, oldChar, newChar);
				formatFilePath = formatFilePath.replace(oldChar, newChar);
			}
		}
		return formatFilePath;
	}

	/**
	 * 文件名称由文件名和扩展名组成，两者由小黑点分隔，扩展名通常是用来表示文件的类 别。
	 * <p>
	 * Windows 中整个文件名称最长 255 个字符（一个中文字算两个字符）； <br>
	 * DOS 中，文件名最长 8 字符，扩展名最长 3 字符，故又称 DOS 8.3 命名规则。 <br>
	 * 文件名称可仅有前半部,即无扩展名，如文件名称最短可以是 “ 1 ” 、 “ C ” 等。 <br>
	 * 给文件命名还应注意以下规则：
	 * </p>
	 * <ul>
	 * <li>文件名不能包含下列任何字符之一（共 9 个）： \/:*?"<>|</li>
	 * <li>不能单独使用 “ 设备名 ” 作文件名。 “ 设备名 ” 包括： con ， aux ， com0 ~ com9 ， lpt0 ~ lpt9 ， nul ， prn</li>
	 * <li>文件名不区分大小写，如 A.txt 和 a.TxT 表示同一文件</li>
	 * </ul>
	 * 
	 * @see <a href="http://support.microsoft.com/kb/177506/zh-cn">错误消息： 文件名是无效的或不能包含任何以下字符</a>
	 * @since 1.0.7
	 */
	private static final String[][]	MICROSOFT_PC	= { //
													//			{ "\\", "" }, // \
			//	{ "/", "" }, // /
			{ "\"", "" }, // "
			{ ":", "" }, // :
			{ "*", "" }, // *
			{ "?", "" }, // ?
			{ "<", "" }, // <
			{ ">", "" }, // >
			{ "|", "" }, // |
													};
}