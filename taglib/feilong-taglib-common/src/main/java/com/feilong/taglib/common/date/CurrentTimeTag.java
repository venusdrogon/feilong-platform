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
package com.feilong.taglib.common.date;

import java.util.Date;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 显示当前时间
 * 
 * @author 金鑫 2010-4-13 下午09:17:10
 */
public class CurrentTimeTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 显示模式
	 */
	private String				pattern				= "yyyy-MM-dd";

	@Override
	public String writeContent(){
		return DateUtil.date2String(new Date(), pattern);
	}

	/**
	 * @param pattern
	 *            the pattern to set
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}
}
