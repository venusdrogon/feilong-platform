<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<title>amCharts examples</title>

<script type="text/javascript">
	var chart;

	var chartData =

	[ {
		"cancelCount" : 0,
		"createCount" : 48,
		"minute" : "00:30"
	}, {
		"cancelCount" : 0,
		"createCount" : 31,
		"minute" : "00:31"
	}, {
		"cancelCount" : 0,
		"createCount" : 10,
		"minute" : "00:32"
	}, {
		"cancelCount" : 0,
		"createCount" : 15,
		"minute" : "00:33"
	}, {
		"cancelCount" : 0,
		"createCount" : 108,
		"minute" : "00:34"
	}, {
		"cancelCount" : 0,
		"createCount" : 413,
		"minute" : "00:35"
	}, {
		"cancelCount" : 0,
		"createCount" : 24,
		"minute" : "00:36"
	}, {
		"cancelCount" : 0,
		"createCount" : 16,
		"minute" : "00:37"
	}, {
		"cancelCount" : 0,
		"createCount" : 23,
		"minute" : "00:38"
	}, {
		"cancelCount" : 0,
		"createCount" : 12,
		"minute" : "00:39"
	}, {
		"cancelCount" : 0,
		"createCount" : 63,
		"minute" : "00:40"
	}, {
		"cancelCount" : 0,
		"createCount" : 560,
		"minute" : "00:41"
	}, {
		"cancelCount" : 0,
		"createCount" : 146,
		"minute" : "00:42"
	}, {
		"cancelCount" : 0,
		"createCount" : 49,
		"minute" : "00:43"
	}, {
		"cancelCount" : 0,
		"createCount" : 13,
		"minute" : "00:44"
	}, {
		"cancelCount" : 65,
		"createCount" : 2,
		"minute" : "00:45"
	}, {
		"cancelCount" : 28,
		"createCount" : 15,
		"minute" : "00:46"
	}, {
		"cancelCount" : 14,
		"createCount" : 7,
		"minute" : "00:47"
	}, {
		"cancelCount" : 16,
		"createCount" : 57,
		"minute" : "00:48"
	}, {
		"cancelCount" : 652,
		"createCount" : 116,
		"minute" : "00:49"
	}, {
		"cancelCount" : 52,
		"createCount" : 27,
		"minute" : "00:50"
	}, {
		"cancelCount" : 18,
		"createCount" : 20,
		"minute" : "00:51"
	}, {
		"cancelCount" : 13,
		"createCount" : 608,
		"minute" : "00:52"
	}, {
		"cancelCount" : 25,
		"createCount" : 82,
		"minute" : "00:53"
	}, {
		"cancelCount" : 9,
		"createCount" : 16,
		"minute" : "00:54"
	}, {
		"cancelCount" : 474,
		"createCount" : 14,
		"minute" : "00:55"
	}, {
		"cancelCount" : 281,
		"createCount" : 19,
		"minute" : "00:56"
	}, {
		"cancelCount" : 19,
		"createCount" : 15,
		"minute" : "00:57"
	}, {
		"cancelCount" : 0,
		"createCount" : 504,
		"minute" : "00:58"
	}, {
		"cancelCount" : 0,
		"createCount" : 64,
		"minute" : "00:59"
	}, {
		"cancelCount" : 0,
		"createCount" : 16,
		"minute" : "01:00"
	}, {
		"cancelCount" : 12,
		"createCount" : 19,
		"minute" : "01:01"
	}, {
		"cancelCount" : 10,
		"createCount" : 7,
		"minute" : "01:02"
	}, {
		"cancelCount" : 80,
		"createCount" : 3,
		"minute" : "01:03"
	}, {
		"cancelCount" : 17,
		"createCount" : 333,
		"minute" : "01:04"
	}, {
		"cancelCount" : 34,
		"createCount" : 28,
		"minute" : "01:05"
	}, {
		"cancelCount" : 186,
		"createCount" : 17,
		"minute" : "01:06"
	}, {
		"cancelCount" : 500,
		"createCount" : 16,
		"minute" : "01:07"
	}, {
		"cancelCount" : 41,
		"createCount" : 12,
		"minute" : "01:08"
	}, {
		"cancelCount" : 16,
		"createCount" : 363,
		"minute" : "01:09"
	}, {
		"cancelCount" : 13,
		"createCount" : 62,
		"minute" : "01:10"
	}, {
		"cancelCount" : 18,
		"createCount" : 16,
		"minute" : "01:11"
	}, {
		"cancelCount" : 178,
		"createCount" : 21,
		"minute" : "01:12"
	}, {
		"cancelCount" : 386,
		"createCount" : 11,
		"minute" : "01:13"
	}, {
		"cancelCount" : 44,
		"createCount" : 523,
		"minute" : "01:14"
	}, {
		"cancelCount" : 17,
		"createCount" : 73,
		"minute" : "01:15"
	}, {
		"cancelCount" : 13,
		"createCount" : 28,
		"minute" : "01:16"
	}, {
		"cancelCount" : 3,
		"createCount" : 22,
		"minute" : "01:17"
	}, {
		"cancelCount" : 15,
		"createCount" : 12,
		"minute" : "01:18"
	}, {
		"cancelCount" : 345,
		"createCount" : 266,
		"minute" : "01:19"
	}, {
		"cancelCount" : 16,
		"createCount" : 65,
		"minute" : "01:20"
	}, {
		"cancelCount" : 18,
		"createCount" : 26,
		"minute" : "01:21"
	}, {
		"cancelCount" : 15,
		"createCount" : 15,
		"minute" : "01:22"
	}, {
		"cancelCount" : 20,
		"createCount" : 8,
		"minute" : "01:23"
	}, {
		"cancelCount" : 443,
		"createCount" : 368,
		"minute" : "01:24"
	}, {
		"cancelCount" : 40,
		"createCount" : 81,
		"minute" : "01:25"
	}, {
		"cancelCount" : 15,
		"createCount" : 30,
		"minute" : "01:26"
	}, {
		"cancelCount" : 20,
		"createCount" : 21,
		"minute" : "01:27"
	}, {
		"cancelCount" : 15,
		"createCount" : 11,
		"minute" : "01:28"
	}, {
		"cancelCount" : 595,
		"createCount" : 330,
		"minute" : "01:29"
	}, {
		"cancelCount" : 62,
		"createCount" : 91,
		"minute" : "01:30"
	}, {
		"cancelCount" : 14,
		"createCount" : 45,
		"minute" : "01:31"
	}, {
		"cancelCount" : 19,
		"createCount" : 28,
		"minute" : "01:32"
	}, {
		"cancelCount" : 23,
		"createCount" : 7,
		"minute" : "01:33"
	}, {
		"cancelCount" : 167,
		"createCount" : 442,
		"minute" : "01:34"
	}, {
		"cancelCount" : 73,
		"createCount" : 69,
		"minute" : "01:35"
	}, {
		"cancelCount" : 16,
		"createCount" : 32,
		"minute" : "01:36"
	}, {
		"cancelCount" : 11,
		"createCount" : 16,
		"minute" : "01:37"
	}, {
		"cancelCount" : 19,
		"createCount" : 8,
		"minute" : "01:38"
	}, {
		"cancelCount" : 307,
		"createCount" : 258,
		"minute" : "01:39"
	}, {
		"cancelCount" : 95,
		"createCount" : 49,
		"minute" : "01:40"
	}, {
		"cancelCount" : 20,
		"createCount" : 27,
		"minute" : "01:41"
	}, {
		"cancelCount" : 19,
		"createCount" : 20,
		"minute" : "01:42"
	}, {
		"cancelCount" : 25,
		"createCount" : 11,
		"minute" : "01:43"
	}, {
		"cancelCount" : 367,
		"createCount" : 365,
		"minute" : "01:44"
	}, {
		"cancelCount" : 95,
		"createCount" : 70,
		"minute" : "01:45"
	}, {
		"cancelCount" : 41,
		"createCount" : 31,
		"minute" : "01:46"
	}, {
		"cancelCount" : 24,
		"createCount" : 22,
		"minute" : "01:47"
	}, {
		"cancelCount" : 17,
		"createCount" : 12,
		"minute" : "01:48"
	}, {
		"cancelCount" : 448,
		"createCount" : 425,
		"minute" : "01:49"
	}, {
		"cancelCount" : 56,
		"createCount" : 93,
		"minute" : "01:50"
	}, {
		"cancelCount" : 19,
		"createCount" : 25,
		"minute" : "01:51"
	}, {
		"cancelCount" : 16,
		"createCount" : 10,
		"minute" : "01:52"
	}, {
		"cancelCount" : 23,
		"createCount" : 13,
		"minute" : "01:53"
	}, {
		"cancelCount" : 250,
		"createCount" : 443,
		"minute" : "01:54"
	}, {
		"cancelCount" : 51,
		"createCount" : 54,
		"minute" : "01:55"
	}, {
		"cancelCount" : 27,
		"createCount" : 21,
		"minute" : "01:56"
	}, {
		"cancelCount" : 19,
		"createCount" : 13,
		"minute" : "01:57"
	}, {
		"cancelCount" : 32,
		"createCount" : 5,
		"minute" : "01:58"
	}, {
		"cancelCount" : 353,
		"createCount" : 315,
		"minute" : "01:59"
	}, {
		"cancelCount" : 65,
		"createCount" : 67,
		"minute" : "02:00"
	}, {
		"cancelCount" : 32,
		"createCount" : 32,
		"minute" : "02:01"
	} ]

	;

	AmCharts.ready(function() {
		// SERIAL CHART
		chart = new AmCharts.AmSerialChart();
		chart.dataProvider = chartData;
		chart.categoryField = "minute";
		chart.startDuration = 1;
		chart.rotate = false;

		chart.depth3D = 20;
		chart.angle = 30;

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
		valueAxis.title = "基调网络压力并发测试 订单情况一览(2012-12-19)";
		chart.addValueAxis(valueAxis);

		// GRAPHS
		// column graph
		var graph1 = new AmCharts.AmGraph();
		graph1.type = "column";
		graph1.title = "创建订单数";
		graph1.valueField = "createCount";
		graph1.lineAlpha = 0;
		graph1.fillColors = "#ADD981";
		graph1.fillAlphas = 1;
		//graph1.balloonText = "value";
		graph1.balloonText = "createCount in [[category]] [[value]]";
		chart.addGraph(graph1);

		// line graph
		/* 	 */var graph2 = new AmCharts.AmGraph();
		graph2.type = "line";
		graph2.title = "取消订单";
		graph2.valueField = "cancelCount";
		graph2.lineThickness = 2;
		graph2.bullet = "round";
		graph2.fillAlphas = 0;
		chart.addGraph(graph2);

		// LEGEND
		var legend = new AmCharts.AmLegend();
		//legend.title="2";
		chart.addLegend(legend);

		// WRITE
		chart.write("chartdiv");
	});
</script>
<div id="chartdiv" style="width: 3800px; height: 1000px;"></div>