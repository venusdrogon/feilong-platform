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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * 读文件
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:27:08 PM
 */
public final class IOReaderUtil{

	private static final Logger	log	= LoggerFactory.getLogger(IOReaderUtil.class);

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
}
