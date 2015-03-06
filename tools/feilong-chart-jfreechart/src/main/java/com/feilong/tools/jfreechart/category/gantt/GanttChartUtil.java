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
package com.feilong.tools.jfreechart.category.gantt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.GanttRenderer;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.RectangleInsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;
import com.feilong.tools.jfreechart.ChartDatesetUtil;
import com.feilong.tools.jfreechart.category.CategoryChartUtil;

/**
 * 甘特图.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 21 01:54:44
 */
public class GanttChartUtil extends CategoryChartUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GanttChartUtil.class);

    /** The date axis. */
    private DateAxis            dateAxis;

    /** The gantt renderer. */
    private GanttRenderer       ganttRenderer;

    /**
     * Instantiates a new gantt chart util.
     * 
     * @param jFreeChart
     *            the j free chart
     */
    public GanttChartUtil(JFreeChart jFreeChart){
        super(jFreeChart);
    }

    /**
     * Instantiates a new gantt chart util.
     * 
     * @param ganttChartEntity
     *            the gantt chart entity
     */
    public GanttChartUtil(GanttChartEntity ganttChartEntity){
        this(createJFreeChart(ganttChartEntity));
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.xy.FeiLongBaseXYChartEntity#setDefaultCategoryItemRenderer()
     */
    @Override
    protected void setDefaultCategoryItemRenderer(){
        ganttRenderer = (GanttRenderer) getCategoryPlot().getRenderer();
        // Paint[] arrayOfPaint = createPaint();
        // barRenderer = new CustomBarRenderer(arrayOfPaint);
        /************************* Base *********************************************/
        //GradientPaint paint = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, Color.yellow);
        // StandardBarPainter painter = new StandardBarPainter();
        //ganttRenderer.setBasePaint(paint);
        // 设置柱子边框颜色
        // barRenderer.setBaseOutlinePaint(freeChartEntity.getPaint_barRenderer_BaseOutline());
        // 设置鼠标提示信息
        //barRenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        /************************* BaseItemLabel *********************************************/
        // 显示每个柱的数值，并修改该数值的字体属性
        //ganttRenderer.setBaseItemLabelsVisible(true);
        ganttRenderer.setBaseItemLabelFont(FontUtil.getVerdanaPlainFont(18));
        ganttRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        /************************* BaseSeries *********************************************/
        ganttRenderer.setBaseSeriesVisible(true);
        ganttRenderer.setBaseSeriesVisibleInLegend(true);
        /*****************************************************************************************/
        //ganttRenderer.setIncludeBaseInRange(false);
        //StandardGradientPaintTransformer transformer = new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL);
        //ganttRenderer.setGradientPaintTransformer(transformer);
        /***************************** Width *****************************************************/
        // 设置柱子最大宽度
        //	ganttRenderer.setMaximumBarWidth(0.1); 
        // 设置柱子最小高度
        //	ganttRenderer.setMinimumBarLength(0.2);
        // 设置每个地区所包含的平行柱的之间距离,组内柱子间隔为组宽的10%
        //ganttRenderer.setItemMargin(0);
        // 设置柱子边框可见
        ganttRenderer.setDrawBarOutline(false);
        ganttRenderer.setCompletePaint(Color.green);
        ganttRenderer.setIncompletePaint(Color.red);
        //		ganttRenderer.setEndPercent(percent);
        //		ganttRenderer.setStartPercent(percent);
        /***************************** Series *************************************************************/
        //某组 是否在 legend里面显示
        //barRenderer.setSeriesVisibleInLegend(1, true);
        // 设置柱的颜色
        // 渐变颜色
        //		GradientPaint gradientPaint = new GradientPaint(0.0f, 8.0f, Color.LIGHT_GRAY, 1.0f, 1.0f, new Color(0, 0, 64));
        //		GradientPaint gradientPaint1 = new GradientPaint(1.0f, 9.0f, Color.GREEN, 1.0f, 1.0f, new Color(0, 64, 0));
        //		ganttRenderer.setSeriesPaint(0, gradientPaint);
        //		ganttRenderer.setSeriesPaint(1, gradientPaint1);
        //barRenderer.setSeriesItemLabelFont(1, new Font("黑体", Font.BOLD, 12));// 12号黑体加粗
        //barRenderer.setSeriesItemLabelsVisible(0, true);
        //barRenderer.setSeriesItemLabelPaint(2, Color.RED);// 字体为黑色
        //barRenderer.setShadowPaint(Color.red);
        // barRenderer.setSeriesOutlinePaint(0, Color.BLACK);// 边框为黑色
        // barRenderer.setSeriesOutlinePaint(1, Color.BLUE);// 边框为黑色
        // barRenderer.setSeriesOutlinePaint(2, Color.RED);// 边框为黑色
    }

    /**
     * 设置Y轴公共属性.
     */
    @Override
    protected void setDefaultValueAxisAttributes(){
        // 数据轴精度
        dateAxis = (DateAxis) getCategoryPlot().getRangeAxis();
        dateAxis.setVisible(true);
        /********************* AxisLine *****************************************************/
        // 坐标轴线条是否可见（3D轴无效）
        dateAxis.setAxisLineVisible(false);
        /********************* TickLabels *****************************************************/
        // 坐标轴标尺值是否显示
        dateAxis.setTickLabelsVisible(true);
        // 设置纵坐标上显示的数字字体
        dateAxis.setTickLabelFont(FontUtil.getYaHeiPlainFont(16));
        /********************** TickMarks ****************************************************************/
        // 坐标轴标尺显示
        dateAxis.setTickMarksVisible(false);
        /********************** Label ****************************************************************/
        dateAxis.setLabelFont(FontUtil.getYaHeiPlainFont(20));
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.DAY, 1);
        dateAxis.setTickUnit(unit);
        //dateAxis.setLabelAngle(0);
        /********************** Margin ****************************************************************/
        // 设置最高的一个 Item 与图片顶端的距离
        //dateAxis.setUpperMargin(0.10);
        // 设置最低的一个 Item 与图片底端的距离
        //dateAxis.setLowerMargin(0.05);
        /********************** NumberFormat ***********************************************/
        // 设置纵轴精度
        //DateFormat dateFormat = new SimpleDateFormat(DatePattern.commonWithoutAndYearSecond);
        //dateAxis.setDateFormatOverride(dateFormat);
    }

    /**
     * Creates the j free chart.
     * 
     * @param ganttChartEntity
     *            the gantt chart entity
     * @return the j free chart
     */
    public static JFreeChart createJFreeChart(GanttChartEntity ganttChartEntity){
        Map<String, List<Task>> seriesAndDataMap = ganttChartEntity.getSeriesAndDataMap();
        TaskSeriesCollection taskSeriesCollection = ChartDatesetUtil.getTaskSeriesCollection(seriesAndDataMap);
        String title = ganttChartEntity.getChartTitle();
        String categoryAxisLabel = ganttChartEntity.getCategoryAxisLabel();
        String dateAxisLabel = ganttChartEntity.getDateAxisLabel();
        IntervalCategoryDataset dataset = taskSeriesCollection;
        boolean legend = ganttChartEntity.isShowLegend();
        boolean tooltips = ganttChartEntity.isTooltips();
        boolean urls = ganttChartEntity.isUrls();
        JFreeChart jFreeChart = ChartFactory.createGanttChart(title, categoryAxisLabel, dateAxisLabel, dataset, legend, tooltips, urls);
        return jFreeChart;
    }

    /**
     * Gets the date axis.
     * 
     * @return the dateAxis
     */
    public DateAxis getDateAxis(){
        return dateAxis;
    }

    /**
     * Gets the gantt renderer.
     * 
     * @return the ganttRenderer
     */
    public GanttRenderer getGanttRenderer(){
        return ganttRenderer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.category.CategoryChartUtil#setChildDefaultCategoryAxisAttributes(org.jfree.chart.axis.CategoryAxis)
     */
    @Override
    protected void setChildDefaultCategoryAxisAttributes(CategoryAxis categoryAxis){// x轴设置
        categoryAxis.setVisible(true);
        /*********************** AxisLine 轴线 *************************************************/
        //坐标轴线条是否可见（3D轴无效）
        categoryAxis.setAxisLineVisible(false);
        //坐标轴线条颜色（3D轴无效）
        categoryAxis.setAxisLinePaint(Color.GRAY);
        //categoryAxis.setAxisLineStroke(stroke);
        /************************ CategoryLabel ********************************************************/
        // 横轴标签之间的距离20%
        categoryAxis.setCategoryMargin(0.10);
        // 图表横轴与标签的距离(8像素)
        categoryAxis.setCategoryLabelPositionOffset(2);
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
        categoryAxis.setLabelFont(FontUtil.getYaHeiPlainFont(20));
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
        categoryAxis.setTickLabelFont(FontUtil.getYaHeiPlainFont(15));
        /************************ TickMarks 刻度标记 *****************************************/
        //标记线是否显示
        categoryAxis.setTickMarksVisible(true);
        //标记线颜色
        categoryAxis.setTickMarkPaint(Color.GRAY);
        //标记线向外长度
        categoryAxis.setTickMarkOutsideLength(4);
        //标记线向内长度
        categoryAxis.setTickMarkInsideLength(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.category.CategoryChartUtil#setChildDefaultCategoryPlotAttributes(org.jfree.chart.plot.CategoryPlot)
     */
    @Override
    protected void setChildDefaultCategoryPlotAttributes(CategoryPlot categoryPlot){
        // 设置轴和面板之间的距离 参数1：上距 参数2：左距 参数3：下距 参数4：右距
        double d = 4d;
        RectangleInsets rectangleInsets = new RectangleInsets(d, d, d, 9);
        categoryPlot.setAxisOffset(rectangleInsets);
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
        categoryPlot.setDomainGridlinesVisible(true);
        categoryPlot.setDomainGridlinePosition(CategoryAnchor.END);
        // x虚线色彩
        categoryPlot.setDomainGridlinePaint(Color.gray);
        // 设置纵横坐标的显示位置
        //categoryPlot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT);// 学校显示在下端(柱子竖直)或左侧(柱子水平)
        /**************************** RangeGridlines y轴 数据轴网格 **********************************************/
        //y轴 数据轴网格
        Stroke stroke = new BasicStroke(0.3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        categoryPlot.setRangeGridlineStroke(stroke);
        // y轴 数据轴网格是否可见
        categoryPlot.setRangeGridlinesVisible(true);//false true
        // y虚线色彩
        categoryPlot.setRangeGridlinePaint(Color.gray);
        // 人数显示在下端(柱子水平)或左侧(柱子竖直)
        categoryPlot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.jfreechart.category.CategoryChartUtil#setChildDefaultNumberAxisAttributes(org.jfree.chart.axis.NumberAxis)
     */
    @Override
    protected void setChildDefaultNumberAxisAttributes(NumberAxis numberAxis){
    }
}
