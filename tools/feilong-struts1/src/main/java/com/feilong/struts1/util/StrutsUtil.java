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
package com.feilong.struts1.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForward;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.servlet.http.ParamUtil;
import com.feilong.servlet.http.RequestUtil;

/**
 * http struts 相关
 * 
 * @author 金鑫 2010-5-24 上午06:04:45
 */
public class StrutsUtil{

	// **********************************************************************
	/**
	 * 创建 ActionForward
	 * 
	 * @param request
	 *            当前请求
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @return 创建 ActionForward
	 * @author 金鑫
	 * @version 1.0 2010-1-5 下午05:16:54
	 */
	public static ActionForward createActionForward(HttpServletRequest request,boolean flag,String method){
		return createActionForward(request, flag, method, false);
	}

	/**
	 * 创建 ActionForward
	 * 
	 * @param request
	 *            当前请求
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @param isAddSessionWarn
	 *            是否添加session提示
	 * @return 创建 ActionForward
	 * @author 金鑫
	 * @version 1.0 2010-1-5 下午05:16:54
	 */
	public static ActionForward createActionForward(HttpServletRequest request,boolean flag,String method,boolean isAddSessionWarn){
		// StringBuffer 字符串变量（线程安全）
		// StringBuilder 字符串变量（非线程安全）
		// String 字符串常量
		// 在大部分情况下 StringBuffer > String
		// 在大部分情况下 StringBuilder > StringBuffer
		String referer = RequestUtil.getHeaderReferer(request);
		String path = getActionForwardHref(referer, flag, method, isAddSessionWarn);
		return new ActionForward(path, true);
	}

	/**
	 * 创建 ActionForward
	 * 
	 * @param request
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @param warn
	 *            自定义提示 自动封装session warn属性
	 * @return 创建 ActionForward
	 * @author 金鑫
	 * @version 1.0 2010年5月24日 14:01:06
	 */
	public static ActionForward createActionForward(HttpServletRequest request,boolean flag,String method,Object warn){
		request.getSession().setAttribute("warn", warn);
		return createActionForward(request, flag, method, true);
	}

	// **********************************************************************
	/**
	 * 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * 
	 * <pre>
	 * 底层调用new ActionForward(),路径前面要&quot;/&quot; 开头
	 * </pre>
	 * 
	 * @param href
	 *            href路径
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @return 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * @author 金鑫
	 * @version 1.0 2010-4-4 下午09:10:09
	 */
	public static ActionForward createActionForward(String href,boolean flag,String method){
		return createActionForward(href, flag, method, false);
	}

	/**
	 * 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * 
	 * <pre>
	 * 底层调用new ActionForward(),路径前面要&quot;/&quot; 开头
	 * </pre>
	 * 
	 * @param href
	 *            href路径
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @param isAddSessionWarn
	 *            是否添加session提示
	 * @return 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * @author 金鑫
	 * @version 1.0 2010-4-4 下午09:11:02
	 */
	public static ActionForward createActionForward(String href,boolean flag,String method,boolean isAddSessionWarn){
		String path = getActionForwardHref(href, flag, method, isAddSessionWarn);
		return new ActionForward(path, true);
	}

	/**
	 * 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * 
	 * <pre>
	 * 底层调用new ActionForward(),路径前面要&quot;/&quot; 开头
	 * </pre>
	 * 
	 * @param href
	 *            href路径
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @param warn
	 *            提示
	 * @param request
	 *            request用来自动设置session参数的
	 * @return 创建 ActionForward 指定跳转路径 后面拼接特殊参数
	 * @author 金鑫
	 * @version 1.0 2010-9-2 上午11:02:16
	 */
	public static ActionForward createActionForward(String href,boolean flag,String method,Object warn,HttpServletRequest request){
		request.getSession().setAttribute("warn", warn);
		return createActionForward(href, flag, method, true);
	}

	// **********************************************************************
	/**
	 * 创建通用的ActionForwardHref
	 * 
	 * @param href
	 *            href路径
	 * @param flag
	 *            是否操作成功 成功true
	 * @param method
	 *            方式
	 * @param isAddSessionWarn
	 *            是否添加session提示
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-4-4 下午09:12:29
	 */
	private static String getActionForwardHref(String href,boolean flag,String method,boolean isAddSessionWarn){
		href = ParamUtil.addParameter(href, "i", flag ? "1" : "0", CharsetType.UTF8);
		href = ParamUtil.addParameter(href, "method", method, CharsetType.UTF8);
		// 添加session提示
		href = ParamUtil.addParameter(href, "isAddSessionWarn", isAddSessionWarn ? "1" : "0", CharsetType.UTF8);
		return href;
	}
}
