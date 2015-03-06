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

package com.feilong.tools.solrj.paramscommand;

import java.io.Serializable;

import org.apache.solr.common.params.GroupParams;

/**
 * 和group by 相关的参数,注释掉的部分 表示暂没有封装.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 4:05:35 PM
 * @see GroupParams
 */
public class GroupParamsCommand implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** if true, turn on result grouping. */
    private boolean           isGroup          = true;

    /**
     * 可以设置为数组 params.set("group.field", "id", "age"); 
     */
    private String[]          groupFields;

    // *************************************************************************************************

    /**
     * the offset for the doclist of each group<br>
     * 这两个参数 只影响 聚合的结果<br>
     * 比如 每款鞋子 我只想显示 colorchip 两双 诸如此类;<br>
     * 如果不配置 只显示第一个 坑爹<br>
     * .
     */
    private int               offset           = 0;

    /** the limit for the number of documents in each group. */
    private int               limit            = 200;

    // *************************************************************************************************

    /**
     * 这个参数 用来 显示 聚合后 一共有多少款 诸如此类<br>
     * group.ngroups:Whether the group count should be included in the response<br>
     */
    private boolean           ngroups          = true;

    /**
     * grouped/simple<br>
     * 区别 simple 不会聚合显示, grouped 会, grouped 是我们想要的.
     */
    private String            format           = "grouped";

    /**
     * treat the first group result as the main result. true/false<br>
     *  when  group.format=simple and  group.main=true, just return the documentList only!!!  
     */
    private String            main             = "false";

    /**
     * 排序,such as "id asc" <br>
     * How to sort documents within a single group.<br>
     * Defaults to the same value as the sort parameter.
     */
    private String            sort;

    /**
     * default is 0;  <br>
     * Whether to cache the first pass search (doc ids and score) for the second pass search. <br>
     * Also defines the maximum size of the group cache relative to maxdoc in a percentage. <br>
     * Values can be a positive integer, from 0 till 100. <br>
     * A value of 0 will disable the group cache.The default is 0. <br>
     * Cache limit of 50 percent relative to maxdoc has exceeded. Please increase cache size or disable caching <br>
     */
    private String            cachePercent;

    // Return a single group of documents that also match the given query.
    // solrQuery.set(GroupParams.GROUP_QUERY, "isNikeIdSku:true", "isNikeIdSku:false");

    // Note: Since you can supply multiple fields to group on, but only have a facets for the whole result.
    // It only makes sense to me to support these parameters for the first group.
    // Whether the docSet (for example for faceting) should be based on plain documents (a.k.a UNGROUPED) or on the groups (a.k.a
    // GROUPED).
    // The docSet will only the most relevant documents per group.
    // It is if you query for everything with group.limit=1
    // default is false;
    // solrQuery.set(GroupParams.GROUP_TRUNCATE, "true");

    // *******************************************************************************************
    // 分布式
    // Retrieve the top search groups (top group values) from the shards being queried
    // solrQuery.set(GroupParams.GROUP_DISTRIBUTED_FIRST, "grouped");

    // Retrieve the top groups from the shards being queries based on the specified search groups in the
    // GROUP_DISTRIBUTED_TOPGROUPS_PREFIX parameters
    // solrQuery.set(GroupParams.GROUP_DISTRIBUTED_SECOND, "grouped");

    // solrQuery.set(GroupParams.GROUP_DISTRIBUTED_TOPGROUPS_PREFIX, "grouped");

    // *********************************************************************************************************

    /**
     * Gets the if true, turn on result grouping.
     * 
     * @return the isGroup
     */
    public boolean getIsGroup(){
        return isGroup;
    }

    /**
     * Sets the if true, turn on result grouping.
     * 
     * @param isGroup
     *            the isGroup to set
     */
    public void setIsGroup(boolean isGroup){
        this.isGroup = isGroup;
    }

    /**
     * Gets the 可以设置为数组 params.
     * 
     * @return the groupFields
     */
    public String[] getGroupFields(){
        return groupFields;
    }

    /**
     * Sets the 可以设置为数组 params.
     * 
     * @param groupFields
     *            the groupFields to set
     */
    public void setGroupFields(String[] groupFields){
        this.groupFields = groupFields;
    }

    /**
     * Gets the offset for the doclist of each group<br>
     * 这两个参数 只影响 聚合的结果<br>
     * 比如 每款鞋子 我只想显示 colorchip 两双 诸如此类;<br>
     * 如果不配置 只显示第一个 坑爹<br>
     * .
     * 
     * @return the offset
     */
    public int getOffset(){
        return offset;
    }

    /**
     * Sets the offset for the doclist of each group<br>
     * 这两个参数 只影响 聚合的结果<br>
     * 比如 每款鞋子 我只想显示 colorchip 两双 诸如此类;<br>
     * 如果不配置 只显示第一个 坑爹<br>
     * .
     * 
     * @param offset
     *            the offset to set
     */
    public void setOffset(int offset){
        this.offset = offset;
    }

    /**
     * Gets the limit for the number of documents in each group.
     * 
     * @return the limit
     */
    public int getLimit(){
        return limit;
    }

    /**
     * Sets the limit for the number of documents in each group.
     * 
     * @param limit
     *            the limit to set
     */
    public void setLimit(int limit){
        this.limit = limit;
    }

    /**
     * Gets the 这个参数 用来 显示 聚合后 一共有多少款 诸如此类<br>
     * group.
     * 
     * @return the ngroups
     */
    public boolean getNgroups(){
        return ngroups;
    }

    /**
     * Sets the 这个参数 用来 显示 聚合后 一共有多少款 诸如此类<br>
     * group.
     * 
     * @param ngroups
     *            the ngroups to set
     */
    public void setNgroups(boolean ngroups){
        this.ngroups = ngroups;
    }

    /**
     * Gets the grouped/simple<br>
     * 区别 simple 不会聚合显示, grouped 会, grouped 是我们想要的.
     * 
     * @return the format
     */
    public String getFormat(){
        return format;
    }

    /**
     * Sets the grouped/simple<br>
     * 区别 simple 不会聚合显示, grouped 会, grouped 是我们想要的.
     * 
     * @param format
     *            the format to set
     */
    public void setFormat(String format){
        this.format = format;
    }

    /**
     * Gets the treat the first group result as the main result.
     * 
     * @return the main
     */
    public String getMain(){
        return main;
    }

    /**
     * Sets the treat the first group result as the main result.
     * 
     * @param main
     *            the main to set
     */
    public void setMain(String main){
        this.main = main;
    }

    /**
     * Gets the 排序,such as "id asc" <br>
     * How to sort documents within a single group.
     * 
     * @return the sort
     */
    public String getSort(){
        return sort;
    }

    /**
     * Sets the 排序,such as "id asc" <br>
     * How to sort documents within a single group.
     * 
     * @param sort
     *            the sort to set
     */
    public void setSort(String sort){
        this.sort = sort;
    }

    /**
     * Gets the default is 0;  <br>
     * Whether to cache the first pass search (doc ids and score) for the second pass search.
     * 
     * @return the cachePercent
     */
    public String getCachePercent(){
        return cachePercent;
    }

    /**
     * Sets the default is 0;  <br>
     * Whether to cache the first pass search (doc ids and score) for the second pass search.
     * 
     * @param cachePercent
     *            the cachePercent to set
     */
    public void setCachePercent(String cachePercent){
        this.cachePercent = cachePercent;
    }

}
