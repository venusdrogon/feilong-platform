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
package com.feilong.tools.om.nginx;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.FileWriteMode;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * main
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusUtilMain{

	private static final Logger	log		= LoggerFactory.getLogger(NginxStubStatusUtilMain.class);

	private static String		pattern	= "%s	%s	%s	%s	%s	%s	%s	%s";

	private static String		encode	= CharsetType.GBK;

	public static void main(String[] args){

		final String uri = "http://www.nikestore.com.cn/nginx_status";

		final String userName = "nginx_status";
		final String password = "baozun_nikestore_status";
		final String patch = "F:\\stubstatus\\${year}\\${monthAndDay}\\${hour}.txt";

		Timer timer = new Timer();

		TimerTask task = new TimerTask(){

			public void run(){
				crawStubStatus(uri, userName, password, patch);

			}
		};
		timer.schedule(task, 2L, 1000);
	}

	/**
	 * 爬取 StubStatus 信息
	 * 
	 * @param stubStatusURI
	 *            stubStatusURI
	 * @param userName
	 *            bisic 用户名
	 * @param password
	 *            bisic 密码
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
				DateUtil.date2String(crawlDate),
				nginxStubStatusCommand.getActiveConnections(),
				nginxStubStatusCommand.getServerAccepts(),
				nginxStubStatusCommand.getServerHandled(),
				nginxStubStatusCommand.getServerRequests(),
				nginxStubStatusCommand.getReading(),
				nginxStubStatusCommand.getWriting(),
				nginxStubStatusCommand.getWaiting()) + "\n";

		IOWriteUtil.write(filePath, content, encode, FileWriteMode.APPEND);

		// 一小时 最后一秒
		// if (condition){
		// sendMail(filePath);
		// }

	}
}
