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
package com.feilong.web.domain;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.configure.PropertiesUtil;
import com.feilong.core.date.DateExtensionUtil;
import com.feilong.core.log.Slf4jUtil;
import com.feilong.core.tools.json.JsonUtil;
import com.feilong.core.util.Validator;

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
public class DomainListener implements ServletContextListener{

    /** The Constant log. */
    private static final Logger log                        = LoggerFactory.getLogger(DomainListener.class);

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

        Date beginDate = new Date();

        ServletContext servletContext = servletContextEvent.getServletContext();
        initDomain(servletContext);

        Date endDate = new Date();

        String message = Slf4jUtil.formatMessage(
                        "Domain Listener Initialized time:{}",
                        DateExtensionUtil.getIntervalForView(beginDate, endDate));

        if (log.isInfoEnabled()){
            log.info(message);
        }
    }

    /**
     * 初始化二级域名.
     * 
     * @param servletContext
     *            the servlet context
     */
    private void initDomain(ServletContext servletContext){
        String domainConfigLocation = getDomainConfigLocation(servletContext);
        Properties properties = loadDomainProperties(servletContext, domainConfigLocation);
        Map<String, DomainConfig> domainConfigMap = propertiesToMap(servletContext, properties);

        setServletContextAttribute(servletContext, domainConfigMap);

        if (log.isInfoEnabled()){
            log.info("domain config info:{}", JsonUtil.format(domainConfigMap));
        }
    }

    /**
     * 获得 domain properties.
     *
     * @param servletContext
     *            the servlet context
     * @param domainConfigLocation
     *            the domain config location
     * @return the domain properties
     * @see com.feilong.core.configure.PropertiesUtil#getPropertiesWithClassLoader(Class, String)
     * @since 1.0.9
     */
    //TODO 自动识别
    protected Properties loadDomainProperties(@SuppressWarnings("unused") ServletContext servletContext,String domainConfigLocation){
        if (Validator.isNullOrEmpty(domainConfigLocation)){
            domainConfigLocation = DEFAULT_CONFIGURATION_FILE;
        }
        Class<? extends DomainListener> klass = this.getClass();
        Properties domainProperties = PropertiesUtil.getPropertiesWithClassLoader(klass, domainConfigLocation);
        return domainProperties;
    }

    /**
     * 获得 domain config location.
     *
     * @param servletContext
     *            the servlet context
     * @return the domain config location
     * @since 1.1.1
     */
    protected String getDomainConfigLocation(ServletContext servletContext){
        String domainConfigLocation = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        return domainConfigLocation;
    }

    /**
     * 设置 servlet context attribute.
     *
     * @param servletContext
     *            the servlet context
     * @param domainConfigMap
     *            the domain config map
     * @since 1.1.1
     */
    private void setServletContextAttribute(ServletContext servletContext,Map<String, DomainConfig> domainConfigMap){
        for (Map.Entry<String, DomainConfig> entry : domainConfigMap.entrySet()){
            DomainConfig domainConfig = entry.getValue();

            String variableName = domainConfig.getVariableName();
            if (Validator.isNotNullOrEmpty(variableName)){
                servletContext.setAttribute(variableName, domainConfig.getValue());
            }
        }
    }

    /**
     * Properties to map 将全部的配置转成map key就是properties中的key.
     *
     * @param servletContext
     *            the servlet context
     * @param properties
     *            the properties
     * @return the map< string, domain config>
     * @since 1.0.9
     */
    private Map<String, DomainConfig> propertiesToMap(ServletContext servletContext,Properties properties){
        Map<String, String> map = PropertiesUtil.toMap(properties);
        Map<String, DomainConfig> domainConfigMap = new LinkedHashMap<String, DomainConfig>();

        //如果 配置的value是空,则使用 contextPath
        String defaultValue = servletContext.getContextPath();

        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();

            DomainConfig domainConfig = new DomainConfig();
            if (Validator.isNotNullOrEmpty(value)){
                //json
                if (value.startsWith("{")){
                    domainConfig = JsonUtil.toBean(value, DomainConfig.class);
                }else{
                    //不是 json 那么直接设置值
                    domainConfig.setValue(value);
                }
            }else{
                //nothing to do
            }
            domainConfig.setKey(key);
            if (Validator.isNullOrEmpty(domainConfig.getValue())){
                log.debug("key:[{}] 's value isNullOrEmpty,use ContextPath:[{}]", key, defaultValue);
                domainConfig.setValue(defaultValue);
            }
            domainConfigMap.put(key, domainConfig);
        }
        return domainConfigMap;
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
