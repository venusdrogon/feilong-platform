<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="feilong" uri="http://java.feilong.com/tags-common"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>社区测试</title>
		<link rel="stylesheet" href="${domain_css}/res/feilong/css/feilong-all.css" type="text/css"></link>
	</head>
	<body><%--
		<iframe width="63" height="24" frameborder="0" allowtransparency="true"
			marginwidth="0" marginheight="0" scrolling="no" frameborder="No" border="0"
			src="http://widget.weibo.com/relationship/followbutton.php?width=63&height=24&uid=1903991210&style=1&btn=red&dpc=1"></iframe>

		--%><br><%--
		<a href="http://weibo.com/1903991210?s=6uyXnP" target="_blank"><img
				border="0"
				src="http://service.t.sina.com.cn/widget/qmd/1903991210/1c853142/9.png" />
		</a>

		--%><br><%--
		[url=http://weibo.com/1903991210?s=6uyXnP][img]http://service.t.sina.com.cn/widget/qmd/1903991210/1c853142/9.png[/img][/url]
		<br>--%>
		<script type="text/javascript" charset="utf-8">
(function(){
  var _w = 106 , _h = 58;
  var param = {
    url:location.href,
    type:'5',
    count:'1', /**是否显示分享数，1显示(可选)*/
    appkey:'', /**您申请的应用appkey,显示分享来源(可选)*/
    title:'', /**分享的文字内容(可选，默认为所在页面的title)*/
    pic:'', /**分享图片的路径(可选)*/
    ralateUid:'1903991210', /**关联用户的UID，分享微博会@该用户(可选)*/
    rnd:new Date().valueOf()
  }
  var temp = [];
  for( var p in param ){
    temp.push(p + '=' + encodeURIComponent( param[p] || '' ) )
  }
  document.write('<iframe allowTransparency="true" frameborder="0" scrolling="no" src="http://hits.sinajs.cn/A1/weiboshare.html?' + temp.join('&') + '" width="'+ _w+'" height="'+_h+'"></iframe>')
})()
</script>
	</body>
</html>
