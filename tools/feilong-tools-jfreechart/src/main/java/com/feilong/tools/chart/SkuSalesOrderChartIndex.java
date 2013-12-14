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
package com.feilong.tools.chart;

/**
 * 商品下单指数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 14, 2013 11:23:20 AM
 */
public final class SkuSalesOrderChartIndex{

	/** 商品code. */
	private String	skuCode;

	/** 总库存. */
	private Integer	totalInventoryCount;

	/** 下单数. */
	private Integer	createSalesOrderCount;

	/** 个人取消数量. */
	private Integer	personalCancelCount;

	/** 商城取消数量. */
	private Integer	storeCancelCount;

	/**
	 * Instantiates a new sku sales order chart index.
	 * 
	 * @param skuCode
	 *            the sku code
	 * @param totalInventoryCount
	 *            the total inventory count
	 * @param createSalesOrderCount
	 *            the create sales order count
	 * @param personalCancelCount
	 *            the personal cancel count
	 * @param storeCancelCount
	 *            the store cancel count
	 */
	public SkuSalesOrderChartIndex(String skuCode, Integer totalInventoryCount, Integer createSalesOrderCount, Integer personalCancelCount,
			Integer storeCancelCount){
		super();
		this.skuCode = skuCode;
		this.createSalesOrderCount = createSalesOrderCount;
		this.personalCancelCount = personalCancelCount;
		this.storeCancelCount = storeCancelCount;
		this.totalInventoryCount = totalInventoryCount;
	}

	/**
	 * Gets the 商品code.
	 * 
	 * @return the skuCode
	 */
	public String getSkuCode(){
		return skuCode;
	}

	/**
	 * Sets the 商品code.
	 * 
	 * @param skuCode
	 *            the skuCode to set
	 */
	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}

	/**
	 * Gets the 下单数.
	 * 
	 * @return the createSalesOrderCount
	 */
	public Integer getCreateSalesOrderCount(){
		return createSalesOrderCount;
	}

	/**
	 * Sets the 下单数.
	 * 
	 * @param createSalesOrderCount
	 *            the createSalesOrderCount to set
	 */
	public void setCreateSalesOrderCount(Integer createSalesOrderCount){
		this.createSalesOrderCount = createSalesOrderCount;
	}

	/**
	 * Gets the 个人取消数量.
	 * 
	 * @return the personalCancelCount
	 */
	public Integer getPersonalCancelCount(){
		return personalCancelCount;
	}

	/**
	 * Sets the 个人取消数量.
	 * 
	 * @param personalCancelCount
	 *            the personalCancelCount to set
	 */
	public void setPersonalCancelCount(Integer personalCancelCount){
		this.personalCancelCount = personalCancelCount;
	}

	/**
	 * Gets the 商城取消数量.
	 * 
	 * @return the storeCancelCount
	 */
	public Integer getStoreCancelCount(){
		return storeCancelCount;
	}

	/**
	 * Sets the 商城取消数量.
	 * 
	 * @param storeCancelCount
	 *            the storeCancelCount to set
	 */
	public void setStoreCancelCount(Integer storeCancelCount){
		this.storeCancelCount = storeCancelCount;
	}

	/**
	 * Gets the 总库存.
	 * 
	 * @return the totalInventoryCount
	 */
	public Integer getTotalInventoryCount(){
		return totalInventoryCount;
	}

	/**
	 * Sets the 总库存.
	 * 
	 * @param totalInventoryCount
	 *            the totalInventoryCount to set
	 */
	public void setTotalInventoryCount(Integer totalInventoryCount){
		this.totalInventoryCount = totalInventoryCount;
	}

}
