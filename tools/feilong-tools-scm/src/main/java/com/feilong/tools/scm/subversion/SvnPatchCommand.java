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
package com.feilong.tools.scm.subversion;

import java.util.ArrayList;
import java.util.List;

import com.feilong.tools.scm.command.ScmPatchCommand;

/**
 * SVN Command.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-11 上午10:16:51
 */
public class SvnPatchCommand extends ScmPatchCommand{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -5625187072452879828L;

	/** 索引的文件行. */
	private String				index;

	/** remote 行. */
	private String				remote;

	/** local 行. */
	private String				local;

	/** 双@(可能有多处). */
	private List<String>		twoAt				= new ArrayList<String>();

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

	/**
	 * Gets the 双@(可能有多处).
	 * 
	 * @return the twoAt
	 */
	public List<String> getTwoAt(){
		return twoAt;
	}

	/**
	 * Sets the 双@(可能有多处).
	 * 
	 * @param twoAt
	 *            the twoAt to set
	 */
	public void setTwoAt(List<String> twoAt){
		this.twoAt = twoAt;
	}

}
