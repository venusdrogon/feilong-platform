/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.tools.middleware.email;

import com.feilong.commons.core.util.StringUtil;

/**
 * 飞龙邮件工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午11:59:09
 * @since 1.0
 */
public final class EmailUtil{

	/**
	 * 获得邮件的中文名称
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
	 * 通过邮件获取其相关信息
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
	 * 获得邮件的后缀名
	 * 
	 * @param email
	 *            邮件
	 * @return
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