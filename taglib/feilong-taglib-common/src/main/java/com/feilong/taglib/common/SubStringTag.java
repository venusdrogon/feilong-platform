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

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 截取页面显示内容,超过长度用省略号表示
 * 
 * @author 金鑫2009-9-29上午10:56:56
 */
public class SubStringTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 需要被截取的内容
	 */
	private String				content;

	/**
	 * 截取长度
	 */
	private int					length;

	@Override
	public String writeContent(){
		String returnValue = "";
		if (Validator.isNotNullOrEmpty(content)){
			content = content.trim();
			if (content.length() > length){
				returnValue = content.substring(0, length) + "...";
			}else{
				returnValue = content;
			}
		}
		return returnValue;
	}

	public void setContent(String content){
		this.content = content;
	}

	public void setLength(int length){
		this.length = length;
	}
}
