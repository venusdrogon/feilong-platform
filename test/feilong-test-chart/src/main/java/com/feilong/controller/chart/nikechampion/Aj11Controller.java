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
import com.feilong.tools.chart.index.SkuSalesOrderChartIndex;
import com.feilong.tools.chart.index.SoStatusChartIndex;

/**
 * The Class Aj11Controller.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class Aj11Controller{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(Aj11Controller.class);

	/**
	 * A j1220131214.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/20131221AJ11" })
	public String AJ1220131214(Model model){
		// *****************************************************************
		List<ChartIndex> chartIndexList = new ArrayList<ChartIndex>();

		chartIndexList.add(new ChartIndex("PC", 1055));
		chartIndexList.add(new ChartIndex("mobile", 0));

		// *****************************************************************
		List<ChartIndex> paymentTypeList = new ArrayList<ChartIndex>();

		paymentTypeList.add(new ChartIndex("6", "支付宝", 739));
		paymentTypeList.add(new ChartIndex("3", "网上支付－网银在线", 246));
		paymentTypeList.add(new ChartIndex("14", "信用卡支付", 62));
		paymentTypeList.add(new ChartIndex("131", "国际信用卡", 8));
		// *****************************************************************
		//Aj11活动页面访问次数
		List<ChartIndex> aj11blue1200VisitCountIndexList = new ArrayList<ChartIndex>();
		
		aj11blue1200VisitCountIndexList.add(new ChartIndex("12-18", "12-18", 6));
		aj11blue1200VisitCountIndexList.add(new ChartIndex("12-19", "12-19", 6));
		aj11blue1200VisitCountIndexList.add(new ChartIndex("12-20", "12-20", 873562));
		aj11blue1200VisitCountIndexList.add(new ChartIndex("12-21", "12-21", 5094493));
		aj11blue1200VisitCountIndexList.add(new ChartIndex("12-22", "12-22", 885271));
 
		// *************************************************************

		List<SkuSalesOrderChartIndex> skuSalesOrderChartIndexList = new ArrayList<SkuSalesOrderChartIndex>();

		skuSalesOrderChartIndexList.add(new SkuSalesOrderChartIndex("130690-125", 500, 587, 25, 62));
		skuSalesOrderChartIndexList.add(new SkuSalesOrderChartIndex("153265-125", 300, 468, 49, 119));

		List<SoStatusChartIndex> soStatusChartIndexList = getSoStatusChartIndexList();

		model.addAttribute("ordertype_chartIndexList", JsonUtil.format(chartIndexList));
		model.addAttribute("paymentTypeList", JsonUtil.format(paymentTypeList));
		model.addAttribute("skuSalesOrderChartIndexList", JsonUtil.format(skuSalesOrderChartIndexList));
		model.addAttribute("soCreateList", JsonUtil.format(soStatusChartIndexList));
		model.addAttribute("aj11blue1200VisitCountIndexList", JsonUtil.format(aj11blue1200VisitCountIndexList));

		return "feilong.chart.amchartsTest.nikechampion.20131221AJ11";
	}

	/**
	 * Gets the so status chart index list.
	 * 
	 * @return the so status chart index list
	 */
	private List<SoStatusChartIndex> getSoStatusChartIndexList(){
		List<SoStatusChartIndex> soStatusChartIndexList = new ArrayList<SoStatusChartIndex>();
		soStatusChartIndexList.add(new SoStatusChartIndex("00:00",4,0,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:01",150,0,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:02",220,17,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:03",275,45,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:04",289,43,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:05",281,50,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:06",207,52,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:07",136,26,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:08",121,44,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:09",96,23,0,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:10",74,24,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:11",56,33,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:12",56,22,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:13",75,20,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:14",56,25,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:15",109,40,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:16",99,41,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:17",83,35,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:18",83,24,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:19",45,24,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:20",24,30,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:21",33,33,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:22",36,32,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:23",44,22,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:24",50,26,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:25",41,27,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:26",54,48,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:27",50,30,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:28",40,34,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:29",88,66,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:30",86,64,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:31",50,69,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:32",53,56,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:33",46,44,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:34",24,22,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:35",27,50,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:36",27,39,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:37",29,55,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:38",31,46,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:39",38,73,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:40",38,4,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:41",56,91,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:42",36,84,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:43",49,151,4,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:44",36,161,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:45",7,76,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:46",7,2,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:47",7,2,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:48",7,3,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:49",7,3,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:50",7,3,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:51",7,3,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:52",7,3,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:53",15,58,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:54",15,58,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:55",41,21,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:56",41,30,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:57",1,69,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:58",3,95,3,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("00:59",3,64,3,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:00",1,96,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:01",1,86,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:02",1,53,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:03",1,38,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:04",1,38,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:05",1,23,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:06",2,29,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:07",1,26,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:08",1,11,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:09",1,20,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:10",1,29,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:11",1,26,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:12",1,14,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:13",1,13,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:14",1,13,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:15",1,9,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:16",1,5,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:17",1,9,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:18",1,2,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:19",1,8,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:20",1,4,1,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:21",2,7,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:22",2,7,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:23",2,8,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:24",2,7,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:25",2,1,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:26",2,4,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:27",2,2,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:28",2,3,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:29",2,4,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:30",2,4,2,0));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:31",1,2,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:32",1,1,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:33",1,3,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:34",1,1,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:35",1,2,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:36",1,5,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:37",1,1,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:38",1,1,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:39",1,2,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:40",1,1,2,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:41",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:42",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:43",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:44",4,4,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:45",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:46",1,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:47",5,1,2,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:48",4,3,2,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:49",5,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:50",1,7,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:51",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:52",2,3,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:53",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:54",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:55",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:56",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:57",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:58",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("01:59",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:00",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:01",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:02",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:03",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:04",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:05",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:06",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:07",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:08",1,3,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:09",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:10",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:11",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:12",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:13",4,2,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:14",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:15",4,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:16",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:17",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:18",3,4,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:19",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:20",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:21",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:22",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:23",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:24",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:25",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:26",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:27",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:28",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:29",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:30",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:31",3,2,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:32",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:33",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:34",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:35",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:36",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:37",2,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:38",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:39",2,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:40",1,2,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:41",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:42",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:43",2,4,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:44",4,4,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:45",2,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:46",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:47",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:48",2,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:49",1,2,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:50",1,3,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:51",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:52",3,1,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:53",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:54",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:55",1,2,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:56",1,3,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:57",6,2,1,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:58",8,2,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("02:59",9,5,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:00",6,7,1,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:01",7,3,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:02",9,6,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:03",9,7,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:04",9,8,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:05",8,9,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:06",10,8,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:07",9,8,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:08",9,9,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:09",4,3,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:10",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:11",9,5,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:12",9,5,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:13",1,5,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:14",8,4,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:15",10,7,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:16",8,10,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:17",8,9,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:18",7,7,1,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:19",8,4,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:20",8,8,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:21",9,6,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:22",8,6,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:23",7,8,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:24",5,8,1,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:25",9,6,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:26",4,4,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:27",8,7,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:28",10,6,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:29",11,7,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:30",10,3,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:31",9,11,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:32",11,11,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:33",8,2,1,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:34",11,6,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:35",7,8,1,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:36",1,7,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:37",1,4,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:38",2,3,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:39",3,3,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:40",4,2,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:41",4,3,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:42",3,5,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:43",4,5,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:44",6,4,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:45",3,6,1,3));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:46",4,4,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:47",2,6,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:48",2,2,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:49",9,4,1,9));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:50",22,5,1,21));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:51",16,12,1,14));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:52",9,8,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:53",23,8,3,22));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:54",24,14,3,24));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:55",17,12,2,12));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:56",6,9,2,6));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:57",12,11,5,8));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:58",13,12,1,13));
		soStatusChartIndexList.add(new SoStatusChartIndex("03:59",7,15,1,4));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:00",11,5,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:01",12,6,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:02",11,10,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:03",11,4,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:04",11,12,1,11));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:05",9,5,1,10));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:06",4,6,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:07",2,5,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:08",2,4,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:09",2,4,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:10",2,3,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:11",2,1,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:12",2,3,2,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:13",2,3,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:14",1,3,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:15",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:16",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:17",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:18",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:19",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:20",1,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:21",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:22",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:23",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:24",1,3,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:25",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:26",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:27",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:28",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:29",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:30",1,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:31",1,1,1,1));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:32",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:33",9,2,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:34",9,2,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:35",9,4,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:36",9,2,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:37",1,2,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:38",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:39",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:40",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:41",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:42",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:43",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:44",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:45",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:46",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:47",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:48",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:49",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:50",1,1,1,7));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:51",5,1,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:52",5,1,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:53",5,1,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:54",1,1,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:55",1,2,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:56",1,1,1,5));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:57",2,1,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:58",2,2,1,2));
		soStatusChartIndexList.add(new SoStatusChartIndex("04:59",2,2,1,2));


		return soStatusChartIndexList;
	}
}
