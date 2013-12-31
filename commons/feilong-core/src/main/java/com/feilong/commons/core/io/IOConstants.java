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

/**
 * 飞龙io单位
 * 
 * @author 金鑫 2010-5-21 下午04:31:08
 */
public final class IOConstants{

	/**
	 * KB 1024
	 */
	public final static long		KB				= 1024;

	/**
	 * MB 1024 * 1024 1048576
	 */
	public final static long		MB				= 1024 * KB;

	/**
	 * GB 1024 * 1024 * 1024 1073741824
	 */
	public final static long		GB				= 1024 * MB;

	/**
	 * 常用图片格式
	 */
	public final static String[]	commonImages	= { "gif", "bmp", "jpg", "png" };
}
