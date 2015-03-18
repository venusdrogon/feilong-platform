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

package com.feilong.tools.solrj.command;

import java.io.Serializable;
import java.util.List;

import org.apache.solr.common.params.FacetParams;

/**
 * 和group by 相关的参数.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 29, 2012 4:05:35 PM
 * @param <T>
 *            the generic type
 * @see FacetParams
 */
public class SolrGroup<T> implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** The group value. */
    private String            groupValue;

    /** 这个聚合值的 总数. */
    // = solrDocumentList.getNumFound();
    private Long              numFound;

    /** 将聚合的 每个 document 转成 bean. */
    private List<T>           beans            = null;

    /**
     * Gets the group value.
     * 
     * @return the groupValue
     */
    public String getGroupValue(){
        return groupValue;
    }

    /**
     * Sets the group value.
     * 
     * @param groupValue
     *            the groupValue to set
     */
    public void setGroupValue(String groupValue){
        this.groupValue = groupValue;
    }

    /**
     * Gets the 这个聚合值的 总数.
     * 
     * @return the numFound
     */
    public Long getNumFound(){
        return numFound;
    }

    /**
     * Sets the 这个聚合值的 总数.
     * 
     * @param numFound
     *            the numFound to set
     */
    public void setNumFound(Long numFound){
        this.numFound = numFound;
    }

    /**
     * Gets the 将聚合的 每个 document 转成 bean.
     * 
     * @return the beans
     */
    public List<T> getBeans(){
        return beans;
    }

    /**
     * Sets the 将聚合的 每个 document 转成 bean.
     * 
     * @param beans
     *            the beans to set
     */
    public void setBeans(List<T> beans){
        this.beans = beans;
    }

}
