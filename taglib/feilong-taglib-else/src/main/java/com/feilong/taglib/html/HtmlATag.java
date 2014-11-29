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
package com.feilong.taglib.html;

import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HTMLA;
import com.feilong.tools.html.HTMLSpan;
import com.feilong.tools.html.entity.HtmlAEntity;

/**
 * 存放a标签属性
 * 
 * @author 金鑫 2010-5-6 上午10:01:46
 * @deprecated
 */
@Deprecated
public class HtmlATag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 是否可用
	 */
	protected String			enable				= "true";

	/**
	 * 文字
	 */
	protected String			text;

	/**
	 * 路径
	 */
	protected String			href;

	/**
	 * 打开方式
	 */
	protected String			target				= "_top";

	@Override
	protected Object writeContent(){
		if ("false".equals(enable)){
			return HTMLSpan.createSpan(text, "color_666", title);
		}
		HtmlAEntity a = new HtmlAEntity();
		a.setTarget(target);
		a.setHref(href);
		a.setText(text);
		a.setTitle(title);
		a.setStyle(style);
		a.setStyleClass(styleClass);
		a.setStyleId(styleId);
		return HTMLA.createA(a);
	}

	/**
	 * @param enable
	 *            the enable to set
	 */
	public void setEnable(String enable){
		this.enable = enable;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text){
		this.text = text;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public void setHref(String href){
		this.href = href;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target){
		this.target = target;
	}
}
