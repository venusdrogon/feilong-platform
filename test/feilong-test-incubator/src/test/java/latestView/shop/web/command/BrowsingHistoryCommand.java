/**
 * Copyright (c) 2008-2012 FeiLong, Inc. All Rights Reserved.
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
package latestView.shop.web.command;

import java.io.Serializable;

/**
 * 浏览历史.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午2:18:01
 */
public class BrowsingHistoryCommand implements Serializable,Comparable<BrowsingHistoryCommand>{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 商品信息. */
	private SimpleSkuCommand	simpleSkuCommand;

	/** 浏览时间 (毫秒)为了减少 json 长度,声明成long 类型. */
	private long				date;

	/**
	 * Gets the 商品信息.
	 * 
	 * @return the simpleSkuCommand
	 */
	public SimpleSkuCommand getSimpleSkuCommand(){
		return simpleSkuCommand;
	}

	/**
	 * Sets the 商品信息.
	 * 
	 * @param simpleSkuCommand
	 *            the simpleSkuCommand to set
	 */
	public void setSimpleSkuCommand(SimpleSkuCommand simpleSkuCommand){
		this.simpleSkuCommand = simpleSkuCommand;
	}

	/**
	 * Gets the 浏览时间 (毫秒)为了减少 json 长度,声明成long 类型.
	 * 
	 * @return the date
	 */
	public long getDate(){
		return date;
	}

	/**
	 * Sets the 浏览时间 (毫秒)为了减少 json 长度,声明成long 类型.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(long date){
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (null == this.simpleSkuCommand && null == obj){
			return true;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		BrowsingHistoryCommand browsingHistoryCommand = (BrowsingHistoryCommand) obj;
		// 只要 simpleSkuCommand equals 就认为 equals
		if (this.simpleSkuCommand.equals(browsingHistoryCommand.getSimpleSkuCommand())){
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BrowsingHistoryCommand browsingHistoryCommand){
		if (this.equals(browsingHistoryCommand)){
			return 0;
		}
		// 最近访问的排前面
		if (this.date > browsingHistoryCommand.getDate()){
			return -1;
		}else if (this.date < browsingHistoryCommand.getDate()){
			return 1;
		}
		return 0;
	}
}
