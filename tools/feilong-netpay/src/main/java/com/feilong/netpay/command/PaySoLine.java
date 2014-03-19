/**
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
package com.feilong.netpay.command;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付订单明细行(独立的entity,不受官方商城版本影响).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 13, 2014 2:02:39 PM
 */
public class PaySoLine implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 购买商品的名称. */
	private String				itemName;

	/** 支付单价. */
	private BigDecimal			unitPrice;

	/** 购买数量. */
	private Integer				quantity;

	/** 小计. */
	private BigDecimal			subTotalPrice;

	/**
	 * Gets the 购买商品的名称.
	 * 
	 * @return the itemName
	 */
	public String getItemName(){
		return itemName;
	}

	/**
	 * Sets the 购买商品的名称.
	 * 
	 * @param itemName
	 *            the itemName to set
	 */
	public void setItemName(String itemName){
		this.itemName = itemName;
	}

	/**
	 * Gets the 支付单价.
	 * 
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice(){
		return unitPrice;
	}

	/**
	 * Sets the 支付单价.
	 * 
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}

	/**
	 * Gets the 购买数量.
	 * 
	 * @return the quantity
	 */
	public Integer getQuantity(){
		return quantity;
	}

	/**
	 * Sets the 购买数量.
	 * 
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	/**
	 * Gets the 小计.
	 * 
	 * @return the subTotalPrice
	 */
	public BigDecimal getSubTotalPrice(){
		return subTotalPrice;
	}

	/**
	 * Sets the 小计.
	 * 
	 * @param subTotalPrice
	 *            the subTotalPrice to set
	 */
	public void setSubTotalPrice(BigDecimal subTotalPrice){
		this.subTotalPrice = subTotalPrice;
	}
}
