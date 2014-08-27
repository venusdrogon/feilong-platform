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
package com.feilong.controller.chart.mp2.person;

import java.io.Serializable;

/**
 * The Class MP2PersonCountIndex.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 12, 2014 4:01:33 AM
 */
public class MP2PersonCountIndex implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 分组的类型. */
	private String				groupType;

	/** The has passport count. */
	private Integer				hasPassportCount	= 0;

	/** The chunjie count. */
	private Integer				chunjieCount		= 0;

	/** The no tongji count. */
	private Integer				noTongjiCount		= 0;

	/** The banbuliao count. */
	private Integer				banbuliaoCount		= 0;

	/** The no feed back. */
	private Integer				noFeedBack			= 0;

	/** The shanghaiban. */
	private Integer				shanghaiban			= 0;

	/** The lizhiCount. */
	private Integer				lizhiCount			= 0;

	/**
	 * Gets the checks for passport count.
	 * 
	 * @return the hasPassportCount
	 */
	public Integer getHasPassportCount(){
		return hasPassportCount;
	}

	/**
	 * Sets the checks for passport count.
	 * 
	 * @param hasPassportCount
	 *            the hasPassportCount to set
	 */
	public void setHasPassportCount(Integer hasPassportCount){
		this.hasPassportCount = hasPassportCount;
	}

	/**
	 * Gets the chunjie count.
	 * 
	 * @return the chunjieCount
	 */
	public Integer getChunjieCount(){
		return chunjieCount;
	}

	/**
	 * Sets the chunjie count.
	 * 
	 * @param chunjieCount
	 *            the chunjieCount to set
	 */
	public void setChunjieCount(Integer chunjieCount){
		this.chunjieCount = chunjieCount;
	}

	/**
	 * Gets the no tongji count.
	 * 
	 * @return the noTongjiCount
	 */
	public Integer getNoTongjiCount(){
		return noTongjiCount;
	}

	/**
	 * Sets the no tongji count.
	 * 
	 * @param noTongjiCount
	 *            the noTongjiCount to set
	 */
	public void setNoTongjiCount(Integer noTongjiCount){
		this.noTongjiCount = noTongjiCount;
	}

	/**
	 * Gets the banbuliao count.
	 * 
	 * @return the banbuliaoCount
	 */
	public Integer getBanbuliaoCount(){
		return banbuliaoCount;
	}

	/**
	 * Sets the banbuliao count.
	 * 
	 * @param banbuliaoCount
	 *            the banbuliaoCount to set
	 */
	public void setBanbuliaoCount(Integer banbuliaoCount){
		this.banbuliaoCount = banbuliaoCount;
	}

	/**
	 * Gets the no feed back.
	 * 
	 * @return the noFeedBack
	 */
	public Integer getNoFeedBack(){
		return noFeedBack;
	}

	/**
	 * Sets the no feed back.
	 * 
	 * @param noFeedBack
	 *            the noFeedBack to set
	 */
	public void setNoFeedBack(Integer noFeedBack){
		this.noFeedBack = noFeedBack;
	}

	/**
	 * Gets the shanghaiban.
	 * 
	 * @return the shanghaiban
	 */
	public Integer getShanghaiban(){
		return shanghaiban;
	}

	/**
	 * Sets the shanghaiban.
	 * 
	 * @param shanghaiban
	 *            the shanghaiban to set
	 */
	public void setShanghaiban(Integer shanghaiban){
		this.shanghaiban = shanghaiban;
	}

	/**
	 * @param groupType
	 *            the groupType to set
	 */
	public void setGroupType(String groupType){
		this.groupType = groupType;
	}

	/**
	 * @return the groupType
	 */
	public String getGroupType(){
		return groupType;
	}

	/**
	 * @return the lizhiCount
	 */
	public Integer getLizhiCount(){
		return lizhiCount;
	}

	/**
	 * @param lizhiCount
	 *            the lizhiCount to set
	 */
	public void setLizhiCount(Integer lizhiCount){
		this.lizhiCount = lizhiCount;
	}
}
