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
package com.feilong.commons.core.entity;

import java.io.Serializable;

/**
 * 返回提示.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-6-24 上午03:14:56
 * @since 1.0
 */
public class BackWarnEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 是否成功. */
	private boolean				isSuccess;

	/** 描述. */
	private Object				description;

	/**
	 * Instantiates a new back warn entity.
	 */
	public BackWarnEntity(){}

	/**
	 * Instantiates a new back warn entity.
	 * 
	 * @param isSuccess
	 *            the is success
	 * @param description
	 *            the description
	 */
	public BackWarnEntity(boolean isSuccess, Object description){
		this.isSuccess = isSuccess;
		this.description = description;
	}

	/**
	 * Gets the 是否成功.
	 * 
	 * @return the isSuccess
	 */
	public boolean getIsSuccess(){
		return isSuccess;
	}

	/**
	 * Sets the 是否成功.
	 * 
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	/**
	 * Gets the 描述.
	 * 
	 * @return the description
	 */
	public Object getDescription(){
		return description;
	}

	/**
	 * Sets the 描述.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(Object description){
		this.description = description;
	}

}
