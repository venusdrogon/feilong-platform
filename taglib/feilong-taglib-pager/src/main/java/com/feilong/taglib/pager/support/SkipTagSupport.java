package com.feilong.taglib.pager.support;

import javax.servlet.jsp.JspException;

@SuppressWarnings("all")public abstract class SkipTagSupport extends PageTagSupport{

	private static final long	serialVersionUID	= 1L;

	/**
	 * 指定,当没有(上一页/下...)的时候,是否执行标签内容,默认是false,不执行标签内容
	 */
	private boolean				ifnull				= false;

	public final void setIfnull(boolean b){
		ifnull = b;
	}

	public final boolean getIfnull(){
		return ifnull;
	}

	protected abstract boolean skip();

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		if (!skip()){
			if (!ifnull){
				return SKIP_BODY;
			}
			removeAttributes();
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void release(){
		ifnull = false;
		super.release();
	}
}
