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
package com.feilong.commons.core;

/**
 * 存放通用的参数.
 * 
 * @author 金鑫 2010-3-19 下午05:37:33
 * @since 1.0
 * @version 1.0
 * @deprecated 每个用到的地方自己实现
 */
public interface Constants{

	/** 本地ip <code>{@value}</code>. */
	String	localhostIp		= "127.0.0.1";

	/** 初始值空格 oracle空格需要空1个实在的空格 ""在oracle自动转换为null. <code>{@value}</code> */
	String	space			= " ";

	/** 生成换行标识 Line separator ("\n" on UNIX). */
	String	lineSeparator	= System.getProperty("line.separator");

	/**
	 * ***************************** Session 常量,内部类 ***************************************.
	 */
	/**
	 * Session 常量,内部类,不建议直接调用Session类,请使用FeiLongConstants.Session
	 * 
	 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
	 * @version 1.0 2011-4-14 下午02:20:15
	 * @since 1.0
	 */
	public static interface Session{

		/** 验证码<code>{@value}</code>. */
		String	validateCode	= "feilong.validateCode";
	}
}
