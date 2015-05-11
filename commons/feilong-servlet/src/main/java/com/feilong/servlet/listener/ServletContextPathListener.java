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
package com.feilong.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 设置 servlet context path 到 {@link javax.servlet.ServletContext} attribute {@link #APPLICATIONATTRIBUTE_BASE}
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年1月3日 上午5:00:38
 * @since 1.0.9
 */
public class ServletContextPathListener implements ServletContextListener{

    /** The Constant log. */
    private static final Logger log                       = LoggerFactory.getLogger(ServletContextPathListener.class);

    /** base 路径. */
    //TODO 设置 init param
    private static String       APPLICATIONATTRIBUTE_BASE = "base";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext servletContext = sce.getServletContext();

        String contextPath = servletContext.getContextPath();
        servletContext.setAttribute(APPLICATIONATTRIBUTE_BASE, contextPath);

        String msg = "Set servletContext setAttribute: '" + APPLICATIONATTRIBUTE_BASE + "' = [" + contextPath + "]";
        servletContext.log(msg);

        if (log.isInfoEnabled()){
            log.info(msg);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce){
    }
}
