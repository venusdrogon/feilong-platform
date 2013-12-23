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
package com.feilong.tools.net.om;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.date.TimeInterval;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.enumeration.FileWriteMode;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.commons.core.util.JsonFormatUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(NginxStubStatusUtilTest.class);

	public static void main(String[] args){
		Timer timer = new Timer();

		TimerTask task = new TimerTask(){

			public void run(){
				aaa();

			}
		};
		timer.schedule(task, 2L, 1000);
	}

	/**
	 * @throws IOException
	 */
	private static void aaa(){
		String uri = "http://www.nikestore.com.cn/nginx_status";

		String userName = "nginx_status";
		String password = "baozun_nikestore_status";

		String pattern = "%s	%s	%s	%s	%s	%s	%s	%s";

		String encode = CharsetType.GBK;

		try{
			NginxStubStatusCommand nginxStubStatusCommand = NginxStubStatusUtil.getNginxStubStatusCommand(uri, userName, password);
			// String format = JsonFormatUtil.format(nginxStubStatusCommand);
			// log.info("\n{}", format);

			String filePath = "F:\\" + DateUtil.date2String(nginxStubStatusCommand.getCrawlDate(), DatePattern.monthAndDay)
					+ " stubstatus.txt";
			String content = StringUtil.format(
					pattern,
					DateUtil.date2String(nginxStubStatusCommand.getCrawlDate()),
					nginxStubStatusCommand.getActiveConnections(),
					nginxStubStatusCommand.getServerAccepts(),
					nginxStubStatusCommand.getServerHandled(),
					nginxStubStatusCommand.getServerRequests(),
					nginxStubStatusCommand.getReading(),
					nginxStubStatusCommand.getWriting(),
					nginxStubStatusCommand.getWaiting())
					+ "\n";

			IOWriteUtil.write(filePath, content, encode, FileWriteMode.APPEND);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
