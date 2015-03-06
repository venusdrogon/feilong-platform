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
package com.feilong.tools.jfreechart.demo;

/*基于JFreeChart 1.0.0 Pre2.jar*/
import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.LineRenderer3D;
import org.jfree.chart.renderer.category.StackedAreaRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer3D;
import org.jfree.chart.renderer.category.WaterfallBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.WaferMapDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.TableOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.FontUtil;
import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.tools.jfreechart.ChartInfoEntity;
import com.feilong.tools.jfreechart.ChartUtil;

/**
 * The Class FreeChart.
 */
public class FreeChart{

    /** The Constant log. */
    private static final Logger              log                            = LoggerFactory.getLogger(FreeChart.class);

    /* 定义各类图表所需的Dataset */
    /** The v default category dataset. */
    private DefaultCategoryDataset           vDefaultCategoryDataset        = null;

    /** The v category dataset. */
    private CategoryDataset                  vCategoryDataset               = null;

    /** The v default pie dataset. */
    private DefaultPieDataset                vDefaultPieDataset             = null;

    /** The v pie dataset. */
    private PieDataset                       vPieDataset                    = null;

    /** The v xy dataset. */
    private XYDataset                        vXYDataset                     = null;

    /** The v table xy dataset. */
    private TableXYDataset                   vTableXYDataset                = null;

    /** The v xyz dataset. */
    private XYZDataset                       vXYZDataset                    = null;

    /** The v interval xy dataset. */
    private IntervalXYDataset                vIntervalXYDataset             = null;

    /** The v wind dataset. */
    private WindDataset                      vWindDataset                   = null;

    /** The v wafer map dataset. */
    private WaferMapDataset                  vWaferMapDataset               = null;

    /** The v interval category dataset. */
    private IntervalCategoryDataset          vIntervalCategoryDataset       = null;

    /** The v ohlc dataset. */
    private OHLCDataset                      vOHLCDataset                   = null;

    /** The v box and whisker xy dataset. */
    private BoxAndWhiskerXYDataset           vBoxAndWhiskerXYDataset        = null;

    /* 定义各类图表所需的Dataset辅助 */
    /** The v time series. */
    TimeSeries                               vTimeSeries                    = null;

    /** The v time series collection. */
    TimeSeriesCollection                     vTimeSeriesCollection          = null;

    /* 定义图表 */
    /** The v free chart. */
    private JFreeChart                       vFreeChart                     = null;

    /** The v free chart extend. */
    private JFreeChart                       vFreeChartExtend               = null;

    /* 定义效果 */
    /** The v category plot. */
    private CategoryPlot                     vCategoryPlot                  = null;

    /** The v pie plot. */
    private PiePlot                          vPiePlot                       = null;

    /** The v multiple pie plot. */
    private MultiplePiePlot                  vMultiplePiePlot               = null;

    /** The v pie plot3 d. */
    private PiePlot3D                        vPiePlot3D                     = null;

    /** The v xy plot. */
    private XYPlot                           vXYPlot                        = null;

    /** The v standard pie item label generator. */
    private StandardPieSectionLabelGenerator vStandardPieItemLabelGenerator = null;

    /** The v date axis. */
    private DateAxis                         vDateAxis                      = null;

    /** The v bar renderer. */
    private BarRenderer                      vBarRenderer                   = null;

    /** The v bar renderer3 d. */
    private BarRenderer3D                    vBarRenderer3D                 = null;

    /** The v stacked bar renderer. */
    private StackedBarRenderer               vStackedBarRenderer            = null;

    /** The v stacked bar renderer3 d. */
    private StackedBarRenderer3D             vStackedBarRenderer3D          = null;

    /** The v stacked area renderer. */
    private StackedAreaRenderer              vStackedAreaRenderer           = null;

    /** The v line and shape renderer. */
    private LineAndShapeRenderer             vLineAndShapeRenderer          = null;

    /** The v line renderer3 d. */
    private LineRenderer3D                   vLineRenderer3D                = null;

    /** The v waterfall bar renderer. */
    private WaterfallBarRenderer             vWaterfallBarRenderer          = null;

    /** The v xy item renderer. */
    private XYItemRenderer                   vXYItemRenderer                = null;

    // 是否启用效果模式
    /** The b render. */
    private boolean                          bRender                        = false;

    /* 定义静态数据 */
    /** The str item array. */
    private String[]                         strItemArray                   = null;

    /** The str multiple item array. */
    private String[]                         strMultipleItemArray           = null;

    /** The str category array. */
    private String[]                         strCategoryArray               = null;

    /** The str data array. */
    private String[]                         strDataArray                   = null;

    /** The str multiple data array. */
    private String[]                         strMultipleDataArray           = null;

    /** The i multiple data array. */
    private double[][]                       iMultipleDataArray             = null;

    /**/
    /** The v chart type array. */
    public static String[]                   vChartTypeArray                = {
            "饼图",
            "分离型饼图",
            "三维饼图",
            "复合饼图",
            "三维分离型饼图",
            "簇状柱形图",
            "三维簇状柱形图",
            "堆积柱形图",
            "三维堆积柱形图",
            "面积图",
            "三维面积图",
            "折线图",
            "三维折线图",
            "三维曲面图",
            "三维柱形图",
            "雷达图",
            "散点图",
            "百分比堆积面积图",
            "三维百分比堆积面积图",
            "折线散点图",
            "面积散点图",
            "时间序列图",
            "三维柱形圆锥图",
            "盘高-盘低-收盘图",
            "开盘-盘高-盘低-收盘图",
            "曲面图（俯视框架图）",
            "气泡图",
            "簇状条形图",
            "数据点折线图",
            "无数据点折线散点图",
            "无数据点平滑线散点图"                                                   };

    /** The str time axis. */
    public String                            strTimeAxis                    = "";

    /**
     * Creates the dataset.
     *
     * @param strMultipleItemList
     *            the str multiple item list
     * @param strCategoryList
     *            the str category list
     * @param strMultipleDataList
     *            the str multiple data list
     */
    public void createDataset(String strMultipleItemList,String strCategoryList,String strMultipleDataList){
        strMultipleItemArray = strMultipleItemList.split(",");
        strCategoryArray = strCategoryList.split(",");
        strMultipleDataArray = strMultipleDataList.split("#");
        iMultipleDataArray = new double[strCategoryArray.length][strMultipleItemArray.length];
        java.text.SimpleDateFormat vSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date vItemDate = null;
        Day vDay = null;
        vTimeSeriesCollection = new TimeSeriesCollection();
        vTimeSeriesCollection.setDomainIsPointsInTime(false);
        for (int mIndex = 0; mIndex < strMultipleDataArray.length; mIndex++){
            String[] strElementArray = strMultipleDataArray[mIndex].split(",");
            if (strTimeAxis.equals("Hour")){
                vTimeSeries = new TimeSeries(strCategoryArray[mIndex], Hour.class);
            }else{
                vTimeSeries = new TimeSeries(strCategoryArray[mIndex], Day.class);
            }
            // vTimeSeries.clear();
            for (int nIndex = 0; nIndex < strElementArray.length; nIndex++){
                iMultipleDataArray[mIndex][nIndex] = Double.parseDouble(strElementArray[nIndex]);
                try{
                    // vItemDate=vSimpleDateFormat.parse(vSimpleDateFormatToday.format(vTodayDate)+String.valueOf(nIndex+1));
                    vItemDate = vSimpleDateFormat.parse(strMultipleItemArray[nIndex]);
                    if (strTimeAxis.equals("Hour")){
                        vDay = new Day(vItemDate.getDate(), 1 + vItemDate.getMonth(), 1900 + vItemDate.getYear());
                        vTimeSeries.add(new Hour(vItemDate.getHours(), vDay), Double.parseDouble(strElementArray[nIndex]));
                    }else{
                        vTimeSeries.add(
                                        new Day(vItemDate.getDate(), 1 + vItemDate.getMonth(), 1900 + vItemDate.getYear()),
                                        Double.parseDouble(strElementArray[nIndex]));
                    }
                }catch (Exception e){
                    log.info(e.getMessage());
                }
            }
            vTimeSeriesCollection.addSeries(vTimeSeries);
        }
        try{
            vCategoryDataset = DatasetUtilities.createCategoryDataset(strCategoryArray, strMultipleItemArray, iMultipleDataArray);
            vPieDataset = DatasetUtilities.createPieDatasetForColumn(vCategoryDataset, 0);
            vPieDataset = DatasetUtilities.createPieDatasetForRow(vCategoryDataset, 0);
            // vWaferMapDataset=(WaferMapDataset)vCategoryDataset;
            vTableXYDataset = (TableXYDataset) vTimeSeriesCollection;
            vIntervalXYDataset = vTimeSeriesCollection;
            vXYDataset = vTimeSeriesCollection;
            /*
             * vXYZDataset=(XYZDataset)vTimeSeriesCollection; //vWaferMapDataset=(WaferMapDataset)vTimeSeriesCollection;
             * vWindDataset=(WindDataset)vTimeSeriesCollection; vOHLCDataset=(OHLCDataset)vTimeSeriesCollection;
             * vSignalsDataset=(SignalsDataset)vTimeSeriesCollection; vBoxAndWhiskerXYDataset=(BoxAndWhiskerXYDataset)vTimeSeriesCollection;
             */
        }catch (Exception e){}
    }

    /**
     * Creates the dataset.
     *
     * @param strItemList
     *            the str item list
     * @param strDataList
     *            the str data list
     */
    public void createDataset(String strItemList,String strDataList){
        vDefaultCategoryDataset = new DefaultCategoryDataset();
        vDefaultPieDataset = new DefaultPieDataset();
        strItemArray = strItemList.split(",");
        strDataArray = strDataList.split(",");
        for (int kIndex = 0; kIndex < strItemArray.length; kIndex++){
            vDefaultCategoryDataset.addValue(Double.parseDouble(strDataArray[kIndex]), " ", strItemArray[kIndex]);
            vDefaultPieDataset.setValue(strItemArray[kIndex], Double.parseDouble(strDataArray[kIndex]));
        }
    }

    /**
     * 获得 default category dataset.
     *
     * @return the default category dataset
     */
    public DefaultCategoryDataset getDefaultCategoryDataset(){
        return this.vDefaultCategoryDataset;
    }

    /**
     * 获得 category dataset.
     *
     * @return the category dataset
     */
    public CategoryDataset getCategoryDataset(){
        return this.vCategoryDataset;
    }

    /**
     * 获得 default pie dataset.
     *
     * @return the default pie dataset
     */
    public DefaultPieDataset getDefaultPieDataset(){
        return this.vDefaultPieDataset;
    }

    /**
     * 获得 pie dataset.
     *
     * @return the pie dataset
     */
    public PieDataset getPieDataset(){
        return this.vPieDataset;
    }

    /**
     * 获得 xy dataset.
     *
     * @return the XY dataset
     */
    public XYDataset getXYDataset(){
        return this.vXYDataset;
    }

    /**
     * 获得 table xy dataset.
     *
     * @return the table xy dataset
     */
    public TableXYDataset getTableXYDataset(){
        return this.vTableXYDataset;
    }

    /**
     * 获得 xyz dataset.
     *
     * @return the XYZ dataset
     */
    public XYZDataset getXYZDataset(){
        return this.vXYZDataset;
    }

    /**
     * 获得 interval xy dataset.
     *
     * @return the interval xy dataset
     */
    public IntervalXYDataset getIntervalXYDataset(){
        return this.vIntervalXYDataset;
    }

    /**
     * 获得 wind dataset.
     *
     * @return the wind dataset
     */
    public WindDataset getWindDataset(){
        return this.vWindDataset;
    }

    /**
     * 获得 wafer map dataset.
     *
     * @return the wafer map dataset
     */
    public WaferMapDataset getWaferMapDataset(){
        return this.vWaferMapDataset;
    }

    /**
     * 获得 interval category dataset.
     *
     * @return the interval category dataset
     */
    public IntervalCategoryDataset getIntervalCategoryDataset(){
        return this.vIntervalCategoryDataset;
    }

    /**
     * 获得 ohlc dataset.
     *
     * @return the OHLC dataset
     */
    public OHLCDataset getOHLCDataset(){
        return this.vOHLCDataset;
    }

    /**
     * 获得 box and whisker xy dataset.
     *
     * @return the box and whisker xy dataset
     */
    public BoxAndWhiskerXYDataset getBoxAndWhiskerXYDataset(){
        return this.vBoxAndWhiskerXYDataset;
    }

    /*
     * iChartType:图表类型
     */
    /**
     * Creates the chart.
     *
     * @param iChartType
     *            the i chart type
     * @param strFreeChartInfo
     *            the str free chart info
     * @param strFreeChartXInfo
     *            the str free chart x info
     * @param strFreeChartYInfo
     *            the str free chart y info
     */
    public void createChart(int iChartType,String strFreeChartInfo,String strFreeChartXInfo,String strFreeChartYInfo){
        switch (iChartType) {
            case 1:
                vFreeChart = ChartFactory.createPieChart(strFreeChartInfo, this.getPieDataset(), true, false, false);
                try{
                    vPiePlot = (PiePlot) vFreeChart.getPlot();
                    if (vPiePlot != null){
                        if (bRender){
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    // 指定Section色彩
                                    vPiePlot.setSectionPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    // 指定Section轮廓线颜色
                                    vPiePlot.setSectionOutlinePaint(0, Color.BLACK);
                                }
                            }
                        }
                        // 指定Section标签格式
                        vStandardPieItemLabelGenerator = new StandardPieSectionLabelGenerator("{1}");
                        vPiePlot.setLabelGenerator(vStandardPieItemLabelGenerator);
                        //
                        vPiePlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 2:
                vFreeChart = ChartFactory.createPieChart(
                                strFreeChartInfo,
                                this.getPieDataset(),
                                this.getPieDataset(),
                                0,
                                false,
                                true,
                                false,
                                false,
                                false,
                                false);
                try{
                    vPiePlot = (PiePlot) vFreeChart.getPlot();
                    if (vPiePlot != null){
                        if (bRender){
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    // 指定Section色彩
                                    vPiePlot.setSectionPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    // 指定Section轮廓线颜色
                                    vPiePlot.setSectionOutlinePaint(0, Color.BLACK);
                                }
                                // 抽取指定块
                                vPiePlot.setExplodePercent(0, 1.00);
                            }
                        }
                        // 指定Section标签格式
                        vStandardPieItemLabelGenerator = new StandardPieSectionLabelGenerator("{1}");
                        vPiePlot.setLabelGenerator(vStandardPieItemLabelGenerator);
                        vPiePlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 3:
                vFreeChart = ChartFactory.createMultiplePieChart(
                                strFreeChartInfo,
                                this.getCategoryDataset(),
                                TableOrder.BY_ROW,
                                true,
                                false,
                                false);
                try{
                    vMultiplePiePlot = (MultiplePiePlot) vFreeChart.getPlot();
                    if (vMultiplePiePlot != null){
                        vFreeChartExtend = vMultiplePiePlot.getPieChart();
                        vPiePlot = (PiePlot) vFreeChartExtend.getPlot();
                        if (bRender){
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    // 指定Section色彩
                                    vPiePlot.setSectionPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    // 指定Section轮廓线颜色
                                    vPiePlot.setSectionOutlinePaint(0, Color.BLACK);
                                }
                            }
                        }
                        // 指定Section标签格式
                        vStandardPieItemLabelGenerator = new StandardPieSectionLabelGenerator("{1}");
                        vPiePlot.setLabelGenerator(vStandardPieItemLabelGenerator);
                        vPiePlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 4:
                vFreeChart = ChartFactory.createPieChart3D(strFreeChartInfo, this.getPieDataset(), true, false, false);
                try{
                    vPiePlot3D = (PiePlot3D) vFreeChart.getPlot();
                    if (vPiePlot != null){
                        if (bRender){
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    // 指定Section色彩
                                    vPiePlot3D.setSectionPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    // 指定Section轮廓线颜色
                                    vPiePlot3D.setSectionOutlinePaint(0, Color.BLACK);
                                }
                            }
                        }
                        // 指定Section标签格式
                        vStandardPieItemLabelGenerator = new StandardPieSectionLabelGenerator("{1}");
                        vPiePlot3D.setLabelGenerator(vStandardPieItemLabelGenerator);
                        //
                        vPiePlot3D.setForegroundAlpha(0.8f);
                    }
                }catch (Exception e){}
                break;
            case 5:
                vFreeChart = ChartFactory.createMultiplePieChart3D(
                                strFreeChartInfo,
                                this.getCategoryDataset(),
                                TableOrder.BY_ROW,
                                true,
                                false,
                                false);
                try{
                    vMultiplePiePlot = (MultiplePiePlot) vFreeChart.getPlot();
                    if (vMultiplePiePlot != null){
                        vFreeChartExtend = vMultiplePiePlot.getPieChart();
                        vPiePlot3D = (PiePlot3D) vFreeChartExtend.getPlot();
                        if (bRender){
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    // 指定Section色彩
                                    vPiePlot3D.setSectionPaint(iIndex, new Color(0, 0 + iIndex * (255 / strCategoryArray.length), 255));
                                    // 指定Section轮廓线颜色
                                    vPiePlot3D.setSectionOutlinePaint(0, Color.BLACK);
                                }
                            }
                        }
                        // 指定Section标签格式
                        vStandardPieItemLabelGenerator = new StandardPieSectionLabelGenerator("{1}");
                        vPiePlot3D.setLabelGenerator(vStandardPieItemLabelGenerator);
                        //
                        vPiePlot3D.setForegroundAlpha(0.8f);
                    }
                }catch (Exception e){}
                break;
            case 6:
                vFreeChart = ChartFactory.createBarChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vBarRenderer = new BarRenderer();
                            vBarRenderer.setBaseOutlinePaint(Color.GRAY);
                            // 设置X轴代表的柱的颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vBarRenderer.setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strCategoryArray.length), 255));
                                    vBarRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vBarRenderer.setItemMargin(0.1);
                            vCategoryPlot.setRenderer(vBarRenderer);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置柱的透明度
                        vCategoryPlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 7:
                vFreeChart = ChartFactory.createStackedBarChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vStackedBarRenderer = new StackedBarRenderer();
                            vStackedBarRenderer.setBaseOutlinePaint(Color.GRAY);
                            // 设置X轴代表的柱的颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vStackedBarRenderer.setSeriesPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vStackedBarRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vCategoryPlot.setRenderer(vStackedBarRenderer);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置柱的透明度
                        vCategoryPlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 8:
                vFreeChart = ChartFactory.createBarChart3D(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vBarRenderer3D = new BarRenderer3D();
                            vBarRenderer3D.setBaseOutlinePaint(Color.GRAY);
                            // 设置X轴代表的柱的颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vBarRenderer3D.setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strCategoryArray.length), 255));
                                    vBarRenderer3D.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vBarRenderer3D.setItemMargin(0.1);
                            vCategoryPlot.setRenderer(vBarRenderer3D);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置柱的透明度
                        vCategoryPlot.setForegroundAlpha(0.8f);
                    }
                }catch (Exception e){}
                break;
            case 9:
                vFreeChart = ChartFactory.createStackedBarChart3D(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vStackedBarRenderer3D = new StackedBarRenderer3D();
                            vStackedBarRenderer3D.setBaseOutlinePaint(Color.GRAY);
                            // 设置X轴代表的柱的颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vStackedBarRenderer3D.setSeriesPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vStackedBarRenderer3D.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vCategoryPlot.setRenderer(vStackedBarRenderer3D);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置柱的透明度
                        vCategoryPlot.setForegroundAlpha(0.8f);
                    }
                }catch (Exception e){}
                break;
            case 10:
                vFreeChart = ChartFactory.createAreaChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getDefaultCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vStackedAreaRenderer = new StackedAreaRenderer();
                            vStackedAreaRenderer.setBaseOutlinePaint(Color.GRAY);
                            // 设置区域颜色
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    vStackedAreaRenderer
                                                    .setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    vStackedAreaRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vCategoryPlot.setRenderer(vStackedAreaRenderer);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置区域透明度
                        vCategoryPlot.setForegroundAlpha(0.5f);
                        // 背景表格线
                        vCategoryPlot.setDomainGridlinesVisible(true);
                    }
                }catch (Exception e){}
                break;
            case 11:
                vFreeChart = ChartFactory.createStackedAreaChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        if (bRender){
                            vStackedAreaRenderer = new StackedAreaRenderer();
                            vStackedAreaRenderer.setBaseOutlinePaint(Color.GRAY);
                            // 设置区域颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vStackedAreaRenderer.setSeriesPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vStackedAreaRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            vCategoryPlot.setRenderer(vStackedAreaRenderer);
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置区域透明度
                        vCategoryPlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 12:
                vFreeChart = ChartFactory.createLineChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        vLineAndShapeRenderer = new LineAndShapeRenderer();
                        vLineAndShapeRenderer.setBaseOutlinePaint(Color.GRAY);
                        if (bRender){
                            // 设置线条颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vLineAndShapeRenderer.setSeriesPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vLineAndShapeRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        vCategoryPlot.setRenderer(vLineAndShapeRenderer);
                        // 背景表格线
                        vCategoryPlot.setDomainGridlinesVisible(true);
                    }
                }catch (Exception e){}
                break;
            case 13:
                vFreeChart = ChartFactory.createLineChart3D(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        vLineRenderer3D = new LineRenderer3D();
                        vLineRenderer3D.setBaseOutlinePaint(Color.GRAY);
                        if (bRender){
                            // 设置线面颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vLineRenderer3D.setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strCategoryArray.length), 255));
                                    vLineRenderer3D.setSeriesFillPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vLineRenderer3D.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        vCategoryPlot.setRenderer(vLineRenderer3D);
                        // 背景表格线
                        vCategoryPlot.setDomainGridlinesVisible(true);
                    }
                }catch (Exception e){}
                break;
            case 14:
                vFreeChart = ChartFactory.createGanttChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getIntervalCategoryDataset(),
                                true,
                                false,
                                false);
                break;
            case 15:
                vFreeChart = ChartFactory.createWaterfallChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getCategoryDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vCategoryPlot = vFreeChart.getCategoryPlot();
                    if (vCategoryPlot != null){
                        vWaterfallBarRenderer = new WaterfallBarRenderer();
                        vWaterfallBarRenderer.setBaseOutlinePaint(Color.GRAY);
                        if (bRender){
                            // 设置柱颜色
                            if (strCategoryArray.length > 0){
                                for (int iIndex = 0; iIndex < strCategoryArray.length; iIndex++){
                                    vWaterfallBarRenderer.setSeriesPaint(iIndex, new Color(
                                                    0,
                                                    0 + iIndex * (255 / strCategoryArray.length),
                                                    255));
                                    vWaterfallBarRenderer.setSeriesFillPaint(iIndex, new Color(0, 0 + iIndex
                                                    * (255 / strCategoryArray.length), 255));
                                    vWaterfallBarRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            // 设置X轴、Y轴的显示位置
                            vCategoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vCategoryPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        vCategoryPlot.setRenderer(vWaterfallBarRenderer);
                        // 背景表格线
                        vCategoryPlot.setDomainGridlinesVisible(true);
                        // 设置区域透明度
                        vCategoryPlot.setForegroundAlpha(0.5f);
                    }
                }catch (Exception e){}
                break;
            case 16:
                vFreeChart = ChartFactory.createPolarChart(strFreeChartInfo, this.getXYDataset(), true, false, false);
                break;
            case 17:
                vFreeChart = ChartFactory.createScatterPlot(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 18:
                vFreeChart = ChartFactory.createXYBarChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                false,
                                strFreeChartYInfo,
                                this.getIntervalXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 19:
                vFreeChart = ChartFactory.createXYAreaChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 20:
                vFreeChart = ChartFactory.createStackedXYAreaChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getTableXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 21:
                vFreeChart = ChartFactory.createXYLineChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                try{
                    vXYPlot = vFreeChart.getXYPlot();
                    if (vXYPlot != null){
                        vXYItemRenderer = vXYPlot.getRenderer();
                        vXYItemRenderer.setBaseOutlinePaint(Color.GRAY);
                        if (bRender){
                            // 设置线面颜色
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    vXYItemRenderer.setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    vXYItemRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            // 设置X轴、Y轴的显示位置
                            vXYPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vXYPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                        }
                        // 设置线条颜色
                        vXYItemRenderer.setPaint(new Color(0, 100, 255));
                        vXYPlot.setRenderer(vXYItemRenderer);
                        // 背景表格线
                        vXYPlot.setDomainGridlinesVisible(true);
                    }
                }catch (Exception e){}
                break;
            case 22:
                vFreeChart = ChartFactory.createXYStepChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 23:
                vFreeChart = ChartFactory.createXYStepAreaChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 24:
                vFreeChart = ChartFactory.createTimeSeriesChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYDataset(),
                                true,
                                false,
                                false);
                try{
                    vXYPlot = vFreeChart.getXYPlot();
                    if (vXYPlot != null){
                        vXYItemRenderer = vXYPlot.getRenderer();
                        vXYItemRenderer.setBaseOutlinePaint(Color.GRAY);
                        if (bRender){
                            // 设置线面颜色
                            if (strItemArray.length > 0){
                                for (int iIndex = 0; iIndex < strItemArray.length; iIndex++){
                                    vXYItemRenderer.setSeriesPaint(iIndex, new Color(0, 0 + iIndex * (255 / strItemArray.length), 255));
                                    vXYItemRenderer.setSeriesOutlinePaint(0, Color.BLACK);
                                }
                            }
                            // 设置X轴、Y轴的显示位置
                            vXYPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
                            vXYPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
                            // 设置线条颜色
                            vXYItemRenderer.setPaint(new Color(0, 100, 255));
                            vXYPlot.setRenderer(vXYItemRenderer);
                        }
                        // 背景表格线
                        vXYPlot.setDomainGridlinesVisible(true);
                        SimpleDateFormat vSimpleDateFormat = null;
                        if (strTimeAxis.equals("Hour")){
                            vSimpleDateFormat = new SimpleDateFormat("dd日HH点");
                        }else{
                            vSimpleDateFormat = new SimpleDateFormat("MM月dd日");
                        }
                        vDateAxis = (DateAxis) vXYPlot.getDomainAxis();
                        vDateAxis.setDateFormatOverride(vSimpleDateFormat);
                    }
                }catch (Exception e){}
                break;
            case 25:
                vFreeChart = ChartFactory.createCandlestickChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getOHLCDataset(),
                                true);
                break;
            case 26:
                vFreeChart = ChartFactory.createHighLowChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getOHLCDataset(),
                                true);
                break;
            case 27:
                vFreeChart = ChartFactory.createHighLowChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getOHLCDataset(),
                                true);
                break;
            case 28:
                // vFreeChart = ChartFactory.createSignalChart(strFreeChartInfo, strFreeChartXInfo, strFreeChartYInfo,
                // this.getSignalsDataset(), true);
                break;
            case 29:
                vFreeChart = ChartFactory.createBubbleChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getXYZDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 30:
                vFreeChart = ChartFactory.createHistogram(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getIntervalXYDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            case 31:
                vFreeChart = ChartFactory.createBoxAndWhiskerChart(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getBoxAndWhiskerXYDataset(),
                                true);
                break;
            case 32:
                vFreeChart = ChartFactory.createWindPlot(
                                strFreeChartInfo,
                                strFreeChartXInfo,
                                strFreeChartYInfo,
                                this.getWindDataset(),
                                true,
                                false,
                                false);
                break;
            case 33:
                vFreeChart = ChartFactory.createWaferMapChart(
                                strFreeChartInfo,
                                this.getWaferMapDataset(),
                                PlotOrientation.VERTICAL,
                                true,
                                false,
                                false);
                break;
            default:
                vFreeChart = ChartFactory.createPieChart(strFreeChartInfo, this.getPieDataset(), true, false, false);
                break;
        }
        // 图表背景颜色
        vFreeChart.setBackgroundPaint(new Color(212, 234, 243));
    }

    /**
     * 获得 chart.
     *
     * @return the chart
     */
    public JFreeChart getChart(){
        return this.vFreeChart;
    }

    /**
     * 获得 application name.
     *
     * @param strRealPath
     *            the str real path
     * @return the application name
     */
    public String getApplicationName(String strRealPath){
        String[] strAppArray = strRealPath.split("\\\\");
        return strAppArray[strAppArray.length - 1];
    }

    /**
     * 设置 render.
     *
     * @param bRender
     *            the render
     */
    public void setRender(boolean bRender){
        this.bRender = bRender;
    }

    /**
     * 设置 time axis.
     *
     * @param strTimeAxis
     *            the time axis
     */
    public void setTimeAxis(String strTimeAxis){
        this.strTimeAxis = strTimeAxis;
    }

    /**
     * The main method.
     *
     * @param args
     *            the args
     * @throws IOException
     *             the IO exception
     */
    public static void main(String[] args) throws IOException{
        for (int i = 0; i < FreeChart.vChartTypeArray.length; i++){
            a(i + 1);
        }
    }

    /**
     * A.
     *
     * @param chartType
     *            the chart type
     * @throws IOException
     *             the IO exception
     */
    private static void a(int chartType) throws IOException{
        FreeChart freeChart = new FreeChart();
        // 初始化
        // 考虑图形中是否直接链接到统计查询表格
        String strTimeList = "2005-05-01 00:00:00,2005-05-02 05:00:00,2005-05-03 10:00:00,2005-05-04 15:00:00,2005-05-05 20:00:00";
        String strMultipleTimeList = "2005-05-01 00:00:00,2005-05-02 05:00:00,2005-05-03 10:00:00,2005-05-04 15:00:00,2005-05-05 20:00:00";
        String strCategoryList = "JSP工作量,Servlet工作量,Bean工作量";
        String strData = "1,2,3,4,5";
        String strMultipleData = "1,2,3,4,5#2,3,4,5,6#3,4,5,6,7";
        freeChart.createDataset(strMultipleTimeList, strCategoryList, strMultipleData);
        freeChart.createDataset(strTimeList, strData);
        freeChart.setRender(true);
        freeChart.setTimeAxis("Hour");
        freeChart.createChart(chartType, "统计图", "时间轴", "统计值轴");
        ChartInfoEntity chartInfoEntity = new ChartInfoEntity();
        chartInfoEntity.setImageNameOrOutputStream(DateUtil.date2String(new Date(), DatePattern.TIMESTAMP_WITH_MILLISECOND) + ".jpg");
        chartInfoEntity.setWidth(800);
        chartInfoEntity.setHeight(400);
        freeChart.getChart().getTitle().setFont(FontUtil.SONG_PLAIN_12);
        freeChart.getChart().getLegend().setItemFont(FontUtil.SONG_PLAIN_12);
        ChartUtil.createImage(freeChart.getChart(), chartInfoEntity);
    }
}
