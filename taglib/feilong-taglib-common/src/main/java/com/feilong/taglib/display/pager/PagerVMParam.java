/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
 * PagerVMParam 可以在 vm中 取到的值
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 */
public class PagerVMParam implements Serializable{

	private static final long				serialVersionUID	= -6666715430016900907L;

	/**
	 * 设置的皮肤
	 */
	private String							skin;

	/**
	 * 总行数，总结果数
	 */
	private int								totalCount;

	/**
	 * 当前页码
	 */
	private int								currentPageNo;

	/**
	 * 总页数
	 */
	private int								allPageNo;

	/**
	 * 上一页链接
	 */
	private String							preUrl;

	/**
	 * 下一页链接
	 */
	private String							nextUrl;

	/**
	 * 第一页的链接
	 */
	private String							firstUrl;

	/**
	 * 最后一页的链接
	 */
	private String							lastUrl;

	/**
	 * 开始迭代索引编号
	 */
	private int								startIteratorIndex;

	/**
	 * 结束迭代索引编号
	 */
	private int								endIteratorIndex;

	/**
	 * 循环 迭代索引map key是编号，value 是页面链接
	 */
	private LinkedHashMap<Integer, String>	iteratorIndexMap;

	/**
	 * @return the skin
	 */
	public String getSkin(){
		return skin;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(String skin){
		this.skin = skin;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount(){
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
	}

	/**
	 * @return the currentPageNo
	 */
	public int getCurrentPageNo(){
		return currentPageNo;
	}

	/**
	 * @param currentPageNo
	 *            the currentPageNo to set
	 */
	public void setCurrentPageNo(int currentPageNo){
		this.currentPageNo = currentPageNo;
	}

	/**
	 * @return the allPageNo
	 */
	public int getAllPageNo(){
		return allPageNo;
	}

	/**
	 * @param allPageNo
	 *            the allPageNo to set
	 */
	public void setAllPageNo(int allPageNo){
		this.allPageNo = allPageNo;
	}

	/**
	 * @return the nextUrl
	 */
	public String getNextUrl(){
		return nextUrl;
	}

	/**
	 * @param nextUrl
	 *            the nextUrl to set
	 */
	public void setNextUrl(String nextUrl){
		this.nextUrl = nextUrl;
	}

	/**
	 * @return the lastUrl
	 */
	public String getLastUrl(){
		return lastUrl;
	}

	/**
	 * @param lastUrl
	 *            the lastUrl to set
	 */
	public void setLastUrl(String lastUrl){
		this.lastUrl = lastUrl;
	}

	/**
	 * @return the preUrl
	 */
	public String getPreUrl(){
		return preUrl;
	}

	/**
	 * @param preUrl
	 *            the preUrl to set
	 */
	public void setPreUrl(String preUrl){
		this.preUrl = preUrl;
	}

	/**
	 * @return the firstUrl
	 */
	public String getFirstUrl(){
		return firstUrl;
	}

	/**
	 * @param firstUrl
	 *            the firstUrl to set
	 */
	public void setFirstUrl(String firstUrl){
		this.firstUrl = firstUrl;
	}

	/**
	 * @return the startIteratorIndex
	 */
	public int getStartIteratorIndex(){
		return startIteratorIndex;
	}

	/**
	 * @param startIteratorIndex
	 *            the startIteratorIndex to set
	 */
	public void setStartIteratorIndex(int startIteratorIndex){
		this.startIteratorIndex = startIteratorIndex;
	}

	/**
	 * @return the endIteratorIndex
	 */
	public int getEndIteratorIndex(){
		return endIteratorIndex;
	}

	/**
	 * @param endIteratorIndex
	 *            the endIteratorIndex to set
	 */
	public void setEndIteratorIndex(int endIteratorIndex){
		this.endIteratorIndex = endIteratorIndex;
	}

	/**
	 * @return the iteratorIndexMap
	 */
	public LinkedHashMap<Integer, String> getIteratorIndexMap(){
		return iteratorIndexMap;
	}

	/**
	 * @param iteratorIndexMap
	 *            the iteratorIndexMap to set
	 */
	public void setIteratorIndexMap(LinkedHashMap<Integer, String> iteratorIndexMap){
		this.iteratorIndexMap = iteratorIndexMap;
	}

}
