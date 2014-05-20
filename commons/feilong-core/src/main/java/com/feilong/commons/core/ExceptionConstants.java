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

import static com.feilong.commons.core.configure.ResourceBundleUtil.getValue;

/**
 * 配置文件<br>
 * This class defines the common ExceptionConstants ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午05:58:59
 * @since 1.0
 * @deprecated 每个类自己实现
 */
@Deprecated
public interface ExceptionConstants{

	/** The $ feilon g_ cor e_ exception. */
	String	$FEILONG_CORE_EXCEPTION			= "messages/feilong-core-exception";

	// ******************************************************************************************************
	/** 未知类型邮箱. */
	String	EXCEPTION_UNKNOWN_TYPE_EMAIL	= getValue($FEILONG_CORE_EXCEPTION, "exception_unknown_type_email");

	/** fileName 文件名不能为null. */
	String	EXCEPTION_FILENAME_NULL			= getValue($FEILONG_CORE_EXCEPTION, "exception_fileName_null");
}
