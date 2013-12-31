/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.ant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.io.IOWriteUtil;

/**
 * 飞龙压缩和解压缩工具类 <br>
 * org.apache.tools.zip.ZipOutputStream 和ZipEntry在ant.jar里，能解决中文问题， <br>
 * 当然，你也可以使用java.util.zip下的，只是中文文件的话.会产生乱码，并且压缩后文件损坏
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-9 下午01:44:02
 */
public final class ZipUtil{

	private final static Logger	log	= LoggerFactory.getLogger(ZipUtil.class);

	/**
	 * 将文件夹压缩成压缩文件,后缀为zip
	 * 
	 * @param inputFileName
	 *            文件夹名称
	 * @param zipFileName
	 *            所输出压缩文件夹的名称，打包后,压缩文件名字
	 * @throws Exception
	 */
	public static void zip(String inputFileName,String zipFileName){
		File file = new File(inputFileName);
		zip(file, zipFileName);
	}

	/**
	 * 将文件夹压缩成压缩文件,后缀为zip
	 * 
	 * @param inputFile
	 *            文件夹/文件
	 * @param zipFileName
	 *            所输出压缩文件夹的名称，打包后,压缩文件名字
	 */
	public static void zip(File inputFile,String zipFileName){
		FileOutputStream fileOutputStream = IOUtil.getFileOutputStream(zipFileName);
		ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
		zip(inputFile, zipOutputStream, "");
		try{
			zipOutputStream.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * 将文件夹压缩成压缩文件,后缀为zip
	 * 
	 * @param file
	 *            文件夹/文件
	 * @param zipOutputStream
	 *            zipOutputStream
	 * @param base
	 */
	public static void zip(File file,ZipOutputStream zipOutputStream,String base){
		// 判断是否为目录
		if (file.isDirectory()){
			File[] files = file.listFiles();
			putNextEntry(zipOutputStream, base + "/");
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < files.length; i++){
				// 递归算法
				zip(files[i], zipOutputStream, base + files[i].getName());
			}
		}else{
			// 压缩目录中的所有文件
			putNextEntry(zipOutputStream, base);
			// *****************************************************************
			FileInputStream fileInputStream = IOUtil.getFileInputStream(file);
			IOWriteUtil.write(fileInputStream, zipOutputStream);
		}
	}

	/**
	 * 使用 org.apache.tools.zip解压
	 * 
	 * @param zipFileName
	 *            被解压缩的zip文件
	 * @param outputDirectory
	 *            解压到哪个目录
	 */
	public static void unZip(String unZipFileName,String outputDirectory){
		try{
			ZipFile zipFile = new ZipFile(unZipFileName);
			Enumeration<ZipEntry> enumeration = zipFile.getEntries();
			ZipEntry zipEntry = null;
			while (enumeration.hasMoreElements()){
				zipEntry = enumeration.nextElement();
				File file = new File(outputDirectory + File.separator + zipEntry.getName());
				if (zipEntry.isDirectory()){
					// log.info("");
				}else{
					File parentFile = file.getParentFile();
					// 自动判断上层目录是否创建
					if (!parentFile.exists()){
						log.info("创建目录:" + parentFile.getAbsolutePath());
						parentFile.mkdirs();
					}
					log.info("解压文件 :" + zipEntry.getName());
					InputStream inputStream = zipFile.getInputStream(zipEntry);
					OutputStream outputStream = new FileOutputStream(file);
					IOWriteUtil.write(inputStream, outputStream);
				}
			}
		}catch (ZipException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}catch (FileNotFoundException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}catch (IOException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处。
	 * 
	 * @param zipOutputStream
	 *            此类为以 ZIP 文件格式写入文件实现输出流过滤器。包括对已压缩和未压缩条目的支持。
	 * @param name
	 *            此类用于表示 ZIP 文件条目
	 */
	private static void putNextEntry(ZipOutputStream zipOutputStream,String name){
		// 此类用于表示 ZIP 文件条目
		ZipEntry zipEntry = new ZipEntry(name);
		// 开始写入新的 ZIP 文件条目并将流定位到条目数据的开始处
		try{
			zipOutputStream.putNextEntry(zipEntry);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
