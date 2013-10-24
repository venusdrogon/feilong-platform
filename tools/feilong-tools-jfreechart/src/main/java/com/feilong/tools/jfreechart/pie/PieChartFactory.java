/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
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
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 01:31:12
 */
public class PieChartFactory{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(PieChartFactory.class);

	public static ChartUtil createChartUtil(PieChartEntity pieChartEntity,PieType pieType){
		switch (pieType) {
			case pie3D:
				return new Pie3DChartUtil(pieChartEntity);
			default:
				return new DefaultPieChartUtil(pieChartEntity, pieType);
		}
	}

	public static JFreeChart createJFreeChart(PieChartEntity pieChartEntity,PieType pieType){
		String chartTitle = pieChartEntity.getChartTitle();// freeChart title
		PieDataset pieDataset = ChartDatesetUtil.getPieDataset(pieChartEntity);// data
		boolean showLegend = pieChartEntity.isShowLegend();// include legend
		boolean tooltips = pieChartEntity.isTooltips();
		boolean urls = pieChartEntity.isUrls();
		switch (pieType) {
			case pie3D:
				return ChartFactory.createPieChart3D(chartTitle, pieDataset, showLegend, tooltips, urls);
			case pie:
				return ChartFactory.createPieChart(chartTitle, pieDataset, showLegend, tooltips, urls);
			case ring:
				return ChartFactory.createRingChart(chartTitle, pieDataset, showLegend, tooltips, urls);
			default:
				throw new IllegalArgumentException("FeiLongBasePieChartUtil don't support " + pieType);
		}
	}
}
