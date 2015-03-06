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
package com.feilong.tools.ant.plugin.jpa.command;

import java.io.Serializable;

/**
 * The Class Table.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月26日 上午2:27:35
 * @since 1.0.7
 */
public class MyTable implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** 文件路径. */
    private String            fileAbsolutePath;

    /** 文件文字. */
    private String            fileName;

    /** 类名. */
    private String            className;

    /** 表名. */
    private String            tableName;

    /** 项目名称. */
    private String            projectName;

    /** 主键. */
    private String            id               = "no";

    /** 主键的数量. */
    private int               idCount;

    /** 主键的数量不正常. */
    private boolean           idCountException;

    /** strategy. */
    private String            strategy;

    /** identity. */
    private boolean           identity         = false;

    /** The Entity. */
    private Boolean           entity;

    /**
     * 获得 表名.
     * 
     * @return the tableName
     */
    public String getTableName(){
        return tableName;
    }

    /**
     * 设置 表名.
     * 
     * @param tableName
     *            the tableName to set
     */
    public void setTableName(String tableName){
        this.tableName = tableName;
    }

    /**
     * 获得 主键.
     * 
     * @return the id
     */
    public String getId(){
        return id;
    }

    /**
     * 设置 主键.
     * 
     * @param id
     *            the id to set
     */
    public void setId(String id){
        this.id = id;
    }

    /**
     * 获得 strategy.
     * 
     * @return the strategy
     */
    public String getStrategy(){
        return strategy;
    }

    /**
     * 设置 strategy.
     * 
     * @param strategy
     *            the strategy to set
     */
    public void setStrategy(String strategy){
        this.strategy = strategy;
    }

    /**
     * 获得 the Entity.
     * 
     * @return the entity
     */
    public Boolean getEntity(){
        return entity;
    }

    /**
     * 设置 the Entity.
     * 
     * @param entity
     *            the entity to set
     */
    public void setEntity(Boolean entity){
        this.entity = entity;
    }

    /**
     * 获得 类名.
     * 
     * @return the className
     */
    public String getClassName(){
        return className;
    }

    /**
     * 设置 类名.
     * 
     * @param className
     *            the className to set
     */
    public void setClassName(String className){
        this.className = className;
    }

    /**
     * 获得 文件路径.
     * 
     * @return the fileAbsolutePath
     */
    public String getFileAbsolutePath(){
        return fileAbsolutePath;
    }

    /**
     * 设置 文件路径.
     * 
     * @param fileAbsolutePath
     *            the fileAbsolutePath to set
     */
    public void setFileAbsolutePath(String fileAbsolutePath){
        this.fileAbsolutePath = fileAbsolutePath;
    }

    /**
     * 获得 文件文字.
     * 
     * @return the fileName
     */
    public String getFileName(){
        return fileName;
    }

    /**
     * 设置 文件文字.
     * 
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName){
        this.fileName = fileName;
    }

    /**
     * 获得 主键的数量.
     * 
     * @return the idCount
     */
    public int getIdCount(){
        return idCount;
    }

    /**
     * 设置 主键的数量.
     * 
     * @param idCount
     *            the idCount to set
     */
    public void setIdCount(int idCount){
        this.idCount = idCount;
    }

    /**
     * 获得 主键的数量不正常.
     * 
     * @return the idCountException
     */
    public boolean getIdCountException(){
        return idCount != 1;
    }

    /**
     * 设置 主键的数量不正常.
     * 
     * @param idCountException
     *            the idCountException to set
     */
    public void setIdCountException(boolean idCountException){
        this.idCountException = idCountException;
    }

    /**
     * 获得 identity.
     * 
     * @return the identity
     */
    public boolean getIdentity(){
        return identity;
    }

    /**
     * 设置 identity.
     * 
     * @param identity
     *            the identity to set
     */
    public void setIdentity(boolean identity){
        this.identity = identity;
    }

    /**
     * 获得 项目名称.
     * 
     * @return the projectName
     */
    public String getProjectName(){
        return projectName;
    }

    /**
     * 设置 项目名称.
     * 
     * @param projectName
     *            the projectName to set
     */
    public void setProjectName(String projectName){
        this.projectName = projectName;
    }

}
