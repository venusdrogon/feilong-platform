/*
 * Pager Tag Library
 * Copyright (C) 2002 James Klicman <james@jsptags.com>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package com.feilong.taglib.pager.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import com.feilong.taglib.pager.parser.PageTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;

public abstract class PageTagSupport extends PagerTagSupport{

	private static final long	serialVersionUID	= 1L;

	private String				export				= null;

	private PageTagExport		pageTagExport		= null;

	private Object				oldPageUrl			= null;

	private Object				oldPageNumber		= null;

	private Object				oldFirstItem		= null;

	private Object				oldLastItem			= null;

	public final void setExport(String value) throws JspException{
		if (export != value){
			try{
				pageTagExport = TagExportParser.parsePageTagExport(value);
			}catch (ParseException ex){
				throw new JspTagException(ex.getMessage());
			}
		}
		export = value;
	}

	public final String getExport(){
		return export;
	}

	protected final void setPageAttributes(int page){
		if (pageTagExport == null){
			pageContext.setAttribute(PageTagExport.PAGE_URL, pagerTag.getPageUrl(page));
			pageContext.setAttribute(PageTagExport.PAGE_NUMBER, pagerTag.getPageNumber(page));
		}else{
			String name;
			if ((name = pageTagExport.getPageUrl()) != null){
				pageContext.setAttribute(name, pagerTag.getPageUrl(page));
			}
			if ((name = pageTagExport.getPageNumber()) != null){
				pageContext.setAttribute(name, pagerTag.getPageNumber(page));
			}
			int maxPageItems = pagerTag.getMaxPageItems();
			if ((name = pageTagExport.getFirstItem()) != null){
				int firstItem = (page * maxPageItems) + 1;
				pageContext.setAttribute(name, new Integer(firstItem));
			}
			if ((name = pageTagExport.getLastItem()) != null){
				int lastItem = Math.min((page + 1) * maxPageItems, pagerTag.getItemCount());
				pageContext.setAttribute(name, new Integer(lastItem));
			}
		}
	}

	protected final void setOffsetAttributes(int offset){
		if (pageTagExport == null){
			pageContext.setAttribute(PageTagExport.PAGE_URL, pagerTag.getOffsetUrl(offset));
			pageContext.setAttribute(PageTagExport.PAGE_NUMBER, pagerTag.getOffsetPageNumber(offset));
		}else{
			String name;
			if ((name = pageTagExport.getPageUrl()) != null){
				pageContext.setAttribute(name, pagerTag.getOffsetUrl(offset));
			}
			if ((name = pageTagExport.getPageNumber()) != null){
				pageContext.setAttribute(name, pagerTag.getOffsetPageNumber(offset));
			}
			int maxPageItems = pagerTag.getMaxPageItems();
			if ((name = pageTagExport.getFirstItem()) != null){
				int firstItem = offset + 1;
				pageContext.setAttribute(name, new Integer(firstItem));
			}
			if ((name = pageTagExport.getLastItem()) != null){
				int lastItem = Math.min(offset + maxPageItems, pagerTag.getItemCount());
				pageContext.setAttribute(name, new Integer(lastItem));
			}
		}
	}

	protected final void removeAttributes(){
		if (pageTagExport == null){
			pageContext.removeAttribute(PageTagExport.PAGE_URL);
			pageContext.removeAttribute(PageTagExport.PAGE_NUMBER);
		}else{
			String name;
			if ((name = pageTagExport.getPageUrl()) != null){
				pageContext.removeAttribute(name);
			}
			if ((name = pageTagExport.getPageNumber()) != null){
				pageContext.removeAttribute(name);
			}
			if ((name = pageTagExport.getFirstItem()) != null){
				pageContext.removeAttribute(name);
			}
			if ((name = pageTagExport.getLastItem()) != null){
				pageContext.removeAttribute(name);
			}
		}
	}

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		if (pageTagExport == null){
			oldPageUrl = pageContext.getAttribute(PageTagExport.PAGE_URL);
			oldPageNumber = pageContext.getAttribute(PageTagExport.PAGE_NUMBER);
		}else{
			String name;
			if ((name = pageTagExport.getPageUrl()) != null){
				oldPageUrl = pageContext.getAttribute(name);
			}
			if ((name = pageTagExport.getPageNumber()) != null){
				oldPageNumber = pageContext.getAttribute(name);
			}
			if ((name = pageTagExport.getFirstItem()) != null){
				oldFirstItem = pageContext.getAttribute(name);
			}
			if ((name = pageTagExport.getLastItem()) != null){
				oldLastItem = pageContext.getAttribute(name);
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException{
		if (pageTagExport == null){
			restoreAttribute(PageTagExport.PAGE_URL, oldPageUrl);
			restoreAttribute(PageTagExport.PAGE_NUMBER, oldPageNumber);
			oldPageUrl = null;
			oldPageNumber = null;
		}else{
			String name;
			if ((name = pageTagExport.getPageUrl()) != null){
				restoreAttribute(name, oldPageUrl);
				oldPageUrl = null;
			}
			if ((name = pageTagExport.getPageNumber()) != null){
				restoreAttribute(name, oldPageNumber);
				oldPageNumber = null;
			}
			if ((name = pageTagExport.getFirstItem()) != null){
				restoreAttribute(name, oldFirstItem);
				oldFirstItem = null;
			}
			if ((name = pageTagExport.getLastItem()) != null){
				restoreAttribute(name, oldLastItem);
				oldLastItem = null;
			}
		}
		super.doEndTag();
		return EVAL_PAGE;
	}

	@Override
	public void release(){
		export = null;
		pageTagExport = null;
		oldPageUrl = null;
		oldPageNumber = null;
		oldFirstItem = null;
		oldLastItem = null;
		super.release();
	}
}
