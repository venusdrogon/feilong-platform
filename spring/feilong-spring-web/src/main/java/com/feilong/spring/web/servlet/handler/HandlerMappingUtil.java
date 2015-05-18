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
package com.feilong.spring.web.servlet.handler;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.spring.web.util.WebSpringUtil;

/**
 * {@link org.springframework.web.servlet.HandlerMapping HandlerMapping} 帮助 {@link org.springframework.web.servlet.DispatcherServlet
 * DispatcherServlet}进行Web请求的URL到具体处理类的匹配.
 * 
 * <h3>之所以称为{@link org.springframework.web.servlet.HandlerMapping HandlerMapping}:</h3>
 * 
 * <blockquote>
 * <p>
 * 是因为，在Spring MVC中，并不只局限于使用 {@link org.springframework.web.servlet.mvc.Controller Controller} 作为DispatcherServlet的次级控制器来进行具体的Web请求的处理。
 * </p>
 * <p>
 * 实际上，在稍后介绍 {@link org.springframework.web.servlet.HandlerAdapter HandlerAdapter}的时候，你就会了解到，我们也可以使用其他类型的次级控制器，包括Spring MVC提供的除了
 * {@link org.springframework.web.servlet.mvc.Controller Controller} 之外的次级控制器类型，或者第三方Web开发框架中的Page
 * Controller组件（如Struts的Action），而所有这些次级控制器类型，在Spring MVC中都称作Handler，
 * </p>
 * <p>
 * 我想这就是{@link org.springframework.web.servlet.HandlerMapping HandlerMapping}这一名称的由来了 。<br>
 * {@link org.springframework.web.servlet.HandlerMapping HandlerMapping} 要处理的也就是Web请求到相应Handler之间的映射关系。<br>
 * 如果你接触过Struts框架的话，可以将{@link org.springframework.web.servlet.HandlerMapping HandlerMapping}与Struts框架的ActionMapping概念进行类比 。只不过
 * {@link org.springframework.web.servlet.HandlerMapping HandlerMapping}的职责更加明确，使用上也更加灵活。
 * </p>
 * </blockquote>
 * 
 * 
 * <h3>Spring MVC默认提供了多个HandlerMapping的实现供我们选用:</h3>
 * 
 * <blockquote>
 * <ul>
 * <li>{@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping}</li>
 * <li>{@link org.springframework.web.servlet.mvc.support.ControllerBeanNameHandlerMapping}</li>
 * <li>{@link org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping}</li>
 * <li>{@link org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping}</li>
 * <li>{@link org.springframework.web.servlet.handler.SimpleUrlHandlerMapping}</li>
 * </ul>
 * </blockquote>
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.1.2 2015年5月15日 下午9:56:33
 * @since 1.1.2
 * @see org.springframework.web.servlet.HandlerMapping
 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping
 * @see org.springframework.web.servlet.handler.AbstractUrlHandlerMapping
 * 
 * @see org.springframework.web.servlet.HandlerExecutionChain
 * 
 * @see org.springframework.web.servlet.HandlerAdapter
 * 
 * @see org.springframework.web.bind.annotation.RequestMapping
 * @see org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping#handleMatch(RequestMappingInfo, String,
 *      HttpServletRequest)
 * 
 * @see org.springframework.web.servlet.HandlerMapping#PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE
 * @see org.springframework.web.servlet.HandlerMapping#BEST_MATCHING_PATTERN_ATTRIBUTE
 * @see org.springframework.web.servlet.HandlerMapping#URI_TEMPLATE_VARIABLES_ATTRIBUTE
 * @see org.springframework.web.servlet.HandlerMapping#MATRIX_VARIABLES_ATTRIBUTE
 * @see org.springframework.web.servlet.HandlerMapping#PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE
 * 
 * @see org.springframework.web.servlet.HandlerMapping#getHandler(HttpServletRequest)
 * @see org.springframework.web.servlet.handler.AbstractHandlerMapping#getHandler(HttpServletRequest)
 * 
 * @see org.springframework.web.servlet.handler.AbstractHandlerMethodMapping#lookupHandlerMethod(String, HttpServletRequest)
 */
public class HandlerMappingUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(HandlerMappingUtil.class);

    /**
     * 获得 request mapping handler mapping info map for log.
     *
     * @param webApplicationContext
     *            the web application context
     * @return the request mapping handler mapping info map for log
     */
    public final static Map<String, Object> getRequestMappingHandlerMappingInfoMapForLog(WebApplicationContext webApplicationContext){
        RequestMappingHandlerMapping requestMappingHandlerMapping = WebSpringUtil.getBean(
                        webApplicationContext,
                        RequestMappingHandlerMapping.class);

        Map<String, Object> object = new LinkedHashMap<String, Object>();

        object.put("useRegisteredSuffixPatternMatch()", requestMappingHandlerMapping.useRegisteredSuffixPatternMatch());
        object.put("useSuffixPatternMatch()", requestMappingHandlerMapping.useSuffixPatternMatch());
        object.put("useTrailingSlashMatch()", requestMappingHandlerMapping.useTrailingSlashMatch());
        object.put("getDefaultHandler()", requestMappingHandlerMapping.getDefaultHandler());
        object.put("getFileExtensions()", requestMappingHandlerMapping.getFileExtensions());
        object.put("getOrder()", requestMappingHandlerMapping.getOrder());
        object.put("getPathMatcher()", requestMappingHandlerMapping.getPathMatcher());
        object.put("getUrlPathHelper()", requestMappingHandlerMapping.getUrlPathHelper());

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        Map<RequestMappingInfo, String> map = new LinkedHashMap<RequestMappingInfo, String>();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()){
            RequestMappingInfo requestMappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();

            map.put(requestMappingInfo, handlerMethod.toString());

            PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
            Set<String> patterns = patternsCondition.getPatterns();

            if (log.isDebugEnabled()){
                log.debug(JsonUtil.format(patterns));
            }

            if (log.isDebugEnabled()){
                log.debug(
                                "RequestMappingInfo:[{}],HandlerMethod:[{}],[{}],requestMappingInfo:{}",
                                JsonUtil.format(requestMappingInfo),
                                handlerMethod,
                                handlerMethod.getClass(),
                                requestMappingInfo);
            }
        }
        object.put("handlerMethods", map);
        return object;
    }
}
