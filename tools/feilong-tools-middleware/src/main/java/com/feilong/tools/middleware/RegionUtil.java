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
package com.feilong.tools.middleware;

/**
 * 地区操作.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-4-15 上午11:11:00
 * @since 1.0
 */
public class RegionUtil{

	/** 直辖市. */
	public static String[]	municipalities					= { "北京市", "上海市", "天津市", "重庆市" };

	/** 特别行政区. */
	public static String[]	specialAdministrativeRegions	= { "香港", "澳门" };

	/**
	 * 是否是直辖市.
	 * 
	 * @param provinceName
	 *            省份名称
	 * @return true, if is municipality
	 */
	public static boolean isMunicipality(String provinceName){
		boolean flag = false;
		for (String municipality : municipalities){
			if (municipality.equals(provinceName)){
				flag = true;
				break;
			}
		}
		return flag;
	}
}
