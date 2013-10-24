/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
