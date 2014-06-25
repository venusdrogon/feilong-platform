/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.tools.middleware.email;

import com.feilong.commons.core.util.StringUtil;

/**
 * 飞龙邮件工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午11:59:09
 * @since 1.0
 */
public final class EmailUtil{

	/**
	 * 获得邮件的中文名称.
	 * 
	 * @param email
	 *            邮件
	 * @return 中文名称
	 */
	public static String getEmailChineseName(String email){
		EmailType emailType = getEmailType(email);
		if (null != emailType){
			return emailType.getChineseName();
		}
		//TODO 此处应该在前端做国际化
		return "unkown type email";
	}

	/**
	 * 通过邮件获取其相关信息.
	 * 
	 * @param email
	 *            邮件
	 * @return MailBoxEntity
	 */
	public static EmailType getEmailType(String email){
		String postfix = getEmailPostfix(email);
		return EmailType.getEmailTypeByPostfix(postfix);
	}

	/**
	 * 获得邮件的后缀名.
	 * 
	 * @param email
	 *            邮件
	 * @return the email postfix
	 */
	public static String getEmailPostfix(String email){
		return StringUtil.substring(email, "@", 1);
	}

	// /**
	// * 获得错误邮件标题
	// *
	// * @param servletContext
	// * @param customerTitle
	// * @param request
	// * @return 获得错误邮件标题
	// */
	// public static String getErrorEmailTitle(ServletContext servletContext,String customerTitle,HttpServletRequest request){
	// String emailTitle = "错误页面";
	// if (Validator.isNotNull(customerTitle)){
	// emailTitle = customerTitle;
	// }
	// if (FeiLongHTTP.isLocalHost(request)){
	// emailTitle = "[本地测试]" + emailTitle;
	// }else{
	// emailTitle = "[" + FeiLongPropertiesConfigure.getPropertiesFeiLongValueWithServletContext(servletContext, "projectChineseName") + "]"
	// + emailTitle;
	// }
	// return emailTitle;
	// }

	// /**
	// * 通过邮件获得邮箱的登录路径
	// *
	// * @param email
	// * 邮件
	// * @return 获得邮箱的登录路径
	// */
	// public static String getEmailLoginHrefByEmail(String email){
	// EmailType emailType = getEmailType(email);
	// // if (null != emailType){
	// // HtmlAEntity htmla = new HtmlAEntity();
	// // htmla.setHref(emailType.getWebsite());
	// // htmla.setText(emailType.getWebsite());
	// // htmla.setTitle("登陆" + emailType.getChineseName());
	// // htmla.setTarget(HtmlAEntity.target_blank);
	// // return HTMLA.createA(htmla);
	// // }
	// return ExceptionConstants.exception_unknown_type_email;
	// }

}