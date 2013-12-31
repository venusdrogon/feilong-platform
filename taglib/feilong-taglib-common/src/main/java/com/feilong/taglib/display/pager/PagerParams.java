/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.taglib.display.pager;

import java.io.Serializable;

/**
 * 方法参数.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 */
public class PagerParams implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7310948528499709685L;

	/** 总数据条数. */
	private int					totalCount;

	/** 当前第几页. */
	private int					currentPageNo;

	/** 每页显示多少条. */
	private int					pageSize;

	/** 最大 分页码. */
	private int					maxIndexPages;

	/** 皮肤:可选. */
	private String				skin				= PagerConstant.default_skin;

	/** 分页的 基础 url. */
	private String				pageUrl;

	/** 参数. */
	private String				pageParamName;

	/** vm的路径. */
	private String				vmPath;

	/** 编码集. */
	private String				charsetType;

	/** debug 模式. */
	private boolean				debugIsNotParseVM;

	/**
	 * The Constructor.
	 * 
	 * @param totalCount
	 *            总数据条数 the total count
	 * @param pageUrl
	 *            分页的 基础 url the page url
	 */
	public PagerParams(int totalCount, String pageUrl){
		super();
		this.totalCount = totalCount;
		this.pageUrl = pageUrl;
	}

	/**
	 * Gets the 总数据条数.
	 * 
	 * @return the totalCount
	 */
	public int getTotalCount(){
		return totalCount;
	}

	/**
	 * Sets the 总数据条数.
	 * 
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	/**
	 * Gets the 当前第几页.
	 * 
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * Sets the 当前第几页.
	 * 
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * Gets the 每页显示多少条.
	 * 
	 * @return the pageSize
	 */
	public int getPageSize(){
		return pageSize;
	}

	/**
	 * Sets the 每页显示多少条.
	 * 
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize){
		this.pageSize = pageSize;
	}

	/**
	 * Gets the 最大 分页码.
	 * 
	 * @return the maxIndexPages
	 */
	public int getMaxIndexPages(){
		return maxIndexPages;
	}

	/**
	 * Sets the 最大 分页码.
	 * 
	 * @param maxIndexPages
	 *            the maxIndexPages to set
	 */
	public void setMaxIndexPages(int maxIndexPages){
		this.maxIndexPages = maxIndexPages;
	}

	/**
	 * Gets the 皮肤:可选.
	 * 
	 * @return the skin
	 */
	public String getSkin(){
		return skin;
	}

	/**
	 * Sets the 皮肤:可选.
	 * 
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin){
		this.skin = skin;
	}

	/**
	 * Gets the 分页的 基础 url.
	 * 
	 * @return the pageUrl
	 */
	public String getPageUrl(){
		return pageUrl;
	}

	/**
	 * Sets the 分页的 基础 url.
	 * 
	 * @param pageUrl
	 *            the pageUrl to set
	 */
	public void setPageUrl(String pageUrl){
		this.pageUrl = pageUrl;
	}

	/**
	 * Gets the 参数.
	 * 
	 * @return the pageParamName
	 */
	public String getPageParamName(){
		return pageParamName;
	}

	/**
	 * Sets the 参数.
	 * 
	 * @param pageParamName
	 *            the pageParamName to set
	 */
	public void setPageParamName(String pageParamName){
		this.pageParamName = pageParamName;
	}

	/**
	 * Gets the vm的路径.
	 * 
	 * @return the vmPath
	 */
	public String getVmPath(){
		return vmPath;
	}

	/**
	 * Sets the vm的路径.
	 * 
	 * @param vmPath
	 *            the vmPath to set
	 */
	public void setVmPath(String vmPath){
		this.vmPath = vmPath;
	}

	/**
	 * Gets the 编码集.
	 * 
	 * @return the charsetType
	 */
	public String getCharsetType(){
		return charsetType;
	}

	/**
	 * Sets the 编码集.
	 * 
	 * @param charsetType
	 *            the charsetType to set
	 */
	public void setCharsetType(String charsetType){
		this.charsetType = charsetType;
	}

	/**
	 * Gets the debug 模式.
	 * 
	 * @return the debugIsNotParseVM
	 */
	public boolean getDebugIsNotParseVM(){
		return debugIsNotParseVM;
	}

	/**
	 * Sets the debug 模式.
	 * 
	 * @param debugIsNotParseVM
	 *            the debugIsNotParseVM to set
	 */
	public void setDebugIsNotParseVM(boolean debugIsNotParseVM){
		this.debugIsNotParseVM = debugIsNotParseVM;
	}
}
