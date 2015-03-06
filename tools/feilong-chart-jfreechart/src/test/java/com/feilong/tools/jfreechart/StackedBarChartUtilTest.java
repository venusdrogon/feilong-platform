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
import com.feilong.tools.jfreechart.category.StackedBarChartUtil;

/**
 * The Class StackedBarChartUtilTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午04:51:49
 */
public class StackedBarChartUtilTest{

    /**
     * 生成堆栈柱状图.
     *
     * @throws IOException
     *             the IO exception
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
