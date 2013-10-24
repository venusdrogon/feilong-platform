package com.feilong.taglib.pager;

import javax.servlet.jsp.JspException;

import com.feilong.taglib.pager.support.PagerTagSupport;

public final class ItemTag extends PagerTagSupport{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		return (pagerTag.nextItem() ? EVAL_BODY_INCLUDE : SKIP_BODY);
	}
}
/* vim:set ts=4 sw=4: */
