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
 * Creates the visualization of the data in following types: line, column, step line, smoothed line, olhc and candlestick.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月5日 上午11:21:14
 * @since 1.0.8
 */
public class AmGraph{

	/** Bullet border opacity. */
	private Number	bulletBorderAlpha		= 0;

	/** Bullet offset. Distance from the actual data point to the bullet. Can be used to place custom bullets above the columns. */
	private Number	bulletOffset			= 0;

	/**
	 * Corner radius of column. It can be set both in pixels or in percents. The chart's depth and angle styles must be set to 0. The
	 * default value is 0. Note, cornerRadiusTop will be applied for all corners of the column, JavaScript charts do not have a possibility
	 * to set separate corner radius for top and bottom. As we want all the property names to be the same both on JS and Flex, we didn't
	 * change this too.
	 */
	private Number	cornerRadiusTop			= 0;

	/** Dash length. If you set it to a value greater than 0, the graph line (or columns border) will be dashed. */
	private Number	dashLength				= 0;

	/**
	 * Opacity of fill. Plural form is used to keep the same property names as our Flex charts'. Flex charts can accept array of numbers to
	 * generate gradients. Although you can set array here, only first value of this array will be used.
	 */
	private Number	fillAlphas				= 0;

	/** If there are more data points than hideBulletsCount, the bullets will not be shown. 0 means the bullets will always be visible. */
	private Number	hideBulletsCount		= 0;

	/** Offset of data label. */
	private Number	labelOffset				= 0;

	/** Rotation of a data label. */
	private Number	labelRotation			= 0;

	/** Specifies minimum size of the bullet (XY chart). */
	private Number	minBulletSize			= 0;

	/**
	 * If you use different colors for your negative values, a graph below zero line is filled with negativeColor. With this property you
	 * can define a different base value at which colors should be changed to negative colors.
	 */
	private Number	negativeBase			= 0;

	/** Opacity of bullets. Value range is 0 - 1. */
	private Number	bulletAlpha				= 1;

	/**
	 * If bulletsEnabled of ChartCurosor is true, a bullet on each graph follows the cursor. You can set opacity of each graphs bullet. In
	 * case you want to disable these bullets for a certain graph, set opacity to 0.
	 */
	private Number	cursorBulletAlpha		= 1;

	/** Opacity of the line (or column border). Value range is 0 - 1. */
	private Number	lineAlpha				= 1;

	/** Specifies thickness of the graph line (or column border). */
	private Number	lineThickness			= 1;

	/**
	 * It is useful if you have really lots of data points. Based on this property the graph will omit some of the lines (if the distance
	 * between points is less that minDistance, in pixels). This will not affect the bullets or indicator in anyway, so the user will not
	 * see any difference (unless you set minValue to a bigger value, let say 5), but will increase performance as less lines will be drawn.
	 * By setting value to a bigger number you can also make your lines look less jagged.
	 */
	private Number	minDistance				= 1;

	/** Opacity of the negative portion of the line (or column border). Value range is 0 - 1. */
	private Number	negativeLineAlpha		= 1;

	/** This property can be used by step graphs - you can set how many periods one horizontal line should span. */
	private Number	periodSpan				= 1;

	/** Bullet border thickness. */
	private Number	bulletBorderThickness	= 2;

	/** Bullet size. */
	private Number	bulletSize				= 8;

	/** Specifies size of the bullet which value is the biggest (XY chart). */
	private Number	maxBulletSize			= 50;

	/**
	 * Balloon text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] or any other field name from
	 * your data provider. HTML tags can also be used.
	 */
	private String	balloonText				= "[[value]]";

	/** Data label text anchor. */
	private String	labelAnchor				= "auto";

	/**
	 * Specifies graphs value at which cursor is showed. This is only important for candlestick and ohlc charts, also if column chart has
	 * "open" value. Possible values are: "open", "close", "high", "low".
	 */
	private String	showBalloonAt			= "close";

	/**
	 * Works with candlestick graph type, you can set it to open, close, high, low. If you set it to high, the events will be shown at the
	 * tip of the high line.
	 */
	private String	showBulletsAt			= "close";

	/**
	 * Type of the graph. <br>
	 * Possible values are: "line", "column", "step", "smoothedLine", "candlestick", "ohlc". XY and Radar charts can only
	 * display "line" type graphs.
	 */
	private String	type					= "line";

	/**
	 * Specifies where data points should be placed - on the beginning of the period (day, hour, etc) or in the middle (only when parseDates
	 * property of categoryAxis is set to true). This setting affects Serial chart only. Possible values are "start", "middle" and "end"
	 */
	private String	pointPosition			= "middle";

	/**
	 * Type of the bullets. <br>
	 * Possible values are: "none", "round", "square", "triangleUp", "triangleDown", "triangleLeft", "triangleRight",
	 * "bubble", "diamond", "xError", "yError" and "custom".
	 */
	private String	bullet					= "none";

	/**
	 * Step graph only. <br>
	 * Specifies to which direction step should be drawn.
	 */
	private String	stepDirection			= "right";

	/**
	 * Position of value label. Possible values are: "bottom", "top", "right", "left", "inside", "middle". Sometimes position is changed by
	 * the chart, depending on a graph type, rotation, etc.
	 */
	private String	labelPosition			= "top";

	///**	Specifies which value axis the graph will use. Will use the first value axis if not set. You can use reference to the real ValueAxis object or set value axis id.	*/	private	ValueAxis	valueAxis	=	ValueAxis	;
	///**	XY chart only. A horizontal value axis object to attach graph to.	*/	private	ValueAxis	xAxis	=	ValueAxis	;
	///**	XY chart only. A vertical value axis object to attach graph to.	*/	private	ValueAxis	yAxis	=	ValueAxis	;
	/** Orientation of the gradient fills (only for "column" graph type). Possible values are "vertical" and "horizontal". */
	private String	gradientOrientation		= "vertical";

	/** Specifies if the line graph should be placed behind column graphs. */
	private Boolean	behindColumns			= false;

	/**
	 * Specifies whether the graph is hidden. <br>
	 * Do not use this to show/hide the graph, use hideGraph(graph) and showGraph(graph) methods instead.
	 */
	private Boolean	hidden					= false;

	/** If you set it to true, column chart will begin new stack. This allows having Clustered and Stacked column/bar chart. */
	private Boolean	newStack				= false;

	/** In case you want to have a step line graph without risers, you should set this to true. */
	private Boolean	noStepRisers			= false;

	/**
	 * If this is set to true, candlesticks will be colored in a different manner - if current close is less than current open, the
	 * candlestick will be empty, otherwise - filled with color. If previous close is less than current close, the candlestick will use
	 * positive color, otherwise - negative color.
	 */
	private Boolean	proCandlesticks			= false;

	/**
	 * If graph's type is column and labelText is set, graph hides labels which do not fit into the column's space. If you don't want these
	 * labels to be hidden, set this to true.
	 */
	private Boolean	showAllValueLabels		= false;

	/** If you want mouse pointer to change to hand when hovering the graph, set this property to true. */
	private Boolean	showHandOnHover			= false;

	/**
	 * It can only be used together with topRadius (when columns look like cylinders). <br>
	 * If you set it to true, the cylinder will be lowered
	 * down so that the center of it's bottom circle would be right on category axis.
	 */
	private Boolean	showOnAxis				= false;

	/**
	 * If negativeLineColor and/or negativeFillColors are set and useNegativeColorIfDown is set to true (default is false), the line, step
	 * and column graphs will use these colors for lines, bullets or columns if previous value is bigger than current value. In case you set
	 * openField for the graph, the graph will compare current value with openField value instead of comparing to previous value. Here is a
	 * demo.
	 */
	private Boolean	useNegativeColorIfDown	= false;

	/**
	 * In case you want to place this graph's columns in front of other columns, set this to false. <br>
	 * In case "true", the columns will be
	 * clustered next to each other.
	 */
	private Boolean	clustered				= true;

	/**
	 * Specifies whether to connect data points if data is missing.<br>
	 * The default value is true.
	 */
	private Boolean	connect					= true;

	/** Whether to include this graph when calculating min and max value of the axis. */
	private Boolean	includeInMinMax			= true;

	/** Specifies whether the value balloon of this graph is shown when mouse is over data item or chart's indicator is over some series. */
	private Boolean	showBalloon				= true;

	/** If the value axis of this graph has stack types like "regular" or "100%" You can exclude this graph from stacking. */
	private Boolean	stackable				= true;

	/** If you set it to false, the graph will not be hidden when user clicks on legend entry. */
	private Boolean	switchable				= true;

	/** Specifies whether this graph should be shown in the Legend. */
	private Boolean	visibleInLegend			= true;

	/** Name of the alpha field in your dataProvider. */
	private String	alphaField;

	/** Value balloon color. Will use graph or data item color if not set. */
	private String	balloonColor;

	///**	If you set some function, the graph will call it and pass GraphDataItem and AmGraph object to it. This function should return a string which will be displayed in a balloon.	*/	private		balloonFunction			;
	///**	bulletAxis value is used when you are building error chart. Error chart is a regular serial or XY chart with bullet type set to "xError" or "yError". The graph should know which axis should be used to determine the size of this bullet - that's when bulletAxis should be set. Besides that, you should also set graph.errorField. You can also use other bullet types with this feature too. For example, if you set bulletAxis for XY chart, the size of a bullet will change as you zoom the chart.	*/	private	ValueAxis	bulletAxis			;
	/** Bullet border color. Will use lineColor if not set. */
	private String	bulletBorderColor;

	/** Bullet color. Will use lineColor if not set. */
	private String	bulletColor;

	/** Name of the bullet field in your dataProvider. */
	private String	bulletField;

	/** Name of the bullet size field in your dataProvider. */
	private String	bulletSizeField;

	/** Name of the close field (used by candlesticks and ohlc) in your dataProvider. */
	private String	closeField;

	/** Color of value labels. Will use chart's color if not set. */
	private String	String;

	/** Name of the color field in your dataProvider. */
	private String	colorField;

	/** You can specify custom column width for each graph individually. Value range is 0 - 1 (we set relative width, not pixel width here). */
	private Number	columnWidth;

	/** Path to the image of custom bullet. */
	private String	customBullet;

	/** Name of the custom bullet field in your dataProvider. */
	private String	customBulletField;

	/** Path to the image for legend marker. */
	private String	customMarker;

	/**
	 * Name of the dash length field in your dataProvider. This property adds a possibility to change graphs’ line from solid to dashed on
	 * any data point. You can also make columns border dashed using this setting.
	 */
	private String	dashLengthField;

	/** Name of the description field in your dataProvider. */
	private String	descriptionField;

	/** Name of error value field in your data provider. */
	private String	errorField;

	/** Fill color. Will use lineColor if not set. You can also set array of colors here. */
	private String	fillColors;

	/**
	 * Name of the fill colors field in your dataProvider. This property adds a possibility to change line graphs’ fill color on any data
	 * point to create highlighted sections of the graph. Works only withAmSerialChart.
	 */
	private String	fillColorsField;

	///**	XY chart only. If you set this property to id or reference of your X or Y axis, and the fillAlphas is > 0, the area between graph and axis will be filled with color, like in this demo.	*/	private	ValueAxis	fillToAxis			;
	/**
	 * You can set another graph here and if fillAlpha is >0, the area from this graph to fillToGraph will be filled (instead of filling the
	 * area to the X axis).
	 */
	private AmGraph	fillToGraph;

	/** Column width in pixels. If you set this property, columns will be of a fixed width and won't adjust to the available space. */
	private Number	fixedColumnWidth;

	/** Size of value labels text. Will use chart's fontSize if not set. */
	private Number	fontSize;

	/** Name of the high field (used by candlesticks and ohlc) in your dataProvider. */
	private String	highField;

	/**
	 * Unique id of a graph. It is not required to set one, unless you want to use this graph for as your scrollbar's graph and need to
	 * indicate which graph should be used.
	 */
	private String	id;

	/** Name of label color field in data provider. */
	private String	labelColorField;

	///**	You can use it to format labels of data items in any way you want. Graph will call this function and pass reference to GraphDataItem and formatted text as attributes. This function should return string which will be displayed as label.	*/	private		labelFunction			;
	/** Value label text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]]. */
	private String	labelText;

	/** Legend marker opacity. Will use lineAlpha if not set. Value range is 0 - 1. */
	private Number	legendAlpha;

	/** Legend marker color. Will use lineColor if not set. */
	private String	legendColor;

	/**
	 * The text which will be displayed in the value portion of the legend when user is not hovering above any data point. The tags should
	 * be made out of two parts - the name of a field (value / open / close / high / low) and the value of the period you want to be show -
	 * open / close / high / low / sum / average / count. For example: [[value.sum]] means that sum of all data points of value field in the
	 * selected period will be displayed.
	 */
	private String	legendPeriodValueText;

	/**
	 * Legend value text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] You can also use custom
	 * fields from your dataProvider. If not set, uses Legend's valueText.
	 */
	private String	legendValueText;

	/**
	 * Color of the line (or column border). If you do not set any, the color from AmCoordinateChart.colors array will be used for each
	 * subsequent graph.
	 */
	private String	lineColor;

	/**
	 * Name of the line color field in your dataProvider. This property adds a possibility to change graphs’ line color on any data point to
	 * create highlighted sections of the graph. Works only withAmSerialChart.
	 */
	private String	lineColorField;

	/** Name of the low field (used by candlesticks and ohlc) in your dataProvider. */
	private String	lowField;

	/**
	 * Legend marker type. You can set legend marker (key) type for individual graphs. Possible values are: square, circle, diamond,
	 * triangleUp, triangleDown, triangleLeft, triangleDown, bubble, line, none.
	 */
	private String	markerType;

	/** Fill opacity of negative part of the graph. Will use fillAlphas if not set. */
	private Number	negativeFillAlphas;

	/** Fill color of negative part of the graph. Will use fillColors if not set. */
	private String	negativeFillColors;

	/**
	 * Color of the line (or column) when the values are negative. In case the graph type is candlestick or ohlc, negativeLineColor is used
	 * when close value is less then open value.
	 */
	private String	negativeLineColor;

	/** Name of the open field (used by floating columns, candlesticks and ohlc) in your dataProvider. */
	private String	openField;

	/**
	 * Value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values. For
	 * example: {"url":"../amcharts/patterns/black/pattern1.png", "width":4, "height":4}. If you want to have individual patterns for each
	 * column, define patterns in data provider and set graph.patternField property. Check amcharts/patterns folder for some patterns. You
	 * can create your own patterns and use them. Note, x, y, randomX and randomY properties won't work with IE8 and older. 3D bar/Pie
	 * charts
	 * won't work properly with patterns.
	 */
	private Object	pattern;

	/**
	 * Field name in your data provider which holds pattern information. Value of pattern should be object with url, width, height of an
	 * image, optionally it might have x, y, randomX and randomY values. For example: {"url":"../amcharts/patterns/black/pattern1.png",
	 * "width":4, "height":4}. Check amcharts/patterns folder for some patterns. You can create your own patterns and use them. Note, x, y,
	 * randomX and randomY properties won't work with IE8 and older. 3D bar/Pie charts won't work properly with patterns.
	 */
	private String	patternField;

	/** Precision of values. Will use chart's precision if not set any. */
	private Number	precision;

	/** Graph title. */
	private String	title;

	/**
	 * If you set this to 1, columns will become cylinders (must set depth3D and angle properties of a chart to >0 values in order this to
	 * be visible). you can make columns look like cones (set topRadius to 0) or even like some glasses (set to bigger than 1). We strongly
	 * recommend setting grid opacity to 0 in order this to look good.
	 */
	private Number	topRadius;

	/** Name of the url field in your dataProvider. */
	private String	urlField;

	/** Target to open URLs in, i.e. _blank, _top, etc. */
	private String	urlTarget;

	/** Name of the value field in your dataProvider. */
	private String	valueField;

	/** XY chart only. Name of the x field in your dataProvider. */
	private String	xField;

	/** XY chart only. Name of the y field in your dataProvider. */
	private String	yField;

	//	/**
	//	 * 获得 bullet border opacity.
	//	 *
	//	 * @return the bulletBorderAlpha
	//	 */
	//	public Number getBulletBorderAlpha(){
	//		return bulletBorderAlpha;
	//	}
	//
	//	/**
	//	 * 设置 bullet border opacity.
	//	 *
	//	 * @param bulletBorderAlpha
	//	 *            the bulletBorderAlpha to set
	//	 */
	//	public void setBulletBorderAlpha(Number bulletBorderAlpha){
	//		this.bulletBorderAlpha = bulletBorderAlpha;
	//	}
	//
	//	/**
	//	 * 获得 bullet offset.
	//	 *
	//	 * @return the bulletOffset
	//	 */
	//	public Number getBulletOffset(){
	//		return bulletOffset;
	//	}
	//
	//	/**
	//	 * 设置 bullet offset.
	//	 *
	//	 * @param bulletOffset
	//	 *            the bulletOffset to set
	//	 */
	//	public void setBulletOffset(Number bulletOffset){
	//		this.bulletOffset = bulletOffset;
	//	}
	//
	//	/**
	//	 * 获得 corner radius of column.
	//	 *
	//	 * @return the cornerRadiusTop
	//	 */
	//	public Number getCornerRadiusTop(){
	//		return cornerRadiusTop;
	//	}
	//
	//	/**
	//	 * 设置 corner radius of column.
	//	 *
	//	 * @param cornerRadiusTop
	//	 *            the cornerRadiusTop to set
	//	 */
	//	public void setCornerRadiusTop(Number cornerRadiusTop){
	//		this.cornerRadiusTop = cornerRadiusTop;
	//	}
	//
	//	/**
	//	 * 获得 dash length.
	//	 *
	//	 * @return the dashLength
	//	 */
	//	public Number getDashLength(){
	//		return dashLength;
	//	}
	//
	//	/**
	//	 * 设置 dash length.
	//	 *
	//	 * @param dashLength
	//	 *            the dashLength to set
	//	 */
	//	public void setDashLength(Number dashLength){
	//		this.dashLength = dashLength;
	//	}
	//
	/**
	 * 获得 opacity of fill.
	 *
	 * @return the fillAlphas
	 */
	public Number getFillAlphas(){
		return fillAlphas;
	}

	/**
	 * 设置 opacity of fill.
	 *
	 * @param fillAlphas
	 *            the fillAlphas to set
	 */
	public void setFillAlphas(Number fillAlphas){
		this.fillAlphas = fillAlphas;
	}

	//
	//	/**
	//	 * 获得 if there are more data points than hideBulletsCount, the bullets will not be shown.
	//	 *
	//	 * @return the hideBulletsCount
	//	 */
	//	public Number getHideBulletsCount(){
	//		return hideBulletsCount;
	//	}
	//
	//	/**
	//	 * 设置 if there are more data points than hideBulletsCount, the bullets will not be shown.
	//	 *
	//	 * @param hideBulletsCount
	//	 *            the hideBulletsCount to set
	//	 */
	//	public void setHideBulletsCount(Number hideBulletsCount){
	//		this.hideBulletsCount = hideBulletsCount;
	//	}
	//
	//	/**
	//	 * 获得 offset of data label.
	//	 *
	//	 * @return the labelOffset
	//	 */
	//	public Number getLabelOffset(){
	//		return labelOffset;
	//	}
	//
	//	/**
	//	 * 设置 offset of data label.
	//	 *
	//	 * @param labelOffset
	//	 *            the labelOffset to set
	//	 */
	//	public void setLabelOffset(Number labelOffset){
	//		this.labelOffset = labelOffset;
	//	}
	//
	//	/**
	//	 * 获得 rotation of a data label.
	//	 *
	//	 * @return the labelRotation
	//	 */
	//	public Number getLabelRotation(){
	//		return labelRotation;
	//	}
	//
	//	/**
	//	 * 设置 rotation of a data label.
	//	 *
	//	 * @param labelRotation
	//	 *            the labelRotation to set
	//	 */
	//	public void setLabelRotation(Number labelRotation){
	//		this.labelRotation = labelRotation;
	//	}
	//
	//	/**
	//	 * 获得 specifies minimum size of the bullet (XY chart).
	//	 *
	//	 * @return the minBulletSize
	//	 */
	//	public Number getMinBulletSize(){
	//		return minBulletSize;
	//	}
	//
	//	/**
	//	 * 设置 specifies minimum size of the bullet (XY chart).
	//	 *
	//	 * @param minBulletSize
	//	 *            the minBulletSize to set
	//	 */
	//	public void setMinBulletSize(Number minBulletSize){
	//		this.minBulletSize = minBulletSize;
	//	}
	//
	//	/**
	//	 * 获得 if you use different colors for your negative values, a graph below zero line is filled with negativeColor.
	//	 *
	//	 * @return the negativeBase
	//	 */
	//	public Number getNegativeBase(){
	//		return negativeBase;
	//	}
	//
	//	/**
	//	 * 设置 if you use different colors for your negative values, a graph below zero line is filled with negativeColor.
	//	 *
	//	 * @param negativeBase
	//	 *            the negativeBase to set
	//	 */
	//	public void setNegativeBase(Number negativeBase){
	//		this.negativeBase = negativeBase;
	//	}
	//
	//	/**
	//	 * 获得 opacity of bullets.
	//	 *
	//	 * @return the bulletAlpha
	//	 */
	//	public Number getBulletAlpha(){
	//		return bulletAlpha;
	//	}
	//
	//	/**
	//	 * 设置 opacity of bullets.
	//	 *
	//	 * @param bulletAlpha
	//	 *            the bulletAlpha to set
	//	 */
	//	public void setBulletAlpha(Number bulletAlpha){
	//		this.bulletAlpha = bulletAlpha;
	//	}
	//
	//	/**
	//	 * 获得 if bulletsEnabled of ChartCurosor is true, a bullet on each graph follows the cursor.
	//	 *
	//	 * @return the cursorBulletAlpha
	//	 */
	//	public Number getCursorBulletAlpha(){
	//		return cursorBulletAlpha;
	//	}
	//
	//	/**
	//	 * 设置 if bulletsEnabled of ChartCurosor is true, a bullet on each graph follows the cursor.
	//	 *
	//	 * @param cursorBulletAlpha
	//	 *            the cursorBulletAlpha to set
	//	 */
	//	public void setCursorBulletAlpha(Number cursorBulletAlpha){
	//		this.cursorBulletAlpha = cursorBulletAlpha;
	//	}
	//
	/**
	 * 获得 opacity of the line (or column border).
	 *
	 * @return the lineAlpha
	 */
	public Number getLineAlpha(){
		return lineAlpha;
	}

	/**
	 * 设置 opacity of the line (or column border).
	 *
	 * @param lineAlpha
	 *            the lineAlpha to set
	 */
	public void setLineAlpha(Number lineAlpha){
		this.lineAlpha = lineAlpha;
	}

	//
	//	/**
	//	 * 获得 specifies thickness of the graph line (or column border).
	//	 *
	//	 * @return the lineThickness
	//	 */
	//	public Number getLineThickness(){
	//		return lineThickness;
	//	}
	//
	//	/**
	//	 * 设置 specifies thickness of the graph line (or column border).
	//	 *
	//	 * @param lineThickness
	//	 *            the lineThickness to set
	//	 */
	//	public void setLineThickness(Number lineThickness){
	//		this.lineThickness = lineThickness;
	//	}
	//
	//	/**
	//	 * 获得 it is useful if you have really lots of data points.
	//	 *
	//	 * @return the minDistance
	//	 */
	//	public Number getMinDistance(){
	//		return minDistance;
	//	}
	//
	//	/**
	//	 * 设置 it is useful if you have really lots of data points.
	//	 *
	//	 * @param minDistance
	//	 *            the minDistance to set
	//	 */
	//	public void setMinDistance(Number minDistance){
	//		this.minDistance = minDistance;
	//	}
	//
	//	/**
	//	 * 获得 opacity of the negative portion of the line (or column border).
	//	 *
	//	 * @return the negativeLineAlpha
	//	 */
	//	public Number getNegativeLineAlpha(){
	//		return negativeLineAlpha;
	//	}
	//
	//	/**
	//	 * 设置 opacity of the negative portion of the line (or column border).
	//	 *
	//	 * @param negativeLineAlpha
	//	 *            the negativeLineAlpha to set
	//	 */
	//	public void setNegativeLineAlpha(Number negativeLineAlpha){
	//		this.negativeLineAlpha = negativeLineAlpha;
	//	}
	//
	//	/**
	//	 * 获得 this property can be used by step graphs - you can set how many periods one horizontal line should span.
	//	 *
	//	 * @return the periodSpan
	//	 */
	//	public Number getPeriodSpan(){
	//		return periodSpan;
	//	}
	//
	//	/**
	//	 * 设置 this property can be used by step graphs - you can set how many periods one horizontal line should span.
	//	 *
	//	 * @param periodSpan
	//	 *            the periodSpan to set
	//	 */
	//	public void setPeriodSpan(Number periodSpan){
	//		this.periodSpan = periodSpan;
	//	}
	//
	//	/**
	//	 * 获得 bullet border thickness.
	//	 *
	//	 * @return the bulletBorderThickness
	//	 */
	//	public Number getBulletBorderThickness(){
	//		return bulletBorderThickness;
	//	}
	//
	//	/**
	//	 * 设置 bullet border thickness.
	//	 *
	//	 * @param bulletBorderThickness
	//	 *            the bulletBorderThickness to set
	//	 */
	//	public void setBulletBorderThickness(Number bulletBorderThickness){
	//		this.bulletBorderThickness = bulletBorderThickness;
	//	}
	//
	//	/**
	//	 * 获得 bullet size.
	//	 *
	//	 * @return the bulletSize
	//	 */
	//	public Number getBulletSize(){
	//		return bulletSize;
	//	}
	//
	//	/**
	//	 * 设置 bullet size.
	//	 *
	//	 * @param bulletSize
	//	 *            the bulletSize to set
	//	 */
	//	public void setBulletSize(Number bulletSize){
	//		this.bulletSize = bulletSize;
	//	}
	//
	//	/**
	//	 * 获得 specifies size of the bullet which value is the biggest (XY chart).
	//	 *
	//	 * @return the maxBulletSize
	//	 */
	//	public Number getMaxBulletSize(){
	//		return maxBulletSize;
	//	}
	//
	//	/**
	//	 * 设置 specifies size of the bullet which value is the biggest (XY chart).
	//	 *
	//	 * @param maxBulletSize
	//	 *            the maxBulletSize to set
	//	 */
	//	public void setMaxBulletSize(Number maxBulletSize){
	//		this.maxBulletSize = maxBulletSize;
	//	}
	//
	/**
	 * Balloon text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] or any other field name from
	 * your data provider. HTML tags can also be used.
	 *
	 * @return the balloonText
	 */
	public String getBalloonText(){
		return balloonText;
	}

	/**
	 * Balloon text. You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]] or any other field name from
	 * your data provider. HTML tags can also be used.
	 *
	 * @param balloonText
	 *            the balloonText to set
	 */
	public void setBalloonText(String balloonText){
		this.balloonText = balloonText;
	}

	//
	//	/**
	//	 * 获得 data label text anchor.
	//	 *
	//	 * @return the labelAnchor
	//	 */
	//	public String getLabelAnchor(){
	//		return labelAnchor;
	//	}
	//
	//	/**
	//	 * 设置 data label text anchor.
	//	 *
	//	 * @param labelAnchor
	//	 *            the labelAnchor to set
	//	 */
	//	public void setLabelAnchor(String labelAnchor){
	//		this.labelAnchor = labelAnchor;
	//	}
	//
	//	/**
	//	 * 获得 specifies graphs value at which cursor is showed.
	//	 *
	//	 * @return the showBalloonAt
	//	 */
	//	public String getShowBalloonAt(){
	//		return showBalloonAt;
	//	}
	//
	//	/**
	//	 * 设置 specifies graphs value at which cursor is showed.
	//	 *
	//	 * @param showBalloonAt
	//	 *            the showBalloonAt to set
	//	 */
	//	public void setShowBalloonAt(String showBalloonAt){
	//		this.showBalloonAt = showBalloonAt;
	//	}
	//
	//	/**
	//	 * 获得 works with candlestick graph type, you can set it to open, close, high, low.
	//	 *
	//	 * @return the showBulletsAt
	//	 */
	//	public String getShowBulletsAt(){
	//		return showBulletsAt;
	//	}
	//
	//	/**
	//	 * 设置 works with candlestick graph type, you can set it to open, close, high, low.
	//	 *
	//	 * @param showBulletsAt
	//	 *            the showBulletsAt to set
	//	 */
	//	public void setShowBulletsAt(String showBulletsAt){
	//		this.showBulletsAt = showBulletsAt;
	//	}

	/**
	 * Type of the graph. Possible values are: "line", "column", "step", "smoothedLine", "candlestick", "ohlc". XY and Radar charts can only
	 * display "line" type graphs.
	 *
	 * @return the type
	 */
	public String getType(){
		return type;
	}

	/**
	 * Type of the graph. Possible values are: "line", "column", "step", "smoothedLine", "candlestick", "ohlc". XY and Radar charts can only
	 * display "line" type graphs.
	 *
	 * @param type
	 *            the type to set
	 */
	public void setType(String type){
		this.type = type;
	}

	//
	//	/**
	//	 * 获得 specifies where data points should be placed - on the beginning of the period (day, hour, etc) or in the middle (only when
	//	 * parseDates property of categoryAxis is set to true).
	//	 *
	//	 * @return the pointPosition
	//	 */
	//	public String getPointPosition(){
	//		return pointPosition;
	//	}
	//
	//	/**
	//	 * 设置 specifies where data points should be placed - on the beginning of the period (day, hour, etc) or in the middle (only when
	//	 * parseDates property of categoryAxis is set to true).
	//	 *
	//	 * @param pointPosition
	//	 *            the pointPosition to set
	//	 */
	//	public void setPointPosition(String pointPosition){
	//		this.pointPosition = pointPosition;
	//	}
	//
	/**
	 * Possible values are: "none", "round", "square", "triangleUp", "triangleDown", "triangleLeft", "triangleRight",
	 * "bubble", "diamond", "xError", "yError" and "custom".
	 *
	 * @return the bullet
	 */
	public String getBullet(){
		return bullet;
	}

	/**
	 * Possible values are: "none", "round", "square", "triangleUp", "triangleDown", "triangleLeft", "triangleRight",
	 * "bubble", "diamond", "xError", "yError" and "custom".
	 *
	 * @param bullet
	 *            the bullet to set
	 */
	public void setBullet(String bullet){
		this.bullet = bullet;
	}

	//
	//	/**
	//	 * 获得 step graph only.
	//	 *
	//	 * @return the stepDirection
	//	 */
	//	public String getStepDirection(){
	//		return stepDirection;
	//	}
	//
	//	/**
	//	 * 设置 step graph only.
	//	 *
	//	 * @param stepDirection
	//	 *            the stepDirection to set
	//	 */
	//	public void setStepDirection(String stepDirection){
	//		this.stepDirection = stepDirection;
	//	}
	//
	//	/**
	//	 * 获得 position of value label.
	//	 *
	//	 * @return the labelPosition
	//	 */
	//	public String getLabelPosition(){
	//		return labelPosition;
	//	}
	//
	//	/**
	//	 * 设置 position of value label.
	//	 *
	//	 * @param labelPosition
	//	 *            the labelPosition to set
	//	 */
	//	public void setLabelPosition(String labelPosition){
	//		this.labelPosition = labelPosition;
	//	}
	//
	//	/**
	//	 * 获得 orientation of the gradient fills (only for "column" graph type).
	//	 *
	//	 * @return the gradientOrientation
	//	 */
	//	public String getGradientOrientation(){
	//		return gradientOrientation;
	//	}
	//
	//	/**
	//	 * 设置 orientation of the gradient fills (only for "column" graph type).
	//	 *
	//	 * @param gradientOrientation
	//	 *            the gradientOrientation to set
	//	 */
	//	public void setGradientOrientation(String gradientOrientation){
	//		this.gradientOrientation = gradientOrientation;
	//	}
	//
	//	/**
	//	 * 获得 specifies if the line graph should be placed behind column graphs.
	//	 *
	//	 * @return the behindColumns
	//	 */
	//	public Boolean getBehindColumns(){
	//		return behindColumns;
	//	}
	//
	//	/**
	//	 * 设置 specifies if the line graph should be placed behind column graphs.
	//	 *
	//	 * @param behindColumns
	//	 *            the behindColumns to set
	//	 */
	//	public void setBehindColumns(Boolean behindColumns){
	//		this.behindColumns = behindColumns;
	//	}
	//
	//	/**
	//	 * 获得 specifies whether the graph is hidden.
	//	 *
	//	 * @return the hidden
	//	 */
	//	public Boolean getHidden(){
	//		return hidden;
	//	}
	//
	//	/**
	//	 * 设置 specifies whether the graph is hidden.
	//	 *
	//	 * @param hidden
	//	 *            the hidden to set
	//	 */
	//	public void setHidden(Boolean hidden){
	//		this.hidden = hidden;
	//	}
	//
	//	/**
	//	 * 获得 if you set it to true, column chart will begin new stack.
	//	 *
	//	 * @return the newStack
	//	 */
	//	public Boolean getNewStack(){
	//		return newStack;
	//	}
	//
	//	/**
	//	 * 设置 if you set it to true, column chart will begin new stack.
	//	 *
	//	 * @param newStack
	//	 *            the newStack to set
	//	 */
	//	public void setNewStack(Boolean newStack){
	//		this.newStack = newStack;
	//	}
	//
	//	/**
	//	 * 获得 in case you want to have a step line graph without risers, you should set this to true.
	//	 *
	//	 * @return the noStepRisers
	//	 */
	//	public Boolean getNoStepRisers(){
	//		return noStepRisers;
	//	}
	//
	//	/**
	//	 * 设置 in case you want to have a step line graph without risers, you should set this to true.
	//	 *
	//	 * @param noStepRisers
	//	 *            the noStepRisers to set
	//	 */
	//	public void setNoStepRisers(Boolean noStepRisers){
	//		this.noStepRisers = noStepRisers;
	//	}
	//
	//	/**
	//	 * 获得 if this is set to true, candlesticks will be colored in a different manner - if current close is less than current open, the
	//	 * candlestick will be empty, otherwise - filled with color.
	//	 *
	//	 * @return the proCandlesticks
	//	 */
	//	public Boolean getProCandlesticks(){
	//		return proCandlesticks;
	//	}
	//
	//	/**
	//	 * 设置 if this is set to true, candlesticks will be colored in a different manner - if current close is less than current open, the
	//	 * candlestick will be empty, otherwise - filled with color.
	//	 *
	//	 * @param proCandlesticks
	//	 *            the proCandlesticks to set
	//	 */
	//	public void setProCandlesticks(Boolean proCandlesticks){
	//		this.proCandlesticks = proCandlesticks;
	//	}
	//
	/**
	 * 获得 if graph's type is column and labelText is set, graph hides labels which do not fit into the column's space.
	 *
	 * @return the showAllValueLabels
	 */
	public Boolean getShowAllValueLabels(){
		return showAllValueLabels;
	}

	/**
	 * 设置 if graph's type is column and labelText is set, graph hides labels which do not fit into the column's space.
	 *
	 * @param showAllValueLabels
	 *            the showAllValueLabels to set
	 */
	public void setShowAllValueLabels(Boolean showAllValueLabels){
		this.showAllValueLabels = showAllValueLabels;
	}

	//
	//	/**
	//	 * 获得 if you want mouse pointer to change to hand when hovering the graph, set this property to true.
	//	 *
	//	 * @return the showHandOnHover
	//	 */
	//	public Boolean getShowHandOnHover(){
	//		return showHandOnHover;
	//	}
	//
	//	/**
	//	 * 设置 if you want mouse pointer to change to hand when hovering the graph, set this property to true.
	//	 *
	//	 * @param showHandOnHover
	//	 *            the showHandOnHover to set
	//	 */
	//	public void setShowHandOnHover(Boolean showHandOnHover){
	//		this.showHandOnHover = showHandOnHover;
	//	}
	//
	//	/**
	//	 * 获得 it can only be used together with topRadius (when columns look like cylinders).
	//	 *
	//	 * @return the showOnAxis
	//	 */
	//	public Boolean getShowOnAxis(){
	//		return showOnAxis;
	//	}
	//
	//	/**
	//	 * 设置 it can only be used together with topRadius (when columns look like cylinders).
	//	 *
	//	 * @param showOnAxis
	//	 *            the showOnAxis to set
	//	 */
	//	public void setShowOnAxis(Boolean showOnAxis){
	//		this.showOnAxis = showOnAxis;
	//	}
	//
	//	/**
	//	 * 获得 if negativeLineColor and/or negativeFillColors are set and useNegativeColorIfDown is set to true (default is false), the line,
	//	 * step and column graphs will use these colors for lines, bullets or columns if previous value is bigger than current value.
	//	 *
	//	 * @return the useNegativeColorIfDown
	//	 */
	//	public Boolean getUseNegativeColorIfDown(){
	//		return useNegativeColorIfDown;
	//	}
	//
	//	/**
	//	 * 设置 if negativeLineColor and/or negativeFillColors are set and useNegativeColorIfDown is set to true (default is false), the line,
	//	 * step and column graphs will use these colors for lines, bullets or columns if previous value is bigger than current value.
	//	 *
	//	 * @param useNegativeColorIfDown
	//	 *            the useNegativeColorIfDown to set
	//	 */
	//	public void setUseNegativeColorIfDown(Boolean useNegativeColorIfDown){
	//		this.useNegativeColorIfDown = useNegativeColorIfDown;
	//	}
	//
	//	/**
	//	 * 获得 in case you want to place this graph's columns in front of other columns, set this to false.
	//	 *
	//	 * @return the clustered
	//	 */
	//	public Boolean getClustered(){
	//		return clustered;
	//	}
	//
	//	/**
	//	 * 设置 in case you want to place this graph's columns in front of other columns, set this to false.
	//	 *
	//	 * @param clustered
	//	 *            the clustered to set
	//	 */
	//	public void setClustered(Boolean clustered){
	//		this.clustered = clustered;
	//	}
	//
	//	/**
	//	 * 获得 specifies whether to connect data points if data is missing.
	//	 *
	//	 * @return the connect
	//	 */
	//	public Boolean getConnect(){
	//		return connect;
	//	}
	//
	//	/**
	//	 * 设置 specifies whether to connect data points if data is missing.
	//	 *
	//	 * @param connect
	//	 *            the connect to set
	//	 */
	//	public void setConnect(Boolean connect){
	//		this.connect = connect;
	//	}
	//
	//	/**
	//	 * 获得 whether to include this graph when calculating min and max value of the axis.
	//	 *
	//	 * @return the includeInMinMax
	//	 */
	//	public Boolean getIncludeInMinMax(){
	//		return includeInMinMax;
	//	}
	//
	//	/**
	//	 * 设置 whether to include this graph when calculating min and max value of the axis.
	//	 *
	//	 * @param includeInMinMax
	//	 *            the includeInMinMax to set
	//	 */
	//	public void setIncludeInMinMax(Boolean includeInMinMax){
	//		this.includeInMinMax = includeInMinMax;
	//	}
	//
	//	/**
	//	 * 获得 specifies whether the value balloon of this graph is shown when mouse is over data item or chart's indicator is over some series.
	//	 *
	//	 * @return the showBalloon
	//	 */
	//	public Boolean getShowBalloon(){
	//		return showBalloon;
	//	}
	//
	//	/**
	//	 * 设置 specifies whether the value balloon of this graph is shown when mouse is over data item or chart's indicator is over some series.
	//	 *
	//	 * @param showBalloon
	//	 *            the showBalloon to set
	//	 */
	//	public void setShowBalloon(Boolean showBalloon){
	//		this.showBalloon = showBalloon;
	//	}
	//
	//	/**
	//	 * 获得 if the value axis of this graph has stack types like "regular" or "100%" You can exclude this graph from stacking.
	//	 *
	//	 * @return the stackable
	//	 */
	//	public Boolean getStackable(){
	//		return stackable;
	//	}
	//
	//	/**
	//	 * 设置 if the value axis of this graph has stack types like "regular" or "100%" You can exclude this graph from stacking.
	//	 *
	//	 * @param stackable
	//	 *            the stackable to set
	//	 */
	//	public void setStackable(Boolean stackable){
	//		this.stackable = stackable;
	//	}
	//
	//	/**
	//	 * 获得 if you set it to false, the graph will not be hidden when user clicks on legend entry.
	//	 *
	//	 * @return the switchable
	//	 */
	//	public Boolean getSwitchable(){
	//		return switchable;
	//	}
	//
	//	/**
	//	 * 设置 if you set it to false, the graph will not be hidden when user clicks on legend entry.
	//	 *
	//	 * @param switchable
	//	 *            the switchable to set
	//	 */
	//	public void setSwitchable(Boolean switchable){
	//		this.switchable = switchable;
	//	}
	//
	//	/**
	//	 * 获得 specifies whether this graph should be shown in the Legend.
	//	 *
	//	 * @return the visibleInLegend
	//	 */
	//	public Boolean getVisibleInLegend(){
	//		return visibleInLegend;
	//	}
	//
	//	/**
	//	 * 设置 specifies whether this graph should be shown in the Legend.
	//	 *
	//	 * @param visibleInLegend
	//	 *            the visibleInLegend to set
	//	 */
	//	public void setVisibleInLegend(Boolean visibleInLegend){
	//		this.visibleInLegend = visibleInLegend;
	//	}
	//
	//	/**
	//	 * 获得 name of the alpha field in your dataProvider.
	//	 *
	//	 * @return the alphaField
	//	 */
	//	public String getAlphaField(){
	//		return alphaField;
	//	}
	//
	//	/**
	//	 * 设置 name of the alpha field in your dataProvider.
	//	 *
	//	 * @param alphaField
	//	 *            the alphaField to set
	//	 */
	//	public void setAlphaField(String alphaField){
	//		this.alphaField = alphaField;
	//	}
	//
	//	/**
	//	 * 获得 value balloon color.
	//	 *
	//	 * @return the balloonColor
	//	 */
	//	public String getBalloonColor(){
	//		return balloonColor;
	//	}
	//
	//	/**
	//	 * 设置 value balloon color.
	//	 *
	//	 * @param balloonColor
	//	 *            the balloonColor to set
	//	 */
	//	public void setBalloonColor(String balloonColor){
	//		this.balloonColor = balloonColor;
	//	}
	//
	//	/**
	//	 * 获得 bullet border color.
	//	 *
	//	 * @return the bulletBorderColor
	//	 */
	//	public String getBulletBorderColor(){
	//		return bulletBorderColor;
	//	}
	//
	//	/**
	//	 * 设置 bullet border color.
	//	 *
	//	 * @param bulletBorderColor
	//	 *            the bulletBorderColor to set
	//	 */
	//	public void setBulletBorderColor(String bulletBorderColor){
	//		this.bulletBorderColor = bulletBorderColor;
	//	}
	//
	//	/**
	//	 * 获得 bullet color.
	//	 *
	//	 * @return the bulletColor
	//	 */
	//	public String getBulletColor(){
	//		return bulletColor;
	//	}
	//
	//	/**
	//	 * 设置 bullet color.
	//	 *
	//	 * @param bulletColor
	//	 *            the bulletColor to set
	//	 */
	//	public void setBulletColor(String bulletColor){
	//		this.bulletColor = bulletColor;
	//	}
	//
	//	/**
	//	 * 获得 name of the bullet field in your dataProvider.
	//	 *
	//	 * @return the bulletField
	//	 */
	//	public String getBulletField(){
	//		return bulletField;
	//	}
	//
	//	/**
	//	 * 设置 name of the bullet field in your dataProvider.
	//	 *
	//	 * @param bulletField
	//	 *            the bulletField to set
	//	 */
	//	public void setBulletField(String bulletField){
	//		this.bulletField = bulletField;
	//	}
	//
	//	/**
	//	 * 获得 name of the bullet size field in your dataProvider.
	//	 *
	//	 * @return the bulletSizeField
	//	 */
	//	public String getBulletSizeField(){
	//		return bulletSizeField;
	//	}
	//
	//	/**
	//	 * 设置 name of the bullet size field in your dataProvider.
	//	 *
	//	 * @param bulletSizeField
	//	 *            the bulletSizeField to set
	//	 */
	//	public void setBulletSizeField(String bulletSizeField){
	//		this.bulletSizeField = bulletSizeField;
	//	}
	//
	//	/**
	//	 * 获得 name of the close field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @return the closeField
	//	 */
	//	public String getCloseField(){
	//		return closeField;
	//	}
	//
	//	/**
	//	 * 设置 name of the close field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @param closeField
	//	 *            the closeField to set
	//	 */
	//	public void setCloseField(String closeField){
	//		this.closeField = closeField;
	//	}
	//
	//	/**
	//	 * 获得 string.
	//	 *
	//	 * @return the string
	//	 */
	//	public String getString(){
	//		return String;
	//	}
	//
	//	/**
	//	 * 设置 string.
	//	 *
	//	 * @param string
	//	 *            the string to set
	//	 */
	//	public void setString(String string){
	//		String = string;
	//	}
	//
	//	/**
	//	 * 获得 name of the color field in your dataProvider.
	//	 *
	//	 * @return the colorField
	//	 */
	//	public String getColorField(){
	//		return colorField;
	//	}
	//
	//	/**
	//	 * 设置 name of the color field in your dataProvider.
	//	 *
	//	 * @param colorField
	//	 *            the colorField to set
	//	 */
	//	public void setColorField(String colorField){
	//		this.colorField = colorField;
	//	}
	//
	//	/**
	//	 * 获得 you can specify custom column width for each graph individually.
	//	 *
	//	 * @return the columnWidth
	//	 */
	//	public Number getColumnWidth(){
	//		return columnWidth;
	//	}
	//
	//	/**
	//	 * 设置 you can specify custom column width for each graph individually.
	//	 *
	//	 * @param columnWidth
	//	 *            the columnWidth to set
	//	 */
	//	public void setColumnWidth(Number columnWidth){
	//		this.columnWidth = columnWidth;
	//	}
	//
	//	/**
	//	 * 获得 path to the image of custom bullet.
	//	 *
	//	 * @return the customBullet
	//	 */
	//	public String getCustomBullet(){
	//		return customBullet;
	//	}
	//
	//	/**
	//	 * 设置 path to the image of custom bullet.
	//	 *
	//	 * @param customBullet
	//	 *            the customBullet to set
	//	 */
	//	public void setCustomBullet(String customBullet){
	//		this.customBullet = customBullet;
	//	}
	//
	//	/**
	//	 * 获得 name of the custom bullet field in your dataProvider.
	//	 *
	//	 * @return the customBulletField
	//	 */
	//	public String getCustomBulletField(){
	//		return customBulletField;
	//	}
	//
	//	/**
	//	 * 设置 name of the custom bullet field in your dataProvider.
	//	 *
	//	 * @param customBulletField
	//	 *            the customBulletField to set
	//	 */
	//	public void setCustomBulletField(String customBulletField){
	//		this.customBulletField = customBulletField;
	//	}
	//
	//	/**
	//	 * 获得 path to the image for legend marker.
	//	 *
	//	 * @return the customMarker
	//	 */
	//	public String getCustomMarker(){
	//		return customMarker;
	//	}
	//
	//	/**
	//	 * 设置 path to the image for legend marker.
	//	 *
	//	 * @param customMarker
	//	 *            the customMarker to set
	//	 */
	//	public void setCustomMarker(String customMarker){
	//		this.customMarker = customMarker;
	//	}
	//
	//	/**
	//	 * 获得 name of the dash length field in your dataProvider.
	//	 *
	//	 * @return the dashLengthField
	//	 */
	//	public String getDashLengthField(){
	//		return dashLengthField;
	//	}
	//
	//	/**
	//	 * 设置 name of the dash length field in your dataProvider.
	//	 *
	//	 * @param dashLengthField
	//	 *            the dashLengthField to set
	//	 */
	//	public void setDashLengthField(String dashLengthField){
	//		this.dashLengthField = dashLengthField;
	//	}
	//
	//	/**
	//	 * 获得 name of the description field in your dataProvider.
	//	 *
	//	 * @return the descriptionField
	//	 */
	//	public String getDescriptionField(){
	//		return descriptionField;
	//	}
	//
	//	/**
	//	 * 设置 name of the description field in your dataProvider.
	//	 *
	//	 * @param descriptionField
	//	 *            the descriptionField to set
	//	 */
	//	public void setDescriptionField(String descriptionField){
	//		this.descriptionField = descriptionField;
	//	}
	//
	//	/**
	//	 * 获得 name of error value field in your data provider.
	//	 *
	//	 * @return the errorField
	//	 */
	//	public String getErrorField(){
	//		return errorField;
	//	}
	//
	//	/**
	//	 * 设置 name of error value field in your data provider.
	//	 *
	//	 * @param errorField
	//	 *            the errorField to set
	//	 */
	//	public void setErrorField(String errorField){
	//		this.errorField = errorField;
	//	}
	//
	//	/**
	//	 * 获得 fill color.
	//	 *
	//	 * @return the fillColors
	//	 */
	//	public String getFillColors(){
	//		return fillColors;
	//	}
	//
	//	/**
	//	 * 设置 fill color.
	//	 *
	//	 * @param fillColors
	//	 *            the fillColors to set
	//	 */
	//	public void setFillColors(String fillColors){
	//		this.fillColors = fillColors;
	//	}
	//
	//	/**
	//	 * 获得 name of the fill colors field in your dataProvider.
	//	 *
	//	 * @return the fillColorsField
	//	 */
	//	public String getFillColorsField(){
	//		return fillColorsField;
	//	}
	//
	//	/**
	//	 * 设置 name of the fill colors field in your dataProvider.
	//	 *
	//	 * @param fillColorsField
	//	 *            the fillColorsField to set
	//	 */
	//	public void setFillColorsField(String fillColorsField){
	//		this.fillColorsField = fillColorsField;
	//	}
	//
	//	/**
	//	 * 获得 you can set another graph here and if fillAlpha is >0, the area from this graph to fillToGraph will be filled (instead of filling
	//	 * the area to the X axis).
	//	 *
	//	 * @return the fillToGraph
	//	 */
	//	public AmGraph getFillToGraph(){
	//		return fillToGraph;
	//	}
	//
	//	/**
	//	 * 设置 you can set another graph here and if fillAlpha is >0, the area from this graph to fillToGraph will be filled (instead of filling
	//	 * the area to the X axis).
	//	 *
	//	 * @param fillToGraph
	//	 *            the fillToGraph to set
	//	 */
	//	public void setFillToGraph(AmGraph fillToGraph){
	//		this.fillToGraph = fillToGraph;
	//	}
	//
	//	/**
	//	 * 获得 column width in pixels.
	//	 *
	//	 * @return the fixedColumnWidth
	//	 */
	//	public Number getFixedColumnWidth(){
	//		return fixedColumnWidth;
	//	}
	//
	//	/**
	//	 * 设置 column width in pixels.
	//	 *
	//	 * @param fixedColumnWidth
	//	 *            the fixedColumnWidth to set
	//	 */
	//	public void setFixedColumnWidth(Number fixedColumnWidth){
	//		this.fixedColumnWidth = fixedColumnWidth;
	//	}
	//
	//	/**
	//	 * 获得 size of value labels text.
	//	 *
	//	 * @return the fontSize
	//	 */
	//	public Number getFontSize(){
	//		return fontSize;
	//	}
	//
	//	/**
	//	 * 设置 size of value labels text.
	//	 *
	//	 * @param fontSize
	//	 *            the fontSize to set
	//	 */
	//	public void setFontSize(Number fontSize){
	//		this.fontSize = fontSize;
	//	}
	//
	//	/**
	//	 * 获得 name of the high field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @return the highField
	//	 */
	//	public String getHighField(){
	//		return highField;
	//	}
	//
	//	/**
	//	 * 设置 name of the high field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @param highField
	//	 *            the highField to set
	//	 */
	//	public void setHighField(String highField){
	//		this.highField = highField;
	//	}
	//
	//	/**
	//	 * 获得 unique id of a graph.
	//	 *
	//	 * @return the id
	//	 */
	//	public String getId(){
	//		return id;
	//	}
	//
	//	/**
	//	 * 设置 unique id of a graph.
	//	 *
	//	 * @param id
	//	 *            the id to set
	//	 */
	//	public void setId(String id){
	//		this.id = id;
	//	}
	//
	//	/**
	//	 * 获得 name of label color field in data provider.
	//	 *
	//	 * @return the labelColorField
	//	 */
	//	public String getLabelColorField(){
	//		return labelColorField;
	//	}
	//
	//	/**
	//	 * 设置 name of label color field in data provider.
	//	 *
	//	 * @param labelColorField
	//	 *            the labelColorField to set
	//	 */
	//	public void setLabelColorField(String labelColorField){
	//		this.labelColorField = labelColorField;
	//	}
	//
	/**
	 * 获得 value label text.You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]].
	 *
	 * @return the labelText
	 */
	public String getLabelText(){
		return labelText;
	}

	/**
	 * 设置 value label text.You can use tags like [[value]], [[description]], [[percents]], [[open]], [[category]].
	 *
	 * @param labelText
	 *            the labelText to set
	 */
	public void setLabelText(String labelText){
		this.labelText = labelText;
	}

	//	/**
	//	 * 获得 legend marker opacity.
	//	 *
	//	 * @return the legendAlpha
	//	 */
	//	public Number getLegendAlpha(){
	//		return legendAlpha;
	//	}
	//
	//	/**
	//	 * 设置 legend marker opacity.
	//	 *
	//	 * @param legendAlpha
	//	 *            the legendAlpha to set
	//	 */
	//	public void setLegendAlpha(Number legendAlpha){
	//		this.legendAlpha = legendAlpha;
	//	}
	//
	//	/**
	//	 * 获得 legend marker color.
	//	 *
	//	 * @return the legendColor
	//	 */
	//	public String getLegendColor(){
	//		return legendColor;
	//	}
	//
	//	/**
	//	 * 设置 legend marker color.
	//	 *
	//	 * @param legendColor
	//	 *            the legendColor to set
	//	 */
	//	public void setLegendColor(String legendColor){
	//		this.legendColor = legendColor;
	//	}
	//
	//	/**
	//	 * 获得 the text which will be displayed in the value portion of the legend when user is not hovering above any data point.
	//	 *
	//	 * @return the legendPeriodValueText
	//	 */
	//	public String getLegendPeriodValueText(){
	//		return legendPeriodValueText;
	//	}
	//
	//	/**
	//	 * 设置 the text which will be displayed in the value portion of the legend when user is not hovering above any data point.
	//	 *
	//	 * @param legendPeriodValueText
	//	 *            the legendPeriodValueText to set
	//	 */
	//	public void setLegendPeriodValueText(String legendPeriodValueText){
	//		this.legendPeriodValueText = legendPeriodValueText;
	//	}
	//
	//	/**
	//	 * 获得 legend value text.
	//	 *
	//	 * @return the legendValueText
	//	 */
	//	public String getLegendValueText(){
	//		return legendValueText;
	//	}
	//
	//	/**
	//	 * 设置 legend value text.
	//	 *
	//	 * @param legendValueText
	//	 *            the legendValueText to set
	//	 */
	//	public void setLegendValueText(String legendValueText){
	//		this.legendValueText = legendValueText;
	//	}
	//
	//	/**
	//	 * 获得 color of the line (or column border).
	//	 *
	//	 * @return the lineColor
	//	 */
	//	public String getLineColor(){
	//		return lineColor;
	//	}
	//
	//	/**
	//	 * 设置 color of the line (or column border).
	//	 *
	//	 * @param lineColor
	//	 *            the lineColor to set
	//	 */
	//	public void setLineColor(String lineColor){
	//		this.lineColor = lineColor;
	//	}
	//
	//	/**
	//	 * 获得 name of the line color field in your dataProvider.
	//	 *
	//	 * @return the lineColorField
	//	 */
	//	public String getLineColorField(){
	//		return lineColorField;
	//	}
	//
	//	/**
	//	 * 设置 name of the line color field in your dataProvider.
	//	 *
	//	 * @param lineColorField
	//	 *            the lineColorField to set
	//	 */
	//	public void setLineColorField(String lineColorField){
	//		this.lineColorField = lineColorField;
	//	}
	//
	//	/**
	//	 * 获得 name of the low field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @return the lowField
	//	 */
	//	public String getLowField(){
	//		return lowField;
	//	}
	//
	//	/**
	//	 * 设置 name of the low field (used by candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @param lowField
	//	 *            the lowField to set
	//	 */
	//	public void setLowField(String lowField){
	//		this.lowField = lowField;
	//	}
	//
	//	/**
	//	 * 获得 legend marker type.
	//	 *
	//	 * @return the markerType
	//	 */
	//	public String getMarkerType(){
	//		return markerType;
	//	}
	//
	//	/**
	//	 * 设置 legend marker type.
	//	 *
	//	 * @param markerType
	//	 *            the markerType to set
	//	 */
	//	public void setMarkerType(String markerType){
	//		this.markerType = markerType;
	//	}
	//
	//	/**
	//	 * 获得 fill opacity of negative part of the graph.
	//	 *
	//	 * @return the negativeFillAlphas
	//	 */
	//	public Number getNegativeFillAlphas(){
	//		return negativeFillAlphas;
	//	}
	//
	//	/**
	//	 * 设置 fill opacity of negative part of the graph.
	//	 *
	//	 * @param negativeFillAlphas
	//	 *            the negativeFillAlphas to set
	//	 */
	//	public void setNegativeFillAlphas(Number negativeFillAlphas){
	//		this.negativeFillAlphas = negativeFillAlphas;
	//	}
	//
	//	/**
	//	 * 获得 fill color of negative part of the graph.
	//	 *
	//	 * @return the negativeFillColors
	//	 */
	//	public String getNegativeFillColors(){
	//		return negativeFillColors;
	//	}
	//
	//	/**
	//	 * 设置 fill color of negative part of the graph.
	//	 *
	//	 * @param negativeFillColors
	//	 *            the negativeFillColors to set
	//	 */
	//	public void setNegativeFillColors(String negativeFillColors){
	//		this.negativeFillColors = negativeFillColors;
	//	}
	//
	//	/**
	//	 * 获得 color of the line (or column) when the values are negative.
	//	 *
	//	 * @return the negativeLineColor
	//	 */
	//	public String getNegativeLineColor(){
	//		return negativeLineColor;
	//	}
	//
	//	/**
	//	 * 设置 color of the line (or column) when the values are negative.
	//	 *
	//	 * @param negativeLineColor
	//	 *            the negativeLineColor to set
	//	 */
	//	public void setNegativeLineColor(String negativeLineColor){
	//		this.negativeLineColor = negativeLineColor;
	//	}
	//
	//	/**
	//	 * 获得 name of the open field (used by floating columns, candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @return the openField
	//	 */
	//	public String getOpenField(){
	//		return openField;
	//	}
	//
	//	/**
	//	 * 设置 name of the open field (used by floating columns, candlesticks and ohlc) in your dataProvider.
	//	 *
	//	 * @param openField
	//	 *            the openField to set
	//	 */
	//	public void setOpenField(String openField){
	//		this.openField = openField;
	//	}
	//
	//	/**
	//	 * 获得 value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values.
	//	 *
	//	 * @return the pattern
	//	 */
	//	public Object getPattern(){
	//		return pattern;
	//	}
	//
	//	/**
	//	 * 设置 value of pattern should be object with url, width, height of an image, optionally it might have x, y, randomX and randomY values.
	//	 *
	//	 * @param pattern
	//	 *            the pattern to set
	//	 */
	//	public void setPattern(Object pattern){
	//		this.pattern = pattern;
	//	}
	//
	//	/**
	//	 * 获得 field name in your data provider which holds pattern information.
	//	 *
	//	 * @return the patternField
	//	 */
	//	public String getPatternField(){
	//		return patternField;
	//	}
	//
	//	/**
	//	 * 设置 field name in your data provider which holds pattern information.
	//	 *
	//	 * @param patternField
	//	 *            the patternField to set
	//	 */
	//	public void setPatternField(String patternField){
	//		this.patternField = patternField;
	//	}
	//
	//	/**
	//	 * 获得 precision of values.
	//	 *
	//	 * @return the precision
	//	 */
	//	public Number getPrecision(){
	//		return precision;
	//	}
	//
	//	/**
	//	 * 设置 precision of values.
	//	 *
	//	 * @param precision
	//	 *            the precision to set
	//	 */
	//	public void setPrecision(Number precision){
	//		this.precision = precision;
	//	}
	//
	/**
	 * 获得 graph title.
	 *
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

	/**
	 * 设置 graph title.
	 *
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title){
		this.title = title;
	}

	//
	//	/**
	//	 * 获得 if you set this to 1, columns will become cylinders (must set depth3D and angle properties of a chart to >0 values in order this
	//	 * to be visible).
	//	 *
	//	 * @return the topRadius
	//	 */
	//	public Number getTopRadius(){
	//		return topRadius;
	//	}
	//
	//	/**
	//	 * 设置 if you set this to 1, columns will become cylinders (must set depth3D and angle properties of a chart to >0 values in order this
	//	 * to be visible).
	//	 *
	//	 * @param topRadius
	//	 *            the topRadius to set
	//	 */
	//	public void setTopRadius(Number topRadius){
	//		this.topRadius = topRadius;
	//	}
	//
	//	/**
	//	 * 获得 name of the url field in your dataProvider.
	//	 *
	//	 * @return the urlField
	//	 */
	//	public String getUrlField(){
	//		return urlField;
	//	}
	//
	//	/**
	//	 * 设置 name of the url field in your dataProvider.
	//	 *
	//	 * @param urlField
	//	 *            the urlField to set
	//	 */
	//	public void setUrlField(String urlField){
	//		this.urlField = urlField;
	//	}
	//
	//	/**
	//	 * 获得 target to open URLs in, i.
	//	 *
	//	 * @return the urlTarget
	//	 */
	//	public String getUrlTarget(){
	//		return urlTarget;
	//	}
	//
	//	/**
	//	 * 设置 target to open URLs in, i.
	//	 *
	//	 * @param urlTarget
	//	 *            the urlTarget to set
	//	 */
	//	public void setUrlTarget(String urlTarget){
	//		this.urlTarget = urlTarget;
	//	}
	//
	/**
	 * 获得 name of the value field in your dataProvider.
	 *
	 * @return the valueField
	 */
	public String getValueField(){
		return valueField;
	}

	/**
	 * 设置 name of the value field in your dataProvider.
	 *
	 * @param valueField
	 *            the valueField to set
	 */
	public void setValueField(String valueField){
		this.valueField = valueField;
	}
	//
	//	/**
	//	 * Getx field.
	//	 *
	//	 * @return the xField
	//	 */
	//	public String getxField(){
	//		return xField;
	//	}
	//
	//	/**
	//	 * Setx field.
	//	 *
	//	 * @param xField
	//	 *            the xField to set
	//	 */
	//	public void setxField(String xField){
	//		this.xField = xField;
	//	}
	//
	//	/**
	//	 * Gety field.
	//	 *
	//	 * @return the yField
	//	 */
	//	public String getyField(){
	//		return yField;
	//	}
	//
	//	/**
	//	 * Sety field.
	//	 *
	//	 * @param yField
	//	 *            the yField to set
	//	 */
	//	public void setyField(String yField){
	//		this.yField = yField;
	//	}

}
