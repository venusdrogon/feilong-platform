<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="title" value="AJ3 QS订单示意图-男鞋-136064-120(2013-08-03 10:10)"></c:set>
<title>${title }</title>

<script type="text/javascript">
	var chart;

	var chartData =

		[
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:19"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "10:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:21"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:22"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:23"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "10:24"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 5,
	            "minute": "10:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "10:26"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 5,
	            "minute": "10:27"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "10:28"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "10:29"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "10:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "10:32"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:34"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "10:35"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 8,
	            "minute": "10:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:38"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:39"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 9,
	            "minute": "10:40"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 9,
	            "minute": "10:41"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:42"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 8,
	            "minute": "10:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 5,
	            "minute": "10:44"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:45"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:46"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:47"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:48"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 11,
	            "minute": "10:49"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 8,
	            "minute": "10:50"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "10:51"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "10:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 5,
	            "minute": "10:53"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 5,
	            "minute": "10:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "10:56"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:57"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "10:58"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "10:59"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:00"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:01"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:02"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:03"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:05"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:06"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:07"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:08"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:09"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:10"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:11"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:12"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:14"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "11:15"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 9,
	            "minute": "11:16"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 9,
	            "minute": "11:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "11:18"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:19"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:22"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:23"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:24"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:25"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:29"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:30"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:33"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:34"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:35"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "11:36"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 7,
	            "minute": "11:37"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 6,
	            "minute": "11:38"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:39"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:40"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:41"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:42"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:45"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:47"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:48"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:50"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "11:53"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:54"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "11:55"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:56"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "11:57"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "11:58"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:06"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:08"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:09"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:11"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:12"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:13"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "12:14"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:15"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:16"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 4,
	            "minute": "12:17"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:18"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:19"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:20"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:22"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:23"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:24"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:26"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:31"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:33"
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
	            "minute": "12:43"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:46"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:50"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 1,
	            "minute": "12:51"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:52"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 2,
	            "minute": "12:53"
	        },
	        {
	            "cancelCount": 0,
	            "createCount": 3,
	            "minute": "12:54"
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

		chart.addTitle("${title}");

		// WRITE
		chart.write("chartdiv");
	});
</script>
<div id="chartdiv" style="width: 1600px; height: 400px;"></div>