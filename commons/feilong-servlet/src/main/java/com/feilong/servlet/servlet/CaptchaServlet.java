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

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.ValidateCodeUtil;
import com.feilong.commons.core.io.ImageType;
import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.servlet.http.RequestUtil;
import com.feilong.servlet.http.ResponseUtil;

/**
 * 验证码.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 14, 2013 10:58:37 PM
 */
public class CaptchaServlet extends HttpServlet{

    /** The Constant serialVersionUID. */
    private static final long   serialVersionUID = -5495378311081182425L;

    /** The Constant log. */
    private static final Logger log              = LoggerFactory.getLogger(CaptchaServlet.class);

    /** 验证码<code>{@value}</code>. */
    private static String       VALIDATE_CODE    = "feilong.validateCode";

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#getLastModified(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected long getLastModified(HttpServletRequest request){
        return super.getLastModified(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        // 设置页面不缓存
        ResponseUtil.setNoCacheHeader(response);
        // 保存生成的汉字字符串
        String validateCode = ValidateCodeUtil.generateValidateCode(4);

        String ip = RequestUtil.getClientIp(request);
        log.debug("session setAttribute SECURITY_CODE:[{}],ip:[{}]", validateCode, ip);

        // 将认证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(VALIDATE_CODE, validateCode);

        BufferedImage bufferedImage = ValidateCodeUtil.getBufferedImageAfterGraphics(validateCode, 88, 25);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        try{
            ImageIO.write(bufferedImage, ImageType.PNG, servletOutputStream);
            servletOutputStream.flush();
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
            servletOutputStream.close();
        }
    }

    /**
     * Write image.
     *
     * @param response
     *            the response
     * @param bufferedImage
     *            the buffered image
     * @param pageContext
     *            the page context
     */
    private void writeImage(HttpServletResponse response,BufferedImage bufferedImage,PageContext pageContext){
        // 输出图象到页面
        try{
            ServletOutputStream servletOutputStream = response.getOutputStream();
            ImageIO.write(bufferedImage, ImageType.PNG, servletOutputStream);
            // ----------------------------------------------------------------------------------------
            servletOutputStream.flush();
            servletOutputStream.close();
            servletOutputStream = null;
            response.flushBuffer();
            // ----------------------------------------------------------------------------------------

            // 你是说下载吧?
            // 由于jsp container在处理完成请求后会调用releasePageContet方法释放所用的PageContext
            // object,并且同时调用getWriter方法,由于getWriter方法与在jsp页面中使用流相关的getOutputStream方法冲突,所以会造成这种异常,解决办法是:只需要在jsp页面的最后加上两条语句: out.clear();
            // out=pageContext.pushBody();即可(其中out,pageContext均为jsp内置对象!)

            // JspWriter jspWriter = response.getWriter();
            // jspWriter.clear();
            // jspWriter = pageContext.pushBody();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        service(request, response);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        service(request, response);
    }
}
