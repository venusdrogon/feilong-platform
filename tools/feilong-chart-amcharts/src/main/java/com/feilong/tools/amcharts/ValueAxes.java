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
 * 数据轴,Extension for ValueAxis to create an axis for AmSerialChart, AmRadarChart, AmXYChart charts, multiple can be assigned.<br>
 * Gets automatically populated, one for AmSerialChart and two for AmXYChart charts, if none has been specified.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月28日 下午3:16:21
 * @since 1.0.8
 */
public class ValueAxes implements Serializable{

	private static final long	serialVersionUID		= 288232184048495608L;

	/** Specifies whether number of gridCount is specified automatically, acoarding to the axis size. */
	private Boolean				autoGridCount			= true;

	/** Axis opacity. Value range is 0 - 1. */
	private Number				axisAlpha				= 1;

	/** Axis color. */
	private String				axisColor				= "#000000";

	/** Thickness of the axis. */
	private Number				axisThickness			= 1;

	/** Radar chart only. Specifies distance from axis to the axis title (category) */
	private Number				axisTitleOffset			= 10;

	/** Read-only. Returns x coordinate of the axis. */
	private Number				axisX;

	/** Read-only. Returns y coordinate of the axis. */
	private Number				axisY;

	/** Read-only. Coordinate of the base value. */
	private Number				baseCoord;

	/** Specifies base value of the axis. */
	private Number				baseValue				= 0;

	/** Specifies if axis labels should be bold or not. */
	private Boolean				boldLabels				= false;

	/** Color of axis value labels. Will use chart's color if not set. */
	private String				color;

	/** Length of a dash. 0 means line is not dashed. */
	private Number				dashLength				= 0;

	/**
	 * If your values represents time units, and you want value axis labels to be formatted as duration, you have to set the duration unit.
	 * Possible values are: "ss", "mm", "hh" and "DD".
	 */
	private String				duration;

	/** If duration property is set, you can specify what string should be displayed next to day, hour, minute and second. */
	private Object				durationUnits			= "{DD:'d. ', hh:':', mm:':',ss:''}";

	/**
	 * Fill opacity. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the
	 * fills.
	 */
	private Number				fillAlpha				= 0;

	/**
	 * Fill color. Every second space between grid lines can be filled with color. Set fillAlpha to a value greater than 0 to see the fills.
	 */
	private String				fillColor				= "#FFFFFF";

	/** Size of value labels text. Will use chart's fontSize if not set. */
	private Number				fontSize;

	/** Opacity of grid lines. */
	private Number				gridAlpha				= 0.15;

	/** Color of grid lines. */
	private String				gridColor				= "#000000";

	/**
	 * Number of grid lines. In case this is value axis, or your categoryAxis parses dates, the number is approximate. The default value is
	 * 5. If you set autoGridCount to true, this property is ignored.
	 */
	private Number				gridCount				= 5;

	/** Thickness of grid lines. */
	private Number				gridThickness			= 1;

	/** Radar chart only. Possible values are: "polygons" and "circles". Set "circles" for polar charts. */
	private String				gridType				= "polygons";

	/** The array of guides belonging to this axis. */
	//private	Array[Guide]	guides	=	[]	;
	/** Unique id of value axis. It is not required to set it, unless you need to tell the graph which exact value axis it should use. */
	private String				id;

	/**
	 * If autoMargins of a chart is set to true, but you want this axis not to be measured when calculating margin, set ignoreAxisWidth to
	 * true.
	 */
	private Boolean				ignoreAxisWidth			= false;

	/** Specifies whether guide values should be included when calculating min and max of the axis. */
	private Boolean				includeGuidesInMinMax	= false;

	/** If true, the axis will include hidden graphs when calculating min and max values. */
	private Boolean				includeHidden			= false;

	/** Specifies whether values should be placed inside or outside plot area. */
	private Boolean				inside					= false;

	/** Specifies whether values on axis can only be integers or both integers and doubles. */
	private Boolean				integersOnly			= false;

	/** Frequency at which labels should be placed. Doesn't work forCategoryAxisif parseDates is set to true. */
	private Number				labelFrequency			= 1;

	/**
	 * You can use this function to format Value axis labels. This function is called and these parameters are passed: labelFunction(value,
	 * valueText, valueAxis);<br>
	 * Where value is numeric value, valueText is formatted string and valueAxis is a reference to valueAxis object. Your function should
	 * return string.
	 */
	//private		labelFunction			;
	/** You can use it to adjust position of axes labels. Works both withCategoryAxisand ValueAxis. */
	private Number				labelOffset				= 0;

	/**
	 * Rotation angle of a label. Only horizontal axis' values can be rotated. If you set this for vertical axis, the setting will be
	 * ignored. Possible values from -90 to 90.
	 */
	private Number				labelRotation			= 0;

	/** Specifies whether axis displays category axis' labels and value axis' values. */
	private Boolean				labelsEnabled			= true;

	/** Specifies if this value axis' scale should be logarithmic. */
	private Boolean				logarithmic				= false;

	/** Read-only. Maximum value of the axis. */
	private Number				max;

	/**
	 * If you don't want max value to be calculated by the chart, set it using this property. <br>
	 * This value might still be adjusted so that it would be possible to draw grid at rounded intervals.
	 */
	private Number				maximum;

	/** Read-only. Minimum value of the axis. */
	private Number				min;

	/**
	 * This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell width required for one span
	 * between grid lines.
	 */
	private Number				minHorizontalGap		= 75;

	/**
	 * If you don't want min value to be calculated by the chart, set it using this property. This value might still be adjusted so that it
	 * would be possible to draw grid at rounded intervals.
	 */
	private Number				minimum;

	/** If set value axis scale (min and max numbers) will be multiplied by it. I.e. if set to 1.2 the scope of values will increase by 20%. */
	private Number				minMaxMultiplier		= 1;

	/** Opacity of minor grid. In order minor to be visible, you should set minorGridEnabled to true. */
	private Number				minorGridAlpha			= 0.07;

	/** Specifies if minor grid should be displayed. */
	private Boolean				minorGridEnabled		= false;

	/**
	 * This property is used when calculating grid count (when autoGridCount is true). It specifies minimum cell height required for one
	 * span between grid lines.
	 */
	private Number				minVerticalGap			= 35;

	/** The distance of the axis to the plot area, in pixels. Negative values can also be used. */
	private Number				offset					= 0;

	/**
	 * Possible values are: "top", "bottom", "left", "right". If axis is vertical, default position is "left". If axis is horizontal,
	 * default position is "bottom".
	 */
	private String				position				= "left";

	/** Precision (number of decimals) of values. */
	private Number				precision;

	/** Radar chart only. Specifies if categories (axes' titles) should be displayed near axes) */
	private Boolean				radarCategoriesEnabled	= true;

	/** Specifies if graphs's values should be recalculated to percents. */
	private Boolean				recalculateToPercents	= false;

	/** Specifies if value axis should be reversed (smaller values on top). */
	private Boolean				reversed				= false;

	/**
	 * Whether to show first axis label or not. This works properly only on ValueAxis. WithCategoryAxisit wont work 100%, it depends on the
	 * period, zooming, etc. There is no guaranteed way to force category axis to show or hide first label.
	 */
	private Boolean				showFirstLabel			= true;

	/**
	 * Whether to show last axis label or not. This works properly only on ValueAxis. WithCategoryAxisit wont work 100%, it depends on the
	 * period, zooming, etc. There is no guaranteed way to force category axis to show or hide last label.
	 */
	private Boolean				showLastLabel			= true;

	/**
	 * Stacking mode of the axis. Possible values are: "none", "regular", "100%", "3d".<br>
	 * Note, only graphs of one type will be stacked.
	 */
	private String				stackType				= "none";

	/** Read-only. Value difference between two grid lines. */
	private Number				step;

	/**
	 * In case you synchronize one value axis with another, you need to set the synchronization multiplier. Use synchronizeWithAxis method
	 * to set with which axis it should be synced.
	 */
	private Number				synchronizationMultiplier;

	/**
	 * One value axis can be synchronized with another value axis. You can use both reference to your axis or id of the axis here. You
	 * should set synchronizationMultiplyer in order for this to work.
	 */
	//private	ValueAxis	synchronizeWith			;
	/** Length of the tick marks. */
	private Number				tickLength				= 5;

	/** Titleof the axis. */
	private String				title;

	/** Specifies if title should be bold or not. */
	private Boolean				titleBold				= true;

	/** Color of axis title. Will use text color of chart if not set any. */
	private String				titleColor;

	/** Font size of axis title. Will use font size of chart plus two pixels if not set any. */
	private Number				titleFontSize;

	/**
	 * If this value axis is stacked and has columns, setting valueAxis.totalText = "[[total]]" will make it to display total value above
	 * the most-top column.
	 */
	private String				totalText;

	/** Color of total text. */
	private String				totalTextColor;

	/** This allows you to have logarithmic value axis and have zero values in the data. You must set it to >0 value in order to work. */
	private Number				treatZeroAs				= 0;

	/** Unit which will be added to the value label. */
	private String				unit;

	/** Position of the unit. Possible values are "left" and "right". */
	private String				unitPosition			= "right";

	/**
	 * If true, prefixes will be used for big and small numbers. You can set arrays of prefixes directly to the chart object via
	 * prefixesOfSmallNumbers and prefixesOfBigNumbers.
	 */
	private Boolean				usePrefixes				= false;

	/**
	 * If true, values will always be formatted using scientific notation (5e+8, 5e-8...) Otherwise only values bigger then 1e+21 and
	 * smaller then 1e-7 will be displayed in scientific notation.
	 */
	private Boolean				useScientificNotation	= false;

	/**
	 * @return the autoGridCount
	 */
	public Boolean getAutoGridCount(){
		return autoGridCount;
	}

	/**
	 * @param autoGridCount
	 *            the autoGridCount to set
	 */
	public void setAutoGridCount(Boolean autoGridCount){
		this.autoGridCount = autoGridCount;
	}

	/**
	 * @return the axisAlpha
	 */
	public Number getAxisAlpha(){
		return axisAlpha;
	}

	/**
	 * @param axisAlpha
	 *            the axisAlpha to set
	 */
	public void setAxisAlpha(Number axisAlpha){
		this.axisAlpha = axisAlpha;
	}

	/**
	 * @return the axisColor
	 */
	public String getAxisColor(){
		return axisColor;
	}

	/**
	 * @param axisColor
	 *            the axisColor to set
	 */
	public void setAxisColor(String axisColor){
		this.axisColor = axisColor;
	}

	/**
	 * @return the axisThickness
	 */
	public Number getAxisThickness(){
		return axisThickness;
	}

	/**
	 * @param axisThickness
	 *            the axisThickness to set
	 */
	public void setAxisThickness(Number axisThickness){
		this.axisThickness = axisThickness;
	}

	/**
	 * @return the axisTitleOffset
	 */
	public Number getAxisTitleOffset(){
		return axisTitleOffset;
	}

	/**
	 * @param axisTitleOffset
	 *            the axisTitleOffset to set
	 */
	public void setAxisTitleOffset(Number axisTitleOffset){
		this.axisTitleOffset = axisTitleOffset;
	}

	/**
	 * @return the axisX
	 */
	public Number getAxisX(){
		return axisX;
	}

	/**
	 * @param axisX
	 *            the axisX to set
	 */
	public void setAxisX(Number axisX){
		this.axisX = axisX;
	}

	/**
	 * @return the axisY
	 */
	public Number getAxisY(){
		return axisY;
	}

	/**
	 * @param axisY
	 *            the axisY to set
	 */
	public void setAxisY(Number axisY){
		this.axisY = axisY;
	}

	/**
	 * @return the baseCoord
	 */
	public Number getBaseCoord(){
		return baseCoord;
	}

	/**
	 * @param baseCoord
	 *            the baseCoord to set
	 */
	public void setBaseCoord(Number baseCoord){
		this.baseCoord = baseCoord;
	}

	/**
	 * @return the baseValue
	 */
	public Number getBaseValue(){
		return baseValue;
	}

	/**
	 * @param baseValue
	 *            the baseValue to set
	 */
	public void setBaseValue(Number baseValue){
		this.baseValue = baseValue;
	}

	/**
	 * @return the boldLabels
	 */
	public Boolean getBoldLabels(){
		return boldLabels;
	}

	/**
	 * @param boldLabels
	 *            the boldLabels to set
	 */
	public void setBoldLabels(Boolean boldLabels){
		this.boldLabels = boldLabels;
	}

	/**
	 * @return the color
	 */
	public String getColor(){
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color){
		this.color = color;
	}

	/**
	 * @return the dashLength
	 */
	public Number getDashLength(){
		return dashLength;
	}

	/**
	 * @param dashLength
	 *            the dashLength to set
	 */
	public void setDashLength(Number dashLength){
		this.dashLength = dashLength;
	}

	/**
	 * @return the duration
	 */
	public String getDuration(){
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(String duration){
		this.duration = duration;
	}

	/**
	 * @return the durationUnits
	 */
	public Object getDurationUnits(){
		return durationUnits;
	}

	/**
	 * @param durationUnits
	 *            the durationUnits to set
	 */
	public void setDurationUnits(Object durationUnits){
		this.durationUnits = durationUnits;
	}

	/**
	 * @return the fillAlpha
	 */
	public Number getFillAlpha(){
		return fillAlpha;
	}

	/**
	 * @param fillAlpha
	 *            the fillAlpha to set
	 */
	public void setFillAlpha(Number fillAlpha){
		this.fillAlpha = fillAlpha;
	}

	/**
	 * @return the fillColor
	 */
	public String getFillColor(){
		return fillColor;
	}

	/**
	 * @param fillColor
	 *            the fillColor to set
	 */
	public void setFillColor(String fillColor){
		this.fillColor = fillColor;
	}

	/**
	 * @return the fontSize
	 */
	public Number getFontSize(){
		return fontSize;
	}

	/**
	 * @param fontSize
	 *            the fontSize to set
	 */
	public void setFontSize(Number fontSize){
		this.fontSize = fontSize;
	}

	/**
	 * @return the gridAlpha
	 */
	public Number getGridAlpha(){
		return gridAlpha;
	}

	/**
	 * @param gridAlpha
	 *            the gridAlpha to set
	 */
	public void setGridAlpha(Number gridAlpha){
		this.gridAlpha = gridAlpha;
	}

	/**
	 * @return the gridColor
	 */
	public String getGridColor(){
		return gridColor;
	}

	/**
	 * @param gridColor
	 *            the gridColor to set
	 */
	public void setGridColor(String gridColor){
		this.gridColor = gridColor;
	}

	/**
	 * @return the gridCount
	 */
	public Number getGridCount(){
		return gridCount;
	}

	/**
	 * @param gridCount
	 *            the gridCount to set
	 */
	public void setGridCount(Number gridCount){
		this.gridCount = gridCount;
	}

	/**
	 * @return the gridThickness
	 */
	public Number getGridThickness(){
		return gridThickness;
	}

	/**
	 * @param gridThickness
	 *            the gridThickness to set
	 */
	public void setGridThickness(Number gridThickness){
		this.gridThickness = gridThickness;
	}

	/**
	 * @return the gridType
	 */
	public String getGridType(){
		return gridType;
	}

	/**
	 * @param gridType
	 *            the gridType to set
	 */
	public void setGridType(String gridType){
		this.gridType = gridType;
	}

	/**
	 * @return the id
	 */
	public String getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id){
		this.id = id;
	}

	/**
	 * @return the ignoreAxisWidth
	 */
	public Boolean getIgnoreAxisWidth(){
		return ignoreAxisWidth;
	}

	/**
	 * @param ignoreAxisWidth
	 *            the ignoreAxisWidth to set
	 */
	public void setIgnoreAxisWidth(Boolean ignoreAxisWidth){
		this.ignoreAxisWidth = ignoreAxisWidth;
	}

	/**
	 * @return the includeGuidesInMinMax
	 */
	public Boolean getIncludeGuidesInMinMax(){
		return includeGuidesInMinMax;
	}

	/**
	 * @param includeGuidesInMinMax
	 *            the includeGuidesInMinMax to set
	 */
	public void setIncludeGuidesInMinMax(Boolean includeGuidesInMinMax){
		this.includeGuidesInMinMax = includeGuidesInMinMax;
	}

	/**
	 * @return the includeHidden
	 */
	public Boolean getIncludeHidden(){
		return includeHidden;
	}

	/**
	 * @param includeHidden
	 *            the includeHidden to set
	 */
	public void setIncludeHidden(Boolean includeHidden){
		this.includeHidden = includeHidden;
	}

	/**
	 * @return the inside
	 */
	public Boolean getInside(){
		return inside;
	}

	/**
	 * @param inside
	 *            the inside to set
	 */
	public void setInside(Boolean inside){
		this.inside = inside;
	}

	/**
	 * @return the integersOnly
	 */
	public Boolean getIntegersOnly(){
		return integersOnly;
	}

	/**
	 * @param integersOnly
	 *            the integersOnly to set
	 */
	public void setIntegersOnly(Boolean integersOnly){
		this.integersOnly = integersOnly;
	}

	/**
	 * @return the labelFrequency
	 */
	public Number getLabelFrequency(){
		return labelFrequency;
	}

	/**
	 * @param labelFrequency
	 *            the labelFrequency to set
	 */
	public void setLabelFrequency(Number labelFrequency){
		this.labelFrequency = labelFrequency;
	}

	/**
	 * @return the labelOffset
	 */
	public Number getLabelOffset(){
		return labelOffset;
	}

	/**
	 * @param labelOffset
	 *            the labelOffset to set
	 */
	public void setLabelOffset(Number labelOffset){
		this.labelOffset = labelOffset;
	}

	/**
	 * @return the labelRotation
	 */
	public Number getLabelRotation(){
		return labelRotation;
	}

	/**
	 * @param labelRotation
	 *            the labelRotation to set
	 */
	public void setLabelRotation(Number labelRotation){
		this.labelRotation = labelRotation;
	}

	/**
	 * @return the labelsEnabled
	 */
	public Boolean getLabelsEnabled(){
		return labelsEnabled;
	}

	/**
	 * @param labelsEnabled
	 *            the labelsEnabled to set
	 */
	public void setLabelsEnabled(Boolean labelsEnabled){
		this.labelsEnabled = labelsEnabled;
	}

	/**
	 * @return the logarithmic
	 */
	public Boolean getLogarithmic(){
		return logarithmic;
	}

	/**
	 * @param logarithmic
	 *            the logarithmic to set
	 */
	public void setLogarithmic(Boolean logarithmic){
		this.logarithmic = logarithmic;
	}

	/**
	 * @return the max
	 */
	public Number getMax(){
		return max;
	}

	/**
	 * @param max
	 *            the max to set
	 */
	public void setMax(Number max){
		this.max = max;
	}

	/**
	 * @return the maximum
	 */
	public Number getMaximum(){
		return maximum;
	}

	/**
	 * @param maximum
	 *            the maximum to set
	 */
	public void setMaximum(Number maximum){
		this.maximum = maximum;
	}

	/**
	 * @return the min
	 */
	public Number getMin(){
		return min;
	}

	/**
	 * @param min
	 *            the min to set
	 */
	public void setMin(Number min){
		this.min = min;
	}

	/**
	 * @return the minHorizontalGap
	 */
	public Number getMinHorizontalGap(){
		return minHorizontalGap;
	}

	/**
	 * @param minHorizontalGap
	 *            the minHorizontalGap to set
	 */
	public void setMinHorizontalGap(Number minHorizontalGap){
		this.minHorizontalGap = minHorizontalGap;
	}

	/**
	 * @return the minimum
	 */
	public Number getMinimum(){
		return minimum;
	}

	/**
	 * @param minimum
	 *            the minimum to set
	 */
	public void setMinimum(Number minimum){
		this.minimum = minimum;
	}

	/**
	 * @return the minMaxMultiplier
	 */
	public Number getMinMaxMultiplier(){
		return minMaxMultiplier;
	}

	/**
	 * @param minMaxMultiplier
	 *            the minMaxMultiplier to set
	 */
	public void setMinMaxMultiplier(Number minMaxMultiplier){
		this.minMaxMultiplier = minMaxMultiplier;
	}

	/**
	 * @return the minorGridAlpha
	 */
	public Number getMinorGridAlpha(){
		return minorGridAlpha;
	}

	/**
	 * @param minorGridAlpha
	 *            the minorGridAlpha to set
	 */
	public void setMinorGridAlpha(Number minorGridAlpha){
		this.minorGridAlpha = minorGridAlpha;
	}

	/**
	 * @return the minorGridEnabled
	 */
	public Boolean getMinorGridEnabled(){
		return minorGridEnabled;
	}

	/**
	 * @param minorGridEnabled
	 *            the minorGridEnabled to set
	 */
	public void setMinorGridEnabled(Boolean minorGridEnabled){
		this.minorGridEnabled = minorGridEnabled;
	}

	/**
	 * @return the minVerticalGap
	 */
	public Number getMinVerticalGap(){
		return minVerticalGap;
	}

	/**
	 * @param minVerticalGap
	 *            the minVerticalGap to set
	 */
	public void setMinVerticalGap(Number minVerticalGap){
		this.minVerticalGap = minVerticalGap;
	}

	/**
	 * @return the offset
	 */
	public Number getOffset(){
		return offset;
	}

	/**
	 * @param offset
	 *            the offset to set
	 */
	public void setOffset(Number offset){
		this.offset = offset;
	}

	/**
	 * @return the position
	 */
	public String getPosition(){
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position){
		this.position = position;
	}

	/**
	 * @return the precision
	 */
	public Number getPrecision(){
		return precision;
	}

	/**
	 * @param precision
	 *            the precision to set
	 */
	public void setPrecision(Number precision){
		this.precision = precision;
	}

	/**
	 * @return the radarCategoriesEnabled
	 */
	public Boolean getRadarCategoriesEnabled(){
		return radarCategoriesEnabled;
	}

	/**
	 * @param radarCategoriesEnabled
	 *            the radarCategoriesEnabled to set
	 */
	public void setRadarCategoriesEnabled(Boolean radarCategoriesEnabled){
		this.radarCategoriesEnabled = radarCategoriesEnabled;
	}

	/**
	 * @return the recalculateToPercents
	 */
	public Boolean getRecalculateToPercents(){
		return recalculateToPercents;
	}

	/**
	 * @param recalculateToPercents
	 *            the recalculateToPercents to set
	 */
	public void setRecalculateToPercents(Boolean recalculateToPercents){
		this.recalculateToPercents = recalculateToPercents;
	}

	/**
	 * @return the reversed
	 */
	public Boolean getReversed(){
		return reversed;
	}

	/**
	 * @param reversed
	 *            the reversed to set
	 */
	public void setReversed(Boolean reversed){
		this.reversed = reversed;
	}

	/**
	 * @return the showFirstLabel
	 */
	public Boolean getShowFirstLabel(){
		return showFirstLabel;
	}

	/**
	 * @param showFirstLabel
	 *            the showFirstLabel to set
	 */
	public void setShowFirstLabel(Boolean showFirstLabel){
		this.showFirstLabel = showFirstLabel;
	}

	/**
	 * @return the showLastLabel
	 */
	public Boolean getShowLastLabel(){
		return showLastLabel;
	}

	/**
	 * @param showLastLabel
	 *            the showLastLabel to set
	 */
	public void setShowLastLabel(Boolean showLastLabel){
		this.showLastLabel = showLastLabel;
	}

	/**
	 * @return the stackType
	 */
	public String getStackType(){
		return stackType;
	}

	/**
	 * @param stackType
	 *            the stackType to set
	 */
	public void setStackType(String stackType){
		this.stackType = stackType;
	}

	/**
	 * @return the step
	 */
	public Number getStep(){
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(Number step){
		this.step = step;
	}

	/**
	 * @return the synchronizationMultiplier
	 */
	public Number getSynchronizationMultiplier(){
		return synchronizationMultiplier;
	}

	/**
	 * @param synchronizationMultiplier
	 *            the synchronizationMultiplier to set
	 */
	public void setSynchronizationMultiplier(Number synchronizationMultiplier){
		this.synchronizationMultiplier = synchronizationMultiplier;
	}

	/**
	 * @return the tickLength
	 */
	public Number getTickLength(){
		return tickLength;
	}

	/**
	 * @param tickLength
	 *            the tickLength to set
	 */
	public void setTickLength(Number tickLength){
		this.tickLength = tickLength;
	}

	/**
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	/**
	 * @return the titleBold
	 */
	public Boolean getTitleBold(){
		return titleBold;
	}

	/**
	 * @param titleBold
	 *            the titleBold to set
	 */
	public void setTitleBold(Boolean titleBold){
		this.titleBold = titleBold;
	}

	/**
	 * @return the titleColor
	 */
	public String getTitleColor(){
		return titleColor;
	}

	/**
	 * @param titleColor
	 *            the titleColor to set
	 */
	public void setTitleColor(String titleColor){
		this.titleColor = titleColor;
	}

	/**
	 * @return the titleFontSize
	 */
	public Number getTitleFontSize(){
		return titleFontSize;
	}

	/**
	 * @param titleFontSize
	 *            the titleFontSize to set
	 */
	public void setTitleFontSize(Number titleFontSize){
		this.titleFontSize = titleFontSize;
	}

	/**
	 * @return the totalText
	 */
	public String getTotalText(){
		return totalText;
	}

	/**
	 * @param totalText
	 *            the totalText to set
	 */
	public void setTotalText(String totalText){
		this.totalText = totalText;
	}

	/**
	 * @return the totalTextColor
	 */
	public String getTotalTextColor(){
		return totalTextColor;
	}

	/**
	 * @param totalTextColor
	 *            the totalTextColor to set
	 */
	public void setTotalTextColor(String totalTextColor){
		this.totalTextColor = totalTextColor;
	}

	/**
	 * @return the treatZeroAs
	 */
	public Number getTreatZeroAs(){
		return treatZeroAs;
	}

	/**
	 * @param treatZeroAs
	 *            the treatZeroAs to set
	 */
	public void setTreatZeroAs(Number treatZeroAs){
		this.treatZeroAs = treatZeroAs;
	}

	/**
	 * @return the unit
	 */
	public String getUnit(){
		return unit;
	}

	/**
	 * @param unit
	 *            the unit to set
	 */
	public void setUnit(String unit){
		this.unit = unit;
	}

	/**
	 * @return the unitPosition
	 */
	public String getUnitPosition(){
		return unitPosition;
	}

	/**
	 * @param unitPosition
	 *            the unitPosition to set
	 */
	public void setUnitPosition(String unitPosition){
		this.unitPosition = unitPosition;
	}

	/**
	 * @return the usePrefixes
	 */
	public Boolean getUsePrefixes(){
		return usePrefixes;
	}

	/**
	 * @param usePrefixes
	 *            the usePrefixes to set
	 */
	public void setUsePrefixes(Boolean usePrefixes){
		this.usePrefixes = usePrefixes;
	}

	/**
	 * @return the useScientificNotation
	 */
	public Boolean getUseScientificNotation(){
		return useScientificNotation;
	}

	/**
	 * @param useScientificNotation
	 *            the useScientificNotation to set
	 */
	public void setUseScientificNotation(Boolean useScientificNotation){
		this.useScientificNotation = useScientificNotation;
	}

}
