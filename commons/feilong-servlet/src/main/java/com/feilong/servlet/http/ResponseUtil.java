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
package com.feilong.servlet.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.date.DateExtensionUtil;
import com.feilong.core.io.CharsetType;
import com.feilong.core.io.FileUtil;
import com.feilong.core.io.IOWriteUtil;
import com.feilong.core.io.MimeType;
import com.feilong.core.io.MimeTypeUtil;
import com.feilong.core.io.UncheckedIOException;
import com.feilong.core.net.URIUtil;
import com.feilong.core.util.StringUtil;
import com.feilong.core.util.Validator;
import com.feilong.servlet.http.entity.HttpHeaders;

/**
 * {@link javax.servlet.http.HttpServletResponse} 工具类.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-3 下午02:26:14
 * @see javax.servlet.http.HttpServletResponse
 * @since 1.0.0
 */
public final class ResponseUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ResponseUtil.class);

    /** Don't let anyone instantiate this class. */
    private ResponseUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 下载(以 contentType=application/force-download) 强制下载.
     *
     * @param saveFileName
     *            保存文件的文件名,将会被设置到 Content-Disposition header 中
     * @param inputStream
     *            保存数据输入流
     * @param contentLength
     *            如果是网络流就需要自己来取到大小了
     * @param request
     *            用来 获取request相关信息 记录log
     * @param response
     *            response
     * @throws IOException
     *             the IO exception
     * @see IOWriteUtil#write(InputStream, OutputStream)
     * @see "org.springframework.http.MediaType"
     */
    public static void download(
                    String saveFileName,
                    InputStream inputStream,
                    Number contentLength,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException{
        //均采用默认的
        String contentType = null;
        String contentDisposition = null;
        download(saveFileName, inputStream, contentLength, contentType, contentDisposition, request, response);
    }

    /**
     * 下载.
     *
     * @param saveFileName
     *            保存文件的文件名,将会被设置到 Content-Disposition header 中
     * @param inputStream
     *            保存数据输入流
     * @param contentLength
     *            如果是网络流就需要自己来取到大小了
     * @param contentType
     *            the content type
     * @param contentDisposition
     *            the content disposition
     * @param request
     *            用来 获取request相关信息 记录log
     * @param response
     *            response
     * @throws IOException
     *             the IO exception
     * @see IOWriteUtil#write(InputStream, OutputStream)
     * @see "org.springframework.http.MediaType"
     * @see "org.apache.http.HttpHeaders"
     * @see "org.springframework.http.HttpHeaders"
     * @see com.feilong.core.io.MimeTypeUtil#getContentTypeByFileName(String)
     * @see javax.servlet.ServletContext#getMimeType(String)
     */
    public static void download(
                    String saveFileName,
                    InputStream inputStream,
                    Number contentLength,
                    String contentType,
                    String contentDisposition,
                    HttpServletRequest request,
                    HttpServletResponse response) throws IOException{

        setDownloadResponseHeader(saveFileName, contentLength, contentType, contentDisposition, response);

        //**********************************下载数据********************************************************************
        downLoadData(saveFileName, inputStream, contentLength, request, response);
    }

    /**
     * Down load data.
     *
     * @param saveFileName
     *            the save file name
     * @param inputStream
     *            the input stream
     * @param contentLength
     *            the content length
     * @param request
     *            the request
     * @param response
     *            the response
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    private static void downLoadData(
                    String saveFileName,
                    InputStream inputStream,
                    Number contentLength,
                    HttpServletRequest request,
                    HttpServletResponse response) throws UncheckedIOException{
        Date beginDate = new Date();

        if (log.isInfoEnabled()){
            log.info("begin download~~,saveFileName:[{}],contentLength:[{}]", saveFileName, FileUtil.formatSize(contentLength.longValue()));
        }
        try{
            OutputStream outputStream = response.getOutputStream();

            //这种 如果文件一大，很容易内存溢出
            //inputStream.read(buffer);
            //outputStream = new BufferedOutputStream(response.getOutputStream());
            //outputStream.write(buffer);

            IOWriteUtil.write(inputStream, outputStream);
            if (log.isInfoEnabled()){
                Date endDate = new Date();
                log.info(
                                "end download,saveFileName:[{}],contentLength:[{}],time use:[{}]",
                                saveFileName,
                                FileUtil.formatSize(contentLength.longValue()),
                                DateExtensionUtil.getIntervalForView(beginDate, endDate));
            }
        }catch (IOException e){
            /*
             * 在写数据的时候， 对于 ClientAbortException 之类的异常， 是因为客户端取消了下载，而服务器端继续向浏览器写入数据时， 抛出这个异常，这个是正常的。
             * 尤其是对于迅雷这种吸血的客户端软件， 明明已经有一个线程在读取
             * 如果短时间内没有读取完毕，迅雷会再启第二个、第三个。。。线程来读取相同的字节段，
             * 直到有一个线程读取完毕，迅雷会 KILL掉其他正在下载同一字节段的线程， 强行中止字节读出，造成服务器抛 ClientAbortException。
             */
            //ClientAbortException:  java.net.SocketException: Connection reset by peer: socket write error
            final String exceptionName = e.getClass().getName();

            if (StringUtil.isContain(exceptionName, "ClientAbortException") || StringUtil.isContain(e.getMessage(), "ClientAbortException")){
                log.warn(
                                "[ClientAbortException],maybe user use Thunder soft or abort client soft download,exceptionName:[{}],exception message:[{}] ,request User-Agent:[{}]",
                                exceptionName,
                                e.getMessage(),
                                RequestUtil.getHeaderUserAgent(request));
            }else{
                log.error("[download exception],exception name: " + exceptionName, e);
                throw new UncheckedIOException(e);
            }
        }
    }

    /**
     * 设置 download response header.
     *
     * @param saveFileName
     *            the save file name
     * @param contentLength
     *            the content length
     * @param contentType
     *            the content type
     * @param contentDisposition
     *            the content disposition
     * @param response
     *            the response
     */
    private static void setDownloadResponseHeader(
                    String saveFileName,
                    Number contentLength,
                    String contentType,
                    String contentDisposition,
                    HttpServletResponse response){
        //**********************************************************************************************
        // 清空response
        //getResponse的getWriter()方法连续两次输出流到页面的时候，第二次的流会包括第一次的流，所以可以使用将response.reset或者resetBuffer的方法。
        //getOutputStream() has already been called for this response问题的解决
        //在jsp向页面输出图片的时候,使用response.getOutputStream()会有这样的提示：java.lang.IllegalStateException:getOutputStream() has already been called for this response,会抛出Exception
        response.reset();

        // ===================== Default MIME Type Mappings =================== -->
        //See tomcat web.xml
        //When serving static resources, Tomcat will automatically generate a "Content-Type" header based on the resource's filename extension, based on these mappings.  
        //Additional mappings can be added here (to apply to all web applications), or in your own application's web.xml deployment descriptor.                                               -->

        if (Validator.isNullOrEmpty(contentType)){
            contentType = MimeTypeUtil.getContentTypeByFileName(saveFileName);

            if (Validator.isNullOrEmpty(contentType)){
                //contentType = "application/force-download";//,php强制下载application/force-download,将发送HTTP 标头您的浏览器并告诉它下载，而不是在浏览器中运行的文件
                //application/x-download

                //.*（ 二进制流，不知道下载文件类型）	application/octet-stream
                contentType = MimeType.BIN.getMime();
                //The HTTP specification recommends setting the Content-Type to application/octet-stream. 
                //Unfortunately, this causes problems with Opera 6 on Windows (which will display the raw bytes for any file whose extension it doesn't recognize) and on Internet Explorer 5.1 on the Mac (which will display inline content that would be downloaded if sent with an unrecognized type).
            }
        }

        //浏览器接收到文件后，会进入插件系统进行查找，查找出哪种插件可以识别读取接收到的文件。如果浏览器不清楚调用哪种插件系统，它可能会告诉用户缺少某插件，
        if (Validator.isNotNullOrEmpty(contentType)){
            response.setContentType(contentType);
        }

        //缺省情况下:服务端要输出到客户端的内容,不直接写到客户端,而是先写到一个输出缓冲区中.只有在下面三中情况下，才会把该缓冲区的内容输出到客户端上： 
        //缓冲区的优点是：我们暂时不输出，直到确定某一情况时，才将写入缓冲区的数据输出到浏览器，否则就将缓冲区的数据取消。
        //response.setBufferSize(10240);

        //see org.apache.commons.io.IOUtils.copyLarge(InputStream, OutputStream) javadoc
        //This method buffers the input internally, so there is no need to use a BufferedInputStream

        //****************************************************************************************************

        //Content-Disposition takes one of two values, `inline' and  `attachment'.  
        //'Inline' indicates that the entity should be immediately displayed to the user, 
        //whereas `attachment' means that the user should take additional action to view the entity.
        //The `filename' parameter can be used to suggest a filename for storing the bodypart, if the user wishes to store it in an external file.
        if (Validator.isNullOrEmpty(contentDisposition)){
            //默认 附件形式
            contentDisposition = "attachment; filename=" + URIUtil.encode(saveFileName, CharsetType.UTF8);

        }
        //TODO 看看能否调用 httpcomponents的 httpcore  org.apache.http.HttpHeaders
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);

        response.setContentLength(contentLength.intValue());
    }

    /**
     * 设置不缓存并跳转.
     *
     * @param response
     *            HttpServletResponse
     * @param url
     *            跳转路径
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static void setNoCacheAndRedirect(HttpServletResponse response,String url) throws UncheckedIOException{
        setNoCacheHeader(response);
        try{
            response.sendRedirect(url);
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 设置页面不缓存.
     *
     * @param response
     *            HttpServletResponse
     */
    public static void setNoCacheHeader(HttpServletResponse response){
        // 当HTTP1.1服务器指定 CacheControl = no-cache时，浏览器就不会缓存该网页。
        // 旧式 HTTP1.0 服务器不能使用 Cache-Control 标题

        // 为了向后兼容 HTTP1.0 服务器，IE使用Pragma:no-cache 标题对 HTTP提供特殊支持
        // 如果客户端通过安全连接 (https://)/与服务器通讯，且服务器响应中返回 Pragma:no-cache 标题，则 Internet Explorer不会缓存此响应。
        // 注意：Pragma:no-cache 仅当在安全连接中使用时才防止缓存，如果在非安全页中使用，处理方式与 Expires:-1相同，该页将被缓存，但被标记为立即过期
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");

        // Cache-control值为“no-cache”时，访问此页面不会在Internet临时文章夹留下页面备份。
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");

        //In other words Expires: 0 not always leads to immediate resource expiration, 
        //therefore should be avoided and Expires: -1 or Expires: [some valid date in the past] should be used instead.
        response.setDateHeader(HttpHeaders.EXPIRES, -1);
    }

    //	 [start] PrintWriter

    /**
     * 以json的方式输出.
     *
     * @param response
     *            HttpServletResponse
     * @param json
     *            json字符串
     * @see #write(HttpServletResponse, Object, String, String)
     * @since 1.0.9
     */
    public static void writeJson(HttpServletResponse response,Object json){
        writeJson(response, json, CharsetType.UTF8);
    }

    /**
     * 以json的方式输出.
     *
     * @param response
     *            HttpServletResponse
     * @param json
     *            json字符串
     * @param characterEncoding
     *            the character encoding
     * @see #write(HttpServletResponse, Object, String, String)
     * @since 1.0.9
     */
    public static void writeJson(HttpServletResponse response,Object json,String characterEncoding){
        String contentType = MimeType.JSON.getMime() + ";charset=" + characterEncoding;
        write(response, json, contentType, characterEncoding);
    }

    /**
     * 输出.
     *
     * @param response
     *            HttpServletResponse
     * @param content
     *            相应内容
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see javax.servlet.ServletResponse#getWriter()
     * @see java.io.PrintWriter#print(Object)
     * @see java.io.PrintWriter#flush()
     * @see #write(HttpServletResponse, Object, String, String)
     */
    public static void write(HttpServletResponse response,Object content) throws UncheckedIOException{
        String contentType = null;
        String characterEncoding = null;
        write(response, content, contentType, characterEncoding);
    }

    /**
     * 输出.
     *
     * @param response
     *            HttpServletResponse
     * @param content
     *            相应内容
     * @param contentType
     *            the content type
     * @param characterEncoding
     *            the character encoding
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see javax.servlet.ServletResponse#getWriter()
     * @see java.io.PrintWriter#print(Object)
     * @see java.io.PrintWriter#flush()
     * @since 1.0.9
     */
    public static void write(HttpServletResponse response,Object content,String contentType,String characterEncoding)
                    throws UncheckedIOException{
        try{
            //编码 需要在 getWriter之前设置
            if (Validator.isNotNullOrEmpty(contentType)){
                response.setContentType(contentType);
            }
            if (Validator.isNotNullOrEmpty(characterEncoding)){
                response.setCharacterEncoding(characterEncoding);
            }

            PrintWriter printWriter = response.getWriter();
            printWriter.print(content);
            printWriter.flush();

            //http://www.iteye.com/problems/56543
            //你是用了tomcat，jetty这样的容器，就不需要 printWriter.close();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    // [end]
}
