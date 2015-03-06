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

import java.io.IOException;
import java.util.LinkedHashMap;

import org.junit.Test;

import com.feilong.tools.jfreechart.category.CategoryChartEntity;
import com.feilong.tools.jfreechart.category.CategoryChartType;
import com.feilong.tools.jfreechart.category.DefaultCategoryChartUtil;

/**
 * The Class DefaultCategoryChartUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:32:41
 */
public class DefaultCategoryChartUtilTest{

    /**
     * Creates the image.
     *
     * @throws IOException
     *             the IO exception
     */
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
