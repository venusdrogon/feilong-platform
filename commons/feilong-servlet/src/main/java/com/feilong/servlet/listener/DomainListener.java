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

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.feilong.commons.core.configure.PropertiesUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 初始化配置 监听器.
 * 
 * <h3>关于配置文件</h3>
 * 
 * <blockquote>
 * <p>
 * <ol>
 * <li>读取 web.xml中配置的 domainConfigLocation context-param参数</li>
 * <li>如果没有读取到文件，那么默认读取 domain.properties 默认地址</li>
 * </ol>
 * </p>
 * </blockquote>
 * 
 * 
 * <h3>关于 domain.properties</h3>
 * 
 * <blockquote>
 * 
 * <pre>
 * domain.css={"variableName":"domainCSS","value":"http://rs.feilong.com:8888"}
 * domain.js={"variableName":"domainJS","value":"http://rs.feilong.com:8888"}
 * domain.image={"variableName":"domainImage","value":"http://rs.feilong.com:8888"}
 * domain.resource={"variableName":"domainResource","value":"http://127.0.0.1:6666"}
 * </pre>
 * 
 * 值，如果是 json格式，会自动转换，variableName参数会自动设置到 servletContext作用域中，值是value参数<br>
 * 
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Aug 19, 2013 10:28:05 AM
 */
public final class DomainListener implements ServletContextListener{

    /** The Constant CONFIG_LOCATION_PARAM. */
    private static final String CONFIG_LOCATION_PARAM      = "domainConfigLocation";

    /** 默认的配置地址. */
    private static final String DEFAULT_CONFIGURATION_FILE = "domain.properties";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext servletContext = servletContextEvent.getServletContext();
        initDomain(servletContext);
    }

    /**
     * 初始化二级域名.
     * 
     * @param servletContext
     *            the servlet context
     */
    private void initDomain(ServletContext servletContext){
        String domainConfigLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);

        Map<String, DomainConfig> domainConfigMap = getDomainConfigMap(domainConfigLocation);

        for (Map.Entry<String, DomainConfig> entry : domainConfigMap.entrySet()){
            DomainConfig domainConfig = entry.getValue();

            String variableName = domainConfig.getVariableName();
            if (Validator.isNotNullOrEmpty(variableName)){
                servletContext.setAttribute(variableName, domainConfig.getValue());
            }
        }

        servletContext.log(JsonUtil.format(domainConfigMap));
    }

    /**
     * 将全部的配置转成map key就是properties中的key.
     *
     * @param domainConfigLocation
     *            the domain config location
     * @return the domain config map
     * @since 1.0.9
     */
    private Map<String, DomainConfig> getDomainConfigMap(String domainConfigLocation){
        Properties properties = getDomainProperties(domainConfigLocation);
        return propertiesToMap(properties);
    }

    /**
     * Properties to map.
     *
     * @param properties
     *            the properties
     * @return the map< string, domain config>
     * @since 1.0.9
     */
    private Map<String, DomainConfig> propertiesToMap(Properties properties){
        Map<String, String> map = PropertiesUtil.toMap(properties);
        Map<String, DomainConfig> domainConfigMap = new LinkedHashMap<String, DomainConfig>();

        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            DomainConfig domainConfig = new DomainConfig();
            if (Validator.isNotNullOrEmpty(value)){
                //json
                if (value.startsWith("{")){
                    domainConfig = JsonUtil.toBean(value, DomainConfig.class);
                }else{
                    domainConfig.setValue(value);
                }
            }else{
                //nothing to do
            }
            domainConfigMap.put(key, domainConfig);
        }
        return domainConfigMap;
    }

    /**
     * 获得 domain properties.
     *
     * @param domainConfigLocation
     *            the domain config location
     * @return the domain properties
     * @since 1.0.9
     */
    //TODO 自动识别
    private Properties getDomainProperties(String domainConfigLocation){
        Class<? extends DomainListener> klass = this.getClass();
        Properties domainProperties = PropertiesUtil.getPropertiesWithClassLoader(klass, domainConfigLocation);

        if (null == domainProperties){
            domainProperties = PropertiesUtil.getPropertiesWithClassLoader(klass, DEFAULT_CONFIGURATION_FILE);
        }
        return domainProperties;
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
