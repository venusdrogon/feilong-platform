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
package com.feilong.taglib.spring.base;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.feilong.commons.core.io.UncheckedIOException;

/**
 * 自定义标签的父类,需要和spring控制的业务层交互的请使用这个基类.
 *
 * @author 金鑫 时间:2009年10月28日 10:50:06
 */
public class BaseSpringTag extends RequestContextAwareTag{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5289127954140428690L;

    /**
     * 标签开始.
     *
     * @return the int
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    @Override
    public int doStartTagInternal() throws UncheckedIOException{
        JspWriter jspWriter = pageContext.getOut();// 重要
        try{
            jspWriter.println(this.writeContent());
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
        return SKIP_BODY;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
     */
    @Override
    public int doEndTag(){
        return EVAL_PAGE;// 处理标签后，继续处理JSP后面的内容
    }

    /**
     * 获得spring管理的实体bean.
     *
     * @author 金鑫
     * @version 1.0 时间:2009-10-28上午11:04:17
     * @param <T>
     *            the generic type
     * @param beanName
     *            实体bean名称
     * @return the bean
     */
    @SuppressWarnings("unchecked")
    protected <T> T getBean(String beanName){
        WebApplicationContext webApplicationContext = this.getRequestContext().getWebApplicationContext();
        return (T) webApplicationContext.getBean(beanName);
    }

    /**
     * 显示.
     *
     * @author 金鑫
     * @version 1.0 2010-3-31 上午11:13:45
     * @return the object
     */
    protected Object writeContent(){
        return "";
    }

    // [start] 公用方法
    /**
     * 获得HttpServletRequest.
     *
     * @author 金鑫
     * @version 1.0 2010-2-3 下午01:59:09
     * @return the http servlet request
     */
    protected HttpServletRequest getHttpServletRequest(){
        return (HttpServletRequest) getServletRequest();
    }

    /**
     * 获得ServletRequest.
     *
     * @author 金鑫
     * @version 1.0 2010-2-3 下午01:58:55
     * @return the servlet request
     */
    protected ServletRequest getServletRequest(){
        return this.pageContext.getRequest();
    }

    /**
     * 获得 HttpSession.
     *
     * @author 金鑫
     * @version 1.0 2010-3-18 上午11:04:27
     * @return HttpSession
     */
    protected HttpSession getHttpSession(){
        return this.pageContext.getSession();
    }

    /**
     * 获得HttpServletResponse.
     *
     * @author 金鑫
     * @version 1.0 2010-3-15 下午06:25:18
     * @return the http servlet response
     */
    protected HttpServletResponse getHttpServletResponse(){
        return (HttpServletResponse) pageContext.getResponse();
    }
    // [end]
}