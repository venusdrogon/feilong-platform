package com.feilong.taglib.pager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.taglib.pager.parser.PagerTagExport;
import com.feilong.taglib.pager.parser.ParseException;
import com.feilong.taglib.pager.parser.TagExportParser;

/**
 * 分页主标签
 * 
 * @author James Klicman
 */
public final class PagerTag extends TagSupport{

	private static final long	serialVersionUID		= 1L;

	private static final int	DEFAULT_MAX_ITEMS		= Integer.MAX_VALUE;

	private static final int	DEFAULT_MAX_PAGE_ITEMS	= 10;

	private static final int	DEFAULT_MAX_INDEX_PAGES	= 10;

	/*
	 * 标签属性,特性 Tag Properties
	 */
	private String				url						= null;

	private String				index					= null;

	private int					items					= 0;

	private int					maxItems				= DEFAULT_MAX_ITEMS;

	private int					maxPageItems			= DEFAULT_MAX_PAGE_ITEMS;

	private int					maxIndexPages			= DEFAULT_MAX_INDEX_PAGES;

	private boolean				isOffset				= false;

	private String				export					= null;

	private String				scope					= null;

	/*
	 * 标签可变参数 Tag Variables
	 */
	private StringBuffer		uri						= null;

	private int					params					= 0;

	private int					offset					= 0;

	private int					itemCount				= 0;

	private int					pageNumber				= 0;

	private Integer				pageNumberInteger		= null;

	private PagerTagExport		pagerTagExport			= null;

	private Object				oldPager				= null;

	private Object				oldOffset				= null;

	private Object				oldPageNumber			= null;

	public static final String	DEFAULT_ID				= "pager";

	// 作用域值 scope values
	public static final String	PAGE					= "page";

	public static final String	REQUEST					= "request";

	// 位置值 index values
	public static final String	CENTER					= "center";

	public static final String	FORWARD					= "forward";

	public static final String	OFFSET_PARAM			= ".offset";

	public static final String	HALF_FULL				= "half-full";

	private String				idOffsetParam			= DEFAULT_ID + OFFSET_PARAM;

	public PagerTag(){
		id = DEFAULT_ID;
	}

	/**
	 * 添加参数
	 * 
	 * @param name
	 *            名称
	 * @param value
	 *            值
	 * @author 金鑫
	 * @version 1.0 2010-4-14 上午10:38:05
	 * @throws UnsupportedEncodingException
	 */
	final void addParam(String name,String value){
		if (value != null){
			name = URIUtil.encode(name, CharsetType.GBK);
			value = URIUtil.encode(value, CharsetType.GBK);
			uri.append(params == 0 ? '?' : '&');
			uri.append(name);
			uri.append('=');
			uri.append(value);
			params++;
		}else{
			ServletRequest servletRequest = pageContext.getRequest();
			String[] values = servletRequest.getParameterValues(name);
			if (values != null){
				try{
					name = URLEncoder.encode(name, CharsetType.GB2312);
				}catch (UnsupportedEncodingException e){
					e.printStackTrace();
				}
				for (int i = 0, j = values.length; i < j; i++){
					try{
						value = new String(values[i].getBytes(CharsetType.ISO_8859_1), CharsetType.GB2312);
					}catch (UnsupportedEncodingException e){
						e.printStackTrace();
					}
					value = URIUtil.encode(value, CharsetType.GBK);
					uri.append(params == 0 ? '?' : '&');
					uri.append(name);
					uri.append('=');
					uri.append(value);
					params++;
				}
			}
		}
	}

	@Override
	public int doStartTag() throws JspException{
		String baseUri;
		ServletRequest servletRequest = pageContext.getRequest();
		// 默认当前页面
		if (Validator.isNotNullOrEmpty(url)){
			baseUri = url;
		}else{
			baseUri = ((HttpServletRequest) servletRequest).getRequestURI();
			int i = baseUri.indexOf('?');
			if (i != -1){
				baseUri = baseUri.substring(0, i);
			}
		}
		if (uri == null){
			uri = new StringBuffer(baseUri.length() + 32);
		}else{
			uri.setLength(0);
		}
		uri.append(baseUri);
		params = 0;
		offset = 0;
		itemCount = 0;
		// pager.offset
		String offsetParam = servletRequest.getParameter(idOffsetParam);
		if (offsetParam != null){
			offset = Math.max(0, Integer.parseInt(offsetParam));
			if (isOffset){
				itemCount = offset;
			}
		}
		pageNumber = pageNumber(offset);
		pageNumberInteger = new Integer(1 + pageNumber);
		if (REQUEST.equals(scope)){
			oldPager = servletRequest.getAttribute(id);
			servletRequest.setAttribute(id, this);
			if (pagerTagExport != null){
				String name;
				if ((name = pagerTagExport.getPageOffset()) != null){
					oldOffset = servletRequest.getAttribute(name);
					servletRequest.setAttribute(name, new Integer(offset));
				}
				if ((name = pagerTagExport.getPageNumber()) != null){
					oldPageNumber = servletRequest.getAttribute(name);
					servletRequest.setAttribute(name, pageNumberInteger);
				}
			}
		}else{
			if (pagerTagExport != null){
				String name;
				if ((name = pagerTagExport.getPageOffset()) != null){
					oldOffset = pageContext.getAttribute(name);
					pageContext.setAttribute(name, new Integer(offset));
				}
				if ((name = pagerTagExport.getPageNumber()) != null){
					oldPageNumber = pageContext.getAttribute(name);
					pageContext.setAttribute(name, pageNumberInteger);
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException{
		if (REQUEST.equals(scope)){
			ServletRequest request = pageContext.getRequest();
			restoreAttribute(request, id, oldPager);
			oldPager = null;
			if (pagerTagExport != null){
				String name;
				if ((name = pagerTagExport.getPageOffset()) != null){
					restoreAttribute(request, name, oldOffset);
					oldOffset = null;
				}
				if ((name = pagerTagExport.getPageNumber()) != null){
					restoreAttribute(request, name, oldPageNumber);
					oldPageNumber = null;
				}
			}
		}else{
			if (pagerTagExport != null){
				String name;
				if ((name = pagerTagExport.getPageOffset()) != null){
					restoreAttribute(pageContext, name, oldOffset);
					oldOffset = null;
				}
				if ((name = pagerTagExport.getPageNumber()) != null){
					restoreAttribute(pageContext, name, oldPageNumber);
					oldPageNumber = null;
				}
			}
		}
		// limit size of re-usable StringBuffer
		if (uri.capacity() > 1024){
			uri = null;
		}
		pageNumberInteger = null;
		return EVAL_PAGE;
	}

	@Override
	public final void setId(String sid){
		super.setId(sid);
		idOffsetParam = sid + OFFSET_PARAM;
	}

	public final void setUrl(String value){
		url = value;
	}

	public final String getUrl(){
		return url;
	}

	public final void setIndex(String val) throws JspException{
		if (!(val == null || CENTER.equals(val) || FORWARD.equals(val) || HALF_FULL.equals(val))){
			throw new JspTagException("value for attribute \"index\" " + "must be either \"center\", \"forward\" or \"half-full\".");
		}
		index = val;
	}

	public final String getIndex(){
		return index;
	}

	public final void setItems(int value){
		items = value;
	}

	public final int getItems(){
		return items;
	}

	public final void setMaxItems(int value){
		maxItems = value;
	}

	public final int getMaxItems(){
		return maxItems;
	}

	public final void setMaxPageItems(int value){
		maxPageItems = value;
	}

	public final int getMaxPageItems(){
		return maxPageItems;
	}

	public final void setMaxIndexPages(int value){
		maxIndexPages = value;
	}

	public final int getMaxIndexPages(){
		return maxIndexPages;
	}

	public final void setIsOffset(boolean val){
		isOffset = val;
	}

	public final boolean getIsOffset(){
		return isOffset;
	}

	public final void setExport(String value) throws JspException{
		if (export != value){
			try{
				pagerTagExport = TagExportParser.parsePagerTagExport(value);
			}catch (ParseException ex){
				throw new JspTagException(ex.getMessage());
			}
		}
		export = value;
	}

	public final String getExport(){
		return export;
	}

	public final void setScope(String val) throws JspException{
		if (!(val == null || PAGE.equals(val) || REQUEST.equals(val))){
			throw new JspTagException("value for attribute \"scope\" " + "must be either \"page\" or \"request\".");
		}
		scope = val;
	}

	public final String getScope(){
		return scope;
	}

	public final boolean nextItem(){
		boolean showItem = false;
		if (itemCount < maxItems){
			showItem = (itemCount >= offset && itemCount < (offset + maxPageItems));
			itemCount++;
		}
		return showItem;
	}

	public final int getOffset(){
		return offset;
	}

	public final boolean isIndexNeeded(){
		return (offset != 0 || getItemCount() > maxPageItems);
	}

	public final boolean hasPrevPage(){
		return (offset > 0);
	}

	public final boolean hasNextPage(){
		return (getItemCount() > getNextOffset());
	}

	public final boolean hasPage(int page){
		return (page >= 0 && getItemCount() > (page * maxPageItems));
	}

	public final int getPrevOffset(){
		return Math.max(0, offset - maxPageItems);
	}

	public final int getNextOffset(){
		return offset + maxPageItems;
	}

	public final String getOffsetUrl(int pageOffset){
		int uriLen = uri.length();
		uri.append(params == 0 ? '?' : '&').append(idOffsetParam).append('=').append(pageOffset);
		String offsetUrl = uri.toString();
		uri.setLength(uriLen);
		return offsetUrl;
	}

	public final String getPageUrl(int i){
		return getOffsetUrl(maxPageItems * i);
	}

	public final Integer getOffsetPageNumber(int pageOffset){
		return new Integer(1 + pageNumber(pageOffset));
	}

	public final Integer getPageNumber(int i){
		if (i == pageNumber){
			return pageNumberInteger;
		}
		return new Integer(1 + i);
	}

	public final int getPageNumber(){
		return pageNumber;
	}

	public final int getPageCount(){
		return pageNumber(getItemCount());
	}

	public final int getFirstIndexPage(){
		int firstPage = 0;
		int halfIndexPages = maxIndexPages / 2;
		if (FORWARD.equals(index)){
			firstPage = Math.min(pageNumber + 1, getPageCount());
		}else if (!(HALF_FULL.equals(index) && pageNumber < halfIndexPages)){
			int pages = getPageCount();
			if (pages > maxIndexPages){
				// put the current page in middle of the index
				firstPage = Math.max(0, pageNumber - halfIndexPages);
				int indexPages = pages - firstPage;
				if (indexPages < maxIndexPages){
					firstPage -= (maxIndexPages - indexPages);
				}
			}
		}
		return firstPage;
	}

	public final int getLastIndexPage(int firstPage){
		int pages = getPageCount();
		int halfIndexPages = maxIndexPages / 2;
		int maxPages;
		if (HALF_FULL.equals(index) && pageNumber < halfIndexPages){
			maxPages = pageNumber + halfIndexPages;
		}else{
			maxPages = firstPage + maxIndexPages;
		}
		return (pages <= maxPages ? pages : maxPages) - 1;
	}

	public final int getItemCount(){
		return (items != 0 ? items : itemCount);
	}

	private final int pageNumber(int offset){
		return (offset / maxPageItems) + (offset % maxPageItems == 0 ? 0 : 1);
	}

	private static void restoreAttribute(ServletRequest request,String name,Object oldValue){
		if (oldValue != null){
			request.setAttribute(name, oldValue);
		}else{
			request.removeAttribute(name);
		}
	}

	private static void restoreAttribute(PageContext pageContext,String name,Object oldValue){
		if (oldValue != null){
			pageContext.setAttribute(name, oldValue);
		}else{
			pageContext.removeAttribute(name);
		}
	}

	@Override
	public void release(){
		url = null;
		index = null;
		items = 0;
		maxItems = DEFAULT_MAX_ITEMS;
		maxPageItems = DEFAULT_MAX_PAGE_ITEMS;
		maxIndexPages = DEFAULT_MAX_INDEX_PAGES;
		isOffset = false;
		export = null;
		scope = null;
		uri = null;
		params = 0;
		offset = 0;
		itemCount = 0;
		pageNumber = 0;
		pageNumberInteger = null;
		idOffsetParam = DEFAULT_ID + OFFSET_PARAM;
		pagerTagExport = null;
		oldPager = null;
		oldOffset = null;
		oldPageNumber = null;
		super.release();
	}
}