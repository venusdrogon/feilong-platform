/**
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
package com.feilong.framework.netpay.payment.adaptor.sprintasia.klikpay.command;

import java.io.Serializable;

/**
 * The Class Reason.
 */
public class Reason implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5331964326828362389L;

    /** The english. */
    private String            english;

    /** The indonesian. */
    private String            indonesian;

    /**
     * Gets the indonesian.
     * 
     * @return the indonesian
     */
    public String getIndonesian(){
        return indonesian;
    }

    /**
     * Sets the indonesian.
     * 
     * @param indonesian
     *            the indonesian to set
     */
    public void setIndonesian(String indonesian){
        this.indonesian = indonesian;
    }

    /**
     * Gets the english.
     * 
     * @return the english
     */
    public String getEnglish(){
        return english;
    }

    /**
     * Sets the english.
     * 
     * @param english
     *            the english to set
     */
    public void setEnglish(String english){
        this.english = english;
    }
}