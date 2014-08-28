$(function() {
	// 出生日期
	$("#txt_birthday").focus(function() {
		WdatePicker({
			isShowClear					: false,
			isShowToday					: false,
			readOnly						: true,
			qsEnabled						: false,
			isShowOthers				: false,
			startDate						: "#{%y-25}-01-01",
			minDate							: '#{%y-35}-01-01',
			maxDate							: '#{%y-10}-12-31',
			autoPickDate				: true,
			alwaysUseStartDate	: false,
			oncleared						: function() {
				$(this).blur();
			},
			onpicked						: function() {
				$(this).blur();
			}
		})
	})
	// 出生日期
	$(".date_birthday_managerWorker").focus(function() {
		WdatePicker({
			isShowClear					: false,
			isShowToday					: false,
			readOnly						: false,
			qsEnabled						: false,
			isShowOthers				: false,
			skin								: "whyGreen",
			startDate						: "#{%y-35}-01-01",
			minDate							: '#{%y-70}-01-01',
			maxDate							: '#{%y-10}-12-31',
			autoPickDate				: true,
			alwaysUseStartDate	: false,
			oncleared						: function() {
				$(this).blur();
			},
			onpicked						: function() {
				$(this).blur();
			}
		})
	})
	// 年月日
	$(".txt_yyyy-MM-dd").focus(function() {
		WdatePicker({
			isShowClear					: false,
			isShowToday					: false,
			readOnly						: true,
			qsEnabled						: false,
			isShowOthers				: false,
			skin								: "whyGreen",
			dateFmt							: "yyyy-MM-dd",
			startDate						: "#{%y-10}-01-01",
			minDate							: '#{%y-50}-01-01',
			maxDate							: "%y-%M-%d",// 今天
			autoPickDate				: true,
			alwaysUseStartDate	: false,
			oncleared						: function() {
				$(this).blur();
			},
			onpicked						: function() {
				$(this).blur();
			}
		})
	});
	// 开始日期
	$("#beginDate").focus(function() {
		WdatePicker({
			isShowClear					: false,
			isShowToday					: false,
			readOnly						: true,
			qsEnabled						: false,
			isShowOthers				: false,
			skin								: "whyGreen",
			startDate						: "#{%y-2}-01-01",
			minDate							: '#{%y-2}-01-01',
			maxDate							: "#{%y+1}-01-01",
			autoPickDate				: true,
			alwaysUseStartDate	: false
		})
	});
	// 截至日期
	$("#stopDate").focus(function() {
		WdatePicker({
			isShowClear					: false,
			isShowToday					: false,
			readOnly						: true,
			qsEnabled						: false,
			isShowOthers				: false,
			skin								: "whyGreen",
			minDate							: "#F{$dp.$D(\'beginDate\',{d:3});}",
			maxDate							: "#{%y+2}-12-31",
			autoPickDate				: true,
			alwaysUseStartDate	: false
		})
	});
});
/**
 * 飞龙DatePicker
 * 
 * @param {String}
 *          selector 选择器
 * @param {feilongDatePickerEntity}
 *          feilongDatePickerEntity 飞龙feilongDatePickerEntity
 */
function feilongDatePicker(selector/* 选择器 */, feilongDatePickerEntity/* 飞龙feilongDatePickerEntity */) {
	// 最小日期
	var f_minDate = feilongDatePickerEntity.minDate;
	if (FeiLongUtil.isNull(f_minDate)) {
		f_minDate = feilongDatePickerEntity.startDate;
	}
	// 最大日期
	var f_maxDate = feilongDatePickerEntity.maxDate;
	if (FeiLongUtil.isNull(f_maxDate)) {
		// f_maxDate = "%y-%M-%d %H:%m";
	}
	$(selector).focus(function() {
		WdatePicker({
			// 清空按钮
			isShowClear					: false,
			// 今天按钮
			isShowToday					: false,
			// 只读
			readOnly						: true,
			// 快速选择
			qsEnabled						: false,
			// 是否显示其他月的日期
			isShowOthers				: false,
			skin								: "whyGreen",
			dateFmt							: feilongDatePickerEntity.pattern,/* 格式类型 */
			// 起始时间
			startDate						: feilongDatePickerEntity.startDate,
			minDate							: f_minDate,
			maxDate							: f_maxDate,
			// 为false时 点日期的时候不自动输入,而是要通过确定才能输入
			// 为true时 即点击日期即可返回日期值
			// 为null时(推荐使用) 如果有时间置为false 否则置为true
			autoPickDate				: true,
			// 当日期框无论是何值,始终使用 startDate 做为起始日期
			alwaysUseStartDate	: false,
			oncleared						: function() {
				$(this).blur();
			},
			onpicked						: function() {
				$(this).blur();
			}
		})
	});
}
/**
 * 验证数字
 * 
 * @param {string}
 *          selector 选择器
 * @param {string}
 *          min 最小数字
 * @param {string}
 *          max 最大数字
 * @param {string}
 *          chineseDescription 中文描述
 */
function validateNumber(selector, min, max, chineseDescription) {
	// chineseDescription
	$(selector).formValidator({}).inputValidator({
		type		: "number",
		min			: min,
		max			: max,
		onerror	: chineseDescription + "格式不正确"
	});
}
/**
 * 验证时间
 * 
 * @param {string}
 *          selector 选择器
 * @param {string}
 *          min 最小时间
 * @param {string}
 *          max 最大时间
 * @param {string}
 *          chineseDescription 中文描述
 */
function validateTime(selector, min, max, chineseDescription) {
	$(selector).formValidator({}).inputValidator({
		type		: "date",
		min			: min,
		max			: max,
		onerror	: chineseDescription + "<br/>必须在" + min + "和" + max + "之间"
	});
}
/**
 * 验证身高
 * 
 * @param {string}
 *          selector 选择器
 */
function validateHeight(selector) {
	// 身高
	$(selector).formValidator({
		empty	: true
	}).inputValidator({
		min			: 100,
		max			: 250,
		type		: "value",
		onerror	: "身高必须介于100-250(cm)之间"
	}).regexValidator({
		regexp	: "^[1-9]\\d*|0$",
		onerror	: "身高格式不正确"
	});
}
/**
 * 验证体重
 * 
 * @param {string}
 *          selector 选择器
 */
function validateWeight(selector) {
	// 体重
	$(selector).formValidator({
		empty	: true
	}).inputValidator({
		min			: 30,
		max			: 300,
		type		: "value",
		onerror	: "体重必须介于30-300(kg)之间"
	}).regexValidator({
		regexp	: "^[1-9]\\d*|0$",
		onerror	: "体重格式不正确"
	});
}
/**
 * MSN
 */
function validateMSN() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// MSN
	$("#txt_msn").formValidator({
		empty	: flag
	}).regexValidator({
		regexp		: "email",
		datatype	: "enum",
		onerror		: "MSN格式不正确"
	});
}
/**
 * QQ
 */
function validateQQ() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// QQ
	$("#txt_qq").formValidator({
		empty	: flag
	}).regexValidator({
		regexp		: "qq",
		datatype	: "enum",
		onerror		: "QQ号码格式不正确"
	});
}
/**
 * 联系人
 */
function validateLinkMan() {
	// 联系人
	$("#txt_linkMan").formValidator({}).regexValidator({
		regexp	: "^\\S+$",
		onerror	: "联系人不能为空"
	});
}
/**
 * 联系电话
 */
function validatePhone() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// 联系电话
	$("#txt_phone").formValidator({
		empty		: flag,
		onfocus	: "例如:021-33199360,必须加区号"
	}).regexValidator({
		regexp	: "^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$",
		onerror	: "联系电话格式不正确"
	});
}
/**
 * 验证电话
 */
function validateTelephone(selector) {
	$(selector).formValidator({
		onfocus	: "区号加电话号码,例:021-33199360",
		empty		: true
	}).regexValidator({
		regexp		: "tel",
		datatype	: "enum",
		onerror		: "联系电话格式不正确"
	});
}
/**
 * 手机
 */
function validateMobilePhone() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	$("#txt_mobilePhone").formValidator({
		empty		: flag,
		onfocus	: "11位手机号码"
	}).inputValidator({
		min			: 11,
		max			: 11,
		onerror	: "手机号码长度错误"
	}).regexValidator({
		regexp		: "mobile",
		datatype	: "enum",
		onerror		: "手机号码格式不正确"
	});
}
/**
 * 传真
 */
function validateFax() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// 传真
	$("#txt_fax").formValidator({
		empty	: flag
	}).regexValidator({
		regexp	: "^(0[0-9]{2,3}\-)+([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$",
		onerror	: "传真号码格式不正确"
	});
}
/**
 * 邮箱
 */
function validateEmail() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// 邮箱
	$("#txt_email").formValidator({
		empty	: flag
	}).regexValidator({
		regexp		: "email",
		datatype	: "enum",
		onerror		: "邮箱格式不正确"
	});
}
/**
 * 邮箱不重复
 */
function validateEmailNoRepeat() {
	// 邮箱
	$("#txt_email").formValidator({}).regexValidator({
		regexp		: "email",
		datatype	: "enum",
		onerror		: "邮箱格式不正确"
	}).functionValidator({
		fun	: function(val, elem) {
			var returnValue;
			// 设置成同步
			DWREngine.setAsync(false);
			UserAjax.isRepeatEmail(val, function(data) {
				returnValue = data;
			});
			// 重新设置为异步方式
			DWREngine.setAsync(true);
			if (!returnValue) {
				return true;
			} else {
				return "该邮箱已经被人注册,如果您是邮箱的所有者,请联系中国招工021-33199360"
			}
		}
	});
}
/**
 * 邮编
 */
function validateZip() {
	/* 第一个参数为 是否可以为空 */
	var flag = true;
	var l = arguments.length;
	if (l != 0) {
		flag = arguments[0];
	}
	// 邮编
	$("#txt_zip").formValidator({
		empty	: flag
	}).regexValidator({
		regexp	: "^\\d{6}$",
		onerror	: "邮编格式不正确"
	});
}
/**
 * 地址
 */
function validateAddress() {
	// 地址
	$("#txt_address").formValidator({}).regexValidator({
		regexp	: "^\\S+$",
		onerror	: "地址不能为空"
	});
}
/**
 * 验证身份证
 * 
 * @param {string}
 *          selector 选择器
 */
function validateIdCard(selector) {
	$(selector).formValidator({
		onfocus	: "请输入15或18位的身份证",
		empty		: true
	}).functionValidator({
		fun	: isCardID
	});
}
/**
 * 验证真实姓名
 * 
 * @param {string}
 *          selector 选择器
 */
function validateUserRealName(selector) {
	// 真实姓名
	$(selector).formValidator({
		onfocus	: "姓名2-5个中文",// 获得焦点
		onempty	: "姓名不能为空"
	}).inputValidator({
		min			: 2,
		max			: 10,
		onerror	: "姓名格式不正确,长度必须2-5个中文"// 错误
	}).regexValidator({
		regexp		: "chinese",
		datatype	: "enum",
		onerror		: "姓名格式不正确,只能是2-5个中文"
	});
}
/**
 * 验证用户名
 */
function validateUserName(type) {
	// 用户名
	$("#txt_userName").formValidator({}).inputValidator({
		min			: 4,
		max			: 20,
		onerror	: "用户名长度4-20字符"
	}).regexValidator({
		regexp		: "username",
		datatype	: "enum",
		onerror		: "用户名格式不正确"
	}).functionValidator({
		fun	: function(val, elem) {
			if (FeiLongUtil.isNotNull(val)) {
				var returnValue;
				var methodName = "";
				// 设置成同步
				DWREngine.setAsync(false);
				switch (type) {
					case "laowu" :
						methodName = "isRepeatUserName";
						break;
					case "manager" :
						methodName = "ajaxValidateIsRepeatManagerName";
						break;
					case "person_zhaopin" :
						methodName = "isRepeatUserName_ajax";
						break;
					default :
						break;
				}
				dwr.engine._execute(UserAjax._path, "UserAjax", methodName, val, function(data) {
					returnValue = data;
				});
				// 重新设置为异步方式
				DWREngine.setAsync(true);
				if (!returnValue) {
					$("#div_repeatNameWarn").html("<div class='yes'>该用户名可以使用</div>");
					return true;
				} else {
					$("#div_repeatNameWarn").html("<div class='no'>该用户名已经被注册</div>");
					return "该用户名已经被注册"
				}
			}
		}
	});
}
/**
 * 验证用户密码
 */
function validateUserPassword() {
	$("#txt_userPassword").formValidator({}).inputValidator({
		min			: 6,
		max			: 20,
		onerror	: "密码长度6-20字符"
	}).regexValidator({
		regexp		: "pass",
		datatype	: "enum",
		onerror		: "密码格式不正确"
	});
}
/**
 * 验证重复用户密码
 */
function validateUserPassword2() {
	$("#txt_userPassword2").formValidator({}).compareValidator({
		desid			: "txt_userPassword",// 要比较控件的ID
		operateor	: "=",// 比较符号 一共有如下几种类型:=、!=、>、>=、<、<=
		// datatype 目前只支持2种:"string"、"number","datetime","date"
		onerror		: "2次密码输入不一致,请重新输入"
	});
}
/**
 * 修改密码
 */
function validateChangePass(type) {
	// 原始密码
	$("#pass0").formValidator({}).inputValidator({
		empty				: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "密码两边不能有空符号"
		},
		min					: 6,
		max					: 16,
		onerrormin	: "当前密码长度错误,只能是6-16个字符",
		onerrormax	: "当前密码长度错误,只能是6-16个字符"
	}).functionValidator({
		fun	: function(val, elem) {
			var returnValue;
			// 设置成同步
			DWREngine.setAsync(false);
			if (type == "user") {
				UserAjax.validateOldPass(userId, val, function(data) {
					returnValue = data;
				});
			} else if (type == "manager") {
				UserAjax.ajaxValidateManagerOldPass(managerId, val, function(data) {
					returnValue = data;
				});
			} else if (type == "userPerson") {
				UserAjax.ajaxValidateUserPersonOldPass(userPersonId, val, function(data) {
					returnValue = data;
				});
			}
			// 重新设置为异步方式
			DWREngine.setAsync(true);
			if (returnValue) {
				return true;
			} else {
				return "当前密码和您的密码不匹配,请重新输入"
			}
		}
	});
	// 新密码
	$("#pass1").formValidator({}).inputValidator({
		empty				: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "新密码两边不能有空符号"
		},
		min					: 6,
		max					: 16,
		onerrormin	: "新密码长度错误,必须是6-16个字符",
		onerrormax	: "新密码长度错误,必须是6-16个字符"
	}).regexValidator({
		regexp		: "pass",
		datatype	: "enum",
		// param 附加参数
		// datatype 数据类型
		onerror		: "新密码格式不正确,6-16位字符,特殊字符只能包含!@#$%"
	});
	// 确认新密码
	$("#pass11").formValidator({}).compareValidator({
		desid			: "pass1",// 要比较控件的ID
		operateor	: "=",// 比较符号 一共有如下几种类型:=、!=、>、>=、<、<=
		// datatype 目前只支持2种:"string"、"number","datetime","date"
		onerror		: "2次密码输入不一致,请核实后重新输入"
	});
}
/**
 * 飞龙验证 金鑫 2009年9月24日 14:42:17
 * 
 * @deprecated 建议使用 formvalidator
 */
var FeiLongValidate = {
	/**
	 * 数字区间验证
	 */
	between					: function(selector, min, max) {
		return (FeiLongUtil.getValue(selector) >= min && FeiLongUtil.getValue(selector) <= max);
	},
	/**
	 * 验证长度
	 */
	length					: function(selector, minLength, maxLength) {
		var txtLength = FeiLongUtil.getValue(selector).length;
		return (txtLength >= minLength && txtLength <= maxLength);
	},
	/**
	 * 格式验证
	 */
	format					: function(selector, pattern) {
		return pattern.test(FeiLongUtil.getValue(selector));
	},
	/**
	 * 下拉框有没有选择验证
	 */
	selectNull			: function(selector) {
		return $(selector).val() == 0;
	},
	/**
	 * 下拉框比较验证(后者比前者大)
	 */
	selectMoreThan	: function(beforSelector, afterSeletor) {
		return $(afterSeletor).val() >= $(beforSelector).val();
	},
	/**
	 * 下拉框比较验证(比较时间 结束时间和开始时间比较)
	 */
	selectTimeThan	: function(beginYearSelector, beginMonthSelector, endYearSelector, endMonthSelector) {
		var bMonth = $(beginMonthSelector).val();
		var eMonth = $(endMonthSelector).val();
		if (bMonth < 10) {
			bMonth = "0" + bMonth;
		}
		if (eMonth < 10) {
			eMonth = "0" + eMonth;
		}
		var begin = $(beginYearSelector).val() + "" + bMonth;
		var end = $(endYearSelector).val() + "" + eMonth;
		return end >= begin;
	}
};
/**
 * 飞龙表单验证
 * 
 * @type
 */
var FeiLongFormValidator = {
	/**
	 * 验证
	 * 
	 * @param {object}
	 *          entity_FeiLongFormValidator FeiLongFormValidator entity
	 */
	formValidator						: function(entity_FeiLongFormValidator) {
		$.formValidator.initConfig({
			formid		: entity_FeiLongFormValidator.formId,// 要自动注册pageIsValid函数的表单ID号
			debug			: false,// 是否处于调试模式。true:不提交表单
			onerror		: entity_FeiLongFormValidator.onerror,
			onsuccess	: entity_FeiLongFormValidator.onsuccess
		});
		entity_FeiLongFormValidator.currentValidatorFunction();
	},
	/**
	 * 验证用户名
	 */
	validateUserName				: function(selector, entity_FeiLongValidate) {
		$(selector).formValidator({}).inputValidator({
			min			: 4,
			max			: 20,
			onerror	: "用户名长度4-20字符"
		}).regexValidator({
			regexp		: "username",
			datatype	: "enum",
			onerror		: "用户名格式不正确"
		}).functionValidator({
			fun	: function(val, elem) {
				if (FeiLongUtil.isNotNull(val)) {
					var returnValue;
					// 设置成同步
					DWREngine.setAsync(false);
					dwr.engine._execute(UserAjax._path, entity_FeiLongValidate.userName.ajaxScriptName, entity_FeiLongValidate.userName.ajaxMethodName, val, function(
					data) {
						returnValue = data;
					});
					// 重新设置为异步方式
					DWREngine.setAsync(true);
					if (!returnValue) {
						return true;
					} else {
						return "该用户名已经被注册"
					}
				}
			}
		});
	},
	/**
	 * 验证验证码
	 * 
	 * @param {string}
	 *          selector 选择器
	 */
	validateValidateCode		: function(selector) {
		$(selector).formValidator({
			onempty	: "请填写验证码"
		}).functionValidator({
			fun	: function(val, elem) {
				var returnValue;
				// 设置成同步
				DWREngine.setAsync(false);
				FeiLongHTTPAjax.isPassValidateCode_ajax(val, function(data) {
					returnValue = data;
				});
				// 重新设置为异步方式
				DWREngine.setAsync(true);
				if (returnValue) {
					return true;
				} else {
					return "验证码不正确";
				}
			}
		});
	},
	/**
	 * 注册的时候验证邮箱
	 */
	validateRigister_email	: function(selector, entity_FeiLongValidate) {
		// 邮箱
		$(selector).formValidator({}).regexValidator({
			regexp		: "email",
			datatype	: "enum",
			onerror		: "邮箱格式不正确"
		}).functionValidator({
			fun	: function(val, elem) {
				var returnValue;
				// 设置成同步
				DWREngine.setAsync(false);
				dwr.engine._execute(UserAjax._path, "UserAjax", "isRepeatEmail_ajax", val, function(data) {
					returnValue = data;
				});
				// 重新设置为异步方式
				DWREngine.setAsync(true);
				if (!returnValue) {
					return true;
				} else {
					return entity_FeiLongValidate.email.warn_repeatEmail;
				}
			}
		});
	},
	/**
	 * 验证手机
	 * 
	 * @param {string}
	 *          selector
	 * @param {object}
	 *          entity_FeiLongValidate
	 */
	validateMobilePhone			: function(selector, entity_FeiLongValidate) {
		var isAllowEmpty = entity_FeiLongValidate.mobilePhone.isAllowEmpty;
		$(selector).formValidator({
			empty		: isAllowEmpty,
			onfocus	: "11位手机号码"
		}).inputValidator({}).regexValidator({
			regexp		: "mobile",
			datatype	: "enum",
			onerror		: "手机号码格式不正确"
		});
	}
};