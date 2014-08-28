/**
 * <pre>
 * 作者
 * :   金鑫(飞天奔月)
 *     Company :   上海涣元
 *     时间    :   2008年9月16日
 * 
 *     功能    :   验证注册
 * 
 * 修改时间:
 *         第一期修改:9月17日  金鑫(飞天奔月)
 *         第二次修改:10月20日 修改
 *         第三次修改:10月23日 构造主体
 *         第四次修改:2008年10月24日 19:39:33
 *         第五次修改:2008年11月4日 19:08:31 重新构建 优化 主体构思完毕
 *         第六次修改:2008年11月5日 10:23:20 完善
 *         第七次修改:2008年11月5日 14:10:28 加入Ajax验证
 *         第八次修改;2008年11月7日 17:35:00 加入验证码验证 并且优化代码
 *         第九次修改:2008年11月8日 08:43:53 查找一些细节不合理的地方
 *         第十次修改:2008年11月12日 18:52:02 添加密码强度提示
 *         第11次修改:2008年11月13日 09:32:54　优化系统
 *         第12次修改:2009年9月15日 15:39:48  修改成中国招工特有参数
 * </pre>
 */
/**
 * <pre>
 *  飞龙javascript语言规范:
 * 
 *  函数参数说明:
 * 
 *  飞龙通用参数:        
 *  	selector                :   选择器
 *  	thiswarn                :   提示语句
 *  	stringValue             :   字符串的值
 *  	pattern                 :   匹配的正则表达式
 *  	isTrim                  :   是否去除空格
 *  	returnValue             :   返回值类型　
 *  	void            		:   无返回值
 *  	string          		:   字符串型
 *  	bool            		:   布尔型
 *  	时间                   	:   表示开发的时间
 * 
 * 
 *  本js参数:
 *  	thisAjaxValidateType    :   ajax验证的类型
 *  	yesWarn                 :   验证正确的提示
 *  	noWarn                  :   验证错误的提示
 *  	minLength               :   当前选择器对应的对象数值的最小长度
 *  	maxLength               :   当前选择器对应的对象数值的最大长度
 *  	minWarn                 :   小于最小长度时的提示字符串
 *  	maxWarn                 :   大于最小长度时的提示字符串
 * 
 *  本js作者:金鑫(飞天奔月)   
 * </pre>
 */
var tagName = "div";
var warnClassName = "warn";
// 提示第一个不通过验证的jquery对象
var firstQuery = null;
// 当前选择器
var thisSelector;
// 当前文本框的值
var thisValue;
// 选择器
var selectors = {
	loginName		: "#userName",
	pass1				: "#userPass1",
	pass2				: "#userPass2",
	realName		: "#txtName",
	email				: "#userEmail",
	phone				: "#txtPhone",
	address			: "#txtAddress",
	btnRegister	: "#btn_Register",
	serial			: "#text_code",// 验证
	cboItem			: "#cbo_agree",// 条款复选框
	hiddenJs		: "#CheckJavascript",
	checkJS			: "#noJs"
};
$(function() {
	// 清空没有开启javascript说明
	// $(selectors.checkJS).text("");
	// $(selectors.hiddenJs).val("true");
	color.original.foreColor = $(selectors.loginName).css("color");
	color.original.backgroundColor = $(selectors.loginName).css("background-color");
	// 密码强度每段宽度
	var eachSectWidth = parseInt($(selectors.loginName).css("width")) / 3;
	charLength.passIntensity[0] = eachSectWidth * 1;
	charLength.passIntensity[1] = eachSectWidth * 2;
	charLength.passIntensity[2] = eachSectWidth * 3;
	// 用户名
	$(selectors.loginName).blur(function() {
		if (validateLoginName()) {
			// ajax验证用户名
			UserAjax.isRepeatUserName(FeiLongUtil.getValue(selectors.loginName, true), function(data) {
				if (data) {
					showWarn(selectors.loginName, ajaxWarn.loginNameDisenable);
				} else {
					showRightWarn(selectors.loginName, ajaxWarn.loginNameEnable1 + FeiLongUtil.getValue(selectors.loginName, true) + ajaxWarn.loginNameEnable2);
				}
			});
		}
	});
	// 密码1
	$(selectors.pass1).blur(function() {
		validatePass1();
	}).keyup(function() {
		validatePassIntensity(selectors.pass1);
	});
	// 密码2
	$(selectors.pass2).blur(function() {
		validatePass2();
	});
	// 邮箱
	$(selectors.email).blur(function() {
		if (validateEmail()) {
			var emailValue = FeiLongUtil.getValue(selectors.email, true);
			if ("venusdrogon@163.com" != emailValue) {
				// ajax验证邮箱
				UserAjax.isRepeatEmail(emailValue, function(data) {
					if (data) {
						showWarn(selectors.email, ajaxWarn.emailDisenable);
					} else {
						showRightWarn(selectors.email, ajaxWarn.emailEnable);
					}
				});
			} else {
				showRightWarn(selectors.email, ajaxWarn.emailEnable);
			}
		}
	});
	// 验证码
	$(selectors.serial).blur(function() {
		if (validateSerial()) {
			var v = FeiLongUtil.getValue(selectors.serial, true);
			$.get("user.do?opt=ajaxCode&code=" + v, function(data) {
				if ("true" == data)
					showRightWarn(selectors.serial, ajaxWarn.serialRight);
				else {
					// createWarn("验证码错误");
					// $("#validateImage").attr("src",$("#validateImage").attr("src"));
					// $("#validateCode").select();
					showWarn($(selectors.serial), ajaxWarn.serialError);
				}
			});
		}
	});
	// $(selectors.realName).blur( function() {
	// validateRealName();
	// });
	// $(selectors.phone).blur( function() {
	// validatePhone();
	// });
	// $(selectors.address).blur( function() {
	// validateAddress();
	// });
});
// 注册验证所有 void 时间:2008年11月5日 12:47:19
function validateRegister() {
	var flag = validateCommon();
	if (!flag) {
		return false;
	}
	// if (flag) {
	// // ajax验证用户名
	// UserAjax.isRepeatUserName(FeiLongUtil.getValue(selectors.loginName, true), function(data) {
	// if (data) {
	// alert("该用户名已经被使用");
	// showWarn(selectors.loginName, ajaxWarn.loginNameDisenable);
	// $(selectors.loginName).focus();
	// return false;
	// } else {
	// showRightWarn(selectors.loginName, ajaxWarn.loginNameEnable1 + FeiLongUtil.getValue(selectors.loginName, true) + ajaxWarn.loginNameEnable2);
	// // ajax验证邮箱
	// UserAjax.isRepeatEmail(FeiLongUtil.getValue(selectors.email, true), function(data) {
	// if (data) {
	// alert("该邮箱已经被使用");
	// showWarn(selectors.email, ajaxWarn.emailDisenable);
	// $(selectors.email).focus();
	// return false;
	// } else {
	// showRightWarn(selectors.email, ajaxWarn.emailEnable);
	// var v = FeiLongUtil.getValue(selectors.serial, true);
	// $.get("user.do", {
	// opt :"ajaxCode",
	// code :v
	// }, function(data) {
	// if ("true" == data) {
	// showRightWarn(selectors.serial, ajaxWarn.serialRight);
	// return true;
	// // $.post("register.do?" + $("form").serialize());
	// } else {
	// alert(ajaxWarn.serialError);
	// showWarn($(selectors.serial), ajaxWarn.serialError);
	// $(selectors.serial).focus();
	// return false;
	// }
	// });
	// }
	// });
	// }
	// });
	// }
}
/**
 * 验证普通验证
 * 
 * @return
 */
function validateCommon() {
	firstQuery = null;
	validateLoginName();
	validatePass1();
	validatePass2();
	validateEmail();
	validateSerial();
	validateItem();
	var bool = (firstQuery == null);
	if (!bool) {
		alert(firstQuery.parent(0).find(tagName).html().replace(/<br>/gi, "\n"))
		firstQuery.focus();
	}
	return bool;
}
// 验证 验证码 void 时间:2008年11月7日 18:39:12
function validateSerial() {
	thisSelector = selectors.serial;
	return noNullValidate(thisSelector, nullWarn.serial);
}
// 验证用户名 void 时间:2008年11月5日 12:47:34
function validateLoginName() {
	thisSelector = selectors.loginName;
	// 是否通过非空验证
	if (noNullValidate(thisSelector, nullWarn.loginName)) {
		// 是否通过长度验证
		if (lengthValidate(thisSelector, charLength.minLoginName, charLength.maxLoginName, formatWarn.minLoginName, formatWarn.maxLoginName)) {
			if (formatValidateNot(thisSelector, /^zg\d*$/, "该用户已经被注册")) {
				// 是否通过格式验证
				if (formatValidate(thisSelector, patterns.loginName, formatWarn.formatLoginName)) {
					return true;
				}
			}
		}
	}
	return false;
}
// 验证邮箱 void 时间:2008年11月5日 12:48:13
function validateEmail() {
	thisSelector = selectors.email;
	if (noNullValidate(thisSelector, nullWarn.email)) {
		if (lengthValidate(thisSelector, charLength.minEmail, charLength.maxEmail, formatWarn.minEmail, formatWarn.maxEmail)) {
			if (formatValidate(thisSelector, patterns.email, formatWarn.formatEmail)) {
				return true;
			}
		}
	}
	return false;
}
// 验证密码 void 时间:2008年11月5日 12:47:48
function validatePass1() {
	thisSelector = selectors.pass1;
	if (noNullValidate(thisSelector, nullWarn.pass1)) {
		if (lengthValidate(thisSelector, charLength.minPass, charLength.maxPass, formatWarn.minPass1, formatWarn.maxPass1)) {
			if (formatValidate(thisSelector, patterns.pass, formatWarn.formatPass)) {
				validatePassIntensity(thisSelector);
			}
			// formatValidate(thisSelector, patterns.pass, formatWarn.formatPass);
		}
	}
}
// 密码强度提示 void 时间:2008年11月12日 19:52:27
function validatePassIntensity(selector) {
	var textValue = $(selector).val();
	var txtLength = textValue.length;
	var txtMaxLength = charLength.maxPass;
	// 密码长度 6-16的时候给出密码强度提示
	if (txtLength >= charLength.minPass && txtLength <= txtMaxLength) {
		if (noNullValidate(selector, nullWarn.pass1)) {
			if (textValue.match(/\S/g).length < 6) {
				showWarn(selector, formatWarn.notNullPass);
			} else {
				passIntensityValidate(selector);
			}
		}
	} else {
		if (txtLength > txtMaxLength) {
			showWarn(selector, formatWarn.maxPass1);
		} else {
			clearWarn(selector);
		}
	}
}
// 大小写组合
// 密码强度提示 void 时间:2008年11月13日 10:06:57
function passIntensityValidate(selector) {
	var i = -1;
	var textValue = $(selector).val();
	if (textValue.search(/\d/) != -1)
		i++;
	if (textValue.search(/[_\W]/) != -1)
		i++;
	if (textValue.search(/[a-zA-Z]/) != -1)
		i++;
	var warn_Pass = passIntensityWarn.prefixString;
	warn_Pass += "<label style='color:" + color.passIntensityColor[i] + "'>";
	warn_Pass += passIntensityString[i];
	warn_Pass += "</label>";
	warn_Pass += "<label style='font-size:8px;width:" + charLength.passIntensity[i] + "px;background:" + color.passIntensityColor[i]
	+ ";display:block;'>&nbsp;</label>";
	showRightWarn(selector, warn_Pass);
}
// 确认密码 void 时间:2008年11月5日 12:47:57
function validatePass2() {
	thisSelector = selectors.pass2;
	if (confirmPassValidate(thisSelector, formatWarn.equalsPass))
		clearWarn(thisSelector);
}
// 验证真实姓名 void 时间:2008年11月5日 12:48:06
function validateRealName() {
	thisSelector = selectors.realName;
	if (noNullValidate(thisSelector, null)) {
		if (lengthValidate(thisSelector, charLength.minRealName, charLength.maxRealName, formatWarn.minRealName, formatWarn.maxRealName)) {
			if (formatValidate(thisSelector, patterns.realName, formatWarn.formatRealName)) {
				clearWarn(thisSelector);
			}
		}
	} else {
		clearWarn(thisSelector);
	}
}
// 验证电话 void 时间:2008年11月5日 12:48:20
function validatePhone() {
	thisSelector = selectors.phone;
	if (noNullValidate(thisSelector, null)) {
		if (formatValidate(thisSelector, patterns.phone, formatWarn.formatPhone)) {
			clearWarn(thisSelector);
		}
	} else {
		clearWarn(thisSelector);
	}
}
// 验证地址 void 时间:2008年11月5日 12:48:27
function validateAddress() {
	thisSelector = selectors.address;
	if (noNullValidate(thisSelector, null)) {
		if (lengthValidate(thisSelector, charLength.minAddress, charLength.maxAddress, formatWarn.minAddress, formatWarn.maxAddress)) {
			// 非法字符验证
			if (nonlicetCharValidate(thisSelector, patterns.address, formatWarn.formatAddress)) {
				clearWarn(thisSelector);
			}
		}
	} else {
		clearWarn(thisSelector);
	}
}
// 验证是否同意条款
function validateItem() {
	thisSelector = selectors.cboItem;
	if (checkedValidate(thisSelector, nullWarn.cboItem))
		clearWarn(thisSelector);
}
// 非空验证 bool 时间:2008年11月5日 12:42:41
function noNullValidate(selector, thiswarn) {
	var bool = FeiLongUtil.isNotNull(FeiLongUtil.getValue(selector, true));
	if (!bool)
		if (thiswarn != null)
			showWarn(selector, thiswarn);
	return bool;
}
// 验证长度 bool 时间:2008年11月5日 12:50:41
function lengthValidate(selector, minLength, maxLength, minWarn, maxWarn) {
	var txtLength = FeiLongUtil.getValue(selector).length;
	var bool = (txtLength >= minLength && txtLength <= maxLength);
	if (!bool)
		showWarn(selector, txtLength < minLength ? minWarn : maxWarn);
	return bool;
}
// 格式验证 bool 时间:2008年11月5日 12:51:11
function formatValidate(selector, pattern, thisWarn) {
	var bool = pattern.test(FeiLongUtil.getValue(selector));
	if (!bool)
		showWarn(selector, thisWarn);
	return bool;
}
// 反相格式验证 bool 时间:2009年9月20日 22:01:06
function formatValidateNot(selector, pattern, thisWarn) {
	var bool = pattern.test(FeiLongUtil.getValue(selector));
	if (bool)
		showWarn(selector, thisWarn);
	return !bool;
}
// 确认密码验证 bool 时间:2008年11月5日 12:51:23
function confirmPassValidate(selector, thisWarn) {
	var bool = (FeiLongUtil.getValue(selector, false) == FeiLongUtil.getValue(selectors.pass1, false));
	if (!bool)
		showWarn(selector, thisWarn);
	return bool;
}
// 非法字符验证 bool 时间:2008年11月7日 18:35:21
function nonlicetCharValidate(selector, pattern, thisWarn) {
	var bool = (FeiLongUtil.getValue(selector, true).match(pattern) == null);
	if (!bool)
		showWarn(selector, thisWarn);
	return bool;
}
// 选中验证
function checkedValidate(selector, thisWarn) {
	var bool = $(selector).attr("checked");
	if (!bool)
		showWarn(selector, thisWarn);
	return bool;
}
// 显示提示 void 时间:2008年10月23日
function showWarn(selector, thiswarn) {
	if (FeiLongUtil.isNull(firstQuery))
		firstQuery = $(selector);
	$(selector).css("background-color", color.txtWarnBack).parent(0).find(tagName).html(thiswarn.replace("%", FeiLongUtil.getValue(selector, true))).css({
		color		: color.warnColor,
		display	: "block"
	});
}
// 清除提示 void 时间:2008年10月23日
function clearWarn(selector) {
	$(selector).css({
		"background-color"	: color.original.backgroundColor
	}).parent(0).find(tagName).html("").css({
		color		: color.warnColor,
		display	: "block"
	});
}
// 显示正确的提示 void 时间:2008年10月23日
function showRightWarn(selector, thiswarn) {
	$(selector).css("background-color", color.original.backgroundColor).parent(0).find(tagName).html(thiswarn).css({
		color		: color.rightColor,
		display	: "block"
	});
}