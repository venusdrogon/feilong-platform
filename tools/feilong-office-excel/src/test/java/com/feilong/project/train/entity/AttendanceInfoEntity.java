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
 * 签到扩展信息.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月4日 下午5:14:50
 * @since 1.0.8
 */
public class AttendanceInfoEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 288232184048495608L;

	/** 培训地点. */
	private String				trainAddress;

	/** 培训讲师. */
	private String				trainInstructor;

	/** 课程名称. */
	private String				courseName;

	/** 培训日期. */
	private String				trainDate;

	/**
	 * The Constructor.
	 *
	 * @param courseName
	 *            the course name
	 * @param trainDate
	 *            the train date
	 * @param trainInstructor
	 *            the train instructor
	 * @param trainAddress
	 *            the train address
	 */
	public AttendanceInfoEntity(String courseName, String trainDate, String trainInstructor, String trainAddress){
		super();
		this.courseName = courseName;
		this.trainDate = trainDate;
		this.trainInstructor = trainInstructor;
		this.trainAddress = trainAddress;
	}

	/**
	 * 获得 培训地点.
	 *
	 * @return the trainAddress
	 */
	public String getTrainAddress(){
		return trainAddress;
	}

	/**
	 * 设置 培训地点.
	 *
	 * @param trainAddress
	 *            the trainAddress to set
	 */
	public void setTrainAddress(String trainAddress){
		this.trainAddress = trainAddress;
	}

	/**
	 * 获得 培训讲师.
	 *
	 * @return the trainInstructor
	 */
	public String getTrainInstructor(){
		return trainInstructor;
	}

	/**
	 * 设置 培训讲师.
	 *
	 * @param trainInstructor
	 *            the trainInstructor to set
	 */
	public void setTrainInstructor(String trainInstructor){
		this.trainInstructor = trainInstructor;
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
	 * 获得 培训日期.
	 *
	 * @return the trainDate
	 */
	public String getTrainDate(){
		return trainDate;
	}

	/**
	 * 设置 培训日期.
	 *
	 * @param trainDate
	 *            the trainDate to set
	 */
	public void setTrainDate(String trainDate){
		this.trainDate = trainDate;
	}

}
