/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.io;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * CSV参数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-22 下午5:45:23
 */
public class CSVParams{

	/**
	 * 转义引号用的字符 "
	 */
	public static final char	ESCAPE_CHARACTER		= '"';

	/** 默认的引号字符 "引号. */
	public static final char	DEFAULT_QUOTE_CHARACTER	= '"';

	/**
	 * \\u转义字符的意思是“\\u后面的1-4位16进制数表示的Unicode码对应的汉字”,而Unicode 0000 代表的字符是 NUL，也就是空的意思，<br>
	 * 如果把这个字符输出到控制台，显示为空格
	 */
	public static final char	NO_QUOTE_CHARACTER		= '\u0000';

	/** 编码. */
	private String				encode					= CharsetType.GBK;

	/** filed 分隔符 默认,逗号. */
	private char				separator				= ',';

	/**
	 * Gets the 编码.
	 * 
	 * @return the encode
	 */
	public String getEncode(){
		return encode;
	}

	/**
	 * Sets the 编码.
	 * 
	 * @param encode
	 *            the encode to set
	 */
	public void setEncode(String encode){
		this.encode = encode;
	}

	/**
	 * Gets the filed 分隔符 默认,逗号.
	 * 
	 * @return the separator
	 */
	public char getSeparator(){
		return separator;
	}

	/**
	 * Sets the filed 分隔符 默认,逗号.
	 * 
	 * @param separator
	 *            the separator to set
	 */
	public void setSeparator(char separator){
		this.separator = separator;
	}
}
