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
package com.feilong.commons.core.entity;

import java.io.Serializable;

import com.feilong.commons.core.enumeration.FileType;

/**
 * 文件信息 entity
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 7, 2013 8:13:34 PM
 */
public class FileInfoEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 名称. */
	private String				name;

	/** 类型. */
	private FileType			fileType;

	/** 文件大小， 单位 字节,如果是文件夹 不显示size. */
	private Long				size;

	/**
	 * 返回此抽象路径名表示的文件最后一次被修改的时间。 <br>
	 * 表示文件最后一次被修改的时间的 long 值，用与时间点（1970 年 1 月 1 日，00:00:00 GMT）之间的毫秒数表示；如果该文件不存在，或者发生 I/O 错误，则返回 0L.
	 */
	private Long				lastModified;

	/**
	 * Gets the 名称.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the 名称.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the 类型.
	 * 
	 * @return the fileType
	 */
	public FileType getFileType(){
		return fileType;
	}

	/**
	 * Sets the 类型.
	 * 
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(FileType fileType){
		this.fileType = fileType;
	}

	/**
	 * Gets the 文件大小， 单位 字节,如果是文件夹 不显示size.
	 * 
	 * @return the size
	 */
	public Long getSize(){
		return size;
	}

	/**
	 * Sets the 文件大小， 单位 字节,如果是文件夹 不显示size.
	 * 
	 * @param size
	 *            the size to set
	 */
	public void setSize(Long size){
		this.size = size;
	}

	/**
	 * Gets the 返回此抽象路径名表示的文件最后一次被修改的时间。 <br>
	 * 表示文件最后一次被修改的时间的 long 值，用与时间点（1970 年 1 月 1 日，00:00:00 GMT）之间的毫秒数表示；如果该文件不存在，或者发生 I/O 错误，则返回 0L.
	 * 
	 * @return the lastModified
	 */
	public Long getLastModified(){
		return lastModified;
	}

	/**
	 * Sets the 返回此抽象路径名表示的文件最后一次被修改的时间。 <br>
	 * 表示文件最后一次被修改的时间的 long 值，用与时间点（1970 年 1 月 1 日，00:00:00 GMT）之间的毫秒数表示；如果该文件不存在，或者发生 I/O 错误，则返回 0L.
	 * 
	 * @param lastModified
	 *            the lastModified to set
	 */
	public void setLastModified(Long lastModified){
		this.lastModified = lastModified;
	}

}
