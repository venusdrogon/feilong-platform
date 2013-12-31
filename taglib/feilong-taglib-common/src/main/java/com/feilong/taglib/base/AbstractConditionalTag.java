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

/**
 * 条件父类标签,需要条件控制的 例如logic equal请使用这个 <br>
 * 用法:简单条件标签,仅需实现condition()方法即可
 * 
 * @author 金鑫 2009-11-7下午05:39:08
 */
public abstract class AbstractConditionalTag extends BaseTag{

	private static final long	serialVersionUID	= -7583054141845571177L;

	/**
	 * 标签开始
	 */
	@Override
	public int doStartTag(){
		if (condition()){
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	/**
	 * 自定义条件
	 * 
	 * @return 条件满足,返回true,页面会EVAL_BODY_INCLUDE,否则SKIP_BODY
	 */
	protected abstract boolean condition();
}
