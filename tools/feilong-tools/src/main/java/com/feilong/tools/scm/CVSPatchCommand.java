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

/**
 * 补丁 文件解析.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-5-23 下午7:06:23
 */
public class CVSPatchCommand extends BasePatchCommand{

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
