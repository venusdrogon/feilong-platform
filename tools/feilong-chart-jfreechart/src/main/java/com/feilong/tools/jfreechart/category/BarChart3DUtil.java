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
package com.feilong.tools.jfreechart.category;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;

/**
 * The Class BarChart3DUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 16 00:05:23
 */
public final class BarChart3DUtil extends CategoryChartUtil{

    /**
     * Instantiates a new bar chart3 d util.
     * 
     * @param categoryChartEntity
     *            the category chart entity
     */
    public BarChart3DUtil(CategoryChartEntity categoryChartEntity){
        super(categoryChartEntity, CategoryChartType.BAR3D);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis
     * )
     */
    @Override
    protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot
     * )
     */
    @Override
    protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
     */
    @Override
    protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
        // TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.category.FeiLongCategoryChartUtil#setDefaultCategoryItemRenderer()
     */
    @Override
    protected void setDefaultCategoryItemRenderer(){
        // TODO Auto-generated method stub
    }
}
