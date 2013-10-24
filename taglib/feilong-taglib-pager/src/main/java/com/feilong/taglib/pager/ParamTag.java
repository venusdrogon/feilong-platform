package com.feilong.taglib.pager;

import javax.servlet.jsp.JspException;

import com.feilong.taglib.pager.support.PagerTagSupport;

/**
 * 参数标签
 * 
 * @author James Klicman 2010-1-25 下午01:47:53
 */
public final class ParamTag extends PagerTagSupport{

	private static final long	serialVersionUID	= 1L;

	private String				name				= null;

	private String				value				= null;

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		pagerTag.addParam(name, value);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void release(){
		name = null;
		value = null;
		super.release();
	}

	public final void setName(String val){
		name = val;
	}

	public final String getName(){
		return name;
	}

	public final void setValue(String val){
		value = val;
	}

	public final String getValue(){
		return value;
	}
}
