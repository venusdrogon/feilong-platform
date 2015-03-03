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
package com.feilong.tools.chart.index;

/**
 * 指数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 19, 2012 12:43:36 AM
 */
public class SoStatusChartIndex{

	/** 维度,比如按照分钟 维度,秒维度. */
	private String	dimension;

	/** 订单创建量. */
	private Integer	createCount;

	/** 订单付款量. */
	private Integer	paymentCount;

	/** 个人订单取消量. */
	private Integer	personalCancelCount;

	/** 商城订单取消量. */
	private Integer	storeCancelCount;

	/** 客服确认量. */
	private Integer	confirmCount;

	/**
	 * @param dimension
	 * @param createCount
	 */
	public SoStatusChartIndex(String dimension, Integer createCount){
		super();
		this.dimension = dimension;
		this.createCount = createCount;
	}

	/**
	 * @param dimension
	 * @param createCount
	 * @param paymentCount
	 */
	public SoStatusChartIndex(String dimension, Integer createCount, Integer paymentCount){
		super();
		this.dimension = dimension;
		this.createCount = createCount;
		this.paymentCount = paymentCount;
	}

	/**
	 * @param dimension
	 * @param createCount
	 * @param paymentCount
	 * @param personalCancelCount
	 * @param storeCancelCount
	 */
	public SoStatusChartIndex(String dimension, Integer createCount, Integer paymentCount, Integer personalCancelCount,
					Integer storeCancelCount){
		super();
		this.dimension = dimension;
		this.createCount = createCount;
		this.paymentCount = paymentCount;
		this.personalCancelCount = personalCancelCount;
		this.storeCancelCount = storeCancelCount;
	}

	/**
	 * @return the dimension
	 */
	public String getDimension(){
		return dimension;
	}

	/**
	 * @param dimension
	 *            the dimension to set
	 */
	public void setDimension(String dimension){
		this.dimension = dimension;
	}

	/**
	 * @return the createCount
	 */
	public Integer getCreateCount(){
		return createCount;
	}

	/**
	 * @param createCount
	 *            the createCount to set
	 */
	public void setCreateCount(Integer createCount){
		this.createCount = createCount;
	}

	/**
	 * @return the paymentCount
	 */
	public Integer getPaymentCount(){
		return paymentCount;
	}

	/**
	 * @param paymentCount
	 *            the paymentCount to set
	 */
	public void setPaymentCount(Integer paymentCount){
		this.paymentCount = paymentCount;
	}

	/**
	 * @return the personalCancelCount
	 */
	public Integer getPersonalCancelCount(){
		return personalCancelCount;
	}

	/**
	 * @param personalCancelCount
	 *            the personalCancelCount to set
	 */
	public void setPersonalCancelCount(Integer personalCancelCount){
		this.personalCancelCount = personalCancelCount;
	}

	/**
	 * @return the storeCancelCount
	 */
	public Integer getStoreCancelCount(){
		return storeCancelCount;
	}

	/**
	 * @param storeCancelCount
	 *            the storeCancelCount to set
	 */
	public void setStoreCancelCount(Integer storeCancelCount){
		this.storeCancelCount = storeCancelCount;
	}

	/**
	 * @return the confirmCount
	 */
	public Integer getConfirmCount(){
		return confirmCount;
	}

	/**
	 * @param confirmCount
	 *            the confirmCount to set
	 */
	public void setConfirmCount(Integer confirmCount){
		this.confirmCount = confirmCount;
	}

}
