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
package com.feilong.web.browser;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.util.StringUtil;
import com.feilong.servlet.http.RequestUtil;

/**
 * 浏览器.<br>
 * 
 * <pre>
 *  userAgent 属性是一个只读的字符串，声明了浏览器用于 HTTP 请求的用户代理头的值。
 *  一般来讲，它是在 navigator.appCodeName 的值之后加上斜线和 navigator.appVersion 的值构成的。
 *  例如：Mozilla/4.0 (compatible; MSIE6.0; Windows NT 5.2; SV1; .NET CLR 1.1.4322)。
 *  注：用户代理头：user-agent header。
 * 
 *  User Agent中文名为用户代理，简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等。
 * 
 *  标准格式为： 
 *  浏览器标识 (操作系统标识; 加密等级标识; 浏览器语言) 渲染引擎标识 版本信息
 *  
 *  //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.17 (KHTML, like Gecko) Chrome/24.0.1312.57 Safari/537.17
 *  
 *  iphone :Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_0 like Mac OS X; en-us) AppleWebKit/532.9 (KHTML, like Gecko) Version/4.0.5 Mobile/8A293 Safari/6531.22.7
 * 
 * </pre>
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 25, 2013 3:38:09 PM
 * @see "c# 的 HttpCapabilitiesBase"
 */
public class Browser implements Serializable{

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(Browser.class);

    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = -3594490109178217925L;

    /** userAgent. */
    private String              userAgent;

    /** 是不是移动设备. */
    private boolean             isMobileDevice;

    /** 是不是 ipad. */
    private boolean             isIpad;

    /** 是不是 isIphone. */
    private boolean             isIphone;

    // /** 浏览器版本号. */
    // private String version;

    /**
     * Instantiates a new browser.
     * 
     * @param request
     *            the request
     */
    public Browser(HttpServletRequest request){
        this.userAgent = RequestUtil.getHeaderUserAgent(request);

        if (log.isDebugEnabled()){
            log.debug("the user-agent:[{}]", userAgent);
        }

        this.isIpad = StringUtil.isContainIgnoreCase(userAgent, "ipad");
        this.isIphone = StringUtil.isContainIgnoreCase(userAgent, "iphone");

        this.isMobileDevice = BrowserUtil.getIsMobileDevice(userAgent);
    }

    /**
     * Gets the userAgent.
     * 
     * @return the userAgent
     */
    public String getUserAgent(){
        return userAgent;
    }

    /**
     * Gets the 是不是移动设备.
     * 
     * @return the isMobileDevice
     */
    public boolean getIsMobileDevice(){
        return isMobileDevice;
    }

    /**
     * Gets the 是不是 ipad.
     * 
     * @return the isIpad
     */
    public boolean getIsIpad(){
        return isIpad;
    }

    /**
     * Gets the 是不是 isIphone.
     * 
     * @return the isIphone
     */
    public boolean getIsIphone(){
        return isIphone;
    }

    /**
     * Gets the 浏览器版本号.
     * 
     * @return the version
     */
    // public String getVersion(){
    // String version = "";
    // return version;
    // }
}
