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
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.statistics.BoxAndWhiskerItem;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.junit.Test;

/**
 * The Class ChartUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 14 05:16:17
 */
public class ChartUtilTest{

	/**
	 * Creates the image.
	 *
	 * @throws IOException
	 *             the IO exception
	 */
	@Test
	public void createImage() throws IOException{
		JFreeChart chart = createGanttChart();
		//createBoxAndWhiskerChart();
		//getFreeChart(getCatagoryDataset());
		ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream("某公司的组织结构图.png");
		chartInfoEntity.setWidth(800);
		chartInfoEntity.setHeight(600);
		ChartUtil.createImage(chart, chartInfoEntity);
	}

	/**
	 * 获得 catagory dataset.
	 *
	 * @return the catagory dataset
	 */
	public static CategoryDataset getCatagoryDataset(){
		DefaultCategoryDataset dfg = new DefaultCategoryDataset();
		dfg.setValue(25, "行政人员", "行政人员");
		dfg.setValue(12, "市场人员", "市场人员");
		dfg.setValue(20, "研发人员", "研发人员");
		dfg.setValue(5, "财务人员", "财务人员");
		dfg.setValue(16, "其他人员", "其他人员");
		return dfg;
	}

	/**
	 * 获得 free chart.
	 *
	 * @param dataset
	 *            the dataset
	 * @return the free chart
	 */
	public static JFreeChart getFreeChart(CategoryDataset dataset){
		JFreeChart chart = ChartFactory.createBarChart("某公司的组织结构图", "人员分布", "人员数量", dataset, PlotOrientation.VERTICAL, true, false, false);
		chart.getTitle().setFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getLegend().setItemFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		return chart;
	}

	/**
	 * Creates the box and whisker chart.
	 *
	 * @return the j free chart
	 */
	public static JFreeChart createBoxAndWhiskerChart(){
		DefaultBoxAndWhiskerCategoryDataset boxAndWhiskerCategoryDataset = new DefaultBoxAndWhiskerCategoryDataset();
		boxAndWhiskerCategoryDataset.add(new BoxAndWhiskerItem(1, 2, 3, 4, 5, 7, null, 9, new ArrayList()), "ROW1", "COLUMN1");
		boxAndWhiskerCategoryDataset.add(new BoxAndWhiskerItem(2, 3, 3, 4, 6, 10, 7, null, new ArrayList()), "ROW1", "COLUMN2");
		JFreeChart chart = ChartFactory.createBoxAndWhiskerChart("某公司的组织结构图", "人员分布", "人员数量", boxAndWhiskerCategoryDataset, true);
		chart.getTitle().setFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getLegend().setItemFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getDomainAxis().setLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		chart.getCategoryPlot().getRangeAxis().setLabelFont(new Font("隶书", Font.BOLD + Font.ITALIC, 15));
		return chart;
	}

	/**
	 * Creates the gantt chart.
	 *
	 * @return the j free chart
	 */
	public static JFreeChart createGanttChart(){
		String[] seriesNames = { "a", "b", "c", "d" };
		String[] categoryKeys = { "111", "222", "ccc" };
		Number[][] starts = new Number[][] { { 8, 10, 11 }, { 9, 12, 14 }, { 9, 12, 14 }, { 9, 12, 14 } };
		Number[][] ends = new Number[][] { { 10, 11, 12 }, { 11, 14, 15 }, { 11, 14, 15 }, { 11, 14, 15 } };
		IntervalCategoryDataset dataset = new DefaultIntervalCategoryDataset(seriesNames, categoryKeys, starts, ends);
		JFreeChart chart = ChartFactory.createGanttChart("工作示意图", "类目", "时间", dataset, true, true, true);
		return chart;
	}
}
