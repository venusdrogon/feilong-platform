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
package com.feilong.tools.jfreechart;

import java.awt.Font;
import java.io.IOException;
import java.util.LinkedHashMap;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.ui.Align;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.ColorUtil;
import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.awt.FontType;
import com.feilong.commons.core.awt.FontUtil;
import com.feilong.commons.core.awt.ImageUtil;
import com.feilong.test.TestConstants;
import com.feilong.tools.jfreechart.category.BarChartUtil;
import com.feilong.tools.jfreechart.category.CategoryChartEntity;

/**
 * The Class BarChartUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午02:26:38
 */
@SuppressWarnings("all")
public class BarChartUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log				= LoggerFactory.getLogger(BarChartUtilTest.class);

	/** The jfree chart util. */
	private BarChartUtil		jfreeChartUtil	= null;

	/** The chart info entity. */
	private ChartInfoEntity		chartInfoEntity;

	// String pathname =TestConstants.WATERMARK_PRESSIMG;

	/** The pathname. */
	String						pathname		= "E:\\DataOther\\Material\\baozun.png";

	/**
	 * @throws IOException
	 */
	private void createImageAndOpen(BarChartUtil jfreeChartUtil,ChartInfoEntity chartInfoEntity) throws IOException{
		jfreeChartUtil.createImage(chartInfoEntity);

		String url = chartInfoEntity.getChartPath() + "/" + chartInfoEntity.getImageNameOrOutputStream().toString();
		log.info(url);
		DesktopUtil.open(url);
	}

	/**
	 * 价格图.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void makeBarChart_price() throws IOException{
		String[] rowKeys = { "First", "Second", "Third" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("优化前", new double[] { 143, 37, 35 });
		categoryAndValues.put("优化sql", new double[] { 118, 6, 5 });
		categoryAndValues.put("优化加缓存", new double[] { 124, 6, 5 });
		// // **********************************
		// categoryAndValues = new LinkedHashMap<String, double[]>();
		// categoryAndValues.put("原始(2个渠道)", new double[] { 140, 47, 31 });
		// categoryAndValues.put("优化sql(2个渠道)", new double[] { 119, 6, 6 });
		// categoryAndValues.put("加缓存(2个渠道)", new double[] { 157, 8, 6 });
		// // ******************************
		// categoryAndValues = new LinkedHashMap<String, double[]>();
		// categoryAndValues.put("原始(4个渠道)", new double[] { 142, 32, 42 });
		// categoryAndValues.put("优化sql(4个渠道)", new double[] { 119, 6, 6 });
		// categoryAndValues.put("加缓存(4个渠道)", new double[] { 146, 8, 7 });
		String chartTitle = "xxx取价优化耗时对比图 V1.0";
		String categoryAxisLabel = "One member has one channel,循环3次取价对比";
		String valueAxisLabel = "单位(毫秒)";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 1200;
		int height = 800;

		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);

		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);

		jfreeChartUtil = new BarChartUtil(categoryChartEntity);

		JFreeChart jFreeChart = jfreeChartUtil.getJFreeChart();

		jFreeChart.setBackgroundImage(ImageUtil.getBufferedImage(TestConstants.WATERMARK_PRESSIMG));
		jFreeChart.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
		jFreeChart.setBackgroundImageAlpha(0.6f);

		CategoryPlot categoryPlot = jfreeChartUtil.getCategoryPlot();
		categoryPlot.setBackgroundPaint(ColorUtil.getColor("313131"));
		categoryPlot.setBackgroundAlpha(1f);
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setDomainGridlinePosition(CategoryAnchor.END);
		categoryPlot.setDomainGridlinePaint(ColorUtil.getColor("FF6600"));
		categoryPlot.setRangeGridlinePaint(ColorUtil.getColor("FF6600"));

		jfreeChartUtil.getNumberAxis().setTickLabelPaint(ColorUtil.getColor("FF6600"));
		// y轴 数据轴网格
		// Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE);
		// categoryPlot.setRangeGridlineStroke(stroke);
		BarRenderer barRenderer = jfreeChartUtil.getBarRenderer();
		barRenderer.setBaseItemLabelPaint(ColorUtil.getColor("FF6600"));// FF6600
		barRenderer.setBaseItemLabelFont(FontUtil.getFont(FontType.VERDANA, Font.PLAIN, 20));
		createImageAndOpen(jfreeChartUtil, chartInfoEntity);
	}

	/**
	 * Make bar chart_price1.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void makeBarChart_price1() throws IOException{
		String[] rowKeys = { "基调网络压力并发测试 订单创建情况" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("30", new double[] { 15 });
		categoryAndValues.put("31", new double[] { 21 });
		categoryAndValues.put("32", new double[] { 10 });
		categoryAndValues.put("33", new double[] { 12 });
		categoryAndValues.put("34", new double[] { 4 });
		categoryAndValues.put("35", new double[] { 173 });
		categoryAndValues.put("38", new double[] { 315 });
		categoryAndValues.put("39", new double[] { 68 });
		categoryAndValues.put("40", new double[] { 153 });
		categoryAndValues.put("41", new double[] { 200 });
		categoryAndValues.put("43", new double[] { 32 });
		categoryAndValues.put("44", new double[] { 16 });
		categoryAndValues.put("45", new double[] { 20 });
		categoryAndValues.put("46", new double[] { 9 });
		categoryAndValues.put("47", new double[] { 5 });
		categoryAndValues.put("48", new double[] { 21 });
		categoryAndValues.put("52", new double[] { 34 });
		categoryAndValues.put("53", new double[] { 22 });
		categoryAndValues.put("54", new double[] { 1 });

		String chartTitle = "基调网络压力并发测试 订单创建情况 V1.0";
		String categoryAxisLabel = "";
		String valueAxisLabel = "单位(笔)";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 1200;
		int height = 800;
		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);
		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		jfreeChartUtil = new BarChartUtil(categoryChartEntity);
		JFreeChart jFreeChart = jfreeChartUtil.getJFreeChart();

		jFreeChart.setBackgroundImage(ImageUtil.getBufferedImage(pathname));
		jFreeChart.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
		jFreeChart.setBackgroundImageAlpha(0.6f);
		CategoryPlot categoryPlot = jfreeChartUtil.getCategoryPlot();
		categoryPlot.setBackgroundPaint(ColorUtil.getColor("313131"));
		categoryPlot.setBackgroundAlpha(1f);
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setDomainGridlinePosition(CategoryAnchor.END);
		categoryPlot.setDomainGridlinePaint(ColorUtil.getColor("FF6600"));
		categoryPlot.setRangeGridlinePaint(ColorUtil.getColor("FF6600"));
		jfreeChartUtil.getNumberAxis().setTickLabelPaint(ColorUtil.getColor("FF6600"));
		// y轴 数据轴网格
		// Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE);
		// categoryPlot.setRangeGridlineStroke(stroke);
		BarRenderer barRenderer = jfreeChartUtil.getBarRenderer();
		barRenderer.setBaseItemLabelPaint(ColorUtil.getColor("FF6600"));// FF6600
		barRenderer.setBaseItemLabelFont(FontUtil.getFont(FontType.VERDANA, Font.PLAIN, 20));
		createImageAndOpen(jfreeChartUtil, chartInfoEntity);
	}

	/**
	 * 时间段登录.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void 时间段登录() throws IOException{
		String[] rowKeys = { "基调有效登录测试用户" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("00:00-01:00", new double[] { 2996 });
		categoryAndValues.put("00:30-01:00", new double[] { 2956 });

		String chartTitle = "基调有效登录测试用户";
		String categoryAxisLabel = "时间段";
		String valueAxisLabel = "单位(个)";
		String imageNameOrOutputStream = "基调有效登录测试用户" + ".png";

		int width = 800;
		int height = 600;

		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);

		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);

		jfreeChartUtil = new BarChartUtil(categoryChartEntity);

		JFreeChart jFreeChart = jfreeChartUtil.getJFreeChart();

		jFreeChart.setBackgroundImage(ImageUtil.getBufferedImage(pathname));
		jFreeChart.setBackgroundImageAlignment(Align.BOTTOM_RIGHT);
		jFreeChart.setBackgroundImageAlpha(0.8f);
		CategoryPlot categoryPlot = jfreeChartUtil.getCategoryPlot();
		categoryPlot.setBackgroundPaint(ColorUtil.getColor("353535"));
		categoryPlot.setBackgroundAlpha(1f);
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setDomainGridlinePosition(CategoryAnchor.END);
		categoryPlot.setDomainGridlinePaint(ColorUtil.getColor("FF6600"));
		categoryPlot.setRangeGridlinePaint(ColorUtil.getColor("FF6600"));
		jfreeChartUtil.getNumberAxis().setTickLabelPaint(ColorUtil.getColor("FF6600"));
		// jfreeChartUtil.getNumberAxis().set
		// y轴 数据轴网格
		// Stroke stroke = new BasicStroke(2f, BasicStroke.CAP_SQUARE, BasicStroke.CAP_SQUARE);
		// categoryPlot.setRangeGridlineStroke(stroke);
		BarRenderer barRenderer = jfreeChartUtil.getBarRenderer();
		barRenderer.setBaseItemLabelPaint(ColorUtil.getColor("FF6600"));// FF6600
		barRenderer.setBaseItemLabelFont(FontUtil.getFont(FontType.VERDANA, Font.PLAIN, 20));
		createImageAndOpen(jfreeChartUtil, chartInfoEntity);
	}

	/**
	 * 生成分组的柱状图.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void makeBarGroupChart() throws IOException{
		String[] rowKeys = { "北京", "上海", "广州", "成都", "深圳" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("苹果", new double[] { 672, 325, 332, 332, 332 });
		categoryAndValues.put("梨子", new double[] { 766, 521, 256, 256, 256 });
		categoryAndValues.put("葡萄", new double[] { 223, 210, 523, 523, 1523 });
		String chartTitle = "柱状图Title";
		String categoryAxisLabel = "柱状图X";
		String valueAxisLabel = "柱状图Y";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 900;
		int height = 600;
		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);
		jfreeChartUtil = new BarChartUtil(categoryChartEntity);
		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		createImageAndOpen(jfreeChartUtil, chartInfoEntity);
	}

	/**
	 * 生成单组柱状图.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void makeBarChart() throws IOException{
		String[] rowKeys = { "haha" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("其它类别", new double[] { 3000 });
		categoryAndValues.put("计算机", new double[] { 6000 });
		String chartTitle = "企业在线报名的学生人数及专业分布";
		String categoryAxisLabel = "one member has one channel";
		String valueAxisLabel = "单位(毫秒)";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 600;
		int height = 360;
		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);
		jfreeChartUtil = new BarChartUtil(categoryChartEntity);
		chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		createImageAndOpen(jfreeChartUtil, chartInfoEntity);
	}
}
