package com.feilong.struts2.action;

import com.feilong.struts2.bean.User;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 企业开发只用这种 extends ActionSupport
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-9 上午2:15:33
 */
public class UserAction extends ActionSupport{

	private static final long	serialVersionUID	= 9045579025409967859L;

	private User				user;

	@Override
	public String execute() throws Exception{
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		return SUCCESS;
	}

	public User getUser(){
		return user;
	}

	public void setUser(User user){
		this.user = user;
	}
}
