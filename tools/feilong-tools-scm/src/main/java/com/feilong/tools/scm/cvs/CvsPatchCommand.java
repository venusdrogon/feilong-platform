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
package com.feilong.tools.scm.cvs;

import com.feilong.tools.scm.command.ScmPatchCommand;

/**
 * 补丁 文件解析.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:06:23
 */
public class CvsPatchCommand extends ScmPatchCommand{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 2469900787893755928L;

	/** 索引的文件行. */
	private String				index;

	/** rcs 行. */
	private String				rcs;

	/** diff 行. */
	private String				diff;

	/** remote 行. */
	private String				remote;

	/** local 行. */
	private String				local;

	/**
	 * Gets the 索引的文件行.
	 * 
	 * @return the index
	 */
	public String getIndex(){
		return index;
	}

	/**
	 * Sets the 索引的文件行.
	 * 
	 * @param index
	 *            the index to set
	 */
	public void setIndex(String index){
		this.index = index;
	}

	/**
	 * Gets the rcs 行.
	 * 
	 * @return the rcs
	 */
	public String getRcs(){
		return rcs;
	}

	/**
	 * Sets the rcs 行.
	 * 
	 * @param rcs
	 *            the rcs to set
	 */
	public void setRcs(String rcs){
		this.rcs = rcs;
	}

	/**
	 * Gets the diff 行.
	 * 
	 * @return the diff
	 */
	public String getDiff(){
		return diff;
	}

	/**
	 * Sets the diff 行.
	 * 
	 * @param diff
	 *            the diff to set
	 */
	public void setDiff(String diff){
		this.diff = diff;
	}

	/**
	 * Gets the remote 行.
	 * 
	 * @return the remote
	 */
	public String getRemote(){
		return remote;
	}

	/**
	 * Sets the remote 行.
	 * 
	 * @param remote
	 *            the remote to set
	 */
	public void setRemote(String remote){
		this.remote = remote;
	}

	/**
	 * Gets the local 行.
	 * 
	 * @return the local
	 */
	public String getLocal(){
		return local;
	}

	/**
	 * Sets the local 行.
	 * 
	 * @param local
	 *            the local to set
	 */
	public void setLocal(String local){
		this.local = local;
	}

}
