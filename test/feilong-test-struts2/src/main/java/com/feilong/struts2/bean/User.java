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
package com.feilong.struts2.bean;

/**
 * The Class User.
 */
public class User{

	/** The user name. */
	private String	userName;

	/** The password. */
	private String	password;

	/**
	 * 获得 user name.
	 *
	 * @return the user name
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * 设置 user name.
	 *
	 * @param userName
	 *            the user name
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}

	/**
	 * 获得 password.
	 *
	 * @return the password
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * 设置 password.
	 *
	 * @param password
	 *            the password
	 */
	public void setPassword(String password){
		this.password = password;
	}
}
