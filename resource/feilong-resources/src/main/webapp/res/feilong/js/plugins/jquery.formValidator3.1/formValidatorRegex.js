var regexEnum = {
	// 邮件
	email							: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$",
	// 颜色
	color							: "^[a-fA-F0-9]{6}$",
	// url
	url								: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$",
	// 仅中文
	chinese						: "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$",
	// 2-25个中文
	chinese2_25				: "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{2,25}$",
	// 邮编
	zipcode						: "^\\d{6}$",
	// 手机
	mobile						: "^(13|15|18)[0-9]{9}$",
	// ip地址
	ip4								: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$",
	// 图片
	picture						: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$",
	// 压缩文件
	rar								: "(.*)\\.(rar|zip|7zip|tgz)$",
	// 日期
	date							: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$",
	// QQ号码
	qq								: "^[1-9]*[1-9][0-9]*$",
	// 电话号码的函数(包括验证国内区号,国际区号,分机号)
	tel								: "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$",
	// 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串
	username					: "^\\w+$",
	// 身份证
	idcard						: "^[1-9]([0-9]{14}|[0-9]{17})$",
	// 密码
	pass							: "^.{6,16}$",
	// 5-16个注册字符
	registerName5_16	: "^[a-zA-Z0-9][a-zA-Z0-9_\\.-]{2,14}[a-zA-Z0-9]$",
	// 整数
	intege						: "^-?[1-9]\\d*$",
	// 正整数
	intege1						: "^[1-9]\\d*$",
	// 负整数
	intege2						: "^-[1-9]\\d*$",
	// 正整数>0 （自增）
	intege3						: "^[1-9][0-9]*$",
	// 数字
	num								: "^([+-]?)\\d*\\.?\\d+$",
	// 正数（正整数 + 0）
	num1							: "^[1-9]\\d*|0$",
	// 负数（负整数 + 0）
	num2							: "^-[1-9]\\d*|0$",
	// 浮点数
	decmal						: "^([+-]?)\\d*\\.\\d+$",
	// 正浮点数
	decmal1						: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$",
	// 负浮点数
	decmal2						: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$",
	// 浮点数
	decmal3						: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$",
	// 非负浮点数（正浮点数 + 0）
	decmal4						: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$",
	// 非正浮点数（负浮点数 + 0）
	decmal5						: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$",
	// 仅ACSII字符
	ascii							: "^[\\x00-\\xFF]+$",
	// 字母
	letter						: "^[A-Za-z]+$",
	// 大写字母
	letter_u					: "^[A-Z]+$",
	// 小写字母
	letter_l					: "^[a-z]+$",
	// 非空
	notempty					: "^\\S+$"
}
var aCity = {
	11	: "北京",
	12	: "天津",
	13	: "河北",
	14	: "山西",
	15	: "内蒙古",
	21	: "辽宁",
	22	: "吉林",
	23	: "黑龙江",
	31	: "上海",
	32	: "江苏",
	33	: "浙江",
	34	: "安徽",
	35	: "福建",
	36	: "江西",
	37	: "山东",
	41	: "河南",
	42	: "湖北",
	43	: "湖南",
	44	: "广东",
	45	: "广西",
	46	: "海南",
	50	: "重庆",
	51	: "四川",
	52	: "贵州",
	53	: "云南",
	54	: "西藏",
	61	: "陕西",
	62	: "甘肃",
	63	: "青海",
	64	: "宁夏",
	65	: "新疆",
	71	: "台湾",
	81	: "香港",
	82	: "澳门",
	91	: "国外"
}
/**
 * 是否是省份证
 * 
 * @param {string}
 *          sId 身份证号码
 * @return {String} 验证提示
 */
function isCardID(sId) {
	var iSum = 0;
	var info = "";
	if (!/^\d{17}(\d|x)$/i.test(sId))
		return "你输入的身份证长度或格式错误";
	sId = sId.replace(/x$/i, "a");
	if (aCity[parseInt(sId.substr(0, 2))] == null)
		return "你的身份证地区非法";
	sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
	var d = new Date(sBirthday.replace(/-/g, "/"));
	if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate()))
		return "身份证上的出生日期非法";
	for (var i = 17; i >= 0; i--)
		iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
	if (iSum % 11 != 1)
		return "你输入的身份证号非法";
	return true;// aCity[parseInt(sId.substr(0,2))]+","+sBirthday+","+(sId.substr(16,1)%2?"男":"女")
}
// 短时间，形如 (13:04:06)
function isTime(str) {
	var a = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/);
	if (a == null) {
		return false
	}
	if (a[1] > 24 || a[3] > 60 || a[4] > 60) {
		return false;
	}
	return true;
}
// 短日期，形如 (2003-12-05)
function isDate(str) {
	var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}
// 长时间，形如 (2003-12-05 13:04:06)
function isDateTime(str) {
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/;
	var r = str.match(reg);
	if (r == null)
		return false;
	var d = new Date(r[1], r[3] - 1, r[4], r[5], r[6], r[7]);
	return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4] && d.getHours() == r[5] && d.getMinutes() == r[6] && d.getSeconds() == r[7]);
}