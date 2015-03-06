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
package com.feilong.tools.amcharts;

/**
 * The Class ChartConfig.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月5日 上午11:30:50
 * @since 1.0.8
 */
public class ChartConfig{

    /** The type. */
    private String    type;

    private String    titleField;

    private String    categoryField;

    /**
     * Creates the visualization of the data in following types: line, column, step line, smoothed line, olhc and candlestick.
     */
    private AmGraph[] graphs;

    /** 不透明图表的边框。取值范围为0 - 1。. */
    private Number    borderAlpha;

    /**
     * Color of chart's border. </br>You should set borderAlpha >0 in order border to be visible. </br>
     * We recommend setting border color directly on a chart's DIV instead of using this property.
     */
    private String    borderColor;

    /**
     * Font family. "Segoe Print"
     */
    private String    fontFamily;

    // chart.fontSize = "4";//Font size

    private Number    startDuration;

    private Number    plotAreaFillAlphas;

    /** The legend. */
    private AmLegend  legend;

    private Title[]   titles;

    private Object    dataProvider;

    /**
     * 获得 type.
     *
     * @return the type
     */
    public String getType(){
        return type;
    }

    /**
     * 设置 type.
     *
     * @param type
     *            the type to set
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * 获得 creates the visualization of the data in following types: line, column, step line, smoothed line, olhc and candlestick.
     *
     * @return the graphs
     */
    public AmGraph[] getGraphs(){
        return graphs;
    }

    /**
     * 设置 creates the visualization of the data in following types: line, column, step line, smoothed line, olhc and candlestick.
     *
     * @param graphs
     *            the graphs to set
     */
    public void setGraphs(AmGraph[] graphs){
        this.graphs = graphs;
    }

    /**
     * 获得 不透明图表的边框。取值范围为0 - 1。.
     *
     * @return the borderAlpha
     */
    public Number getBorderAlpha(){
        return borderAlpha;
    }

    /**
     * 设置 不透明图表的边框。取值范围为0 - 1。.
     *
     * @param borderAlpha
     *            the borderAlpha to set
     */
    public void setBorderAlpha(Number borderAlpha){
        this.borderAlpha = borderAlpha;
    }

    /**
     * 获得 color of chart's border.
     *
     * @return the borderColor
     */
    public String getBorderColor(){
        return borderColor;
    }

    /**
     * 设置 color of chart's border.
     *
     * @param borderColor
     *            the borderColor to set
     */
    public void setBorderColor(String borderColor){
        this.borderColor = borderColor;
    }

    /**
     * 获得 legend.
     *
     * @return the legend
     */
    public AmLegend getLegend(){
        return legend;
    }

    /**
     * 设置 legend.
     *
     * @param legend
     *            the legend to set
     */
    public void setLegend(AmLegend legend){
        this.legend = legend;
    }

    /**
     * @return the dataProvider
     */
    public Object getDataProvider(){
        return dataProvider;
    }

    /**
     * @param dataProvider
     *            the dataProvider to set
     */
    public void setDataProvider(Object dataProvider){
        this.dataProvider = dataProvider;
    }

    /**
     * @return the titles
     */
    public Title[] getTitles(){
        return titles;
    }

    /**
     * @param titles
     *            the titles to set
     */
    public void setTitles(Title[] titles){
        this.titles = titles;
    }

    /**
     * @return the fontFamily
     */
    public String getFontFamily(){
        return fontFamily;
    }

    /**
     * @param fontFamily
     *            the fontFamily to set
     */
    public void setFontFamily(String fontFamily){
        this.fontFamily = fontFamily;
    }

    /**
     * @return the startDuration
     */
    public Number getStartDuration(){
        return startDuration;
    }

    /**
     * @param startDuration
     *            the startDuration to set
     */
    public void setStartDuration(Number startDuration){
        this.startDuration = startDuration;
    }

    /**
     * @return the plotAreaFillAlphas
     */
    public Number getPlotAreaFillAlphas(){
        return plotAreaFillAlphas;
    }

    /**
     * @param plotAreaFillAlphas
     *            the plotAreaFillAlphas to set
     */
    public void setPlotAreaFillAlphas(Number plotAreaFillAlphas){
        this.plotAreaFillAlphas = plotAreaFillAlphas;
    }

    /**
     * @return the titleField
     */
    public String getTitleField(){
        return titleField;
    }

    /**
     * @param titleField
     *            the titleField to set
     */
    public void setTitleField(String titleField){
        this.titleField = titleField;
    }

    /**
     * @return the categoryField
     */
    public String getCategoryField(){
        return categoryField;
    }

    /**
     * @param categoryField
     *            the categoryField to set
     */
    public void setCategoryField(String categoryField){
        this.categoryField = categoryField;
    }

}
