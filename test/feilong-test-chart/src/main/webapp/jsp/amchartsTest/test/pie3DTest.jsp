<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>amCharts examples</title>

<script type="text/javascript">
	var chart;
	var legend;

	var chartData = [ {
		"cancelCount" : null,
		"createCount" : 502,
		"minute" : "网上支付－网银在线"
	}, {
		"cancelCount" : null,
		"createCount" : 325,
		"minute" : "信用卡支付"
	}, {
		"cancelCount" : null,
		"createCount" : 835,
		"minute" : "支付宝"
	}, {
		"cancelCount" : null,
		"createCount" : 1,
		"minute" : "汇付天下"
	} ];

	AmCharts.ready(function() {
		// PIE CHART
		chart = new AmCharts.AmPieChart();
		chart.dataProvider = chartData;

		chart.titleField = "minute";
		chart.valueField = "createCount";

		chart.outlineColor = "#FFFFFF";
		chart.outlineAlpha = 0.8;
		chart.outlineThickness = 2;
		// this makes the chart 3D
		chart.depth3D = 12;
		chart.angle = 30;

		// LEGEND
		var legend = new AmCharts.AmLegend();
		legend.align = "center";

		legend.position = "right";
		//legend.reversedOrder="true";
		legend.valueAlign = "right";
		legend.spacing = "1";

		//legend.title="2";
		chart.addLegend(legend);

		// WRITE
		chart.write("chartdiv");
	});
</script>

<div id="chartdiv" style="width: 100%; height: 400px;"></div>

