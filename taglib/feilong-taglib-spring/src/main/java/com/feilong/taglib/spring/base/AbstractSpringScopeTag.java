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
package com.feilong.taglib.spring.base;

/**
 * 需要和spring控制的业务层交互,且仅仅设置作用域,请使用这个基类
 * 
 * <pre>
 * 
 * 只需要重写doExecute()方法即可
 * </pre>
 * 
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-8-27 下午01:33:05
 */
public abstract class AbstractSpringScopeTag extends BaseSpringTag{

	private static final long	serialVersionUID	= -9131054886965798341L;

	/**
	 * 设置作用域名称 类似于bean:define标签
	 */
	@SuppressWarnings("hiding")
	protected String			id;

	/**
	 * 标签开始
	 */
	@Override
	public int doStartTagInternal(){
		// 重写该方法
		doExecute();
		return SKIP_BODY; // 表示不用处理标签体，直接调用doEndTag()方法
	}

	/**
	 * 执行方法,重写该方法
	 * 
	 * @author 金鑫
	 * @version 1.0 2010-8-27 下午01:37:40
	 */
	protected abstract void doExecute();

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id){
		this.id = id;
	}
}