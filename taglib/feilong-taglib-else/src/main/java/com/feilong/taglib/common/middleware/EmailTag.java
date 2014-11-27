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
package com.feilong.taglib.common.middleware;

import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 邮箱显示其名称及登录路径.
 * 
 * @author 金鑫 2010-3-25 下午02:42:49
 */
@Deprecated
public class EmailTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 邮箱. */
	private String				email;

	/**
	 * 显示内容 仅支持
	 * 
	 * <pre>
	 * chineseName :中文名 loginHref :登录路径  all	:所有
	 * </pre>
	 * 
	 * 默认all.
	 */
	private String				showContent			= "all";

	/* (non-Javadoc)
	 * @see com.feilong.taglib.base.AbstractCommonTag#writeContent()
	 */
	@Override
	public String writeContent(){
		// if ("all".equals(showContent)){// 所有
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }else if ("chineseName".equals(showContent)){// 中文名
		// return EmailUtil.getEmailChineseName(email);
		// }else if ("loginHref".equals(showContent)){// 登录路径
		// return EmailUtil.getEmailLoginHrefByEmail(email);
		// }
		return "";
	}

	/**
	 * 设置 邮箱.
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/**
	 * 设置 显示内容 仅支持
	 * 
	 * <pre>
	 * chineseName :中文名 loginHref :登录路径 all	:所有
	 * </pre>
	 * 
	 * 默认all.
	 * 
	 * @param showContent
	 *            the showContent to set
	 */
	public void setShowContent(String showContent){
		this.showContent = showContent;
	}
}
