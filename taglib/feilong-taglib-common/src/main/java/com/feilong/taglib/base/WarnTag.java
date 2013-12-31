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
package com.feilong.taglib.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.feilong.commons.core.util.Validator;

/**
 * 提示标签 FeiLongWarnTag
 * 
 * @author 金鑫 2010-3-5 上午11:43:42
 */
@Deprecated
public abstract class WarnTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 是否添加默认样式,true添加 false不添加 不填默认添加true
	 */
	private String				isAddDefaultClass;

	@Override
	public String writeContent(){
		StringBuilder stringBuilder = new StringBuilder("");
		HttpServletRequest request = getHttpServletRequest();
		String method = request.getParameter("method");
		// 不填默认添加
		if (Validator.isNullOrEmpty(isAddDefaultClass)){
			isAddDefaultClass = "true";
		}
		if (Validator.isNotNullOrEmpty(method)){
			String i = request.getParameter("i");
			if ("true".equals(isAddDefaultClass)){
				// 层样式
				String className = "1".equals(i) ? "successWarn" : "failWarn";
				stringBuilder.append("<div class='" + className + "'>");
			}else{
				stringBuilder.append("<div>");
			}
			String t = request.getParameter("t");
			// 含有t
			if (Validator.isNotNullOrEmpty(t)){
				// 拼接说明
				showString(t, method, stringBuilder);
				stringBuilder.append("1".equals(i) ? "成功!" : "失败!");
			}
			// 是否添加session提示
			String isAddSessionWarn = request.getParameter("isAddSessionWarn");
			// 带有isAddSessionWarn参数
			if (Validator.isNotNullOrEmpty(isAddSessionWarn)){
				HttpSession session = getHttpSession();
				// 1表示添加
				if ("1".equals(isAddSessionWarn)){
					stringBuilder.append(session.getAttribute("warn"));
				}
			}
			stringBuilder.append("</div>");
		}
		return stringBuilder.toString();
	}

	/**
	 * 通用的method
	 * 
	 * @param method
	 *            方法
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-3-16 下午04:00:12
	 */
	protected String commonMethod(String method){
		String returnValue = "";
		if ("add".equals(method)){
			returnValue = "添加";
		}else if ("delete".equals(method)){
			returnValue = "删除";
		}else if ("update".equals(method)){
			returnValue = "修改";
		}
		return returnValue;
	}

	/**
	 * 拼接说明文字
	 * 
	 * @param t
	 *            参数页面t
	 * @param method
	 *            方式
	 * @param stringBuilder
	 *            stringBuilder
	 * @author 金鑫
	 * @version 1.0 2010-3-18 上午11:02:25
	 */
	protected abstract void showString(String t,String method,StringBuilder stringBuilder);

	/**
	 * @param isAddDefaultClass
	 *            the isAddDefaultClass to set
	 */
	public void setIsAddDefaultClass(String isAddDefaultClass){
		this.isAddDefaultClass = isAddDefaultClass;
	}
}
