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
package com.feilong.tools.html;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.html.entity.HtmlAEntity;

/**
 * 专门用于后台生成html代码的类
 * 
 * @author 金鑫 2009-12-30 下午04:42:43
 */
public final class HTMLA{

	/**
	 * 创建a标签
	 * 
	 * @param text
	 *            文本
	 * @param href
	 *            链接
	 * @return 拼装好的a链接
	 */
	public static String createA(Object text,String href){
		HtmlAEntity htmla = new HtmlAEntity();
		htmla.setHref(href);
		htmla.setText(text);
		return createA(htmla);
	}

	/**
	 * 创建a标签
	 * 
	 * @param text
	 *            文本
	 * @param href
	 *            链接
	 * @param title
	 *            显示的title
	 * @return 拼装好的a链接
	 */
	public static String createAWithTitle(Object text,String href,String title){
		HtmlAEntity htmla = new HtmlAEntity();
		htmla.setHref(href);
		htmla.setText(text);
		htmla.setTitle(title);
		return createA(htmla);
	}

	/**
	 * 创建a标签
	 * 
	 * @param text
	 *            文本
	 * @param href
	 *            链接
	 * @param className
	 *            类名
	 * @return 拼装好的a链接
	 */
	public static String createA(Object text,String href,String className){
		HtmlAEntity a = new HtmlAEntity();
		a.setText(text.toString());
		a.setHref(href);
		a.setStyleClass(className);
		return createA(a);
	}

	/**
	 * 创建a标签
	 * 
	 * @param a
	 *            a实体
	 * @return 拼装好的a链接
	 */
	public static String createA(HtmlAEntity a){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<a");
		HtmlUtil.addAttribute(stringBuilder, "href", a.getHref());
		// 类名
		HtmlUtil.addAttribute(stringBuilder, "class", a.getStyleClass());
		// 样式
		HtmlUtil.addAttribute(stringBuilder, "style", a.getStyle());
		// id
		HtmlUtil.addAttribute(stringBuilder, "id", a.getStyleId());
		// 打开方式
		HtmlUtil.addAttribute(stringBuilder, "target", a.getTarget());
		// title
		if (Validator.isNotNullOrEmpty(a.getTitle())){
			stringBuilder.append(" title=\"" + a.getTitle() + "\"");
		}else{
			stringBuilder.append(" title=\"" + a.getText() + "\"");
		}
		stringBuilder.append(">");
		stringBuilder.append(a.getText());
		stringBuilder.append("</a>");
		return stringBuilder.toString();
	}

	/**
	 * 创建a标签
	 * 
	 * @param text
	 *            文本
	 * @param href
	 *            链接
	 * @param highLight
	 *            高亮字符串
	 * @param highLightColor
	 *            高亮字符串的颜色
	 * @return 拼装好的a链接
	 */
	public static String createA(String text,String href,String highLight,String highLightColor){
		return createA(HTMLSpan.getHighLight(text, highLight, highLightColor), href);
	}
}
