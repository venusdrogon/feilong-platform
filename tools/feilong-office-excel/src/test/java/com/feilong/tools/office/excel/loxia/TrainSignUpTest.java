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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.tools.mail.internet.InternetAddressUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class TrainSignUpEntityTest.
 */
public class TrainSignUpTest extends BaseTrainSignUpTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TrainSignUpTest.class);

	/**
	 * TestTrainSignUpEntityTest.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void test() throws IOException{

		//**************************************************************************************

		//发送邮件名单
		parseSendEmails();

		Collections.sort(trainSignUpEntityList, comparator);

		Map<Object, List<TrainSignUpEntity>> group = CollectionsUtil.group(trainSignUpEntityList, "storeCategoryName");
		log.debug("storeCategoryName分组情况:\n{}", JsonUtil.format(group));

		//编内人员
		List<TrainSignUpEntity> selectTrainSignUpEntityList = CollectionsUtil.select(trainSignUpEntityList, "offStaff", "");
		//log.debug("编内人员:\n{}", selectTrainSignUpEntityList.size());

		//编外人员
		List<TrainSignUpEntity> thankstTrainSignUpEntityList = CollectionsUtil.select(trainSignUpEntityList, "offStaff", "1");
		//log.debug("编外人员:\n{}", thankstTrainSignUpEntityList.size());

		//随机洗牌
		Collections.shuffle(selectTrainSignUpEntityList);

		//log.debug("\n{}", JsonUtil.format(selectTrainSignUpEntityList));

		//取前30人
		//返回列表中指定的 fromIndex（包括 ）和 toIndex（不包括）之间的部分视图。（如果 fromIndex 和 toIndex 相等，则返回的列表为空）。
		List<TrainSignUpEntity> finalTrainSignUpEntityList = selectTrainSignUpEntityList.subList(0, 30);

		//		log.debug(
		//						"subList.size:{},\n{},",
		//						finalTrainSignUpEntityList.size(),
		//						JsonUtil.format(CollectionsUtil.getPropertyValueList(finalTrainSignUpEntityList, "name")));

		//下一期名单
		List<TrainSignUpEntity> nextTrainSignUpEntityList = (List<TrainSignUpEntity>) CollectionUtils.subtract(
						selectTrainSignUpEntityList,
						finalTrainSignUpEntityList);
		//		log.debug(
		//						"subtract.size:{},\n{}",
		//						nextTrainSignUpEntityList.size(),
		//						JsonUtil.format(CollectionsUtil.getPropertyValueList(nextTrainSignUpEntityList, "name")));

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

		//log.debug(JsonUtil.format(fieldValueList));

		Collections.sort(fieldValueList);

		//log.debug("\n{}", CollectionsUtil.toString(fieldValueList, new JoinStringEntity(";")));

		//*-*************************************************************************

		Map<String, String> nameAndEmailMap = getPropertyValueMap(trainSignUpEntityList, "name", "storeCategoryName", "email");
		//log.debug(JsonUtil.format(nameAndEmailMap));

		//log.debug("\n{}", InternetAddress.toString(InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, CharsetType.UTF8), 0));

		InternetAddress[] internetAddressArray = InternetAddressUtil.toInternetAddressArray(nameAndEmailMap, null);
		//log.debug("\n{}", InternetAddress.toString(internetAddressArray, 0));

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
}