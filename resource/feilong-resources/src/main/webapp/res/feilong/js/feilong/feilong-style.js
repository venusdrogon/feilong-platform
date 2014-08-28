/*
	作者		:	金鑫
	Company	:	飞龙公司
	时间		:	2008年9月16日
	
	功能		:	给所有的表单元素赋予样式
	
	修改时间	:
			v1.01:	2008年09月17日  金鑫
			v1.02:	2008年10月21日 金鑫
			v1.03:	2008年10月13日 金鑫 回车按钮变tab
			v1.04:	2008年11月23日 15:47:26  添加飞龙自制弹出层
			v1.05:	2008年11月29日 19:48:22  添加飞龙自制js合并单元格
			v1.06:	2009年1月2日 11:49:13    完善并添加飞龙自定义一些样式     	
			v1.07:	2009年1月7日 10:45:20    添加飞龙自定义复选框样式    
			v1.08:	2009年7月3日 10:13:23    优化文本框样式代码 
			v1.09:	2009年8月27日 16:02:45 分离initInputStyle 以及 initInputBehavior
 */
var res = {
	color : {
		trSelect_bg :"#FFFDD7", // 行复选框选中行，行背景颜色
		trUnSelect_bg :"#fff" // 行复选框不选中行，行背景颜色
	}
};
/**
 * 初始化文本框样式
 */
var text = {
	width :"260px",
	height :"24px",
	focus : {
		borderColor :"#FFCC00",
		backgroundColor :"#FFFFF7"
	},
	blur : {
		borderColor :"#BBE1F1",
		backgroundColor :"#ffe"
	}
};
$( function() {
	$("a").click( function() {
		$(this).blur();
	});
	// 文本框、密码框、单选框 回车变table键
	/*
	 * $(":text,:password,:radio,textarea").keydown( function() { if (event.keyCode == 13) { event.keyCode = 9; } });
	 */
	$("button").click( function() {
		$(this).blur();
	});
});
/**
 * 加载input元素的样式
 * 
 * @return
 */
function initInputStyle() {
	$(":text,:password").css( {
		font :"12px Verdana",
		padding :"4px 0",
		height :text.height
	});
}
/**
 * 初始化input元素的动作
 * 
 * @return
 */
function initInputBehavior() {
	$(":text,:password,textarea").css( {
		color :"#000",
		border :"1px solid " + text.blur.borderColor + "",
		background :text.blur.backgroundColor
	}).focus( function() {
		$(this).css( {
			"border-color" :text.focus.borderColor,
			"background-color" :text.focus.backgroundColor
		}).select();
	}).blur( function() {
		$(this).css( {
			"border-color" :text.blur.borderColor,
			"background-color" :text.blur.backgroundColor
		})
	});
}
/**
 * 初始化按钮 提交按钮样式
 * 
 * @return
 */
function initButton() {
	/* 按钮 提交按钮样式 */
	$("input[type='submit'],input[type='button'],input[type='reset']").css( {
		height :"28px",
		padding :"0 8px",
		margin :"2px",
		cursor :"pointer",
		"font-size" :"15px",
		background :"#EEFAFF",
		"text-align" :"center",
		"line-height" :"150%",
		border :"1px solid #BBE1F1"
	}).hover( function() {
		$(this).css( {
			"background" :"#FFFDD7",
			"border-color" :"green"
		});
	}, function() {
		$(this).css( {
			"background" :"#EEFAFF",
			"border-color" :"#BBE1F1"
		});
	}).focus( function() {
		this.blur();
	});
}
/* 定义上传文本框的样式 */
function file() {
	$("input[type='file']").css( {
		color :"#000",
		width :text.width,
		height :text.height
	});
}
/**
 * 判断全选复选框要不要选中
 */
function judgeSelectAll() {
	if ($(".cbo_all").length != 0) {
		var flag = true;
		var onelen = $(".cbo_one").length;
		for ( var i = 0; i < onelen; i++) {
			if (!$(".cbo_one")[i].checked)
				flag = false;
		}
		$(".cbo_all").attr("checked", flag);
		if (flag) {
			if (!$(".cbo_all+label").hasClass("checked"))
				$(".cbo_all+label").addClass("checked");
		} else {
			if ($(".cbo_all+label").hasClass("checked"))
				$(".cbo_all+label").removeClass("checked");
		}
	}
}
/**
 * 表格样式
 * 
 * @return
 */
function tableStyle() {
	$.each($("table"), function(i, n) {
		$.each($(n).find("tr"), function(j, m) {
			$.each($(m).find("td"), function(z, td) {
				if (z == 0) {
					$(td).addClass("td_title");
				}
				if (z == 2) {
					$(td).find("span").addClass("tishi");
				}
			});
		});
	});
}