package com.feilong.taglib.common;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 截取页面显示内容,超过长度用省略号表示
 * 
 * @author 金鑫2009-9-29上午10:56:56
 */
public class SubStringTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 需要被截取的内容
	 */
	private String				content;

	/**
	 * 截取长度
	 */
	private int					length;

	@Override
	public String writeContent(){
		String returnValue = "";
		if (Validator.isNotNullOrEmpty(content)){
			content = content.trim();
			if (content.length() > length){
				returnValue = content.substring(0, length) + "...";
			}else{
				returnValue = content;
			}
		}
		return returnValue;
	}

	public void setContent(String content){
		this.content = content;
	}

	public void setLength(int length){
		this.length = length;
	}
}
