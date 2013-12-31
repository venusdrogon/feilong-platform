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
package com.feilong.spring.beans.propertyeditors;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.net.URIUtil;

/**
 * 使用 URIUtil.decode(text, charsetType) 解码<br>
 * 使用方法:
 * 
 * <pre>
 * 
 * &#064;InitBinder({ &quot;categoryCode&quot; })
 * // 此处的参数也可以是ServletRequestDataBinder类型
 * public void initBinder(WebDataBinder binder) throws Exception{
 * 	// 注册自定义的属性编辑器
 * 	binder.registerCustomEditor(String.class, new URLDecoderEditor(CharsetType.UTF8));
 * }
 * 
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 5, 2012 10:59:38 PM
 */
public class URLDecoderEditor extends PropertyEditorSupport{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(URLDecoderEditor.class);

	/** 编码. */
	private String				charsetType;

	/**
	 * Instantiates a new uRL decoder editor.
	 * 
	 * @param charsetType
	 *            编码.
	 */
	public URLDecoderEditor(String charsetType){
		this.charsetType = charsetType;
	}

	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		String newText = URIUtil.decode(text, charsetType);
		log.debug("the old text:{},new text:{}", text, newText);
		setValue(newText);
	}
}
