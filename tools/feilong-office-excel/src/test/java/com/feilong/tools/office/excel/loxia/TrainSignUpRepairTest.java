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
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.PropertyUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.ArrayUtil;
import com.feilong.commons.core.util.CollectionsUtil;

/**
 * The Class TrainSignUpEntityTest.
 */
public class TrainSignUpRepairTest extends BaseTrainSignUpTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TrainSignUpRepairTest.class);

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
}