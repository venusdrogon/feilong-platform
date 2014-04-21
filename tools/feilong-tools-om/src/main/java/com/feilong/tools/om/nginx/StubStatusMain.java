/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.om.nginx;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
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
		// 此参数暂时不设置成 可配置式的
		final String path = "F:\\stubstatus\\${year}\\${monthAndDay}\\${hour}.txt";

		if (Validator.isNullOrEmpty(uri)){
			throw new IllegalArgumentException("StubStatus.uri can't be null/empty!");
		}

		Timer timer = new Timer();

		TimerTask task = new TimerTask(){

			public void run(){
				crawStubStatusNike(uri, userName, password, path);
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
	 */
	public static void crawStubStatusNike(String uri,String userName,String password,String path){
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
	 */
	private static void crawStubStatus(String stubStatusURI,String userName,String password,String path){
		StubStatusCommand stubStatusCommand = StubStatusUtil.getStubStatusCommand(stubStatusURI, userName, password);
		// String format = JsonUtil.format(nginxStubStatusCommand);
		// log.info("\n{}", format);

		Date crawlDate = stubStatusCommand.getCrawlDate();
		String monthAndDay = DateUtil.date2String(crawlDate, DatePattern.monthAndDay);
		String year = DateUtil.date2String(crawlDate, DatePattern.yyyy);
		String hour = DateUtil.date2String(crawlDate, DatePattern.HH);

		String filePath = path.replace("${year}", year).replace("${monthAndDay}", monthAndDay).replace("${hour}", hour);

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
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
