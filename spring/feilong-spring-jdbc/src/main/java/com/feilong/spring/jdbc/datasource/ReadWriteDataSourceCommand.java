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
package com.feilong.spring.jdbc.datasource;

import java.io.Serializable;

import javax.sql.DataSource;

/**
 * 包含 读({@link #readDataSource}),写 ({@link #writeDataSource})数据源配置.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.1 2015年4月1日 上午3:42:04
 * @since 1.1.1
 * @see javax.sql.DataSource
 */
public class ReadWriteDataSourceCommand implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** The read data source. */
    private DataSource        readDataSource;

    /** The write data source. */
    private DataSource        writeDataSource;

    /**
     * 获得 read data source.
     *
     * @return the readDataSource
     */
    public DataSource getReadDataSource(){
        return readDataSource;
    }

    /**
     * 设置 read data source.
     *
     * @param readDataSource
     *            the readDataSource to set
     */
    public void setReadDataSource(DataSource readDataSource){
        this.readDataSource = readDataSource;
    }

    /**
     * 获得 write data source.
     *
     * @return the writeDataSource
     */
    public DataSource getWriteDataSource(){
        return writeDataSource;
    }

    /**
     * 设置 write data source.
     *
     * @param writeDataSource
     *            the writeDataSource to set
     */
    public void setWriteDataSource(DataSource writeDataSource){
        this.writeDataSource = writeDataSource;
    }
}