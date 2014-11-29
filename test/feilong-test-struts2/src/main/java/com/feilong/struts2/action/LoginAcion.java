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
package com.feilong.struts2.action;

import java.util.Date;

/**
 * The Class LoginAcion.
 */
public class LoginAcion{

	/** The username. */
	private String	username;

	/** The password. */
	private String	password;

	/** The birthday. */
	private Date	birthday;

	//**********************************************************
	/**
	 * Execute.
	 *
	 * @return the string
	 */
	public String execute(){
		return "success";
	}

	/**
	 * 获得 username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return username;
	}

	/**
	 * 设置 username.
	 *
	 * @param username
	 *            the username
	 */
	public void setUsername(String username){
		this.username = username;
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

	/**
	 * 获得 birthday.
	 *
	 * @return the birthday
	 */
	public Date getBirthday(){
		return birthday;
	}

	/**
	 * 设置 birthday.
	 *
	 * @param birthday
	 *            the birthday
	 */
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
}
