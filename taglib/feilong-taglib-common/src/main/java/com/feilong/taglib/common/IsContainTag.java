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
package com.feilong.taglib.common;

import com.feilong.commons.core.util.ListUtil;
import com.feilong.taglib.base.AbstractConditionalTag;

/**
 * 判断一个值是否在一个集合(或者可以被转成Iterator)当中.
 * 
 * @author 金鑫 2010-7-5 下午01:16:43
 */
public class IsContainTag extends AbstractConditionalTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator. */
	private Object				collection			= null;

	/** 任意类型的值,最终toString 判断比较. */
	private Object				value				= null;

	/*
	 * (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractConditionalTag#condition()
	 */
	@Override
	public boolean condition(){
		return ListUtil._isContainTag(collection, value);
	}

	/**
	 * Sets the 一个集合,将会被转成Iterator,可以为逗号隔开的字符串,会被分隔成Iterator.
	 * 
	 * @param collection
	 *            the collection to set
	 */
	public void setCollection(Object collection){
		this.collection = collection;
	}

	/**
	 * Sets the 一个值.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value){
		this.value = value;
	}
}
