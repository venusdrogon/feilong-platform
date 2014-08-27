<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
收银台:
<br>
总支付金额 :
<input name="amount" type="text" />

<br>
支付类型选择:
<br>

<ul>
	<li><input name="paymentType" type="radio" value="151" checked="checked" /> doku MandiriClickPay</li>
	<li><input name="paymentType" type="radio" value="152" /> doku BRIEPay</li>
	<li><input name="paymentType" type="radio" value="153" /> doku ATM</li>
	<li><input name="paymentType" type="radio" value="154" /> bca CreditCard</li>
	<li><input name="paymentType" type="radio" value="155" /> bca klikpay</li>
<!-- 	<li><input name="paymentType" type="radio" value="156" /> bca KlikBCA</li> -->
</ul>

<input id="goToChannelInput" type="button" value="确认" />


<script type="text/javascript">
    $(function() {
	$("#goToChannelInput")
		.click(
			function() {
			    var paymentType = $(
				    "input[name='paymentType']:checked").val();
			    location.href = "${base}/payment/paymentChannel?paymentType="
				    + paymentType;
			});
    });
</script>
