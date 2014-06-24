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
package com.feilong.tools.jfreechart;

import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.jfreechart.category.CategoryChartEntity;
import com.feilong.tools.jfreechart.category.Line3DChartUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:07:18
 */
@SuppressWarnings("all")public class BarChart3DUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(BarChart3DUtilTest.class);

	@Test
	public void createImage() throws IOException{
		String[] rowKeys = { "First", "Second", "Third" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("原始(1 channel)", new double[] { 143, 37, 35 });
		categoryAndValues.put("优化sql(1 channel)", new double[] { 118, 6, 5 });
		categoryAndValues.put("加缓存(1 channel)", new double[] { 124, 6, 5 });
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
		String chartTitle = "Nike取价优化性能对比图V1.0";
		String categoryAxisLabel = "One member has one channel";
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

		ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		
		ChartUtil jfreeChartUtil = new Line3DChartUtil(categoryChartEntity);
		jfreeChartUtil.createImage(chartInfoEntity);
	}

	@Test
	public void createImage1() throws IOException{
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

		String chartTitle = "Nike取价优化性能对比图V1.0";
		String categoryAxisLabel = "One member has one channel";
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
		ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		ChartUtil jfreeChartUtil = new Line3DChartUtil(categoryChartEntity);
		jfreeChartUtil.createImage(chartInfoEntity);
	}
}
