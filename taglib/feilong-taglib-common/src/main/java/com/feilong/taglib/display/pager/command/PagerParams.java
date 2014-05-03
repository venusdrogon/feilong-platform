/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.taglib.display.pager.command;

import java.io.Serializable;
import java.util.Locale;

import com.feilong.commons.core.entity.Pager;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.taglib.display.pager.PagerUtil;

/**
 * 方法参数.<br>
 * 用于{@link PagerUtil#getPagerContent(PagerParams)}参数封装
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 */
public class PagerParams implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 7310948528499709685L;

	/** 分页的 基础 url. */
	private String				pageUrl;

	/** 总数据条数. */
	private Integer				totalCount;

	/** 当前第几页. */
	private Integer				currentPageNo;

	/**
	 * 最大 分页码数量.
	 * 
	 * @deprecated 参数名字取得不好,在将来的版本会更改替换,不建议使用这个参数
	 */
	private Integer				maxIndexPages;

	/** 每页显示多少条. */
	private Integer				pageSize			= PagerConstants.DEFAULT_PAGESIZE;

	/**
	 * 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 */
	private Integer				maxShowPageNo		= Pager.DEFAULT_LIMITED_MAX_PAGENO;

	/** 皮肤:可选. */
	private String				skin				= PagerConstants.DEFAULT_SKIN;

	/** 参数. */
	private String				pageParamName		= PagerConstants.DEFAULT_PAGE_PARAM_NAME;

	/** vm的路径. */
	private String				vmPath				= PagerConstants.DEFAULT_TEMPLATE_IN_CLASSPATH;

	/** 编码集. */
	private String				charsetType			= CharsetType.UTF8;

	/**
	 * 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 */
	private Locale				locale				= Locale.getDefault();

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
	public PagerParams(Integer totalCount, String pageUrl){
		this.totalCount = totalCount;
		this.pageUrl = pageUrl;
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
	 * Gets the 总数据条数.
	 * 
	 * @return the totalCount
	 */
	public Integer getTotalCount(){
		return totalCount;
	}

	/**
	 * Sets the 总数据条数.
	 * 
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(Integer totalCount){
		this.totalCount = totalCount;
	}

	/**
	 * Gets the 当前第几页.
	 * 
	 * @return the currentPageNo
	 */
	public Integer getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * Sets the 当前第几页.
	 * 
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(Integer currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * Gets the 每页显示多少条.
	 * 
	 * @return the pageSize
	 */
	public Integer getPageSize(){
		return pageSize;
	}

	/**
	 * Sets the 每页显示多少条.
	 * 
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(Integer pageSize){
		this.pageSize = pageSize;
	}

	/**
	 * Gets the 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 * 
	 * @return the maxShowPageNo
	 */
	public Integer getMaxShowPageNo(){
		return maxShowPageNo;
	}

	/**
	 * Sets the 最多显示页数,(-1或者不设置,默认显示所有页数)<br>
	 * 类似于淘宝不管搜索东西多少,最多显示100页<br>
	 * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
	 * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
	 * 
	 * @param maxShowPageNo
	 *            the maxShowPageNo to set
	 */
	public void setMaxShowPageNo(Integer maxShowPageNo){
		this.maxShowPageNo = maxShowPageNo;
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
	 * Gets the 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 * 
	 * @return the locale
	 */
	public Locale getLocale(){
		return locale;
	}

	/**
	 * Sets the 获得此 Java 虚拟机实例的当前默认语言环境值。 <br>
	 * Java 虚拟机根据主机的环境在启动期间设置默认语言环境。如果没有明确地指定语言环境，则很多语言环境敏感的方法都使用该方法。可使用 setDefault 方法更改该值。.
	 * 
	 * @param locale
	 *            the locale to set
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
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

	/**
	 * Gets the 最大 分页码数量.
	 * 
	 * @return the maxIndexPages
	 * @deprecated 参数名字取得不好,在将来的版本会更改替换,不建议使用这个参数
	 */
	public Integer getMaxIndexPages(){
		return maxIndexPages;
	}

	/**
	 * Sets the 最大 分页码数量.
	 * 
	 * @param maxIndexPages
	 *            the maxIndexPages to set
	 * @deprecated 参数名字取得不好,在将来的版本会更改替换,不建议使用这个参数
	 */
	public void setMaxIndexPages(Integer maxIndexPages){
		this.maxIndexPages = maxIndexPages;
	}
}
