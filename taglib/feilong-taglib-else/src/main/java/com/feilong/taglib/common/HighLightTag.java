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
 * 关键字高亮显示标签
 * 
 * @author 金鑫 2009-11-11上午10:36:28
 */
@Deprecated
public class HighLightTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 一串文字
	 */
	private String				content;

	/**
	 * 需要高亮显示的文字
	 */
	private String				highLight;

	/**
	 * 高亮字体颜色
	 */
	private String				highLightColor;

	@Override
	public String writeContent(){
		return HTMLSpan.getHighLight(content, highLight, highLightColor);
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * @param highLight
	 *            the highLight to set
	 */
	public void setHighLight(String highLight){
		this.highLight = highLight;
	}

	/**
	 * @param highLightColor
	 *            the highLightColor to set
	 */
	public void setHighLightColor(String highLightColor){
		this.highLightColor = highLightColor;
	}
}
