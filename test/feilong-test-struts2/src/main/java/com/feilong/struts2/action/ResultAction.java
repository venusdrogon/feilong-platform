package com.feilong.struts2.action;

import com.opensymphony.xwork2.ActionSupport;

public class ResultAction extends ActionSupport{

	private static final long	serialVersionUID	= 1L;

	public String execute(){
		System.out.println(1111);
		return null;
	}
}
