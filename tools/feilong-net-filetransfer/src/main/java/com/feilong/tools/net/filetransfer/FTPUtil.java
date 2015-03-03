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
package com.feilong.tools.net.filetransfer;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.io.FileType;

/**
 * ftp 相关的工具类, 注:依赖于 commons-net<br>
 * 包括
 * <ul>
 * <li>单个文件上传</li>
 * <li>整个文件夹上传</li>
 * <li>多个不定路径文件上传</li>
 * <li>多个不定路径文件夹上传</li>
 * </ul>
 * <p>
 * FTP（File Transfer Protocol, FTP）是TCP/IP网络上两台计算机传送文件的协议，FTP是在TCP/IP网络和INTERNET上最早使用的协议之一，它属于网络协议组的应用层。
 * </p>
 * .
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-6 上午10:18:20
 * @version 1.1 2011-8-30 16:22
 *          <ul>
 *          <li>{@link #sendLocalFileToRemote(String, String)}</li>
 *          <li>{@link #sendLocalFileToRemote(String[], String)}</li>
 *          </ul>
 * @version 1.2 换成实例化方式,方便spring di,支持 单个站点 多个ftp 方式
 * @version 1.3 抽取出通用的{@link FileTransfer}
 * @since 1.0
 */
public class FTPUtil extends FileTransfer{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(FTPUtil.class);

	/** The host name. */
	private String				hostName;

	/** 用户名,默认 anonymous. */
	private String				userName	= "anonymous";

	/** 密码,默认 anonymous. */
	private String				password	= "anonymous";

	// ********************************************************

	/** The ftp client. */
	private FTPClient			ftpClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#connect()
	 */
	@Override
	protected boolean connect(){
		// 是否连接成功, 默认不成功
		boolean isSuccess = false;
		try{
			// 连接
			ftpClient.connect(hostName);
			log.debug("connect hostName:{}", hostName);

			boolean isLoginSuccess = ftpClient.login(userName, password);
			Object[] params = { userName, password, isLoginSuccess };
			log.debug("login:[{}] [{}], {}~~~", params);

			if (isLoginSuccess){
				int replyCode = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(replyCode)){
					log.error("FTP 服务拒绝连接！ReplyCode is:{},will ftpClient.disconnect()", replyCode);
					ftpClient.disconnect();
					return false;
				}else{
					// *******************************************************************************************
					// 设置 本机被动模式 这个到不用在login之后执行, 因为它只改变FTPClient实例的内部属性.
					ftpClient.enterLocalPassiveMode();

					// FTPClient默认使用ASCII作为传输模式, 所有不能传输二进制文件
					// 设置以二进制流的方式传输,图片保真,[要在login以后执行]. 因为这个方法要向服务器发送"TYPE I"命令
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

					String systemName = ftpClient.getSystemName();
					log.debug("ftpClient systemName:[{}]", systemName);

					// 设置FTP客服端的配置--一般可以不设置 
					// FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
					// ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
					// ftpClient.configure(ftpClientConfig);

					// ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);

					// 没有异常则 确定成功
					isSuccess = true;
				}
			}
		}catch (SocketException e){
			log.error(e.getClass().getName(), e);
			disconnect();
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
			disconnect();
		}
		log.info("connect :{}", isSuccess);
		return isSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#disconnect()
	 */
	@Override
	protected void disconnect(){
		if (ftpClient != null && ftpClient.isConnected()){
			try{
				boolean logout = ftpClient.logout();
				log.debug("ftpClient logout:[{}]", logout);

				ftpClient.disconnect();
				log.debug("ftpClient disconnect...");
			}catch (IOException e){
				log.error(e.getClass().getName(), e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#cd(java.lang.String)
	 */
	@Override
	protected boolean cd(String remoteDirectory) throws Exception{
		// 转移到FTP服务器目录
		boolean flag = ftpClient.changeWorkingDirectory(remoteDirectory);
		if (flag){
			log.info("cd :[{}]:[{}]", remoteDirectory, flag);
		}else{
			log.warn("cd: [{}] error,ReplyString is:{}", remoteDirectory, ftpClient.getReplyString());
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#mkdir(java.lang.String)
	 */
	@Override
	protected boolean mkdir(String remoteDirectory) throws Exception{
		boolean flag = ftpClient.makeDirectory(remoteDirectory);
		if (flag){
			log.info("mkdir :{} :{}", remoteDirectory, flag);
		}else{
			log.error("mkdir error,ReplyString is:{}", ftpClient.getReplyString());
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#put(java.io.FileInputStream, java.lang.String)
	 */
	@Override
	protected boolean put(FileInputStream fileInputStream_local,String toFileName) throws Exception{
		boolean flag = ftpClient.storeFile(toFileName, fileInputStream_local);
		if (flag){
			log.info("put :[{}] :[{}]", toFileName, flag);
		}else{
			log.error("stor file error,ReplyString is:{}", ftpClient.getReplyString());
		}
		return flag;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#getLsFileMap(java.lang.String)
	 */
	@Override
	protected Map<String, FileInfoEntity> getLsFileMap(String remotePath) throws Exception{
		Map<String, FileInfoEntity> map = new HashMap<String, FileInfoEntity>();
		FTPFile[] ftpFiles = ftpClient.listFiles(remotePath);
		for (FTPFile ftpFile : ftpFiles){

			String filename = ftpFile.getName();

			boolean isDirectory = ftpFile.isDirectory();

			FileInfoEntity fileEntity = new FileInfoEntity();
			fileEntity.setFileType(isDirectory ? FileType.DIRECTORY : FileType.FILE);
			fileEntity.setName(filename);
			fileEntity.setSize(ftpFile.getSize());
			fileEntity.setLastModified(ftpFile.getTimestamp().getTimeInMillis());

			map.put(filename, fileEntity);
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#rmdir(java.lang.String)
	 */
	@Override
	protected boolean rmdir(String remotePath) throws Exception{
		boolean flag = false;
		// 不知道为什么 此处 需要 先 cd / 否则 删除不了文件夹
		log.info("ftpClient cwd root .....");
		cd("/");

		// if empty
		flag = ftpClient.removeDirectory(remotePath);

		if (flag){
			log.info("ftpClient removeDirectory,remotePath [{}]:[{}]", remotePath, flag);
		}else{
			Object[] params = { remotePath, flag, ftpClient.getReplyCode() };
			log.warn("ftpClient removeDirectory,remotePath [{}]:[{}] ReplyCode:{}", params);
		}
		return flag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#rm(java.lang.String)
	 */
	@Override
	protected boolean rm(String remotePath) throws Exception{
		boolean flag = false;
		log.info("remotePath is [not directory],deleteFile.....");
		flag = ftpClient.deleteFile(remotePath);

		if (flag){
			log.info("ftpClient deleteFile,remotePath[{}] : [{}]", remotePath, flag);
		}else{
			Object[] params = { remotePath, flag, ftpClient.getReplyCode() };
			log.warn("ftpClient deleteFile,remotePath[{}] : [{}] , ReplyCode:[{}]", params);
		}
		return flag;
	}

	/**
	 * Log ftp file.
	 * 
	 * @param ftpFile
	 *            the ftp file
	 */
	private void logFTPFile(FTPFile ftpFile){
		String ftpFileName = ftpFile.getName();
		Object[] params = { ftpFileName, ftpFile.isDirectory(), ftpFile.getType() };
		log.info("ftpFile Name:[{}] ,isDirectory:[{}],ftpFile type:[{}]", params);
	}

	// TODO 可能不精准
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.filetransfer.FileTransfer#isDirectory(java.lang.String)
	 */
	@Override
	protected boolean isDirectory(String remotePath) throws Exception{
		// 可以成功进入 代表 是文件夹
		// 经测试 如果 remotePath 不存在 返回false ,如果是文件 返回false

		// 文件夹 213
		// 文件213
		//
		// int mlsd = ftpClient.mlsd(remotePath);
		// log.info("mlsd:{}", mlsd);
		// FTPListParseEngine ftpListParseEngine = ftpClient.initiateListParsing(remotePath);
		//
		// FTPFile[] files = ftpListParseEngine.getFiles();
		//
		// for (FTPFile ftpFile : files){
		// log.info("ftpListParseEngine:[{}]", ftpFile);
		// }

		// log.debug("remotePath:{}", remotePath);

		// log.info("{}", ftpClient.getStatus(remotePath));
		// boolean success = FTPReply.isPositiveCompletion(sendCommand(FTPCommand.MLST, pathname));
		// if (success){
		// String entry = getReplyStrings()[1].substring(1); // skip leading space for parser
		// return MLSxEntryParser.parseEntry(entry);
		// } else {
		// return null;
		// }

		// FTPFile listFiles = ftpClient.mlistFile(remotePath);
		// log.info("ftpFile getReplyString:[{}]", ftpClient.getReplyString());
		//
		// log.info(listFiles.isDirectory() + "");
		// log.info(ftpClient.getModificationTime(remotePath) + "");
		// for (FTPFile ftpFile : listFiles){
		//
		// }

		return cd(remotePath);

		// return true;
	}

	// *****************************************************************************************************
	/**
	 * Sets the ftp client.
	 * 
	 * @param ftpClient
	 *            the ftpClient to set
	 */
	public void setFtpClient(FTPClient ftpClient){
		this.ftpClient = ftpClient;
	}

	/**
	 * Sets the host name.
	 * 
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName){
		this.hostName = hostName;
	}

	/**
	 * Sets the 用户名,如果用户名和密码有一个为null/"",则使用anonymous/anonymous 登陆.
	 * 
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * Sets the 密码,如果用户名和密码有一个为null/"",则使用anonymous/anonymous 登陆.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#_downRemoteSingleFile(java.lang.String, java.lang.String)
	 */
	@Override
	protected boolean _downRemoteSingleFile(String remoteSingleFile,String filePath) throws Exception{
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
		boolean success = ftpClient.retrieveFile(remoteSingleFile, bufferedOutputStream);

		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		return success;
	}

}