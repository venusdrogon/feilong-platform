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
package com.feilong.hibernate.search.model;

/**
 * 项目中所有Entity的基本接口，定义有每个Entity的默认状态列表，每个Entity在定义时初始值都是使用 STATUS_ENABLE.
 *
 * @author benjamin
 */
public interface BaseModel{

	/** 禁用/删除/不活动/无效 状态. */
	public static final Integer	STATUS_DISABLE	= 0;

	/** 活动/有效 状态. */
	public static final Integer	STATUS_ENABLE	= 1;

	/** 默认状态为有效状态. */
	public static final Integer	DEFAULT_STATUS	= STATUS_ENABLE;

	/**
	 * 返回Entity的类定义.
	 *
	 * @return the model class
	 */
	Class<?> getModelClass();
}
