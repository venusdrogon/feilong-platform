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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * The Class ClientCacheInterceptor.
 */
public class ClientCacheInterceptor extends HandlerInterceptorAdapter implements ServletContextAware{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ClientCacheInterceptor.class);

    /** The servlet context. */
    private ServletContext      servletContext;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        if (handler instanceof HandlerMethod){
            HandlerMethod method = (HandlerMethod) handler;
            ClientCache clientCache = method.getMethodAnnotation(ClientCache.class);
            if (clientCache != null){

                long value = clientCache.value();

                if (value <= 0){
                    response.addHeader("Pragma", "no-cache");
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expires", 0);

                }else{
                    response.setHeader("Cache-Control", "max-age=" + value);
                }
            }
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
     */
    @Override
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }
}