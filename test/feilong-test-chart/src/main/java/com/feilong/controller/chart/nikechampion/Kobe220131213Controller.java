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
package com.feilong.controller.chart.nikechampion;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.tools.chart.index.ChartIndex;

/**
 * The Class Kobe220131213Controller.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class Kobe220131213Controller{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Kobe220131213Controller.class);

	/**
	 * Kobe220131213.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/20131213kobe2" })
	public String kobe220131213(Model model){
		// *****************************************************************
		List<ChartIndex> chartIndexList = new ArrayList<ChartIndex>();

		chartIndexList.add(new ChartIndex("PC", 93));
		chartIndexList.add(new ChartIndex("mobile", 1));

		// *****************************************************************
		List<ChartIndex> soStatusList = new ArrayList<ChartIndex>();

		soStatusList.add(new ChartIndex("下单", 95));
		soStatusList.add(new ChartIndex("个人取消", 0));
		soStatusList.add(new ChartIndex("商城取消", 1));

		model.addAttribute("ordertype_chartIndexList", JsonUtil.format(chartIndexList));
		model.addAttribute("soStatus_chartIndexList", JsonUtil.format(soStatusList));

		return "feilong.chart.amchartsTest.nikechampion.20131213kobe2";
	}
}