/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.jfreechart.category;

import java.awt.Color;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;

/**
 * 折线图
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:18:15
 */
public final class LineChartUtil extends CategoryChartUtil{

	@SuppressWarnings("unused")
	private static final Logger		log	= LoggerFactory.getLogger(LineChartUtil.class);

	private LineAndShapeRenderer	lineandshaperenderer;

	public LineChartUtil(CategoryChartEntity categoryChartEntity){
		super(categoryChartEntity, CategoryChartType.line3D);
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.xy.FeiLongBaseXYChartEntity#setChildDefaultNumberAxisAttributes()
	 */
	protected void setChildDefaultNumberAxisAttributes(){
		CategoryDataset categoryDataset = categoryPlot.getDataset();
		// 数据轴的数据标签 可以只显示整数标签，需要将AutoTickUnitSelection设false
		// numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		double fudong = 0.5;
		NumberAxis numberAxis = getNumberAxis();
		// 设置最大值
		numberAxis.setUpperBound(DatasetUtilities.findMaximumRangeValue(categoryDataset).doubleValue() + fudong);
		// 设置最小值
		numberAxis.setLowerBound(DatasetUtilities.findMinimumRangeValue(categoryDataset).doubleValue() - fudong);
		// 设置坐标轴的最小值，默认的是0.00000001
		// numberAxis.setAutoRangeMinimumSize(1);
		// 是否强制在自动选择的数据范围中包含0
		numberAxis.setAutoRangeIncludesZero(false);
		// 纵轴显示范围
		// numberAxis.setRange(85, 100.5);
	}

	protected void setDefaultCategoryItemRenderer(){
		// 获得renderer 注意这里是下嗍造型到lineandshaperenderer！！
		lineandshaperenderer = (LineAndShapeRenderer) categoryPlot.getRenderer();
		// 天蓝
		lineandshaperenderer.setSeriesPaint(0, new Color(41, 106, 232));
		// lineandshaperenderer.setSeriesPaint(1, new Color(255,0,255));//桃红2
		// lineandshaperenderer.setSeriesPaint(2, new Color(255,255,128));//黄色3
		// lineandshaperenderer.setSeriesPaint(3, new Color(0,255,255));//蓝4
		// lineandshaperenderer.setSeriesPaint(4, new Color(128,0,128));//紫5
		// lineandshaperenderer.setSeriesPaint(5, new Color(0,0,255));//墨绿6
		// lineandshaperenderer.setSeriesPaint(6, new Color(0,0,128));//蓝7
		// lineandshaperenderer.setSeriesPaint(7, new Color(0,204,255));//草绿
		// lineandshaperenderer.setSeriesPaint(8, new Color(192,192,192));//天蓝
		// lineandshaperenderer.setSeriesPaint(9, new Color(204,255,204));//天蓝
		// 点（即数据点）可见
		lineandshaperenderer.setBaseShapesVisible(true);
		// 显示折点数据
		lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 显示折点数据
		lineandshaperenderer.setBaseItemLabelsVisible(true);
		// 折点数据字体
		lineandshaperenderer.setBaseItemLabelFont(FontUtil.getFont_verdana_Plain(12));
		// 折点数据显示位置
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER);
		lineandshaperenderer.setBasePositiveItemLabelPosition(position);
		// 点（即数据点）间有连线可见
		lineandshaperenderer.setBaseLinesVisible(true);
		// 定义series为”First”的（即series1）点之间的连线 ，这里是虚线，默认是直线
		// lineandshaperenderer.setSeriesStroke(0, new BasicStroke(2.0F, 1, 1, 1.0F, new float[] { 10F, 6F }, 0.0F));
		// XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer();
		// xylineandshaperenderer.setSeriesLinesVisible(0, false); //第一个XYSeries数据点间连线不可见
		// xylineandshaperenderer.setSeriesShapesVisible(1, false); //第二个XYSeries数据点不可见
		// xyplot.setRenderer(xylineandshaperenderer);
	}

	/**
	 * @return the lineandshaperenderer
	 */
	public LineAndShapeRenderer getLineandshaperenderer(){
		return lineandshaperenderer;
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis)
	 */
	@Override
	protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot)
	 */
	@Override
	protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
	// TODO Auto-generated method stub
	}

	/* (non-Javadoc)
	 * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
	 */
	@Override
	protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
	// TODO Auto-generated method stub
	}
}
