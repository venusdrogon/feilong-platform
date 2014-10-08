/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
	 * 
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); i++){
			char c = text.charAt(i);
			// These characters are part of the query syntax and must be escaped
			if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')' || c == ':' || c == '^' || c == '[' || c == ']'
							|| c == '\"' || c == '{' || c == '}' || c == '~' || c == '*' || c == '?' || c == '|' || c == '&'){
				sb.append('\\');
			}
			sb.append(c);
		}

		String newText = sb.toString();

		log.debug("the old text:{},new text:{}", text, newText);
		setValue(newText);
	}
}
