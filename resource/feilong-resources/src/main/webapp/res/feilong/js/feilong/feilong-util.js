/**
 * 飞龙ajax
 * 
 * @type object
 */
var FeiLongAjax = {
	/**
	 * 生成ajax毫秒参数,用于jquery ajax 提交,防止缓存
	 * 
	 * 
	 * @return {String} 拼接参数,当前毫秒
	 */
	getAjaxTimeParam			: function() {
		return "feilongAjaxTime=" + FeiLongTime.getTime();
	},
	/**
	 * 添加参数
	 * 
	 * @param {string}
	 *          paramsString paramsString 返回添加前的参数字符串
	 * @param {string}
	 *          paramName 参数名称
	 * @param {object}
	 *          paramValue 参数值
	 * @return {返回添加完的参数}
	 */
	addAjaxParam					: function(paramsString, paramName, paramValue) {
		return paramsString + "&" + paramName + "=" + paramValue;
	},
	/**
	 * 获得ajax请求返回值
	 * 
	 * @param {String}
	 *          path 诸如:UserAjax._path
	 * @param {String}
	 *          scriptName
	 * @param {String}
	 *          methodName 方法名称
	 * @param {String...String}
	 *          vararg_params p0 ajax后台方法参数
	 * @return {Object} 返回值
	 */
	getReturnValueBoolean	: function(path, scriptName, methodName, vararg_params) {
		var returnValue;
		// 设置成同步
		DWREngine.setAsync(false);
		dwr.engine._execute(path, scriptName, methodName, vararg_params, function(data) {
			returnValue = data;
		});
		// 重新设置为异步方式
		DWREngine.setAsync(true);
		return returnValue;
	}
};
/**
 * 飞龙数字
 * 
 * @type
 */
var FeiLongNumber = {
	/**
	 * 生成数字区间内的随机数
	 * 
	 * @param {Number}
	 *          startNumber 数字区间开始数字
	 * @param {Number}
	 *          endNumber 数字区间结束数字
	 * @return {Number} 区间内的随机数
	 */
	getRandomNumber	: function(startNumber, endNumber) {
		return startNumber + parseInt(Math.random() * (endNumber - startNumber));
	}
};
/**
 * 飞龙效果
 * 
 * @type
 */
var FeiLongEffects = {
	/**
	 * 通过取element元素id的形式,将form或者自定义parent内的表单元素值取出来,依次赋值到计先设定的parent内,用于ajax提交成功之后显示
	 * 
	 * <pre>
	 * For example:FeiLongEffects.copyFormElementsToShowById(&quot;#div_updatePersonInfo_show&quot;, &quot;#div_updatePersonInfo&quot;, &quot;_show&quot;);
	 * 
	 * </pre>
	 * 
	 * @param {string}
	 *          limitedToSelectorParent 计先设定的parent
	 * @param {string}
	 *          fromSelectorParent 从form或者自定义parent内,可以为form 也可以为div
	 * @param {string}
	 *          suffix 一一对应的显示元素后缀,如文本框的id为"text_name",显示span的id为"text_name_show",则suffix为"_show"
	 */
	copyFormElementsToShowById				: function(limitedToSelectorParent, fromSelectorParent, suffix) {
		/**
		 * 要循环的jquery Elements 元素集合
		 */
		var jquery_formElements = $(fromSelectorParent + " [name]");
		/**
		 * 文本框循环赋值
		 */
		$.each(jquery_formElements, function(i, element) {
			var thisId = element.id;
			// 显示的id
			var show_selector = "#" + thisId + suffix;
			var JshowObj = $(limitedToSelectorParent + " " + show_selector);
			FeiLongEffects.copyFormElementsToShow(element, JshowObj);
		});
	},
	/**
	 * 通过取element元素className的形式,将form或者自定义parent内的表单元素值取出来,依次赋值到计先设定的parent内,用于ajax提交成功之后显示
	 * 
	 * <pre>
	 * 当表单元素含有多个className的时候
	 * ,关联className 必须写在第一个
	 * </pre>
	 * 
	 * @param {Jquery}
	 *          jquery_toShowParent 要显示的jquery父对象
	 * @param {Jquery}
	 *          jquery_formElements 要循环的jquery Elements 元素集合
	 * @param {}
	 *          suffix
	 */
	copyFormElementsToShowByClassName	: function(jquery_toShowParent, jquery_formElements, suffix) {
		/**
		 * 文本框循环赋值
		 */
		$.each(jquery_formElements, function(i, element) {
			var thisClass = element.className.split(' ')[0];
			// 显示的id
			var show_selector = "." + thisClass + suffix;
			var JshowObj = jquery_toShowParent.find(show_selector);
			FeiLongEffects.copyFormElementsToShow(element, JshowObj);
		});
	},
	/**
	 * 底层将form或者自定义parent内的表单元素值取出来,依次赋值到计先设定的parent内,用于ajax提交成功之后显示
	 * 
	 * @param {object}
	 *          element 表单元素element
	 * @param {jquery}
	 *          JshowObj jquery显示元素
	 */
	copyFormElementsToShow						: function(element, JshowObj) {
		switch (element.type.toLowerCase()) {
			/**
			 * 文本框
			 */
			case "text" :
				JshowObj.html($(element).val());
				break;
			/**
			 * 文本域
			 */
			case "textarea" :
				JshowObj.html($(element).val());
				break;
			/**
			 * 单选框
			 */
			case "radio" :
				/**
				 * 此处有个小问题,当单选框有多个选项的时候,会重复赋值
				 */
				var radioName = $(element).attr("name");
				// 单选框选中的文字值
				JshowObj.html($("input[name='" + radioName + "'][checked]").attr("textValue"));
				break;
			/**
			 * 复选框
			 */
			case "checkbox" :
				var checkboxName = $(element).attr("name");
				// 复选框选中的文字值
				JshowObj.html($("input[name='" + checkboxName + "'][checked]").attr("textValue"));
				break;
			/**
			 * 单选下拉框
			 */
			case "select-one" :
				if (null != $(element).val() && "" != $(element).val() && 0 != $(element).val()) {
					JshowObj.html(FeilongForm.getSelectText(element));
				} else {
					/**
					 * 如果没有选择,或者选择的默认的"请选择" 则赋值为空
					 */
					JshowObj.html("");
				}
				break;
			default :
				break;
		}
	},
	/**
	 * 广告
	 * 
	 * @type
	 */
	ad																: {
		/**
		 * 参数配置
		 * 
		 * @type
		 */
		initFloatConfig	: {
			x							: 20,// 浮动层的初始位置，分别对应层的初始X坐标和Y坐标
			y							: 250,
			// 层移动的步长，值越大移动速度越快
			step					: 1,
			// 层移动的时间间隔,单位为毫秒,值越小移动速度越快
			delay					: 12,
			flag_goRight	: true,// 判断层X坐标是否在在控制范围之内,flag_goRight为真是层向右移动,否则向左;
			// 判断层Y坐标是否在在控制范围之内flag_goDown为真是层向下移动,否则向上
			flag_goDown		: true,
			// 层移动范围的左边界 坐标
			left_border		: 0,
			// 层移动范围 上边界 坐标
			top_border		: 0
		},
		/**
		 * 漂浮广告
		 * 
		 * @param {string}
		 *          selector 选择器
		 * @param {int}
		 *          delay 定时时间 单位毫秒
		 */
		floatAD					: function(selector, delay) {
			// 每delay秒执行一次floatAD函数
			var _currentTime = setInterval(function() {
				FeiLongEffects.ad.floatAD_bottom(selector);
			}, delay);
			$(selector).mouseover(function() {
				// 层在鼠标移上时清除上面的间隔事件，实现层在的鼠标移上时停止运动的效果
				clearInterval(_currentTime);
			}).mouseout(function() {
				// 层在鼠标移开时开始间隔事件，实现层在的鼠标移开时继续运动的效果
				_currentTime = setInterval(function() {
					FeiLongEffects.ad.floatAD_bottom(selector);
				}, delay);
			});
		},
		/**
		 * 漂浮广告底层操作代码
		 * 
		 * @param {string}
		 *          selector 选择器
		 */
		floatAD_bottom	: function(selector) {
			// 参数配置
			var setting = FeiLongEffects.ad.initFloatConfig;
			// 层移动的右边界
			var right_border = document.documentElement.clientWidth - $(selector)[0].offsetWidth;
			// 层移动的下边界
			var bottom_border = document.documentElement.clientHeight - $(selector)[0].offsetHeight;
			$(selector).css({
				// 更新层的X坐标，实现X轴方向上的运动；
				"left"			: setting.x + document.documentElement.scrollLeft,
				// 更新层的Y坐标，实现Y轴方向上的运动；
				"top"				: setting.y + document.documentElement.scrollTop,
				"display"		: "block",
				visibility	: "visible"
			});
			/**
			 * 通过判断层的范围决定层在X轴上的运动方向
			 */
			setting.x = setting.x + setting.step * (setting.flag_goRight ? 1 : -1);
			// 层超出左边界时的处理
			if (setting.x < setting.left_border) {
				setting.flag_goRight = true;
				setting.x = setting.left_border;
			}
			// 层超出右边界时的处理
			if (setting.x > right_border) {
				setting.flag_goRight = false;
				setting.x = right_border;
			}
			/**
			 * 通过判断层的范围决定层在Y轴上的运动方向
			 */
			setting.y = setting.y + setting.step * (setting.flag_goDown ? 1 : -1);
			// 层超出上边界时的处理
			if (setting.y < setting.top_border) {
				setting.flag_goDown = true;
				setting.y = setting.top_border;
			}
			// 层超出下边界时的处理
			if (setting.y > bottom_border) {
				setting.flag_goDown = false;
				setting.y = bottom_border;
			}
		},
		/**
		 * 自动切换广告
		 * 
		 * @type
		 */
		controlAD				: {
			initFloatConfig	: {
				// 定时器默认显示第几个
				i			: 1,
				// 动画执行时间间隔
				delay	: 3000
			},
			/**
			 * 广告自动切换
			 * 
			 * @param {string}
			 *          controlSelector 控制器
			 * @param {string }
			 *          toggleSelector 显示隐藏的内容
			 */
			controlToggle		: function(controlSelector, toggleSelector) {
				// 参数配置
				var setting = FeiLongEffects.ad.controlAD.initFloatConfig;
				FeiLongEffects.toggleTableCard(controlSelector, toggleSelector);
				// 控制器元素长度
				var controlSelector_length = $(controlSelector).length;
				var time_ad_control = setInterval(function() {
					setting.i = setting.i + 1;
					if (setting.i > controlSelector_length) {
						// 重头开始
						setting.i = 1;
					}
					// 模拟点击
					$(controlSelector).eq(setting.i - 1).click();
				}, setting.delay);
				// $(controlSelector).click(function() {
				// clearInterval(time_ad_control);
				// }).mouseout(function() {
				// time_ad_control = setInterval(function() {
				// i = i + 1;
				// if (i > controlSelector_length) {
				// // 重头开始
				// i = 1;
				// }
				// // 模拟点击
				// $(controlSelector).eq(i - 1).click();
				// }, delay);
				// });
			}
		}
	},
	/**
	 * 选项卡式切换
	 * 
	 * @param {string}
	 *          controlSelector 控制器
	 * @param {string}
	 *          toggleSelector 显示隐藏的内容
	 * @param {string[]}
	 *          controlSelectorClassNames 控制器样式(可选) 字符串数组 {"选中","不选中"}
	 * 
	 */
	toggleTableCard										: function(controlSelector, toggleSelector,/* 控制器样式 */controlSelectorClassNames) {
		$(controlSelector).click(function() {
			if (FeiLongUtil.isNotNull(controlSelectorClassNames)) {
				$(controlSelector).removeClass().addClass(controlSelectorClassNames[1]);
				$(this).removeClass().addClass(controlSelectorClassNames[0]);
			} else {
				$(controlSelector).css({
					"background"	: "transparent"
				});
				$(this).css({
					"background"	: "#ff9933"
				});
			}
			$(toggleSelector).css("display", "none");
			// 当前索引
			var this_index = $(controlSelector).index($(this));
			FeiLongEffects.ad.controlAD.initFloatConfig.i = this_index + 1;
			$(toggleSelector).eq(this_index).css("display", "block");
		});
	},
	/**
	 * 选项卡式切换
	 * 
	 * @param {string}
	 *          controlSelector 控制器
	 * @param {string}
	 *          toggleSelector 显示隐藏的内容
	 * @param {string[]}
	 *          controlSelectorClassNames 控制器样式(可选) 字符串数组 {"选中","不选中"}
	 * 
	 */
	toggleTableCardMouseOver					: function(controlSelector, toggleSelector,/* 控制器样式 */controlSelectorClassNames) {
		$(controlSelector).mouseover(function() {
			$(controlSelector).removeClass(controlSelectorClassNames[0]);
			$(this).addClass(controlSelectorClassNames[0]);
			$(toggleSelector).css("display", "none");
			$(toggleSelector).eq($(controlSelector).index($(this))).css("display", "block");
		});
	},
	/**
	 * 数字滚动
	 * 
	 * @param {string}
	 *          selector 选择器
	 * 
	 * @param {int}
	 *          goalNumber 目标最终数值
	 * @param {int}
	 *          delay 延迟时间
	 */
	numberRoll												: function(selector/* 选择器 */, goalNumber/* 目标最终数值 */, delay/* 延迟时间 */) {
		$(selector).text(0);
		// 当前对象元素值
		var span_countValue = 0;
		// 定时器每格滚动步长
		var stepValue = 0;
		// 目标值和现在的值差距
		var marginValue;
		// 定时器
		var time_numberRoll = setInterval(function() {
			span_countValue = parseInt($(selector).text());
			// 目标值和现在的值差距
			marginValue = goalNumber - span_countValue;
			if (marginValue <= 0) {
				$(selector).text(goalNumber);
				clearInterval(time_numberRoll);
			} else {
				if (marginValue >= 1000000) {
					stepValue = FeiLongNumber.getRandomNumber(800000, 888888);
				} else if (marginValue >= 100000 && marginValue < 1000000) {
					stepValue = FeiLongNumber.getRandomNumber(80000, 88888);
				} else if (marginValue >= 10000 && marginValue < 100000) {
					stepValue = FeiLongNumber.getRandomNumber(8000, 8888);
				} else if (marginValue >= 1000 && marginValue < 10000) {
					stepValue = FeiLongNumber.getRandomNumber(800, 888);
				} else if (marginValue >= 500 && marginValue < 1000) {
					stepValue = FeiLongNumber.getRandomNumber(80, 88);
				} else if (marginValue >= 50 && marginValue < 500) {
					stepValue = FeiLongNumber.getRandomNumber(18, 50);
				} else if (marginValue < 50) {
					stepValue = FeiLongNumber.getRandomNumber(3, 5);
				}
				$(selector).text(span_countValue + stepValue);
			}
		}, delay);
	},
	/**
	 * 文本框 获得焦点以及失去焦点切换文字值
	 * 
	 * @param selector
	 *          选择器
	 * @param textValue
	 *          文字
	 * @return
	 */
	toggleText												: function(selector, textValue) {
		$(selector).focus(function() {
			if ($(this).val() == textValue) {
				$(this).val("");
			}
		}).blur(function() {
			if (FeiLongUtil.isNull($(this).val())) {
				$(this).val(textValue);
			}
		});
	},
	/**
	 * 默认显示内容显示提示
	 * 
	 * @param selector
	 *          选择器
	 * @param content
	 *          显示提示内容
	 * @param isSuccessWarn
	 *          是不是成功提示(正确提示/友好提示)
	 * @return
	 */
	showTableFormWarn									: function(selector, content, isSuccessWarn) {
		/**
		 * 默认是错误提示(不成功提示) 样式
		 */
		var class_Name = "no";
		if (arguments.length == 3) {
			if (true == isSuccessWarn) {
				class_Name = "yes";
			}
		}
		showTableWarn(selector, "<div class=" + class_Name + ">" + content + "</div>");
	},
	/**
	 * 自定义显示提示
	 * 
	 * @param selector
	 *          选择器
	 * @param content
	 *          显示提示内容
	 * @param isSuccessWarn
	 *          是不是成功提示(正确提示/友好提示)
	 * @return
	 */
	showTableWarn											: function(selector, content) {
		$(selector).parents("td").next("td").html(content);
	},
	// jquery对象切换两个样式
	toggleTwoClass										: function(jObj, removeClass, addClass) {
		jObj.removeClass(removeClass).addClass(addClass);
	}
};
/**
 * 飞龙工具
 * 
 * @type
 */
var FeiLongUtil = {
	/**
	 * 将单词的首字母变成大写
	 * 
	 * @param {string}
	 *          word 单词
	 * @return {String} 首字母变成大写
	 */
	firstCharToUpperCase							: function(word) {
		if (FeiLongUtil.isNull(word)) {
			return "";
		}
		var firstChar = word.substr(0, 1).toUpperCase();
		var elseString = word.substring(1, word.length);
		return firstChar + elseString;
	},
	/**
	 * 获得表单元素值
	 * 
	 * @param {string}
	 *          selector 选择器
	 * @param {boolean}
	 *          isTrim 可选参数 是否去除空格
	 * @return {string} 表单元素值
	 */
	getValue													: function(selector, isTrim) {
		var l = arguments.length;
		if (1 == l) {
			return $.trim($(selector).val());
		}
		return isTrim ? $.trim($(selector).val()) : $(selector).val();
	},
	/**
	 * 不是空
	 * 
	 * @param {string}
	 *          stringValue 字符串值
	 * @return {boolean}不为空返回true
	 */
	isNotNull													: function(stringValue) {
		return !FeiLongUtil.isNull(stringValue);
	},
	/**
	 * 是否为空
	 * 
	 * @param {string}
	 *          stringValue 字符串值
	 * @return {boolean} 空返回true
	 */
	isNull														: function(stringValue) {
		return (null == stringValue || "" == $.trim(stringValue));
	},
	/**
	 * 是否不是空
	 * 
	 * @param {string}
	 *          selector selector 选择器
	 * @return {boolean}不为空返回true
	 */
	$isNotNull												: function(selector) {
		return !FeiLongUtil.$isNull(selector);
	},
	/**
	 * 是否为空
	 * 
	 * @param {string}
	 *          selector 选择器
	 * @return {boolean} 空返回true
	 */
	$isNull														: function(selector) {
		var v = FeiLongUtil.getValue(selector, true);
		return (null == v || "" == v);
	},
	/**
	 * 是否为空或者0 一般用于判断下拉框是否选择了
	 * 
	 * @param {string}
	 *          selector 选择器
	 * @return {boolean} 空或者0返回true
	 */
	$isNullOrZero											: function(selector) {
		var v = FeiLongUtil.getValue(selector, true);
		return (null == v || "" == v || 0 == v);
	},
	/**
	 * jquery seletor 或者普通元素转成普通元素
	 * 
	 * @param {string
	 *          or object} selectorOrElement
	 * @return {普通元素element}
	 */
	convertSelectorOrElementToElement	: function(selectorOrElement) {
		var element;
		if ("string" == typeof(selectorOrElement)) {
			element = $(selectorOrElement).get(0);
		} else if ("object" == typeof(selectorOrElement)) {
			element = selectorOrElement;
		}
		return element;
	}
};
var scriptName = "ParamAjax";
// 飞龙表单操作
var FeilongForm = {
	/**
	 * 二级级联
	 * 
	 * @param {string}
	 *          nextSelector 下一个jquery元素
	 * @param {string}
	 *          methodName 方法名称
	 * @param {string}
	 *          value 值字段
	 * @param {string}
	 *          label 显示文字
	 * @param {string}
	 *          p0 ajax后台方法参数
	 */
	cascade2					: function(/* 下一个jquery元素 */nextSelector,/* 方法名称 */methodName,/* 值字段 */value,/* 显示文字 */label,/* ajax后台方法参数 */p0) {
		FeilongForm.removeAllOptions(nextSelector);
		FeilongForm.addOption(nextSelector, "", "");
		if (FeiLongUtil.isNotNull(p0)) {
			dwr.engine._execute(ParamAjax._path, scriptName, methodName, p0, function(data) {
				FeilongForm.addOptions(nextSelector, data, value, label);
			});
		}
	},
	addOption					: function(selector, value, text) {// select 新增一个节点
		var l = arguments.length;
		var e;
		if ("string" == typeof(selector)) {
			e = $(selector)[0];
		} else {
			if ("object" == typeof(selector)) {
				e = selector[0];
			}
		}
		if (null != e) {
			e.options.add(new Option(text, value));
		}
	},
	// 加载集合list数据
	addOptions				: function(selector, data, value, label) {
		if (null != data) {
			// 数据集合长度
			var c = data.length;
			// 参数长度
			// 4个参数 第一个参数为selector或者jquery对象,第二个参数为数据集合,第三个参数为下拉框value字段,第四个为下拉框label字段
			var l = arguments.length;
			// 判断元素
			var e;
			if ("string" == typeof(selector)) {
				e = $(selector)[0];
			} else if ("object" == typeof(selector)) {
				e = selector[0];
			}
			/**
			 * 循环生成option
			 */
			var o;
			for (var i = 0; i < c; i++) {
				o = new Option(data[i][label], data[i][value]);
				e.options.add(o);
			}
		}
	},
	addViewOptions		: function(id, data, value, text, isClearSelect) {// 加载视图数据
		// id
		// 要么传过来不带#的id形式
		// 要么是jquery对象
		/**
		 * 参数长度
		 */
		var l = arguments.length;
		var e;
		if ("string" == typeof(id)) {
			e = $("#" + id)[0];
		} else {
			if ("object" == typeof(id)) {
				/**
				 * 是否是jquery对象 此时id 为$(this) 等形式
				 */
				e = id[0];
			}
		}
		if (l >= 5 && true == isClearSelect) {
			/**
			 * 第五个参数 是否清空
			 */
			$(e).empty();
		}
		var o;
		/**
		 * 数据长度
		 */
		var c = data.length;
		for (var i = 0; i < c; i++) {
			o = new Option(data[i]["id"][text], data[i]["id"][value]);
			e.options.add(o);
		}
	},
	/**
	 * 修改密码
	 * 
	 * @param {string}
	 *          oldSelector 旧密码选择器
	 * @param {string}
	 *          new1Selector 新密码
	 * @param {string}
	 *          new2Selector 确认密码
	 * @return {Boolean}
	 * @deprecated 不建议使用
	 */
	changePassword		: function(oldSelector, new1Selector, new2Selector) {// 修改密码
		if (FeiLongUtil.$isNull(oldSelector)) {
			alert("请输入原密码");
			$(oldSelector).focus();
			return false;
		} else if (FeiLongUtil.$isNull(new1Selector)) {
			alert("请输入新密码");
			$(new1Selector).focus();
			return false;
		} else if (FeiLongUtil.getValue(new2Selector) != FeiLongUtil.getValue(new1Selector)) {
			alert("两次输入的密码不一样");
			$(new2Selector).select();
			return false;
		} else if (!FeiLongValidate.length(new1Selector, charLength.minPass, charLength.maxPass)) {
			alert("密码长度为" + charLength.minPass + "-" + charLength.maxPass + "");
			$(new1Selector).select();
			return false;
		} else if (!FeiLongValidate.format(new1Selector, patterns.pass)) {
			alert(formatWarn.formatPass);
			$(new1Selector).select();
			return false;
		}
		return true;
	},
	/**
	 * 获得下拉框的文本
	 * 
	 * @param {selectorOrElement}
	 *          jquery seletor 或者普通元素
	 * @return {获得下拉框的文本}
	 */
	getSelectText			: function(selectorOrElement) {
		var element = FeiLongUtil.convertSelectorOrElementToElement(selectorOrElement);
		return element.options[element.selectedIndex].text;
	},
	removeAllOptions	: function(selector) {// 是否是jquery对象
		if ("string" == typeof(selector)) {
			$(selector).empty();
		} else {
			if ("object" == typeof(selector)) {
				selector.empty();
			}
		}
	}
};
/**
 * 飞龙时间 作者:金鑫
 */
var FeiLongTime = {
	/**
	 * 按照格式获得时间的字符串
	 * 
	 * @param {string}
	 *          pattern 格式
	 * @return {String} 格式化的时间
	 */
	getDate				: function(pattern/* 模式 */) {
		var returnValue = "";
		var now = new Date();
		var yy = now.getFullYear();
		var MM = now.getMonth() + 1;
		var dd = now.getDate();
		if ("yyyy-MM-dd" == pattern) {
			returnValue = yy + "-" + MM + "-" + dd;
		}
		return returnValue;
	},
	/**
	 * 通过出生日期获得年龄
	 * 
	 * @param {string}
	 *          birthday 出生日期
	 */
	getAge				: function(birthday) {
		return FeiLongTime.getYear() - birthday.substr(0, 4) + 1;
	},
	/**
	 * 获得年份
	 * 
	 * @return {Number} 现在年份
	 */
	getYear				: function() {
		return new Date().getFullYear();
	},
	/**
	 * 返回date对象的时间戳表示法(毫秒表示)
	 * 
	 * @return {Number} 现在毫秒
	 */
	getTime				: function() {
		return new Date().getTime();
	},
	/**
	 * 是不是闰年
	 */
	isLeapYear		: function(year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	},
	/**
	 * 返回月最大天数
	 */
	maxDay				: function(year, month) {
		if (2 == month) return this.isLeapYear(year) ? 29 : 28;
		else if (this.isBigMonth(month)) return 31;
		else if (this.isSmallMonth(month)) return 30;
	},
	/**
	 * 是不是大月 31 天的
	 */
	isBigMonth		: function(month) {
		var big = [1, 3, 5, 7, 8, 10, 12];
		return FeiLongArray.isInArray(month, big);
	},
	/**
	 * 是不是小月 30天的
	 */
	isSmallMonth	: function(month) {
		var small = [4, 6, 9, 11];
		return FeiLongArray.isInArray(month, small);
	},
	/**
	 * 增加天数 支持参数形式: ("20081012",1)
	 */
	addDay				: function() {
		var len = arguments.length;
		var year, month, day;
		var dayCount = arguments[len - 1];
		var val;
		if (2 == len) {
			var old = arguments[0];
			year = parseInt(old.substring(0, 4));
			month = parseInt(old.substr(4, 2));
			day = parseInt(old.substr(6, 2));
			var resultDay = day + dayCount;
			var maxDay = this.maxDay(year, month);
			if (resultDay <= maxDay) {
				val = parseInt(old) + dayCount;
			} else {
				var thenDay = resultDay - maxDay;
				var newMonth = month + 1;
				var newYear = year;
				if (newMonth > 12) {
					newYear = year + 1;
					newMonth = newMonth - 12;
				}
				val = "" + newYear + (newMonth < 10 ? ("0" + newMonth) : newMonth) + (thenDay < 10 ? ("0" + thenDay) : thenDay);
			}
		}
		return val;
	}
};
/* region 动态创建dom元素 */
var FeiLongDom = {
	/* 创建input */
	createInput		: function(/* 表单元素id名称 */objId, /* 表单元素类型 不填默认为text */typeName,/* 默认值 */defaultValue) {
		var t_name = "";
		if (FeiLongUtil.isNull(typeName)) {
			t_name = "text";
		} else {
			t_name = typeName;
		}
		var returnValue = "<input type=\"" + t_name + "\" id=\"" + objId + "\" ";
		if (FeiLongUtil.isNotNull(defaultValue)) {
			returnValue += "value=\"" + defaultValue + "\" ";
		}
		returnValue += "/>";
		return returnValue;
	},
	/* 创建span */
	createSpan		: function(/* span文字 */spanText,/* 类名 默认color_666 */className) {
		var c_name = "";
		if (FeiLongUtil.isNull(className)) {
			c_name = "color_666";
		} else {
			c_name = className;
		}
		return "<span class=\"" + c_name + "\">" + spanText + "</span>";
	},
	/* 创建必填的星星 */
	createMustIn	: function() {
		return FeiLongDom.createSpan("*", "s");
	},
	/* 创建button */
	createButton	: function(/* id名称 */objId, /* button文字 */buttonText,/* 表单元素类型 不填默认为button */typeName) {
		var t_name = "";
		if (FeiLongUtil.isNull(typeName)) {
			t_name = "button";
		} else {
			t_name = typeName;
		}
		return "<button id=\"" + objId + "\" type=\"" + typeName + "\">" + buttonText + "</button>";
	}
};
/**
 * 动态创建表格 第一个
 * 
 * @param {string}
 *          content 表格文字
 * @param {boolean}
 *          isMustIn 是否必填
 * @return {string} 动态表格html
 */
function creatDomTdTiTle(content, isMustIn) {
	var returnValue = "<td class=\"td_title\">";
	// 如果是必填,则显示红星
	if (isMustIn) {
		returnValue += "<span class=\"s\">*</span>";
	}
	returnValue += "<span class=\"color_666\">" + content + "</span>";
	returnValue += "</td>";
	return returnValue;
}
/* endregion */
/**
 * 飞龙文件
 * 
 * @type
 */
var FeiLongFile = {
	/**
	 * 获得路径中的文件名称(不带后缀)
	 * 
	 * @param {string}
	 *          path 路径
	 * @return {String} 不带后缀
	 */
	getFileName							: function(path) {
		var fileNameWithPostfix = FeiLongFile.getFileNameWithPostfix(path);
		return fileNameWithPostfix.substring(0, fileNameWithPostfix.indexOf("."));
	},
	/**
	 * 获得路径中的文件名称(带后缀)
	 * 
	 * @param {string}
	 *          path 路径
	 * @return {String}带后缀
	 */
	getFileNameWithPostfix	: function(path) {
		return path.substring(path.lastIndexOf("\\") + 1);
	},
	/**
	 * 获得路径中的文件后缀名
	 * 
	 * @param {string}
	 *          path 路径
	 * @return {String}后缀名
	 */
	getFilePostfix					: function(path) {
		return path.substring(path.lastIndexOf(".") + 1);
	}
};
/**
 * 飞龙数组 作者:金鑫
 */
var FeiLongArray = {
	/**
	 * 判断一个值在不在数组里面
	 */
	isInArray	: function(value, array) {
		var flag = false;
		for (var i = 0; i < array.length; i++) {
			if (array[i] == value) {
				flag = true;
				break;
			}
		}
		return flag;
	}
};
/**
 * FeiLongFCKEditor
 * 
 * @type
 */
var FeiLongFCKEditor = {
	/**
	 * 验证FCKEditor内容
	 * 
	 * @param {string}
	 *          editorName 编辑器名称
	 * @param {int}
	 *          maxLength 最大长度
	 * @return {Boolean}
	 */
	validateFCKEditorContent	: function(editorName, maxLength) {
		var contentLength = FeiLongFCKEditor.getFCKEditorLength(editorName);
		// alert(contentLength);
		if (contentLength == 0) {
			return "内容不能为空!";
		} else {
			if (contentLength > maxLength) {
				return "内容不能超过" + maxLength + "个汉字!";
			} else {
				return true;
			}
		}
		return false;
	},
	/**
	 * 获得FCKEditor字符串长度
	 * 
	 * @param {string}
	 *          editorName 编辑器的名称
	 * @return {长度}
	 */
	getFCKEditorLength				: function(editorName) {
		var editor = FCKeditorAPI.GetInstance(editorName);
		var editorDocument = editor.EditorDocument;
		var iLength;
		if (document.all) {// If Internet Explorer.
			iLength = editorDocument.body.innerText.length;
		} else {// If Gecko.
			var r = editorDocument.createRange();
			r.selectNodeContents(editorDocument.body);
			iLength = r.toString().length;
		}
		return iLength;
	}
};
/**
 * 写文档
 * 
 * @param text
 * @return
 */
function write(text) {
	document.write(text);
}
/**
 * 中文打招呼
 * 
 * @return
 */
function sayChineseHello() {
	var hello = sayHello();
	write(hello[0]);
	write("<img src='res/feilong/images/face/xy2/" + hello[1] + ".gif'></img>");
}
/**
 * 中文打招呼
 * 
 * @return
 */
function sayHello() {
	var date = new Date();
	var hh = date.getHours();
	var t = "";
	var i_path = "23";
	if (hh >= 0 && hh < 6) {
		t = "凌晨好";
		i_path = "34";
	} else if (hh >= 6 && hh < 10) {
		t = "早上好";
	} else if (hh >= 10 && hh < 13) {
		t = "中午好";
	} else if (hh >= 13 && hh < 18) {
		t = "下午好";
	} else if (hh >= 18 && hh < 24) {
		t = "晚上好";
		i_path = "34";
	}
	return [t, i_path];
}
/**
 * ajax返回提示内容
 * 
 * @param content
 *          提示内容
 * @param i
 *          标识成功还是失败,1成功 0失败
 * @param isShow
 *          该层是否显示,true 显示 false 不显示
 * @return
 */
function ajaxReturnValueDiv(content, i, isShow) {
	if (arguments.length == 2 || isShow) {
		var class_Name = i == 1 ? "successWarn" : "failWarn";
		$("#div_returnValue").html(content).addClass(class_Name);
	} else {
		$("#div_returnValue").css("display", "none");
	}
}
/**
 * 转换数字颜色
 * 
 * @param decimal
 * @return
 */
function decimalColor(decimal) {
	var color = "";
	if (decimal >= 2000) {
		color = "green";
	} else if (decimal >= 1000 && decimal < 2000) {
		color = "#ff00ff";
	} else if (decimal >= 200 && decimal < 1000) {
		color = "blue";
	} else if (decimal >= 0 && decimal < 200) {
		color = "red";
	}
	return "<span style='color:" + color + "'>" + decimal + "</span>";
}
/* 回车键变table键方便用户切换 */
function enterToTable() {
	if (event.keyCode == 13) {
		event.keyCode = 9;
	}
}
/**
 * 加为收藏
 */
function addFavorite() {
	if (isIE) external.AddFavorite(url, title);
	else {
		try {
			window.sidebar.addPanel(title, url, "");
		} catch (e) {
			alert("加入收藏失败,请使用Ctrl+D进行添加!");
		}
	}
}
/**
 * 设为首页
 */
function setHomePage(elem) {
	if (isIE) {
		elem.style.behavior = "url(#default#homepage)";
		elem.setHomePage(url);
	} else {
		if (isFF) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				var prefs = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
				prefs.setCharPref("browser.startup.homepage", location.href);
			} catch (e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将[signed.applets.codebase_principal_support]设置为'true'");
			}
		}
	}
}
/**
 * 飞龙浏览器
 * 
 * @type object
 */
var FeiLongBrowser = {
	/**
	 * 刷新
	 */
	refresh	: function() {
		location.href = location.href;
	}
};
/**
 * 刷新
 */
function refresh() {
	location.href = location.href;
}
// 滚动插件
(function($) {
	$.fn.extend({
		Scroll	: function(opt, callback) {
			// 参数初始化
			if (!opt) var opt = {};
			var _this = this.eq(0).find("ul:first");
			var lineH = _this.find("li:first").height(), // 获取行高
			line = opt.line ? parseInt(opt.line, 10) : parseInt(this.height() / lineH, 10), // 每次滚动的行数，默认为一屏，即父容器高度
			speed = opt.speed ? parseInt(opt.speed, 10) : 500, // 卷动速度，数值越大，速度越慢（毫秒）
			timer = opt.timer ? parseInt(opt.timer, 10) : 3000;
			if (line == 0) line = 1;
			var upHeight = 0 - line * lineH;
			// 滚动函数
			scrollUp = function() {
				_this.animate({
					marginTop	: upHeight
				}, speed, function() {
					for (i = 1; i <= line; i++) {
						_this.find("li:first").appendTo(_this);
					}
					_this.css({
						marginTop	: 0
					});
				});
			}
			// 鼠标事件绑定
			_this.hover(function() {
				clearInterval(timerID);
			}, function() {
				timerID = setInterval("scrollUp()", timer);
			}).mouseout();
		}
	})
})(jQuery);
(function($) {
	$.fn.scrollIt = function(options) {
		options = $.extend({
			overcss		: {// 外框的CSS
				position	: "absolute",
				width			: "200px",
				height		: "20px",
				overflow	: "hidden"
			},
			offset		: 20,// 每次移动量
			itemCount	: 0,// 项目数
			speed			: 500,// 滚动速度
			delay			: 1000,// 停顿时间
			innerEL		: "td",// 项目的 selector
			outerEL		: null,// 项目的包容器的 selector 如果为null则等同于调用者
			hover			: true, // 鼠标移过是否停止
			v					: false
			// 横向还是纵向
	}	, options);
		options.overcss = $.extend(options.overcss, {
			position	: "absolute",
			overflow	: "hidden"
		});
		options.el = $(this);
		$(this).wrap($("<div></div>").css(options.overcss)).css({
			position	: "absolute",
			overflow	: "hidden"
		});
		if (options.itemCount == 0) options.itemCount = $(this).find(options.innerEL).length;
		var pos = 0;
		if (options.outerEL) options.outerEL = $(this).find(options.outerEL);
		else options.outerEL = $(this);
		$(this).find(options.innerEL).clone(true).appendTo(options.outerEL);
		var scrollHandler;
		if (options.v) {
			scrollHandler = setInterval(function() {
				if (pos == options.itemCount) {
					pos = 0;
					options.el.get(0).style.top = "0";
				}
				options.el.animate({
					top	: "-=" + options.offset + "px"
				}, options.speed);
				pos++;
			}, options.delay);
		} else {
			scrollHandler = setInterval(function() {
				if (pos == options.itemCount) {
					pos = 0;
					options.el.get(0).style.left = "0";
				}
				options.el.animate({
					left	: "-=" + options.offset + "px"
				}, options.speed);
				pos++;
			}, options.delay);
		}
		if (options.hover) {
			$(this).find(options.innerEL).hover(function() {
				clearInterval(scrollHandler);
			}, function() {
				if (options.v) {
					scrollHandler = setInterval(function() {
						if (pos == options.itemCount) {
							pos = 0;
							options.el.get(0).style.top = "0";
						}
						options.el.animate({
							top	: "-=" + options.offset + "px"
						}, options.speed);
						pos++;
					}, options.delay);
				} else {
					scrollHandler = setInterval(function() {
						if (pos == options.itemCount) {
							pos = 0;
							options.el.get(0).style.left = "0";
						}
						options.el.animate({
							left	: "-=" + options.offset + "px"
						}, options.speed);
						pos++;
					}, options.delay);
				}
			});
		}
	}
})(jQuery);
