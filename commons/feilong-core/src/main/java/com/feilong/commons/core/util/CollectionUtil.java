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
package com.feilong.commons.core.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import com.feilong.commons.core.entity.JoinStringEntity;

/**
 * 集合工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:08:40 PM
 * @since 1.0
 */
public final class CollectionUtil{

	/** Don't let anyone instantiate this class. */
	private CollectionUtil(){}

	/**
	 * 集合转成字符串.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param collection
	 *            the collection
	 * @param joinStringEntity
	 *            连接字符串 实体
	 * @return the string
	 */
	public static <T> String toString(final Collection<T> collection,final JoinStringEntity joinStringEntity){
		// 默认逗号连接
		String connector_default = ",";

		StringBuilder stringBuilder = null;
		if (Validator.isNotNullOrEmpty(collection) && collection.size() > 0){
			if (Validator.isNotNullOrEmpty(joinStringEntity) && Validator.isNotNullOrEmpty(joinStringEntity.getConnector())){
				connector_default = joinStringEntity.getConnector();
			}
			stringBuilder = new StringBuilder();
			int i = 0;
			for (T t : collection){
				stringBuilder.append(t);
				// 拼接连接符
				if (i < collection.size() - 1){
					stringBuilder.append(connector_default);
				}
				i++;
			}
			return stringBuilder.toString();
		}
		return null;
	}

	/**
	 * 将集合转成枚举.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param collection
	 *            集合
	 * @return Enumeration
	 */
	public static <T> Enumeration<T> toEnumeration(final Collection<T> collection){
		return Collections.enumeration(collection);
	}
}
