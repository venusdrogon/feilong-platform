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

import loxia.dao.Pagination;

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
public class SolrGroupCommand<T> implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long        serialVersionUID = 288232184048495608L;

    /** filedName 按照什么聚合的. */
    private String                   name;

    /** 聚合的结果(包含分页信息). */
    private Pagination<SolrGroup<T>> pagination;

    /** 聚合前的总数. */
    private int                      matches;

    /** 聚合后的总数. */
    private Integer                  ngroups;

    /**
     * Gets the filedName 按照什么聚合.
     * 
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the filedName 按照什么聚合.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the 聚合前的总数.
     * 
     * @return the matches
     */
    public int getMatches(){
        return matches;
    }

    /**
     * Sets the 聚合前的总数.
     * 
     * @param matches
     *            the matches to set
     */
    public void setMatches(int matches){
        this.matches = matches;
    }

    /**
     * Gets the 聚合后的总数.
     * 
     * @return the ngroups
     */
    public Integer getNgroups(){
        return ngroups;
    }

    /**
     * Sets the 聚合后的总数.
     * 
     * @param ngroups
     *            the ngroups to set
     */
    public void setNgroups(Integer ngroups){
        this.ngroups = ngroups;
    }

    /**
     * Gets the 聚合的结果(包含分页信息).
     * 
     * @return the pagination
     */
    public Pagination<SolrGroup<T>> getPagination(){
        return pagination;
    }

    /**
     * Sets the 聚合的结果(包含分页信息).
     * 
     * @param pagination
     *            the pagination to set
     */
    public void setPagination(Pagination<SolrGroup<T>> pagination){
        this.pagination = pagination;
    }

}
