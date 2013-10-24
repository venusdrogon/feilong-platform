package com.feilong.taglib.pager.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.TagSupport;

import com.feilong.taglib.pager.PagerTag;

/**
 * 分页标签Support,用于取到嵌套标签父类的
 * 
 * @author James Klicman
 */
public abstract class PagerTagSupport extends TagSupport{

	private static final long	serialVersionUID	= 1L;

	protected PagerTag			pagerTag			= null;

	protected final void restoreAttribute(String name,Object oldValue){
		if (oldValue != null){
			pageContext.setAttribute(name, oldValue);
		}else{
			pageContext.removeAttribute(name);
		}
	}

	private final PagerTag findRequestPagerTag(String pagerId){
		Object obj = pageContext.getRequest().getAttribute(pagerId);
		if (obj instanceof PagerTag){
			return (PagerTag) obj;
		}
		return null;
	}

	@Override
	public int doStartTag() throws JspException{
		if (id != null){
			pagerTag = findRequestPagerTag(id);
			if (pagerTag == null){
				throw new JspTagException("pager tag with id of \"" + id + "\" not found.");
			}
		}else{
			// 获取父标记对应的对象 先找到此嵌套tag对象
			pagerTag = (PagerTag) findAncestorWithClass(this, PagerTag.class);
			if (pagerTag == null){
				pagerTag = findRequestPagerTag(PagerTag.DEFAULT_ID);
				if (pagerTag == null){
					throw new JspTagException("not nested within a pager tag" + " and no pager tag found at request scope.");
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException{
		pagerTag = null;
		return EVAL_PAGE;
	}

	@Override
	public void release(){
		pagerTag = null;
		super.release();
	}
}
