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

/**
 * 使用 URIUtil.decode(text, charsetType) 解码<br>
 * 使用方法:
 * 
 * <pre>
 * 
 * 
 * 
 * 
 * 
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 5, 2012 10:59:38 PM
 */
public class URLEscapeEditor extends PropertyEditorSupport{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(URLEscapeEditor.class);

	/*
	 * (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); i++){
			char c = text.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{'
					|| c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&'){
				sb.append('\\');
			}
			sb.append(c);
		}

		String newText = sb.toString();

		log.debug("the old text:{},new text:{}", text, newText);
		setValue(newText);
	}
}
