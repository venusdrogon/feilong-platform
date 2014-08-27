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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.awt.toolkit.ClipboardUtil;
import com.feilong.commons.core.bean.BeanUtilException;
import com.feilong.commons.core.bean.PropertyUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.internet.InternetAddressUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class TrainSignUpEntityTest.
 */
public class TrainSignUpEntityTest extends BaseLoxiaExcelReaderTest{

	/** The Constant log. */
	private static final Logger				log					= LoggerFactory.getLogger(TrainSignUpEntityTest.class);

	/** The configuration. */
	private String							configuration		= "loxia/feilong-sheets.xml";

	/** The sheet. */
	private String							trainSignUpSheet	= "trainSignUpSheet";

	/** The attendance sheet. */
	private String							attendanceSheet		= "attendanceSheet";

	/** The template excel folder. */
	private String							templateExcelFolder	= "E:\\Workspaces\\feilong\\feilong-platform\\tools\\feilong-office-excel\\src\\test\\resources\\loxia\\excel";

	/** The file name. */
	private String							trainSignUpExcel	= templateExcelFolder + "\\java集合框架报名.xlsx";

	/** 文件文件夹. */
	private String							templateFolder		= templateExcelFolder + "\\template";

	/** 签到表. */
	private String							attendanceExcel		= templateFolder + "\\IT培训签到表.xlsx";

	/** trainSignUp. */
	private String							templateInClassPath	= "\\loxia\\excel\\template\\trainSignUp.html";

	/** The data name. */
	private String							dataName			= "trainSignUplist";

	/** The sheet no. */
	private int								sheetNo				= 0;

	/** The train sign up entity list. */
	private List<TrainSignUpEntity>			trainSignUpEntityList;

	/** The comparator. */
	private Comparator<TrainSignUpEntity>	comparator			= this.getTrainSignUpEntityComparator();

	/**
	 * Inits the.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Before
	public void init() throws IOException{ //取到list
		trainSignUpEntityList = getList(configuration, trainSignUpSheet, trainSignUpExcel, dataName, sheetNo);
		log.debug(JsonUtil.format(trainSignUpEntityList));
	}

	/**
	 * TestTrainSignUpEntityTest.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void testTrainSignUpEntityTest() throws IOException{
		String[] aStrings = {
				"张军",
				"夏龙",
				"王文彬",
				"欧翠华",
				"孙文强",
				"王玉莲",
				"袁晓",
				"孙宝宝",
				"叶冠杰",
				"谭斌",
				"谭林子博",
				"李广春",
				"黄毅",
				"谢云峰",
				"李俊峰",
				"代伟",
				"龙巫保",
				"康晓龙",
				"魏源海",
				"王耀华",
				"冯明雷",
				"曹子杰",
				"姚真",
				"岳松毅",
				"桑佳佳",
				"周中波",
				"钟小花",
				"胡伟",
				"刘星语",
				"周晨光" };

		Predicate predicate = new Predicate(){

			public boolean evaluate(Object object){
				String name = PropertyUtil.getProperty(object, "name");
				return ArrayUtil.isContain(aStrings, name);
			}
		};

		Collections.sort(trainSignUpEntityList, comparator);

		@SuppressWarnings("unchecked")
		List<TrainSignUpEntity> finalTrainSignUpEntityList = (List<TrainSignUpEntity>) CollectionUtils.select(
						trainSignUpEntityList,
						predicate);

		Collections.sort(finalTrainSignUpEntityList, comparator);

		log.debug(JsonUtil.format(CollectionsUtil.getPropertyValueList(finalTrainSignUpEntityList, "name")));

		//生成签到文件
		writeAttendanceExcel(finalTrainSignUpEntityList);
	}

	/**
	 * TestTrainSignUpEntityTest.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void test() throws IOException{

		//发送邮件名单
		parseSendEmails();

		Collections.sort(trainSignUpEntityList, comparator);

		Map<Object, List<TrainSignUpEntity>> group = CollectionsUtil.group(trainSignUpEntityList, "storeCategoryName");
		log.debug("storeCategoryName分组情况:\n{}", JsonUtil.format(group));

		//编内人员
		List<TrainSignUpEntity> selectTrainSignUpEntityList = CollectionsUtil.select(trainSignUpEntityList, "offStaff", "");
		log.debug("编内人员:\n{}", selectTrainSignUpEntityList.size());

		//编外人员
		List<TrainSignUpEntity> thankstTrainSignUpEntityList = CollectionsUtil.select(trainSignUpEntityList, "offStaff", "1");
		log.debug("编外人员:\n{}", thankstTrainSignUpEntityList.size());

		//随机洗牌
		Collections.shuffle(selectTrainSignUpEntityList);

		log.debug("\n{}", JsonUtil.format(selectTrainSignUpEntityList));

		//取前30人
		//返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。（如果 fromIndex 和 toIndex 相等，则返回的列表为空）。
		List<TrainSignUpEntity> finalTrainSignUpEntityList = selectTrainSignUpEntityList.subList(0, 30);

		log.debug(
						"subList.size:{},\n{},",
						finalTrainSignUpEntityList.size(),
						JsonUtil.format(CollectionsUtil.getPropertyValueList(finalTrainSignUpEntityList, "name")));

		//下一期名单
		List<TrainSignUpEntity> nextTrainSignUpEntityList = (List<TrainSignUpEntity>) CollectionUtils.subtract(
						selectTrainSignUpEntityList,
						finalTrainSignUpEntityList);
		log.debug(
						"subtract.size:{},\n{}",
						nextTrainSignUpEntityList.size(),
						JsonUtil.format(CollectionsUtil.getPropertyValueList(nextTrainSignUpEntityList, "name")));

		Collections.sort(finalTrainSignUpEntityList, comparator);
		Collections.sort(nextTrainSignUpEntityList, comparator);
		Collections.sort(thankstTrainSignUpEntityList, comparator);//thankstTrainSignUpEntityList

		//生成签到文件
		writeAttendanceExcel(finalTrainSignUpEntityList);

		//生成邀请通知
		writeHtml(finalTrainSignUpEntityList, nextTrainSignUpEntityList, thankstTrainSignUpEntityList);
	}

	/**
	 * Parses the send emails.
	 *
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public void parseSendEmails() throws UnsupportedEncodingException{
		List<String> fieldValueList = CollectionsUtil.getPropertyValueList(trainSignUpEntityList, "email");

		log.debug(JsonUtil.format(fieldValueList));

		Collections.sort(fieldValueList);

		JoinStringEntity joinStringEntity = new JoinStringEntity(";");
		String string = CollectionsUtil.toString(fieldValueList, joinStringEntity);
		log.debug("\n{}", string);

		//*-*************************************************************************

		Map<String, String> nameAndEmailMap = getPropertyValueMap(trainSignUpEntityList, "name", "storeCategoryName", "email");
		log.debug(JsonUtil.format(nameAndEmailMap));

		log.debug("\n{}", InternetAddress.toString(InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, CharsetType.UTF8), 0));

		InternetAddress[] internetAddressArray = InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, null);
		log.debug("\n{}", InternetAddress.toString(internetAddressArray, 0));

		List<String> unicodeStringList = InternetAddressUtil.toUnicodeStringList(internetAddressArray);
		log.debug("\n{}", unicodeStringList);
		log.debug("\n{}", CollectionsUtil.toString(unicodeStringList, null));

		//************************************************
		String replace = CollectionsUtil.toString(unicodeStringList, null).replace(" ", "");
		log.debug("邮件发送:\n{}", replace);

		ClipboardUtil.setClipboardContent(replace);
	}

	/**
	 * Write html.
	 *
	 * @param finalTrainSignUpEntityList
	 *            the final train sign up entity list
	 * @param nextTrainSignUpEntityList
	 *            the next train sign up entity list
	 * @param thankstTrainSignUpEntityList
	 *            the thankst train sign up entity list
	 * @throws IOException
	 *             the IO exception
	 */
	private void writeHtml(
					List<TrainSignUpEntity> finalTrainSignUpEntityList,
					List<TrainSignUpEntity> nextTrainSignUpEntityList,
					List<TrainSignUpEntity> thankstTrainSignUpEntityList) throws IOException{
		//*****************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();

		contextKeyValues.put("finalTrainSignUpEntityList", finalTrainSignUpEntityList);

		contextKeyValues.put("nextTrainSignUpEntityList", nextTrainSignUpEntityList);

		//感谢

		contextKeyValues.put("thankstTrainSignUpEntityList", thankstTrainSignUpEntityList);

		String parseTemplateWithClasspathResourceLoader = VelocityUtil.parseTemplateWithClasspathResourceLoader(
						templateInClassPath,
						contextKeyValues);

		//log.debug(parseTemplateWithClasspathResourceLoader);

		String filePath = SystemUtils.USER_HOME + "\\feilong\\trainSignUp\\trainSignUp"
						+ DateUtil.date2String(new Date(), DatePattern.timestamp) + ".html";
		IOWriteUtil.write(filePath, parseTemplateWithClasspathResourceLoader, CharsetType.UTF8);

		DesktopUtil.open(filePath);
	}

	/**
	 * Write attendance excel.
	 *
	 * @param finalTrainSignUpEntityList
	 *            the final train sign up entity list
	 * @throws IOException
	 *             the IO exception
	 */
	private void writeAttendanceExcel(List<TrainSignUpEntity> finalTrainSignUpEntityList) throws IOException{
		//****************签到表***********************************

		List<AttendanceEntity> attendanceList = new ArrayList<AttendanceEntity>();

		int id = 0;
		for (TrainSignUpEntity trainSignUpEntity : finalTrainSignUpEntityList){
			AttendanceEntity attendanceEntity = new AttendanceEntity();
			attendanceEntity.setId(++id);
			attendanceEntity.setDepartName("技术部");
			attendanceEntity.setGroupName(trainSignUpEntity.getStoreCategoryName());
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
		beans.put("trainAddress", "F楼培训室");
		beans.put("trainInstructor", "金鑫");
		beans.put("courseName", "Java集合框架");
		beans.put("trainDate", "2014-08-27");

		write(configuration, attendanceSheet, attendanceExcel, outputFileName, beans);

		DesktopUtil.open(outputFileName);
	}

	/**
	 * 获得 train sign up entity comparator.
	 *
	 * @return the train sign up entity comparator
	 */
	private Comparator<TrainSignUpEntity> getTrainSignUpEntityComparator(){
		return new Comparator<TrainSignUpEntity>(){

			public int compare(TrainSignUpEntity o1,TrainSignUpEntity o2){
				String storeCategoryName = o1.getStoreCategoryName();
				String storeCategoryName2 = o2.getStoreCategoryName();

				if (storeCategoryName.equals(storeCategoryName2)){
					return 0;
				}else if (storeCategoryName.length() > storeCategoryName2.length()){
					return 1;
				}else if (storeCategoryName.length() == storeCategoryName2.length()){
					return storeCategoryName.compareToIgnoreCase(storeCategoryName2);
				}
				return -1;
			}
		};
	}

	/**
	 * 获得 property value map.
	 *
	 * @param <O>
	 *            the generic type
	 * @param <V>
	 *            the value type
	 * @param objectList
	 *            the object list
	 * @param keyPropertyName
	 *            the key property name
	 * @param storeCategoryNamePropertyName
	 *            the store category name property name
	 * @param valuePropertyName
	 *            the value property name
	 * @return the property value map {@link LinkedHashMap}
	 * @throws NullPointerException
	 *             the null pointer exception
	 */
	private static <O, V> Map<String, V> getPropertyValueMap(
					Collection<O> objectList,
					String keyPropertyName,
					String storeCategoryNamePropertyName,
					String valuePropertyName) throws NullPointerException{
		if (Validator.isNullOrEmpty(objectList)){
			throw new NullPointerException("objectList is null or empty!");
		}

		if (Validator.isNullOrEmpty(keyPropertyName)){
			throw new NullPointerException("keyPropertyName is null or empty!");
		}

		if (Validator.isNullOrEmpty(valuePropertyName)){
			throw new NullPointerException("valuePropertyName is null or empty!");
		}

		Map<String, V> map = new LinkedHashMap<String, V>();

		try{
			for (O bean : objectList){
				String key = (String) PropertyUtil.getProperty(bean, keyPropertyName);
				String storeCategoryName1 = "" + PropertyUtil.getProperty(bean, storeCategoryNamePropertyName);
				@SuppressWarnings("unchecked")
				V value = (V) PropertyUtil.getProperty(bean, valuePropertyName);

				map.put(key + "(" + storeCategoryName1 + ")", value);
			}
		}catch (BeanUtilException e){
			e.printStackTrace();
		}
		return map;
	}
}