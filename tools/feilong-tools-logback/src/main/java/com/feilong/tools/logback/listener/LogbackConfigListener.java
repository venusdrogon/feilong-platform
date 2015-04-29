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
package com.feilong.tools.logback.listener;

import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.ResourceUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

import com.feilong.commons.core.util.Validator;

/**
 * <p style="color:red">
 * 注意:该listener要写在所有 listener 最前面
 * </p>
 * 
 * <h3>有了 {@link ch.qos.logback.ext.spring.web.LogbackConfigListener},为什么还要再建一个LogbackConfigListener类呢?</h3>
 * 
 * <blockquote>
 * <p>
 * 有以下几点理由:
 * 
 * <ol>
 * <li>目前的{@link ch.qos.logback.ext.spring.web.LogbackConfigListener}解析 配置参数
 * {@link ch.qos.logback.ext.spring.web.WebLogbackConfigurer#CONFIG_LOCATION_PARAM} 使用的是
 * {@link org.springframework.util.SystemPropertyUtils#resolvePlaceholders(String)} ，这么一来 ，如果配置成
 * <code>classpath:config/${spring.profiles.active}/logback.xml</code> 那么就解析不了 .参考我在github官方提的bug，
 * {@link "https://github.com/qos-ch/logback-extensions/issues/31"},如果这个bug更新之后，可以切换到官方上去</li>
 * <li>官方提供的{@link ch.qos.logback.ext.spring.web.LogbackConfigListener}，其实是在logback已经初始化一遍之后，调用了
 * {@link ch.qos.logback.ext.spring.LogbackConfigurer#initLogging(String)} 重置的,而这个
 * {@link com.feilong.tools.logback.listener.LogbackConfigListener}只会初始化一次logback,具体可以参考
 * {@link <a href="http://feitianbenyue.iteye.com/blog/2205482">【飞天奔月出品】剖析logback1:logback1.1.13配置文件加载顺序</a>}</li>
 * </ol>
 * 
 * </p>
 * </blockquote>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年4月28日 下午10:13:34
 * @see ch.qos.logback.ext.spring.web.LogbackConfigListener
 * @see ch.qos.logback.ext.spring.web.WebLogbackConfigurer
 * @see org.springframework.web.util.ServletContextPropertyUtils#resolvePlaceholders(String, ServletContext)
 * @see org.springframework.util.ResourceUtils#isUrl(String)
 * @see org.springframework.web.util.WebUtils#getRealPath(ServletContext, String)
 * @see "https://github.com/qos-ch/logback-extensions/issues/31"
 * @see <a href="http://feitianbenyue.iteye.com/blog/2205482">【飞天奔月出品】剖析logback1:logback1.1.13配置文件加载顺序</a>
 * @since 1.1.2
 * @since spring 3.2.2
 */
public class LogbackConfigListener implements ServletContextListener{

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce){

        ServletContext servletContext = sce.getServletContext();

        String location = servletContext.getInitParameter(ch.qos.logback.ext.spring.web.WebLogbackConfigurer.CONFIG_LOCATION_PARAM);

        if (Validator.isNotNullOrEmpty(location)){
            try{
                location = resolveLocation(servletContext, location);

                servletContext.log("System.setProperty [" + ch.qos.logback.classic.util.ContextInitializer.CONFIG_FILE_PROPERTY
                                + "] Logback from [" + location + "]");
                System.setProperty(ch.qos.logback.classic.util.ContextInitializer.CONFIG_FILE_PROPERTY, location);

                //ch.qos.logback.ext.spring.LogbackConfigurer.initLogging(location);
            }catch (FileNotFoundException ex){
                throw new LogbackInitializationException("Invalid '"
                                + ch.qos.logback.ext.spring.web.WebLogbackConfigurer.CONFIG_LOCATION_PARAM + "' parameter", ex);
            }

            //            catch (JoranException e){
            //                throw new LogbackInitializationException("Unexpected error while configuring logback", e);
            //            }
        }else{
            String message = "servletContext initParameter:[" + ch.qos.logback.ext.spring.web.WebLogbackConfigurer.CONFIG_LOCATION_PARAM
                            + "] value is null or empty";
            servletContext.log(message, new LogbackInitializationException(message));
        }
    }

    /**
     * 解析配置的路径.
     *
     * @param servletContext
     *            the servlet context
     * @param location
     *            the location
     * @return the string
     * @throws FileNotFoundException
     *             the file not found exception
     * @since 1.1.2
     */
    protected String resolveLocation(ServletContext servletContext,String location) throws FileNotFoundException{
        // Resolve ServletContextProperty placeholders before potentially resolving real path.
        location = ServletContextPropertyUtils.resolvePlaceholders(location, servletContext);

        // Return a URL (e.g. "classpath:" or "file:") as-is;
        // consider a plain file path as relative to the web application root directory.
        if (!ResourceUtils.isUrl(location)){
            location = WebUtils.getRealPath(servletContext, location);
        }
        return location;
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
