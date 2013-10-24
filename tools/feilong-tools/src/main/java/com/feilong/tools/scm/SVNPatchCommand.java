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
package com.feilong.tools.scm;

import java.util.ArrayList;
import java.util.List;

/**
 * SVN Command.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-7-11 上午10:16:51
 */
public class SVNPatchCommand extends BasePatchCommand{

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
