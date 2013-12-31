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

import com.feilong.taglib.base.AbstractCommonTag;
import com.feilong.tools.html.HTMLSpan;

/**
 * 数字标签
 * 
 * @author 金鑫 2009-7-1下午03:59:46
 */
@Deprecated
public class DecimalTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 数字值
	 */
	private double				value;

	@Override
	public String writeContent(){
		return HTMLSpan.createSpan_decimalAddColor(value);
	}

	/**
	 * 数字值
	 * 
	 * @param value
	 */
	public void setValue(double value){
		this.value = value;
	}
}
