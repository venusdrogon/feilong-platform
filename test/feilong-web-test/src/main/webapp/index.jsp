<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>飞龙首页 ${name }</title>
<style type="text/css">
p {
	font: bold 15px '微软雅黑';
	text-align: left
}
</style>

<script type="text/javascript">
    $("#main a").attr("target", "_blank");
</script>

<p>支付:</p>
<table>
	<tr>
		<!-- jsp/module/payment/paymentIntegration -->
		<td><a href="${base}/payment/paymentIntegration">支付集成</a></td>
	</tr>
</table>

<p>下载:</p>
<table>
	<tr>
		<td><a href="${base}/download" target="_self">download</a></td>
	</tr>
</table>

<p>css:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/css/feilongCleanlily15">feilongCleanlily15</a></td>
		<td><a href="${base}/jsp/amchartsTest/chartColor.html">chartColor</a></td>
	</tr>
</table>

<!-- MP2图表测试: -->
<p>MP2图表测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/amchartsTest/20131227MP2">20131227MP2</a></td>
		<td><a href="${base}/jsp/amchartsTest/20140123MP2">20140123MP2</a></td>
		<td><a href="${base}/jsp/amchartsTest/MP2Person20140212">MP2Person20140212</a></td>
	</tr>
</table>

<!-- nikechampion -->
<p>nikechampion图表测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/amchartsTest/20130728AJ1OG">AJ 1 OG</a></td>
		<td><a href="${base}/jsp/amchartsTest/20131213kobe2">20131213kobe2</a></td>
		<td><a href="${base}/jsp/amchartsTest/20131214AJ12">20131214AJ12</a></td>
	</tr>
	<tr>
		<td><a href="${base}/jsp/amchartsTest/20131221AJ11">20131221AJ11</a></td>
	</tr>
</table>

<p>图表测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/amchartsTest/test/pie3DTest">饼状图</a></td>
		<td><a href="${base}/jsp/amchartsTest/test/barAndLineMix">柱状图</a></td>
	</tr>
</table>


<p>社区测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/sns/communityTest.jsp">微博测试</a></td>
		<td><a href="${base}/jsp/sns/baiduzhidaoTest">百度知道</a></td>
	</tr>
	<tr>
		<td><a href="${base}/c2-5-3-11/mpige-c黑-s52-kchuck taylor all star-svintage.htm">UriTemplateUtilController test</a></td>
		<td><a href="${base}/c54758-jinxin">c54758-jinxin</a></td>
	</tr>
</table>

<p>标准作用域测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/scope/request?a=b&a=c&c=d">request test</a></td>
	</tr>
</table>

<p>SpringMVC测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/interceptor/browserInterceptor">browserInterceptor test</a></td>
		<td><a href="${base}/test/responseBody">@ResponseBody</a></td>
	</tr>
</table>

<p>自定义标签测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/taglibTest/isInTimeTag">IsInTimeTag</a></td>
		<td><a href="${base}/jsp/taglibTest/isExistsFileTag">isExistsFileTag</a></td>
	</tr>
	<tr>
		<td><a href="${base}/jsp/taglibTest/isContainTag">isContainTag</a></td>
		<td><a href="${base}/jsp/taglibTest/isNotContainTag">isNotContainTag</a></td>
		<td><a href="${base}/jsp/taglibTest/siteMap/siteMapTest">siteMapTest.jsp</a></td>
	</tr>
	<tr>
		<td><a href="${base}/pager.htm">分页测试</a> <a href="${base}/jsp/taglibTest/pager.html">分页html效果</a></td>
	</tr>
</table>

<p>feilong-el测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/elTest/feilong-elTest">feilong-elTest</a></td>
	</tr>
</table>

<p>富文本编辑器测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/editor/ckeditorTest.jsp">ckeditorTest</a></td>
		<td><a href="${base}/jsp/editor/ckfinderTest.jsp">ckfinderTest</a></td>
	</tr>
</table>

<p>安全测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/taglibTest/esapiTagTest">esapiTagTest.jsp</a></td>
	</tr>
</table>

<p>其他测试:</p>
<table>
	<tr>
		<td><a href="${base}/jsp/captcha/captcha">验证码</a></td>
		<td><a href="${base}/jsp/ztree/ztree">ztree</a></td>
	</tr>
</table>