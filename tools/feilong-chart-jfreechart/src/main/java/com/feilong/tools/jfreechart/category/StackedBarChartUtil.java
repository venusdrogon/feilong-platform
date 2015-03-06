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

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StackedBarRenderer;

import com.feilong.tools.jfreechart.ChartDatesetUtil;

/**
 * 堆栈柱子.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-12-31 下午05:14:17
 */
public final class StackedBarChartUtil extends CategoryChartUtil{

    /** The stacked bar renderer. */
    private StackedBarRenderer stackedBarRenderer;

    /**
     * Instantiates a new stacked bar chart util.
     * 
     * @param categoryChartEntity
     *            the category chart entity
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.category.CategoryChartUtil#setDefaultCategoryItemRenderer()
     */
    @Override
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
     * Gets the stacked bar renderer.
     * 
     * @return the stackedBarRenderer
     */
    public StackedBarRenderer getStackedBarRenderer(){
        return stackedBarRenderer;
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
        // TODO Auto-generated method stub
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
}
