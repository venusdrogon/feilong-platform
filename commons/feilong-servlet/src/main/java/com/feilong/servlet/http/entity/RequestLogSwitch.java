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
package com.feilong.servlet.http.entity;

import java.io.Serializable;

/**
 * request log显示开关.<br>
 * 默认显示:
 * <ul>
 * <li>{@link #showFullURL} 请求路径</li>
 * <li>{@link #showMethod} 提交方法</li>
 * <li>{@link #showParams} 参数</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 23, 2014 11:33:28 AM
 */
public final class RequestLogSwitch implements Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 288232184048495608L;

    /** 显示和FullURL相关. */
    private boolean           showFullURL      = true;

    /** 显示和Method. */
    private boolean           showMethod       = true;

    /** 显示参数. */
    private boolean           showParams       = true;

    // ****************************************************************************

    /** 显示cookie. */
    private boolean           showCookies;

    /** 显示和ip相关. */
    private boolean           showIPs;

    /** 显示和url相关. */
    private boolean           showURLs;

    /** 显示和Port相关. */
    private boolean           showPorts;

    /** 显示和else相关. */
    private boolean           showElses;

    /** 显示和Header相关. */
    private boolean           showHeaders;

    /** 显示和Error相关. */
    private boolean           showErrors;

    /**
     * Instantiates a new request log switch<br>
     * 默认
     * <ul>
     * <li>showFullURL = true;</li>
     * <li>showMethod = true;</li>
     * <li>showParams = true;</li>
     * </ul>
     * .
     */
    public RequestLogSwitch(){
        super();
    }

    /**
     * The Constructor.
     *
     * @param full
     *            the full
     */
    public RequestLogSwitch(boolean full){
        if (full){
            this.showCookies = true;
            this.showElses = true;
            this.showErrors = true;
            this.showFullURL = true;
            this.showHeaders = true;
            this.showIPs = true;
            this.showMethod = true;
            this.showParams = true;
            this.showPorts = true;
            this.showURLs = true;
        }
    }

    /**
     * Gets the 显示参数.
     * 
     * @return the showParams
     */
    public boolean getShowParams(){
        return showParams;
    }

    /**
     * Sets the 显示参数.
     * 
     * @param showParams
     *            the showParams to set
     */
    public void setShowParams(boolean showParams){
        this.showParams = showParams;
    }

    /**
     * Gets the 显示cookie.
     * 
     * @return the showCookies
     */
    public boolean getShowCookies(){
        return showCookies;
    }

    /**
     * Sets the 显示cookie.
     * 
     * @param showCookies
     *            the showCookies to set
     */
    public void setShowCookies(boolean showCookies){
        this.showCookies = showCookies;
    }

    /**
     * Gets the 显示和ip相关.
     * 
     * @return the showIPs
     */
    public boolean getShowIPs(){
        return showIPs;
    }

    /**
     * Sets the 显示和ip相关.
     * 
     * @param showIPs
     *            the showIPs to set
     */
    public void setShowIPs(boolean showIPs){
        this.showIPs = showIPs;
    }

    /**
     * Gets the 显示和url相关.
     * 
     * @return the showURLs
     */
    public boolean getShowURLs(){
        return showURLs;
    }

    /**
     * Sets the 显示和url相关.
     * 
     * @param showURLs
     *            the showURLs to set
     */
    public void setShowURLs(boolean showURLs){
        this.showURLs = showURLs;
    }

    /**
     * Gets the 显示和Port相关.
     * 
     * @return the showPorts
     */
    public boolean getShowPorts(){
        return showPorts;
    }

    /**
     * Sets the 显示和Port相关.
     * 
     * @param showPorts
     *            the showPorts to set
     */
    public void setShowPorts(boolean showPorts){
        this.showPorts = showPorts;
    }

    /**
     * Gets the 显示和else相关.
     * 
     * @return the showElses
     */
    public boolean getShowElses(){
        return showElses;
    }

    /**
     * Sets the 显示和else相关.
     * 
     * @param showElses
     *            the showElses to set
     */
    public void setShowElses(boolean showElses){
        this.showElses = showElses;
    }

    /**
     * Gets the 显示和Header相关.
     * 
     * @return the showHeaders
     */
    public boolean getShowHeaders(){
        return showHeaders;
    }

    /**
     * Sets the 显示和Header相关.
     * 
     * @param showHeaders
     *            the showHeaders to set
     */
    public void setShowHeaders(boolean showHeaders){
        this.showHeaders = showHeaders;
    }

    /**
     * Gets the 显示和Error相关.
     * 
     * @return the showErrors
     */
    public boolean getShowErrors(){
        return showErrors;
    }

    /**
     * Sets the 显示和Error相关.
     * 
     * @param showErrors
     *            the showErrors to set
     */
    public void setShowErrors(boolean showErrors){
        this.showErrors = showErrors;
    }

    /**
     * Gets the 显示和FullURL相关.
     * 
     * @return the showFullURL
     */
    public boolean getShowFullURL(){
        return showFullURL;
    }

    /**
     * Sets the 显示和FullURL相关.
     * 
     * @param showFullURL
     *            the showFullURL to set
     */
    public void setShowFullURL(boolean showFullURL){
        this.showFullURL = showFullURL;
    }

    /**
     * Gets the 显示和Method.
     * 
     * @return the showMethod
     */
    public boolean getShowMethod(){
        return showMethod;
    }

    /**
     * Sets the 显示和Method.
     * 
     * @param showMethod
     *            the showMethod to set
     */
    public void setShowMethod(boolean showMethod){
        this.showMethod = showMethod;
    }

}
