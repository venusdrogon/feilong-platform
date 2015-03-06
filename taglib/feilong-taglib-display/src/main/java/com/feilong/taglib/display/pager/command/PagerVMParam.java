/*
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
import java.util.LinkedHashMap;

/**
 * PagerVMParam 可以在 vm中 取到的值.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 9, 2013 1:14:35 AM
 * @version 1.0.5 2014-5-7 13:26
 * @version 1.0.6 2014-5-7 13:50
 */
public class PagerVMParam implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long              serialVersionUID = -6666715430016900907L;

    /** 设置的皮肤. */
    private String                         skin;

    /** 总行数，总结果数. */
    private Integer                        totalCount;

    /** 当前页码. */
    private Integer                        currentPageNo;

    /** 总页数. */
    private Integer                        allPageNo;

    /**
     * 最多显示页数,(如果不设置,默认显示所有页数)<br>
     * 类似于淘宝不管搜索东西多少,最多显示100页<br>
     * 这是一种折中的处理方式，空间换时间。 数据查询越往后翻，对服务器的压力越大，速度越低，而且从业务上来讲商品质量也越差，所以就没有必要给太多了。<br>
     * 新浪微博的时间轴也只给出了10页，同样的折中处理。.
     */
    private Integer                        maxShowPageNo;

    /** 上一页链接. */
    private String                         preUrl;

    /** 下一页链接. */
    private String                         nextUrl;

    /** 第一页的链接. */
    private String                         firstUrl;

    /** 最后一页的链接. */
    private String                         lastUrl;

    /** 开始迭代索引编号. */
    private Integer                        startIteratorIndex;

    /** 结束迭代索引编号. */
    private Integer                        endIteratorIndex;

    /** 循环 迭代索引map key是编号，value 是页面链接. */
    private LinkedHashMap<Integer, String> iteratorIndexMap;

    /** The pager url template. */
    private PagerUrlTemplate               pagerUrlTemplate;

    /**
     * 分页参数名称 .
     * 
     * @since 1.0.6
     */
    private String                         pageParamName;

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
    public Integer getTotalCount(){
        return totalCount;
    }

    /**
     * Sets the 总行数，总结果数.
     * 
     * @param totalCount
     *            the totalCount to set
     */
    public void setTotalCount(Integer totalCount){
        this.totalCount = totalCount;
    }

    /**
     * Gets the 当前页码.
     * 
     * @return the currentPageNo
     */
    public Integer getCurrentPageNo(){
        return currentPageNo;
    }

    /**
     * Sets the 当前页码.
     * 
     * @param currentPageNo
     *            the currentPageNo to set
     */
    public void setCurrentPageNo(Integer currentPageNo){
        this.currentPageNo = currentPageNo;
    }

    /**
     * Gets the 总页数.
     * 
     * @return the allPageNo
     */
    public Integer getAllPageNo(){
        return allPageNo;
    }

    /**
     * Sets the 总页数.
     * 
     * @param allPageNo
     *            the allPageNo to set
     */
    public void setAllPageNo(Integer allPageNo){
        this.allPageNo = allPageNo;
    }

    /**
     * Gets the 最多显示页数,(如果不设置,默认显示所有页数)<br>
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
     * Sets the 最多显示页数,(如果不设置,默认显示所有页数)<br>
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
     * Gets the 开始迭代索引编号.
     * 
     * @return the startIteratorIndex
     */
    public Integer getStartIteratorIndex(){
        return startIteratorIndex;
    }

    /**
     * Sets the 开始迭代索引编号.
     * 
     * @param startIteratorIndex
     *            the startIteratorIndex to set
     */
    public void setStartIteratorIndex(Integer startIteratorIndex){
        this.startIteratorIndex = startIteratorIndex;
    }

    /**
     * Gets the 结束迭代索引编号.
     * 
     * @return the endIteratorIndex
     */
    public Integer getEndIteratorIndex(){
        return endIteratorIndex;
    }

    /**
     * Sets the 结束迭代索引编号.
     * 
     * @param endIteratorIndex
     *            the endIteratorIndex to set
     */
    public void setEndIteratorIndex(Integer endIteratorIndex){
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

    /**
     * Gets the pager url template.
     * 
     * @return the pagerUrlTemplate
     */
    public PagerUrlTemplate getPagerUrlTemplate(){
        return pagerUrlTemplate;
    }

    /**
     * Sets the pager url template.
     * 
     * @param pagerUrlTemplate
     *            the pagerUrlTemplate to set
     */
    public void setPagerUrlTemplate(PagerUrlTemplate pagerUrlTemplate){
        this.pagerUrlTemplate = pagerUrlTemplate;
    }

    /**
     * 获得 参数.
     * 
     * @return the pageParamName
     * @since 1.0.6
     */
    public String getPageParamName(){
        return pageParamName;
    }

    /**
     * 设置 参数.
     * 
     * @param pageParamName
     *            the pageParamName to set
     * @since 1.0.6
     */
    public void setPageParamName(String pageParamName){
        this.pageParamName = pageParamName;
    }

}
