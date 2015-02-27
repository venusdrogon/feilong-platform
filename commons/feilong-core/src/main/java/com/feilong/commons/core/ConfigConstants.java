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

import com.feilong.commons.core.configure.ResourceBundleUtil;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月30日 下午6:00:36
 * @since 1.0.8
 */
public abstract class ConfigConstants{

	private static String	$FEILONG_CORE_CONFIG					= "config/feilong-core-config";

	/** 数字和小写的字母. */
	public static String	VALIDATECODE_NUMBERSANDLITTLELETTERS	= ResourceBundleUtil.getValue(
																					$FEILONG_CORE_CONFIG,
																					"validateCode_numbersAndLittleLetters");

	/** 数字和大小写字母. */
	public static String	NUMBERSANDALLLETTERS					= ResourceBundleUtil.getValue(
																					$FEILONG_CORE_CONFIG,
																					"numbersAndAllLetters");

	/** 所有的数字. */
	public static String	NUMBERS									= ResourceBundleUtil.getValue($FEILONG_CORE_CONFIG, "numbers");
}
