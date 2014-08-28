<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left">

	menu

	<div id="pushStateDiv">
		pushState:<br>
		<ul>
			<li><a href="${base}/jsp/css/feilongCleanlily15">feilongCleanlily15</a></li>
			<li><a href="${base}/jsp/elTest/feilong-elTest">feilong-elTest</a></li>
		</ul>

	</div>
	<div id="pushStateShowDiv"></div>


	<script type="text/javascript">
	$(function() {
	    $("#main a").attr("target", "_blank");

	    //$("#pushStateDiv a").pjax('#center');

	    $("#pushStateDiv a").click(function(e) {
		var link = $(this);

		var title = link.text();
		var url = link.attr("href");

		$("#pushStateShowDiv").html(url + "@" + title);

		pushState(title, url);
		//return false;
		e.preventDefault();

	    });

	    //console.log(history.state);

	    //// THIS EVENT MAKES SURE THAT THE BACK/FORWARD BUTTONS WORK AS WELL
	    window.onpopstate = function(event) {
		//console.log(event.state);

		//// USES JQUERY TO LOAD THE CONTENT
		var url = document.location;
		loadContent(url);
	    };
	});

	function pushState(title, url) {
	    var state = {
		title : title,
		url : url,
		otherkey : "jinxin" + title
	    };

	    loadContent(url);
	    window.history.pushState(state, title, url);
	    console.log(history.state);
	}

	function loadContent(url) {
	    $.get(url + ".jsp", {
		contentid : url
	    }, function(data) {
		$("#center").html(data);
	    });
	}
    </script>

</div>