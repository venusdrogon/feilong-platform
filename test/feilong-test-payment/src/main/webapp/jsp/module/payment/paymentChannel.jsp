<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


请稍等,我们再努力的为您跳转到支付网关.....
<form id="submitForm" target="_bank" name="submitForm" action="${paymentFormEntity.action}" method="${paymentFormEntity.method}">
	<c:forEach var="entry" items="${paymentFormEntity.hiddenParamMap}">
		<input type="hidden" name="${entry.key}" value="${entry.value}" />
	</c:forEach>
</form>

<script type="text/javascript">
    $(function() {
	$('#submitForm').submit();
    });
</script>
