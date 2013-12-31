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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jfreechart.ChartDatesetUtil;

/**
 * 堆栈柱子
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:14:17
 */
public final class StackedBarChartUtil extends CategoryChartUtil{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(StackedBarChartUtil.class);

	private StackedBarRenderer	stackedBarRenderer;

	public StackedBarChartUtil(CategoryChartEntity categoryChartEntity){
		super(ChartFactory.createStackedBarChart(//
				categoryChartEntity.getChartTitle(), // 图表标题
				categoryChartEntity.getCategoryAxisLabel(), // 目录轴的显示标签
				categoryChartEntity.getValueAxisLabel(),// 数值轴的显示标签
				ChartDatesetUtil.getCategoryDataset(categoryChartEntity), // 数据集
				categoryChartEntity.getPlotOrientation(), // 图表方向，垂直（竖向）horizontal平行的
				categoryChartEntity.isShowLegend(),// 是否显示图例(对于简单的柱状图必须是false) 是否显示legend，即下面说明
				categoryChartEntity.isTooltips(),// 
				categoryChartEntity.isUrls()// 是否生成URL链接
				));
	}

	protected void setDefaultCategoryItemRenderer(){
		// Renderer 对象是图形的绘制单元
		/**
		 * 堆栈柱子
		 */
		stackedBarRenderer = (StackedBarRenderer) categoryPlot.getRenderer();
		// 设置柱子宽度
		stackedBarRenderer.setMaximumBarWidth(0.05);
		// 设置柱子高度
		stackedBarRenderer.setMinimumBarLength(0.1);
		// 设置柱的边框颜色
		stackedBarRenderer.setBaseOutlinePaint(Color.BLACK);
		// 设置柱的边框可见
		stackedBarRenderer.setDrawBarOutline(true);
		// // 设置柱的颜色(可设定也可默认)
		stackedBarRenderer.setSeriesPaint(0, new Color(204, 255, 204));
		// stackedBarRenderer.setSeriesPaint(1, new Color(255, 204, 153));
		stackedBarRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		// 设置每个地区所包含的平行柱的之间距离
		stackedBarRenderer.setItemMargin(0.4);
	}

	/**
	 * @return the stackedBarRenderer
	 */
	public StackedBarRenderer getStackedBarRenderer(){
		return stackedBarRenderer;
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
