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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.servlet.http.ResponseUtil;

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
		//		pathname = "D:\\Downloads\\1.txt";
		//		pathname = "C:\\Users\\feilong\\feilong\\sitemap.xml";
		pathname = "C:\\Users\\feilong\\Downloads\\那片海.mp4";
		//pathname = "D:\\Downloads\\viewfile.png";
		//pathname = "D:\\Downloads\\export-飞天奔月.opml";

		//int contentLength = inputStream.available();

		File file = new File(pathname);
		Number contentLength = FileUtil.getFileSize(file);

		// 以流的形式下载文件。
		InputStream inputStream = new FileInputStream(pathname);
		String saveFileName = FileUtil.getFileName(pathname);

		ResponseUtil.download(saveFileName, inputStream, contentLength, request, response);
	}
}