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
package com.feilong.spring.context;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

/**
 * The Class ApplicationContextUtil.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月29日 下午7:38:13
 * @since 1.0.8
 */
public final class ApplicationContextUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ApplicationContextUtil.class);

    /**
     * 获得 application context for log map.
     *
     * @param applicationContext
     *            the application context
     * @return the application context for log map
     */
    public static Map<String, Object> getApplicationContextForLogMap(ApplicationContext applicationContext){
        Map<String, Object> applicationContextForLogMap = new LinkedHashMap<String, Object>();

        applicationContextForLogMap.put("applicationContext.getBeanDefinitionCount()", applicationContext.getBeanDefinitionCount());

        applicationContextForLogMap.put("applicationContext.getApplicationName()", applicationContext.getApplicationName());
        applicationContextForLogMap.put("applicationContext.getDisplayName()", applicationContext.getDisplayName());

        applicationContextForLogMap.put("applicationContext.getId()", applicationContext.getId());
        applicationContextForLogMap.put("applicationContext.getStartupDate()", applicationContext.getStartupDate());

        applicationContextForLogMap.put("ApplicationContext.CLASSPATH_ALL_URL_PREFIX", ApplicationContext.CLASSPATH_ALL_URL_PREFIX);
        applicationContextForLogMap.put("ApplicationContext.CLASSPATH_URL_PREFIX", ApplicationContext.CLASSPATH_URL_PREFIX);
        applicationContextForLogMap.put("ApplicationContext.FACTORY_BEAN_PREFIX", ApplicationContext.FACTORY_BEAN_PREFIX);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);

        //applicationContextForLogMap.put("applicationContext.getBeanDefinitionNames()", beanDefinitionNames);

        Map<String, Object> beanDefinitionNamesAndClassMap = new LinkedHashMap<String, Object>();
        for (String beanDefinitionName : beanDefinitionNames){
            try{
                Object bean = applicationContext.getBean(beanDefinitionName);
                String canonicalName = bean.getClass().getCanonicalName();
                Object vObject = canonicalName
                                + (applicationContext.isPrototype(beanDefinitionName) ? "[Prototype]" : (applicationContext
                                                .isSingleton(beanDefinitionName) ? "[Singleton]" : ""));
                beanDefinitionNamesAndClassMap.put(beanDefinitionName, vObject);
            }catch (BeansException e){
                beanDefinitionNamesAndClassMap.put(beanDefinitionName, e.getMessage());
            }
        }

        applicationContextForLogMap.put("beanDefinitionNamesAndClassMap", beanDefinitionNamesAndClassMap);

        Environment environment = applicationContext.getEnvironment();
        applicationContextForLogMap.put("applicationContext.getEnvironment()", environment);

        return applicationContextForLogMap;
    }
}
