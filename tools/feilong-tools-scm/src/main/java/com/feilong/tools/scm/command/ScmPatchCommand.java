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
package com.feilong.tools.scm.command;

import java.io.Serializable;

/**
 * base command.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-11 上午11:22:47
 */
public abstract class ScmPatchCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 566966317818490406L;

	/**
	 * 文件路径 如 src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java
	 */
	private String				filePath;

	/** 标识类型,默认更新. */
	private PatchType			patchType			= PatchType.UPDATE;

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
