package com.feilong.struts2.action;

import java.util.Date;

public class LoginAcion{

	private String	username;

	private String	password;

	private Date	birthday;

	//**********************************************************
	public String execute(){
		return "success";
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
}
