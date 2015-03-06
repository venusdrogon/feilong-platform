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
package com.feilong.framework.netpay.payment.adaptor.tcash;

import com.feilong.framework.netpay.payment.adaptor.AdaptorParseUsableData;

/**
 * Below table shows T-Cash Public Token Generator response (Json).
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月21日 下午3:35:08
 * @since 1.0.8
 */
public final class PublicTokenResponse implements AdaptorParseUsableData{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /**
     * Public token that used by customer when they redirect into T-Cash landing page<br>
     * Example 1: -----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.47\n\nhQEOAyYtPQbS+5JtEAP/fI1MbXcrVa/83WlZS6y+76g
     */
    private String            pgpToken;

    /**
     * T-Cash reference number that will be used by merchant for checking customer transaction<br>
     * Example 1: 2506141402007010.
     */
    private String            refNum;

    /**
     * Limit time for using public token (in milisecond)<br>
     * Example 1: 1403679721180.
     */
    private String            fastTime;

    /**
     * 获得 public token that used by customer when they redirect into T-Cash landing page<br>
     * Example 1: -----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.
     * 
     * @return the pgpToken
     */
    public String getPgpToken(){
        return pgpToken;
    }

    /**
     * 设置 public token that used by customer when they redirect into T-Cash landing page<br>
     * Example 1: -----BEGIN PGP MESSAGE-----\nVersion: BCPG v1.
     * 
     * @param pgpToken
     *            the pgpToken to set
     */
    public void setPgpToken(String pgpToken){
        this.pgpToken = pgpToken;
    }

    /**
     * 获得 t-Cash reference number that will be used by merchant for checking customer transaction<br>
     * Example 1: 2506141402007010.
     * 
     * @return the refNum
     */
    public String getRefNum(){
        return refNum;
    }

    /**
     * 设置 t-Cash reference number that will be used by merchant for checking customer transaction<br>
     * Example 1: 2506141402007010.
     * 
     * @param refNum
     *            the refNum to set
     */
    public void setRefNum(String refNum){
        this.refNum = refNum;
    }

    /**
     * 获得 limit time for using public token (in milisecond)<br>
     * Example 1: 1403679721180.
     * 
     * @return the fastTime
     */
    public String getFastTime(){
        return fastTime;
    }

    /**
     * 设置 limit time for using public token (in milisecond)<br>
     * Example 1: 1403679721180.
     * 
     * @param fastTime
     *            the fastTime to set
     */
    public void setFastTime(String fastTime){
        this.fastTime = fastTime;
    }

}
