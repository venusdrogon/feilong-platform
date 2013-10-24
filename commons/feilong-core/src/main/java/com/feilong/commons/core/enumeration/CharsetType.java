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
package com.feilong.commons.core.enumeration;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import org.apache.commons.lang3.CharEncoding;

/**
 * 常用的编码
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-5 下午4:51:08
 * @see {@link CharEncoding}
 */
public final class CharsetType{

	/** <code>{@value}</code> */
	public static final String	GBK			= "GBK";

	/** <code>{@value}</code> */
	public static final String	GB2312		= "GB2312";

	/** <code>{@value}</code> */
	public static final String	GB18030		= "GB18030";

	// *********************************************************************************************
	/** <code>{@value}</code> */
	public static final String	UTF8		= "UTF-8";

	/**
	 * <p>
	 * ISO Latin Alphabet #1, also known as ISO-LATIN-1.
	 * </p>
	 * <p>
	 * Every implementation of the Java platform is required to support this character encoding.
	 * </p>
	 * 　ISO/IEC 8859-1，又称Latin-1或“西欧语言”，是国际标准化组织内ISO/IEC 8859的第一个8位字符集。<br>
	 * 它以ASCII为基础，在空置的0xA0-0xFF的范围内，加入192个字母及符号，藉以供使用变音符号的拉丁字母语言使用。<br>
	 * 此字符集支援部分于欧洲使用的语言，包括阿尔巴尼亚语、巴斯克语、布列塔尼语、加泰罗尼亚语、丹麦语、荷兰语、法罗语、弗里西语、加利西亚语、德语、格陵兰语、冰岛语、爱尔兰盖尔语、意大利语、拉丁语、卢森堡语、挪威语、葡萄牙语、里托罗曼斯语、苏格兰盖尔语、西班牙语及瑞典语。 　<br>
	 * 　英语虽然没有重音字母，但仍会标明为ISO 8859-1编码。除此之外，欧洲以外的部分语言，如南非荷兰语、斯瓦希里语、印尼语及马来语、菲律宾他加洛语等也可使用ISO 8859-1编码。 　　<br>
	 * 法语及芬兰语本来也使用ISO 8859-1来表示。但因它没有法语使用的 œ、Œ、 Ÿ 三个字母及芬兰语使用的 Š、š、Ž、ž ，故于1998年被ISO/IEC 8859-15所取代。（ISO 8859-15同时加入了欧元符号）
	 */
	public static final String	ISO_8859_1	= "ISO-8859-1";

	/**
	 * <p>
	 * Returns whether the named charset is supported.
	 * </p>
	 * <p>
	 * This is similar to <a href="http://download.oracle.com/javase/1.4.2/docs/api/java/nio/charset/Charset.html#isSupported%28java.lang.String%29">
	 * java.nio.charset.Charset.isSupported(String)</a> but handles more formats
	 * </p>
	 * 
	 * @param name
	 *            the name of the requested charset; may be either a canonical name or an alias, null returns false
	 * @return {@code true} if the charset is available in the current Java virtual machine
	 */
	public static boolean isSupported(String name){
		if (name == null){
			return false;
		}
		try{
			return Charset.isSupported(name);
		}catch (IllegalCharsetNameException ex){
			return false;
		}
	}
}
