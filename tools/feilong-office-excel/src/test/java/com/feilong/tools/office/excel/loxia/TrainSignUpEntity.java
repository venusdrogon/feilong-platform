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
package com.feilong.tools.office.excel.loxia;

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

	//************************************************************************

	/** The p0827. */
	private String				p0827;

	/** 会议通知. */
	private Integer				meetingNoticeScore;

	/** 培训时间安排. */
	private Integer				meetingTimeScore;

	/** 培训组织者态度. */
	private Integer				trainOrganizersAttitudeScore;

	/** 培训教室布置. */
	private Integer				trainRoomLayoutScore;

	/** 现场次序维护. */
	private Integer				sceneOrderMaintenanceScore;

	/** 表达清晰态度友善. */
	private Integer				clearExpressionFriendlyScore;

	/** 对授课时间的掌控度. */
	private Integer				teachTimeDegreeControlScore;

	/** 现场气氛调节能力. */
	private Integer				sceneAtmosphereAdjustmentAbilityScore;

	/** PPT内容. */
	private Integer				pptContentScore;

	/** PPT设计. */
	private Integer				pptDesignScore;

	/** 对我有用. */
	private Integer				usefulToMeScore;

	/** 干货. */
	private Integer				ganhuoScore;

	/** 是否满意老师. */
	private Integer				satisfiedTeacherScore;

	/** 评价类型. */
	private String				evaluationType;

	/** 评价内容. */
	private String				evaluationContent;

	/** 沟通结果. */
	private String				communicateResults;

	//*************************************************************************

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

	/**
	 * 获得 p0827.
	 *
	 * @return the p0827
	 */
	public String getP0827(){
		return p0827;
	}

	/**
	 * 设置 p0827.
	 *
	 * @param p0827
	 *            the p0827 to set
	 */
	public void setP0827(String p0827){
		this.p0827 = p0827;
	}

	/**
	 * 获得 会议通知.
	 *
	 * @return the meetingNoticeScore
	 */
	public Integer getMeetingNoticeScore(){
		return meetingNoticeScore;
	}

	/**
	 * 设置 会议通知.
	 *
	 * @param meetingNoticeScore
	 *            the meetingNoticeScore to set
	 */
	public void setMeetingNoticeScore(Integer meetingNoticeScore){
		this.meetingNoticeScore = meetingNoticeScore;
	}

	/**
	 * 获得 培训时间安排.
	 *
	 * @return the meetingTimeScore
	 */
	public Integer getMeetingTimeScore(){
		return meetingTimeScore;
	}

	/**
	 * 设置 培训时间安排.
	 *
	 * @param meetingTimeScore
	 *            the meetingTimeScore to set
	 */
	public void setMeetingTimeScore(Integer meetingTimeScore){
		this.meetingTimeScore = meetingTimeScore;
	}

	/**
	 * 获得 培训组织者态度.
	 *
	 * @return the trainOrganizersAttitudeScore
	 */
	public Integer getTrainOrganizersAttitudeScore(){
		return trainOrganizersAttitudeScore;
	}

	/**
	 * 设置 培训组织者态度.
	 *
	 * @param trainOrganizersAttitudeScore
	 *            the trainOrganizersAttitudeScore to set
	 */
	public void setTrainOrganizersAttitudeScore(Integer trainOrganizersAttitudeScore){
		this.trainOrganizersAttitudeScore = trainOrganizersAttitudeScore;
	}

	/**
	 * 获得 培训教室布置.
	 *
	 * @return the trainRoomLayoutScore
	 */
	public Integer getTrainRoomLayoutScore(){
		return trainRoomLayoutScore;
	}

	/**
	 * 设置 培训教室布置.
	 *
	 * @param trainRoomLayoutScore
	 *            the trainRoomLayoutScore to set
	 */
	public void setTrainRoomLayoutScore(Integer trainRoomLayoutScore){
		this.trainRoomLayoutScore = trainRoomLayoutScore;
	}

	/**
	 * 获得 现场次序维护.
	 *
	 * @return the sceneOrderMaintenanceScore
	 */
	public Integer getSceneOrderMaintenanceScore(){
		return sceneOrderMaintenanceScore;
	}

	/**
	 * 设置 现场次序维护.
	 *
	 * @param sceneOrderMaintenanceScore
	 *            the sceneOrderMaintenanceScore to set
	 */
	public void setSceneOrderMaintenanceScore(Integer sceneOrderMaintenanceScore){
		this.sceneOrderMaintenanceScore = sceneOrderMaintenanceScore;
	}

	/**
	 * 获得 表达清晰态度友善.
	 *
	 * @return the clearExpressionFriendlyScore
	 */
	public Integer getClearExpressionFriendlyScore(){
		return clearExpressionFriendlyScore;
	}

	/**
	 * 设置 表达清晰态度友善.
	 *
	 * @param clearExpressionFriendlyScore
	 *            the clearExpressionFriendlyScore to set
	 */
	public void setClearExpressionFriendlyScore(Integer clearExpressionFriendlyScore){
		this.clearExpressionFriendlyScore = clearExpressionFriendlyScore;
	}

	/**
	 * 获得 对授课时间的掌控度.
	 *
	 * @return the teachTimeDegreeControlScore
	 */
	public Integer getTeachTimeDegreeControlScore(){
		return teachTimeDegreeControlScore;
	}

	/**
	 * 设置 对授课时间的掌控度.
	 *
	 * @param teachTimeDegreeControlScore
	 *            the teachTimeDegreeControlScore to set
	 */
	public void setTeachTimeDegreeControlScore(Integer teachTimeDegreeControlScore){
		this.teachTimeDegreeControlScore = teachTimeDegreeControlScore;
	}

	/**
	 * 获得 现场气氛调节能力.
	 *
	 * @return the sceneAtmosphereAdjustmentAbilityScore
	 */
	public Integer getSceneAtmosphereAdjustmentAbilityScore(){
		return sceneAtmosphereAdjustmentAbilityScore;
	}

	/**
	 * 设置 现场气氛调节能力.
	 *
	 * @param sceneAtmosphereAdjustmentAbilityScore
	 *            the sceneAtmosphereAdjustmentAbilityScore to set
	 */
	public void setSceneAtmosphereAdjustmentAbilityScore(Integer sceneAtmosphereAdjustmentAbilityScore){
		this.sceneAtmosphereAdjustmentAbilityScore = sceneAtmosphereAdjustmentAbilityScore;
	}

	/**
	 * 获得 pPT内容.
	 *
	 * @return the pptContentScore
	 */
	public Integer getPptContentScore(){
		return pptContentScore;
	}

	/**
	 * 设置 pPT内容.
	 *
	 * @param pptContentScore
	 *            the pptContentScore to set
	 */
	public void setPptContentScore(Integer pptContentScore){
		this.pptContentScore = pptContentScore;
	}

	/**
	 * 获得 pPT设计.
	 *
	 * @return the pptDesignScore
	 */
	public Integer getPptDesignScore(){
		return pptDesignScore;
	}

	/**
	 * 设置 pPT设计.
	 *
	 * @param pptDesignScore
	 *            the pptDesignScore to set
	 */
	public void setPptDesignScore(Integer pptDesignScore){
		this.pptDesignScore = pptDesignScore;
	}

	/**
	 * 获得 对我有用.
	 *
	 * @return the usefulToMeScore
	 */
	public Integer getUsefulToMeScore(){
		return usefulToMeScore;
	}

	/**
	 * 设置 对我有用.
	 *
	 * @param usefulToMeScore
	 *            the usefulToMeScore to set
	 */
	public void setUsefulToMeScore(Integer usefulToMeScore){
		this.usefulToMeScore = usefulToMeScore;
	}

	/**
	 * 获得 干货.
	 *
	 * @return the ganhuoScore
	 */
	public Integer getGanhuoScore(){
		return ganhuoScore;
	}

	/**
	 * 设置 干货.
	 *
	 * @param ganhuoScore
	 *            the ganhuoScore to set
	 */
	public void setGanhuoScore(Integer ganhuoScore){
		this.ganhuoScore = ganhuoScore;
	}

	/**
	 * 获得 是否满意老师.
	 *
	 * @return the satisfiedTeacherScore
	 */
	public Integer getSatisfiedTeacherScore(){
		return satisfiedTeacherScore;
	}

	/**
	 * 设置 是否满意老师.
	 *
	 * @param satisfiedTeacherScore
	 *            the satisfiedTeacherScore to set
	 */
	public void setSatisfiedTeacherScore(Integer satisfiedTeacherScore){
		this.satisfiedTeacherScore = satisfiedTeacherScore;
	}

	/**
	 * 获得 评价类型.
	 *
	 * @return the evaluationType
	 */
	public String getEvaluationType(){
		return evaluationType;
	}

	/**
	 * 设置 评价类型.
	 *
	 * @param evaluationType
	 *            the evaluationType to set
	 */
	public void setEvaluationType(String evaluationType){
		this.evaluationType = evaluationType;
	}

	/**
	 * 获得 评价内容.
	 *
	 * @return the evaluationContent
	 */
	public String getEvaluationContent(){
		return evaluationContent;
	}

	/**
	 * 设置 评价内容.
	 *
	 * @param evaluationContent
	 *            the evaluationContent to set
	 */
	public void setEvaluationContent(String evaluationContent){
		this.evaluationContent = evaluationContent;
	}

	/**
	 * 获得 沟通结果.
	 *
	 * @return the communicateResults
	 */
	public String getCommunicateResults(){
		return communicateResults;
	}

	/**
	 * 设置 沟通结果.
	 *
	 * @param communicateResults
	 *            the communicateResults to set
	 */
	public void setCommunicateResults(String communicateResults){
		this.communicateResults = communicateResults;
	}
}