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

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * The Class BufferController.
 */
@Controller
public class BufferController{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BufferController.class);

	/**
	 * Buffer test1.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws IOException
	 *             the IO exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@RequestMapping(value = "/bufferTest1")
	public void bufferTest1(HttpServletRequest request,HttpServletResponse response) throws IOException,InterruptedException{
		//关闭缓冲区，输出会一个一个写出来（只有在火狐浏览器中才有效果）。
		final PrintWriter writer = response.getWriter();

		//每次都写到Response缓冲区中，最后一次性写出来。
		for (int j = 0; j < 15; j++){
			writer.write("呀哈~</br>");

			if (log.isDebugEnabled()){
				log.debug("Thread.sleep(5000)");
			}
			Thread.sleep(5000);

			if (j % 5 == 0){
				//立即输出缓冲区里面的内容，五次输出一次
				response.flushBuffer();
			}
		}
	}

	/**
	 * Buffer test.
	 *
	 * @param response
	 *            the response
	 * @throws IOException
	 *             the IO exception
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	@RequestMapping(value = "/bufferTest")
	public void bufferTest(HttpServletResponse response) throws IOException,InterruptedException{
		response.setBufferSize(2048);
		response.setCharacterEncoding(CharsetType.GBK);
		final PrintWriter writer = response.getWriter();

		writer.write("第1句");
		writer.flush();

		writer.write("第2句");
		response.reset();

		writer.write("第3句");
	}
}