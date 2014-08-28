// 图片滚动列表 mengjia 070816

var Speed = 180;// 速度(毫秒)
var Space = 5;// 每次移动(px)
var fill = 0;// 整体移位
var PageWidth = 528;// 翻页宽度
var Comp = 0;
var MoveLock = false;// 是否移动 默认false
var MoveTimeObj = null;// 时间滚动定时器
var AutoPlayObj = null;// 自动滚动定时器

// 最后一次操作的左按钮索引
var index_leftButton = 0;
// 最后一次操作的右按钮索引
var index_RightButton = 0;

$(function() {
	// 右翻--->
	$(".RightBotton").click(function() {
				scrollRight(this);
			}).mouseout(function() {
				// stopScrollRight(this);
			});
	// 左翻<----
	$(".LeftBotton").mousedown(function() {
				index_leftButton = $(".LeftBotton").index(this);
				alert(index_leftButton);
				scrollLeft(this);
			}).mouseout(function() {
				// ISL_StopUp(this);
			});

	// 滚动元素
	$(".Cont").mouseover(function() {
				clearInterval(AutoPlayObj);
			}).mouseout(function() {
				// AutoPlay();
			});

		/**
		 * 自动滚动
		 */
		// AutoPlay();
	});

// ********************************************************************************
// 下翻
function scrollRight(obj) {
	// 关闭移动定时器
	clearInterval(MoveTimeObj);
	// 如果移动,return
	// if (MoveLock)
	// return;
	// 关闭自动滚动定时器
	clearInterval(AutoPlayObj);
	// 标识开始移动
	MoveLock = true;

	// 开启定时器
	MoveTimeObj = setInterval(function() {

				// 滚动对象 加入缓存
				var JQscroolDiv = $(obj).prev(".Cont");
				// 滚动列表
				var JQscroolDivList = JQscroolDiv.find(".div_scroolList");
				// 左边偏移量
				var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
				// 列表内容总宽度
				var JQscroolDivList_scrollWidth = JQscroolDivList[0].scrollWidth;

				if (JQscroolDiv_scrollLeft >= JQscroolDivList_scrollWidth) {
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft - JQscroolDivList_scrollWidth);
				}
				JQscroolDiv.scrollLeft(JQscroolDiv.scrollLeft() + Space);
			}, Speed);
}

// 下翻停止
function stopScrollRight(obj) {
	clearInterval(MoveTimeObj);
	// 滚动对象 加入缓存
	var JQscroolDiv = $(obj).prev(".Cont");
	// 左边偏移量
	var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
	if (JQscroolDiv_scrollLeft % PageWidth - fill != 0) {
		Comp = PageWidth - JQscroolDiv_scrollLeft % PageWidth + fill;
		setInterval(function() {
					var num;
					if (Comp == 0) {
						MoveLock = false;
						return;
					}
					if (Comp > Space) {
						Comp -= Space;
						num = Space;
					} else {
						num = Comp;
						Comp = 0;
					}
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft + num);
				}, Speed);
	} else {
		MoveLock = false;
	}
	// AutoPlay();
}

// *********************************************************************************
// 上翻开始
function scrollLeft(obj) {
	// if (MoveLock)
	// return;
	clearInterval(MoveTimeObj);
	clearInterval(AutoPlayObj);
	MoveLock = true;
	MoveTimeObj = setInterval(function() {
				// 滚动对象 加入缓存
				var JQscroolDiv = $(obj).next(".Cont");
				// 滚动列表
				var JQscroolDivList = JQscroolDiv.find(".div_scroolList");
				// 左边偏移量
				var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
				// 列表内容总宽度
				var JQscroolDivList_offsetWidth = JQscroolDivList[0].offsetWidth;
				if (JQscroolDiv_scrollLeft <= 0) {
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft + JQscroolDivList_offsetWidth);
				}
				JQscroolDiv.scrollLeft(JQscroolDiv.scrollLeft() - Space);
			}, Speed);
}
// 上翻停止
function ISL_StopUp() {
	clearInterval(MoveTimeObj);
	// 滚动对象 加入缓存
	var JQscroolDiv = $(obj).next(".Cont");
	// 左边偏移量
	var JQscroolDiv_scrollLeft = JQscroolDiv.scrollLeft();
	if (JQscroolDiv_scrollLeft % PageWidth - fill != 0) {
		Comp = fill - (JQscroolDiv_scrollLeft % PageWidth);
		setInterval(function() {
					var num;
					if (Comp == 0) {
						MoveLock = false;
						return;
					}
					if (Comp < -Space) {
						Comp += Space;
						num = Space;
					} else {
						num = -Comp;
						Comp = 0;
					}
					JQscroolDiv.scrollLeft(JQscroolDiv_scrollLeft - num);
				}, Speed);
	} else {
		MoveLock = false;
	}
	// AutoPlay();
}

// ********************************************************************************
// 自动滚动
function AutoPlay() {
	clearInterval(AutoPlayObj);
	AutoPlayObj = setInterval('scrollRight();stopScrollRight();', 3000); // 间隔时间
}