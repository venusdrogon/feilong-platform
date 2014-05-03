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
package com.feilong.commons.core.lang;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ClassUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-6-1 下午7:19:47
 */
public class ClassUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(ClassUtil.class);

	/**
	 * debug.
	 * 
	 * @param clz
	 *            the clz
	 * @return the map for log
	 */
	public static Map<String, Object> getMapForLog(Class<? extends Object> clz){

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("clz.getComponentType()", clz.getComponentType());
		// 用来判断指定的Class类是否为一个基本类型。
		map.put("clz.isPrimitive()", clz.isPrimitive());
		map.put("clz.isLocalClass()", clz.isLocalClass());
		map.put("clz.isMemberClass()", clz.isMemberClass());
		map.put("clz.isSynthetic()", clz.isSynthetic());
		map.put("clz.isArray()", clz.isArray());
		map.put("clz.isAnnotation()", clz.isAnnotation());
		map.put("clz.isAnonymousClass()", clz.isAnonymousClass());
		map.put("clz.isEnum()", clz.isEnum());

		return map;
	}
}
