<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>AJ1 QS订单示意图(2013-07-28 12:00)</title>
<script type="text/javascript">
	var chart;

	var chartData =

	[ {
		"cancelCount" : 0,
		"createCount" : 1,
		"minute" : "00:20"
	}, {
		"cancelCount" : 0,
		"createCount" : 2,
		"minute" : "00:21"
	}, {
		"cancelCount" : 0,
		"createCount" : 6,
		"minute" : "00:22"
	}, {
		"cancelCount" : 0,
		"createCount" : 8,
		"minute" : "00:23"
	}, {
		"cancelCount" : 0,
		"createCount" : 10,
		"minute" : "00:24"
	}, {
		"cancelCount" : 0,
		"createCount" : 18,
		"minute" : "00:25"
	}, {
		"cancelCount" : 0,
		"createCount" : 13,
		"minute" : "00:26"
	}, {
		"cancelCount" : 0,
		"createCount" : 9,
		"minute" : "00:27"
	}, {
		"cancelCount" : 0,
		"createCount" : 10,
		"minute" : "00:28"
	}, {
		"cancelCount" : 0,
		"createCount" : 12,
		"minute" : "00:29"
	}, {
		"cancelCount" : 0,
		"createCount" : 17,
		"minute" : "00:30"
	}, {
		"cancelCount" : 0,
		"createCount" : 11,
		"minute" : "00:31"
	}, {
		"cancelCount" : 0,
		"createCount" : 25,
		"minute" : "00:32"
	}, {
		"cancelCount" : 0,
		"createCount" : 24,
		"minute" : "00:33"
	}, {
		"cancelCount" : 0,
		"createCount" : 16,
		"minute" : "00:34"
	}, {
		"cancelCount" : 0,
		"createCount" : 9,
		"minute" : "00:35"
	}, {
		"cancelCount" : 0,
		"createCount" : 6,
		"minute" : "00:36"
	}, {
		"cancelCount" : 0,
		"createCount" : 16,
		"minute" : "00:37"
	}, {
		"cancelCount" : 0,
		"createCount" : 9,
		"minute" : "00:38"
	}, {
		"cancelCount" : 0,
		"createCount" : 10,
		"minute" : "00:39"
	}, {
		"cancelCount" : 0,
		"createCount" : 4,
		"minute" : "00:40"
	}, {
		"cancelCount" : 0,
		"createCount" : 12,
		"minute" : "00:41"
	}, {
		"cancelCount" : 0,
		"createCount" : 10,
		"minute" : "00:42"
	}, {
		"cancelCount" : 0,
		"createCount" : 7,
		"minute" : "00:43"
	}, {
		"cancelCount" : 0,
		"createCount" : 1,
		"minute" : "00:44"
	}, {
		"cancelCount" : 0,
		"createCount" : 5,
		"minute" : "00:45"
	}, {
		"cancelCount" : 0,
		"createCount" : 8,
		"minute" : "00:46"
	}, {
		"cancelCount" : 0,
		"createCount" : 4,
		"minute" : "00:47"
	}, {
		"cancelCount" : 0,
		"createCount" : 6,
		"minute" : "00:48"
	}, {
		"cancelCount" : 0,
		"createCount" : 2,
		"minute" : "00:50"
	}, {
		"cancelCount" : 0,
		"createCount" : 6,
		"minute" : "00:51"
	}, {
		"cancelCount" : 0,
		"createCount" : 4,
		"minute" : "00:52"
	}, {
		"cancelCount" : 0,
		"createCount" : 3,
		"minute" : "00:53"
	} ]

	;

	AmCharts.ready(function() {
		// SERIAL CHART
		chart = new AmCharts.AmSerialChart();
		chart.dataProvider = chartData;
		chart.categoryField = "minute";
		chart.startDuration = 1;
		chart.rotate = false;

		chart.pathToImages = "http://img.nikestore.com.cn/images/freeflyknit/ff-kv0725.jpg";
		chart.marginLeft = 0;
		chart.marginRight = 0;
		chart.marginTop = 0;
		chart.autoMarginOffset = 5;

		chart.depth3D = 0;
		chart.angle = 0;

		// AXES
		// category
		var categoryAxis = chart.categoryAxis;
		categoryAxis.gridPosition = "start";
		categoryAxis.axisColor = "#DADADA";
		categoryAxis.dashLength = 5;

		// value
		var valueAxis = new AmCharts.ValueAxis();
		valueAxis.dashLength = 5;
		valueAxis.axisAlpha = 0.2;
		valueAxis.position = "left";
		valueAxis.title = "下单数";
		chart.addValueAxis(valueAxis);
		
		 // GUIDES are used to create horizontal range fills
	   
		 /*
		 var guide = new AmCharts.Guide();
	    guide.value = 0;
	    guide.toValue = 105;
	    guide.fillColor = "#CC0000";
	    guide.fillAlpha = 0.2;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 105;
	    guide.toValue = 110;
	    guide.fillColor = "#CC0000";
	    guide.fillAlpha = 0.15;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 110;
	    guide.toValue = 115;
	    guide.fillColor = "#CC0000";
	    guide.fillAlpha = 0.1;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 115;
	    guide.toValue = 120;
	    guide.fillColor = "#CC0000";
	    guide.fillAlpha = 0.05;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 120;
	    guide.toValue = 125;
	    guide.fillColor = "#0000cc";
	    guide.fillAlpha = 0.05;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 125;
	    guide.toValue = 130;
	    guide.fillColor = "#0000cc";
	    guide.fillAlpha = 0.1;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 130;
	    guide.toValue = 135;
	    guide.fillColor = "#0000cc";
	    guide.fillAlpha = 0.15;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);

	    guide = new AmCharts.Guide();
	    guide.value = 135;
	    guide.toValue = 140;
	    guide.fillColor = "#0000cc";
	    guide.fillAlpha = 0.2;
	    guide.lineAlpha = 0;
	    valueAxis.addGuide(guide);
	    
	    */

		// GRAPHS
		// column graph
		var graph1 = new AmCharts.AmGraph();
		//graph1.type = "column";
		graph1.type = "line";
		graph1.title = "订单创建数";
		graph1.valueField = "createCount";
		//graph1.lineAlpha = 0;
		graph1.fillColors = "#ADD981";
		//graph1.fillAlphas = 1;

		graph1.lineThickness = 2;
		
		//Type of the bullets. Possible values are: "none", "round", "square", "triangleUp", "triangleDown", "bubble", "custom".
		graph1.bullet = "round";
		graph1.fillAlphas = 0;

		graph1.balloonText = "[[value]]";
		//graph1.balloonText = "[[category]] 下单数   [[value]]";
		chart.addGraph(graph1);
		
		  // CURSOR
	    var chartCursor = new AmCharts.ChartCursor();
	    chartCursor.cursorAlpha = 1;
	    chart.addChartCursor(chartCursor);

		// line graph
		/* 	 */var graph2 = new AmCharts.AmGraph();
		graph2.type = "line";
		graph2.title = "取消订单";
		graph2.valueField = "cancelCount";
		graph2.lineThickness = 2;
		graph2.bullet = "round";
		graph2.fillAlphas = 0;
		//chart.addGraph(graph2);

		// LEGEND
		var legend = new AmCharts.AmLegend();
		//legend.title="AJ1 QS订单情况一览(2013-07-28)";
		chart.addLegend(legend);

		chart.addTitle("AJ1 QS订单示意图(2013-07-28 12:00)");

		// WRITE
		chart.write("chartdiv");
	});
</script>
<div id="chartdiv" style="width: 1600px; height: 400px;"></div>