package com.feilong.netpay;

import java.util.ResourceBundle;

public class AliPayConfig{
	
	public static ResourceBundle interfaceConfig		= ResourceBundle.getBundle("config/interface-config");
	
	// ↓↓↓↓↓↓↓↓↓↓请在这里配置基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串 2088101568355903
	public static String		partner					= interfaceConfig.getString("alipay.partner");

	// 交易安全检验码，由数字和字母组成的32位字符串 ldpdr169a3e5n86i9f92sn3cznbyx72s
	public static String		key						= interfaceConfig.getString("alipay.key");

	/**
	 * HTTP形式消息验证地址
	 */
	public static final String	HTTP_VERIFY_URL			= interfaceConfig.getString("alipay.httpVerifyUrl");  

	// 当前页面跳转后的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数
	// 域名不能写成http://localhost/alipay.auth.authorize_jsp_utf8/return_url.jsp ，否则会导致return_url执行无效
	// 124.74.76.70:8002
	// http://www.nikestore.com.cn/member/register.htm
	// http://www.nikestore.com.cn/enterpayment.htm
	public static String		return_url				= interfaceConfig.getString("alipay.returnUrl");  

	public static String		redirectUrl				= interfaceConfig.getString("alipay.redirectUrl");

	public static String		target_url				= interfaceConfig.getString("alipay.targetUrl"); 

	public static String		promote_url				= interfaceConfig.getString("alipay.promoteUrl");

	public static String		query_return_url		= interfaceConfig.getString("alipay.queryReturnUrl"); 

	public static String		service					= interfaceConfig.getString("alipay.service"); 

	public static String		regsource				= interfaceConfig.getString("alipay.regsource");

	// 查询服务
	public static String		query_service			= interfaceConfig.getString("alipay.queryService");

	public static String		target_service			= interfaceConfig.getString("alipay.targetService");

	public static String		alipay_path				= interfaceConfig.getString("alipay.alipayPath"); 

	public static String		alipayName				= interfaceConfig.getString("alipay.alipayName");

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	// 调试用，创建TXT日志路径
	public static String		log_path				= interfaceConfig.getString("alipay.logPath");

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String		input_charset			= interfaceConfig.getString("alipay.inputCharset");

	// 签名方式 不需修改
	public static String		sign_type				= interfaceConfig.getString("alipay.signType"); 

	// 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	public static String		transport				= interfaceConfig.getString("alipay.transport"); 

	// alipay用户等级
	public static String		vip_userGrade			= interfaceConfig.getString("alipay.vipUserGrade"); 

	// alipay用户等级状态
	public static String		user_grade_type			= interfaceConfig.getString("alipay.user_gradeType"); 

	/**
	 * 支付宝快捷登录,自动设值用户 默认的密码,<br>
	 * create by jinxin (2011-8-12 下午01:31:30)
	 */
	public static final String	alipayLoginAutoPassword	= interfaceConfig.getString("alipay.alipayLoginAutoPassword");
}
