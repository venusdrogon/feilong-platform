/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.entity;

import java.io.Serializable;

/**
 * 返回提示.<br>
 * 可以用来被继承,以便实现各种定制化的功能
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-6-24 上午03:14:56
 * @since 1.0
 */
public class BackWarnEntity implements Serializable{

	private static final long	serialVersionUID	= 3703766119930341844L;

	/** 是否成功. */
	private boolean				isSuccess;

	/** 描述. */
	private Object				description;

	/**
	 * Instantiates a new back warn entity.
	 */
	public BackWarnEntity(){}

	/**
	 * Instantiates a new back warn entity.
	 * 
	 * @param isSuccess
	 *            the is success
	 * @param description
	 *            the description
	 */
	public BackWarnEntity(boolean isSuccess, Object description){
		this.isSuccess = isSuccess;
		this.description = description;
	}

	/**
	 * Gets the 是否成功.
	 * 
	 * @return the isSuccess
	 */
	public boolean getIsSuccess(){
		return isSuccess;
	}

	/**
	 * Sets the 是否成功.
	 * 
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	/**
	 * Gets the 描述.
	 * 
	 * @return the description
	 */
	public Object getDescription(){
		return description;
	}

	/**
	 * Sets the 描述.
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(Object description){
		this.description = description;
	}
}