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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.io.FileType;
import com.feilong.commons.core.util.Validator;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

/**
 * SFTP工具类 注:依赖于jsch.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 3, 2013 1:02:08 PM
 */
public class SFTPUtil extends FileTransfer{

	/** The Constant log. */
	private final static Logger	log				= LoggerFactory.getLogger(SFTPUtil.class);

	/** 主机名. */
	private String				hostName;

	/** 用户名. */
	private String				userName;

	/** 密码. */
	private String				password;

	/** The port. */
	private Integer				port			= 22;

	/** The type. */
	private String				type			= "sftp";

	/** The ssh config. */
	private Properties			sshConfig;

	/** The connect timeout. */
	private int					sessionTimeout	= 0;

	// ***********************************************************************

	/** The channel sftp. */
	private ChannelSftp			channelSftp;

	/** The session. */
	private Session				session;

	/**
	 * 创建 链接.
	 * 
	 * @return true, if successful
	 */
	@Override
	public boolean connect(){

		// 是否连接成功, 默认不成功
		boolean isSuccess = false;

		// If the client is already connected, disconnect
		if (channelSftp != null){
			log.warn("channelSftp is not null,will disconnect first....");
			disconnect();
		}
		try{
			JSch jsch = new JSch();

			session = jsch.getSession(userName, hostName, port);
			Object[] params = { userName, hostName, port };
			log.debug("create session,[{}],[{}],[{}]...", params);

			session.setPassword(password);
			log.debug("session use password:[{}]", password);

			// setConfig
			if (Validator.isNotNullOrEmpty(sshConfig)){
				log.debug("session setConfig:{}", sshConfig);
				session.setConfig(sshConfig);
			}
			session.setTimeout(sessionTimeout);

			log.debug("session connecting......");
			session.connect();

			log.debug("session open channel,type is :[{}]...", type);
			Channel channel = session.openChannel(type);

			log.debug("channel connecting...");
			channel.connect();

			channelSftp = (ChannelSftp) channel;
			isSuccess = channelSftp.isConnected();
			log.debug("channelSftp isConnected:[{}]", isSuccess);
		}catch (JSchException e){
			log.error(e.getClass().getName(), e);
		}
		log.info("connect :{}", isSuccess);
		return isSuccess;
	}

	/**
	 * 关闭链接.
	 */
	@Override
	public void disconnect(){
		if (channelSftp != null){
			channelSftp.exit();
			log.debug("channelSftp exit....");
		}
		if (session != null){
			session.disconnect();
			log.debug("session disconnect....");
		}
		channelSftp = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#getLsFileMap(java.lang.String)
	 */
	@Override
	protected Map<String, FileInfoEntity> getLsFileMap(String remotePath) throws Exception{
		Map<String, FileInfoEntity> map = new HashMap<String, FileInfoEntity>();

		@SuppressWarnings("unchecked")
		Vector<LsEntry> rs = channelSftp.ls(remotePath);
		for (int i = 0; i < rs.size(); i++){
			LsEntry lsEntry = rs.get(i);
			String fileName = lsEntry.getFilename();

			// 本文件夹
			if (".".equals(fileName)){
				continue;
			}
			// 上层目录 均过滤掉,否则会影响 delete
			else if ("..".equals(fileName)){
				continue;
			}

			String path = remotePath + "/" + fileName;
			boolean isDirectory = isDirectory(path);

			log.debug("fileName:{}", fileName);

			SftpATTRS attrs = lsEntry.getAttrs();

			FileInfoEntity fileEntity = new FileInfoEntity();
			fileEntity.setFileType(isDirectory ? FileType.DIRECTORY : FileType.FILE);
			fileEntity.setName(fileName);
			fileEntity.setSize(attrs.getSize());
			// returns the last modification time.
			fileEntity.setLastModified(Long.parseLong(attrs.getMTime() + ""));

			map.put(fileName, fileEntity);
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.AbstractFileTransfer#mkdir(java.lang.String)
	 */
	@Override
	protected boolean mkdir(String dirName) throws Exception{
		log.info("channelSftp mkdir:{} ", dirName);
		channelSftp.mkdir(dirName);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.AbstractFileTransfer#cd(java.lang.String)
	 */
	@Override
	public boolean cd(String remoteDirectory) throws Exception{
		log.info("channelSftp cd:[{}]", remoteDirectory);
		channelSftp.cd(remoteDirectory);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#put(java.io.FileInputStream, java.lang.String)
	 */
	@Override
	protected boolean put(FileInputStream fileInputStream_local,String toFileName) throws Exception{
		log.info("channelSftp put:{}", toFileName);
		channelSftp.put(fileInputStream_local, toFileName);
		return true;
	}

	/**
	 * Checks if is dir.
	 * 
	 * @param path
	 *            the path
	 * @return true, if is dir
	 * @throws Exception
	 *             the exception
	 */
	@Override
	public boolean isDirectory(String path) throws Exception{
		SftpATTRS sftpATTRS = channelSftp.stat(path);

		boolean isDir = sftpATTRS.isDir();

		log.debug("path:[{}] isDir:[{}]", path, isDir);
		return isDir;

	}

	/**
	 * Gets the working directory.
	 * 
	 * @return the working directory
	 * @throws Exception
	 *             the exception
	 */
	public String getWorkingDirectory() throws Exception{
		return channelSftp.pwd();
	}

	/**
	 * Sets the 主机名.
	 * 
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName){
		this.hostName = hostName;
	}

	/**
	 * Sets the 用户名.
	 * 
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * Sets the 密码.
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * Gets the 密码.
	 * 
	 * @return the password
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * Sets the port.
	 * 
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port){
		this.port = port;
	}

	/**
	 * Sets the ssh config.
	 * 
	 * @param sshConfig
	 *            the sshConfig to set
	 */
	public void setSshConfig(Properties sshConfig){
		this.sshConfig = sshConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#rmdir(java.lang.String)
	 */
	@Override
	protected boolean rmdir(String remotePath) throws Exception{
		channelSftp.rmdir(remotePath);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#rm(java.lang.String)
	 */
	@Override
	protected boolean rm(String remotePath) throws Exception{
		channelSftp.rm(remotePath);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.tools.net.FileTransfer#_downRemoteSingleFile(java.lang.String, java.lang.String)
	 */
	@Override
	protected boolean _downRemoteSingleFile(String remoteSingleFile,String filePath) throws Exception{
		OutputStream outputStream = new FileOutputStream(filePath);
		channelSftp.get(remoteSingleFile, outputStream);
		return true;
	}

	/**
	 * @param sessionTimeout
	 *            the sessionTimeout to set
	 */
	public void setSessionTimeout(int sessionTimeout){
		this.sessionTimeout = sessionTimeout;
	}
}