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

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.BaseHtmlTag;
import com.feilong.tools.html.HtmlUtil;

/**
 * 图片标签
 * 
 * @author 金鑫 2009-9-4下午01:25:31
 * @deprecated
 */
public class HtmlImageTag extends BaseHtmlTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 图片路径
	 */
	private String				src;

	/**
	 * 显示图片宽度
	 */
	private String				width;

	/**
	 * 显示图片高度
	 */
	private String				height;

	/**
	 * 极限宽
	 */
	private String				limitWidth;

	/**
	 * 极限高
	 */
	private String				limitHeight;

	@Override
	public String writeContent(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<img");
		HtmlUtil.addAttribute(stringBuilder, "src", src);
		innerCommonAttribute(stringBuilder);
		// 宽度
		if (Validator.isNotNullOrEmpty(this.width)){
			HtmlUtil.addAttribute(stringBuilder, "width", width);
		}
		// 高度
		if (Validator.isNotNullOrEmpty(this.height)){
			HtmlUtil.addAttribute(stringBuilder, "height", height);
		}
		if (Validator.isNotNullOrEmpty(this.limitWidth) || Validator.isNotNullOrEmpty(this.limitHeight)){
			// String imageRealUrl = pageContext.getServletContext().getRealPath(this.src);
		}
		stringBuilder.append("/>");
		return stringBuilder.toString();
	}

	/**
	 * @param src
	 *            the src to set
	 */
	public void setSrc(String src){
		this.src = src;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(String width){
		this.width = width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(String height){
		this.height = height;
	}

	/**
	 * @param limitWidth
	 *            the limitWidth to set
	 */
	public void setLimitWidth(String limitWidth){
		this.limitWidth = limitWidth;
	}

	/**
	 * @param limitHeight
	 *            the limitHeight to set
	 */
	public void setLimitHeight(String limitHeight){
		this.limitHeight = limitHeight;
	}
}
