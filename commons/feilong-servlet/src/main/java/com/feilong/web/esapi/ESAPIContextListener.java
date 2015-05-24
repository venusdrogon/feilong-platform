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
package com.feilong.web.esapi;

import java.io.File;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.lang.ClassLoaderUtil;

/**
 * 专门给esapi 设置的监听器.
 * 
 * <h3>使用步骤:</h3>
 * 
 * <ul>
 * <li>1.只需要将 esapi 目录copy 到项目中classpath下即可，</li>
 * <li>2.web.xml 配置
 * 
 * <pre>
 * {@code
 *  <listener>
 *      <listener-class>com.feilong.web.esapi.ESAPIContextListener</listener-class>
 *  </listener>
 * }
 * </pre>
 * 
 * </li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 9, 2012 5:16:58 PM
 */
public class ESAPIContextListener implements ServletContextListener{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ESAPIContextListener.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext servletContext = servletContextEvent.getServletContext();

        try{
            // 取到classes/esapi 绝对地址,
            // 此处不能直接使用ClassLoaderUtil.getResource("esapi") 做org.owasp.esapi.resources 参数值
            // 否则 后面拼接的路径 会将 file:/ 纯粹当 文件路径一部分拼接
            File esapiDirectory = new File(ClassLoaderUtil.getResource("esapi").toURI());
            String customDirectory = esapiDirectory.getAbsolutePath();

            File file = new File(customDirectory, "ESAPI.properties");

            servletContext.log("ESAPI.properties getAbsolutePath():" + file.getAbsolutePath());
            servletContext.log("customDirectory != null && file.canRead():" + (customDirectory != null && file.canRead()) + "");

            // 设置值 以便esapi使用
            System.setProperty("org.owasp.esapi.resources", customDirectory);
            servletContext.log("set setProperty org.owasp.esapi.resources:" + customDirectory);
        }catch (URISyntaxException e){
            log.error(e.getClass().getName(), e);
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
