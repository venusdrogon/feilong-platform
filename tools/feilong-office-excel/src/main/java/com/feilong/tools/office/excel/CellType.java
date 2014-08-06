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
package com.feilong.tools.office.excel;

/**
 * The Enum CellType.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月6日 下午1:43:24
 * @since 1.0.8
 */
public enum CellType{

	/** The numeric. */
	NUMERIC(0),

	/** The string. */
	STRING(1),

	/** The formula. */
	FORMULA(2),

	/** The blank. */
	BLANK(3),

	/** The boolean. */
	BOOLEAN(4),

	/** The error. */
	ERROR(5);

	/** The value. */
	private Integer	value;

	/**
	 * The Constructor.
	 *
	 * @param value
	 *            the value
	 */
	private CellType(Integer value){
		this.value = value;
	}

	/**
	 * 获得 value.
	 *
	 * @return the value
	 */
	public Integer getValue(){
		return value;
	}

	/**
	 * 设置 value.
	 *
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value){
		this.value = value;
	}

}
