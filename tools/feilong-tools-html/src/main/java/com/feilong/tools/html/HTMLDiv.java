package com.feilong.tools.html;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.html.entity.HtmlDivEntity;

/**
 * 专门用于后台生成html div代码的类
 * 
 * @author 金鑫 2010年8月16日 22:52:49
 */
public final class HTMLDiv{

	/**
	 * 创建span标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @return span标签
	 */
	public static String createDiv(Object content,String className){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		return createDiv(content, className, null);
	}

	/**
	 * 创建span标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @param title
	 *            title
	 * @return span标签
	 */
	public static String createDiv(Object content,String className,String title){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		HtmlDivEntity htmlDivEntity = new HtmlDivEntity();
		htmlDivEntity.setContent(content.toString());
		htmlDivEntity.setTitle(title);
		htmlDivEntity.setStyleClass(className);
		return createDiv(htmlDivEntity);
	}

	/**
	 * 创建span标签
	 * 
	 * @param htmlDivEntity
	 *            htmlDivEntity
	 * @return 创建span标签
	 */
	public static String createDiv(HtmlDivEntity htmlDivEntity){
		if (null == htmlDivEntity || htmlDivEntity.isEmpty()){
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder("<div");
		HtmlUtil.addAttribute(stringBuilder, "class", htmlDivEntity.getStyleClass());
		HtmlUtil.addAttribute(stringBuilder, "title", htmlDivEntity.getTitle());
		stringBuilder.append(">");
		stringBuilder.append(htmlDivEntity.getContent());
		stringBuilder.append("</div>");
		return stringBuilder.toString();
	}
}
