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
package com.feilong.entity;

/**
 * 数组.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月8日 下午3:57:50
 * @since 1.0.8
 */
public class DIUserArray extends BaseDIUser{

	/** The skills. */
	private String[]	skills;

	/** The secret strategys. */
	private String[]	secretStrategys;

	/**
	 * 获得 skills.
	 *
	 * @return the skills
	 */
	public String[] getSkills(){
		return skills;
	}

	/**
	 * 设置 skills.
	 *
	 * @param skills
	 *            the skills to set
	 */
	public void setSkills(String[] skills){
		this.skills = skills;
	}

	/**
	 * 获得 secret strategys.
	 *
	 * @return the secretStrategys
	 */
	public String[] getSecretStrategys(){
		return secretStrategys;
	}

	/**
	 * 设置 secret strategys.
	 *
	 * @param secretStrategys
	 *            the secretStrategys to set
	 */
	public void setSecretStrategys(String[] secretStrategys){
		this.secretStrategys = secretStrategys;
	}

}
