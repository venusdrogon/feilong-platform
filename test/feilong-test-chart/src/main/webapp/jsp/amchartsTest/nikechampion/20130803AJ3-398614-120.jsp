<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="title" value="AJ3 QS订单示意图-女鞋-398614(2013-08-03 10:10)"></c:set>
<title>${title }</title>
<script type="text/javascript">
	var chart;

	var chartData =

		[
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:58"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:01"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:03"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:04"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:09"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:15"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:16"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:21"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:23"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:27"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:34"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:40"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:41"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:42"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:46"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:47"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:48"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:49"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:51"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:54"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:59"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:00"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:01"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:02"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:03"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:05"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:06"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:10"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:11"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:12"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:16"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:21"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "12:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:34"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "12:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:38"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:40"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:42"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:44"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "12:48"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:50"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:57"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:59"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:00"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "13:07"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "13:11"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:21"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "13:22"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:23"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:26"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "13:29"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:35"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:38"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:40"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:41"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:45"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:53"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "13:59"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:00"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:02"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:10"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:15"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:19"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:26"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "14:47"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:54"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:57"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "14:58"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:08"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:11"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:15"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:19"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "15:21"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:32"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "15:34"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:39"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:46"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:49"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "15:51"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:07"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "16:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:28"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:50"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "16:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "17:09"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "17:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "17:35"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "17:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "17:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "17:58"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "18:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "18:24"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "18:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:08"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:09"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:16"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:28"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "19:41"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:49"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "19:52"
	        }
	    ]

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

		// GRAPHS
		// column graph
		var graph1 = new AmCharts.AmGraph();
		//graph1.type = "column";
		graph1.type = "line";
		graph1.lineColor = "#0D8ECF";
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

		chart.addTitle("${title}");

		// WRITE
		chart.write("chartdiv");
	});
</script>
<div id="chartdiv" style="width: 1600px; height: 400px;"></div>