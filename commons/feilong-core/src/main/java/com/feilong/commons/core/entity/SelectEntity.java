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
 *自定义下拉框 包含 值和显示文本
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-8-11 上午11:27:47
 * @since 1.0
 */
@Deprecated
public class SelectEntity implements Serializable{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 值
	 */
	private int					value;

	/**
	 * 显示的文本
	 */
	private int					label;

	public SelectEntity(){}

	/**
	 * @param value
	 *            值
	 * @param label
	 *            显示的文本
	 */
	public SelectEntity(int value, int label){
		this.value = value;
		this.label = label;
	}

	public int getValue(){
		return value;
	}

	public void setValue(int value){
		this.value = value;
	}

	public int getLabel(){
		return label;
	}

	public void setLabel(int label){
		this.label = label;
	}
}