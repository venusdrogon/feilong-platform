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
package com.feilong.tools.jfreechart.pie;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jfreechart.ChartDatesetUtil;
import com.feilong.tools.jfreechart.ChartUtil;

/**
 * A factory for creating PieChart objects.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 01:31:12
 */
public class PieChartFactory{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(PieChartFactory.class);

	/**
	 * Creates a new PieChart object.
	 * 
	 * @param pieChartEntity
	 *            the pie chart entity
	 * @param pieType
	 *            the pie type
	 * @return the chart util
	 */
	public static ChartUtil createChartUtil(PieChartEntity pieChartEntity,PieType pieType){
		switch (pieType) {
			case PIE3D:
				return new Pie3DChartUtil(pieChartEntity);
			default:
				return new DefaultPieChartUtil(pieChartEntity, pieType);
		}
	}

	/**
	 * Creates a new PieChart object.
	 * 
	 * @param pieChartEntity
	 *            the pie chart entity
	 * @param pieType
	 *            the pie type
	 * @return the j free chart
	 */
	public static JFreeChart createJFreeChart(PieChartEntity pieChartEntity,PieType pieType){
		String chartTitle = pieChartEntity.getChartTitle();// freeChart title
		PieDataset pieDataset = ChartDatesetUtil.getPieDataset(pieChartEntity);// data
		boolean showLegend = pieChartEntity.isShowLegend();// include legend
		boolean tooltips = pieChartEntity.isTooltips();
		boolean urls = pieChartEntity.isUrls();
		switch (pieType) {
			case PIE3D:
				return ChartFactory.createPieChart3D(chartTitle, pieDataset, showLegend, tooltips, urls);
			case PIE:
				return ChartFactory.createPieChart(chartTitle, pieDataset, showLegend, tooltips, urls);
			case RING:
				return ChartFactory.createRingChart(chartTitle, pieDataset, showLegend, tooltips, urls);
			default:
				throw new IllegalArgumentException("FeiLongBasePieChartUtil don't support " + pieType);
		}
	}
}
