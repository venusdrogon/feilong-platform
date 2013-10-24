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
package loxia.support.json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Shopping.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 27, 2012 12:17:11 AM
 */
public class Shopping{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(Shopping.class);

	/** 月份. */
	private Integer				month;

	/** 买了什么东西. */
	private String				productName;

	/** 价钱. */
	private Integer				price;

	/**
	 * @param month
	 * @param productName
	 * @param price
	 */
	public Shopping(Integer month, String productName, Integer price){
		super();
		this.month = month;
		this.productName = productName;
		this.price = price;
	}

	/**
	 * Gets the 月份.
	 * 
	 * @return the month
	 */
	public Integer getMonth(){
		return month;
	}

	/**
	 * Sets the 月份.
	 * 
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month){
		this.month = month;
	}

	/**
	 * Gets the 买了什么东西.
	 * 
	 * @return the productName
	 */
	public String getProductName(){
		return productName;
	}

	/**
	 * Sets the 买了什么东西.
	 * 
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName){
		this.productName = productName;
	}

	/**
	 * Gets the 价钱.
	 * 
	 * @return the price
	 */
	public Integer getPrice(){
		return price;
	}

	/**
	 * Sets the 价钱.
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Integer price){
		this.price = price;
	}
}
