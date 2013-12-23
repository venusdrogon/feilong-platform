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
package com.feilong.tools.chart.index;

/**
 * 图片指数(基本的).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 19, 2012 12:43:36 AM
 */
public final class ChartIndex{

	/** 编码. */
	private String	code;

	/** 名称. */
	private String	name;

	/** 值. */
	private Integer	value;

	/**
	 * The Constructor.
	 * 
	 * @param name
	 *            the name
	 * @param code
	 *            the code
	 * @param value
	 *            the value
	 */
	public ChartIndex(String code, String name, Integer value){
		super();
		this.code = code;
		this.name = name;
		this.value = value;
	}

	/**
	 * The Constructor.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public ChartIndex(String name, Integer value){
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * The Constructor.
	 */
	public ChartIndex(){
		super();
	}

	/**
	 * Gets the 名称.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the 名称.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the 编码.
	 * 
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Sets the 编码.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
	}

	/**
	 * Gets the 值.
	 * 
	 * @return the value
	 */
	public Integer getValue(){
		return value;
	}

	/**
	 * Sets the 值.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value){
		this.value = value;
	}

}
