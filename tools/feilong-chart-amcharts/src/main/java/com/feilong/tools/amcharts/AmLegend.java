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

import java.io.Serializable;

/**
 * Creates the legend for the chart, automatically adapts the color settings of the graphs.
 * 
 * <pre>
 * 
 * Example 1:
 * {@code
 * var chart = AmCharts.makeChart("chartdiv",{
 * 	...
 * 	"legend": {
 * 		"useGraphSettings": true
 * 	},
 * });
 * }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月24日 下午4:24:59
 * @since 1.0.8
 */
public class AmLegend implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID        = 288232184048495608L;

    /**
     * Alignment of legend entries. <br>
     * Possible values are: "left", "center", "right".
     */
    private String            align                   = "left";

    /**
     * Used if chart is Serial or XY.<br>
     * In case true, margins of the legend are adjusted and made equal to chart's margins.
     * 
     */
    private Boolean           autoMargins             = true;

    /**
     * Opacity of legend's background.<br>
     * Value range is 0 - 1
     */
    private Number            backgroundAlpha         = 0;

    /**
     * Background color.<br>
     * You should set backgroundAlpha to >0 vallue in order background to be visible.
     */
    private String            backgroundColor         = "#FFFFFF";

    /**
     * Opacity of chart's border.<br>
     * Value range is 0 - 1.
     */
    private Number            borderAlpha             = 0;

    /**
     * Color of legend's border. <br>
     * You should set borderAlpha >0 in order border to be visible.
     * 
     */
    private String            borderColor             = "#000000";

    /**
     * In case legend position is set to "absolute", you can set distance from bottom of the chart, in pixels.
     */

    private Number            bottom;

    /**
     * Text color.
     */
    private String            color                   = "#000000";

    //	/**
    //	 * You can pass array of objects with title, color, markerType values, <br>
    //	 * for example: [{title: "One", color: "#3366CC"},{title: "Two", color: "#FFCC33"}]
    //	 */
    //private	data	Array[Object]		
    /**
     * You can set id of a div or a reference to div object in case you want the legend to be placed in a separate container.
     * 
     */
    private String            divId;

    /**
     * Specifies if each of legend entry should be equal to the most wide entry.<br>
     * Won't look good if legend has more than one line.
     */
    private Boolean           equalWidths             = true;

    /**
     * Font size.
     */
    private Number            fontSize                = 11;

    /**
     * Horizontal space between legend item and left/right border.
     */
    private Number            horizontalGap           = 0;

    /**
     * The text which will be displayed in the legend.<br>
     * Tag [[title]] will be replaced with the title of the graph.
     * 
     */
    //	[[title]]
    private String            labelText;

    /**
     * If width of the label is bigger than labelWidth, it will be wrapped.;
     * 
     */
    private Number            labelWidth;

    /**
     * In case legend position is set to "absolute", you can set distance from left side of the chart, in pixels.
     */
    private Number            left;

    /**
     * Bottom margin.
     * 
     */
    private Number            marginBottom            = 0;

    /**
     * Left margin. <br>
     * This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
     * 
     */
    private Number            marginLeft              = 20;

    /**
     * Right margin. This property will be ignored if chart is Serial or XY and autoMargins property of the legend is true (default).
     */
    private Number            marginRight             = 20;

    /**
     * Top margin.
     * 
     */
    private Number            marginTop               = 0;

    /**
     * Marker border opacity.
     */
    private Number            markerBorderAlpha       = 1;

    /**
     * Marker border color.<br>
     * If not set, will use the same color as marker.
     */
    private String            markerBorderColor;

    /**
     * Thickness of the legend border.<br>
     * The default value (0) means the line will be a "hairline" (1 px). <br>
     * In case marker type is line, this style will be used for line thickness.
     */
    private Number            markerBorderThickness   = 1;

    /**
     * The color of the disabled marker (when the graph is hidden).
     * 
     */
    private String            markerDisabledColor     = "#AAB3B3";

    /**
     * Space between legend marker and legend text, in pixels.
     */
    private Number            markerLabelGap          = 5;

    /**
     * Size of the legend marker (key).
     */
    private Number            markerSize              = 16;

    /**
     * Shape of the legend marker (key).<br>
     * Possible values are: square, circle, diamond, triangleUp, triangleDown, triangleLeft, triangleDown, bubble, line, none.
     * 
     */
    private String            markerType              = "square";

    /**
     * Maximum number of columns in the legend. <br>
     * If Legend's position is set to "right" or "left", maxColumns is automatically set to 1.
     */
    private Number            maxColumns;

    /**
     * The text which will be displayed in the value portion of the legend when user is not hovering above any data point.<br>
     * The tags should be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you
     * want to be show -
     * open / close / high / low / sum / average / count.<br>
     * For example: [[value.sum]] means that sum of all data points of value field in the
     * selected period will be displayed.
     */
    private String            periodValueText;

    /**
     * Position of a legend.<br>
     * Possible values are: "bottom", "top", "left", "right" and "absolute". In case "absolute", you should set left
     * and top properties too. (this setting is ignored in Stock charts). In case legend is used with AmMap, position is set to "absolute"
     * automatically.
     */
    private String            position                = "bottom";

    /**
     * Specifies whether legend entries should be placed in reversed order.;
     */
    private Boolean           reversedOrder           = false;

    /**
     * In case legend position is set to "absolute", you can set distance from right side of the chart, in pixels.
     */
    private Number            right;

    /**
     * Legend item text color on roll-over.
     * 
     */
    private String            rollOverColor           = "#CC0000";

    /**
     * When you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be
     * distinguished.<br>
     * This style specifies the opacity of the graphs.
     */
    private Number            rollOverGraphAlpha      = 1;

    /**
     * You can use this property to turn all the legend entries off.
     */
    private Boolean           showEntries             = true;

    /**
     * Horizontal space between legend items, in pixels.
     */
    private Number            spacing                 = 10;

    /**
     * Whether showing/hiding of graphs by clicking on the legend marker is enabled or not.<br>
     * In case legend is used with AmMap, this is set to false automatically.;
     */
    private Boolean           switchable              = true;

    /**
     * Legend switch color.
     * 
     */
    private String            switchColor             = "#FFFFFF";

    /**
     * Legend switch type (in case the legend is switchable).<br>
     * Possible values are "x" and "v".
     */
    private String            switchType              = "x";

    /**
     * If true, clicking on the text will show/hide balloon of the graph.<br>
     * Otherwise it will show/hide graph/slice, if switchable is set to
     * true.
     */
    private Boolean           textClickEnabled        = false;

    /**
     * In case legend position is set to "absolute", you can set distance from top of the chart, in pixels.
     * 
     */
    private Number            top;

    /**
     * Legend markers can mirror graph’s settings, displaying a line and a real bullet as in the graph itself.<br>
     * Set this property to true if
     * you want to enable this feature.;
     */
    private Boolean           useGraphSettings        = false;

    /**
     * Labels will use marker color if you set this to true.;
     */
    private Boolean           useMarkerColorForLabels = false;

    /**
     * Specifies if legend values should be use same color as corresponding markers.;
     */
    private Boolean           useMarkerColorForValues = false;

    /**
     * Alignment of the value text.<br>
     * Possible values are "left" and "right".
     */
    private String            valueAlign              = "right";

    /**
     * You can use it to format value labels in any way you want. <br>
     * Legend will call this method and will pass GraphDataItem and formatted
     * text of currently hovered item (works only with ChartCursor added to the chart). This method should return string which will be
     * displayed as value in the legend.
     * 
     */
    //private	valueFunction			
    /**
     * The text which will be displayed in the value portion of the legend. <br>
     * You can use tags like [[value]], [[open]], [[high]], [[low]],
     * [[close]], [[percents]], [[description]].
     */
    private String            valueText               = "[[value]]";

    /**
     * Width of the value text.
     */
    private Number            valueWidth              = 50;

    /**
     * Vertical space between legend items also between legend border and first and last legend row.
     * 
     */
    private Number            verticalGap             = 10;

    /**
     * Width of a legend, when position is set to absolute.
     */
    private Number            width;

    /**
     * 获得 alignment of legend entries.
     *
     * @return the align
     */
    public String getAlign(){
        return align;
    }

    /**
     * 设置 alignment of legend entries.
     *
     * @param align
     *            the align to set
     */
    public void setAlign(String align){
        this.align = align;
    }

    //
    //	/**
    //	 * 获得 used if chart is Serial or XY.
    //	 *
    //	 * @return the autoMargins
    //	 */
    //	public Boolean getAutoMargins(){
    //		return autoMargins;
    //	}
    //
    //	/**
    //	 * 设置 used if chart is Serial or XY.
    //	 *
    //	 * @param autoMargins
    //	 *            the autoMargins to set
    //	 */
    //	public void setAutoMargins(Boolean autoMargins){
    //		this.autoMargins = autoMargins;
    //	}
    //
    //	/**
    //	 * 获得 opacity of legend's background.
    //	 *
    //	 * @return the backgroundAlpha
    //	 */
    //	public Number getBackgroundAlpha(){
    //		return backgroundAlpha;
    //	}
    //
    //	/**
    //	 * 设置 opacity of legend's background.
    //	 *
    //	 * @param backgroundAlpha
    //	 *            the backgroundAlpha to set
    //	 */
    //	public void setBackgroundAlpha(Number backgroundAlpha){
    //		this.backgroundAlpha = backgroundAlpha;
    //	}
    //
    //	/**
    //	 * 获得 background color.
    //	 *
    //	 * @return the backgroundColor
    //	 */
    //	public String getBackgroundColor(){
    //		return backgroundColor;
    //	}
    //
    //	/**
    //	 * 设置 background color.
    //	 *
    //	 * @param backgroundColor
    //	 *            the backgroundColor to set
    //	 */
    //	public void setBackgroundColor(String backgroundColor){
    //		this.backgroundColor = backgroundColor;
    //	}
    //
    //	/**
    //	 * 获得 opacity of chart's border.
    //	 *
    //	 * @return the borderAlpha
    //	 */
    //	public Number getBorderAlpha(){
    //		return borderAlpha;
    //	}
    //
    //	/**
    //	 * 设置 opacity of chart's border.
    //	 *
    //	 * @param borderAlpha
    //	 *            the borderAlpha to set
    //	 */
    //	public void setBorderAlpha(Number borderAlpha){
    //		this.borderAlpha = borderAlpha;
    //	}
    //
    //	/**
    //	 * 获得 color of legend's border.
    //	 *
    //	 * @return the borderColor
    //	 */
    //	public String getBorderColor(){
    //		return borderColor;
    //	}
    //
    //	/**
    //	 * 设置 color of legend's border.
    //	 *
    //	 * @param borderColor
    //	 *            the borderColor to set
    //	 */
    //	public void setBorderColor(String borderColor){
    //		this.borderColor = borderColor;
    //	}
    //
    //	/**
    //	 * 获得 in case legend position is set to "absolute", you can set distance from bottom of the chart, in pixels.
    //	 *
    //	 * @return the bottom
    //	 */
    //	public Number getBottom(){
    //		return bottom;
    //	}
    //
    //	/**
    //	 * 设置 in case legend position is set to "absolute", you can set distance from bottom of the chart, in pixels.
    //	 *
    //	 * @param bottom
    //	 *            the bottom to set
    //	 */
    //	public void setBottom(Number bottom){
    //		this.bottom = bottom;
    //	}
    //
    //	/**
    //	 * 获得 text color.
    //	 *
    //	 * @return the color
    //	 */
    //	public String getColor(){
    //		return color;
    //	}
    //
    //	/**
    //	 * 设置 text color.
    //	 *
    //	 * @param color
    //	 *            the color to set
    //	 */
    //	public void setColor(String color){
    //		this.color = color;
    //	}
    //
    //	/**
    //	 * 获得 you can set id of a div or a reference to div object in case you want the legend to be placed in a separate container.
    //	 *
    //	 * @return the divId
    //	 */
    //	public String getDivId(){
    //		return divId;
    //	}
    //
    //	/**
    //	 * 设置 you can set id of a div or a reference to div object in case you want the legend to be placed in a separate container.
    //	 *
    //	 * @param divId
    //	 *            the divId to set
    //	 */
    //	public void setDivId(String divId){
    //		this.divId = divId;
    //	}
    //
    //	/**
    //	 * 获得 specifies if each of legend entry should be equal to the most wide entry.
    //	 *
    //	 * @return the equalWidths
    //	 */
    //	public Boolean getEqualWidths(){
    //		return equalWidths;
    //	}
    //
    //	/**
    //	 * 设置 specifies if each of legend entry should be equal to the most wide entry.
    //	 *
    //	 * @param equalWidths
    //	 *            the equalWidths to set
    //	 */
    //	public void setEqualWidths(Boolean equalWidths){
    //		this.equalWidths = equalWidths;
    //	}
    //
    //	/**
    //	 * 获得 font size.
    //	 *
    //	 * @return the fontSize
    //	 */
    //	public Number getFontSize(){
    //		return fontSize;
    //	}
    //
    //	/**
    //	 * 设置 font size.
    //	 *
    //	 * @param fontSize
    //	 *            the fontSize to set
    //	 */
    //	public void setFontSize(Number fontSize){
    //		this.fontSize = fontSize;
    //	}
    //
    //	/**
    //	 * 获得 horizontal space between legend item and left/right border.
    //	 *
    //	 * @return the horizontalGap
    //	 */
    //	public Number getHorizontalGap(){
    //		return horizontalGap;
    //	}
    //
    //	/**
    //	 * 设置 horizontal space between legend item and left/right border.
    //	 *
    //	 * @param horizontalGap
    //	 *            the horizontalGap to set
    //	 */
    //	public void setHorizontalGap(Number horizontalGap){
    //		this.horizontalGap = horizontalGap;
    //	}
    //
    //	/**
    //	 * 获得 the text which will be displayed in the legend.
    //	 *
    //	 * @return the labelText
    //	 */
    //	public String getLabelText(){
    //		return labelText;
    //	}
    //
    //	/**
    //	 * 设置 the text which will be displayed in the legend.
    //	 *
    //	 * @param labelText
    //	 *            the labelText to set
    //	 */
    //	public void setLabelText(String labelText){
    //		this.labelText = labelText;
    //	}
    //
    //	/**
    //	 * 获得 if width of the label is bigger than labelWidth, it will be wrapped.
    //	 *
    //	 * @return the labelWidth
    //	 */
    //	public Number getLabelWidth(){
    //		return labelWidth;
    //	}
    //
    //	/**
    //	 * 设置 if width of the label is bigger than labelWidth, it will be wrapped.
    //	 *
    //	 * @param labelWidth
    //	 *            the labelWidth to set
    //	 */
    //	public void setLabelWidth(Number labelWidth){
    //		this.labelWidth = labelWidth;
    //	}
    //
    //	/**
    //	 * 获得 in case legend position is set to "absolute", you can set distance from left side of the chart, in pixels.
    //	 *
    //	 * @return the left
    //	 */
    //	public Number getLeft(){
    //		return left;
    //	}
    //
    //	/**
    //	 * 设置 in case legend position is set to "absolute", you can set distance from left side of the chart, in pixels.
    //	 *
    //	 * @param left
    //	 *            the left to set
    //	 */
    //	public void setLeft(Number left){
    //		this.left = left;
    //	}
    //
    //	/**
    //	 * 获得 bottom margin.
    //	 *
    //	 * @return the marginBottom
    //	 */
    //	public Number getMarginBottom(){
    //		return marginBottom;
    //	}
    //
    //	/**
    //	 * 设置 bottom margin.
    //	 *
    //	 * @param marginBottom
    //	 *            the marginBottom to set
    //	 */
    //	public void setMarginBottom(Number marginBottom){
    //		this.marginBottom = marginBottom;
    //	}
    //
    //	/**
    //	 * 获得 left margin.
    //	 *
    //	 * @return the marginLeft
    //	 */
    //	public Number getMarginLeft(){
    //		return marginLeft;
    //	}
    //
    //	/**
    //	 * 设置 left margin.
    //	 *
    //	 * @param marginLeft
    //	 *            the marginLeft to set
    //	 */
    //	public void setMarginLeft(Number marginLeft){
    //		this.marginLeft = marginLeft;
    //	}
    //
    //	/**
    //	 * 获得 right margin.
    //	 *
    //	 * @return the marginRight
    //	 */
    //	public Number getMarginRight(){
    //		return marginRight;
    //	}
    //
    //	/**
    //	 * 设置 right margin.
    //	 *
    //	 * @param marginRight
    //	 *            the marginRight to set
    //	 */
    //	public void setMarginRight(Number marginRight){
    //		this.marginRight = marginRight;
    //	}
    //
    //	/**
    //	 * 获得 top margin.
    //	 *
    //	 * @return the marginTop
    //	 */
    //	public Number getMarginTop(){
    //		return marginTop;
    //	}
    //
    //	/**
    //	 * 设置 top margin.
    //	 *
    //	 * @param marginTop
    //	 *            the marginTop to set
    //	 */
    //	public void setMarginTop(Number marginTop){
    //		this.marginTop = marginTop;
    //	}
    //
    //	/**
    //	 * 获得 marker border opacity.
    //	 *
    //	 * @return the markerBorderAlpha
    //	 */
    //	public Number getMarkerBorderAlpha(){
    //		return markerBorderAlpha;
    //	}
    //
    //	/**
    //	 * 设置 marker border opacity.
    //	 *
    //	 * @param markerBorderAlpha
    //	 *            the markerBorderAlpha to set
    //	 */
    //	public void setMarkerBorderAlpha(Number markerBorderAlpha){
    //		this.markerBorderAlpha = markerBorderAlpha;
    //	}
    //
    //	/**
    //	 * 获得 marker border color.
    //	 *
    //	 * @return the markerBorderColor
    //	 */
    //	public String getMarkerBorderColor(){
    //		return markerBorderColor;
    //	}
    //
    //	/**
    //	 * 设置 marker border color.
    //	 *
    //	 * @param markerBorderColor
    //	 *            the markerBorderColor to set
    //	 */
    //	public void setMarkerBorderColor(String markerBorderColor){
    //		this.markerBorderColor = markerBorderColor;
    //	}
    //
    //	/**
    //	 * 获得 thickness of the legend border.
    //	 *
    //	 * @return the markerBorderThickness
    //	 */
    //	public Number getMarkerBorderThickness(){
    //		return markerBorderThickness;
    //	}
    //
    //	/**
    //	 * 设置 thickness of the legend border.
    //	 *
    //	 * @param markerBorderThickness
    //	 *            the markerBorderThickness to set
    //	 */
    //	public void setMarkerBorderThickness(Number markerBorderThickness){
    //		this.markerBorderThickness = markerBorderThickness;
    //	}
    //
    //	/**
    //	 * 获得 the color of the disabled marker (when the graph is hidden).
    //	 *
    //	 * @return the markerDisabledColor
    //	 */
    //	public String getMarkerDisabledColor(){
    //		return markerDisabledColor;
    //	}
    //
    //	/**
    //	 * 设置 the color of the disabled marker (when the graph is hidden).
    //	 *
    //	 * @param markerDisabledColor
    //	 *            the markerDisabledColor to set
    //	 */
    //	public void setMarkerDisabledColor(String markerDisabledColor){
    //		this.markerDisabledColor = markerDisabledColor;
    //	}
    //
    //	/**
    //	 * 获得 space between legend marker and legend text, in pixels.
    //	 *
    //	 * @return the markerLabelGap
    //	 */
    //	public Number getMarkerLabelGap(){
    //		return markerLabelGap;
    //	}
    //
    //	/**
    //	 * 设置 space between legend marker and legend text, in pixels.
    //	 *
    //	 * @param markerLabelGap
    //	 *            the markerLabelGap to set
    //	 */
    //	public void setMarkerLabelGap(Number markerLabelGap){
    //		this.markerLabelGap = markerLabelGap;
    //	}
    //
    //	/**
    //	 * 获得 size of the legend marker (key).
    //	 *
    //	 * @return the markerSize
    //	 */
    //	public Number getMarkerSize(){
    //		return markerSize;
    //	}
    //
    //	/**
    //	 * 设置 size of the legend marker (key).
    //	 *
    //	 * @param markerSize
    //	 *            the markerSize to set
    //	 */
    //	public void setMarkerSize(Number markerSize){
    //		this.markerSize = markerSize;
    //	}
    //
    //	/**
    //	 * 获得 shape of the legend marker (key).
    //	 *
    //	 * @return the markerType
    //	 */
    //	public String getMarkerType(){
    //		return markerType;
    //	}
    //
    //	/**
    //	 * 设置 shape of the legend marker (key).
    //	 *
    //	 * @param markerType
    //	 *            the markerType to set
    //	 */
    //	public void setMarkerType(String markerType){
    //		this.markerType = markerType;
    //	}
    //
    //	/**
    //	 * 获得 maximum number of columns in the legend.
    //	 *
    //	 * @return the maxColumns
    //	 */
    //	public Number getMaxColumns(){
    //		return maxColumns;
    //	}
    //
    //	/**
    //	 * 设置 maximum number of columns in the legend.
    //	 *
    //	 * @param maxColumns
    //	 *            the maxColumns to set
    //	 */
    //	public void setMaxColumns(Number maxColumns){
    //		this.maxColumns = maxColumns;
    //	}
    //
    //	/**
    //	 * 获得 the text which will be displayed in the value portion of the legend when user is not hovering above any data point.
    //	 *
    //	 * @return the periodValueText
    //	 */
    //	public String getPeriodValueText(){
    //		return periodValueText;
    //	}
    //
    //	/**
    //	 * 设置 the text which will be displayed in the value portion of the legend when user is not hovering above any data point.
    //	 *
    //	 * @param periodValueText
    //	 *            the periodValueText to set
    //	 */
    //	public void setPeriodValueText(String periodValueText){
    //		this.periodValueText = periodValueText;
    //	}
    //
    /**
     * 获得 position of a legend.
     *
     * @return the position
     */
    public String getPosition(){
        return position;
    }

    /**
     * 设置 position of a legend.
     *
     * @param position
     *            the position to set
     */
    public void setPosition(String position){
        this.position = position;
    }

    //
    //	/**
    //	 * 获得 specifies whether legend entries should be placed in reversed order.
    //	 *
    //	 * @return the reversedOrder
    //	 */
    //	public Boolean getReversedOrder(){
    //		return reversedOrder;
    //	}
    //
    //	/**
    //	 * 设置 specifies whether legend entries should be placed in reversed order.
    //	 *
    //	 * @param reversedOrder
    //	 *            the reversedOrder to set
    //	 */
    //	public void setReversedOrder(Boolean reversedOrder){
    //		this.reversedOrder = reversedOrder;
    //	}
    //
    //	/**
    //	 * 获得 in case legend position is set to "absolute", you can set distance from right side of the chart, in pixels.
    //	 *
    //	 * @return the right
    //	 */
    //	public Number getRight(){
    //		return right;
    //	}
    //
    //	/**
    //	 * 设置 in case legend position is set to "absolute", you can set distance from right side of the chart, in pixels.
    //	 *
    //	 * @param right
    //	 *            the right to set
    //	 */
    //	public void setRight(Number right){
    //		this.right = right;
    //	}
    //
    //	/**
    //	 * 获得 legend item text color on roll-over.
    //	 *
    //	 * @return the rollOverColor
    //	 */
    //	public String getRollOverColor(){
    //		return rollOverColor;
    //	}
    //
    //	/**
    //	 * 设置 legend item text color on roll-over.
    //	 *
    //	 * @param rollOverColor
    //	 *            the rollOverColor to set
    //	 */
    //	public void setRollOverColor(String rollOverColor){
    //		this.rollOverColor = rollOverColor;
    //	}
    //
    //	/**
    //	 * 获得 when you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be
    //	 * distinguished.
    //	 *
    //	 * @return the rollOverGraphAlpha
    //	 */
    //	public Number getRollOverGraphAlpha(){
    //		return rollOverGraphAlpha;
    //	}
    //
    //	/**
    //	 * 设置 when you roll-over the legend entry, all other graphs can reduce their opacity, so that the graph you rolled-over would be
    //	 * distinguished.
    //	 *
    //	 * @param rollOverGraphAlpha
    //	 *            the rollOverGraphAlpha to set
    //	 */
    //	public void setRollOverGraphAlpha(Number rollOverGraphAlpha){
    //		this.rollOverGraphAlpha = rollOverGraphAlpha;
    //	}
    //
    //	/**
    //	 * 获得 you can use this property to turn all the legend entries off.
    //	 *
    //	 * @return the showEntries
    //	 */
    //	public Boolean getShowEntries(){
    //		return showEntries;
    //	}
    //
    //	/**
    //	 * 设置 you can use this property to turn all the legend entries off.
    //	 *
    //	 * @param showEntries
    //	 *            the showEntries to set
    //	 */
    //	public void setShowEntries(Boolean showEntries){
    //		this.showEntries = showEntries;
    //	}
    //
    /**
     * 获得 horizontal space between legend items, in pixels.
     *
     * @return the spacing
     */
    public Number getSpacing(){
        return spacing;
    }

    /**
     * 设置 horizontal space between legend items, in pixels.
     *
     * @param spacing
     *            the spacing to set
     */
    public void setSpacing(Number spacing){
        this.spacing = spacing;
    }

    //
    //	/**
    //	 * 获得 whether showing/hiding of graphs by clicking on the legend marker is enabled or not.
    //	 *
    //	 * @return the switchable
    //	 */
    //	public Boolean getSwitchable(){
    //		return switchable;
    //	}
    //
    //	/**
    //	 * 设置 whether showing/hiding of graphs by clicking on the legend marker is enabled or not.
    //	 *
    //	 * @param switchable
    //	 *            the switchable to set
    //	 */
    //	public void setSwitchable(Boolean switchable){
    //		this.switchable = switchable;
    //	}
    //
    //	/**
    //	 * 获得 legend switch color.
    //	 *
    //	 * @return the switchColor
    //	 */
    //	public String getSwitchColor(){
    //		return switchColor;
    //	}
    //
    //	/**
    //	 * 设置 legend switch color.
    //	 *
    //	 * @param switchColor
    //	 *            the switchColor to set
    //	 */
    //	public void setSwitchColor(String switchColor){
    //		this.switchColor = switchColor;
    //	}
    //
    //	/**
    //	 * 获得 legend switch type (in case the legend is switchable).
    //	 *
    //	 * @return the switchType
    //	 */
    //	public String getSwitchType(){
    //		return switchType;
    //	}
    //
    //	/**
    //	 * 设置 legend switch type (in case the legend is switchable).
    //	 *
    //	 * @param switchType
    //	 *            the switchType to set
    //	 */
    //	public void setSwitchType(String switchType){
    //		this.switchType = switchType;
    //	}
    //
    //	/**
    //	 * 获得 if true, clicking on the text will show/hide balloon of the graph.
    //	 *
    //	 * @return the textClickEnabled
    //	 */
    //	public Boolean getTextClickEnabled(){
    //		return textClickEnabled;
    //	}
    //
    //	/**
    //	 * 设置 if true, clicking on the text will show/hide balloon of the graph.
    //	 *
    //	 * @param textClickEnabled
    //	 *            the textClickEnabled to set
    //	 */
    //	public void setTextClickEnabled(Boolean textClickEnabled){
    //		this.textClickEnabled = textClickEnabled;
    //	}
    //
    //	/**
    //	 * 获得 in case legend position is set to "absolute", you can set distance from top of the chart, in pixels.
    //	 *
    //	 * @return the top
    //	 */
    //	public Number getTop(){
    //		return top;
    //	}
    //
    //	/**
    //	 * 设置 in case legend position is set to "absolute", you can set distance from top of the chart, in pixels.
    //	 *
    //	 * @param top
    //	 *            the top to set
    //	 */
    //	public void setTop(Number top){
    //		this.top = top;
    //	}
    //
    //	/**
    //	 * 获得 legend markers can mirror graph’s settings, displaying a line and a real bullet as in the graph itself.
    //	 *
    //	 * @return the useGraphSettings
    //	 */
    //	public Boolean getUseGraphSettings(){
    //		return useGraphSettings;
    //	}
    //
    //	/**
    //	 * 设置 legend markers can mirror graph’s settings, displaying a line and a real bullet as in the graph itself.
    //	 *
    //	 * @param useGraphSettings
    //	 *            the useGraphSettings to set
    //	 */
    //	public void setUseGraphSettings(Boolean useGraphSettings){
    //		this.useGraphSettings = useGraphSettings;
    //	}
    //
    //	/**
    //	 * 获得 labels will use marker color if you set this to true.
    //	 *
    //	 * @return the useMarkerColorForLabels
    //	 */
    //	public Boolean getUseMarkerColorForLabels(){
    //		return useMarkerColorForLabels;
    //	}
    //
    //	/**
    //	 * 设置 labels will use marker color if you set this to true.
    //	 *
    //	 * @param useMarkerColorForLabels
    //	 *            the useMarkerColorForLabels to set
    //	 */
    //	public void setUseMarkerColorForLabels(Boolean useMarkerColorForLabels){
    //		this.useMarkerColorForLabels = useMarkerColorForLabels;
    //	}
    //
    //	/**
    //	 * 获得 specifies if legend values should be use same color as corresponding markers.
    //	 *
    //	 * @return the useMarkerColorForValues
    //	 */
    //	public Boolean getUseMarkerColorForValues(){
    //		return useMarkerColorForValues;
    //	}
    //
    //	/**
    //	 * 设置 specifies if legend values should be use same color as corresponding markers.
    //	 *
    //	 * @param useMarkerColorForValues
    //	 *            the useMarkerColorForValues to set
    //	 */
    //	public void setUseMarkerColorForValues(Boolean useMarkerColorForValues){
    //		this.useMarkerColorForValues = useMarkerColorForValues;
    //	}

    /**
     * 获得 alignment of the value text.
     *
     * @return the valueAlign
     */
    public String getValueAlign(){
        return valueAlign;
    }

    /**
     * 设置 alignment of the value text.
     *
     * @param valueAlign
     *            the valueAlign to set
     */
    public void setValueAlign(String valueAlign){
        this.valueAlign = valueAlign;
    }

    //	/**
    //	 * 获得 you can use it to format value labels in any way you want.
    //	 *
    //	 * @return the valueText
    //	 */
    //	public String getValueText(){
    //		return valueText;
    //	}
    //
    //	/**
    //	 * 设置 you can use it to format value labels in any way you want.
    //	 *
    //	 * @param valueText
    //	 *            the valueText to set
    //	 */
    //	public void setValueText(String valueText){
    //		this.valueText = valueText;
    //	}
    //
    //	/**
    //	 * 获得 width of the value text.
    //	 *
    //	 * @return the valueWidth
    //	 */
    //	public Number getValueWidth(){
    //		return valueWidth;
    //	}
    //
    //	/**
    //	 * 设置 width of the value text.
    //	 *
    //	 * @param valueWidth
    //	 *            the valueWidth to set
    //	 */
    //	public void setValueWidth(Number valueWidth){
    //		this.valueWidth = valueWidth;
    //	}
    //
    //	/**
    //	 * 获得 vertical space between legend items also between legend border and first and last legend row.
    //	 *
    //	 * @return the verticalGap
    //	 */
    //	public Number getVerticalGap(){
    //		return verticalGap;
    //	}
    //
    //	/**
    //	 * 设置 vertical space between legend items also between legend border and first and last legend row.
    //	 *
    //	 * @param verticalGap
    //	 *            the verticalGap to set
    //	 */
    //	public void setVerticalGap(Number verticalGap){
    //		this.verticalGap = verticalGap;
    //	}
    //
    //	/**
    //	 * 获得 width of a legend, when position is set to absolute.
    //	 *
    //	 * @return the width
    //	 */
    //	public Number getWidth(){
    //		return width;
    //	}
    //
    //	/**
    //	 * 设置 width of a legend, when position is set to absolute.
    //	 *
    //	 * @param width
    //	 *            the width to set
    //	 */
    //	public void setWidth(Number width){
    //		this.width = width;
    //	}

}
