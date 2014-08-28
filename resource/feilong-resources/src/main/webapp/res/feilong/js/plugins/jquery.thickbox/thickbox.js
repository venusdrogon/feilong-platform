var rootPath = "res/feilong/js/plugins/jquery.thickbox/";
var tb_pathToImage = rootPath + "loadingAnimation.gif";
var closeImage = rootPath + "close.gif";
var contentId = "";
// 加载
$(function() {
	// pass where to apply thickbox
	tb_init("a.thickbox, area.thickbox, input.thickbox,button.thickbox");
	// preload image
	imgLoader = new Image();
	imgLoader.src = tb_pathToImage;
});
// 初始化
function tb_init(domChunk) {
	$(domChunk).click(function() {
		var t = this.title || this.name || null;
		var a = this.href || this.alt;
		var g = this.rel || false;
		// 显示
		// tb_show(t, a, g);
		// 支持frame
		self.parent.tb_show(t, a, g);
		this.blur();
		contentId = "";
		return false;
	});
}
/**
 * 显示层信息
 * 
 * @param caption
 *          标题
 * @param url
 *          url
 * @param imageGroup
 *          图片组
 * @return
 */
function tb_show(caption, url, imageGroup) {
	try {
		// $.support.boxModel
		// if IE 6
		if (typeof document.body.style.maxHeight === "undefined") {
			$("body", "html").css({
				height	: "100%",
				width		: "100%"
			});
			$("html").css("overflow", "hidden");
			// iframe to hide select elements in ie6
			if (document.getElementById("TB_HideSelect") === null) {
				$("body").append("<iframe id='TB_HideSelect'></iframe><div id='TB_overlay'></div><div id='TB_window'></div>");
				$("#TB_overlay").click(tb_remove);
			}
		} else {
			if (document.getElementById("TB_overlay") === null) {
				$("body").append("<div id='TB_overlay'></div><div id='TB_window'></div>");
				$("#TB_overlay").click(tb_remove);
			}
		}
		if (tb_detectMacXFF()) {
			$("#TB_overlay").addClass("TB_overlayMacFFBGHack");// use png overlay so hide flash
		} else {
			$("#TB_overlay").addClass("TB_overlayBG");// use background and opacity
		}
		if (caption === null) {
			caption = "";
		}
		$("body").append("<div id='TB_load'><img src='" + imgLoader.src + "' /></div>");// add loader to the page
		$('#TB_load').show();// show loader
		// 基础路径
		var baseURL;
		if (url.indexOf("?") !== -1) { // ff there is a query string involved
			baseURL = url.substr(0, url.indexOf("?"));
		} else {
			baseURL = url;
		}
		// 正则表达式
		var urlString = /\.jpg$|\.jpeg$|\.png$|\.gif$|\.bmp$/;
		// url的类型 是不是图片
		var urlType = baseURL.toLowerCase().match(urlString);
		// 图片
		if (urlType == '.jpg' || urlType == '.jpeg' || urlType == '.png' || urlType == '.gif' || urlType == '.bmp') {// code to show images
			TB_PrevCaption = "";
			TB_PrevURL = "";
			TB_PrevHTML = "";
			TB_NextCaption = "";
			TB_NextURL = "";
			TB_NextHTML = "";
			TB_imageCount = "";
			TB_FoundURL = false;
			if (imageGroup) {
				TB_TempArray = $("a[rel=" + imageGroup + "]").get();
				for (TB_Counter = 0; ((TB_Counter < TB_TempArray.length) && (TB_NextHTML === "")); TB_Counter++) {
					var urlTypeTemp = TB_TempArray[TB_Counter].href.toLowerCase().match(urlString);
					if (!(TB_TempArray[TB_Counter].href == url)) {
						if (TB_FoundURL) {
							TB_NextCaption = TB_TempArray[TB_Counter].title;
							TB_NextURL = TB_TempArray[TB_Counter].href;
							TB_NextHTML = "<span id='TB_next'>&nbsp;&nbsp;<a href='#'>Next &gt;</a></span>";
						} else {
							TB_PrevCaption = TB_TempArray[TB_Counter].title;
							TB_PrevURL = TB_TempArray[TB_Counter].href;
							TB_PrevHTML = "<span id='TB_prev'>&nbsp;&nbsp;<a href='#'>&lt; Prev</a></span>";
						}
					} else {
						TB_FoundURL = true;
						TB_imageCount = "Image " + (TB_Counter + 1) + " of " + (TB_TempArray.length);
					}
				}
			}
			imgPreloader = new Image();
			imgPreloader.onload = function() {
				imgPreloader.onload = null;
				// Resizing large images - orginal by Christian Montoya edited by me.
				var pagesize = tb_getPageSize();
				var x = pagesize[0] - 150;
				var y = pagesize[1] - 150;
				var imageWidth = imgPreloader.width;
				var imageHeight = imgPreloader.height;
				if (imageWidth > x) {
					imageHeight = imageHeight * (x / imageWidth);
					imageWidth = x;
					if (imageHeight > y) {
						imageWidth = imageWidth * (y / imageHeight);
						imageHeight = y;
					}
				} else if (imageHeight > y) {
					imageWidth = imageWidth * (y / imageHeight);
					imageHeight = y;
					if (imageWidth > x) {
						imageHeight = imageHeight * (x / imageWidth);
						imageWidth = x;
					}
				}
				// End Resizing
				TB_WIDTH = imageWidth + 30;
				TB_HEIGHT = imageHeight + 60;
				$("#TB_window").append("<a href='' id='TB_ImageOff' title='Close'><img id='TB_Image' src='" + url + "' width='" + imageWidth + "' height='"
				+ imageHeight + "' alt='" + caption + "'/></a>" + "<div id='TB_caption'>" + caption + "<div id='TB_secondLine'>" + TB_imageCount + TB_PrevHTML
				+ TB_NextHTML + "</div></div><div id='TB_closeWindow'><a href='#' id='TB_closeWindowButton' title='Close'><img src='" + closeImage + "'/></a></div>");
				$("#TB_closeWindowButton").click(tb_remove);
				if (!(TB_PrevHTML === "")) {
					function goPrev() {
						if ($(document).unbind("click", goPrev)) {
							$(document).unbind("click", goPrev);
						}
						$("#TB_window").remove();
						$("body").append("<div id='TB_window'></div>");
						tb_show(TB_PrevCaption, TB_PrevURL, imageGroup);
						return false;
					}
					$("#TB_prev").click(goPrev);
				}
				if (!(TB_NextHTML === "")) {
					function goNext() {
						$("#TB_window").remove();
						$("body").append("<div id='TB_window'></div>");
						tb_show(TB_NextCaption, TB_NextURL, imageGroup);
						return false;
					}
					$("#TB_next").click(goNext);
				}
				document.onkeydown = function(e) {
					if (e == null) { // ie
						keycode = event.keyCode;
					} else { // mozilla
						keycode = e.which;
					}
					if (keycode == 27) { // close
						tb_remove();
					} else if (keycode == 190) { // display previous image
						if (!(TB_NextHTML == "")) {
							document.onkeydown = "";
							goNext();
						}
					} else if (keycode == 188) { // display next image
						if (!(TB_PrevHTML == "")) {
							document.onkeydown = "";
							goPrev();
						}
					}
				};
				tb_position();
				$("#TB_load").remove();
				$("#TB_ImageOff").click(tb_remove);
				$("#TB_window").css({
					display	: "block"
				}); // for safari using css instead of show
			};
			imgPreloader.src = url;
		} else {// 不是图片code to show html
			// 获得?后面部分
			var queryString = url.replace(/^[^\?]+\??/, '');
			var params = tb_parseQuery(queryString);
			// 宽,如果没有宽这个参数 默认630
			TB_WIDTH = (params['width'] * 1) + 30 || 630; // defaults to 630 if no paramaters were added to URL
			// 高,如果没有高这个参数 默认440
			TB_HEIGHT = (params['height'] * 1) + 40 || 440; // defaults to 440 if no paramaters were added to URL
			ajaxContentW = TB_WIDTH - 30;
			ajaxContentH = TB_HEIGHT - 45;
			if (url.indexOf('TB_iframe') != -1) {// either iframe or ajax window
				urlNoQuery = url.split('TB_');
				$("#TB_iframeContent").remove();
				if (params['modal'] != "true") {// iframe no modal
					$("#TB_window").append("<div id='TB_title'><div id='TB_ajaxWindowTitle'>" + caption
					+ "</div><div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton' title='Close'><img src='" + closeImage
					+ "'/></a></div></div><iframe frameborder='0' hspace='0' src='" + urlNoQuery[0] + "' id='TB_iframeContent' name='TB_iframeContent"
					+ Math.round(Math.random() * 1000) + "' onload='tb_showIframe()' style='width:" + (ajaxContentW + 29) + "px;height:" + (ajaxContentH + 17)
					+ "px;' > </iframe>");
				} else {// iframe modal
					$("#TB_overlay").unbind();
					$("#TB_window").append("<iframe frameborder='0' hspace='0' src='" + urlNoQuery[0] + "' id='TB_iframeContent' name='TB_iframeContent"
					+ Math.round(Math.random() * 1000) + "' onload='tb_showIframe()' style='width:" + (ajaxContentW + 29) + "px;height:" + (ajaxContentH + 17)
					+ "px;'> </iframe>");
				}
			} else {// 不是iframe/ajax页面 not an iframe, ajax
				if ($("#TB_window").css("display") != "block") {
					if (params['modal'] != "true") {// ajax no modal
						$("#TB_window").append("<div id='TB_title'><div id='TB_ajaxWindowTitle'>" + caption
						+ "</div><div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton'><img src='" + closeImage
						+ "'/></a></div></div><div id='TB_ajaxContent' style='width:" + ajaxContentW + "px;height:" + ajaxContentH + "px'></div>");
					} else {// ajax modal
						$("#TB_overlay").unbind();
						$("#TB_window").append("<div id='TB_ajaxContent' class='TB_modal' style='width:" + ajaxContentW + "px;height:" + ajaxContentH + "px;'></div>");
					}
				} else {// this means the window is already up, we are just loading new content via ajax
					$("#TB_ajaxContent")[0].style.width = ajaxContentW + "px";
					$("#TB_ajaxContent")[0].style.height = ajaxContentH + "px";
					$("#TB_ajaxContent")[0].scrollTop = 0;
					$("#TB_ajaxWindowTitle").html(caption);
				}
			}
			$("#TB_closeWindowButton").click(tb_remove);
			// TB_inline
			if (url.indexOf('TB_inline') != -1) {
				contentId = params['inlineId'];
				// 内容装载进去
				$("#TB_ajaxContent").html($('#' + contentId).html());
				// 内容清空~~~金鑫添加
				$('#' + contentId).html("");
				$("#TB_window").unload(function() {
					// 结束的时候 将元素移回去 move elements back when you're finished
					$('#' + contentId).html($("#TB_ajaxContent").html());
				});
				// 定位
				tb_position();
				$("#TB_load").remove();
				$("#TB_window").css({
					display	: "block"
				});
				// $("#TB_window select").css("display", "block");
			}
			// TB_iframe
			else if (url.indexOf('TB_iframe') != -1) {
				tb_position();
				if ($.browser.safari) {// safari needs help because it will not fire iframe onload
					$("#TB_load").remove();
					$("#TB_window").css({
						display	: "block"
					});
				}
			}
			// TB_ajaxContent
			else {
				$("#TB_ajaxContent").load(url += "&random=" + (new Date().getTime()), function() {// to do a post change this load method
					tb_position();
					$("#TB_load").remove();
					tb_init("#TB_ajaxContent a.thickbox");
					$("#TB_window").css({
						display	: "block"
					});
				});
			}
		}
		if (!params['modal']) {
			document.onkeyup = function(e) {
				if (e == null) { // ie
					keycode = event.keyCode;
				} else { // mozilla
					keycode = e.which;
				}
				if (keycode == 27) { // close
					tb_remove();
				}
			};
		}
	} catch (e) {
		// nothing here
	}
}
// helper functions below
function tb_showIframe() {
	$("#TB_load").remove();
	$("#TB_window").css({
		display	: "block"
	});
}
/**
 * 移除
 * 
 * @return {Boolean}
 */
function tb_remove() {
	if ("" != contentId) {
		$("#TB_window select").css("display", "none");
	}
	$("#TB_imageOff").unbind("click");
	$("#TB_closeWindowButton").unbind("click");
	$("#TB_window").fadeOut("fast", function() {
		$('#TB_window,#TB_overlay,#TB_HideSelect').trigger("unload").unbind().remove();
	});
	$("#TB_load").remove();
	if (typeof document.body.style.maxHeight == "undefined") {// if IE 6
		$("body", "html").css({
			height	: "auto",
			width		: "auto"
		});
		$("html").css("overflow", "");
	}
	document.onkeydown = "";
	document.onkeyup = "";
	return false;
}
/**
 * 定位
 */
function tb_position() {
	$("#TB_window").css({
		marginLeft	: '-' + parseInt((TB_WIDTH / 2), 10) + 'px',
		width				: TB_WIDTH + 'px'
	});
	if (!(jQuery.browser.msie && jQuery.browser.version < 7)) { // take away IE6
		$("#TB_window").css({
			marginTop	: '-' + parseInt((TB_HEIGHT / 2), 10) + 'px'
		});
	}
}
/**
 * 转换参数
 * 
 * @param query
 * @return
 */
function tb_parseQuery(query) {
	var Params = {};
	if (!query) {
		return Params;
	}// return empty object
	// & 转换为数组
	var Pairs = query.split(/[;&]/);
	for (var i = 0; i < Pairs.length; i++) {
		// 每个参数=号转换为数组
		var KeyVal = Pairs[i].split('=');
		// 不是=关系的时候
		if (!KeyVal || KeyVal.length != 2) {
			continue;
		}
		var key = unescape(KeyVal[0]);
		var val = unescape(KeyVal[1]);
		val = val.replace(/\+/g, ' ');
		Params[key] = val;
	}
	return Params;
}
function tb_getPageSize() {
	var de = document.documentElement;
	var w = window.innerWidth || self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
	var h = window.innerHeight || self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;
	arrayPageSize = [w, h];
	return arrayPageSize;
}
/**
 * 判断浏览器是不是MacXFF
 * 
 * @return
 */
function tb_detectMacXFF() {
	var userAgent = navigator.userAgent.toLowerCase();
	if (userAgent.indexOf('mac') != -1 && userAgent.indexOf('firefox') != -1) {
		return true;
	}
}
/**
 * 显示层信息
 * 
 * @param caption
 * @param html
 * @param width
 * @param height
 * @return
 */
function tb_showHTML(caption, html, width, height) {
	// $.support.boxModel
	if (typeof document.body.style.maxHeight === "undefined") {// if IE 6
		$("body", "html").css({
			height	: "100%",
			width		: "100%"
		});
		$("html").css("overflow", "hidden");
		if (document.getElementById("TB_HideSelect") === null) {// iframe to hide select elements in ie6
			$("body").append("<iframe id='TB_HideSelect'></iframe><div id='TB_overlay'></div><div id='TB_window'></div>");
			$("#TB_overlay").click(tb_remove);
		}
	} else {
		if (document.getElementById("TB_overlay") === null) {
			$("body").append("<div id='TB_overlay'></div><div id='TB_window'></div>");
			$("#TB_overlay").click(tb_remove);
		}
	}
	// 判断浏览器是不是MacXFF
	if (tb_detectMacXFF()) {
		$("#TB_overlay").addClass("TB_overlayMacFFBGHack");// use png overlay so hide flash
	} else {
		$("#TB_overlay").addClass("TB_overlayBG");// use background and opacity
	}
	if (caption === null) {
		caption = "";
	}
	$("body").append("<div id='TB_load'><img src='" + imgLoader.src + "' /></div>");// add loader to the page
	$('#TB_load').show();// show loader
	// 宽,如果没有宽这个参数 默认630
	TB_WIDTH = width; // defaults to 630 if no paramaters were added to URL
	// 高,如果没有高这个参数 默认440
	TB_HEIGHT = height; // defaults to 440 if no paramaters were added to URL
	ajaxContentW = TB_WIDTH - 30;
	ajaxContentH = TB_HEIGHT - 45;
	// 不是iframe/ajax页面 not an iframe, ajax
	if ($("#TB_window").css("display") != "block") {
		$("#TB_window").append("<div id='TB_title'><div id='TB_ajaxWindowTitle'>" + caption
		+ "</div><div id='TB_closeAjaxWindow'><a href='#' id='TB_closeWindowButton'><img src='" + closeImage
		+ "'/></a></div></div><div id='TB_ajaxContent' style='width:" + ajaxContentW + "px;height:" + ajaxContentH + "px'></div>");
	} else {// this means the window is already up, we are just loading new content via ajax
		$("#TB_ajaxContent")[0].style.width = ajaxContentW + "px";
		$("#TB_ajaxContent")[0].style.height = ajaxContentH + "px";
		$("#TB_ajaxContent")[0].scrollTop = 0;
		$("#TB_ajaxWindowTitle").html(caption);
	}
	$("#TB_closeWindowButton").click(tb_remove);
	// 内容装载进去
	$("#TB_ajaxContent").html(html);
	tb_position();
	$("#TB_load").remove();
	$("#TB_window").css({
		display	: "block"
	});
	// $("#TB_window select").css("display", "block");
	document.onkeyup = function(e) {
		if (e == null) { // ie
			keycode = event.keyCode;
		} else { // mozilla
			keycode = e.which;
		}
		if (keycode == 27) { // close
			tb_remove();
		}
	};
}