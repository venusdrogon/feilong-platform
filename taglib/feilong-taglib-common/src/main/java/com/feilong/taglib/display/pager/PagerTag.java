package com.feilong.taglib.display.pager;

import javax.servlet.http.HttpServletRequest;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.taglib.base.AbstractCommonTag;

/**
 * 自制分页标签.
 * 
 * @author 金鑫 2010-2-3 下午01:03:14
 */
public class PagerTag extends AbstractCommonTag{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 最多显示多少个导航页码. */
	private int					maxIndexPages;

	/** 皮肤 默认digg. */
	private String				skin				= PagerConstant.default_skin;

	/** 数据总数. */
	private int					count;

	/** 每页显示多少行. */
	private int					pageSize;

	/** url页码参数,默认 pageNo. */
	private String				pageParamName		= PagerConstant.default_pageParamName;

	/** The vm path. */
	private String				vmPath				= PagerConstant.default_templateInClassPath;

	// *****************************end**************************************************.

	/**
	 * @return the string
	 */
	@Override
	public String writeContent(){
		HttpServletRequest request = getHttpServletRequest();
		// 当前页码
		int currentPageNo = PagerUtil.getCurrentPageNo(request, pageParamName);
		// 当前全路径
		// TODO
		String pageUrl = RequestUtil.getRequestFullURL(request, CharsetType.UTF8);

		PagerParams pagerParams = new PagerParams(count, pageUrl);

		pagerParams.setCurrentPageNo(currentPageNo);
		pagerParams.setPageSize(pageSize);
		pagerParams.setMaxIndexPages(maxIndexPages);
		pagerParams.setSkin(skin);
		pagerParams.setPageParamName(pageParamName);
		pagerParams.setVmPath(vmPath);
		pagerParams.setCharsetType(CharsetType.UTF8);

		String parameter = request.getParameter(PagerConstant.default_param_debugIsNotParseVM);
		boolean debugIsNotParseVM = PagerConstant.default_param_debugIsNotParseVM_value.equals(parameter);
		pagerParams.setDebugIsNotParseVM(debugIsNotParseVM);

		String html = PagerUtil.getPagerContent(pagerParams);

		pageContext.setAttribute(PagerConstant.default_request_name, html);
		return html;
	}

	/**
	 * Sets the 最多显示多少个导航页码.
	 * 
	 * @param maxIndexPages
	 *            the new 最多显示多少个导航页码
	 */
	public void setMaxIndexPages(int maxIndexPages){
		this.maxIndexPages = maxIndexPages;
	}

	/**
	 * Sets the 皮肤 默认digg.
	 * 
	 * @param skin
	 *            the new 皮肤 默认digg
	 */
	public void setSkin(String skin){
		this.skin = skin;
	}

	/**
	 * Sets the url页码参数,默认 pageNo.
	 * 
	 * @param pageParamName
	 *            the new url页码参数,默认 pageNo
	 */
	public void setPageParamName(String pageParamName){
		this.pageParamName = pageParamName;
	}

	/**
	 * Sets the 数据总数.
	 * 
	 * @param count
	 *            the new 数据总数
	 */
	public void setCount(int count){
		this.count = count;
	}

	/**
	 * Sets the 每页显示多少行.
	 * 
	 * @param pageSize
	 *            the new 每页显示多少行
	 */
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	/**
	 * Sets the vm path.
	 * 
	 * @param vmPath
	 *            the new vm path
	 */
	public void setVmPath(String vmPath){
		this.vmPath = vmPath;
	}
}
