package com.feilong.taglib.pager;

import javax.servlet.jsp.JspException;

import com.feilong.taglib.pager.support.PageTagSupport;

public final class PageTag extends PageTagSupport{

	private static final long	serialVersionUID	= 1L;

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		setOffsetAttributes(pagerTag.getOffset());
		return EVAL_BODY_INCLUDE;
	}
}
