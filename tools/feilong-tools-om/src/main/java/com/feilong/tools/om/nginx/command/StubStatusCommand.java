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

/**
 * The Class StubStatusCommand.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 3:42:00 AM
 */
public class StubStatusCommand implements Serializable,Comparable<StubStatusCommand>{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/**
	 * 当前 Nginx 正处理的活动连接数。 <br>
	 * Active connections: 70.
	 */
	private Integer				activeConnections;

	// server accepts handled requests
	// 14553819 14553819 19239266
	/** 总共处理了 14553819 个连接. */
	private Long				serverAccepts;

	/** 成功创建 14553819 次握手 ( 证明中间没有失败的 ). */
	private Long				serverHandled;

	/**
	 * 总共处理了 19239266 个请求 ( 平均每次握手处理了 1.3 个数据请求 )
	 */
	private Long				serverRequests;

	/**
	 * nginx 读取到客户端的 Header 信息数。<br>
	 * Reading: 0.
	 */
	private Integer				reading;

	/**
	 * nginx 返回给客户端的 Header 信息数 <br>
	 * Writing: 3.
	 */
	private Integer				writing;

	/**
	 * 开启 keep-alive 的情况下，这个值等于 active - (reading + writing)，<br>
	 * 意思就是 Nginx 已经处理完正在等候下一次请求指令的驻留连接。<br>
	 * Waiting: 67.
	 */
	private Integer				waiting;

	/** 抓取时间. */
	private Date				crawlDate;

	/**
	 * Gets the 当前 Nginx 正处理的活动连接数。 <br>
	 * Active connections: 70.
	 * 
	 * @return the activeConnections
	 */
	public Integer getActiveConnections(){
		return activeConnections;
	}

	/**
	 * Sets the 当前 Nginx 正处理的活动连接数。 <br>
	 * Active connections: 70.
	 * 
	 * @param activeConnections
	 *            the activeConnections to set
	 */
	public void setActiveConnections(Integer activeConnections){
		this.activeConnections = activeConnections;
	}

	/**
	 * Gets the 总共处理了 14553819 个连接.
	 * 
	 * @return the serverAccepts
	 */
	public Long getServerAccepts(){
		return serverAccepts;
	}

	/**
	 * Sets the 总共处理了 14553819 个连接.
	 * 
	 * @param serverAccepts
	 *            the serverAccepts to set
	 */
	public void setServerAccepts(Long serverAccepts){
		this.serverAccepts = serverAccepts;
	}

	/**
	 * Gets the 成功创建 14553819 次握手 ( 证明中间没有失败的 ).
	 * 
	 * @return the serverHandled
	 */
	public Long getServerHandled(){
		return serverHandled;
	}

	/**
	 * Sets the 成功创建 14553819 次握手 ( 证明中间没有失败的 ).
	 * 
	 * @param serverHandled
	 *            the serverHandled to set
	 */
	public void setServerHandled(Long serverHandled){
		this.serverHandled = serverHandled;
	}

	/**
	 * Gets the 总共处理了 19239266 个请求 ( 平均每次握手处理了 1.
	 * 
	 * @return the serverRequests
	 */
	public Long getServerRequests(){
		return serverRequests;
	}

	/**
	 * Sets the 总共处理了 19239266 个请求 ( 平均每次握手处理了 1.
	 * 
	 * @param serverRequests
	 *            the serverRequests to set
	 */
	public void setServerRequests(Long serverRequests){
		this.serverRequests = serverRequests;
	}

	/**
	 * Gets the nginx 读取到客户端的 Header 信息数。<br>
	 * Reading: 0.
	 * 
	 * @return the reading
	 */
	public Integer getReading(){
		return reading;
	}

	/**
	 * Sets the nginx 读取到客户端的 Header 信息数。<br>
	 * Reading: 0.
	 * 
	 * @param reading
	 *            the reading to set
	 */
	public void setReading(Integer reading){
		this.reading = reading;
	}

	/**
	 * Gets the nginx 返回给客户端的 Header 信息数 <br>
	 * Writing: 3.
	 * 
	 * @return the writing
	 */
	public Integer getWriting(){
		return writing;
	}

	/**
	 * Sets the nginx 返回给客户端的 Header 信息数 <br>
	 * Writing: 3.
	 * 
	 * @param writing
	 *            the writing to set
	 */
	public void setWriting(Integer writing){
		this.writing = writing;
	}

	/**
	 * Gets the 开启 keep-alive 的情况下，这个值等于 active - (reading + writing)，<br>
	 * 意思就是 Nginx 已经处理完正在等候下一次请求指令的驻留连接。<br>
	 * Waiting: 67.
	 * 
	 * @return the waiting
	 */
	public Integer getWaiting(){
		return waiting;
	}

	/**
	 * Sets the 开启 keep-alive 的情况下，这个值等于 active - (reading + writing)，<br>
	 * 意思就是 Nginx 已经处理完正在等候下一次请求指令的驻留连接。<br>
	 * Waiting: 67.
	 * 
	 * @param waiting
	 *            the waiting to set
	 */
	public void setWaiting(Integer waiting){
		this.waiting = waiting;
	}

	/**
	 * Gets the 抓取时间.
	 * 
	 * @return the crawlDate
	 */
	public Date getCrawlDate(){
		return crawlDate;
	}

	/**
	 * Sets the 抓取时间.
	 * 
	 * @param crawlDate
	 *            the crawlDate to set
	 */
	public void setCrawlDate(Date crawlDate){
		this.crawlDate = crawlDate;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(StubStatusCommand o){
		if (this.activeConnections == o.getActiveConnections()){
			return 0;
		}else if (this.activeConnections < o.getActiveConnections()){
			return -1;
		}
		return 1;
	}

}
