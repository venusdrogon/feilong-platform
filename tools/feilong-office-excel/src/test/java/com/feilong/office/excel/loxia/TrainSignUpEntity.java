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
package com.feilong.office.excel.loxia;

import java.io.Serializable;

/**
 * 报名实体.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月22日 上午12:25:18
 * @since 1.0.8
 */
public class TrainSignUpEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 姓名. */
	private String				name;

	/** 邮箱. */
	private String				email;

	/** 报名时间. */
	private String				signUpTime;

	/** 手机. */
	private String				mobile;

	/** 商城分类. */
	private String				storeCategoryName;

	/** 商城名称 . */
	private String				storeName;

	/** 课程时间. */
	private String				courseTime;

	/** 课程名称. */
	private String				courseName;

	/** 是否是编外. */
	private String				offStaff;

	/** 备注. */
	private String				remark;

	/**
	 * 获得 姓名.
	 *
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置 姓名.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 邮箱.
	 *
	 * @return the email
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * 设置 邮箱.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 获得 报名时间.
	 *
	 * @return the signUpTime
	 */
	public String getSignUpTime(){
		return signUpTime;
	}

	/**
	 * 设置 报名时间.
	 *
	 * @param signUpTime
	 *            the signUpTime to set
	 */
	public void setSignUpTime(String signUpTime){
		this.signUpTime = signUpTime;
	}

	/**
	 * 获得 手机.
	 *
	 * @return the mobile
	 */
	public String getMobile(){
		return mobile;
	}

	/**
	 * 设置 手机.
	 *
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	/**
	 * 获得 课程时间.
	 *
	 * @return the courseTime
	 */
	public String getCourseTime(){
		return courseTime;
	}

	/**
	 * 设置 课程时间.
	 *
	 * @param courseTime
	 *            the courseTime to set
	 */
	public void setCourseTime(String courseTime){
		this.courseTime = courseTime;
	}

	/**
	 * 获得 课程名称.
	 *
	 * @return the courseName
	 */
	public String getCourseName(){
		return courseName;
	}

	/**
	 * 设置 课程名称.
	 *
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	/**
	 * 获得 是否是编外.
	 *
	 * @return the offStaff
	 */
	public String getOffStaff(){
		return offStaff;
	}

	/**
	 * 设置 是否是编外.
	 *
	 * @param offStaff
	 *            the offStaff to set
	 */
	public void setOffStaff(String offStaff){
		this.offStaff = offStaff;
	}

	/**
	 * 获得 备注.
	 *
	 * @return the remark
	 */
	public String getRemark(){
		return remark;
	}

	/**
	 * 设置 备注.
	 *
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	/**
	 * 获得 商城分类.
	 *
	 * @return the storeCategoryName
	 */
	public String getStoreCategoryName(){
		return storeCategoryName;
	}

	/**
	 * 设置 商城分类.
	 *
	 * @param storeCategoryName
	 *            the storeCategoryName to set
	 */
	public void setStoreCategoryName(String storeCategoryName){
		this.storeCategoryName = storeCategoryName;
	}

	/**
	 * 获得 商城名称 .
	 *
	 * @return the storeName
	 */
	public String getStoreName(){
		return storeName;
	}

	/**
	 * 设置 商城名称 .
	 *
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName){
		this.storeName = storeName;
	}
}