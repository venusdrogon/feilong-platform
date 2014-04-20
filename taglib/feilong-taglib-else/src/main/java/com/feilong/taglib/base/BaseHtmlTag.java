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
package com.feilong.taglib.base;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.html.HtmlUtil;

/**
 * html标签 基类 ,包含公共属性 和连接公共属性的方法
 * 
 * @author 金鑫 2010-1-9 上午09:56:33
 */
@Deprecated
public abstract class BaseHtmlTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * id
	 */
	protected String			styleId;

	/**
	 * class
	 */
	protected String			styleClass;

	/**
	 * 样式
	 */
	protected String			style;

	/**
	 * title
	 */
	protected String			title;

	/**
	 * 添加公共的属性
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-1-9 上午10:01:03
	 */
	protected void innerCommonAttribute(StringBuilder stringBuilder){
		if (Validator.isNotNullOrEmpty(styleId)){
			HtmlUtil.addAttribute(stringBuilder, "id", styleId);
		}
		if (Validator.isNotNullOrEmpty(styleClass)){
			HtmlUtil.addAttribute(stringBuilder, "class", styleClass);
		}
		if (Validator.isNotNullOrEmpty(style)){
			HtmlUtil.addAttribute(stringBuilder, "style", style);
		}
		if (Validator.isNotNullOrEmpty(title)){
			HtmlUtil.addAttribute(stringBuilder, "title", title);
		}
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style){
		this.style = style;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * @param styleId
	 *            the styleId to set
	 */
	public void setStyleId(String styleId){
		this.styleId = styleId;
	}

	/**
	 * @param styleClass
	 *            the styleClass to set
	 */
	public void setStyleClass(String styleClass){
		this.styleClass = styleClass;
	}
}
