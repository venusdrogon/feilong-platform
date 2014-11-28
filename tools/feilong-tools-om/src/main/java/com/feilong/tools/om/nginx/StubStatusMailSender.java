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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
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
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.mail.MailSenderConfig;
import com.feilong.tools.mail.MailSenderUtil;
import com.feilong.tools.om.nginx.command.StubStatusCommand;
import com.feilong.tools.om.nginx.command.StubStatusVMCommand;
import com.feilong.tools.om.nginx.comparator.ActiveConnectionsComparator;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * StubStatusMailSender.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class StubStatusMailSender{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(StubStatusMailSender.class);

	/** 发送. */
	private static String[]		tos					= { "xin.jin@baozun.com" };

	/** The template in class path. */
	private static String		templateInClassPath	= "velocity/nginxStubStatusMail.vm";

	/**
	 * 发送监控邮件.
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws MessagingException
	 *             the messaging exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void sendMonitorMail(String filePath) throws MessagingException,IOException{

		if (Validator.isNullOrEmpty(filePath)){
			throw new IllegalArgumentException("filePath can't be null/empty!");
		}

		String userName = "sanguoxuhuang@163.com";
		String password = "521000";

		MailSenderUtil mailSenderUtil = new MailSenderUtil();

		MailSenderConfig mailSenderConfig = new MailSenderConfig();
		mailSenderConfig.setMailServerHost("smtp.163.com");
		mailSenderConfig.setMailServerPort("25");

		mailSenderConfig.setUserName(userName);
		mailSenderConfig.setPassword(password);

		mailSenderConfig.setFromAddress(userName);
		mailSenderConfig.setPersonal("小K监控");

		mailSenderConfig.setTos(tos);

		mailSenderConfig.setSubject("小K监控-NginxStubStatus");// + DateUtil.date2String(new Date())

		String textContent = getTextContentForEmail(filePath);
		mailSenderConfig.setContent(textContent);

		String[] filenameString = { FileUtil.getFileName(filePath) };
		mailSenderConfig.setAttachFileNames(filenameString);

		List<byte[]> attachList = new ArrayList<byte[]>();
		attachList.add(IOUtil.convertFileToByteArray(new File(filePath)));
		mailSenderConfig.setAttachList(attachList);

		mailSenderUtil.sendMail(mailSenderConfig);
	}

	/**
	 * 基于文件 获得要发送邮件的内容.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the text content for email
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getTextContentForEmail(String filePath) throws IOException{

		if (Validator.isNullOrEmpty(filePath)){
			throw new IllegalArgumentException("filePath can't be null/empty!");
		}

		if (FileUtil.isNotExistFile(filePath)){
			throw new IllegalArgumentException("filePath is not exist");
		}

		// *************************************************************************

		LinkedList<StubStatusCommand> stubStatusCommandList = new LinkedList<StubStatusCommand>();

		Reader reader = new FileReader(filePath);
		LineNumberReader lineNumberReader = new LineNumberReader(reader);
		String line = null;

		while ((line = lineNumberReader.readLine()) != null){
			int lineNumber = lineNumberReader.getLineNumber();
			log.debug("the param lineNumber:{}", lineNumber);

			StubStatusCommand nginxStubStatusCommand = toNginxStubStatusCommand(line);
			stubStatusCommandList.add(nginxStubStatusCommand);
		}

		if (Validator.isNullOrEmpty(stubStatusCommandList)){
			throw new NullPointerException("the nginxStubStatusCommandList is null or empty!");
		}
		// *************************************************************************
		ActiveConnectionsComparator activeConnectionsComparator = new ActiveConnectionsComparator();

		StubStatusCommand maxActiveConnectionsStubStatusCommand = Collections.max(stubStatusCommandList, activeConnectionsComparator);
		StubStatusCommand minActiveConnectionsStubStatusCommand = Collections.min(stubStatusCommandList, activeConnectionsComparator);

		// *************************************************************************
		StubStatusVMCommand stubStatusVMCommand = new StubStatusVMCommand();
		stubStatusVMCommand.setBeginDate(stubStatusCommandList.getFirst().getCrawlDate());
		stubStatusVMCommand.setEndDate(stubStatusCommandList.getLast().getCrawlDate());
		stubStatusVMCommand.setStubStatusCommandList(stubStatusCommandList);

		stubStatusVMCommand.setMaxActiveConnectionsStubStatusCommand(maxActiveConnectionsStubStatusCommand);
		stubStatusVMCommand.setMinActiveConnectionsStubStatusCommand(minActiveConnectionsStubStatusCommand);
		// ******************************************************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();
		contextKeyValues.put("DateUtil", DateUtil.class);
		contextKeyValues.put("stubStatusVMCommand", stubStatusVMCommand);

		// ******************************************************************************************
		String textContent = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);
		return textContent;
	}

	/**
	 * 将line 解析成 NginxStubStatusCommand.
	 * 
	 * @param line
	 *            the line
	 * @return the stub status command
	 */
	private static StubStatusCommand toNginxStubStatusCommand(String line){
		String[] split = line.split("	");

		Date now = DateUtil.string2Date(split[0], StubStatusMain.pattern_crawlDate);
		Integer activeConnections = Integer.parseInt(split[1]);

		Long serverAccepts = Long.parseLong(split[2]);
		Long serverHandled = Long.parseLong(split[3]);
		Long serverRequests = Long.parseLong(split[4]);
		Integer reading = Integer.parseInt(split[5]);
		Integer writing = Integer.parseInt(split[6]);
		Integer waiting = Integer.parseInt(split[7]);

		StubStatusCommand stubStatusCommand = new StubStatusCommand();
		stubStatusCommand.setActiveConnections(activeConnections);
		stubStatusCommand.setReading(reading);
		stubStatusCommand.setServerAccepts(serverAccepts);
		stubStatusCommand.setServerHandled(serverHandled);
		stubStatusCommand.setServerRequests(serverRequests);
		stubStatusCommand.setWaiting(waiting);
		stubStatusCommand.setWriting(writing);
		stubStatusCommand.setCrawlDate(now);
		return stubStatusCommand;
	}
}