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
package com.feilong.taglib.display.httpconcat;

import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * Tengine用的src.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月4日 下午11:45:20
 */
public class HttpConcatItemTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -4587653933261044593L;

	/** css/js的src. */
	private String				src;

	/** The BEGI n_ tag. */
	public static String		BEGIN_TAG			= "<TengineFile>";

	/** The EN d_ tag. */
	public static String		END_TAG				= "</TengineFile>";

	/*
	 * (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	protected Object writeContent(){
		if (Validator.isNotNullOrEmpty(src)){
			return BEGIN_TAG + src + END_TAG;
		}
		return "";
	}

	/**
	 * Sets the css/js的src.
	 * 
	 * @param src
	 *            the new css/js的src
	 */
	public void setSrc(String src){
		this.src = src;
	}
}
