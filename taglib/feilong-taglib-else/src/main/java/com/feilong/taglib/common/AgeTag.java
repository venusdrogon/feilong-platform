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

/**
 * 出生日期转换为年龄
 * 
 * @author 金鑫 2009-9-7上午11:49:48
 */
@Deprecated
public class AgeTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 出生日期
	 */
	private String				birthday;

	@Override
	public String writeContent(){
//		return SelectHelper.convertBirthdayToAge(birthday) + "";
		return null;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}
}
