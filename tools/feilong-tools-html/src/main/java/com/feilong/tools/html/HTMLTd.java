package com.feilong.tools.html;

import com.feilong.commons.core.util.Validator;
import com.feilong.tools.html.entity.HtmlTdEntity;

/**
 * 专门用于后台生成html td代码的类
 * 
 * @author 金鑫 2010年8月16日 23:08:42
 */
public final class HTMLTd{

	/**
	 * 创建td标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @return td标签
	 */
	public static String createTd(Object content,String className){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		return createTd(content, className, null);
	}

	/**
	 * 创建td标签
	 * 
	 * @param content
	 *            内容
	 * @param className
	 *            class名称
	 * @param title
	 *            title
	 * @return td标签
	 */
	public static String createTd(Object content,String className,String title){
		if (Validator.isNullOrEmpty(content)){
			return "";
		}
		HtmlTdEntity htmlTdEntity = new HtmlTdEntity();
		htmlTdEntity.setContent(content.toString());
		htmlTdEntity.setTitle(title);
		htmlTdEntity.setStyleClass(className);
		return createTd(htmlTdEntity);
	}

	/**
	 * 创建td标签
	 * 
	 * @param htmlTdEntity
	 *            htmlTdEntity
	 * @return 创建td标签
	 */
	public static String createTd(HtmlTdEntity htmlTdEntity){
		if (null == htmlTdEntity || htmlTdEntity.isEmpty()){
			return "";
		}
		StringBuilder stringBuilder = new StringBuilder("<td");
		HtmlUtil.addAttribute(stringBuilder, "class", htmlTdEntity.getStyleClass());
		HtmlUtil.addAttribute(stringBuilder, "title", htmlTdEntity.getTitle());
		stringBuilder.append(">");
		stringBuilder.append(htmlTdEntity.getContent());
		stringBuilder.append("</td>");
		return stringBuilder.toString();
	}
}
