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

/**
 * The Class AJ1220131214Controller.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class AJ1220131214Controller{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(AJ1220131214Controller.class);

	/**
	 * A j1220131214.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(value = { "/jsp/amchartsTest/20131214AJ12" })
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

		// *************************************************************

		List<SkuSalesOrderChartIndex> skuSalesOrderChartIndexList = new ArrayList<SkuSalesOrderChartIndex>();

		skuSalesOrderChartIndexList.add(new SkuSalesOrderChartIndex("130690-125", 500, 587, 25, 62));
		skuSalesOrderChartIndexList.add(new SkuSalesOrderChartIndex("153265-125", 300, 468, 49, 119));
		
		model.addAttribute("ordertype_chartIndexList", JsonUtil.format(chartIndexList));
		model.addAttribute("paymentTypeList", JsonUtil.format(paymentTypeList));
		model.addAttribute("skuSalesOrderChartIndexList", JsonUtil.format(skuSalesOrderChartIndexList));

		return "feilong.chart.amchartsTest.nikechampion.20131214AJ12";
	}
}
