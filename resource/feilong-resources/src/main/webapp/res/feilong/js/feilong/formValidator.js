/**
 * formValidator验证
 * 
 * @param param_type
 *          类型
 * @return
 */
function formValidator(param_type) {
	$.formValidator.initConfig({
		formid		: "frm",// 要自动注册pageIsValid函数的表单ID号
		debug			: false,// 是否处于调试模式。true:不提交表单
		onerror		: function(msg) {
			alert(msg)
		},
		onsuccess	: function() {
			switch (param_type) {
				case "forgetPass" :// 找回密码
					return extendValidateForgetPass();
				case "bindCard" :// 绑定求职卡
					return extendValidateBindCard();
				case "registerPerson" :// 个人注册
					// 同意协议
					if ($("#checked_isAgreeAgreement").attr("checked")) {
						return true;
					}
					alert("您必须遵守会员权益与义务");
					return false;
			}
			return true;
		}
	});
	switch (param_type) {
		case "schoolPhoto" :// 学校图片
			validateSchoolPhoto();
			break;
		case "schoolHonor" :// 学校荣誉
			validateSchoolHonor();
			break;
		case "schoolFaculty" :// 师资力量
			validateSchoolFaculty();
			break;
		case "schoolLinkman" :// 学校联系人
			validateSchoolLinkman();
			break;
		case "schoolZhuanye" :// 设置专业
			validateSchoolZhuanye();
			break;
		case "jobFair" :// 招聘会
			validateJobFair();
			break;
		case "registerManager" :// 注册管理员信息
			validateUserName("manager");
			validateUserPassword();
			validateUserPassword2();
			break;
		case "managerInfo" :// 管理员信息
			validateManagerInfo();
			break;
		case "bindCard" :// 绑定求职卡
			validateBindCard();
			break;
		case "message" :// 添加留言
			validateMessage();
			break;
		case "updateEmail" :// 修改邮箱
			validateEmailNoRepeat();
			break;
		case "study" :// 学习经历
			validateStudy();
			break;
		case "train" :// 培训经历
			validateTrain();
			break;
		case "experience" :// 工作经历
			validateExperience();
			break;
		case "news" :// 新闻
			validateNews();
			break;
		case "agency" :// 代理商
			validateAgency();
			break;
		case "school" :// 校企通学校
			validateSchool();
			break;
		case "xiaoqitongSchool" :// 校企通学校(人才市场报)
			validateXiaoqitongSchool();
			break;
		case "laowuEnterprise" :// 劳务公司
			validateUserName("laowu");
			validateLaoWuEnterprise();
			break;
		case "enterprise" :// 企业
			validateEnterprise();
			break;
		case "job" :// 工作岗位
			validateJob();
			break;
		case "worker" :// 务工人员
			validateWorker();
			break;
		case "easyWorker" :// 简单注册
			validateEasyWorker();
			break;
		case "student" :// 学生
			validateStudent();
			break;
		case "forgetPass" :// 找回密码
			validateForgetPass();
			break;
		case "changePass" :// 修改密码
			validateChangePass("user");
			break;
		case "changeUserPersonPass" :// 修改精英人才个人密码
			validateChangePass("userPerson");
			break;
		case "changeManagerPass" :// 修改学校管理员密码
			validateChangePass("manager");
			break;
		case "employee" :// 内部员工
			validateEmployee();
			break;
		case "addPerson" :// 添加个人用户
			validateAddPerson();
			break;
		case "registerPerson" :// 个人用户注册
			 FeiLongFormValidator.validateRigister_email("#txt_userEmail", entity_FeiLongValidate);
			FeiLongFormValidator.validateUserName("#txt_userName", entity_FeiLongValidate);
			validateUserPassword();
			validateUserPassword2();
			FeiLongFormValidator.validateValidateCode("#txt_validateCode");
			break;
		default :
			break;
	}
}
/**
 * 添加个人用户
 */
function validateAddPerson() {
	// 真实姓名
	validateUserRealName("#txt_personName");
	// 身高
	validateHeight("#txt_height");
	// 体重
	validateWeight("#txt_weight");
	// 户籍
	$("#select_HujiProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择户籍"
	});
	// 居住地
	$("#select_livingProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择居住地"
	});
	/**
	 * 生日
	 */
	validateTime("#txt_birthday", "1950-01-01", "2000-01-01", "生日格式不正确");
	// 身份证
	validateIdCard("#txt_idCard");
	// 电话
	validateTelephone("#txt_phone");
	// 手机
	validateMobilePhone(false);
}
/**
 * 师资力量
 */
function validateSchoolFaculty() {
	validateNumber("#txt_nationalKeyDisciplineCount", 0, 1000, "国家重点学科数");
	validateNumber("#txt_masterProgrammeCount", 0, 5000, "硕士点数");
	validateNumber("#txt_doctoralProgramCount", 0, 5000, "博士点数");
	validateNumber("#txt_academicianCount", 0, 100000, "院士人数");
}
/**
 * 学校荣誉
 */
function validateSchoolHonor() {
	// 图片路径
	$("#txt_file").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择图片路径"
	});
	// 荣誉名称(说明)
	$("#txt_photoName").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "荣誉名称(说明)不能为空"
	});
	// 荣誉获得时间
	validateTime("#txt_gainTime", "1900", FeiLongTime.getYear(), "荣誉获得时间");
}
/**
 * 学校图片
 */
function validateSchoolPhoto() {
	// 图片路径
	$("#txt_file").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择图片路径"
	});
	// 图片说明
	$("#txt_photoName").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "图片名称(说明不能为空)"
	});
}
/**
 * 学校联系人
 */
function validateSchoolLinkman() {
	validateLinkMan();
	// 职务
	$("#txt_post").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "职务不能为空"
	});
	validatePhone();
	validateMobilePhone();
	validateEmail();
	validateMSN();
	validateQQ();
}
/**
 * 设置专业
 */
function validateSchoolZhuanye() {
	// 专业类型
	$("#select_zhuanYeAll").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择专业类型"
	});
	// 男生数量
	$("#txt_boyCount").formValidator({}).inputValidator({
		type		: "number",
		min			: 1,
		max			: 100000,
		onerror	: "男生数量格式不正确"
	});
	// 女生数量
	$("#txt_girlCount").formValidator({}).inputValidator({
		type		: "number",
		min			: 1,
		max			: 100000,
		onerror	: "女生数量格式不正确"
	});
	// 最小年龄
	$("#txt_ageSmall").formValidator({}).inputValidator({
		type		: "number",
		min			: 16,
		max			: 40,
		onerror	: "最小年龄格式不正确"
	});
	// 最大年龄
	$("#txt_ageBig").formValidator({}).inputValidator({
		type		: "number",
		min			: 16,
		max			: 40,
		onerror	: "最大年龄格式不正确"
	}).compareValidator({
		desid			: "txt_ageSmall",
		operateor	: ">=",
		datatype	: "number",
		onerror		: "最大年龄必须大于等于最小年龄"
	});
	// 实习时间
	// $("#txt_shixiTime").formValidator({}).inputValidator({
	// min : 1,
	// max : 100,
	// onerror : "实习时间1-100个字符!"
	// });
	// 最小薪资预期
	$("#txt_salarySmall").formValidator({}).inputValidator({
		type		: "number",
		min			: 0,
		max			: 3000000,
		onerror	: "最小薪资预期格式不正确"
	});
	// 最大薪资预期
	$("#txt_salaryBig").formValidator({}).inputValidator({
		type		: "number",
		min			: 0,
		max			: 3000000,
		onerror	: "最大薪资预期格式不正确"
	}).compareValidator({
		desid			: "txt_salarySmall",
		operateor	: ">=",
		datatype	: "number",
		onerror		: "最大薪资预期必须大于等于最小薪资预期"
	});
	// 最小输出时间
	$("#txt_outTimeMin").formValidator({}).inputValidator({
		type		: "date",
		min			: (FeiLongTime.getYear() - 4) + "-01-01",
		max			: (FeiLongTime.getYear() + 4) + "-01-01",
		onerror	: "最大输出时间格式不正确,必须介于" + (FeiLongTime.getYear() - 4) + "-01-01与" + (FeiLongTime.getYear() + 4) + "-01-01之间"
	});
	// 最大输出时间
	$("#txt_outTimeMax").formValidator({}).inputValidator({
		type		: "date",
		min			: (FeiLongTime.getYear() - 4) + "-01-01",
		max			: (FeiLongTime.getYear() + 4) + "-01-01",
		onerror	: "最大输出时间格式不正确,必须介于" + (FeiLongTime.getYear() - 4) + "-01-01与" + (FeiLongTime.getYear() + 4) + "-01-01之间"
	}).compareValidator({
		desid			: "txt_outTimeMin",
		operateor	: ">=",
		datatype	: "date",
		onerror		: "最大输出时间必须大于等于最小输出时间"
	});
}
/**
 * 验证招聘会
 */
function validateJobFair() {
	// 招聘会标题
	$("#txt_fairTitle").formValidator({}).inputValidator({
		min			: 5,
		max			: 100,
		onerror	: "招聘会标题5-100个字符!"
	});
	// 招聘会时间
	$("#txt_fairTime").formValidator({}).inputValidator({
		min			: 5,
		max			: 100,
		onerror	: "招聘会时间5-100个字符!"
	});
	// 举行地点
	$("#txt_fairAddress").formValidator({}).inputValidator({
		min			: 4,
		max			: 100,
		onerror	: "举行地点4-100个字符!"
	});
	// 招聘会内容
	$("#txt_fairContent").formValidator({}).inputValidator({
		min			: 4,
		max			: 2000,
		onerror	: "招聘会内容4-2000个字符!"
	});
	// 联系人
	$("#txt_linkMan").formValidator({}).inputValidator({
		min			: 4,
		max			: 50,
		onerror	: "联系人4-50个字符!"
	});
	// 联系方式
	$("#txt_linkType").formValidator({}).inputValidator({
		min			: 4,
		max			: 100,
		onerror	: "联系方式4-100个字符!"
	});
}
/**
 * 管理员信息
 */
function validateManagerInfo() {
	// 真实姓名
	$("#txt_managerRealname").formValidator({}).inputValidator({
		min			: 4,
		max			: 16,
		onerror	: "真实姓名4-16个字符!"
	});
	validatePhone();
	validateMobilePhone();
	validateEmail();
	validateQQ();
	validateMSN();
}
/**
 * 扩展验证绑定求职卡
 * 
 * @return {Boolean}
 */
function extendValidateBindCard() {
	var returnValue = "";
	// 设置成同步
	DWREngine.setAsync(false);
	CardAjax.ajaxValidateBingCard($("#txt_cardNo").val(), $("#txt_cardPass").val(), function(data) {
		returnValue = data;
	});
	// 重新设置为异步方式
	DWREngine.setAsync(true);
	if ("" == returnValue) {
		ajaxReturnValueDiv(returnValue, 1, false);
		return true;
	}
	ajaxReturnValueDiv(returnValue, 0);
	return false;
}
/**
 * 绑定求职卡
 */
function validateBindCard() {
	// 求职卡卡号
	$("#txt_cardNo").formValidator({}).inputValidator({
		min			: 4,
		max			: 20,
		onerror	: "求职卡卡号长度不正确,4-20个字符!"
	});
	// 求职卡密码
	$("#txt_cardPass").formValidator({}).inputValidator({
		min			: 4,
		max			: 8,
		onerror	: "求职卡密码长度不正确,4-8个字符!"
	});
}
/**
 * 验证留言
 */
function validateMessage() {
	// 留言标题
	$("#txt_title").formValidator({}).inputValidator({
		min			: 1,
		max			: 200,
		onerror	: "留言标题1-200个字符!"
	});
	// 内容
	$("#txt_content").formValidator({}).inputValidator({
		min			: 1,
		max			: 10000,
		onerror	: "留言内容1-10000个字符!"
	});
}
/**
 * 扩展验证找回密码
 * 
 * @return {Boolean}
 */
function extendValidateForgetPass() {
	var returnValue = "";
	// 设置成同步
	DWREngine.setAsync(false);
	UserAjax.ajaxValidateUserNameAndUserEmail($("#txt_userName").val(), $("#txt_userEmail").val(), function(data) {
		returnValue = data;
	});
	// 重新设置为异步方式
	DWREngine.setAsync(true);
	if ("" == returnValue) {
		ajaxReturnValueDiv(returnValue, 1, false);
		return true;
	}
	ajaxReturnValueDiv(returnValue, 0);
	return false;
}
/**
 * 找回密码
 */
function validateForgetPass() {
	// 用户名
	$("#txt_userName").formValidator({}).inputValidator({
		min			: 4,
		max			: 16,
		onerror	: "用户名4-16个字符!"
	});
	// 邮箱
	validateEmail();
}
/**
 * 学习经历
 */
function validateStudy() {
	// 毕业学校
	$("#txt_school").formValidator({}).inputValidator({
		min			: 4,
		max			: 200,
		onerror	: "毕业学校4-200个字符!"
	});
	// 开始时间(年)
	$("#select_beginYear").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择 开始时间(年)"
	});
	// 开始时间(月)
	$("#select_beginMonth").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择 开始时间(月)"
	});
	// 结束时间(年)
	$("#select_endYear").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择 结束时间(年)"
	});
	// 结束时间(月)
	$("#select_endMonth").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择 结束时间(月)"
	});
	// 专业名称
	$("#txt_speciality").formValidator({}).inputValidator({
		min			: 4,
		max			: 50,
		onerror	: "专业名称4-50个字符!"
	});
	/**
	 * 结束时间是否比开始时间晚
	 */
	// if (!FeiLongValidate.selectTimeThan("#select_beginYear", "#select_beginMonth", "#select_endYear", "#select_endMonth")) {
	// alert("结束时间不能早于开始时间");
	// $("#select_endYear").focus();
	// return false;
	// }
}
/**
 * 验证培训经历
 */
function validateTrain() {
	// 培训课程
	$("#txt_course").formValidator({}).inputValidator({
		min			: 2,
		max			: 100,
		onerror	: "培训课程 2-100个字符!"
	});
	// 培训时间(年)
	$("#select_trainYear").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择培训时间(年)"
	});
	// 培训时间(月)
	$("#select_trainMonth").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择培训时间(月)"
	});
}
/**
 * 验证工作经历
 */
function validateExperience() {
	// 公司名称
	$("#txt_companyName").formValidator({}).inputValidator({
		min			: 4,
		max			: 200,
		onerror	: "公司名称4-200个字符!"
	});
	// 职位名称
	$("#txt_post").formValidator({}).inputValidator({
		min			: 1,
		max			: 50,
		onerror	: "职位名称1-50个字符!"
	});
	// 工作开始时间(年)
	$("#select_beginYear").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择工作开始时间(年)"
	});
	// 工作开始时间(月)
	$("#select_beginMonth").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择工作开始时间(月)"
	});
	// 工作结束时间(年)
	$("#select_endYear").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择工作结束时间(年)"
	});
	// 工作结束时间(月)
	$("#select_endMonth").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择工作结束时间(月)"
	});
	/**
	 * 结束时间是否比开始时间晚
	 */
	// if (!FeiLongValidate.selectTimeThan("#select_beginYear", "#select_beginMonth", "#select_endYear", "#select_endMonth")) {
	// alert("工作结束时间不能早于开始时间");
	// $("#select_endYear").focus();
	// return false;
	// }
}
/**
 * 验证新闻
 */
function validateNews() {
	// 新闻标题
	$("#txt_title").formValidator({}).inputValidator({
		min			: 1,
		max			: 100,
		onerror	: "新闻标题1-100个字符!"
	});
	// 新闻来源
	$("#txt_from").formValidator({}).inputValidator({
		min			: 1,
		max			: 200,
		onerror	: "新闻来源1-200个字符!"
	});
	// 新闻内容
	$("#newsContent").formValidator({}).functionValidator({
		fun	: function(val, elem) {
			return FeiLongFCKEditor.validateFCKEditorContent("newsContent", 1000);
		}
	});
}
/**
 * 验证代理商
 */
function validateAgency() {
	// 公司名称
	$("#txt_companyName").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "公司名称不能为空!"
	});
	// 联系人
	$("#txt_linkman").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "联系人不能为空!"
	});
}
/**
 * 验证校企通学校
 */
function validateXiaoqitongSchool() {
	// 学校名称
	$("#txt_schoolName").formValidator({}).inputValidator({
		min			: 4,
		max			: 50,
		onerror	: "学校名称长度不正确,4-50个字符(2-25个汉字)"
	}).regexValidator({
		regexp		: "chinese2_25",
		datatype	: "enum",
		onerror		: "学校名称格式不正确,4-50个字符(2-25个汉字)"
	});
	// 所在地区省份
	$("#select_regionByProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区省份必须选择"
	});
	// 所在地区市区
	$("#select_regionByCity").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区市区必须选择"
	});
	// 所在地区县
	$("#select_regionByDistrict").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区县必须选择"
	});
	// 学校地址
	$("#txt_schoolAddress").formValidator({
		onempty	: "学校地址不能为空"
	}).inputValidator({
		min			: 10,
		max			: 200,
		onerror	: "学校地址长度10-200个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "学校地址两端不能出现空字符"
		}
	});
	// 学校介绍
	$("#txt_schoolInfo").formValidator({
		onempty	: "学校介绍不能为空"
	}).inputValidator({
		min			: 5,
		max			: 1000,
		onerror	: "学校介绍长度5-1000个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "学校介绍两端不能出现空字符"
		}
	});
	validatePhone();
	validateFax();
	validateZip();
	validateEmail();
	// 在校人数
	$("#txt_studentInCount").formValidator({}).inputValidator({
		type		: "number",
		min			: 1,
		max			: 1000000,
		onerror	: "在校人数格式不正确"
	});
	// 应届人数
	$("#txt_studentGraduateCount").formValidator({}).inputValidator({
		type		: "number",
		min			: 1,
		max			: 1000000,
		onerror	: "应届人数格式不正确"
	});
	// 已输出人数
	$("#txt_studentOutCount").formValidator({}).inputValidator({
		type		: "number",
		min			: 1,
		max			: 1000000,
		onerror	: "已输出人数格式不正确"
	});
}
/**
 * 验证学校
 */
function validateSchool() {
	// 学校名称
	$("#txt_schoolName").formValidator({}).inputValidator({
		min			: 4,
		max			: 50,
		onerror	: "学校名称长度不正确,4-50个字符(2-25个汉字)"
	}).regexValidator({
		regexp		: "chinese2_25",
		datatype	: "enum",
		onerror		: "学校名称格式不正确,4-50个字符(2-25个汉字)"
	});
	// 校园帐号
	$("#txt_schoolTag").formValidator({}).inputValidator({
		min			: 5,
		max			: 16,
		onerror	: "校园帐号格式不正确<br>,必须是5-16个英文,数字字符(可以含有点.,横-,下划线_)两个特殊字符,<br>且必须是数字字母开头.数字字母结尾"
	}).regexValidator({
		regexp		: "registerName5_16",
		datatype	: "enum",
		onerror		: "校园帐号格式不正确<br>,必须是5-16个英文,数字字符(可以含有点.,横-,下划线_)两个特殊字符,<br>且必须是数字字母开头.数字字母结尾"
	});
	// 所在地区省份
	$("#select_regionByProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区省份必须选择"
	});
	// 所在地区市区
	$("#select_regionByCity").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区市区必须选择"
	});
	// 所在地区县
	$("#select_regionByDistrict").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "所在地区县必须选择"
	});
	// 联系人
	$("#txt_linkMan").formValidator({
		onempty	: "联系人不能为空"
	}).inputValidator({
		min			: 1,
		max			: 15,
		onerror	: "联系人长度1-15个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "联系人两端不能出现空字符"
		}
	});
	// 联系方式
	$("#txt_linkType").formValidator({
		onempty	: "联系方式不能为空"
	}).inputValidator({
		min			: 1,
		max			: 200,
		onerror	: "联系方式长度1-200个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "联系方式两端不能出现空字符"
		}
	});
	// 学校地址
	$("#txt_schoolAddress").formValidator({
		onempty	: "学校地址不能为空"
	}).inputValidator({
		min			: 10,
		max			: 200,
		onerror	: "学校地址长度10-200个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "学校地址两端不能出现空字符"
		}
	});
	// 学校介绍
	$("#txt_schoolInfo").formValidator({
		onempty	: "学校介绍不能为空"
	}).inputValidator({
		min			: 5,
		max			: 1000,
		onerror	: "学校介绍长度5-1000个字符",
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "学校介绍两端不能出现空字符"
		}
	});
}
/**
 * 简单注册
 */
function validateEasyWorker() {
	// 姓名
	$("#workerName").formValidator({
		onfocus	: "姓名2-5个中文"// 获得焦点
	}).inputValidator({
		min			: 2,
		max			: 10,
		onerror	: "姓名必须2-5个中文"// 错误
	}).regexValidator({
		regexp		: "chinese",
		datatype	: "enum",
		onerror		: "姓名必须2-5个中文"
	});
	// 手机
	validateMobilePhone();
}
/**
 * 验证务工人员
 */
function validateWorker() {
	// 公用的简单注册
	validateEasyWorker();
	// 薪水
	$("#salary").formValidator({}).regexValidator({
		regexp	: "^[1-9]\\d*|0$",
		onerror	: "最低薪水待遇数格式不正确"
	});
	// 出生日期
	$("#birthday").formValidator({}).inputValidator({
		min			: "1900-01-01",
		max			: "2000-01-01",
		type		: "date",
		onerror	: "日期在\"1900-01-01\"和\"2000-01-01\"之间"
	});
	// 居住地省份
	$("#areaLiving").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "居住地省份必须选择"
	});
	// 户籍省份
	$("#areaHuji").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "户籍省份必须选择"
	});
	// 求职意向
	$("#intention").formValidator({
		onfocus	: "求职意向2-20个字符"
	}).inputValidator({
		min			: 2,
		max			: 20,
		onerror	: "求职意向2-20个字符"
	});
	// 理想工作地点
	$("#areaWork_Province").formValidator({
		onfocus	: "理想工作地点必须选择"
	}).inputValidator({
		min			: 1,
		onerror	: "必须选择理想工作地点"
	});
	// register easyRegister adult webRegister
	// 身高
	$("#height").formValidator({
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
	// 体重
	$("#weight").formValidator({
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
	// 电话
	$("#phone").formValidator({
		onfocus	: "区号加电话号码,例:021-88888888或省略区号88888888",
		empty		: true
	}).regexValidator({
		regexp		: "tel",
		datatype	: "enum",
		onerror		: "联系电话格式不正确"
	});
	// 身份证
	$("#idCard").formValidator({
		onfocus	: "请输入15或18位的身份证",
		empty		: true
	}).functionValidator({
		fun	: isCardID
	});
	// 网上注册
	if (type == "webRegister") {
		$("#selfEvaluation").formValidator({
			onshow				: " ",
			onfocus				: " ",
			defaultvalue	: "",
			oncorrect			: "&nbsp;"
		}).inputValidator({
			min			: 2,
			max			: 1000,
			onerror	: "自我评价长度不正确"
		});
	}
}
/**
 * 验证学生
 */
function validateStudent() {
	// 验证码
	$("#txt_code").formValidator({
		defaultvalue	: ""
	}).inputValidator({
		min			: 4,
		max			: 4,
		onerror	: "验证码长度错误"
	});
	// 真实姓名
	$("#txt_studentRealname").formValidator({
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
	// 身高
	$("#txt_height").formValidator({
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
	// 体重
	$("#txt_weight").formValidator({
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
	// 户籍
	$("#select_HujiProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择户籍"
	});
	$("#txt_birthday").formValidator({}).inputValidator({
		min			: "1950-01-01",
		max			: "2000-01-01",
		type		: "date",
		onerror	: "日期在\"1950-01-01\"和\"2000-01-01\"之间"
	});
	// 身份证
	$("#txt_idCard").formValidator({
		onfocus	: "请输入15或18位的身份证",
		empty		: true
	}).functionValidator({
		fun	: isCardID
	});
	// 院系
	$("#select_college").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择所在院系"
	});
	// 班级
	$("#select_class").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择所在班级"
	});
	$("#txt_intention").formValidator({
		onfocus	: "求职意向2-20个字符"
	}).inputValidator({
		min			: 2,
		max			: 20,
		onerror	: "求职意向2-20个字符"
	});
	$("#select_WorkProvince").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "请选择理想工作地点"
	});
	$("#txt_salary").formValidator({}).regexValidator({
		regexp	: "^[1-9]\\d*|0$",
		onerror	: "最低薪水待遇数格式不正确"
	});
	// 联系电话
	$("#txt_phone").formValidator({
		onfocus	: "区号加电话号码,例:021-33199360",
		empty		: true
	}).regexValidator({
		regexp		: "tel",
		datatype	: "enum",
		onerror		: "联系电话格式不正确"
	});
	// 手机
	validateMobilePhone();
	// 邮箱
	validateEmail();
	// QQ
	validateQQ();
	// MSN
	validateMSN();
}
/**
 * 注册劳务公司
 */
function validateLaoWuEnterprise() {
	// 企业名称
	$("#enterpriseName").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "企业名称不能为空!"
	});
	// 企业简称
	// $("#shortName").formValidator({}).inputValidator({
	// min : 1,
	// onerror : "企业简称必须输入"
	// });
	// 工作地区
	$("#area").formValidator({
		onfocus	: "工作地区(精确到县区)必须选择"
	}).inputValidator({
		min			: 1,
		onerror	: "工作地区(精确到县区)必须选择"
	});
	// 注册资金
	$("#registerMoney").formValidator({}).regexValidator({
		regexp	: "^[1-9][0-9]*$",
		onerror	: "注册资金格式不正确"
	});
	// 员工人数
	$("#employeeCountId").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "员工人数必须选择"
	});
	// 企业性质
	$("#companyKindId").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "企业性质必须选择"
	});
	// 企业成立日期
	$("#registerDate").formValidator({}).inputValidator({
		min			: "1900-01-01",
		max			: FeiLongTime.getDate("yyyy-MM-dd"),
		type		: "date",
		onerror	: "日期必须在\"1900-01-01\"和\"" + FeiLongTime.getDate("yyyy-MM-dd") + "\"之间"
	});
	// 联系电话
	validatePhone();
	// 传真号码
	validateFax();
	// 联系人
	validateLinkMan();
	// 邮编
	validateZip();
	// 具体地址
	validateAddress();
}
/**
 * 验证企业
 */
function validateEnterprise() {
	validateLaoWuEnterprise();
}
/**
 * 验证岗位
 */
function validateJob() {
	// 行业类别
	$("#industryId").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "行业类别必须选择"
	}).defaultPassed();
	// 工种类别
	$("#jobKindId").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "工种类别必须选择"
	}).defaultPassed();
	// 职位名称
	$("#jobName").formValidator({}).inputValidator({
		min			: 1,
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "职位名称两边不能有空符号"
		},
		onerror	: "职位名称不能为空"
	});
	// 工作地区
	$("#province").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "你是不是忘记选择工作地区了!"
	}).defaultPassed();
	// 招聘人数
	$("#renShu").formValidator({}).regexValidator({
		regexp	: "^[1-9][0-9]*$",
		onerror	: "招聘人数格式不正确"
	});
	// 招聘启始日期
	$("#beginDate").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "日期格式不正确!"
	});
	// 招聘截止日期
	$("#stopDate").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "日期格式不正确!"
	});
	// 基本工资
	$("#baseSalary").formValidator({}).regexValidator({
		regexp	: "^[1-9][0-9]*$",
		onerror	: "基本工资格式不正确"
	});
	// 综合工资
	$("#totalSalary").formValidator({}).regexValidator({
		regexp	: "^[1-9][0-9]*$",
		onerror	: "&nbsp;"
	});
	// 邮箱
	validateEmail();
	// 联系人
	validateLinkMan();
	// 联系电话
	validatePhone();
	// 邮编
	validateZip();
	// 验证地址
	validateAddress();
}
/*
 * 验证内部员工
 */
function validateEmployee() {
	// 用户名
	$("#userName").formValidator({}).inputValidator({
		empty				: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "用户名两边不能有空符号"
		},
		min					: 4,
		max					: 50,
		onerrormin	: "用户名长度错误,只能是4-50个字符",
		onerrormax	: "用户名长度错误,只能是4-50个字符"
	}).functionValidator({
		fun	: function(val, elem) {
			var returnValue;
			// 设置成同步
			DWREngine.setAsync(false);
			UserAjax.isRepeatUserName(val, function(data) {
				returnValue = data;
			});
			// 重新设置为异步方式
			DWREngine.setAsync(true);
			if (returnValue) {
				return "当前用户名已被注册,请重新输入";
			} else {
				return true;
			}
		}
	});
	// 密码
	$("#userPassword").formValidator({}).inputValidator({
		empty				: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "密码两边不能有空符号"
		},
		min					: 6,
		max					: 16,
		onerrormin	: "密码长度错误,必须是6-16个字符",
		onerrormax	: "密码长度错误,必须是6-16个字符"
	}).regexValidator({
		regexp		: "pass",
		datatype	: "enum",
		// param 附加参数
		// datatype 数据类型
		onerror		: "密码格式不正确,6-16位字符,特殊字符只能包含!@#$%"
	});
	// 确认密码
	$("#passwordOK").formValidator({}).compareValidator({
		desid			: "userPassword",// 要比较控件的ID
		operateor	: "=",// 比较符号 一共有如下几种类型:=、!=、>、>=、<、<=
		// datatype 目前只支持2种:"string"、"number","datetime","date"
		onerror		: "2次密码输入不一致,请核实后重新输入"
	});
	$("#userEmail").formValidator({
		// onshow:"请输入用户邮箱",
		onfocus		: "邮箱必须是标准格式:**@**.com",
		oncorrect	: "&nbsp;"
	}).inputValidator({
		min			: 6,
		max			: 50,
		onerror	: "你输入的邮箱格式不正确"
	}).regexValidator({
		regexp	: "^([\\w-.]+)@(([[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.)|(([\\w-]+.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(]?)$",
		onerror	: "你输入的邮箱格式不正确"
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
			if (returnValue) {
				return "当前用户邮箱已被注册,请重新输入";
			} else {
				return true;
			}
		}
		// error: function(){alert("服务器没有返回数据，可能服务器忙，请重试");},
	// onerror : "该邮箱已注册",
	// onwait : "正在对邮箱进行合法性校验，请稍候..."
}	);
	$("#employeeCode").formValidator({
		// onshow :"请输入员工工号",
		onfocus		: "工号必须是4位数字字符",
		oncorrect	: "&nbsp;"
	}).inputValidator({
		min			: 4,
		max			: 4,
		onerror	: "你输入的工号格式错误"
	}).regexValidator({
		regexp	: "^[1-9][0-9]*$",
		onerror	: "员工工号格式不正确"
	}).functionValidator({
		fun	: function(val, elem) {
			var returnValue;
			// 设置成同步
			DWREngine.setAsync(false);
			UserAjax.isRepeatEmployeeCode(val, function(data) {
				returnValue = data;
			});
			// 重新设置为异步方式
			DWREngine.setAsync(true);
			if (returnValue) {
				return "当前工号已存在,请重新输入";
			} else {
				return true;
			}
		}
	});
	// 工种类别
	$("#departId").formValidator({}).inputValidator({
		min			: 1,
		onerror	: "公司部门必须选择"
	});
	// 姓名
	$("#employeeName").formValidator({}).inputValidator({
		min			: 1,
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "姓名两边不能有空符号"
		},
		onerror	: "姓名不能为空"
	});
	$("#birthday").focus(function() {
		WdatePicker({
			skin			: 'whyGreen',
			oncleared	: function() {
				$(this).blur();
			},
			onpicked	: function() {
				$(this).blur();
			}
		})
	}).formValidator({
		onshow		: "",
		empty			: false,
		onfocus		: "请输入日期格式,不能为空或为0",
		oncorrect	: "&nbsp;"
	}).inputValidator({
		min			: "1900-01-01",
		max			: "2010-01-01",
		type		: "value",
		onerror	: "日期必须在\"1900-01-01\"和\"2010-01-01\"之间"
	});
	// 身份证号
	$("#idcard").formValidator({}).inputValidator({
		min			: 1,
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "身份证号两边不能有空符号"
		},
		onerror	: "身份证号不能为空"
	});
	// 联系电话
	$("#telephone").formValidator({}).inputValidator({
		min			: 1,
		empty		: {
			leftempty		: false,
			rightempty	: false,
			emptyerror	: "联系电话两边不能有空符号"
		},
		onerror	: "联系电话不能为空"
	});
}