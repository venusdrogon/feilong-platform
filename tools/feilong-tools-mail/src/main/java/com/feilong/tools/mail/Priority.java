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
package com.feilong.tools.mail;

/**
 * 邮件发送级别.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 1:05:45 AM
 */
public enum Priority{

    /** 最高. */
    highest("1"),

    /** 高. */
    high("2"),

    /** 一般. */
    normal("3"),

    /** 低. */
    low("4"),

    /** 最低. */
    lowest("5");

    /** 级别. */
    private String levelValue;

    /**
     * Instantiates a new priority.
     * 
     * @param levelValue
     *            the level value
     */
    private Priority(String levelValue){
        this.levelValue = levelValue;
    }

    /**
     * Gets the 级别.
     * 
     * @return the levelValue
     */
    public String getLevelValue(){
        return levelValue;
    }

    /**
     * Sets the 级别.
     * 
     * @param levelValue
     *            the levelValue to set
     */
    public void setLevelValue(String levelValue){
        this.levelValue = levelValue;
    }
}