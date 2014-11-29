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

import com.feilong.struts2.bean.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 企业开发只用这种 extends ActionSupport.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-9 上午2:15:33
 */
public class UserAction extends ActionSupport{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 9045579025409967859L;

	/** The user. */
	private User				user;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception{
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		return SUCCESS;
	}

	/**
	 * 获得 user.
	 *
	 * @return the user
	 */
	public User getUser(){
		return user;
	}

	/**
	 * 设置 user.
	 *
	 * @param user
	 *            the user
	 */
	public void setUser(User user){
		this.user = user;
	}
}
