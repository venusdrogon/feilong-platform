package com.feilong.taglib.pager;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

import com.feilong.taglib.pager.support.PageTagSupport;

@SuppressWarnings("all")public final class PagesTag extends PageTagSupport implements BodyTag{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private BodyContent			bodyContent			= null;

	private int					page				= 0;

	private int					lastPage			= 0;

	public void setBodyContent(BodyContent bc){
		bodyContent = bc;
	}

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		int firstPage = pagerTag.getFirstIndexPage();
		lastPage = pagerTag.getLastIndexPage(firstPage);
		page = firstPage;
		return (page <= lastPage ? EVAL_BODY_AGAIN : SKIP_BODY);
	}

	public void doInitBody() throws JspException{
		setPageAttributes(page);
		page++;
	}

	@Override
	public int doAfterBody() throws JspException{
		if (page <= lastPage){
			setPageAttributes(page);
			page++;
			return EVAL_BODY_AGAIN;
		}
		try{
			bodyContent.writeOut(bodyContent.getEnclosingWriter());
			return SKIP_BODY;
		}catch (IOException e){
			throw new JspTagException(e.toString());
		}
	}

	@Override
	public int doEndTag() throws JspException{
		bodyContent = null;
		super.doEndTag();
		return EVAL_PAGE;
	}

	@Override
	public void release(){
		bodyContent = null;
		super.release();
	}
}
