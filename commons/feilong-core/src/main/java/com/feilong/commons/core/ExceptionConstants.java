/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core;

import static com.feilong.commons.core.configure.ResourceBundleUtil.getValue;

/**
 * 配置文件.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午05:58:59
 * @since 1.0
 */
public class ExceptionConstants{

	public final static String	$feilong_core_exception			= "messages/feilong-core-exception";

	// ******************************************************************************************************
	/** 未知类型邮箱. */
	public final static String	exception_unknown_type_email	= getValue($feilong_core_exception, "exception_unknown_type_email");

	/** fileName 文件名不能为null. */
	public final static String	exception_fileName_null			= getValue($feilong_core_exception, "exception_fileName_null");
}
