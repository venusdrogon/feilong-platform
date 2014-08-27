<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>amCharts examples</title>

<script type="text/javascript">

	var ordertypeChartData = ${ordertype_chartIndexList};
	var soStatusChartData = ${soStatus_chartIndexList};

	AmCharts.ready(function() {
		createAmPieChart("orderTypeChartdiv",ordertypeChartData,"name","value","20131213 kobe2 订单类型");
		createAmPieChart("soStatusChartdiv",soStatusChartData,"name","value","20131213 kobe2 订单状态");
	});
	
	
	function createAmPieChart(chartDivId,dataProvider,titleField,valueField,chartTitle){
		
		// PIE CHART
		var chart = new AmCharts.AmPieChart();
		chart.dataProvider = dataProvider;

		chart.titleField = titleField;
		chart.valueField = valueField;

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
		
		chart.addTitle(chartTitle);//,"12px","微软雅黑","red",true

		// WRITE
		chart.write(chartDivId);
	}
	
	
</script>

 

<div id="orderTypeChartdiv" style="width: 80%; height: 400px;"></div>
<div id="soStatusChartdiv" style="width: 80%; height: 400px;"></div>

