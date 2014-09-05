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
package com.feilong.project.train;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.project.train.entity.AttendanceEntity;
import com.feilong.project.train.entity.AttendanceInfoEntity;
import com.feilong.project.train.entity.TrainSignUpEntity;

/**
 * The Class BaseTrainSignUpTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月29日 上午11:42:12
 * @since 1.0.8
 */
public abstract class BaseTrainSignUpTest extends BaseTrainTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(BaseTrainSignUpTest.class);

	/**
	 * Write attendance excel.
	 *
	 * @param finalTrainSignUpEntityList
	 *            the final train sign up entity list
	 * @throws IOException
	 *             the IO exception
	 */
	protected void writeAttendanceExcel(List<TrainSignUpEntity> finalTrainSignUpEntityList,AttendanceInfoEntity attendanceInfoEntity)
					throws IOException{
		//****************签到表***********************************

		List<AttendanceEntity> attendanceList = new ArrayList<AttendanceEntity>();

		int id = 0;
		for (TrainSignUpEntity trainSignUpEntity : finalTrainSignUpEntityList){
			AttendanceEntity attendanceEntity = new AttendanceEntity();
			attendanceEntity.setId(++id);
			attendanceEntity.setDepartName("技术部");
			attendanceEntity.setGroupName(trainSignUpEntity.getTrainSignUpEmployeeEntity().getStoreCategoryName());
			attendanceEntity.setName(trainSignUpEntity.getName());
			attendanceEntity.setNotice("参加");
			attendanceEntity.setAttendanceSign("");
			attendanceEntity.setRemark("");
			attendanceList.add(attendanceEntity);
		}

		String outputFileName = SystemUtils.USER_HOME + "\\feilong\\trainSignUp\\attendance"
						+ DateUtil.date2String(new Date(), DatePattern.timestamp) + ".xlsx";

		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("attendanceList", attendanceList);
		beans.put("trainAddress",attendanceInfoEntity.getTrainAddress());
		beans.put("trainInstructor",attendanceInfoEntity.getTrainInstructor() );
		beans.put("courseName",attendanceInfoEntity.getCourseName());
		beans.put("trainDate",attendanceInfoEntity.getTrainDate());

		write(CONFIGURATION, attendanceSheet, attendanceExcel, outputFileName, beans);

		DesktopUtil.open(outputFileName);
	}
}
