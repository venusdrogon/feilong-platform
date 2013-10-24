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
package com.feilong.tools.jfreechart.category;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;
import com.feilong.commons.core.enumeration.FontType;
import com.feilong.tools.jfreechart.ChartDatesetUtil;
import com.feilong.tools.jfreechart.ChartUtil;

/**
 * 线状图,柱状图等基类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 3 02:06:10
 */
public abstract class CategoryChartUtil extends ChartUtil{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(CategoryChartUtil.class);

	/** 图表区域对象 */
	protected CategoryPlot		categoryPlot;

	private CategoryAxis		categoryAxis;

	private NumberAxis			numberAxis;

	/**
	 * @param categoryChartEntity
	 * @param line3d
	 */
	public CategoryChartUtil(CategoryChartEntity categoryChartEntity, CategoryChartType chartType){
		this(CategoryChartUtil.createJFreeChart(categoryChartEntity, chartType));
	}

	/**
	 * @param jFreeChart
	 */
	public CategoryChartUtil(JFreeChart jFreeChart){
		super(jFreeChart);
		setDefaultCategoryPlotAttributes();
		setDefaultCategoryAxisAttributes();
		setDefaultValueAxisAttributes();
		setDefaultCategoryItemRenderer();
	}

	public static JFreeChart createJFreeChart(CategoryChartEntity categoryChartEntity,CategoryChartType chartType){
		// 图表标题
		String chartTitle = categoryChartEntity.getChartTitle();
		// 目录轴的显示标签
		String categoryAxisLabel = categoryChartEntity.getCategoryAxisLabel();
		// 数值轴的显示标签
		String valueAxisLabel = categoryChartEntity.getValueAxisLabel();
		// 数据集
		CategoryDataset categoryDataset = ChartDatesetUtil.getCategoryDataset(categoryChartEntity);
		// 图表方向，垂直（竖向）horizontal平行的
		PlotOrientation plotOrientation = categoryChartEntity.getPlotOrientation();
		// 是否显示图例(对于简单的柱状图必须是false) 是否显示legend，即下面说明
		boolean showLegend = categoryChartEntity.isShowLegend();
		//
		boolean tooltips = categoryChartEntity.isTooltips();
		// 是否生成URL链接
		boolean urls = categoryChartEntity.isUrls();
		switch (chartType) {
			case line:
				return ChartFactory
						.createLineChart(chartTitle, categoryAxisLabel, valueAxisLabel, categoryDataset, plotOrientation, showLegend, tooltips, urls);
			case line3D:
				return ChartFactory.createLineChart3D(
						chartTitle,
						categoryAxisLabel,
						valueAxisLabel,
						categoryDataset,
						plotOrientation,
						showLegend,
						tooltips,
						urls);
			case bar:
				return ChartFactory.createBarChart(chartTitle, categoryAxisLabel, valueAxisLabel, categoryDataset, plotOrientation, showLegend, tooltips, urls);
			case bar3D:
				return ChartFactory.createBarChart3D(
						chartTitle,
						categoryAxisLabel,
						valueAxisLabel,
						categoryDataset,
						plotOrientation,
						showLegend,
						tooltips,
						urls);
			case area:
				return ChartFactory
						.createAreaChart(chartTitle, categoryAxisLabel, valueAxisLabel, categoryDataset, plotOrientation, showLegend, tooltips, urls);
			case stackedArea:
				return ChartFactory.createStackedAreaChart(
						chartTitle,
						categoryAxisLabel,
						valueAxisLabel,
						categoryDataset,
						plotOrientation,
						showLegend,
						tooltips,
						urls);
			case stackedBar:
				return ChartFactory.createStackedBarChart(
						chartTitle,
						categoryAxisLabel,
						valueAxisLabel,
						categoryDataset,
						plotOrientation,
						showLegend,
						tooltips,
						urls);
			case stackedBar3D:
				return ChartFactory.createStackedBarChart3D(
						chartTitle,
						categoryAxisLabel,
						valueAxisLabel,
						categoryDataset,
						plotOrientation,
						showLegend,
						tooltips,
						urls);
			default:
				throw new IllegalArgumentException("chartType don't support");
		}
	}

	/**
	 * 设置CategoryPlot图表区域对象属性
	 * 
	 * @param freeChart
	 * @param freeChartEntity
	 *            参数
	 */
	protected void setDefaultCategoryPlotAttributes(){
		// 图表区域对象
		categoryPlot = getJFreeChart().getCategoryPlot();
		// 设置轴和面板之间的距离 参数1：上距 参数2：左距 参数3：下距 参数4：右距
		categoryPlot.setAxisOffset(new RectangleInsets(5d, 5d, 5d, 5d));
		// 设置柱的透明度
		categoryPlot.setForegroundAlpha(0.8f);
		// 设置此区域背景色透明度
		categoryPlot.setBackgroundAlpha(0.5f);
		// 设定图表数据显示部分背景色 （注意，系统取色的时候要使用16位的模式来查看颜色编码，这样比较准确）
		categoryPlot.setBackgroundPaint(new Color(230, 230, 230));
		// 设置是否显示边框,true in debug mode
		categoryPlot.setOutlineVisible(true);
		/**************************** DomainGrid x轴 分类轴网格 **********************************************/
		// x轴 分类轴网格是否可见
		categoryPlot.setDomainGridlinesVisible(false);
		// x虚线色彩
		categoryPlot.setDomainGridlinePaint(Color.gray);
		// 设置纵横坐标的显示位置
		categoryPlot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);// 学校显示在下端(柱子竖直)或左侧(柱子水平)
		/**************************** RangeGridlines y轴 数据轴网格 **********************************************/
		//y轴 数据轴网格
		//Stroke stroke = new BasicStroke(0.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
		//categoryPlot.setRangeGridlineStroke(stroke);
		// y轴 数据轴网格是否可见
		categoryPlot.setRangeGridlinesVisible(true);//false true
		// y虚线色彩
		categoryPlot.setRangeGridlinePaint(Color.gray);
		// 人数显示在下端(柱子水平)或左侧(柱子竖直)
		categoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
		/***************************** NoDataMessage *****************************************/
		//无数据时显示内容
		categoryPlot.setNoDataMessage("无对应的数据，请重新查询。");
		//无数据时显示内容字体
		categoryPlot.setNoDataMessageFont(FontUtil.getFont(FontType.kaiti, 14));
		//无数据时显示内容字体颜色
		categoryPlot.setNoDataMessagePaint(Color.red);
		setChildDefaultCategoryPlotAttributes(categoryPlot);
	}

	/**
	 * 设置x轴公共属性
	 */
	protected void setDefaultCategoryAxisAttributes(){
		// x轴设置
		categoryAxis = categoryPlot.getDomainAxis();
		categoryAxis.setVisible(true);
		/*********************** AxisLine 轴线 *************************************************/
		//坐标轴线条是否可见（3D轴无效）
		categoryAxis.setAxisLineVisible(false);
		//坐标轴线条颜色（3D轴无效）
		categoryAxis.setAxisLinePaint(Color.GRAY);
		//categoryAxis.setAxisLineStroke(stroke);
		/************************ CategoryLabel ********************************************************/
		// 横轴标签之间的距离20%
		categoryAxis.setCategoryMargin(0.20);
		// 图表横轴与标签的距离(8像素)
		categoryAxis.setCategoryLabelPositionOffset(8);
		//横坐标标签位置(不确定)
		//categoryAxis.setCategoryLabelPositions(new CategoryLabelPositions());
		// 横轴上的Lable 倾斜
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
		// 横轴上的Lable 45度倾斜
		//categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// Lable（Math.PI/3.0）度倾斜
		// categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 3.0));
		//categoryAxis.setFixedDimension(2);
		/************************ Label ********************************************************/
		//X坐标名称
		//categoryAxis.setLabel("sasdfafafas"); //已经被categoryChartEntity.setCategoryAxisLabel  设置了
		categoryAxis.setLabelFont(FontUtil.getFont_YaHei_Plain(20));
		//坐标轴标题旋转角度（纵坐标可以旋转）
		categoryAxis.setLabelAngle(0);
		//		categoryAxis.setLabelPaint(paint);
		//		categoryAxis.setLowerMargin(margin);
		//		categoryAxis.setLabelInsets(insets);
		/*********************** Margin *************************************************/
		// 设置距离图片左端距离
		categoryAxis.setLowerMargin(0);// 0.1
		// 设置距离图片右端距离
		categoryAxis.setUpperMargin(0);// 0.1
		categoryAxis.setMaximumCategoryLabelLines(1);
		// 横轴上的 Lable 是否完整显示
		categoryAxis.setMaximumCategoryLabelWidthRatio(1f);
		//次要刻度线
		//categoryAxis.setMinorTickMarksVisible(false);
		//		categoryAxis.setMinorTickMarkInsideLength(5);
		//		categoryAxis.setMinorTickMarkOutsideLength(5);//显示有多少标记段
		/************************ TickLabels 刻度标签 ************************************************/
		//X轴的标题文字是否显示
		categoryAxis.setTickLabelsVisible(true);
		//X轴的标题文字颜色
		categoryAxis.setTickLabelPaint(Color.black);
		//设置TickLabelFont字体
		categoryAxis.setTickLabelFont(FontUtil.getFont_YaHei_Plain(15));
		/************************ TickMarks 刻度标记 *****************************************/
		//标记线是否显示
		categoryAxis.setTickMarksVisible(true);
		//标记线颜色
		categoryAxis.setTickMarkPaint(Color.GRAY);
		//标记线向外长度
		categoryAxis.setTickMarkOutsideLength(6);
		//标记线向内长度
		categoryAxis.setTickMarkInsideLength(0);
		setChildDefaultCategoryAxisAttributes(categoryAxis);
	}

	/**
	 * 设置Y轴公共属性
	 * 
	 * @param freeChartEntity
	 *            参数
	 * @param chartType
	 *            图片枚举类型
	 */
	protected void setDefaultValueAxisAttributes(){
		// y轴设置
		// 数据轴精度
		numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
		numberAxis.setVisible(true);
		/********************* AxisLine *****************************************************/
		// 坐标轴线条是否可见（3D轴无效）
		numberAxis.setAxisLineVisible(false);
		/********************* TickLabels *****************************************************/
		// 坐标轴标尺值是否显示
		numberAxis.setTickLabelsVisible(true);
		// 设置纵坐标上显示的数字字体
		numberAxis.setTickLabelFont(FontUtil.getFont_YaHei_Plain(16));
		/********************** TickMarks ****************************************************************/
		// 坐标轴标尺显示
		numberAxis.setTickMarksVisible(false);
		/********************** Label ****************************************************************/
		numberAxis.setLabelFont(FontUtil.getFont_YaHei_Plain(20));
		numberAxis.setLabelAngle(0);
		/********************** Margin ****************************************************************/
		// 设置最高的一个 Item 与图片顶端的距离
		numberAxis.setUpperMargin(0.10);
		// 设置最低的一个 Item 与图片底端的距离
		numberAxis.setLowerMargin(0.05);
		/********************** NumberFormat ***********************************************/
		// 设置纵轴精度
		NumberFormat numberFormat = new DecimalFormat("#0");
		numberAxis.setNumberFormatOverride(numberFormat);
		//numberAxis.setLeftArrow();
		// 设置当前数字轴的单位
		//numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		setChildDefaultNumberAxisAttributes(numberAxis);
	}

	/**
	 *每个独立的chart 可以设置自己默认的CategoryPlot 属性<br>
	 * can be overwrite
	 */
	protected abstract void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot);

	/**
	 *每个独立的chart 可以设置自己默认的CategoryAxis 属性<br>
	 * can be overwrite
	 */
	protected abstract void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis);

	/**
	 *每个独立的chart 可以设置自己默认的numberAxis 属性<br>
	 * can be overwrite
	 */
	protected abstract void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis);

	/**
	 * 设置 CategoryItemRenderer must be overwrite
	 */
	protected abstract void setDefaultCategoryItemRenderer();

	/**
	 * @return the categoryAxis
	 */
	public CategoryAxis getCategoryAxis(){
		return categoryAxis;
	}

	/**
	 * @return the categoryPlot
	 */
	public CategoryPlot getCategoryPlot(){
		return categoryPlot;
	}

	/**
	 * @return the numberAxis
	 */
	public NumberAxis getNumberAxis(){
		return numberAxis;
	}
}
