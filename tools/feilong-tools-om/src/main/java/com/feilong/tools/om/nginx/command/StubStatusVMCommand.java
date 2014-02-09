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
package com.feilong.tools.om.nginx.command;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * StubStatusVMCommand.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 1, 2014 12:47:52 AM
 */
public final class StubStatusVMCommand implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long		serialVersionUID	= 288232184048495608L;

	/** 第一条记录时间, 开始抓取的时间. */
	private Date					beginDate;

	/** 最后一条记录的时间, 结束抓取的时间. */
	private Date					endDate;

	/** 最大值的时候. */
	private StubStatusCommand		maxActiveConnectionsStubStatusCommand;

	/** 最小值的时候. */
	private StubStatusCommand		minActiveConnectionsStubStatusCommand;

	/** The nginx stub status command list. */
	private List<StubStatusCommand>	stubStatusCommandList;

	/**
	 * Gets the 第一条记录时间, 开始抓取的时间.
	 * 
	 * @return the beginDate
	 */
	public Date getBeginDate(){
		return beginDate;
	}

	/**
	 * Sets the 第一条记录时间, 开始抓取的时间.
	 * 
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate){
		this.beginDate = beginDate;
	}

	/**
	 * Gets the 最后一条记录的时间, 结束抓取的时间.
	 * 
	 * @return the endDate
	 */
	public Date getEndDate(){
		return endDate;
	}

	/**
	 * Sets the 最后一条记录的时间, 结束抓取的时间.
	 * 
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate){
		this.endDate = endDate;
	}

	/**
	 * Gets the nginx stub status command list.
	 * 
	 * @return the stubStatusCommandList
	 */
	public List<StubStatusCommand> getStubStatusCommandList(){
		return stubStatusCommandList;
	}

	/**
	 * Sets the nginx stub status command list.
	 * 
	 * @param stubStatusCommandList
	 *            the stubStatusCommandList to set
	 */
	public void setStubStatusCommandList(List<StubStatusCommand> stubStatusCommandList){
		this.stubStatusCommandList = stubStatusCommandList;
	}

	/**
	 * Gets the 最大值的时候.
	 * 
	 * @return the maxActiveConnectionsStubStatusCommand
	 */
	public StubStatusCommand getMaxActiveConnectionsStubStatusCommand(){
		return maxActiveConnectionsStubStatusCommand;
	}

	/**
	 * Sets the 最大值的时候.
	 * 
	 * @param maxActiveConnectionsStubStatusCommand
	 *            the maxActiveConnectionsStubStatusCommand to set
	 */
	public void setMaxActiveConnectionsStubStatusCommand(StubStatusCommand maxActiveConnectionsStubStatusCommand){
		this.maxActiveConnectionsStubStatusCommand = maxActiveConnectionsStubStatusCommand;
	}

	/**
	 * Gets the 最小值的时候.
	 * 
	 * @return the minActiveConnectionsStubStatusCommand
	 */
	public StubStatusCommand getMinActiveConnectionsStubStatusCommand(){
		return minActiveConnectionsStubStatusCommand;
	}

	/**
	 * Sets the 最小值的时候.
	 * 
	 * @param minActiveConnectionsStubStatusCommand
	 *            the minActiveConnectionsStubStatusCommand to set
	 */
	public void setMinActiveConnectionsStubStatusCommand(StubStatusCommand minActiveConnectionsStubStatusCommand){
		this.minActiveConnectionsStubStatusCommand = minActiveConnectionsStubStatusCommand;
	}

}