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
package com.feilong.tools.html.entity;

/**
 * html标签实体基类
 * 
 * @author 金鑫 2010-1-3 上午10:48:43
 */
public class BaseHTMLEntity{

	/**
	 * 样式
	 */
	protected String	style;

	/**
	 * id
	 */
	protected String	styleId;

	/**
	 * 类名
	 */
	protected String	styleClass;

	/**
	 * 标题
	 */
	protected String	title;

	/**
	 * @return the styleId
	 */
	public String getStyleId(){
		return styleId;
	}

	/**
	 * @param styleId
	 *            the styleId to set
	 */
	public void setStyleId(String styleId){
		this.styleId = styleId;
	}

	/**
	 * @return the styleClass
	 */
	public String getStyleClass(){
		return styleClass;
	}

	/**
	 * @param styleClass
	 *            the styleClass to set
	 */
	public void setStyleClass(String styleClass){
		this.styleClass = styleClass;
	}

	/**
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * @return the style
	 */
	public String getStyle(){
		return style;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style){
		this.style = style;
	}
}
