/**
 * 柱状图
 * 
 */
function createAmSerialChart(feilongChart) {

    var chartDivId = feilongChart.chartDivId;
    var titleField = feilongChart.titleField;
    var valueField = feilongChart.valueField;
    var chartTitle = feilongChart.chartTitle;

    // SERIAL CHART
    var chart = new AmCharts.AmSerialChart();
    chart.dataProvider = feilongChart.dataProvider;
    chart.categoryField = feilongChart.titleField;
    chart.startDuration = 1;

    var balloon = chart.balloon;
    // set properties
    balloon.adjustBorderColor = true;
    balloon.color = "#000000";
    balloon.cornerRadius = 5;
    balloon.fillColor = "#FFFFFF";

    chart.plotAreaFillAlphas = 1;

    chart.rotate = true; // If you set this to true, the chart will be
    // rotated
    // by 90 degrees (the columns will become bars).
    // AXES
    // category
    var categoryAxis = chart.categoryAxis;
    categoryAxis.labelRotation = 30;// Rotation angle of a label. Only
    // horizontal axis' values can be rotated.
    // If you set this for vertical axis, the
    // setting will be ignored.
    categoryAxis.gridPosition = "start";

    // value
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.dashLength = 5;
    // valueAxis.title = chartTitle;
    valueAxis.includeGuidesInMinMax = true;// Specifies whether guide values
    // should be included when
    // calculating min and max of the
    // axis.
    valueAxis.includeHidden = false;// If true, the axis will include hidden
    // graphs when calculating min and max
    // values.
    // valueAxis.inside = true;//Specifies whether values should be placed
    // inside or outside plot area.
    valueAxis.axisAlpha = 0;

    // **********************************************************************************
    var guide = new AmCharts.Guide();
    guide.value = 63;
    guide.lineColor = "#aa0000";
    guide.dashLength = 4;
    guide.label = "batch1";
    guide.inside = true;
    guide.lineAlpha = 4;
    guide.date = new Date(2011, 4, 25);

    var guide2 = new AmCharts.Guide();
    guide2.value = 140;
    guide2.lineColor = "#FF0F00";
    guide2.dashLength = 4;
    guide2.label = "batch2";
    guide2.inside = true;
    guide2.lineAlpha = 4;
    guide2.date = new Date(2011, 5, 25);

    var guide3 = new AmCharts.Guide();
    guide3.value = 180;
    guide3.lineColor = "#FF0F00";
    guide3.dashLength = 6;
    guide3.label = "batch3";
    guide3.inside = true;
    guide3.lineAlpha = 4;
    guide3.date = new Date(2011, 6, 25);

    var guide4 = new AmCharts.Guide();
    guide4.value = 220;
    guide4.lineColor = "#2A0CD0";
    guide4.dashLength = 8;
    guide4.label = "batch4";
    guide4.inside = true;
    guide4.lineAlpha = 4;
    guide4.date = new Date(2011, 8, 25);

    var guide5 = new AmCharts.Guide();
    guide5.value = 260;
    guide5.lineColor = "#CD0D74";
    guide5.dashLength = 8;
    guide5.label = "batch5";
    guide5.inside = true;
    guide5.lineAlpha = 40;
    guide5.above = false;
    guide5.date = new Date(2012, 6, 25);

    // valueAxis.addGuide(guide);
    // valueAxis.addGuide(guide2);
    // valueAxis.addGuide(guide3);
    // valueAxis.addGuide(guide4);
    // valueAxis.addGuide(guide5);

    chart.addValueAxis(valueAxis);

    // GRAPH
    var graph = new AmCharts.AmGraph();
    graph.valueField = valueField;
    // graph.colorField = "color";
    graph.balloonText = "[[category]]: [[value]]";// You can use tags like
    // [[value]],
    // [[description]],
    // [[percents]], [[open]],
    // [[category]]
    graph.labelText = "[[value]]";// You can use tags like [[value]],
    // [[description]], [[percents]], [[open]],
    // [[category]]

    // graph.customBullet = "/Demo/amcharts/images/star.gif";//Path to the image
    // of custom bullet.
    // graph.customBulletField = "color";//Path to the image of custom bullet.
    graph.type = "column";// Type of the graph. Possible values are: "line",
    // "column", "step", "smoothedLine", "candlestick",
    // "ohlc". XY and Radar charts can only display
    // "line" type graphs.
    // graph.bullet = "round";//Type of the bullets. Possible values are:
    // "none", "round", "square", "triangleUp", "triangleDown", "bubble",
    // "custom".
    // graph.dashLength =20;
    // graph.descriptionField ="color";
    // graph.labelColorField ="color";
    graph.showAllValueLabels = true;
    // graph.legendValueText ="color";
    // graph.includeInMinMax =false;
    graph.lineAlpha = 0;
    graph.fillAlphas = 1;
    // graph.title = "wulala";
    chart.addGraph(graph);

    // ***************************************************************************

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.align = "center";

    legend.position = "right";
    // legend.reversedOrder="true";

    legend.valueAlign = "right";
    legend.spacing = "1";

    // chart.addLegend(legend);

    addChartBorder(chart);
    addChartTitle(chart, chartTitle);
    chart.write(chartDivId);

}

/**
 * 饼图
 * 
 * @param chartDivId
 * @param dataProvider
 * @param titleField
 * @param valueField
 * @param chartTitle
 */
function createAmPieChart(feilongChart) {

    // PIE CHART
    var chart = new AmCharts.AmPieChart();
    chart.dataProvider = feilongChart.dataProvider;

    chart.titleField = feilongChart.titleField;
    chart.valueField = feilongChart.valueField;

    // chart.colorField = feilongChart.colorField;

    chart.outlineColor = "#FFFFFF";
    chart.outlineAlpha = 0.8;
    chart.outlineThickness = 2;
    // this makes the chart 3D
    chart.depth3D = 12;
    chart.angle = 30;

    // chart.colors = [ "#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#2A0CD0",
    // "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333",
    // "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25" ];

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.align = "center";

    legend.position = "right";
    // legend.reversedOrder="true";
    legend.valueAlign = "right";
    legend.spacing = "1";

    // legend.title="2";
    chart.addLegend(legend);

    addChartBorder(chart);
    addChartTitle(chart, feilongChart.chartTitle);

    // WRITE
    chart.write(feilongChart.chartDivId);
}

/**
 * 雷达图
 */
function createAmRadarChart(feilongChart) {

    var chartDivId = feilongChart.chartDivId;
    var titleField = feilongChart.titleField;
    var valueField = feilongChart.valueField;
    var chartTitle = feilongChart.chartTitle;
    var dataProvider = feilongChart.dataProvider;

    // RADAR CHART
    var chart = new AmCharts.AmRadarChart();
    chart.dataProvider = dataProvider;
    chart.categoryField = titleField;
    chart.startDuration = 2;

    // VALUE AXIS
    var valueAxis = new AmCharts.ValueAxis();
    valueAxis.axisAlpha = 0.15;
    valueAxis.minimum = 4.3;
    valueAxis.maximum = 5;
    valueAxis.dashLength = 3;
    valueAxis.axisTitleOffset = 20;
    valueAxis.gridCount = 5;
    chart.addValueAxis(valueAxis);

    // GRAPH
    var graph = new AmCharts.AmGraph();
    graph.valueField = valueField;
    graph.bullet = "round";
    graph.balloonText = "[[value]]"
    chart.addGraph(graph);

    addChartBorder(chart);
    addChartTitle(chart, chartTitle);

    // WRITE
    chart.write(chartDivId);
}

/**
 * 添加title
 * 
 * @param chart
 *                chart
 * @param chartTitle
 *                标题
 */
function addChartTitle(chart, chartTitle) {
    // addTitle(text, size, color, alpha, bold)
    chart.addTitle(chartTitle, 18, "#000000", 0.8, false);
}

/**
 * 添加边框
 * 
 * @param chart
 *                chart
 */
function addChartBorder(chart) {

    chart.borderAlpha = 0.2;// 不透明图表的边框。取值范围为0 - 1。
    chart.borderColor = "#666666";// Color of chart's border. You should set
    // borderAlpha >0 in order border to be
    // visible. We recommend setting border
    // color directly on a chart's DIV instead
    // of using this property.
    chart.fontFamily = "微软雅黑";// Font family. "Segoe Print"
    // chart.fontSize = "4";//Font size

}
