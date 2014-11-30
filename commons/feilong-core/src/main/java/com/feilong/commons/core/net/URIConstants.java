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
package com.feilong.commons.core.net;

/**
 * uri使用的常量 <br>
 * This class defines the common URI constants ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2014 1:36:16 PM
 * @see "org.springframework.web.util.HierarchicalUriComponents.Type"
 * @since 1.0.0
 */
public interface URIConstants{ // 声明成接口,不想被实例化

	/** 查询片段 <code>{@value}</code>. */
	String	FRAGMENT		= "#";

	/** <code>{@value}</code> The question mark is used as a separator and is not part of the query string. */
	String	QUESTIONMARK	= "?";

	/** The Constant ampersand<code>{@value}</code>. */
	String	AMPERSAND		= "&";

	/** http协议<code>{@value}</code>. */
	String	SCHEME_HTTP		= "http";

	/** https协议<code>{@value}</code>. */
	String	SCHEME_HTTPS	= "https";

	// public static final String equal = "=";
}
