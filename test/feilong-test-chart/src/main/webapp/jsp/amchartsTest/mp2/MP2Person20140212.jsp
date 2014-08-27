<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>2014-02-12 MP2 personInfoList</title>

<script type="text/javascript">
	var passportStatusList = ${passportStatusList};
 

	AmCharts.ready(function() {
	    
	    var feilongChart={
		    	chartDivId:"passportStatusListAmPieChartIndexdiv",
		    	dataProvider:passportStatusList,
		    	titleField:"name",
		    	valueField:"value",
		    	chartTitle:"2014-02-12 MP2 护照办理情况纵览（饼状图）",
		    	colorField:"color"
		    };
		//饼状图
		createAmPieChart(feilongChart);
		
		//柱状图
		createAmSerialChart("passportStatusListAmSerialChartIndexdiv", passportStatusList, "name",
				"value", "2014-02-12 MP2 护照办理情况纵览（柱状图）");
		 
	});
</script>


 <script type="text/javascript">
			
			 var chartData = ${personCountIndexGroupByTitleList};


            AmCharts.ready(function () {
                // SERIAL CHART
                var chart = new AmCharts.AmSerialChart();
                chart.dataProvider = chartData;
                chart.categoryField = "groupType";
                chart.plotAreaBorderAlpha = 0.2;

                // AXES
                // category
                var categoryAxis = chart.categoryAxis;
                categoryAxis.gridAlpha = 0.1;
                categoryAxis.axisAlpha = 0;
                categoryAxis.gridPosition = "start";

                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.stackType = "regular";
                valueAxis.gridAlpha = 0.1;
                valueAxis.axisAlpha = 0;
                chart.addValueAxis(valueAxis);

                // GRAPHS
                // first graph    
                var graph = new AmCharts.AmGraph();
                graph.title = "有护照数";
                graph.labelText = "[[value]]";
                graph.valueField = "hasPassportCount";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#04D215";
               // graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);

                // second graph              
                graph = new AmCharts.AmGraph();
                graph.title = "春节在家办理了护照数";
                graph.labelText = "[[value]]";
                graph.valueField = "chunjieCount";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#0D52D1";
              //  graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);                

                // third graph                              
                graph = new AmCharts.AmGraph();
                graph.title = "将到上海办理数";
                graph.labelText = "[[value]]";
                graph.valueField = "shanghaiban";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#B0DE09";
              //  graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);

                // fourth graph  
                graph = new AmCharts.AmGraph();
                graph.title = "未收到反馈";
                graph.labelText = "[[value]]";
                graph.valueField = "noFeedBack";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#FCD202";
              //  graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);

                // fifth graph
                graph = new AmCharts.AmGraph();
                graph.title = "明确办不了数";
                graph.labelText = "[[value]]";
                graph.valueField = "banbuliaoCount";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#666666";
               // graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span style='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);

                // sixth graph   
                graph = new AmCharts.AmGraph();
                graph.title = "未参与统计数";
                graph.labelText = "[[value]]";
                graph.valueField = "noTongjiCount";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#4B0C25";
              //  graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span class='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);
                
                // 7 graph   
                graph = new AmCharts.AmGraph();
                graph.title = "离职人数";
                graph.labelText = "[[value]]";
                graph.valueField = "lizhiCount";
                graph.type = "column";
                graph.lineAlpha = 0;
                graph.fillAlphas = 1;
                graph.lineColor = "#000000";
              //  graph.balloonText = "<span style='color:#555555;'>[[category]]</span><br><span class='font-size:14px'>[[title]]:<b>[[value]]</b></span>";
                chart.addGraph(graph);

                // LEGEND                  
                var legend = new AmCharts.AmLegend();
                legend.borderAlpha = 0.2;
                legend.horizontalGap = 10;
                chart.addLegend(legend);
                
                
                addChartBorder(chart);
                addChartTitle(chart,"2014-02-12 MP2 护照办理情况纵览（按Title统计）");

                // WRITE
                chart.write("chartdiv");
            });

            
        </script>
    </head>
<table class="dataTable">
	<tr style="text-align: center">
		<th style="text-align: center">Name</th>
		<th style="text-align: center">Title</th>
		<th style="text-align: center">Level</th>
		<!-- 		<th style="text-align:center">EntryTime</th>
		<th style="text-align:center">JoinTime</th> -->
		<th style="text-align: center">Memo</th>
		<!-- 		<th style="text-align:center">PassportCase</th> -->
		<th style="text-align: center">Mark</th>
	</tr>


	<tr class="feilongEvenTrClass">
		<td colspan="5" class="td_module_title">将到上海办理情况</td>
	</tr>
	<c:forEach var="mp2PersonInfo" items="${groupByStatusMap[6] }">
		<tr>
			<td style="padding-left: 55px;">${mp2PersonInfo.name }</td>
			<td>${mp2PersonInfo.title }</td>
			<td>${mp2PersonInfo.level }</td>
			<%-- <td>${mp2PersonInfo.entryTime }</td>
			<td>${mp2PersonInfo.joinTime }</td> --%>
			<td>${mp2PersonInfo.memo }</td>
			<%-- <td>${mp2PersonInfo.passportCase }</td> --%>
			<td>${mp2PersonInfo.mark }</td>
		</tr>

	</c:forEach>

	<tr class="feilongEvenTrClass">
		<td colspan="5" class="td_module_title">明确办不了情况</td>
	</tr>
	<c:forEach var="mp2PersonInfo" items="${groupByStatusMap[4] }">
		<tr>
			<td style="padding-left: 55px">${mp2PersonInfo.name }</td>
			<td>${mp2PersonInfo.title }</td>
			<td>${mp2PersonInfo.level }</td>
			<%-- <td>${mp2PersonInfo.entryTime }</td>
			<td>${mp2PersonInfo.joinTime }</td> --%>
			<td>${mp2PersonInfo.memo }</td>
			<%--<td>${mp2PersonInfo.passportCase }</td> --%>
			<td>${mp2PersonInfo.mark }</td>
		</tr>
	</c:forEach>
	
	
	<tr class="feilongEvenTrClass">
		<td colspan="5" class="td_module_title">离职人数情况</td>
	</tr>
	<c:forEach var="mp2PersonInfo" items="${groupByStatusMap[7] }">
		<tr>
			<td style="padding-left: 55px">${mp2PersonInfo.name }</td>
			<td>${mp2PersonInfo.title }</td>
			<td>${mp2PersonInfo.level }</td>
			<%-- <td>${mp2PersonInfo.entryTime }</td>
			<td>${mp2PersonInfo.joinTime }</td> --%>
			<td>${mp2PersonInfo.memo }</td>
			<%-- <td>${mp2PersonInfo.passportCase }</td> --%>
			<td>${mp2PersonInfo.mark }</td>
		</tr>
	</c:forEach>
</table>


<!--passportStatusListAmPieChartIndexdiv-->
<div id="passportStatusListAmPieChartIndexdiv" style="width: 80%; height: 400px;"></div>

<!--passportStatusListAmSerialChartIndexdiv-->
<div id="passportStatusListAmSerialChartIndexdiv" style="width: 80%; height: 400px;margin-top:20px"></div>


<div id="chartdiv" style="width: 80%; height: 400px;"></div>
 