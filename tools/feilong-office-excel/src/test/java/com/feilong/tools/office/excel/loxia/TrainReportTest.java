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
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.CollectionsUtil;
import com.feilong.tools.ChartIndexUtil;
import com.feilong.tools.chart.index.ChartIndex;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class TrainSignUpEntityTest.
 */
public class TrainReportTest extends BaseTrainTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TrainReportTest.class);

	/**
	 * TestTrainSignUpEntityTest.
	 *
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void testTrainReport() throws IllegalArgumentException,IOException{
		//p0827学习名单
		List<TrainSignUpEntity> p0827TrainSignUpEntityList = CollectionsUtil.selectRejected(trainSignUpEntityList, "p0827", "");
		//assertEquals(expected, actual);

		if (log.isDebugEnabled()){
			log.debug("the param p0827TrainSignUpEntityList.size():{}", p0827TrainSignUpEntityList.size());
		}
		//*************************************************************************************************

		Map<String, List<TrainSignUpEntity>> groupByCome = CollectionsUtil.group(p0827TrainSignUpEntityList, "p0827");

		//		if (log.isDebugEnabled()){
		//			log.debug(JsonUtil.format(groupByCome.keySet()));
		//		}

		Map<String, String> object = new LinkedHashMap<String, String>();

		object.put("1", "签到");
		object.put("0", "请假");

		List<ChartIndex> comeChartIndexList = ChartIndexUtil.toChartIndexList(groupByCome, object);

		//*************************************************************************************************
		List<TrainSignUpEntity> comeList = groupByCome.get("1");
		Map<String, List<TrainSignUpEntity>> evaluationTypeAndTrainSignUpListMap = CollectionsUtil.group(comeList, "evaluationType");

		//		Map<String, String> object = new LinkedHashMap<String, String>();
		//		
		//		object.put("1", "签到");
		//		object.put("0", "请假");

		List<ChartIndex> evaluationTypeChartIndexList = ChartIndexUtil.toChartIndexList(evaluationTypeAndTrainSignUpListMap);

		//***********************************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();

		Collections.sort(comeChartIndexList);
		contextKeyValues.put("comeChartIndexList", JsonUtil.format(comeChartIndexList));

		Collections.sort(evaluationTypeChartIndexList);
		contextKeyValues.put("evaluationTypeChartIndexList", JsonUtil.format(evaluationTypeChartIndexList));

		contextKeyValues.put("evaluationTypeAndTrainSignUpListMap", evaluationTypeAndTrainSignUpListMap);
		contextKeyValues.put("comeList", comeList);

		writeAndOpenTrainReportFile(contextKeyValues);

		log.debug("p0827分组情况:\n{}", JsonUtil.format(groupByCome));
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