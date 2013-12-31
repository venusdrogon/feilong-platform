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
package com.feilong.tools.scm;

import java.io.Serializable;

/**
 * base command.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-11 上午11:22:47
 */
public abstract class BasePatchCommand implements Serializable{

	private static final long	serialVersionUID	= 566966317818490406L;

	/**
	 * 文件路径 如 src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java
	 */
	private String				filePath;

	/** 标识类型,默认更新. */
	private PatchType			patchType			= PatchType.update;

	/**
	 * Gets the 文件路径 如 src/main/java/com/jumbo/shop/web/command/PageCacheCommand.
	 * 
	 * @return the filePath
	 */
	public String getFilePath(){
		return filePath;
	}

	/**
	 * Sets the 文件路径 如 src/main/java/com/jumbo/shop/web/command/PageCacheCommand.
	 * 
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	/**
	 * Gets the 标识类型,默认更新.
	 * 
	 * @return the patchType
	 */
	public PatchType getPatchType(){
		return patchType;
	}

	/**
	 * Sets the 标识类型,默认更新.
	 * 
	 * @param patchType
	 *            the patchType to set
	 */
	public void setPatchType(PatchType patchType){
		this.patchType = patchType;
	}
}
