/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import com.feilong.commons.core.entity.JoinStringEntity;

/**
 * 集合工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 2, 2010 8:08:40 PM
 * @since 1.0
 */
public final class CollectionUtil{

	/** Don't let anyone instantiate this class. */
	private CollectionUtil(){}

	/**
	 * 默认逗号连接
	 */
	public static String	connector_default	= ",";

	/**
	 * 集合转成字符串
	 * 
	 * @param <T>
	 * @param collection
	 * @param joinStringEntity
	 *            连接字符串 实体
	 * @return
	 */
	public static <T> String toString(final Collection<T> collection,final JoinStringEntity joinStringEntity){
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
	 * 将集合转成枚举
	 * 
	 * @param <T>
	 * @param collection
	 *            集合
	 * @return Enumeration
	 */
	public static <T> Enumeration<T> toEnumeration(final Collection<T> collection){
		return Collections.enumeration(collection);
	}
}
