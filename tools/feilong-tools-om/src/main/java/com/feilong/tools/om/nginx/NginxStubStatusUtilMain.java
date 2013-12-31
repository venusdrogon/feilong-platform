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

/**
 * main.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusUtilMain{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(NginxStubStatusUtilMain.class);

	/** 中间使用tab键分隔. */
	private static String		pattern				= "%s	%s	%s	%s	%s	%s	%s	%s";

	/** The encode. */
	private static String		encode				= CharsetType.GBK;

	public static String		pattern_crawlDate	= DatePattern.commonWithTime;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		Timer timer = new Timer();

		TimerTask task = new TimerTask(){

			public void run(){
				crawStubStatusNike();
			}
		};
		timer.schedule(task, 2L, 1000);
	}

	/**
	 * nike爬取信息
	 */
	public static void crawStubStatusNike(){
		final String uri = "http://www.nikestore.com.cn/nginx_status";

		final String userName = "nginx_status";
		final String password = "baozun_nikestore_status";
		final String patch = "F:\\stubstatus\\${year}\\${monthAndDay}\\${hour}.txt";

		crawStubStatus(uri, userName, password, patch);
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
	 * @param patch
	 *            the patch
	 */
	private static void crawStubStatus(String stubStatusURI,String userName,String password,String patch){
		NginxStubStatusCommand nginxStubStatusCommand = NginxStubStatusUtil.getNginxStubStatusCommand(stubStatusURI, userName, password);
		// String format = JsonFormatUtil.format(nginxStubStatusCommand);
		// log.info("\n{}", format);

		Date crawlDate = nginxStubStatusCommand.getCrawlDate();
		String monthAndDay = DateUtil.date2String(crawlDate, DatePattern.monthAndDay);
		String year = DateUtil.date2String(crawlDate, DatePattern.yyyy);
		String hour = DateUtil.date2String(crawlDate, DatePattern.HH);

		String filePath = patch.replace("${year}", year).replace("${monthAndDay}", monthAndDay).replace("${hour}", hour);

		String content = StringUtil.format(
				pattern,
				DateUtil.date2String(crawlDate, pattern_crawlDate),
				nginxStubStatusCommand.getActiveConnections(),
				nginxStubStatusCommand.getServerAccepts(),
				nginxStubStatusCommand.getServerHandled(),
				nginxStubStatusCommand.getServerRequests(),
				nginxStubStatusCommand.getReading(),
				nginxStubStatusCommand.getWriting(),
				nginxStubStatusCommand.getWaiting())
				+ "\n";

		IOWriteUtil.write(filePath, content, encode, FileWriteMode.APPEND);

		// 每小时 最后一秒
		int minute = DateUtil.getMinute(crawlDate);
		int second = DateUtil.getSecond(crawlDate);
		boolean isLastSecondOfHour = (59 == minute && 59 == second);
		if (isLastSecondOfHour){
			try{
				NginxStubStatusMailSender.sendMonitorMail(filePath);
			}catch (MessagingException e){
				e.printStackTrace();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
