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
package com.feilong.controller.chart.mp2.progress;

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
 * The Class MP2ProgressController.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class MP2ProgressController{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(MP2ProgressController.class);

	/**
	 * A j1220131214.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/20131227MP2" })
	public String AJ1220131214(Model model){

		// *****************************************************************
		List<ChartIndex> progressList = new ArrayList<ChartIndex>();

		int totlePage = 63;
		int inTestPageCount = 16;
		int baipi = 38;
		int caipi = 33;
		int complete = 0;
		int notest = totlePage - inTestPageCount;
		progressList.add(new ChartIndex("总页面数", "总页面数", totlePage, "#FF6600"));
		progressList.add(new ChartIndex("白皮", "白皮", baipi, "#FF9E01"));
		progressList.add(new ChartIndex("彩皮", "彩皮", caipi, "#0D8ECF"));
		progressList.add(new ChartIndex("在测试计划中的页面", "在测试计划中的页面", inTestPageCount, "#0D52D1"));
		progressList.add(new ChartIndex("尚未进入测试计划中的页面", "尚未进入测试计划中的页面", notest, "#8A0CCF"));
		progressList.add(new ChartIndex("完全测试通过的页面", "完全测试通过的页面", complete, "#333333"));

		model.addAttribute("progressList", JsonUtil.format(progressList));

		return "feilong.chart.amchartsTest.mp2.20131227MP2";
	}

	/**
	 * M p220140123.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/20140123MP2" })
	public String MP220140123(Model model){

		// *****************************************************************

		int totleFunction = 77;
		int noNeedBRDCount = 1;
		int hasReview1317 = 22;

		int notReviewCount = totleFunction - noNeedBRDCount - hasReview1317;

		List<ChartIndex> progressList = new ArrayList<ChartIndex>();
		progressList.add(new ChartIndex("总function数量", "总function数量", totleFunction, "#FF6600"));
		progressList.add(new ChartIndex("不需要BRD function数量", "不需要BRD function数量", noNeedBRDCount, "#FF9E01"));
		progressList.add(new ChartIndex("13-17印尼人已经review的 function数量", "13-17印尼人已经review的 function数量", hasReview1317, "#0D8ECF"));
		progressList.add(new ChartIndex("剩余没有review的function数量", "剩余没有review的function数量", notReviewCount, "#0D52D1"));

		model.addAttribute("progressList", JsonUtil.format(progressList));

		int hasCompleteBRD = 28;
		int noCompleteBRD = notReviewCount - hasCompleteBRD;
		List<ChartIndex> noReviewProgressList = new ArrayList<ChartIndex>();

		noReviewProgressList.add(new ChartIndex("剩余没有review的function数量", "剩余没有review的function数量", notReviewCount, "#0D52D1"));

		noReviewProgressList.add(new ChartIndex("已完成BRD数量", "已完成BRD数量", hasCompleteBRD, "#FF6600"));

		noReviewProgressList.add(new ChartIndex("未完成BRD数量", "未完成BRD数量", noCompleteBRD, "#FF9E01"));

		model.addAttribute("noReviewProgressList", JsonUtil.format(noReviewProgressList));

		List<ChartIndex> noCompleteBRDProgressList = new ArrayList<ChartIndex>();

		int inprogressBRDCount = 14;

		noCompleteBRDProgressList.add(new ChartIndex("未完成BRD数量", "未完成BRD数量", noCompleteBRD, "#0D52D1"));

		noCompleteBRDProgressList.add(new ChartIndex("在做中数量", "在做中数量", inprogressBRDCount, "#FF6600"));
		noCompleteBRDProgressList.add(new ChartIndex("还没做数量", "还没做数量", noCompleteBRD - inprogressBRDCount, "#FF9E01"));

		model.addAttribute("noCompleteBRDProgressList", JsonUtil.format(noCompleteBRDProgressList));

		return "feilong.chart.amchartsTest.mp2.20140123MP2";
	}
}
