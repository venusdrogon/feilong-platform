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
package com.feilong.taglib.display.pager;

/**
 * 分页常量<br>
 * This class defines the common PagerConstants ,so that they can be referenced as a constant within Java code. <br>
 * 参考了 velocity RuntimeConstants
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-16 上午12:03:45
 */
public interface PagerConstants{

	/** The Constant maxPageItems. */
	int		maxPageItems							= 10;

	/** <code>{@value}</code>. */
	String	default_skin							= "digg";

	/** 默认分页参数名称. */
	String	default_pageParamName					= "pageNo";

	/** 默认分页使用的vm 脚本. */
	String	default_templateInClassPath				= "velocity/feilong-default-pager.vm";

	// **************************************************************************************
	/** 默认将解析出来的htm 的存放在 request 作用域里面的变量. */
	String	default_request_name					= "feilongPagerHtml";

	// ******************************************************************************

	/** url 中特殊变量,如果带有这个变量 将不解析vm,调试使用问题所在 . */
	String	default_param_debugIsNotParseVM			= "debugIsNotParseVM";

	/** url 中特殊变量,如果带有这个变量 将不解析vm,调试使用问题所在 . */
	String	default_param_debugIsNotParseVM_value	= "true";
}
