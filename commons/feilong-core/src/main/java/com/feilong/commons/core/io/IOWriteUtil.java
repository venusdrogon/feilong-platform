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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.FileWriteMode;
import com.feilong.commons.core.util.Validator;

/**
 * 写文件<br>
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
	 * 将inputStream 写到 某个文件夹,名字为fileName <br>
	 * 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建(支持级联创建 文件夹)
	 * 
	 * @param inputStream
	 *            上传得文件流
	 * @param directoryName
	 *            文件夹路径 最后不带"/"
	 * @param fileName
	 *            文件名称
	 * @return 是否成功
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean write(InputStream inputStream,String directoryName,String fileName) throws IOException{
		String fileAllName = directoryName + "/" + fileName;
		// 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建
		File file = new File(fileAllName);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()){
			fileParent.mkdirs();
		}
		OutputStream outputStream = new FileOutputStream(fileAllName);
		write(inputStream, outputStream);
		return true;
	}

	/**
	 * 写资源,速度最快的方法,速度比较请看 电脑资料<<压缩解压性能探究>>.
	 * 
	 * @param inputStream
	 *            inputStream
	 * @param outputStream
	 *            outputStream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
	 * 将字符串写到文件中,如果文件存在则覆盖旧文件,<br>
	 * 默认采用GBK 编码 (支持级联创建 文件夹)<br>
	 * .
	 * 
	 * @param filePath
	 *            文件路径
	 *            <ul>
	 *            <li>如果文件不存在,自动创建,包括其父文件夹 (支持级联创建 文件夹)</li>
	 *            <li>如果文件存在, 如果文件是isDirectory ,throw new IllegalArgumentException</li>
	 *            <li>如果文件存在, 如果文件是!canWrite ,throw new IllegalArgumentException</li>
	 *            </ul>
	 * @param content
	 *            字符串内容
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void write(String filePath,String content) throws IOException{
		write(filePath, content, null);
	}

	/**
	 * 将字符串写到文件中,如果文件存在则覆盖旧文件 (支持级联创建 文件夹),默认 以覆盖的模式 write内容.
	 * 
	 * @param filePath
	 *            文件路径
	 *            <ul>
	 *            <li>如果文件不存在,自动创建,包括其父文件夹 (支持级联创建 文件夹)</li>
	 *            <li>如果文件存在, 如果文件是isDirectory ,throw new IllegalArgumentException</li>
	 *            <li>如果文件存在, 如果文件是!canWrite ,throw new IllegalArgumentException</li>
	 *            </ul>
	 * @param content
	 *            字符串内容
	 * @param encode
	 *            编码,如果isNullOrEmpty,则默认使用 GBK编码
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void write(String filePath,String content,String encode) throws IOException{
		write(filePath, content, encode, FileWriteMode.COVER);
	}

	/**
	 * 将字符串写到文件中,如果文件存在则覆盖旧文件 (支持级联创建 文件夹),可以通过 指定fileWriteMode.append 来表示追加内容而非覆盖
	 * 
	 * @param filePath
	 *            文件路径
	 *            <ul>
	 *            <li>如果文件不存在,自动创建,包括其父文件夹 (支持级联创建 文件夹)</li>
	 *            <li>如果文件存在, 如果文件是isDirectory ,throw new IllegalArgumentException</li>
	 *            <li>如果文件存在, 如果文件是!canWrite ,throw new IllegalArgumentException</li>
	 *            </ul>
	 * @param content
	 *            字符串内容
	 * @param encode
	 *            编码,如果isNullOrEmpty,则默认使用 GBK编码
	 * @param fileWriteMode
	 *            写模式
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void write(String filePath,String content,String encode,FileWriteMode fileWriteMode) throws IOException{
		if (Validator.isNullOrEmpty(encode)){
			encode = CharsetType.GBK;
		}

		Object[] logArgs = { filePath, encode, fileWriteMode };
		log.debug("begin write: {},use encode : {},fileWriteMode:{}", logArgs);

		// **************************************************************************8
		File file = new File(filePath);
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
		_write(filePath, content, encode, fileWriteMode);
	}

	/**
	 * 将字符串写到文件中,该方法一般给上面{@link #write(String, String, String)}调用.
	 * 
	 * @param filePath
	 *            文件路径 ,必须是存在的路径,且不是文件夹,且不是只读属性
	 * @param content
	 *            内容
	 * @param encode
	 *            编码
	 * @param fileWriteMode
	 *            write 模式
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void _write(String filePath,String content,String encode,FileWriteMode fileWriteMode) throws IOException{
		// 向文本输出流打印对象的格式化表示形式
		// 会自动创建文件,替换覆盖文字(非追加)

		// XXX \ / : * ? " < > |
		// 而且这些符号好像都是英文状态下的,换成中文状态下的就可以
		// String[] specialChars = { "*", "?" };
		filePath = filePath.replace("?", "");
		File file = new File(filePath);

		boolean append = (fileWriteMode == FileWriteMode.APPEND);
		FileOutputStream fileOutputStream = new FileOutputStream(file, append);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encode);
		PrintWriter printWriter = new PrintWriter(outputStreamWriter);
		printWriter.write(content);
		printWriter.close();

		if (log.isInfoEnabled()){
			Object[] params = { fileWriteMode, content.length(), FileUtil.formatSize(FileUtil.getFileSize(file)), file.getAbsolutePath() };
			log.info("fileWriteMode:[{}],contentLength:[{}],fileSize:[{}],absolutePath:[{}]", params);
		}
	}
}