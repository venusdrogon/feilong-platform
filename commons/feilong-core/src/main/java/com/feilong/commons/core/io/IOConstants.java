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
package com.feilong.commons.core.io;

/**
 * io常用单位<br>
 * This class defines the common IOConstants ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-5-21 下午04:31:08
 * @version 1.1 Apr 24, 2014 1:56:38 AM
 */
public interface IOConstants{

	/** KB 1024. */
	long		KB				= 1024;

	/** MB 1024 * 1024 1048576. */
	long		MB				= 1024 * KB;

	/** GB 1024 * 1024 * 1024 1073741824. */
	long		GB				= 1024 * MB;

	/** 常用图片格式. */
	String[]	commonImages	= { "gif", "bmp", "jpg", "png" };
}
