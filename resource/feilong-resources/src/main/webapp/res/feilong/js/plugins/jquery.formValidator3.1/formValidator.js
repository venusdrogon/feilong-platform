// ====================================================================================================
// [插件名称] jQuery formValidator
// ----------------------------------------------------------------------------------------------------
// [描 述] 基于jQuery类库，实现了js脚本于页面的分离。
// 对一个表单对象，你只需要写一行代码就可以轻松实现20种以上的脚本控制。
// 现支持一个表单元素累加很多种校验方式,采用配置信息的思想，而不是把信息写在表单元素上，能比较完美的实现ajax请求。
// ----------------------------------------------------------------------------------------------------
// [作者网名] 猫冬
// [日 期] 2008-01-11
// [作者博客] http://wzmaodong.cnblogs.com
// ====================================================================================================
var jQuery_formValidator_initConfig;
(function($) {
	$.formValidator = {
		// 各种校验方式支持的控件类型
		sustainType			: function(id, setting) {
			var elem = $("#" + id).get(0);
			var srcTag = elem.tagName;
			var stype = elem.type;
			switch (setting.validatetype) {
				case "InitValidator" :
					return true;
				case "InputValidator" :
					if (srcTag == "INPUT" || srcTag == "TEXTAREA" || srcTag == "SELECT") {
						return true;
					} else {
						return false;
					}
				case "CompareValidator" :
					if (srcTag == "INPUT" || srcTag == "TEXTAREA") {
						if (stype == "checkbox" || stype == "radio") {
							return false;
						} else {
							return true;
						}
					}
					return false;
				case "AjaxValidator" :
					if (stype == "text" || stype == "textarea" || stype == "file" || stype == "select-one") {
						return true;
					} else {
						return false;
					}
				case "RegexValidator" :
					if (srcTag == "INPUT" || srcTag == "TEXTAREA") {
						if (stype == "checkbox" || stype == "radio") {
							return false;
						} else {
							return true;
						}
					}
					return false;
				case "FunctionValidator" :
					return true;
			}
		},
		initConfig			: function(controlOptions) {
			var settings = {
				debug						: false,
				validatorgroup	: "1",
				alertmessage		: false,
				validobjectids	: "",
				onsuccess				: function() {
					return true;
				},
				onerror					: function() {},
				submitonce			: false,
				formid					: "",
				autotip					: false
			};
			controlOptions = controlOptions || {};
			$.extend(settings, controlOptions);
			if (settings.formid != "") {
				$("#" + settings.formid).submit(function() {
					return $.formValidator.pageIsValid("1");
				})
			};
			if (jQuery_formValidator_initConfig == null) {
				jQuery_formValidator_initConfig = new Array();
			}
			jQuery_formValidator_initConfig.push(settings);
		},
		// 如果validator对象对应的element对象的validator属性追加要进行的校验。
		appendValid			: function(id, setting) {
			// 如果是各种校验不支持的类型，就不追加到。返回-1表示没有追加成功
			if (!$.formValidator.sustainType(id, setting))
				return -1;
			var srcjo = $("#" + id).get(0);
			if (setting.validatetype == "InitValidator" || !srcjo.settings || srcjo.settings == undefined) {
				srcjo.settings = new Array();
			}
			var len = srcjo.settings.push(setting);
			srcjo.settings[len - 1].index = len - 1;
			return len - 1;
		},
		// 如果validator对象对应的element对象的validator属性追加要进行的校验。
		getInitConfig		: function(validatorgroup) {
			if (jQuery_formValidator_initConfig != null) {
				for (i = 0; i < jQuery_formValidator_initConfig.length; i++) {
					if (validatorgroup == jQuery_formValidator_initConfig[i].validatorgroup) {
						return jQuery_formValidator_initConfig[i];
					}
				}
			}
			return null;
		},
		// 触发每个控件上的各种校验
		triggerValidate	: function(returnObj) {
			switch (returnObj.setting.validatetype) {
				case "InputValidator" :
					$.formValidator.inputValid(returnObj);
					break;
				case "CompareValidator" :
					$.formValidator.compareValid(returnObj);
					break;
				case "AjaxValidator" :
					$.formValidator.ajaxValid(returnObj);
					break;
				case "RegexValidator" :
					$.formValidator.regexValid(returnObj);
					break;
				case "FunctionValidator" :
					$.formValidator.functionValid(returnObj);
					break;
			}
		},
		// 设置显示信息
		setTipState			: function(tipid, showclass, showmsg) {
			var tip = $("#" + tipid);
			if (showmsg == null || showmsg == "") {
				tip.hide();
			} else {
				tip.show();
				tip.removeClass();
				tip.addClass(showclass);
				tip.html("<nobr>" + showmsg + "</nobr>");
			}
		},
		// 设置错误的显示信息
		setFailState		: function(tipid, showmsg) {
			var tip = $("#" + tipid);
			tip.removeClass();
			tip.addClass("onError");
			tip.html(showmsg);
		},
		// 根据单个对象,正确:正确提示,错误:错误提示
		showMessage			: function(returnObj) {
			var id = returnObj.id;
			var isvalid = returnObj.isvalid;
			var setting = returnObj.setting;// 正确:setting[0],错误:对应的setting[i]
			var showmsg = "";
			var showclass = "";
			var settings = $("#" + id).get(0).settings;
			var intiConfig = $.formValidator.getInitConfig(settings[0].validatorgroup);
			if (!isvalid) {
				if (setting.validatetype == "AjaxValidator") {
					if (setting.lastValid == "") {
						showclass = "onLoad";
						showmsg = setting.onwait;
					} else {
						showclass = "onError";
						showmsg = setting.onerror;
					}
				} else {
					showmsg = (returnObj.errormsg == "" ? setting.onerror : returnObj.errormsg);
					showclass = "onError";
				}
				if (intiConfig.alertmessage) {
					var elem = $("#" + id).get(0);
					if (elem.validoldvalue != $(elem).val()) {
						alert(showmsg);
					}
				} else {
					$.formValidator.setTipState(settings[0].tipid, showclass, showmsg);
				}
			} else {
				// 验证成功后,如果没有设置成功提示信息,则给出默认提示,否则给出自定义提示;允许为空,值为空的提示
				if (!intiConfig.alertmessage) {
					var showmsg = "";
					if ($.formValidator.isEmpty(id)) {
						showmsg = setting.onempty;
					} else {
						showmsg = setting.oncorrect;
					}
					$.formValidator.setTipState(setting.tipid, "onSuccess", showmsg);
				}
			}
		},
		// 获取指定字符串的长度
		getLength				: function(id) {
			var srcjo = $("#" + id);
			sType = srcjo.get(0).type;
			var len = 0;
			switch (sType) {
				case "text" :
				case "hidden" :
				case "password" :
				case "textarea" :
				case "file" :
					var val = srcjo.val();
					for (var i = 0; i < val.length; i++) {
						if (val.charCodeAt(i) >= 0x4e00 && val.charCodeAt(i) <= 0x9fa5) {
							len += 2;
						} else {
							len++;
						}
					}
					break;
				case "checkbox" :
				case "radio" :
					len = $("input[@type='" + sType + "'][@name='" + srcjo.attr("name") + "'][@checked]").length;
					break;
				case "select-one" :
					len = srcjo.get(0).options ? srcjo.get(0).options.selectedIndex : -1;
					break;
				case "select-multiple" :
					len = $("select[@name=" + srcjo.get(0).name + "] option[@selected]").length;
					break;
			}
			return len;
		},
		// 结合empty这个属性，判断仅仅是否为空的校验情况。
		isEmpty					: function(id) {
			if ($("#" + id).get(0).settings[0].empty && $.formValidator.getLength(id) == 0) {
				return true;
			} else {
				return false;
			}
		},
		// 对外调用：判断单个表单元素是否验证通过，不带回调函数
		isOneValid			: function(id) {
			return $.formValidator.oneIsValid(id, 1).isvalid;
		},
		// 验证单个是否验证通过,正确返回settings[0],错误返回对应的settings[i]
		oneIsValid			: function(id, index) {
			var returnObj = new Object();
			returnObj.id = id;
			returnObj.ajax = -1;
			returnObj.errormsg = ""; // 自定义错误信息
			var elem = $("#" + id).get(0);
			var settings = elem.settings;
			var settingslen = settings.length;
			// 只有一个formValidator的时候不检验
			if (settingslen == 1) {
				settings[0].bind = false;
			}
			if (!settings[0].bind) {
				return null;
			}
			for (var i = 0; i < settingslen; i++) {
				if (i == 0) {
					if ($.formValidator.isEmpty(id)) {
						returnObj.isvalid = true;
						returnObj.setting = settings[0];
						break;
					}
					continue;
				}
				returnObj.setting = settings[i];
				if (settings[i].validatetype != "AjaxValidator") {
					$.formValidator.triggerValidate(returnObj);
				} else {
					returnObj.ajax = i;
				}
				if (!settings[i].isvalid) {
					returnObj.isvalid = false;
					returnObj.setting = settings[i];
					break;
				} else {
					returnObj.isvalid = true;
					returnObj.setting = settings[0];
					if (settings[i].validatetype == "AjaxValidator")
						break;
				}
			}
			return returnObj;
		},
		resetTipState		: function(validatorgroup) {
			var initConfig = $.formValidator.getInitConfig(validatorgroup);
			var jqObjs = $(initConfig.validobjectids);
			jqObjs.each(function() {
				var setting0 = this.settings[0];
				$.formValidator.setTipState(setting0.tipid, "onShow", setting0.onshow);
			});
		},
		// 验证所有需要验证的对象，并返回是否验证成功。
		pageIsValid			: function(validatorgroup) {
			if (validatorgroup == null || validatorgroup == undefined)
				validatorgroup = "1";
			var isvalid = true;
			var thefirstid = "", thefirsterrmsg;
			var returnObj, setting;
			var error_tip = "^";
			var initConfig = $.formValidator.getInitConfig(validatorgroup);
			var jqObjs = $(initConfig.validobjectids);
			jqObjs.each(function(i, elem) {
				if (elem.settings[0].bind) {
					returnObj = $.formValidator.oneIsValid(elem.id, 1);
					if (returnObj) {
						var tipid = elem.settings[0].tipid;
						// 校验失败,获取第一个发生错误的信息和ID
						if (!returnObj.isvalid) {
							isvalid = false;
							if (thefirstid == "") {
								thefirstid = returnObj.id;
								thefirsterrmsg = (returnObj.errormsg == "" ? returnObj.setting.onerror : returnObj.errormsg)
							}
						}
						// 为了解决使用同个TIP提示问题:后面的成功或失败都不覆盖前面的失败
						if (!initConfig.alertmessage) {
							if (error_tip.indexOf("^" + tipid + "^") == -1) {
								error_tip = error_tip + tipid + "^";
								if (!returnObj.isvalid) {
									error_tip = error_tip + tipid + "^";
								}
								$.formValidator.showMessage(returnObj);
							}
						}
					}
				}
			});
			// 成功或失败后，进行回调函数的处理，以及成功后的灰掉提交按钮的功能
			if (isvalid) {
				isvalid = initConfig.onsuccess();
				if (initConfig.submitonce) {
					$("input[@type='submit']").attr("disabled", true);
				}
			} else {
				var obj = $("#" + thefirstid).get(0);
				initConfig.onerror(thefirsterrmsg, obj);
				if (thefirstid != "") {
					$("#" + thefirstid).focus();
				}
			}
			return !initConfig.debug && isvalid;
		},
		// ajax校验
		ajaxValid				: function(returnObj) {
			var id = returnObj.id;
			var srcjo = $("#" + id);
			var setting = srcjo.get(0).settings[returnObj.ajax];
			var ls_url = setting.url;
			if (srcjo.size() == 0 && srcjo.get(0).settings[0].empty) {
				returnObj.setting = $("#" + id).get(0).settings[0];
				returnObj.isvalid = true;
				$.formValidator.showMessage(returnObj);
				setting.isvalid = true;
				return;
			}
			if (setting.addidvalue) {
				var parm = "clientid=" + id + "&" + id + "=" + encodeURIComponent(srcjo.val());
				ls_url = ls_url + ((ls_url).indexOf("?") > 0 ? ("&" + parm) : ("?" + parm));
			}
			$.ajax({
				mode				: "abort",
				type				: setting.type,
				url					: ls_url,
				cache				: setting.cache,
				data				: setting.data,
				async				: setting.async,
				dataType		: setting.datatype,
				success			: function(data) {
					setting0 = srcjo.get(0).settings[0];
					if (setting.success(data)) {
						$.formValidator.setTipState(setting0.tipid, "onSuccess", setting0.oncorrect);
						setting.isvalid = true;
					} else {
						$.formValidator.setTipState(setting0.tipid, "onError", setting.onerror);
						setting.isvalid = false;
					}
				},
				complete		: function() {
					if (setting.buttons && setting.buttons.length > 0)
						setting.buttons.attr({
							"disabled"	: false
						});
					setting.complete;
				},
				beforeSend	: function(xhr) {
					// 再服务器没有返回数据之前，先回调提交按钮
					if (setting.buttons && setting.buttons.length > 0)
						setting.buttons.attr({
							"disabled"	: true
						});
					var isvalid = setting.beforesend(xhr);
					if (isvalid)
						setting.isvalid = false; // 如果前面ajax请求成功了，再次请求之前先当作错误处理
					setting.lastValid = "-1";
					return isvalid;
				},
				error				: function() {
					setting0 = srcjo.get(0).settings[0];
					$.formValidator.setTipState(setting0.tipid, "onError", setting.onerror);
					setting.isvalid = false;
					setting.error();
				},
				processData	: setting.processdata
			});
		},
		// 对正则表达式进行校验（目前只针对input和textarea）
		regexValid			: function(returnObj) {
			var id = returnObj.id;
			var setting = returnObj.setting;
			var srcTag = $("#" + id).get(0).tagName;
			var elem = $("#" + id).get(0);
			// 如果有输入正则表达式，就进行表达式校验
			if (elem.settings[0].empty && elem.value == "") {
				setting.isvalid = true;
			} else {
				var regexpress = setting.regexp;
				if (setting.datatype == "enum") {
					regexpress = eval("regexEnum." + regexpress);
				}
				if (regexpress == undefined || regexpress == "") {
					setting.isvalid = false;
					return;
				}
				var exp = new RegExp(regexpress, setting.param);
				if (exp.test($("#" + id).val())) {
					setting.isvalid = true;
				} else {
					setting.isvalid = false;
				}
			}
		},
		// 函数校验。返回true/false表示校验是否成功;返回字符串表示错误信息，校验失败;如果没有返回值表示处理函数，校验成功
		functionValid		: function(returnObj) {
			var id = returnObj.id;
			var setting = returnObj.setting;
			var srcjo = $("#" + id);
			var lb_ret = setting.fun(srcjo.val(), srcjo.get(0));
			if (lb_ret != undefined) {
				if (typeof lb_ret == "string") {
					setting.isvalid = false;
					returnObj.errormsg = lb_ret;
				} else {
					setting.isvalid = lb_ret;
				}
			}
		},
		// 对input和select类型控件进行校验
		inputValid			: function(returnObj) {
			var id = returnObj.id;
			var setting = returnObj.setting;
			var srcjo = $("#" + id);
			var elem = srcjo.get(0);
			var val = srcjo.val();
			var sType = elem.type;
			var len = $.formValidator.getLength(id);
			var empty = setting.empty, emptyerror = false;
			switch (sType) {
				case "text" :
				case "hidden" :
				case "password" :
				case "textarea" :
				case "file" :
					if (setting.type == "size") {
						empty = setting.empty;
						if (!empty.leftempty) {
							emptyerror = (val.replace(/^[ \s]+/, '').length != val.length);
						}
						if (!emptyerror && !empty.rightempty) {
							emptyerror = (val.replace(/[ \s]+$/, '').length != val.length);
						}
						if (emptyerror && empty.emptyerror) {
							returnObj.errormsg = empty.emptyerror
						}
					}
				case "checkbox" :
				case "select-one" :
				case "select-multiple" :
				case "radio" :
					var li_panduan = false;
					if (sType == "select-one" || sType == "select-multiple") {
						setting.type = "size";
					}
					if (setting.type == "size") { // 获得输入的字符长度，并进行校验
						if (!emptyerror) {
							li_panduan = true
						}
						if (li_panduan) {
							val = len
						}
					} else {
						stype = (typeof setting.min);
						if (stype == "number") {
							val = (new Number(val)).valueOf();
							if (!isNaN(val)) {
								li_panduan = true;
							}
						}
						if (stype == "string") {
							li_panduan = true;
						}
					}
					if (li_panduan) {
						if (val < setting.min || val > setting.max) {
							if (val < setting.min && setting.onerrormin) {
								returnObj.errormsg = setting.onerrormin;
							}
							if (val > setting.min && setting.onerrormax) {
								returnObj.errormsg = setting.onerrormax;
							}
							setting.isvalid = false;
						} else {
							setting.isvalid = true;
						}
					} else {
						setting.isvalid = false;
					}
					break;
			}
		},
		compareValid		: function(returnObj) {
			var id = returnObj.id;
			var setting = returnObj.setting;
			var srcjo = $("#" + id);
			var desjo = $("#" + setting.desid);
			setting.isvalid = false;
			curvalue = srcjo.val();
			ls_data = desjo.val();
			if (setting.datatype == "number") {
				if (!isNaN(curvalue) && !isNaN(ls_data)) {
					curvalue = parseFloat(curvalue);
					ls_data = parseFloat(ls_data);
				} else {
					return;
				}
			}
			switch (setting.operateor) {
				case "=" :
					if (curvalue == ls_data) {
						setting.isvalid = true;
					}
					break;
				case "!=" :
					if (curvalue != ls_data) {
						setting.isvalid = true;
					}
					break;
				case ">" :
					if (curvalue > ls_data) {
						setting.isvalid = true;
					}
					break;
				case ">=" :
					if (curvalue >= ls_data) {
						setting.isvalid = true;
					}
					break;
				case "<" :
					if (curvalue < ls_data) {
						setting.isvalid = true;
					}
					break;
				case "<=" :
					if (curvalue <= ls_data) {
						setting.isvalid = true;
					}
					break;
				case "oneok" :
					if ($.formValidator.isEmpty(id) || $.formValidator.isEmpty(isEmpty.desid)) {
						setting.isvalid = false;
					} else {
						setting.isvalid = true;
					}
			}
		}
	};
	// 每个校验控件必须初始化的
	$.fn.formValidator = function(msgOptions) {
		var setting = {
			validatorgroup	: "1",
			empty						: false,
			submitonce			: false,
			automodify			: false,
			onshow					: " ",
			onfocus					: " ",
			oncorrect				: "&nbsp;",
			onempty					: " ",
			defaultvalue		: null,
			bind						: true,
			validatetype		: "InitValidator",
			tipcss					: {
				"left"		: "10px",
				"top"			: "1px",
				"height"	: "20px",
				"width"		: "250px"
			},
			triggerevent		: "blur"
		};
		// 先合并整个配置(深度拷贝)
		msgOptions = msgOptions || {};
		$.extend(true, setting, msgOptions);
		// 获取该校验组的全局配置信息
		var initConfig = $.formValidator.getInitConfig(setting.validatorgroup);
		return this.each(function() {
			var setting_temp = {};
			$.extend(setting_temp, setting);
			// 自动形成TIP
			var tip = "";
			if (initConfig.autotip) {
				if (!setting_temp.tipid) {
					setting_temp.tipid = this.id + "Tip"
				};
				tip = setting_temp.tipid;
				if (!setting_temp.relativeid) {
					setting_temp.relativeid = this.id
				};
				aftertip = setting_temp.relativeid;
				var y = getAbsoluteTop(aftertip) - 3;
				var x = getElementWidth(aftertip) + getAbsoluteLeft(aftertip);
				if ($("#" + tip).length == 0) {
					$("<div class='formValidateTip'></div>").appendTo($("body")).css({
						left	: x + "px",
						top		: y + "px"
					}).prepend($('<div id="' + tip + '"></div>').css(setting_temp.tipcss));
				}
				setting_temp.tipid = tip;
			}
			// 手动TIP
			else {
				if (!setting_temp.tipid) {
					setting_temp.tipid = this.id + "Tip"
				};
				tip = setting_temp.tipid;
			}
			// 每个控件都要保存这个配置信息
			$.formValidator.appendValid(this.id, setting_temp);
			// 保存控件ID
			var validobjectids = initConfig.validobjectids;
			if (validobjectids.indexOf("#" + this.id + " ") == -1) {
				initConfig.validobjectids = (validobjectids == "" ? "#" + this.id : validobjectids + ",#" + this.id);
			}
			// 初始化显示信息
			if (!initConfig.alertmessage) {
				$.formValidator.setTipState(tip, "onShow", setting.onshow);
			}
			// 注册事件
			var srcTag = this.tagName;
			var stype = this.type;
			var defaultvalue = setting.defaultvalue;
			var jqobj = $(this);
			if (srcTag == "INPUT" || srcTag == "TEXTAREA") {
				// 处理默认值
				if (defaultvalue) {
					if (stype == "checkbox" || stype == "radio") {
						if (stype == "radio") {
							if (this.value == defaultvalue) {
								jqobj.attr("checked", true)
							}
						} else {
							jqobj.attr("checked", $.inArray(this.value, defaultvalue) >= 0);
						}
					} else {
						jqobj.val(defaultvalue);
					}
				}
				// 注册获得焦点的事件。改变提示对象的文字和样式，保存原值
				jqobj.focus(function() {
					if (!initConfig.alertmessage) {
						$.formValidator.setTipState(tip, "onFocus", setting.onfocus);
					}
					if (stype == "password" || stype == "text" || stype == "textarea" || stype == "file") {
						this.validoldvalue = jqobj.val();
					}
				});
				// 注册失去焦点的事件。进行校验，改变提示对象的文字和样式；出错就提示处理
				jqobj.bind(setting.triggerevent, function() {
					var settings = this.settings;
					var returnObj = $.formValidator.oneIsValid(this.id, 1);
					if (returnObj == null) {
						return;
					}
					if (returnObj.ajax >= 0) {
						if (this.validoldvalue != $(this).val()) {
							$.formValidator.setTipState(tip, "onLoad", settings[returnObj.ajax].onwait);
							$.formValidator.ajaxValid(returnObj);
						}
					} else {
						$.formValidator.showMessage(returnObj);
						if (!returnObj.isvalid) {
							// 自动修正错误
							var auto = setting.automodify && (this.type == "text" || this.type == "textarea" || this.type == "file");
							if (auto && !initConfig.alertmessage) {
								alert(returnObj.setting.onerror);
								$.formValidator.setTipState(tip, "onShow", setting.onshow);
							}
						}
					}
				});
			} else if (srcTag == "SELECT") {
				// 设置默认值
				if (defaultvalue) {
					if (stype == "select-one") {
						jqobj.attr("value", defaultvalue);
					}
				}
				// 获得焦点或单击
				// stype=="select-one"?"focus":"click"
				jqobj.bind(stype == "select-one" ? "focus" : "click", function() {
					if (!initConfig.alertmessage) {
						$.formValidator.setTipState(tip, "onFocus", setting.onfocus);
					}
				});
				// 选择项目后触发
				jqobj.bind(stype == "select-one" ? "change" : "blur", function() {
					var returnObj = $.formValidator.oneIsValid(this.id, 1);
					if (returnObj == null) {
						return;
					}
					if (returnObj.ajax >= 0 && this.validoldvalue != $(this).val()) {
						$.formValidator.ajaxValid(this.id, returnObj.setting);
					} else {
						$.formValidator.showMessage(returnObj);
					}
				});
			}
		});
	};
	$.fn.inputValidator = function(controlOptions) {
		var settings = {
			isvalid				: false,
			min						: 0,
			max						: 99999999999999,
			type					: "size",
			onerror				: "输入错误",
			validatetype	: "InputValidator",
			empty					: {
				leftempty				: true,
				rightempty			: true,
				leftemptyerror	: null,
				rightemptyerror	: null
			}
		};
		controlOptions = controlOptions || {};
		$.extend(true, settings, controlOptions);
		return this.each(function() {
			$.formValidator.appendValid(this.id, settings);
		});
	};
	$.fn.compareValidator = function(controlOptions) {
		var settings = {
			isvalid				: false,
			desid					: "",
			operateor			: "=",
			onerror				: "输入错误",
			validatetype	: "CompareValidator"
		};
		controlOptions = controlOptions || {};
		$.extend(true, settings, controlOptions);
		return this.each(function() {
			$.formValidator.appendValid(this.id, settings);
		});
	};
	$.fn.regexValidator = function(controlOptions) {
		var settings = {
			isvalid				: false,
			regexp				: "",
			param					: "i",
			datatype			: "string",
			onerror				: "输入的格式不正确",
			validatetype	: "RegexValidator"
		};
		controlOptions = controlOptions || {};
		$.extend(true, settings, controlOptions);
		return this.each(function() {
			$.formValidator.appendValid(this.id, settings);
		});
	};
	$.fn.functionValidator = function(controlOptions) {
		var settings = {
			isvalid				: true,
			fun						: function() {
				this.isvalid = true;
			},
			validatetype	: "FunctionValidator",
			onerror				: "输入错误"
		};
		controlOptions = controlOptions || {};
		$.extend(true, settings, controlOptions);
		return this.each(function() {
			$.formValidator.appendValid(this.id, settings);
		});
	};
	$.fn.ajaxValidator = function(controlOptions) {
		var settings = {
			isvalid				: false,
			lastValid			: "",
			type					: "GET",
			url						: "",
			addidvalue		: true,
			datatype			: "html",
			data					: "",
			async					: true,
			cache					: false,
			beforesend		: function() {
				return true;
			},
			success				: function() {
				return true;
			},
			complete			: function() {},
			processdata		: false,
			error					: function() {},
			buttons				: null,
			onerror				: "服务器校验没有通过",
			onwait				: "正在等待服务器返回数据",
			validatetype	: "AjaxValidator"
		};
		controlOptions = controlOptions || {};
		$.extend(true, settings, controlOptions);
		return this.each(function() {
			$.formValidator.appendValid(this.id, settings);
		});
	};
	$.fn.defaultPassed = function(onshow) {
		return this.each(function() {
			var settings = this.settings;
			for (var i = 1; i < settings.length; i++) {
				settings[i].isvalid = true;
				if (!$.formValidator.getInitConfig(settings[0].validatorgroup).alertmessage) {
					var ls_style = "onSuccess";
					if (onshow) {
						ls_style = "onShow"
					};
					$.formValidator.setTipState(settings[0].tipid, ls_style, settings[0].oncorrect);
				}
			}
		});
	};
	$.fn.unFormValidator = function(unbind) {
		return this.each(function() {
			this.settings[0].bind = !unbind;
			if (unbind) {
				$("#" + this.settings[0].tipid).hide();
			} else {
				$("#" + this.settings[0].tipid).show();
			}
		});
	};
})(jQuery);
//
function getElementWidth(objectId) {
	x = document.getElementById(objectId);
	return x.offsetWidth;
}
//
function getAbsoluteLeft(objectId) {
	o = document.getElementById(objectId);
	oLeft = o.offsetLeft;
	while (o.offsetParent != null) {
		oParent = o.offsetParent;
		oLeft += oParent.offsetLeft;
		o = oParent;
	}
	return oLeft;
}
//
function getAbsoluteTop(objectId) {
	o = document.getElementById(objectId);
	oTop = o.offsetTop;
	while (o.offsetParent != null) {
		oParent = o.offsetParent;
		oTop += oParent.offsetTop;
		o = oParent;
	}
	return oTop;
}