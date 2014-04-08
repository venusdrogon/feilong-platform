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
package com.feilong.tools.net.filetransfer;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.entity.FileInfoEntity;
import com.feilong.commons.core.enumeration.FileType;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.json.JsonUtil;

/**
 * 通用的文件传输 ,开放出来的方法有: <br>
 * 下载:
 * <ul>
 * <li>{@link #download(String, String)}</li>
 * <li>{@link #download(String[], String)}</li>
 * </ul>
 * 上传:
 * <ul>
 * <li>{@link #sendLocalFileToRemote(String, String)}</li>
 * <li>{@link #sendLocalFileToRemote(String[], String)}</li>
 * </ul>
 * 删除:
 * <ul>
 * <li>{@link #delete(String)}</li>
 * <li>{@link #delete(String[], String)}</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 3, 2013 12:57:37 PM
 */
public abstract class FileTransfer{

	private static final Logger	log	= LoggerFactory.getLogger(FileTransfer.class);

	/**
	 * 打开链接
	 * 
	 * @return
	 */
	protected abstract boolean connect();

	/**
	 * 将远程路径文件/文件夹 下载到 本地指定的目录下面
	 * 
	 * <pre>
	 * 
	 * String	remotePath	= &quot;/webstore/InlineSales_Test/nikestore_china_cancel20130103.csv&quot;
	 * String	localAbsoluteDirectoryPath	= &quot;E:\\test\\1&quot;;
	 * 
	 * 如果 localAbsoluteDirectoryPath 不存在, 会级联创建
	 * 
	 * 如果  remotePath 是个文件(file), 会将此文件 直接下载到 localAbsoluteDirectoryPath 目录下面
	 * 如果  remotePath 是个文件夹(Directory), 首先 会先在 localAbsoluteDirectoryPath 下面创建同名文件夹,并递归下载文件(相当于整个文件夹下载下来)
	 * 
	 * </pre>
	 * 
	 * @param remotePath
	 *            远程路径 可以是单个文件 也可以是个文件夹 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}
	 * @param localAbsoluteDirectoryPath
	 *            本地绝对的目录,如果不存在 支持级联创建 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}
	 * @throws Exception
	 */
	public void download(String remotePath,String localAbsoluteDirectoryPath) throws Exception{
		String[] remotePaths = { remotePath };
		download(remotePaths, localAbsoluteDirectoryPath);
	}

	/**
	 * 打开一次链接,将一批 远程文件/文件夹 循环下载到 本地目录
	 * 
	 * <pre>
	 * 
	 * String[]	remotePaths	= {&quot;/webstore/InlineSales_Test/nikestore_china_cancel20130103.csv&quot,&quot;/webstore/InlineSales_Test/test&quot;};
	 * String	localAbsoluteDirectoryPath	= &quot;E:\\test\\1&quot;;
	 * 
	 * 如果 localAbsoluteDirectoryPath 不存在, 会级联创建
	 * 
	 * 如果  remotePath 是个文件(file), 会将此文件 直接下载到 localAbsoluteDirectoryPath 目录下面
	 * 如果  remotePath 是个文件夹(Directory), 首先 会先在 localAbsoluteDirectoryPath 下面创建同名文件夹,并<b>递归下载</b>文件(相当于整个文件夹下载下来)
	 * 
	 * </pre>
	 * 
	 * @param remotePaths
	 *            一批 远程文件远程路径 可以是文件 也可以文件夹 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}<br>
	 *            任意一个值 Null Or Empty, {@link IllegalArgumentException}
	 * @param localAbsoluteDirectoryPath
	 *            本地绝对的目录,如果不存在 支持级联创建 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}
	 * @throws Exception
	 */
	public void download(String[] remotePaths,String localAbsoluteDirectoryPath) throws Exception{
		if (Validator.isNullOrEmpty(remotePaths)){
			throw new IllegalArgumentException("remotePaths can not be NullOrEmpty");
		}
		for (String remotePath : remotePaths){
			if (Validator.isNullOrEmpty(remotePath)){
				throw new IllegalArgumentException("remotePath can not be NullOrEmpty");
			}
		}
		// localAbsoluteDirectoryPath
		if (Validator.isNullOrEmpty(localAbsoluteDirectoryPath)){
			throw new IllegalArgumentException("localAbsoluteDirectoryPath can not be NullOrEmpty");
		}

		boolean isSuccess = connect();
		if (isSuccess){
			for (String remotePath : remotePaths){
				isSuccess = _downloadDontClose(remotePath, localAbsoluteDirectoryPath);
				if (!isSuccess){
					break;
				}
			}
			// 关闭连接
			disconnect();
		}
	}

	/**
	 * 不关闭连接 下载
	 * 
	 * @param remotePath
	 *            远程文件远程路径 可以是文件 也可以文件夹
	 * @param localAbsoluteDirectoryPath
	 *            本地绝对的目录,如果不存在 支持级联创建
	 * @return
	 */
	protected boolean _downloadDontClose(String remotePath,String localAbsoluteDirectoryPath) throws Exception{

		boolean success = false;
		File file = new File(remotePath);
		// 远程 文件的文件名
		String remoteFileName = file.getName();
		String filePath = localAbsoluteDirectoryPath + "/" + remoteFileName;
		// 文件夹
		if (isDirectory(remotePath)){
			log.info("In [{}] will be create directory:[{}]", localAbsoluteDirectoryPath, remoteFileName);

			success = FileUtil.createDirectory(filePath);

			if (success){
				Map<String, FileInfoEntity> lsFileMap = getLsFileMap(remotePath);
				for (Map.Entry<String, FileInfoEntity> entry : lsFileMap.entrySet()){
					String key = entry.getKey();
					// Boolean value = entry.getValue();

					// 级联下载
					success = _downloadDontClose(remotePath + "/" + key, filePath);
				}
			}
		}else{
			// 下载到本地的文件 路径
			log.info("remotePath:{} will be download to filePath:{} ", remotePath, filePath);
			success = _downRemoteSingleFile(remotePath, filePath);
		}
		return success;

	}

	/**
	 * 下载远程单个文件
	 * 
	 * @param remoteSingleFile
	 *            远程单个文件
	 * @param filePath
	 *            保存到本地的路径
	 * @return
	 * @throws Exception
	 */
	protected abstract boolean _downRemoteSingleFile(String remoteSingleFile,String filePath) throws Exception;

	/**
	 * 将单个路径文件,传到远程服务器上面
	 * 
	 * <pre>
	 * 	String singleLocalFileFullPath =  "E:\\test";
	 * 	String remoteDirectory	= "/webstore/InlineSales_Test/2011-07-05/";
	 *  
	 *  如果  singleLocalFileFullPath 是个文件(file), 会将此文件 直接上传到 remoteDirectory 目录下面
	 *  如果  singleLocalFileFullPath 是个文件夹(Directory), 首先 会先在 remoteDirectory 下面创建同名文件夹,并<b>递归上传</b>文件(相当于整个文件夹上传)
	 *  
	 * 注:新的文件的名称和你上传文件的名称一样的,也就是说 你传的什么名称文件/文件夹 到服务器就是什么名称文件/文件夹 <br>
	 * </pre>
	 * 
	 * @param singleLocalFileFullPath
	 *            单个路径,全路径 <br>
	 *            注意:文件路径最好不要有中文,有些系统支持不好<br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}<br>
	 *            不能文件不存在, {@link IllegalArgumentException}
	 * @param remoteDirectory
	 *            要传到哪个文件夹下面,<br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}
	 * @return 操作全部成功 返回true,否则返回false
	 * @throws Exception
	 */
	public boolean sendLocalFileToRemote(String singleLocalFileFullPath,String remoteDirectory) throws Exception{
		String[] batchLocalFileFullPaths = { singleLocalFileFullPath };
		return sendLocalFileToRemote(batchLocalFileFullPaths, remoteDirectory);
	}

	/**
	 * 支持数组类型,多个不定路径文件上传
	 * 
	 * <pre>
	 * 	String[] batchLocalFileFullPaths = { "E:\\test", "E:\\1.jpg" };
	 * 	String remoteDirectory	= "/webstore/InlineSales_Test/2011-07-05/";
	 *  
	 *  如果  localFileFullPath 是个文件(file), 会将此文件 直接上传到 remoteDirectory 目录下面
	 *  如果  localFileFullPath 是个文件夹(Directory), 首先 会先在 remoteDirectory 下面创建同名文件夹,并<b>递归上传</b>文件(相当于整个文件夹上传)
	 *  
	 * 注:新的文件的名称和你上传文件的名称一样的,也就是说 你传的什么名称文件/文件夹 到服务器就是什么名称文件/文件夹 <br>
	 * </pre>
	 * 
	 * @param remoteDirectory
	 *            要传到哪个文件夹下面,<br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}
	 * @param batchLocalFileFullPaths
	 *            上传的文件名(数组),全路径 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}<br>
	 *            任意一个值 Null Or Empty, {@link IllegalArgumentException}<br>
	 *            任意一个值localFileFullPath文件不存在, {@link IllegalArgumentException}
	 * @return 全部成功返回true,否则一旦有失败则返回false
	 * @throws Exception
	 */
	public boolean sendLocalFileToRemote(String[] batchLocalFileFullPaths,String remoteDirectory) throws Exception{
		if (Validator.isNullOrEmpty(batchLocalFileFullPaths)){
			throw new IllegalArgumentException("batchLocalFileFullPaths can not NullOrEmpty");
		}
		for (String localFileFullPath : batchLocalFileFullPaths){
			if (Validator.isNullOrEmpty(localFileFullPath)){
				throw new IllegalArgumentException("localFileFullPath can not be NullOrEmpty");
			}
			// 文件必需存在
			else if (!FileUtil.isExistFile(localFileFullPath)){
				throw new IllegalArgumentException("localFileFullPath:" + localFileFullPath + "  not exist");
			}
		}
		if (Validator.isNullOrEmpty(remoteDirectory)){
			throw new IllegalArgumentException("remoteDirectory can not NullOrEmpty");
		}

		boolean isSuccess = connect();
		if (isSuccess){
			for (String singleLocalFileFullPath : batchLocalFileFullPaths){
				isSuccess = _sendLocalFileToRemoteDontClose(singleLocalFileFullPath, remoteDirectory);
				if (!isSuccess){
					break;
				}
			}
			// 关闭连接
			disconnect();
			return isSuccess;
		}
		return false;
	}

	/**
	 * 递归传递文件夹/文件,<br>
	 * 自动辨别 是否是文件还是文件夹<br>
	 * 如果是文件夹,先判断remoteDirectory 下面是否已经有同名的文件夹,如果没有,则在对应的 remoteDirectory 创建文件夹.
	 * 
	 * @param singleLocalFileFullPath
	 *            要上传的单个文件 全路径 ,自动辨别 是否是文件还是文件夹<br>
	 *            如果是文件夹,先判断remoteDirectory 下面是否已经有同名的文件夹,如果没有,则在对应的 remoteDirectory 创建文件夹
	 * @param remoteDirectory
	 *            远程保存目录
	 * @return true, if successful
	 * @throws Exception
	 */
	protected boolean _sendLocalFileToRemoteDontClose(String singleLocalFileFullPath,String remoteDirectory) throws Exception{
		File localFile = new File(singleLocalFileFullPath);

		log.debug("singleLocalFileFullPath's absolutePath:[{}]", localFile.getAbsolutePath());
		log.debug("remoteDirectory:[{}]", remoteDirectory);

		// 定义返回值 默认是失败
		boolean isSuccess = false;
		// 转移到FTP服务器目录
		isSuccess = cd(remoteDirectory);
		if (isSuccess){
			log.info("cd:[{}] success~~~~", remoteDirectory);
		}else{
			log.error("cd:[{}] error~~~~", remoteDirectory);
		}

		if (isSuccess){
			String localFileName = localFile.getName();
			// 文件
			if (localFile.isFile()){
				FileInputStream fileInputStream_local = IOUtil.getFileInputStream(singleLocalFileFullPath);
				isSuccess = put(fileInputStream_local, localFileName);

				if (isSuccess){
					log.info("put [{}] to [{}] success...", singleLocalFileFullPath, remoteDirectory);
				}else{// 如果传送不成功
					log.error("put [{}] to [{}] error!!!", singleLocalFileFullPath, remoteDirectory);
				}
				fileInputStream_local.close();
			}
			// 文件夹
			else if (localFile.isDirectory()){
				// 是否存在同名且同类型的文件,
				// 如果 不存在,则创建一个文件夹
				if (!isExistsSameNameAndTypeFile(localFile, remoteDirectory)){
					String dirNameToCreate = localFileName;
					isSuccess = mkdir(dirNameToCreate);
					if (isSuccess){
						log.info("mkdir:[{}] success~~~~", dirNameToCreate);
					}else{
						log.error("mkdir:[{}] error~~~~", dirNameToCreate);
					}
				}
				File[] files = localFile.listFiles();
				// 递归
				for (File childrenFile : files){
					_sendLocalFileToRemoteDontClose(childrenFile.getAbsolutePath(), remoteDirectory + "/" + localFileName);
				}
			}
		}
		return isSuccess;
	}

	/**
	 * 切换 远程操作目录
	 * 
	 * @param remoteDirectory
	 * @throws Exception
	 */

	protected abstract boolean cd(String remoteDirectory) throws Exception;

	/**
	 * 创建远程文件夹
	 * 
	 * @param remoteDirectory
	 * @throws Exception
	 */
	protected abstract boolean mkdir(String remoteDirectory) throws Exception;

	/**
	 * 上传
	 * 
	 * @param fileInputStream_local
	 * @param toFileName
	 * @throws Exception
	 */
	protected abstract boolean put(FileInputStream fileInputStream_local,String toFileName) throws Exception;

	/**
	 * 是否存在同名且同类型的文件
	 * 
	 * @param file
	 * @param remotePath
	 *            远程路径
	 * @return 存在 返回true,否则返回false
	 * @throws Exception
	 */
	protected boolean isExistsSameNameAndTypeFile(File file,String remotePath) throws Exception{
		boolean flag = false;
		Map<String, FileInfoEntity> map = getLsFileMap(remotePath);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(map));
		}

		if (Validator.isNotNullOrEmpty(map)){
			String fileName = file.getName();
			boolean isFile = file.isFile();
			boolean isDirectory = file.isDirectory();
			String type = isFile ? "isFile" : "isDirectory";
			log.debug("Input fileName:[{}],type:[{}]", fileName, type);

			// 判断同名
			if (map.containsKey(fileName)){
				// 远程是否是 文件夹
				FileInfoEntity fileEntity = map.get(fileName);
				boolean _isDirectory = (fileEntity.getFileType() == FileType.DIRECTORY);
				// 判断 同类型,isFile
				if (isFile && !_isDirectory){
					flag = true;
				}else if (isDirectory && _isDirectory){
					flag = true;
				}
			}
			/******************************************************************/
			if (flag){
				log.info("hasSameNameAndTypeFile,filename:[{}],type:[{}]", type, fileName);
			}
		}
		return flag;
	}

	/**
	 * 获得 ls map <br>
	 * key 为文件名称(不是全路径),<br>
	 * value FileEntity
	 * 
	 * @param remotePath
	 *            远程路径
	 * @return
	 * @throws Exception
	 */
	protected abstract Map<String, FileInfoEntity> getLsFileMap(String remotePath) throws Exception;

	/**
	 * 获得某特定文件夹下面 指定文件名相关信息
	 * 
	 * @param remotePath
	 *            远程地址
	 * @param fileNames
	 *            文件名称组
	 * @return 如果fileNames 有文件不在 remotePath 路径下面, 则返回的map中这条数据的value 是null
	 * @throws Exception
	 */
	public Map<String, FileInfoEntity> getFileEntityMap(String remotePath,String[] fileNames) throws Exception{
		if (Validator.isNullOrEmpty(remotePath)){
			throw new IllegalArgumentException("remotePath can not NullOrEmpty");
		}

		if (Validator.isNullOrEmpty(fileNames)){
			throw new IllegalArgumentException("fileNames can not NullOrEmpty");
		}

		boolean isSuccess = connect();
		if (isSuccess){
			Map<String, FileInfoEntity> _getFileEntityMap = _getFileEntityMap(remotePath, fileNames);
			disconnect();
			return _getFileEntityMap;
		}
		return null;
	}

	/**
	 * 获得远程目录下面 文件信息map,<br>
	 * 传过来几条,返回几条, 如果文件不存在 返回null
	 * 
	 * @param remotePath
	 * @param childFile
	 * @return
	 * @throws Exception
	 */
	protected Map<String, FileInfoEntity> _getFileEntityMap(String remotePath,String[] fileNames) throws Exception{
		Map<String, FileInfoEntity> map = getLsFileMap(remotePath);

		// 返回的map
		Map<String, FileInfoEntity> returnMap = new HashMap<String, FileInfoEntity>();
		for (String fileName : fileNames){
			if (map.containsKey(fileName)){
				returnMap.put(fileName, map.get(fileName));
			}else{
				returnMap.put(fileName, null);
			}
		}
		return returnMap;
	};

	/**
	 * 关闭链接
	 */
	protected abstract void disconnect();

	/**
	 * 删除远程的一个文件
	 * 
	 * <pre>
	 * String remoteAbsolutePath="/webstore/InlineSales_Test/2011-07-05/test";
	 *  
	 *  如果  remoteAbsolutePath 是个文件(file), 会将此文件 直接删除
	 *  如果  remoteAbsolutePath 是个文件夹(Directory), 首先<b>递归删除</b>该文件夹下面 所有的文件/文件夹 再删除此文件夹
	 * 
	 * 注意:un supported delete '/'remotePath  (危险)
	 * </pre>
	 * 
	 * @param remoteAbsolutePath
	 *            文件绝对路径<br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}<br>
	 *            不能= / , {@link IllegalArgumentException} 危险!!
	 * @return 删除成功返回true,否则false
	 */
	public boolean delete(String remoteAbsolutePath){
		String[] remoteAbsolutePaths = { remoteAbsolutePath };
		return delete(remoteAbsolutePaths);
	}

	/**
	 * 删除远程的一组文件<br>
	 * tips:不管是windows还是linux、unix，都不能在同一目录结构下创建同名的文件夹和文件
	 * 
	 * <pre>
	 * String[] remoteAbsolutePaths={"/webstore/InlineSales_Test/2011-07-05/test","/webstore/InlineSales_Test/2011-07-05/1.cvs"};
	 *  
	 *  如果  remoteAbsolutePath 是个文件(file), 会将此文件 直接删除
	 *  如果  remoteAbsolutePath 是个文件夹(Directory), 首先<b>递归删除</b>该文件夹下面 所有的文件/文件夹 再删除此文件夹
	 * 
	 * 注意:un supported delete '/'remotePath  (危险)
	 * </pre>
	 * 
	 * @param remoteAbsolutePaths
	 *            一组文件,绝对路径 <br>
	 *            不能为 Null Or Empty,否则 {@link IllegalArgumentException}<br>
	 *            任意一个值 Null Or Empty, {@link IllegalArgumentException}<br>
	 *            任意一个值= / , {@link IllegalArgumentException} 危险!!
	 * @return 删除成功返回true,否则false<br>
	 *         不支持 删除全部 危险<br>
	 *         一旦 有一个文件 null or empty 抛错
	 */
	public boolean delete(String[] remoteAbsolutePaths){
		if (Validator.isNullOrEmpty(remoteAbsolutePaths)){
			throw new IllegalArgumentException("remotePaths can't be null/empty!");
		}

		for (String remoteAbsolutePath : remoteAbsolutePaths){
			// 一旦 有一个文件 null or empty 抛错
			if (Validator.isNullOrEmpty(remoteAbsolutePath)){
				throw new IllegalArgumentException("remotePath can't be null/empty!");
			}

			// 不支持 删除全部 危险
			if ("/".equals(remoteAbsolutePath)){
				throw new UnsupportedOperationException("un supported delete '/'remotePath ");
			}
		}

		boolean isConnect = connect();
		if (isConnect){
			try{
				boolean deleteDontClose = _deleteDontClose(remoteAbsolutePaths);
				return deleteDontClose;
			}catch (Exception e){
				e.printStackTrace();
			}finally{
				disconnect();
			}
		}
		return false;
	}

	/**
	 * 删除 单个文件
	 * 
	 * @param remoteAbsolutePath
	 * @return
	 * @throws Exception
	 */
	protected boolean _deleteSingleDontClose(String remoteAbsolutePath) throws Exception{
		String[] remoteAbsolutePaths = { remoteAbsolutePath };
		return _deleteDontClose(remoteAbsolutePaths);
	}

	/**
	 * 不关闭 链接 删除 一组文件
	 * 
	 * @param remoteAbsolutePaths
	 * @return
	 * @throws Exception
	 */
	protected boolean _deleteDontClose(String[] remoteAbsolutePaths) throws Exception{

		boolean flag = false;

		for (String remotePath : remoteAbsolutePaths){
			boolean isDirectory = isDirectory(remotePath);
			if (isDirectory){

				log.debug("remotePath :[{}] is [directory],removeDirectory.....", remotePath);

				Map<String, FileInfoEntity> map = getLsFileMap(remotePath);

				// 删除 子文件
				for (Map.Entry<String, FileInfoEntity> entry : map.entrySet()){
					String key = entry.getKey();
					// 级联删除
					_deleteSingleDontClose(joinPath(remotePath, key));
				}

				log.info("channelSftp rmdir,remotePath [{}]", remotePath);
				flag = rmdir(remotePath);

			}else{
				log.info("remotePath:[{}] is [not directory],rm....", remotePath);
				flag = rm(remotePath);
			}
		}
		return flag;
	}

	/**
	 * 删除 文件夹
	 * 
	 * @param remoteAbsolutePath
	 *            远程绝对路径
	 * @return 成功返回true 否则 返回false
	 * @throws Exception
	 */
	protected abstract boolean rmdir(String remoteAbsolutePath) throws Exception;

	/**
	 * 删除文件
	 * 
	 * @param remoteAbsolutePath
	 *            远程绝对路径
	 * @return 成功返回true 否则 返回false
	 * @throws Exception
	 */
	protected abstract boolean rm(String remoteAbsolutePath) throws Exception;

	/**
	 * 判断 一个远程地址 是否是文件夹
	 * 
	 * @param remoteAbsolutePath
	 *            远程绝对路径
	 * @return 是文件夹返回true,否则false
	 * @throws Exception
	 */
	protected abstract boolean isDirectory(String remoteAbsolutePath) throws Exception;

	/**
	 * 文件夹 和文件 拼接路径
	 * 
	 * @param directoryPath
	 *            文件夹路径,有得时候 在远程ftp 或者sftp 上面 不知道 这个路径到底是否是文件夹
	 * @param ftpFileName
	 * @return
	 */
	// TODO 不满意这种方式
	protected String joinPath(String directoryPath,String ftpFileName){
		if (directoryPath.endsWith("/")){
			return directoryPath + ftpFileName;
		}else{
			return directoryPath + "/" + ftpFileName;
		}
	}
}
