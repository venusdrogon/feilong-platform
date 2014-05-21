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

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import com.feilong.commons.core.entity.JoinStringEntity;

/**
 * {@link Collection}工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:08:40 PM
 * @since 1.0.0
 */
public final class CollectionUtil{

	/** Don't let anyone instantiate this class. */
	private CollectionUtil(){}

	/**
	 * 将集合使用连接符号链接成字符串.
	 * 
	 * @param <T>
	 *            the generic type ,必须实现 {@link Serializable} 接口
	 * @param collection
	 *            集合, 建议基本类型泛型的结合,因为这个方法是直接循环collection 进行拼接
	 * @param joinStringEntity
	 *            连接字符串 实体
	 * @return 如果 collection isNullOrEmpty,返回null<br>
	 *         如果 joinStringEntity 是null,默认使用 {@link JoinStringEntity#DEFAULT_CONNECTOR} 进行连接<br>
	 *         都不是null,会循环,拼接joinStringEntity.getConnector()
	 */
	// XXX 空字符串不拼接
	public final static <T extends Serializable> String toString(final Collection<T> collection,final JoinStringEntity joinStringEntity){

		if (Validator.isNotNullOrEmpty(collection)){

			String connector = JoinStringEntity.DEFAULT_CONNECTOR;
			if (Validator.isNotNullOrEmpty(joinStringEntity)){
				connector = joinStringEntity.getConnector();
			}

			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (T t : collection){
				sb.append(t);
				// 拼接连接符
				if (i < collection.size() - 1){
					sb.append(connector);
				}
				i++;
			}
			return sb.toString();
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
	public final static <T> Enumeration<T> toEnumeration(final Collection<T> collection){
		return Collections.enumeration(collection);
	}
}
