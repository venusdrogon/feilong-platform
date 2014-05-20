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
package com.feilong.commons.core;

/**
 * 存放通用的参数.
 * 
 * @author 金鑫 2010-3-19 下午05:37:33
 * @since 1.0
 * @version 1.0
 * @deprecated 即将调整
 */
@Deprecated
public interface Constants{

	/** 初始值空格 oracle空格需要空1个实在的空格 ""在oracle自动转换为null. <code>{@value}</code> */
	String	SPACE			= " ";

	/** 生成换行标识 Line separator ("\n" on UNIX). */
	String	LINE_SEPARATOR	= System.getProperty("line.separator");
}
