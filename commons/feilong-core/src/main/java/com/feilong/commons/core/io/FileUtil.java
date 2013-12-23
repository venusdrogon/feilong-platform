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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.Validator;

/**
 * File 文件操作
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午5:00:54
 */
public final class FileUtil{

	private static final Logger	log	= LoggerFactory.getLogger(FileUtil.class);

	/** Don't let anyone instantiate this class. */
	private FileUtil(){}

	/**
	 * 判断一个目录 是否是空目录(里面没有文件)
	 * 
	 * @param directory
	 *            指定一个存在的文件夹
	 * @return <ul>
	 *         <li>如果directory isNullOrEmpty,throw IllegalArgumentException</li>
	 *         <li>如果directory don't exists,throw IllegalArgumentException</li>
	 *         <li>如果directory is not Directory,throw IllegalArgumentException</li>
	 *         <li>return file.list() ==0</li>
	 *         </ul>
	 */
	public static boolean isEmptyDirectory(String directory){
		if (Validator.isNullOrEmpty(directory)){
			throw new IllegalArgumentException("directory param " + directory + " can't be null/empty!");
		}
		File file = new File(directory);
		if (!file.exists()){
			throw new IllegalArgumentException("directory file " + directory + " don't exists!");
		}

		if (!file.isDirectory()){
			throw new IllegalArgumentException("directory file " + directory + " is not Directory!");
		}

		// Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.
		// 如果此抽象路径名不表示一个目录，那么此方法将返回 null

		// ubuntu 已经 测试ok
		String[] list = file.list();

		int length = list.length;

		if (log.isDebugEnabled()){

			log.debug("file :[{}] list length:[{}]", directory, length);

			for (String fileName : list){
				log.debug(fileName);
			}
		}

		boolean flag = (length == 0);
		return flag;
	}

	/**
	 * 创建文件夹<br>
	 * 支持级联创建
	 * 
	 * @param folderPath
	 *            文件夹路径, 支持级联创建
	 * @return 如果创建失败 返回false,<br>
	 *         创建成功返回true,<br>
	 *         如果文件夹已经存在返回true
	 */
	public static boolean createDirectory(String folderPath){
		// 文件和目录路径名的抽象表示形式
		File folder = new File(folderPath);
		if (!folder.exists()){
			// mkdir 如果 parent 目录不存在 会返回false 不会报错
			boolean flag = folder.mkdirs();
			if (!flag){
				log.warn("folder:[{}] make [faild]", folder);
			}
			return flag;
		}else{
			log.debug("the folder:[{}] exists,don't need mkdirs", folder);
		}
		return true;
	}

	/**
	 * 删除某个文件或者文件夹
	 * 
	 * @param fileName
	 *            文件或者文件夹名称
	 */
	public static void deleteFileOrDirectory(String fileName){
		File file = new File(fileName);
		if (file.exists()){
			deleteFileOrDirectory(file);
		}else{
			log.error(fileName + "不存在");
		}
	}

	/**
	 * 删除文件或者文件夹,如果是文件夹 ,递归深层次 删除
	 * 
	 * @param file
	 *            文件或者文件夹名称
	 */
	public static void deleteFileOrDirectory(File file){
		if (file.exists() && file.isDirectory()){
			File[] files = file.listFiles();
			if (null != files && files.length > 0){
				for (File currentFile : files){
					if (!currentFile.isDirectory()){
						currentFile.delete();
					}else{
						deleteFileOrDirectory(currentFile);
					}
				}
			}
			file.delete();
		}else{
			file.delete();
		}
	}

	/**************************************************************************************/
	/**
	 * 使用新的 后缀名称
	 * 
	 * @param fileName
	 *            文件名称
	 * @param newPostfixName
	 *            新的文件名
	 * @return
	 */
	public final static String getNewFileName(String fileName,String newPostfixName){
		// 有后缀
		if (hasPostfixName(fileName)){
			return fileName.substring(0, fileName.lastIndexOf(".") + 1) + newPostfixName;
		}
		// 没有后缀直接拼接
		return fileName + "." + newPostfixName;
	}

	/**
	 * 判断是否有后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public final static boolean hasPostfixName(String fileName){
		String _fileName = getFileName(fileName);
		int lastIndexOf = _fileName.lastIndexOf(".");
		if (-1 == lastIndexOf){
			return false;
		}
		return true;
	}

	/**
	 * 获得文件后缀名,并返回原样字母<br>
	 * 如果文件没有后缀名 返回 ""
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 不带. 的后缀
	 */
	public final static String getFilePostfixName(String fileName){
		if (hasPostfixName(fileName)){
			String filePostfixName = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			return filePostfixName;
		}
		return "";
	}

	/**
	 * 获得文件后缀名,并返回小写字母<br>
	 * 如果文件没有后缀名 返回 ""
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 不带. 的后缀
	 */
	public final static String getFilePostfixNameLowerCase(String fileName){
		String postfixName = getFilePostfixName(fileName);
		return postfixName.toLowerCase();
	}

	/**
	 * 获得文件的不带后缀名的名称
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 获得文件的不带后缀名的名称
	 */
	public final static String getFilePreName(String fileName){
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 获得带后缀的文件纯名称<br>
	 * 如:F:/pie2.png,return pie2.png
	 * 
	 * @param fileName
	 * @return
	 */
	public final static String getFileName(String fileName){
		File file = new File(fileName);
		return file.getName();
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public final static boolean isExistFile(String filePath){
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 取得文件大小
	 * 
	 * @param file
	 *            文件
	 * @return b
	 * @throws IOException
	 */
	public static long getFileSize(File file){

		// try{
		// FileInputStream fileInputStream = new FileInputStream(file);
		// return fileInputStream.available();
		// }catch (FileNotFoundException e){
		// e.printStackTrace();
		// }catch (IOException e){
		// e.printStackTrace();
		// }
		return file.length();

	}

	// ************************************************************************
	//
	// 各种判断
	//
	// ************************************************************************
	/**
	 * 上传的文件是否是常用图片格式
	 * 
	 * @param fileName
	 *            文件名称,可以是全路径 ,也可以是 部分路径,会解析取到后缀名
	 * @return 上传的文件是否是常用图片格式
	 */
	public final static boolean isCommonImage(String fileName){
		return isInAppointTypes(fileName, IOConstants.commonImages);
	}

	/**
	 * 上传的文件是否在指定的文件类型里面
	 * 
	 * @param fileName
	 *            文件名称
	 * @param appointTypes
	 *            指定的文件类型数组
	 * @return 上传的文件是否在指定的文件类型里面
	 */
	public final static boolean isInAppointTypes(String fileName,String[] appointTypes){
		return ArrayUtil.isContain(appointTypes, getFilePostfixName(fileName));
	}

	// /**
	// * 判断excel文件是否是2003的版本 不精准
	// *
	// * @param fileName
	// * 文件名称带后缀 或者是路径名称
	// * @return 判断excel文件是否是2003的版本
	// */
	// public final static boolean isExcel2003(String fileName){
	// return "xls".equalsIgnoreCase(getFilePostfixName(fileName));
	// }
	//
	// /**
	// * 判断excel文件是否是2007的版本 不精准
	// *
	// * @param fileName
	// * 文件名称带后缀 或者是路径名称
	// * @return 判断excel文件是否是2007的版本
	// */
	// public final static boolean isExcel2007(String fileName){
	// return "xlsx".equalsIgnoreCase(getFilePostfixName(fileName));
	// }

	/**************************************************************************************/
	/**
	 * 文件大小格式化
	 * 
	 * @param fileSize
	 *            文件大小 单位byte
	 * @return 文件大小byte 转换
	 */
	public final static String formatFileSize(long fileSize){
		String danwei = "Bytes";
		// 除完之后的余数
		String yushu = "";
		// 除数
		long chushu = 1;
		if (fileSize >= IOConstants.GB){
			danwei = "GB";
			chushu = IOConstants.GB;
		}else if (fileSize >= IOConstants.MB){
			danwei = "MB";
			chushu = IOConstants.MB;
		}else if (fileSize >= IOConstants.KB){
			danwei = "KB";
			chushu = IOConstants.KB;
		}
		if (chushu == 1){
			return fileSize + danwei;
		}
		yushu = 100 * (fileSize % chushu) / chushu + "";
		if ("0".equals(yushu)){
			return fileSize / chushu + danwei;
		}
		return fileSize / chushu + "." + yushu + danwei;
	}
}
