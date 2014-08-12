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
package com.feilong.commons.core.lang.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * *
 * <p>
 * Utility methods focusing on type inspection, particularly with regard to generics.
 * </p>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月21日 上午11:01:31
 * @since 1.0.8
 * @see org.apache.commons.lang3.reflect.TypeUtils
 */
public final class TypeUtil{

	private static final Logger	log	= LoggerFactory.getLogger(TypeUtil.class);

	/** Don't let anyone instantiate this class. */
	private TypeUtil(){}

	/**
	 * Gets the generic model class.<br>
	 * {@code  public class SkuItemRepositoryImpl extends BaseSolrRepositoryImpl<SkuItem, Long> implements SkuItemRepository}<br>
	 * 这样的类,取到泛型里面第一个参数 SkuItem.class
	 * 
	 * @param <T>
	 *            the generic type
	 * @param klass
	 *            the clazz
	 * @return the generic model class
	 * @see jdk 1.5
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericModelClass(Class<?> klass){
		//返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type.
		Type type = klass.getGenericSuperclass();

		while (!(type instanceof ParameterizedType) && null != klass && Object.class != klass){
			klass = klass.getSuperclass();
		}
		if (!(type instanceof ParameterizedType)){
			Class<?>[] iclazzs = klass.getInterfaces();
			if (iclazzs.length > 0){
				int index = -1;
				if (index >= 0){
					Type[] genericInterfaces = klass.getGenericInterfaces();
					Type type2 = genericInterfaces[index];
					if (type2 instanceof ParameterizedType){
						type = type2;
					}
				}
			}
		}
		if (!(type instanceof ParameterizedType)){
			throw new RuntimeException("Can not find the right Generic Class.");
		}

		ParameterizedType parameterizedType = (ParameterizedType) type;
		Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(actualTypeArguments));
		}

		return (Class<T>) actualTypeArguments[0];
	}

}
