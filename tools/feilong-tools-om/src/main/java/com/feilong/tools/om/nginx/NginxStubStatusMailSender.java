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

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.tools.mail.MailEntity;
import com.feilong.tools.mail.MailSenderUtil;

/**
 * main
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 7:58:34 PM
 */
public class NginxStubStatusMailSender{

	private static final Logger	log	= LoggerFactory.getLogger(NginxStubStatusMailSender.class);

	/**
	 * @param userName
	 * @param password
	 */
	public static void sendMail(String filePath){

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
		/** 发送. */
		String[] tos = { "xin.jin@baozun.com" };
		mailEntity.setTos(tos);

		mailEntity.setSubject("feilong mail test");// + DateUtil.date2String(new Date())

		mailEntity.setContent("test");

		try{

			String[] filenameString = { FileUtil.getFileName(filePath) };
			mailEntity.setAttachFileNames(filenameString);

			LinkedList<byte[]> attachList = new LinkedList<byte[]>();
			attachList.add(IOUtil.convertFileToByteArray(new File(filePath)));
			mailEntity.setAttachList(attachList);

			mailSenderUtil.sendMail(mailEntity);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (MessagingException e){
			e.printStackTrace();
		}
	}
}
