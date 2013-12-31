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
package com.feilong.taglib.common.standard;

import javax.servlet.jsp.tagext.Tag;

import com.feilong.taglib.base.BaseTag;

/**
 * default标签 配合switch标签使用
 * 
 * @author 金鑫 2010年3月19日 11:24:28
 */
public class SwitchDefaultTag extends BaseTag{

	private static final long	serialVersionUID	= 1L;

	@Override
	public int doStartTag(){
		Tag tag = getParent();
		SwitchTag switchTag = (SwitchTag) tag;
		if (!switchTag.isExecuteTag()){
			switchTag.setExecuteTag();
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}
}
