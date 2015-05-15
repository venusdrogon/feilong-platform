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

package com.feilong.spring.web.servlet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.feilong.web.browser.Browser;

/**
 * 浏览器拦截器<br/>
 * 拦截每个请求 ,拿到UA,封装到 browser,你可以 通过此类来判断 访问方式是否是移动设备.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2013-3-25 15:18
 * @deprecated 最好考虑在session开始的那刻 实用
 */
@Deprecated
public class BrowserInterceptor extends HandlerInterceptorAdapter{

    /** 默认的 变量名称 <code>{@value}</code>. */
    public static final String DEFAULT_BROWSER_REQUEST_NAME = "feilongBrowser";

    /** 请求参数,可以通过spring注入的方式 修改. */
    private String             attributeName                = DEFAULT_BROWSER_REQUEST_NAME;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        Browser browser = new Browser(request);

        request.setAttribute(attributeName, browser);
        return true;
    }

    /**
     * Sets the 请求参数.
     * 
     * @param attributeName
     *            the attributeName to set
     */
    public void setAttributeName(String attributeName){
        this.attributeName = attributeName;
    }
}
