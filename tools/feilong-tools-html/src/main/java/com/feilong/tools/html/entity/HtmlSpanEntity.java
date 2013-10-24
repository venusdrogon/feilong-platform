package com.feilong.tools.html.entity;

import com.feilong.commons.core.util.Validator;

/**
 * span 标签实体
 * 
 * @author 金鑫 2010-6-17 上午11:03:27
 */
public class HtmlSpanEntity extends BaseHTMLEntity{

	/**
	 * 内容
	 */
	private String	content;

	/**
	 * @return the content
	 */
	public String getContent(){
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content){
		this.content = content;
	}

	/**
	 * 实体是否为空
	 * 
	 * @return 如果没有设置content则为空
	 */
	public boolean isEmpty(){
		return Validator.isNullOrEmpty(this.content);
	}
}
