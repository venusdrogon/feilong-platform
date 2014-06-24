package com.feilong.taglib.pager.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

@SuppressWarnings("all")public abstract class JumpTagSupport extends PageTagSupport{

	private static final long	serialVersionUID	= 1L;

	public static final String	CURRENT				= "current";

	public static final String	INDEXED				= "indexed";

	private String				unless				= null;

	public final void setUnless(String value) throws JspException{
		if (!(value == null || CURRENT.equals(value) || INDEXED.equals(value))){
			throw new JspTagException("value for attribute \"unless\" " + "must be either \"current\" or \"indexed\".");
		}
		unless = value;
	}

	public final String getUnless(){
		return unless;
	}

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		int jumpPage = getJumpPage();
		if (CURRENT.equals(unless)){
			if (jumpPage == pagerTag.getPageNumber()){
				return SKIP_BODY;
			}
		}else if (INDEXED.equals(unless)){
			int firstPage = pagerTag.getFirstIndexPage();
			int lastPage = pagerTag.getLastIndexPage(firstPage);
			if (jumpPage >= firstPage && jumpPage <= lastPage){
				return SKIP_BODY;
			}
		}
		setPageAttributes(jumpPage);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public void release(){
		unless = null;
		super.release();
	}

	protected abstract int getJumpPage();
}
