<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>20131227MP2</title>

<script type="text/javascript">
	var progressList = ${progressList};

	AmCharts.ready(function() {
		//柱状图
		createAmSerialChart("progressListtIndexdiv", progressList, "name",
				"value", "2013-12-27 mp2 batch1 progress");
	});
</script>

<!--progressListtIndexdiv-->
<div id="progressListtIndexdiv" style="width: 80%; height: 400px;"></div>
