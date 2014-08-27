<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>AJ11</title>

    $remote_addr - $remote_user [$time_local] $status 
    	"$request" $body_bytes_sent "$http_referer" 
   		"$http_user_agent" $proxy_add_x_forwarded_for "$upstream_addr" "$request_time";
   		<br>
   		112.64.235.89 - - [19/Dec/2013:16:21:47 +0800] 404 "GET /product/fair/aj11blue1200.htm?cid=dis1221ondspaj01 HTTP/1.1" 29520 "-" "Mozilla/4.0" 112.64.235.89 "192.168.10.27:8407" "0.027"
<script type="text/javascript">

	var ordertypeChartData = ${ordertype_chartIndexList};
	var paymentTypeChartData = ${paymentTypeList};
	var skuSalesOrderChartData = ${skuSalesOrderChartIndexList};
	var soCreateListChartData = ${soCreateList};
	var aj11blue1200VisitCountChartData = ${aj11blue1200VisitCountIndexList};

	AmCharts.ready(function() {
		createAmPieChart("orderTypeChartdiv",ordertypeChartData,"name","value","20131214 Aj12 订单类型");
		createAmPieChart("paymentTypeChartdiv",paymentTypeChartData,"name","value","20131214 Aj12支付类型");
		//柱状图
		createAmSerialChart("aj11blue1200VisitCountIndexdiv",aj11blue1200VisitCountChartData,"name","value","aj11blue1200页面访问次数");
	});
	
	

  
	AmCharts.ready(function() {
		// SERIAL CHART
		var chart = new AmCharts.AmSerialChart();
		chart.dataProvider = soCreateListChartData;
		chart.categoryField = "dimension";
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
		valueAxis.title = "AJ11订单创建情况(2013-12-21)";
		chart.addValueAxis(valueAxis);

		// GRAPHS
		// column graph
		var graph1 = new AmCharts.AmGraph();
		graph1.type = "line";
		graph1.title = "订单创建笔数";
		graph1.valueField = "createCount";
		//graph1.lineAlpha = 0;
		graph1.fillColors = "#ADD981";
		graph1.fillAlphas = 0;
		//graph1.balloonText = "value";
		graph1.balloonText = "createCount in [[category]] [[value]]";
		chart.addGraph(graph1);

		// line graph
		/* 	 */var graph2 = new AmCharts.AmGraph();
		graph2.type = "line";
		graph2.title = "订单付款笔数";
		graph2.valueField = "paymentCount";
		graph2.lineThickness = 2;
		//graph2.bullet = "round";
		graph2.fillAlphas = 0;
		chart.addGraph(graph2);
		
		// line graph
		/* 	 */var graph3 = new AmCharts.AmGraph();
		graph3.type = "line";
		graph3.title = "订单个人取消笔数";
		graph3.valueField = "personalCancelCount";
		graph3.lineThickness = 2;
		//graph2.bullet = "round";
		graph3.fillAlphas = 0;
		chart.addGraph(graph3);
		
		
		
		// line graph
		/* 	 */var graph4 = new AmCharts.AmGraph();
		graph4.type = "line";
		graph4.title = "订单商城取消笔数";
		graph4.valueField = "storeCancelCount";
		graph4.lineThickness = 2;
		//graph2.bullet = "round";
		graph4.fillAlphas = 0;
		chart.addGraph(graph4);

		// LEGEND
		var legend = new AmCharts.AmLegend();
		//legend.title="2";
		chart.addLegend(legend);

		// WRITE
		chart.write("soCountIndexdiv");
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
	
	
	
</script>

<!--aj11blue1200页面访问次数-->
<div id="aj11blue1200VisitCountIndexdiv" style="width: 80%; height: 400px;"></div>

<!--订单数据统计-->
<div id="soCountIndexdiv" style="width: 100%; height: 800px;"></div>

<!--支付类型 -->
<div id="paymentTypeChartdiv" style="width: 100%; height: 500px;"></div>

<!-- 商品订单 -->
<div id="skuSalesOrderChartdiv" style="width: 80%; height: 400px;"></div>

<!-- 订单类型-->
<div id="orderTypeChartdiv" style="width: 80%; height: 400px;"></div>