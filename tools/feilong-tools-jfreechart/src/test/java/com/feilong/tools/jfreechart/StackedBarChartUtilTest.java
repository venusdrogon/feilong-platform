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
import com.feilong.tools.jfreechart.category.StackedBarChartUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午04:51:49
 */
public class StackedBarChartUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(StackedBarChartUtilTest.class);

	/**
	 * 生成堆栈柱状图
	 * 
	 * @throws IOException
	 */
	@Test
	public void makeStackedBarChart() throws IOException{
		String[] rowKeys = { "苹果", "桔子" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("北京", new double[] { 88, 88 });
		categoryAndValues.put("上海", new double[] { 80, 80 });
		categoryAndValues.put("广州", new double[] { 82, 82 });
		categoryAndValues.put("成都", new double[] { 89, 89 });
		categoryAndValues.put("深圳", new double[] { 100, 100 });
		String chartTitle = "StackedBarChart";
		String categoryAxisLabel = "x坐标";
		String valueAxisLabel = "y坐标";
		String imageNameOrOutputStream = chartTitle + ".png";
		int width = 900;
		int height = 600;
		CategoryChartEntity categoryChartEntity = new CategoryChartEntity();
		categoryChartEntity.setRowKeys(rowKeys);
		categoryChartEntity.setChartTitle(chartTitle);
		categoryChartEntity.setCategoryAndValues(categoryAndValues);
		categoryChartEntity.setCategoryAxisLabel(categoryAxisLabel);
		categoryChartEntity.setValueAxisLabel(valueAxisLabel);
		StackedBarChartUtil jfreeChartUtil = new StackedBarChartUtil(categoryChartEntity);
		ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
		chartInfoEntity.setImageNameOrOutputStream(imageNameOrOutputStream);
		chartInfoEntity.setWidth(width);
		chartInfoEntity.setHeight(height);
		jfreeChartUtil.createImage(chartInfoEntity);
	}
}
