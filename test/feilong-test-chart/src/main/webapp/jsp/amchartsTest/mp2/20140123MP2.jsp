<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>20131227MP2</title>

<script type="text/javascript">
	var progressList = ${progressList};
	var noReviewProgressList = ${noReviewProgressList};
	var noCompleteBRDProgressList = ${noCompleteBRDProgressList};

	AmCharts.ready(function() {
		//柱状图
		createAmSerialChart("progressListtIndexdiv", progressList, "name",
				"value", "2014-01-23 MP2 BRD progress(Part1)");
		//柱状图
		createAmSerialChart("noReviewProgressListIndexdiv", noReviewProgressList, "name",
				"value", "2014-01-23 MP2 BRD progress(Part2)");
		//柱状图
		createAmSerialChart("noCompleteBRDProgressListIndexdiv", noCompleteBRDProgressList, "name",
				"value", "2014-01-23 MP2 BRD progress(Part3)");
	});
</script>

<!--progressListtIndexdiv-->
<div id="progressListtIndexdiv" style="width: 80%; height: 400px;"></div>

<!--noReviewProgressListIndexdiv-->
<div id="noReviewProgressListIndexdiv" style="width: 80%; height: 400px;"></div>

<!--noReviewProgressListIndexdiv-->
<div id="noCompleteBRDProgressListIndexdiv" style="width: 80%; height: 400px;"></div>
