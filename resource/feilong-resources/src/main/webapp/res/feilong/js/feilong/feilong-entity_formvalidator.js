/**
 * FeiLongFormValidator entity
 * 
 * @type
 */
var entity_FeiLongFormValidator = {
	/**
	 * 表单id名称
	 * 
	 * @type String
	 */
	formId										: "frm",
	/**
	 * 验证不通过
	 * 
	 * @param {string}
	 *          msg 提示
	 */
	onerror										: function(msg) {
		alert(msg)
	},
	/**
	 * 成功方法
	 * 
	 * @return {Boolean}
	 */
	onsuccess									: function() {
		return true;
	},
	/**
	 * 当前验证方法
	 */
	currentValidatorFunction	: function() {
	}
};
/**
 * 飞龙验证的entity
 * 
 * @type object
 */
var entity_FeiLongValidate = {
	/**
	 * 注册时email
	 * 
	 * @type
	 */
	email				: {
		/**
		 * email 重复的提示
		 * 
		 * @type String
		 */
		warn_repeatEmail	: "该邮箱已经被人注册"
	},
	/**
	 * 用户名
	 * 
	 * @type object
	 */
	userName		: {
		/**
		 * dwr中配置的javascript 名称
		 * 
		 * @type String
		 */
		ajaxScriptName	: "UserAjax",
		/**
		 * 对应后台类的方法
		 * 
		 * @type String
		 */
		ajaxMethodName	: "isRepeatUserName_ajax"
	},
	/**
	 * 手机
	 * 
	 * @type object
	 */
	mobilePhone	: {
		/**
		 * 是否允许为空
		 * 
		 * @type Boolean
		 */
		isAllowEmpty	: false
	}
};
/**
 * 时间控件
 * 
 * @type
 */
var feilongDatePickerEntity = {
	minDate		: "%y-%M-%d",
	startDate	: "%y-%M-%d",
	maxDate		: "#{%y+20}-01-01",
	pattern		: "yyyy-MM-dd"/* 格式类型 */
};
