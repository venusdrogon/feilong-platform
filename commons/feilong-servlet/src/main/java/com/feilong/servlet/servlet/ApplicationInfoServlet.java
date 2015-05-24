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
package com.feilong.servlet.servlet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.lang.SystemUtil;
import com.feilong.core.tools.json.JsonUtil;
import com.feilong.servlet.ServletContextUtil;

/**
 * 应用信息（用于监控查看）.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月23日 上午3:51:21
 * @since 1.0.8
 */
public class ApplicationInfoServlet extends HttpServlet{

    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = 672020928153455796L;

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(ApplicationInfoServlet.class);

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException{
        ServletContext servletContext = getServletContext();

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("Charset defaultCharset", Charset.defaultCharset().name());
        map.put("ServletContext Info", ServletContextUtil.getServletContextInfoMapForLog(servletContext));
        map.put("ServletContext initParameter Map", ServletContextUtil.getInitParameterMap(servletContext));
        //		map.put("ServletContext attributeNames", CollectionsUtil.toList(servletContext.getAttributeNames()));
        map.put("ServletContext Attribute String Map", ServletContextUtil.getAttributeStringMapForLog(servletContext));
        map.put("System Env Map", SystemUtil.getEnvMapForLog());
        map.put("System Properties Map", SystemUtil.getPropertiesMapForLog());

        if (log.isInfoEnabled()){
            log.info("ApplicationInfoServlet:{}", JsonUtil.format(map));
        }

        super.init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        super.doGet(req, resp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#getLastModified(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected long getLastModified(HttpServletRequest req){
        return super.getLastModified(req);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        super.doPost(req, resp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        super.service(req, resp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
     */
    @Override
    public void service(ServletRequest req,ServletResponse res) throws ServletException,IOException{
        super.service(req, res);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#destroy()
     */
    @Override
    public void destroy(){
        super.destroy();
    }
}
