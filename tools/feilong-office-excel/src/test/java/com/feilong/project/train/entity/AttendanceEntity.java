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
package com.feilong.project.train.entity;

import java.io.Serializable;

/**
 * 签到sheet .
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月26日 下午5:10:17
 * @since 1.0.8
 */
public class AttendanceEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** No. */
	private Integer				id;

	/** 姓名. */
	private String				name;

	/** 部门. */
	private String				departName;

	/** 组别. */
	private String				groupName;

	/** 会前通知参加. */
	private String				notice;

	/** 培训签到. */
	private String				attendanceSign;

	/** 备注. */
	private String				remark;

	/**
	 * 获得 no.
	 *
	 * @return the id
	 */
	public Integer getId(){
		return id;
	}

	/**
	 * 设置 no.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id){
		this.id = id;
	}

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
	 * 获得 部门.
	 *
	 * @return the departName
	 */
	public String getDepartName(){
		return departName;
	}

	/**
	 * 设置 部门.
	 *
	 * @param departName
	 *            the departName to set
	 */
	public void setDepartName(String departName){
		this.departName = departName;
	}

	/**
	 * 获得 组别.
	 *
	 * @return the groupName
	 */
	public String getGroupName(){
		return groupName;
	}

	/**
	 * 设置 组别.
	 *
	 * @param groupName
	 *            the groupName to set
	 */
	public void setGroupName(String groupName){
		this.groupName = groupName;
	}

	/**
	 * 获得 会前通知参加.
	 *
	 * @return the notice
	 */
	public String getNotice(){
		return notice;
	}

	/**
	 * 设置 会前通知参加.
	 *
	 * @param notice
	 *            the notice to set
	 */
	public void setNotice(String notice){
		this.notice = notice;
	}

	/**
	 * 获得 培训签到.
	 *
	 * @return the attendanceSign
	 */
	public String getAttendanceSign(){
		return attendanceSign;
	}

	/**
	 * 设置 培训签到.
	 *
	 * @param attendanceSign
	 *            the attendanceSign to set
	 */
	public void setAttendanceSign(String attendanceSign){
		this.attendanceSign = attendanceSign;
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
}
