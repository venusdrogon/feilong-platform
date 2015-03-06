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
package com.feilong.taglib.display.breadcrumb;

import java.io.Serializable;

/**
 * 飞龙面包屑所需要的字段 封装<br>
 * 其中泛型中的T 是 id 主键的类型,可以是Number String 或者其他类型.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-16 下午12:55:43
 * @param <PK>
 *            the generic type
 */
public class BreadCrumbEntity<PK> implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2739340747216481761L;

    /** current id, 可以是Number String 或者其他类型.. */
    private PK                id;

    /** name,用于拼接. */
    private String            name;

    /** title. */
    private String            title;

    /** 匹配的路径. */
    private String            requestMapping;

    /** parent id ,可以是Number String 或者其他类型.. */
    private PK                parentId;

    /**
     * Instantiates a new site map entity.
     */
    public BreadCrumbEntity(){
        super();
    }

    /**
     * Instantiates a new site map entity.
     * 
     * @param id
     *            the id
     * @param name
     *            the name
     * @param title
     *            the title
     * @param requestMapping
     *            the request mapping
     * @param parentId
     *            the parent id
     */
    public BreadCrumbEntity(PK id, String name, String title, String requestMapping, PK parentId){
        super();
        this.id = id;
        this.name = name;
        this.title = title;
        this.requestMapping = requestMapping;
        this.parentId = parentId;
    }

    /**
     * Gets the name,用于拼接.
     * 
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * Sets the name,用于拼接.
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Gets the title.
     * 
     * @return the title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Sets the title.
     * 
     * @param title
     *            the title to set
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Gets the 匹配的路径.
     * 
     * @return the requestMapping
     */
    public String getRequestMapping(){
        return requestMapping;
    }

    /**
     * Sets the 匹配的路径.
     * 
     * @param requestMapping
     *            the requestMapping to set
     */
    public void setRequestMapping(String requestMapping){
        this.requestMapping = requestMapping;
    }

    /**
     * 获得 current id, 可以是Number String 或者其他类型.
     * 
     * @return the id
     */
    public PK getId(){
        return id;
    }

    /**
     * 设置 current id, 可以是Number String 或者其他类型.
     * 
     * @param id
     *            the id to set
     */
    public void setId(PK id){
        this.id = id;
    }

    /**
     * 设置 parent id ,可以是Number String 或者其他类型.
     * 
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(PK parentId){
        this.parentId = parentId;
    }

    /**
     * 获得 parent id ,可以是Number String 或者其他类型.
     * 
     * @return the parentId
     */
    public PK getParentId(){
        return parentId;
    }
}
