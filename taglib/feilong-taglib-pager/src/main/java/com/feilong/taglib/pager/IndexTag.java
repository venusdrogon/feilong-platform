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
package com.feilong.taglib.pager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import com.feilong.taglib.pager.parser.IndexTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;
import com.feilong.taglib.pager.support.PagerTagSupport;

/**
 * 分页条显示的内容
 * 
 * @author James Klicman
 */
public final class IndexTag extends PagerTagSupport{

	private static final long	serialVersionUID	= 1L;

	private String				export				= null;

	private IndexTagExport		indexTagExport		= null;

	private Object				oldItemCount		= null;

	private Object				oldPageCount		= null;

	public final void setExport(String value) throws JspException{
		if (export != value){
			try{
				indexTagExport = TagExportParser.parseIndexTagExport(value);
			}catch (ParseException ex){
				throw new JspTagException(ex.getMessage());
			}
		}
		export = value;
	}

	public final String getExport(){
		return export;
	}

	@Override
	public int doStartTag() throws JspException{
		super.doStartTag();
		if (indexTagExport != null){
			String name;
			if ((name = indexTagExport.getItemCount()) != null){
				oldItemCount = pageContext.getAttribute(name);
				pageContext.setAttribute(name, new Integer(pagerTag.getItemCount()));
			}
			if ((name = indexTagExport.getPageCount()) != null){
				oldPageCount = pageContext.getAttribute(name);
				pageContext.setAttribute(name, new Integer(pagerTag.getPageCount()));
			}
		}
		return (pagerTag.isIndexNeeded() ? EVAL_BODY_INCLUDE : SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException{
		if (indexTagExport != null){
			String name;
			if ((name = indexTagExport.getItemCount()) != null){
				restoreAttribute(name, oldItemCount);
				oldItemCount = null;
			}
			if ((name = indexTagExport.getPageCount()) != null){
				restoreAttribute(name, oldPageCount);
				oldPageCount = null;
			}
		}
		super.doEndTag();
		return EVAL_PAGE;
	}

	@Override
	public void release(){
		export = null;
		indexTagExport = null;
		oldItemCount = null;
		oldPageCount = null;
		super.release();
	}
}