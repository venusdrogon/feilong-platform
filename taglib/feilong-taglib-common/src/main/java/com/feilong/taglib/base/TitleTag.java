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

/**
 * t不同标题不同
 * 
 * @author 金鑫 2010-3-23 上午10:16:28
 */
@Deprecated
public abstract class TitleTag extends AbstractCommonTag{

	private static final long	serialVersionUID	= 1L;

	@Override
	public String writeContent(){
		StringBuilder stringBuilder = new StringBuilder("");
		HttpServletRequest request = getHttpServletRequest();
		String t = request.getParameter("t");
		// 拼接说明
		showString(t, stringBuilder);
		return stringBuilder.toString();
	}

	/**
	 * 拼接说明文字
	 * 
	 * @param t
	 *            参数页面t
	 * @param stringBuilder
	 * @author 金鑫
	 * @version 1.0 2010年3月23日 10:19:49
	 */
	protected abstract void showString(String t,StringBuilder stringBuilder);
}
