/**
 * <pre>
 * 功能
 * :   图片无缝滚动(多层滚动无干涉)
 *     	Company :   宝尊
 *      作者		:  	金鑫(飞天奔月) venusdrogon@163.com
 *     	参考		:	图片滚动列表 mengjia 070816
 * 
 * 修改时间:
 *      第一次	:	2010-10-19  金鑫(飞天奔月)
 *      第二次	:	2010-10-20 10:44 完善自动滚动
 * </pre>
 */
/** ********************************************************* */
/**
 * 速度(毫秒)
 * 
 * 
 */
var Speed = 1;
/**
 * 每次移动(px)
 * 
 * 
 */
var Space = 5;
/**
 * 翻页宽度
 * 
 * 
 */
var PageWidth = 528;
/**
 * 整体移位
 * 
 * 
 */
var fill = 0;
var Comp = 0;

var MoveLock = false;

var MoveTimeObj = null;
var AutoPlayObj = null;

// 当前操作的滚动层
var _currentScrollDiv = null;

/**
 * 滚动层的className
 * 
 * 
 */
var className_scrollDiv = ".Cont";
// 
/**
 * 显示数据内容的div className
 * 
 * 
 */
var className_listDiv = ".List1";
// *************************************
$(function() {

			$(".List2").html(function(index, html) {
						return $(this).prev(className_listDiv).html();
					});

			/**
			 * 滚动层自身事件
			 */
			$(className_scrollDiv).scrollLeft(fill).mouseover(function() {
						_currentScrollDiv = $(this);
						clearInterval(AutoPlayObj);
					}).mouseout(function() {
						AutoPlay();
					});

			// 右翻--->
			$(".RightBotton").mousedown(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						ISL_GoDown(_currentScrollDiv);
					}).mouseout(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						scrollRight_stop(_currentScrollDiv);
					}).mouseup(function() {
						_currentScrollDiv = $(this).prev(className_scrollDiv);
						scrollRight_stop(_currentScrollDiv);
					});
			// 左翻<---
			$(".LeftBotton").mousedown(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_GoUp(_currentScrollDiv);
					}).mouseout(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_StopUp(_currentScrollDiv);
					}).mouseup(function() {
						_currentScrollDiv = $(this).next(className_scrollDiv);
						ISL_StopUp(_currentScrollDiv);
					});

			/**
			 * 自动滚动
			 */
			AutoPlay();

		});

// ************************自动滚动************************************
// 自动滚动
function AutoPlay() {
	clearInterval(AutoPlayObj);
	AutoPlayObj = setInterval(function() {
				ISL_GoDown();
				scrollRight_stop();
			}, 3000); // 间隔时间
}
// ***************************下翻*********************************
// 
/**
 * 下翻 金鑫
 * 
 * @param {jquery对象}
 *            JQObjScroll 需要滚动的层(jquery对象)
 */
function ISL_GoDown() {
	// $("#span_test").html(parseInt($("#span_test").html(), 10) +1).css("color", "#FF00FF");
	clearInterval(MoveTimeObj);
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	if (null == _currentScrollDiv) {
		_currentScrollDiv = $($(className_scrollDiv)[0]);
	}
	// 自动滚动时先调用一次
	scrollRight();
	MoveTimeObj = setInterval(scrollRight, Speed);
}

/**
 * 右移核心方法
 */
function scrollRight() {
	// 滚动元素 的scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	// 滚动元素 下面的list1元素 的scrollWidth
	var JQObjScrollList1_scrollWidth = _currentScrollDiv.find(className_listDiv).get(0).scrollWidth;
	if (JQObjScroll_scrollLeft >= JQObjScrollList1_scrollWidth) {
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft - JQObjScrollList1_scrollWidth;
	}
	_currentScrollDiv.get(0).scrollLeft = _currentScrollDiv.get(0).scrollLeft + Space;

}
/**
 * 下翻停止 金鑫
 * 
 * @param {jquery对象}
 *            JQObjScroll JQObjScroll 需要滚动的层(jquery对象)
 */
function scrollRight_stop() {
	clearInterval(MoveTimeObj);
	// 滚动元素 的scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (JQObjScroll_scrollLeft % PageWidth - fill != 0) {
		Comp = PageWidth - JQObjScroll_scrollLeft % PageWidth + fill;
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}

// **************************上翻*********************************
// 上翻开始
function ISL_GoUp() {
	if (MoveLock)
		return;
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval(function() {
				// 滚动元素 的scrollLeft
				var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
				// 滚动元素 下面的list1元素 的offsetWidth
				var JQObjScrollList1_offsetWidth = _currentScrollDiv.find(className_listDiv).get(0).offsetWidth;
				if (JQObjScroll_scrollLeft <= 0) {
					_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft + JQObjScrollList1_offsetWidth;
				}
				_currentScrollDiv.get(0).scrollLeft = _currentScrollDiv.get(0).scrollLeft - Space;
			}, Speed);
}

// 上翻停止
function ISL_StopUp() {
	clearInterval(MoveTimeObj);
	// 滚动元素 的scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (JQObjScroll_scrollLeft % PageWidth - fill != 0) {
		Comp = fill - (JQObjScroll_scrollLeft % PageWidth);
		CompScr();
	} else {
		MoveLock = false;
	}
	AutoPlay();
}

// ******************停止控制操作**********************
function CompScr() {
	var num;
	if (Comp == 0) {
		MoveLock = false;
		return;
	}
	// 滚动元素 的scrollLeft
	var JQObjScroll_scrollLeft = _currentScrollDiv.get(0).scrollLeft;
	if (Comp < 0) { // 上翻
		if (Comp < -Space) {
			Comp += Space;
			num = Space;
		} else {
			num = -Comp;
			Comp = 0;
		}
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft - num;

	} else { // 下翻
		if (Comp > Space) {
			Comp -= Space;
			num = Space;
		} else {
			num = Comp;
			Comp = 0;
		}
		_currentScrollDiv.get(0).scrollLeft = JQObjScroll_scrollLeft + num;
	}
	setTimeout('CompScr()', Speed);
}
