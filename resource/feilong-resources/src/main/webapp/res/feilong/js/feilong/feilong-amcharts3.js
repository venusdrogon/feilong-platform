/**
 * 折线图
 */
function createAmSerialChart2(chartDivId, avgScoreChainChartConfig) {

    console.log("%o%o", avgScoreChainChartConfig.dataProvider,
	    avgScoreChainChartConfig.graphs.length);

    if (null != avgScoreChainChartConfig
	    && avgScoreChainChartConfig.graphs.length > 1) {

	var chartConfig = $.extend(true, avgScoreChainChartConfig, {

	    balloon : {
		adjustBorderColor : true,
		color : "#000000",
		cornerRadius : 5,
		fillColor : "#FFFFFF"
	    },

	    categoryAxis : {
		// Rotation angle of a label. Only
		// horizontal axis' values can be rotated.
		// If you set this for vertical axis, the
		// setting will be ignored.
		labelRotation : 30,
		gridPosition : "start"
	    },
	    valueAxes : [ {
		title : "平均分",
		// dashLength : 2,
		includeGuidesInMinMax : true, // Specifies whether guide
		// values
		// should be included when
		// calculating min and max of the
		// axis.
		maximum : 5,
		includeHidden : false,// If true, the axis will include
		// hidden
		// graphs when calculating min and max
		// values.
		// valueAxis.inside = true;//Specifies whether values should be
		// placed
		// inside or outside plot area.
		axisAlpha : 0,
	    } ]
	});

	console.log("chartConfig:%o", chartConfig);

	// ****************************************************************************
	var chart = AmCharts.makeChart(chartDivId, chartConfig);
    } else {
	$("#" + chartDivId).html("暂无数据").css({
	    width : "200px",
	    height : "20px",
	// border:"1px solid #000"
	});
    }

}
/**
 * 折线图
 */
function createAmSerialChart1(feilongChart) {

    console.log("createAmSerialChart1:%o", feilongChart.dataProvider);

    // ****************************************************************************
    var chartConfig = {
	"type" : "serial",
	"titleField" : feilongChart.titleField,
	"valueField" : feilongChart.valueField,
	"dataProvider" : feilongChart.dataProvider,
	categoryField : feilongChart.titleField,

	// SERIAL CHART
	startDuration : 1,

	plotAreaFillAlphas : 1,

	borderAlpha : 0.2,// 不透明图表的边框。取值范围为0 - 1。
	borderColor : "#666666",// Color of chart's border. You should set
	// borderAlpha >0 in order border to be
	// visible. We recommend setting border
	// color directly on a chart's DIV instead
	// of using this property.
	fontFamily : "微软雅黑",// Font family. "Segoe Print"
	// chart.fontSize = "4";//Font size
	// addChartTitle(chart, feilongChart.chartTitle);

	graphs : [ {

	    // GRAPH
	    // var graph = new AmCharts.AmGraph();
	    // graph.valueField = valueField;
	    //
	    // var colorField = "color";
	    //
	    // var dataProviderFirstItem = (chart.dataProvider)[0];
	    //
	    // var dataProviderFirstItemColorValue =
	    // dataProviderFirstItem[colorField];
	    //
	    // console.log("dataProvider first item:%o",
	    // dataProviderFirstItemColorValue);
	    //
	    // if (null != dataProviderFirstItemColorValue
	    // && "" != dataProviderFirstItemColorValue) {
	    // console.log("graph.colorField:%o", graph.colorField);
	    // graph.colorField = colorField;
	    // }

	    // graph.customBullet = "/Demo/amcharts/images/star.gif";//Path to
	    // the image
	    // of custom bullet.
	    // graph.customBulletField = "color";//Path to the image of custom
	    // bullet.

	    // graph.dashLength =20;
	    // graph.descriptionField ="color";
	    // graph.labelColorField ="color";
	    // graph.legendValueText ="color";
	    // graph.includeInMinMax =false;

	    title : "1021SQL优化(18人平均评分)",
	    // lineColor : "#00CC00",
	    valueField : "value",
	    // dashLength : 3,
	    bullet : "round",// Type of the bullets. Possible values are:
	    // "none", "round", "square", "triangleUp", "triangleDown",
	    // "bubble",
	    // "custom".
	    showAllValueLabels : true,
	    lineAlpha : 1,
	    fillAlphas : 0,
	    labelText : "[[value]]",// You can use tags like [[value]],
	    // [[description]], [[percents]], [[open]],
	    // [[category]]
	    type : "line",// Type of the graph. Possible values are:
	    // "line",
	    // "column", "step", "smoothedLine", "candlestick",
	    // "ohlc". XY and Radar charts can only display
	    // "line" type graphs.
	    balloonText : "[[category]]: [[value]]"// You can use tags like
	// [[value]],
	// [[description]],
	// [[percents]], [[open]],
	// [[category]]
	}, {
	    title : "0926SQL优化(29人平均评分)",
	    // lineColor : "#00CC00",
	    valueField : "value3",
	    // dashLength : 3,
	    bullet : "round",// Type of the bullets. Possible values are:
	    // "none", "round", "square", "triangleUp", "triangleDown",
	    // "bubble",
	    // "custom".
	    showAllValueLabels : true,
	    lineAlpha : 1,
	    fillAlphas : 0,
	    // labelText : "[[value]]",// You can use tags like [[value]],
	    // [[description]], [[percents]], [[open]],
	    // [[category]]
	    type : "line",// Type of the graph. Possible values are:
	    // "line",
	    // "column", "step", "smoothedLine", "candlestick",
	    // "ohlc". XY and Radar charts can only display
	    // "line" type graphs.
	    balloonText : "[[category]]: [[value]]" // You can use tags like
	// [[value]],
	// [[description]],
	// [[percents]], [[open]],
	// [[category]]

	} ],

	legend : {
	    align : "center",
	    position : "bottom",

	    // reversedOrder:true,
	    valueAlign : "right",
	    spacing : "1"
	},

	balloon : {
	    adjustBorderColor : true,
	    color : "#000000",
	    cornerRadius : 5,
	    fillColor : "#FFFFFF"
	},

	// addTitle(text, size, color, alpha, bold)

	titles : [ {
	    "text" : feilongChart.chartTitle,
	    "size" : 18,
	    "color" : "#000000",
	    "alpha" : 0.8,
	    "bold" : false
	} ],

	categoryAxis : {

	    // Rotation angle of a label. Only
	    // horizontal axis' values can be rotated.
	    // If you set this for vertical axis, the
	    // setting will be ignored.
	    labelRotation : 30,
	    gridPosition : "start"
	},
	valueAxes : [ {
	    title : "平均分",
	    // dashLength : 2,
	    includeGuidesInMinMax : true, // Specifies whether guide
	    // values
	    // should be included when
	    // calculating min and max of the
	    // axis.
	    maximum : 5,
	    includeHidden : false,// If true, the axis will include
	    // hidden
	    // graphs when calculating min and max
	    // values.
	    // valueAxis.inside = true;//Specifies whether values should be
	    // placed
	    // inside or outside plot area.
	    axisAlpha : 0,

	// guides : [ {
	// value : 4.5,
	// lineColor : "#aa0000",
	// dashLength : 4,
	// label : "batch1",
	// inside : true,
	// lineAlpha : 1,
	// date : new Date(2011, 4, 25),
	// }, {
	// value : 5,
	// lineColor : "#FF0F00",
	// dashLength : 4,
	// label : "batch2",
	// inside : true,
	// lineAlpha : 1,
	// date : new Date(2011, 5, 25),
	// } ]
	} ]

    // chart.rotate = true; // If you set this to true, the chart will
    // be
    // rotated
    // by 90 degrees (the columns will become bars).
    // AXES
    // category
    };
    var chart = AmCharts.makeChart(feilongChart.chartDivId, chartConfig);

}
/**
 * 柱状图
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

    var colorField = "color";

    var dataProviderFirstItem = (chart.dataProvider)[0];

    var dataProviderFirstItemColorValue = dataProviderFirstItem[colorField];

    console.log("dataProvider first item:%o", dataProviderFirstItemColorValue);

    if (null != dataProviderFirstItemColorValue
	    && "" != dataProviderFirstItemColorValue) {
	console.log("graph.colorField:%o", graph.colorField);
	graph.colorField = colorField;
    }
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
 */
function createAmPieChart(feilongChart) {
    console.log("feilongChart.chartTitle:%o,feilongChart:%o",
	    feilongChart.chartTitle, feilongChart);

    // // chart.colors = [ "#B0DE09", "#04D215", "#0D8ECF", "#0D52D1",
    // "#2A0CD0",
    // // "#8A0CCF", "#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333",
    // // "#000000", "#57032A", "#CA9726", "#990000", "#4B0C25" ];
    var colorField = "color";

    var dataProviderFirstItem = (feilongChart.dataProvider)[0];

    var dataProviderFirstItemColorValue = dataProviderFirstItem[colorField];

    console.log("chart title:%o,dataProvider first item color value:%o",
	    feilongChart.chartTitle, dataProviderFirstItemColorValue);

    var chartConfig = {
	"type" : "pie",
	"titleField" : feilongChart.titleField,
	"valueField" : feilongChart.valueField,
	"dataProvider" : feilongChart.dataProvider,
	outlineColor : "#FFFFFF",
	outlineAlpha : 0.8,

	outlineThickness : 2,

	// // this makes the chart 3D
	depth3D : 12,
	angle : 30,

	legend : {
	    align : "center",
	    position : "right",

	    // reversedOrder:true,
	    valueAlign : "right",
	    spacing : "1"
	},

	borderAlpha : 0.2,// 不透明图表的边框。取值范围为0 - 1。
	borderColor : "#666666",// Color of chart's border. You should set
	// borderAlpha >0 in order border to be
	// visible. We recommend setting border
	// color directly on a chart's DIV instead
	// of using this property.
	fontFamily : "微软雅黑",// Font family. "Segoe Print"
	// chart.fontSize = "4";//Font size
	// addChartTitle(chart, feilongChart.chartTitle);

	// addTitle(text, size, color, alpha, bold)
	titles : [ {
	    "text" : feilongChart.chartTitle,
	    "size" : 18,
	    "color" : "#000000",
	    "alpha" : 0.8,
	    "bold" : false
	} ],
    };

    if (null != dataProviderFirstItemColorValue
	    && "" != dataProviderFirstItemColorValue) {
	console.log("chart.colorField:%o", colorField);

	$.extend(true, chartConfig, {
	    colorField : "color"
	});
    }

    var chart = AmCharts.makeChart(feilongChart.chartDivId, chartConfig);
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
