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
import java.util.LinkedHashMap;

/**
 * PagerVMParam 可以在 vm中 取到的值.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 */
public class PagerVMParam implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long				serialVersionUID	= -6666715430016900907L;

	/** 设置的皮肤. */
	private String							skin;

	/** 总行数，总结果数. */
	private int								totalCount;

	/** 当前页码. */
	private int								currentPageNo;

	/** 总页数. */
	private int								allPageNo;

	/** 上一页链接. */
	private String							preUrl;

	/** 下一页链接. */
	private String							nextUrl;

	/** 第一页的链接. */
	private String							firstUrl;

	/** 最后一页的链接. */
	private String							lastUrl;

	/** 开始迭代索引编号. */
	private int								startIteratorIndex;

	/** 结束迭代索引编号. */
	private int								endIteratorIndex;

	/** 循环 迭代索引map key是编号，value 是页面链接. */
	private LinkedHashMap<Integer, String>	iteratorIndexMap;

	/**
	 * Gets the 设置的皮肤.
	 * 
	 * @return the skin
	 */
	public String getSkin(){
		return skin;
	}

	/**
	 * Sets the 设置的皮肤.
	 * 
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin){
		this.skin = skin;
	}

	/**
	 * Gets the 总行数，总结果数.
	 * 
	 * @return the totalCount
	 */
	public int getTotalCount(){
		return totalCount;
	}

	/**
	 * Sets the 总行数，总结果数.
	 * 
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	/**
	 * Gets the 当前页码.
	 * 
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * Sets the 当前页码.
	 * 
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * Gets the 总页数.
	 * 
	 * @return the allPageNo
	 */
	public int getAllPageNo(){
		return allPageNo;
	}

	/**
	 * Sets the 总页数.
	 * 
	 * @param allPageNo
	 *            the allPageNo to set
	 */
	public void setAllPageNo(int allPageNo){
		this.allPageNo = allPageNo;
	}

	/**
	 * Gets the 下一页链接.
	 * 
	 * @return the nextUrl
	 */
	public String getNextUrl(){
		return nextUrl;
	}

	/**
	 * Sets the 下一页链接.
	 * 
	 * @param nextUrl
	 *            the nextUrl to set
	 */
	public void setNextUrl(String nextUrl){
		this.nextUrl = nextUrl;
	}

	/**
	 * Gets the 最后一页的链接.
	 * 
	 * @return the lastUrl
	 */
	public String getLastUrl(){
		return lastUrl;
	}

	/**
	 * Sets the 最后一页的链接.
	 * 
	 * @param lastUrl
	 *            the lastUrl to set
	 */
	public void setLastUrl(String lastUrl){
		this.lastUrl = lastUrl;
	}

	/**
	 * Gets the 上一页链接.
	 * 
	 * @return the preUrl
	 */
	public String getPreUrl(){
		return preUrl;
	}

	/**
	 * Sets the 上一页链接.
	 * 
	 * @param preUrl
	 *            the preUrl to set
	 */
	public void setPreUrl(String preUrl){
		this.preUrl = preUrl;
	}

	/**
	 * Gets the 第一页的链接.
	 * 
	 * @return the firstUrl
	 */
	public String getFirstUrl(){
		return firstUrl;
	}

	/**
	 * Sets the 第一页的链接.
	 * 
	 * @param firstUrl
	 *            the firstUrl to set
	 */
	public void setFirstUrl(String firstUrl){
		this.firstUrl = firstUrl;
	}

	/**
	 * Gets the 开始迭代索引编号.
	 * 
	 * @return the startIteratorIndex
	 */
	public int getStartIteratorIndex(){
		return startIteratorIndex;
	}

	/**
	 * Sets the 开始迭代索引编号.
	 * 
	 * @param startIteratorIndex
	 *            the startIteratorIndex to set
	 */
	public void setStartIteratorIndex(int startIteratorIndex){
		this.startIteratorIndex = startIteratorIndex;
	}

	/**
	 * Gets the 结束迭代索引编号.
	 * 
	 * @return the endIteratorIndex
	 */
	public int getEndIteratorIndex(){
		return endIteratorIndex;
	}

	/**
	 * Sets the 结束迭代索引编号.
	 * 
	 * @param endIteratorIndex
	 *            the endIteratorIndex to set
	 */
	public void setEndIteratorIndex(int endIteratorIndex){
		this.endIteratorIndex = endIteratorIndex;
	}

	/**
	 * Gets the 循环 迭代索引map key是编号，value 是页面链接.
	 * 
	 * @return the iteratorIndexMap
	 */
	public LinkedHashMap<Integer, String> getIteratorIndexMap(){
		return iteratorIndexMap;
	}

	/**
	 * Sets the 循环 迭代索引map key是编号，value 是页面链接.
	 * 
	 * @param iteratorIndexMap
	 *            the iteratorIndexMap to set
	 */
	public void setIteratorIndexMap(LinkedHashMap<Integer, String> iteratorIndexMap){
		this.iteratorIndexMap = iteratorIndexMap;
	}

}
