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
import com.feilong.tools.jfreechart.category.CategoryChartType;
import com.feilong.tools.jfreechart.category.DefaultCategoryChartUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:32:41
 */
@SuppressWarnings("all")
public class DefaultCategoryChartUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(DefaultCategoryChartUtilTest.class);

	@Test
	public void createImage() throws IOException{
		CategoryChartType chartType = CategoryChartType.STACKEDBAR3D;
		String[] rowKeys = { "First", "Second", "Third" };
		LinkedHashMap<String, double[]> categoryAndValues = new LinkedHashMap<String, double[]>();
		categoryAndValues.put("原始(1 channel)", new double[] { 143, 37, 35 });
		categoryAndValues.put("优化sql(1 channel)", new double[] { 118, 6, 5 });
		categoryAndValues.put("加缓存(1 channel)", new double[] { 124, 6, 5 });
		String chartTitle = "Nike取价优化性能对比图(" + chartType + ")V1.0";
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
		ChartUtil jfreeChartUtil = new DefaultCategoryChartUtil(categoryChartEntity, chartType);
		jfreeChartUtil.createImage(chartInfoEntity);
	}
}
