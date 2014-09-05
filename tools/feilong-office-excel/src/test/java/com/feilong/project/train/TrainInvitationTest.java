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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.awt.toolkit.ClipboardUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.project.train.entity.AttendanceInfoEntity;
import com.feilong.project.train.entity.TrainInvitationInfoEntity;
import com.feilong.project.train.entity.TrainSignUpEntity;
import com.feilong.tools.mail.internet.InternetAddressUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * 培训通知.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月4日 下午4:12:27
 * @since 1.0.8
 */
public class TrainInvitationTest extends BaseTrainSignUpTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TrainInvitationTest.class);

	/**
	 * TestTrainSignUpEntityTest.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void testCreateTrainInvitation() throws IOException{
		String templateInClassPath = "\\loxia\\excel\\template\\trainInvitation-exception.html";
		AttendanceInfoEntity attendanceInfoEntity = new AttendanceInfoEntity("Java异常", "2014-09-05", "金鑫", "F楼培训室");

		TrainInvitationInfoEntity trainInvitationInfoEntity = new TrainInvitationInfoEntity();
		trainInvitationInfoEntity.setTrainTime("2014-09-05(星期五) 15:00");
		trainInvitationInfoEntity.setTrainAddress("F栋2楼培训室");
		trainInvitationInfoEntity.setCourseName("Java异常");
		trainInvitationInfoEntity
						.setAgenda("E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-office-excel\\src\\test\\resources\\loxia\\excel\\20140905 Exception\\Agenda.png");

		createTrainInvitation("Java异常", templateInClassPath, attendanceInfoEntity, trainInvitationInfoEntity);
	}

	/**
	 * Creates the train invitation.
	 *
	 * @param courseName
	 *            课程名称
	 * @param templateInClassPath
	 *            the template in class path
	 * @param attendanceInfoEntity
	 *            the attendance info entity
	 * @param trainInvitationInfoEntity
	 *            the train invitation info entity
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * @throws IOException
	 *             the IO exception
	 */
	private void createTrainInvitation(
					String courseName,
					String templateInClassPath,
					AttendanceInfoEntity attendanceInfoEntity,
					TrainInvitationInfoEntity trainInvitationInfoEntity) throws UnsupportedEncodingException,IOException{
		//**************************该次课程所有报名人员名单************************************************************
		List<TrainSignUpEntity> currentCourseTrainSignUpEntityList = CollectionsUtil
						.select(trainSignUpEntityList, "courseName", courseName);

		Collections.sort(currentCourseTrainSignUpEntityList, comparator);

		//*******************************************************************************************

		//发送邮件名单
		parseSendEmails(currentCourseTrainSignUpEntityList);

		//*******************************************************************************************

		//最终可以成功参与的人员名单
		List<TrainSignUpEntity> finalTrainSignUpEntityList = null;

		//下一期名单
		List<TrainSignUpEntity> nextTrainSignUpEntityList = new ArrayList<TrainSignUpEntity>();
		//编内人员
		List<TrainSignUpEntity> selectTrainSignUpEntityList = new ArrayList<TrainSignUpEntity>();
		//感谢人员列表
		List<TrainSignUpEntity> thanksTrainSignUpEntityList = new ArrayList<TrainSignUpEntity>();

		//*******************************************************************************************
		//总报名人数
		int currentCourseSignUpTotalCount = currentCourseTrainSignUpEntityList.size();

		boolean isMoreThanThreshold = currentCourseSignUpTotalCount > thresholdCount;

		if (!isMoreThanThreshold){//座位充足
			//所有报名的人 都可以参与
			finalTrainSignUpEntityList = currentCourseTrainSignUpEntityList;

		}else{

			selectTrainSignUpEntityList = CollectionsUtil.select(currentCourseTrainSignUpEntityList, "offStaff", "");
			//log.debug("编内人员:\n{}", selectTrainSignUpEntityList.size());

			//编外人员
			thanksTrainSignUpEntityList = CollectionsUtil.select(currentCourseTrainSignUpEntityList, "offStaff", "1");
			//log.debug("编外人员:\n{}", thanksTrainSignUpEntityList.size());

			//随机洗牌
			Collections.shuffle(selectTrainSignUpEntityList);

			//log.debug("\n{}", JsonUtil.format(selectTrainSignUpEntityList));

			//取前30人
			//返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。（如果 fromIndex 和 toIndex 相等，则返回的列表为空）。
			finalTrainSignUpEntityList = selectTrainSignUpEntityList.subList(0, thresholdCount);

			//		log.debug(
			//						"subList.size:{},\n{},",
			//						finalTrainSignUpEntityList.size(),
			//						JsonUtil.format(CollectionsUtil.getPropertyValueList(finalTrainSignUpEntityList, "name")));

			//下一期名单
			nextTrainSignUpEntityList = (List<TrainSignUpEntity>) CollectionUtils.subtract(
							selectTrainSignUpEntityList,
							finalTrainSignUpEntityList);
			//		log.debug(
			//						"subtract.size:{},\n{}",
			//						nextTrainSignUpEntityList.size(),
			//						JsonUtil.format(CollectionsUtil.getPropertyValueList(nextTrainSignUpEntityList, "name")));

		}

		//*******************************************************************************************
		if (Validator.isNotNullOrEmpty(finalTrainSignUpEntityList)){
			Collections.sort(finalTrainSignUpEntityList, comparator);
		}
		if (Validator.isNotNullOrEmpty(nextTrainSignUpEntityList)){
			Collections.sort(nextTrainSignUpEntityList, comparator);
		}
		if (Validator.isNotNullOrEmpty(thanksTrainSignUpEntityList)){
			Collections.sort(thanksTrainSignUpEntityList, comparator);
		}

		//*******************************************************************************************

		//生成签到文件
		//生成签到文件
		writeAttendanceExcel(finalTrainSignUpEntityList, attendanceInfoEntity);

		//生成邀请通知
		writeHtml(
						templateInClassPath,
						isMoreThanThreshold,
						finalTrainSignUpEntityList,
						nextTrainSignUpEntityList,
						thanksTrainSignUpEntityList,
						trainInvitationInfoEntity);
	}

	/**
	 * Parses the send emails.
	 *
	 * @param trainSignUpEntityList
	 *            the train sign up entity list
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public void parseSendEmails(List<TrainSignUpEntity> trainSignUpEntityList) throws UnsupportedEncodingException{
		List<String> fieldValueList = CollectionsUtil.getPropertyValueList(trainSignUpEntityList, "trainSignUpEmployeeEntity.email");

		//log.debug(JsonUtil.format(fieldValueList));

		Collections.sort(fieldValueList);

		//log.debug("\n{}", CollectionsUtil.toString(fieldValueList, new JoinStringEntity(";")));

		//*-*************************************************************************

		Map<String, String> nameAndEmailMap = getPropertyValueMap(
						trainSignUpEntityList,
						"name",
						"trainSignUpEmployeeEntity.storeCategoryName",
						"trainSignUpEmployeeEntity.email");
		//log.debug(JsonUtil.format(nameAndEmailMap));

		//log.debug("\n{}", InternetAddress.toString(InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, CharsetType.UTF8), 0));

		InternetAddress[] internetAddressArray = InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, null);
		//log.debug("\n{}", InternetAddress.toString(internetAddressArray, 0));

		List<String> unicodeStringList = InternetAddressUtil.toUnicodeStringList(internetAddressArray);
		//		log.debug("\n{}", unicodeStringList);
		//		log.debug("\n{}", CollectionsUtil.toString(unicodeStringList, null));

		//************************************************
		String replace = CollectionsUtil.toString(unicodeStringList, null).replace(" ", "");
		log.debug("邮件发送:\n{}", replace);

		ClipboardUtil.setClipboardContent(replace);
	}

	/**
	 * Write html.
	 *
	 * @param templateInClassPath
	 *            the template in class path
	 * @param isMoreThanThreshold
	 *            the is more than threshold
	 * @param finalTrainSignUpEntityList
	 *            the final train sign up entity list
	 * @param nextTrainSignUpEntityList
	 *            the next train sign up entity list
	 * @param thanksTrainSignUpEntityList
	 *            the thankst train sign up entity list
	 * @param trainInvitationInfoEntity
	 *            the train invitation info entity
	 * @throws IOException
	 *             the IO exception
	 */
	private void writeHtml(
					String templateInClassPath,
					boolean isMoreThanThreshold,
					List<TrainSignUpEntity> finalTrainSignUpEntityList,
					List<TrainSignUpEntity> nextTrainSignUpEntityList,
					List<TrainSignUpEntity> thanksTrainSignUpEntityList,
					TrainInvitationInfoEntity trainInvitationInfoEntity) throws IOException{
		//*****************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();

		contextKeyValues.put("isMoreThanThreshold", isMoreThanThreshold);

		contextKeyValues.put("finalTrainSignUpEntityList", finalTrainSignUpEntityList);

		contextKeyValues.put("nextTrainSignUpEntityList", nextTrainSignUpEntityList);

		//感谢
		contextKeyValues.put("thanksTrainSignUpEntityList", thanksTrainSignUpEntityList);

		contextKeyValues.put("trainInvitationInfoEntity", trainInvitationInfoEntity);
		String parseTemplateWithClasspathResourceLoader = VelocityUtil.parseTemplateWithClasspathResourceLoader(
						templateInClassPath,
						contextKeyValues);

		//log.debug(parseTemplateWithClasspathResourceLoader);

		String filePath = SystemUtils.USER_HOME + "\\feilong\\trainSignUp\\trainSignUp"
						+ DateUtil.date2String(new Date(), DatePattern.timestamp) + ".html";
		IOWriteUtil.write(filePath, parseTemplateWithClasspathResourceLoader, CharsetType.UTF8);

		DesktopUtil.open(filePath);
	}
}