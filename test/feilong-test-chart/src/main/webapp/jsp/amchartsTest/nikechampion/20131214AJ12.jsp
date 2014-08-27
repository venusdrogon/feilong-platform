<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>amCharts examples</title>

<script type="text/javascript">

	var ordertypeChartData = ${ordertype_chartIndexList};
	var paymentTypeChartData = ${paymentTypeList};
	var skuSalesOrderChartData = ${skuSalesOrderChartIndexList};

	AmCharts.ready(function() {
		createAmPieChart("orderTypeChartdiv",ordertypeChartData,"name","value","20131214 Aj12 订单类型");
		createAmPieChart("paymentTypeChartdiv",paymentTypeChartData,"name","value","20131214 Aj12支付类型");
	});
	
	
	 AmCharts.ready(function () {
         // SERIAL CHART
         chart = new AmCharts.AmSerialChart();
         chart.dataProvider = skuSalesOrderChartData;
         
         chart.categoryField = "skuCode";
         chart.startDuration = 1;
         chart.plotAreaBorderColor = "#DADADA";
         chart.plotAreaBorderAlpha = 1;
         // this single line makes the chart a bar chart          
         chart.rotate = true;
         

         // AXES
         // Category
         var categoryAxis = chart.categoryAxis;
         categoryAxis.gridPosition = "start";
         categoryAxis.gridAlpha = 0.1;
         categoryAxis.axisAlpha = 0;

         // Value
         var valueAxis = new AmCharts.ValueAxis();
         valueAxis.axisAlpha = 0;
         valueAxis.gridAlpha = 0.1;
         valueAxis.position = "top";
         chart.addValueAxis(valueAxis);

         //************************************************************************************
         // GRAPHS
         // first graph
         var graph1 = new AmCharts.AmGraph();
         graph1.type = "column";
         graph1.title = "总库存";
         graph1.valueField = "totalInventoryCount";
         graph1.balloonText = graph1.title+":[[value]]";
         graph1.lineAlpha = 0;
         graph1.fillColors = "#ADD981";
         graph1.fillAlphas = 1;
         chart.addGraph(graph1);

         // second graph
         var graph2 = new AmCharts.AmGraph();
         graph2.type = "column";
         graph2.title = "下单数";
         graph2.valueField = "createSalesOrderCount";
         graph2.balloonText = graph2.title+":[[value]]";
         graph2.lineAlpha = 0;
         graph2.fillColors = "#81acd9";
         graph2.fillAlphas = 1;
         graph2.showAllValueLabels = true;
         graph2.showHandOnHover = true;//If you want mouse pointer to change to hand when hovering the graph, set this property to true.
         chart.addGraph(graph2);

         // 3 graph
         var graph3 = new AmCharts.AmGraph();
         graph3.type = "column";
         graph3.title = "个人取消数";
         graph3.valueField = "personalCancelCount";
         graph3.balloonText = graph3.title+":[[value]]";
         graph3.lineAlpha = 0;
        // graph3.fillColors = "#81acd9";
         graph3.fillAlphas = 1;
         chart.addGraph(graph3);

         // 4 graph
         var graph4 = new AmCharts.AmGraph();
         graph4.type = "column";
         graph4.title = "商城取消数";
         graph4.valueField = "storeCancelCount";
         graph4.balloonText = graph4.title+":[[value]]";
         graph4.lineAlpha = 0;
        // graph4.fillColors = "#81acd9";
         graph4.fillAlphas = 1;
         chart.addGraph(graph4);

         // LEGEND
         var legend = new AmCharts.AmLegend();
         chart.addLegend(legend);
         
        // chart.zoomOut();
         
         chart.addTitle("20131214 Aj12商品订单(下单/个人取消/商城取消)情况");//,"12px","微软雅黑","red",true

         // WRITE
         chart.write("skuSalesOrderChartdiv");
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
		
		chart.colors = [  
		   			"#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#2A0CD0", "#8A0CCF",
		   			"#CD0D74", "#754DEB", "#DDDDDD", "#999999", "#333333", "#000000",
		   			"#57032A", "#CA9726", "#990000", "#4B0C25" ];
		

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


<!--支付类型 -->
<div id="paymentTypeChartdiv" style="width: 100%; height: 500px;"></div>

<!-- 商品订单 -->
<div id="skuSalesOrderChartdiv" style="width: 80%; height: 400px;"></div>

<!-- 订单类型-->
<div id="orderTypeChartdiv" style="width: 80%; height: 400px;"></div>

