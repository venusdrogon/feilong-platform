var isIE;
var isFF;
var title = document.title;
var url = location.href;
// 颜色
var color = {
	txtWarnBack					: "#FF8080",
	warnColor						: "red",
	rightColor					: "green",
	original						: {
		foreColor				: null,
		backgroundColor	: null
	},
	passIntensityColor	: ["#F00", "#FC0", "#090"]
};
// 正则表达式
var patterns = {
	loginName		: /^[a-zA-Z0-9][\w\._-]{2,16}[a-zA-Z0-9]$/,
	pass				: /^.{6,16}$/,
	tel					: /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/,// 区号+座机号码+分机号码:
	chineseName	: /^[\u4e00-\u9fa5]{2,10}$/, // 中文姓名
	phone				: /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/,// 所有的手机号码
	email				: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,// 邮件
	address			: /=|or/gi,// 地址
	realName		: /^(([a-zA-Z]{4,12})|([\u4e00-\u9fa5]{2,5}))$/
	// 真实姓名
};
// 空值提示
var nullWarn = {
	loginName	: "用户名不能为空",
	pass1			: "用户密码不能为空",
	pass2			: "确认密码不能为空",
	serial		: "验证码不能为空",
	email			: "用户邮箱不能为空",
	cboItem		: "不同意中国招工会员协议,不能注册中国招工会员"
};
var nameWarnString = "用户名格式不正确";
nameWarnString += "<br/>由字母a-zA-Z.0-9.点.减号或下划线组成";
nameWarnString += "<br/>只能以数字或字母开头和结尾";
nameWarnString += "<br/>不能出现特殊字符,如= 空格";
var passWarnString = "密码格式不正确";
passWarnString += "<br/>可以用到的字符:A-Za-z0-9 减号 点　和下划线";
var realNameWarnString = "真实姓名格式不正确";
realNameWarnString += "<br/>只能是4-12个英文字母或2-5个中文字符";
// 声明长度
var charLength = {
	minLoginName	: 4,
	maxLoginName	: 18,
	minPass				: 6,
	maxPass				: 16,
	maxPhone			: 30,
	minEmail			: 8,
	maxEmail			: 50,
	minAddress		: 2,
	maxAddress		: 60,
	minRealName		: 2,
	maxRealName		: 12,
	passIntensity	: [1, 2, 3]
};
// 声明提示说明
var formatWarn = {
	minLoginName		: "用户名长度至少为" + charLength.minLoginName + "个字符",
	maxLoginName		: "用户名长度不能大于" + charLength.maxLoginName + "个字符",
	minPass1				: "密码太短,长度为" + charLength.minPass + "-" + charLength.maxPass + "位",
	maxPass1				: "密码太长,长度为" + charLength.minPass + "-" + charLength.maxPass + "位",
	notNullPass			: "至少有6个任意非空字符,比如! @ # 等",
	equalsPass			: "您两次输入的登录密码不一致",
	maxPhone				: "电话太长<br/>可不填,填写则长度不能超过" + charLength.maxPhone + "个字符!",
	minEmail				: "邮箱太短<br/>长度至少" + charLength.minEmail + "个字符!",
	maxEmail				: "邮箱太长<br/>长度不能超过" + charLength.maxEmail + "个字符!",
	minAddress			: "地址太短<br/>可不填,填写则长度至少" + charLength.minAddress + "个字符!",
	maxAddress			: "地址太长<br/>可不填,填写则长度不能超过" + charLength.maxAddress + "个字符!",
	minRealName			: "真实姓名太短<br/>长度至少" + charLength.minRealName + "个字符!",
	maxRealName			: "真实姓名太长<br/>长度不能超过" + charLength.maxRealName + "个字符!",
	formatLoginName	: nameWarnString,
	formatPass			: passWarnString,
	formatRealName	: realNameWarnString,
	formatAddress		: "地址不能包含限制字符,如=,or等",
	formatEmail			: "邮箱格式不正确,请重新输入",
	formatPhone			: "电话格式不正确,请重新输入"
};
// 密码强度提示
var passIntensityWarn = {
	prefixString	: "密码强度:"
};
// 密码强度
var passIntensityString = ["弱", "中", "强"];
// 禁止使用的名字
var tabooName = ["管理员", "系统管理员", "招工管理员", "招工网管理员", "招工管理员", "招工网管理员", "江泽民", "胡锦涛"]
// ajax验证提示
var ajaxWarn = {
	loginNameDisenable	: "<div class='failWarn'>该用户名已经被使用<br/>您可以重新输入用户名或使用该用户名<a href='login.jsp?t=r&v=%'>登录</a></div>",
	loginNameEnable1		: "恭喜你,",
	loginNameEnable2		: "还没有被注册",
	emailDisenable			: "该邮箱已经被人注册过,<br/>为了您能收到准确及时的信息,请换个邮箱",
	emailEnable					: "恭喜你,该邮箱可以使用",
	serialRight					: "验证码输入正确",
	serialError					: "验证码错误"
};
// /^([\u4E00-\u9FA5]|[\uFE30-\uFFA0]|[_\a-zA-Z0-9]|[\s])*$/gi;
// 常规密码 /^[\w\._]{6,16}$/
// 身份证
// ^[0-9]{6}19[2-9][0-9](([0][1-9])|([1][012]))(([0][1-9])|([1][0-9])|([2][0-9])|([3][01]))[0-9]{4}$
$(function() {
	var browserName = navigator.appName;
	isIE = browserName == "Microsoft Internet Explorer";
	isFF = browserName == "Netscape";
	// png透明
	// $(document).pngFix();
	/**
	 * 删除操作
	 */
	$(".a_operate_delete").click(function() {
		if (confirm("确定删除选定的纪录吗?")) {
			return true;
		}
		return false;
	});
	// 回到首页
	$(".index").click(function() {
		location.href = "index.jsp";
	});
	// 自定义提交按钮
	$(".btn_submit").click(function() {
		$(this).parents("form").submit();
	});
	// 返回
	$(".back").click(function() {
		history.go(-1);
	});
	// 关闭
	$(".close").click(function() {
		window.opener = null;
		window.open("", "_self");
		window.close();
	});
	// 验证码
	$("#img_code").click(function() {
		$(this).attr("src", $(this).attr("src") + "?" + new Date().getTime());
	});
	// 彩色文本框
	$(".colorText").focus(function() {
		$(this).css({
			"border-color"	: "#9ECC00"
		}).select();
	}).blur(function() {
		$(this).css({
			"border-color"	: "#84A1BD"
		})
	});
	// 移动
	$("marquee").mouseover(function() {
		this.stop();
	}).mouseout(function() {
		this.start();
	});
	// 层显示不显示切换 控制
	$(".toggleDiv").toggle(function() {
		var btnContent = $(this).html();
		var exchangeContent = $(this).attr("exchangeContent");
		// 控制器交换内容
		$(this).attr("exchangeContent", btnContent);
		$(this).html(exchangeContent);
		$($(this).attr("controlSelector")).css("display", "block");
	}, function() {
		var btnContent = $(this).html();
		var exchangeContent = $(this).attr("exchangeContent");
		// 控制器交换内容
		$(this).attr("exchangeContent", btnContent);
		$(this).html(exchangeContent);
		$($(this).attr("controlSelector")).css("display", "none");
	});
	// 刷新
	$("#btn_Refresh").click(function() {
		refresh();
	});
	// 加入收藏
	$("#a_favorite,.a_favorite").click(function() {
		addFavorite();
	});
	// 主页
	$("#a_homePage,.a_homePage").click(function() {
		setHomePage(this);
	});
	/**
	 * 只能填写数字,自动替换非数字
	 */
	$(".text_feilongNumber").keyup(function() {
		$(this).val($(this).val().replace(/[^0-9.]/g, ''));
	});
});