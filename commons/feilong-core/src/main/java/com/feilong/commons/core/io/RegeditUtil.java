/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *注册表工具类,暂时支持查询
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-7 下午01:36:21
 */
public final class RegeditUtil{

	private final static Logger	log					= LoggerFactory.getLogger(RegeditUtil.class);

	/**
	 * 几大根键简写
	 */
	public static final String	HKEY_CURRENT_USER	= "HKCU";

	// hklm | hkcu | hkcr | hku | hkcc
	public static final String	REGQUERY_UTIL		= "reg query ";

	public static final String	REGSTR_TOKEN		= "REG_SZ";

	/********************************************************************************/
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

	/*************************** StreamReaderThread **************************/
	public static class FeiLongStreamReaderThread extends Thread{

		private InputStream		inputStream;

		private StringWriter	stringWriter;

		public FeiLongStreamReaderThread(InputStream is){
			this.inputStream = is;
			stringWriter = new StringWriter();
		}

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
		public String getResult(){
			return stringWriter.toString();
		}
	}
}