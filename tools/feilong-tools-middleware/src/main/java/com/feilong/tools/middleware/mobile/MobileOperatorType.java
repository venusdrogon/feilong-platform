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
package com.feilong.tools.middleware.mobile;

/**
 * 手机运营商类型.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-4 下午01:27:15
 */
public enum MobileOperatorType{
    
    /** 移动. */
    ChinaMobile("移动"),
    
    /** 联通. */
    ChinaUnicom("联通"),
    
    /** 电信. */
    ChinaTelecom("电信");

    /** 名称. */
    private String name;

    //***********************************************************************
    /**
     * The Constructor.
     *
     * @param name
     *            the name
     */
    private MobileOperatorType(String name){
        this.name = name;
    }

    /**
     * 获得 名称.
     *
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * 设置 名称.
     *
     * @param name
     *            the name to set
     */
    public void setName(String name){
        this.name = name;
    }
}
