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
package com.feilong.struts1.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.feilong.servlet.http.RequestUtil;

/**
 * 通用action,处理些常用跳转,不负责与后台交互,.
 * 
 * @struts.action parameter="opt"
 */
public class CommonAction extends DispatchAction{

	/**
	 * 保存当前路径跳转到某个页面.
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-2-2 下午01:27:26
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward doSaveUrlSendRedirect(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		String redirectHref = request.getParameter("redirectHref");
		// 返回路径
		session.setAttribute("backURL", RequestUtil.getHeaderReferer(request));
//		try{
//			//response.sendRedirect(FeiLongSecurity.getDecryptParam(redirectHref));
//		}catch (IOException e){
//			log.error(e.getClass().getName(), e);
//		}
		return null;
	}
	// public ActionForward test(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	// try{
	// response.sendRedirect("http://127.0.0.1:8080/zhaogong/ccmgm.jsp?t=2010031801&jobId=7241%23workerGroup");
	// }catch (IOException e){
	// log.error(e.getClass().getName(), e);
	// }
	// return null;
	// }
}