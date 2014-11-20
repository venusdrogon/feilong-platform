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
package com.feilong.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.date.DateExtensionUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.io.MimeTypeUtil;
import com.feilong.commons.core.net.URIUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.servlet.http.HttpHeaders;
import com.feilong.servlet.http.RequestUtil;

/**
 * 下载.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-18 下午11:32:05
 */
@Controller
public class DownloadController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DownloadController.class);

	/** The i. */
	private int					i	= 0;

	/**
	 * Download.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             the IO exception
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,HttpServletResponse response) throws IOException{
		i++;
		log.info("access i:{}", i);

		String pathname = null;

		pathname = "D:\\Downloads\\那片海.mp4";
		pathname = "D:\\Downloads\\1.txt";
		pathname = "C:\\Users\\feilong\\feilong\\sitemap.xml";
		 pathname = "C:\\Users\\feilong\\Downloads\\那片海.mp4";
		//pathname = "D:\\Downloads\\viewfile.png";
		//pathname = "D:\\Downloads\\export-飞天奔月.opml";

		//int contentLength = inputStream.available();

		File file = new File(pathname);
		Number contentLength = FileUtil.getFileSize(file);

		// 以流的形式下载文件。
		InputStream inputStream = new FileInputStream(pathname);
		String saveFileName = FileUtil.getFileName(pathname);

		//response.getWriter().write("hahahhaah");

		download(saveFileName, inputStream, contentLength, request, response);
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
	 * @see org.springframework.http.MediaType
	 */
	public void download(
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
	 * @see org.springframework.http.MediaType
	 * @see org.apache.http.HttpHeaders
	 * @see org.springframework.http.HttpHeaders
	 * @see com.feilong.commons.core.io.MimeTypeUtil#getContentTypeByFileName(String)
	 * @see javax.servlet.ServletContext#getMimeType(String)
	 */
	public void download(
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
	 * @throws IOException
	 *             the IO exception
	 */
	private void downLoadData(
					String saveFileName,
					InputStream inputStream,
					Number contentLength,
					HttpServletRequest request,
					HttpServletResponse response) throws IOException{
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
				throw e;
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
	private void setDownloadResponseHeader(
					String saveFileName,
					Number contentLength,
					String contentType,
					String contentDisposition,
					HttpServletResponse response){
		//**********************************************************************************************
		// 清空response
		//getResponse的getWriter()方法连续两次输出流到页面的时候，第二次的流会包括第一次的流，所以可以使用将response.reset或者resetBuffer的方法。
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
				contentType = "application/octet-stream";
				//The HTTP specification recommends setting the Content-Type to application/octet-stream. 
				//Unfortunately, this causes problems with Opera 6 on Windows (which will display the raw bytes for any file whose extension it doesn't recognize) and on Internet Explorer 5.1 on the Mac (which will display inline content that would be downloaded if sent with an unrecognized type).
			}
		}

		//浏览器接收到文件后，会进入插件系统进行查找，查找出哪种插件可以识别读取接收到的文件。如果浏览器不清楚调用哪种插件系统，它可能会告诉用户缺少某插件，
		if (Validator.isNotNullOrEmpty(contentType)){
			response.setContentType(contentType);
		}

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

	// 支持在线打开文件的一种方式
	/**
	 * Down load.
	 *
	 * @param filePath
	 *            the file path
	 * @param response
	 *            the response
	 * @param isOnLine
	 *            the is on line
	 * @throws Exception
	 *             the exception
	 */
	public void downLoad(String filePath,HttpServletResponse response,boolean isOnLine) throws Exception{
		File f = new File(filePath);
		if (!f.exists()){
			response.sendError(404, "File not found!");
			return;
		}
		BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(f));

		response.reset(); // 非常重要
		if (isOnLine){ // 在线打开方式
			URL url = new URL("file:///" + filePath);
			response.setContentType(url.openConnection().getContentType());
			response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
			// 文件名应该编码成UTF-8
		}else{ // 纯下载方式
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
		}

		OutputStream outputStream = response.getOutputStream();

		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = bufferedInputStream.read(buffer)) > 0){
			outputStream.write(buffer, 0, len);
		}
		bufferedInputStream.close();
		outputStream.close();
	}
}