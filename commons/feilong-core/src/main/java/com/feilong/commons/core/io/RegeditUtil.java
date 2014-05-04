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
package com.feilong.commons.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注册表工具类,暂时支持查询.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-7 下午01:36:21
 */
public final class RegeditUtil{

	/** The Constant log. */
	private final static Logger	log					= LoggerFactory.getLogger(RegeditUtil.class);

	/** 几大根键简写. */
	public static final String	HKEY_CURRENT_USER	= "HKCU";

	// hklm | hkcu | hkcr | hku | hkcc
	/** The Constant REGQUERY_UTIL. */
	public static final String	REGQUERY_UTIL		= "reg query ";

	/** The Constant REGSTR_TOKEN. */
	public static final String	REGSTR_TOKEN		= "REG_SZ";

	/**
	 * 查询注册表
	 * 
	 * @param cmdCommand
	 *            cmd 命令
	 * @return 返回查询的结果
	 */
	public static String query(String cmdCommand){
		Process process = Command.exec(cmdCommand);
		InputStream inputStream = process.getInputStream();
		FeiLongStreamReaderThread feiLongStreamReaderThread = new FeiLongStreamReaderThread(inputStream);
		// *********************************************************************
		// 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
		feiLongStreamReaderThread.start();
		try{
			// 导致当前线程等待，如有必要，一直要等到由该 Process 对象表示的进程已经终止。
			process.waitFor();
			// 等待该线程终止
			feiLongStreamReaderThread.join();
		}catch (InterruptedException e){
			log.debug(e.getMessage());
		}
		String result = feiLongStreamReaderThread.getResult();
		return result;
	}

	/**
	 * StreamReaderThread
	 * 
	 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
	 * @version 1.0 2014-5-4 14:41:10
	 */
	public static class FeiLongStreamReaderThread extends Thread{

		/** The input stream. */
		private InputStream		inputStream;

		/** The string writer. */
		private StringWriter	stringWriter;

		/**
		 * Instantiates a new fei long stream reader thread.
		 * 
		 * @param is
		 *            the is
		 */
		public FeiLongStreamReaderThread(InputStream is){
			this.inputStream = is;
			stringWriter = new StringWriter();
		}

		/*
		 * (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run(){
			int i;
			try{
				while ((i = inputStream.read()) != -1){
					stringWriter.write(i);
				}
			}catch (IOException e){
				log.debug(e.getMessage());
			}
		}

		// **************************************************************
		/**
		 * Gets the result.
		 * 
		 * @return the result
		 */
		public String getResult(){
			return stringWriter.toString();
		}
	}
}