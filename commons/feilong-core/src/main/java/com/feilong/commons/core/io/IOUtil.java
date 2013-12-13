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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.Validator;

/**
 * 输入输出,比如文字读写
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

	/***************************************************************************/
	//
	// 读取文件内容
	//
	/***************************************************************************/
	/**
	 * 读取文件内容
	 * 
	 * @param path
	 *            路径
	 * @return 文件内容string
	 */
	public static String getFileContent(String path){
		File file = new File(path);
		return getFileContent(file);
	}

	/**
	 * 读取文件内容
	 * 
	 * @param file
	 *            文件
	 * @return 文件内容string
	 */
	private static String getFileContent(File file){
		return getFileContent(file, CharsetType.GBK);
	}

	/**
	 * 读取文件内容
	 * 
	 * @param file
	 *            文件
	 * @param charsetName
	 *            字符编码
	 * @return
	 */
	public static String getFileContent(File file,String charsetName){
		// 分配新的直接字节缓冲区
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(186140);
		StringBuffer stringBuffer = new StringBuffer(186140);
		try{
			FileInputStream fileInputStream = new FileInputStream(file);
			// 用于读取、写入、映射和操作文件的通道。
			FileChannel fileChannel = fileInputStream.getChannel();
			// 编码字符集和字符编码方案的组合,用于处理中文,可以更改
			Charset charset = Charset.forName(charsetName);
			while (fileChannel.read(byteBuffer) != -1){
				// 反转此缓冲区
				byteBuffer.flip();
				CharBuffer charBuffer = charset.decode(byteBuffer);
				stringBuffer.append(charBuffer.toString());
				byteBuffer.clear();
			}
			fileInputStream.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		return stringBuffer.toString();
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
			IOUtil.write(inputStream, directoryName, fileName);
		}catch (Exception e){
			log.error("down url:" + url + " error,exception is " + e.getMessage());
		}
	}

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
	 */
	public static boolean write(InputStream inputStream,String directoryName,String fileName){
		String fileAllName = directoryName + "/" + fileName;
		// 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建
		File file = new File(fileAllName);
		File fileParent = file.getParentFile();
		if (!fileParent.exists()){
			fileParent.mkdirs();
		}
		try{
			OutputStream outputStream = new FileOutputStream(fileAllName);
			write(inputStream, outputStream);
			return true;
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 写资源,速度最快的方法,速度比较请看 电脑资料<<压缩解压性能探究>>
	 * 
	 * @param inputStream
	 *            inputStream
	 * @param outputStream
	 *            outputStream
	 */
	public static void write(InputStream inputStream,OutputStream outputStream){
		byte[] bytes = new byte[10240];
		int j;
		try{
			while ((j = inputStream.read(bytes)) != -1){
				outputStream.write(bytes, 0, j);
			}
			// 用完关闭流 是个好习惯,^_^
			inputStream.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 将字符串写到文件中,如果文件存在则覆盖旧文件,<br>
	 * 默认采用GBK 编码 (支持级联创建 文件夹)
	 * 
	 * @param filePath
	 *            文件路径,如果文件不存在,自动生成,包括其父文件夹(支持级联创建 文件夹)
	 * @param content
	 *            字符串内容
	 */
	public static void write(String filePath,String content){
		write(filePath, content, null);
	}

	/**
	 * 将字符串写到文件中,如果文件存在则覆盖旧文件 (支持级联创建 文件夹)
	 * 
	 * @param filePath
	 *            文件路径,如果文件不存在,自动生成,包括其父文件夹 (支持级联创建 文件夹)
	 * @param content
	 *            字符串内容
	 * @param encode
	 *            编码
	 */
	public static void write(String filePath,String content,String encode){
		if (Validator.isNullOrEmpty(encode)){
			encode = CharsetType.GBK;
		}
		log.debug("begin write {},use encode : {}", filePath, encode);
		File file = new File(filePath);
		if (file.exists()){
			if (file.isDirectory()){
				log.error("File '" + file + "' exists but is a directory");
			}else if (file.canWrite() == false){
				log.error("File '" + file + "' cannot be written to");
			}else{
				_write(filePath, content, encode);
			}
		}else{
			File parent = file.getParentFile();
			if (parent != null && !parent.exists()){
				if (parent.mkdirs() == false){
					log.error("File '" + file + "' could not be created");
				}
			}
			_write(filePath, content, encode);
		}
	}

	/**
	 * 将字符串写到文件中,该方法一般给上面{@link #write(String, String, String)}调用
	 * 
	 * @param filePath
	 *            文件路径 ,必须是存在的路径,且不是文件夹,且不是只读属性
	 * @param content
	 *            内容
	 * @param encode
	 *            编码
	 */
	private static void _write(String filePath,String content,String encode){
		// 向文本输出流打印对象的格式化表示形式
		// 会自动创建文件,替换覆盖文字(非追加)
		try{
			// \ / : * ? " < > |
			// 而且这些符号好像都是英文状态下的,换成中文状态下的就可以
			String[] specialChars = { "*", "?" };
			filePath = filePath.replace("?", "");
			File file = new File(filePath);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, encode);
			PrintWriter printWriter = new PrintWriter(outputStreamWriter);
			printWriter.write(content);
			printWriter.close();

			if (log.isInfoEnabled()){
				Object[] params = { content.length(), FileUtil.formatFileSize(FileUtil.getFileSize(file)), file.getAbsolutePath() };
				log.info("contentLength:[{}],fileSize:[{}],write:[{}]", params);
			}
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (FileNotFoundException e){
			e.printStackTrace();
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
