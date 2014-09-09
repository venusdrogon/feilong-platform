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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.project.train.entity.TrainSignUpEntity;
import com.feilong.tools.ChartIndexUtil;
import com.feilong.tools.chart.index.ChartIndex;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * 
 * 培训报表
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月9日 上午10:54:49
 * @since 1.0.8
 */
public class TrainReport{

	/** The Constant log. */
	private static final Logger					log							= LoggerFactory.getLogger(TrainReport.class);

	/** The template in class path_report. */
	private static final String					templateInClassPath_report	= "\\loxia\\excel\\template\\trainReport.html";

	/** <code>{@value}</code>. */
	private static final String					SIGNSTATUS_COME				= "1";

	/** <code>{@value}</code>. */
	private static final String					SIGNSTATUS_NOCOME			= "0";

	/** The Constant object. */
	private static final Map<String, String>	signStatusMap;

	static{
		signStatusMap = new LinkedHashMap<String, String>();
		signStatusMap.put(SIGNSTATUS_COME, "签到");
		signStatusMap.put(SIGNSTATUS_NOCOME, "请假");
	}

	/**
	 * Creates the report.
	 *
	 * @param totalTrainSignUpEntityList
	 *            the total train sign up entity list
	 * @param courseName
	 *            the course name
	 * @throws IOException
	 *             the IO exception
	 */
	public void createReport(List<TrainSignUpEntity> totalTrainSignUpEntityList,String courseName) throws IOException{

		List<TrainSignUpEntity> currentCourseTrainSignUpEntityList = CollectionsUtil.select(
						totalTrainSignUpEntityList,
						"courseName",
						courseName);

		Map<Object, List<TrainSignUpEntity>> storeCategoryNameMap = CollectionsUtil.group(
						currentCourseTrainSignUpEntityList,
						"trainSignUpEmployeeEntity.storeCategoryName");
		log.debug("storeCategoryName分组情况:\n{}", JsonUtil.format(storeCategoryNameMap));

		//p0827学习名单
		List<TrainSignUpEntity> signStatusTrainSignUpEntityList = CollectionsUtil.selectRejected(
						currentCourseTrainSignUpEntityList,
						"signStatus",
						"");

		if (log.isDebugEnabled()){
			log.debug("the param p0827TrainSignUpEntityList.size():{}", signStatusTrainSignUpEntityList.size());
		}
		//*************************************************************************************************

		Map<String, List<TrainSignUpEntity>> groupByCome = CollectionsUtil.group(signStatusTrainSignUpEntityList, "signStatus");

		//log.debug(JsonUtil.format(groupByCome.keySet()));

		List<ChartIndex> comeChartIndexList = ChartIndexUtil.toChartIndexList(groupByCome, signStatusMap);

		//*************************************************************************************************
		List<TrainSignUpEntity> comeList = groupByCome.get(SIGNSTATUS_COME);
		Map<String, List<TrainSignUpEntity>> evaluationTypeAndTrainSignUpListMap = CollectionsUtil.group(comeList, "evaluationType");

		List<ChartIndex> evaluationTypeChartIndexList = ChartIndexUtil.toChartIndexList(evaluationTypeAndTrainSignUpListMap);
		List<ChartIndex> storeCategoryNameChartIndexList = ChartIndexUtil.toChartIndexList(storeCategoryNameMap);

		//***********************************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();

		Collections.sort(comeChartIndexList);
		contextKeyValues.put("comeChartIndexList", JsonUtil.format(comeChartIndexList));

		Collections.sort(evaluationTypeChartIndexList);
		contextKeyValues.put("evaluationTypeChartIndexList", JsonUtil.format(evaluationTypeChartIndexList));

		Collections.sort(storeCategoryNameChartIndexList);
		contextKeyValues.put("storeCategoryNameChartIndexList", JsonUtil.format(storeCategoryNameChartIndexList));

		contextKeyValues.put("evaluationTypeAndTrainSignUpListMap", evaluationTypeAndTrainSignUpListMap);
		contextKeyValues.put("comeList", comeList);
		contextKeyValues.put("trainSignUpEntityList", currentCourseTrainSignUpEntityList);
		contextKeyValues.put("groupByCome", groupByCome);
		contextKeyValues.put("p0827TrainSignUpEntityList", signStatusTrainSignUpEntityList);

		writeAndOpenTrainReportFile(contextKeyValues);
	}

	/**
	 * Write and open train report file.
	 *
	 * @param contextKeyValues
	 *            the context key values
	 * @throws IOException
	 *             the IO exception
	 */
	private void writeAndOpenTrainReportFile(Map<String, Object> contextKeyValues) throws IOException{
		String parseTemplateWithClasspathResourceLoader = VelocityUtil.parseTemplateWithClasspathResourceLoader(
						templateInClassPath_report,
						contextKeyValues);

		//*******************************************************

		String filePath = SystemUtils.USER_HOME + "\\feilong\\trainSignUp\\trainReport"
						+ DateUtil.date2String(new Date(), DatePattern.timestamp) + ".html";
		IOWriteUtil.write(filePath, parseTemplateWithClasspathResourceLoader, CharsetType.UTF8);

		DesktopUtil.open(filePath);
	}
}