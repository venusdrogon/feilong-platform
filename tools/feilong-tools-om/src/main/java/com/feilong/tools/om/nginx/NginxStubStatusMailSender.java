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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.tools.mail.MailEntity;
import com.feilong.tools.mail.MailSenderUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * main
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusMailSender{

	private static final Logger	log	= LoggerFactory.getLogger(NginxStubStatusMailSender.class);

	/** 发送. */
	private static String[]		tos	= { "xin.jin@baozun.com" };

	/**
	 * 发送监控邮件
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws MessagingException
	 * @throws IOException
	 */
	public static void sendMonitorMail(String filePath) throws MessagingException,IOException{

		String userName = "sanguoxuhuang@163.com";
		String password = "521000";

		MailSenderUtil mailSenderUtil = new MailSenderUtil();

		MailEntity mailEntity = new MailEntity();
		mailEntity.setMailServerHost("smtp.163.com");
		mailEntity.setMailServerPort("25");

		mailEntity.setUserName(userName);
		mailEntity.setPassword(password);

		mailEntity.setFromAddress(userName);
		mailEntity.setPersonal("小K监控");

		mailEntity.setTos(tos);

		mailEntity.setSubject("小K监控-NginxStubStatus");// + DateUtil.date2String(new Date())

		String textContent = getTextContent(filePath);
		mailEntity.setContent(textContent);

		String[] filenameString = { FileUtil.getFileName(filePath) };
		mailEntity.setAttachFileNames(filenameString);

		LinkedList<byte[]> attachList = new LinkedList<byte[]>();
		attachList.add(IOUtil.convertFileToByteArray(new File(filePath)));
		mailEntity.setAttachList(attachList);

		mailSenderUtil.sendMail(mailEntity);

	}

	public static String getTextContent(String filePath) throws IOException{
		String templateInClassPath = "velocity/nginxStubStatusMail.vm";

		List<NginxStubStatusCommand> nginxStubStatusCommandList = new ArrayList<NginxStubStatusCommand>();

		Reader reader = new FileReader(filePath);
		LineNumberReader lineNumberReader = new LineNumberReader(reader);
		String line = null;

		while ((line = lineNumberReader.readLine()) != null){
			int lineNumber = lineNumberReader.getLineNumber();
			log.debug("the param lineNumber:{}", lineNumber);

			NginxStubStatusCommand nginxStubStatusCommand = toNginxStubStatusCommand(line);
			nginxStubStatusCommandList.add(nginxStubStatusCommand);
		}

		// ******************************************************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();
		contextKeyValues.put("nginxStubStatusCommandList", nginxStubStatusCommandList);
		contextKeyValues.put("DateUtil", DateUtil.class);
		// ******************************************************************************************

		String textContent = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);

		return textContent;
	}

	/**
	 * 将line 解析成 NginxStubStatusCommand
	 * 
	 * @param line
	 * @return
	 */
	private static NginxStubStatusCommand toNginxStubStatusCommand(String line){
		String[] split = line.split("	");

		Date now = DateUtil.string2Date(split[0], NginxStubStatusUtilMain.pattern_crawlDate);
		Integer activeConnections = Integer.parseInt(split[1]);

		Long serverAccepts = Long.parseLong(split[2]);
		Long serverHandled = Long.parseLong(split[3]);
		Long serverRequests = Long.parseLong(split[4]);
		Integer reading = Integer.parseInt(split[5]);
		Integer writing = Integer.parseInt(split[6]);
		Integer waiting = Integer.parseInt(split[7]);

		NginxStubStatusCommand nginxStubStatusCommand = new NginxStubStatusCommand();
		nginxStubStatusCommand.setActiveConnections(activeConnections);
		nginxStubStatusCommand.setReading(reading);
		nginxStubStatusCommand.setServerAccepts(serverAccepts);
		nginxStubStatusCommand.setServerHandled(serverHandled);
		nginxStubStatusCommand.setServerRequests(serverRequests);
		nginxStubStatusCommand.setWaiting(waiting);
		nginxStubStatusCommand.setWriting(writing);
		nginxStubStatusCommand.setCrawlDate(now);
		return nginxStubStatusCommand;
	}
}
