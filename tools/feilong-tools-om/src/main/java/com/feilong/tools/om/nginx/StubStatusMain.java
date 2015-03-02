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
package com.feilong.tools.om.nginx;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.DatePattern;
import com.feilong.commons.core.enumeration.FileWriteMode;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.util.StringUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.om.nginx.command.StubStatusCommand;

/**
 * StubStatusMain.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class StubStatusMain{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(StubStatusMain.class);

	/** 中间使用tab键分隔 <code>{@value}</code>. */
	private final static String	pattern_write		= "%s	%s	%s	%s	%s	%s	%s	%s";

	/** 中间使用tab键分隔 <code>{@value}</code>. */
	private final static String	pattern_log			= "Active(%s)	Reading(%s)	Writing(%s)	Waiting(%s)";

	/** The encode. */
	private static String		encode				= CharsetType.GBK;

	/** The pattern_crawl date. */
	public static String		pattern_crawlDate	= DatePattern.commonWithTime;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){

		final String uri = System.getProperty("StubStatus.uri");

		final String userName = System.getProperty("StubStatus.userName");
		final String password = System.getProperty("StubStatus.password");
		// ::生成文件的路径
		// ::目前支持以下的变量 ${year} ${monthAndDay} ${hour}
		final String path = System.getProperty("StubStatus.path");

		if (Validator.isNullOrEmpty(uri)){
			throw new IllegalArgumentException("StubStatus.uri can't be null/empty!");
		}

		Timer timer = new Timer();

		TimerTask task = new TimerTask(){

			public void run(){
				try{
					crawStubStatusNike(uri, userName, password, path);
				}catch (IOException e){
					log.error(e.getClass().getName(), e);
				}
			}
		};
		timer.schedule(task, 2L, 1000);
	}

	/**
	 * nike爬取信息.
	 * 
	 * @param uri
	 *            the uri
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @param path
	 *            the patch
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void crawStubStatusNike(String uri,String userName,String password,String path) throws IOException{
		crawStubStatus(uri, userName, password, path);
	}

	/**
	 * 爬取 StubStatus 信息.
	 * 
	 * @param stubStatusURI
	 *            stubStatusURI
	 * @param userName
	 *            bisic 用户名
	 * @param password
	 *            bisic 密码
	 * @param path
	 *            the patch
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static void crawStubStatus(String stubStatusURI,String userName,String password,String path) throws IOException{
		StubStatusCommand stubStatusCommand = StubStatusUtil.getStubStatusCommand(stubStatusURI, userName, password);

		Date crawlDate = stubStatusCommand.getCrawlDate();

		String filePath = parseFilePath(path, crawlDate);

		String logContent = StringUtil.format(
				pattern_log,
				stubStatusCommand.getActiveConnections(),
				stubStatusCommand.getReading(),
				stubStatusCommand.getWriting(),
				stubStatusCommand.getWaiting());

		log.info(logContent);
		// ****************************************************************
		String writecontent = StringUtil.format(
				pattern_write,
				DateUtil.date2String(crawlDate, pattern_crawlDate),
				stubStatusCommand.getActiveConnections(),
				stubStatusCommand.getServerAccepts(),
				stubStatusCommand.getServerHandled(),
				stubStatusCommand.getServerRequests(),
				stubStatusCommand.getReading(),
				stubStatusCommand.getWriting(),
				stubStatusCommand.getWaiting())
				+ "\n";

		IOWriteUtil.write(filePath, writecontent, encode, FileWriteMode.APPEND);

		// 每小时 最后一秒
		int minute = DateUtil.getMinute(crawlDate);
		int second = DateUtil.getSecond(crawlDate);
		boolean isLastSecondOfHour = (59 == minute && 59 == second);
		if (isLastSecondOfHour){
			try{
				StubStatusMailSender.sendMonitorMail(filePath);
			}catch (MessagingException e){
				log.error(e.getClass().getName(), e);
			}catch (IOException e){
				log.error(e.getClass().getName(), e);
			}
		}
	}

	/**
	 * 解析文件路径.
	 * 
	 * @param path
	 *            the path
	 * @param crawlDate
	 *            the crawl date
	 * @return the string
	 */
	protected static String parseFilePath(String path,Date crawlDate){
		String monthAndDay = DateUtil.date2String(crawlDate, DatePattern.monthAndDay);
		String year = DateUtil.date2String(crawlDate, DatePattern.yyyy);
		String hour = DateUtil.date2String(crawlDate, DatePattern.HH);

		String filePath = path.replace("${year}", year).replace("${monthAndDay}", monthAndDay).replace("${hour}", hour);
		return filePath;
	}
}
