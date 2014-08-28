<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!-- <!DOCTYPE html> -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>googleMapTest.jsp</title>
<link rel="stylesheet" href="res/feilong/css/feilong-all.css" type="text/css"></link>
<%@ include file="res/feilong/include/js/feilong-js.inc"%>
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0px;
	padding: 0px
}

#map_canvas {
	width: 800px;
	height: 600px
}
</style>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&language=zh">
	
</script>
<script type="text/javascript">
	$(function() {
		initialize();
	});
	function initialize() {
		//上海 31.2, 121.5
		//{纬度, 经度} 
		var latlng = new google.maps.LatLng(31.2, 121.5);
		var myOptions = {
			//其中缩放 0 相当于将地球地图缩小到最低程度，而较高的缩放级别会将地图放大到较高的分辨率
			zoom : 11,
			center : latlng,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		//,zoomControl:false
		};
		var map = new google.maps.Map(document.getElementById("map_canvas"),
				myOptions);
		//map.setZoom(15);
		//alert(map.getZoom());

		var marker = new google.maps.Marker({
			map : map,
			position : map.getCenter(),
			title:"上海"
		});
		var infowindow = new google.maps.InfoWindow();
		infowindow.setContent("<span style='color:red'>上海</span>");
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map, marker);
		})

	}
</script>
</head>
<body>
	<div id="map_canvas"></div>
</body>
</html>
