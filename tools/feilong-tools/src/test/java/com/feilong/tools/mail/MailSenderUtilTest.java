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
package com.feilong.tools.mail;

import java.io.File;
import java.util.LinkedList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.tools.mail.entity.AttachMailEntity;
import com.feilong.tools.mail.entity.HtmlMailEntity;
import com.feilong.tools.mail.entity.TextMailEntity;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2011 11:57:48 PM
 */
public class MailSenderUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(MailSenderUtilTest.class);

	/**
	 * Test method for {@link com.feilong.tools.mail.MailSenderUtil#sendTextMail(com.feilong.tools.mail.entity.FeiLongMailEntity)}.
	 */
	@Test
	public void testSendTextMail(){
		MailSenderUtil feiLongMailSenderUtil = new MailSenderUtil();
		TextMailEntity feiLongTextMailEntity = new TextMailEntity();
		String toAddress = "venusdrogon@163.com";
		String textContent = "测试回执";
		feiLongTextMailEntity.setMailServerHost("smtp.163.com");
		feiLongTextMailEntity.setMailServerPort("25");
		feiLongTextMailEntity.setUserName("sanguoxuhuang@163.com");
		feiLongTextMailEntity.setPassword("521000");
		feiLongTextMailEntity.setFromAddress("sanguoxuhuang@163.com");
		feiLongTextMailEntity.setPersonal("三国徐晃");
		feiLongTextMailEntity.setToAddress(toAddress);
		feiLongTextMailEntity.setSubject("测试回执");
		feiLongTextMailEntity.setContent(textContent);
		feiLongMailSenderUtil.sendTextMail(feiLongTextMailEntity);
	}

	/**
	 * Test method for {@link com.feilong.tools.mail.MailSenderUtil#sendHtmlMail(com.feilong.tools.mail.entity.FeiLongMailEntity)}.
	 */
	@Test
	public void testSendHtmlMail(){
		MailSenderUtil feiLongMailSenderUtil = new MailSenderUtil();
		HtmlMailEntity feiLongHtmlMailEntity = new HtmlMailEntity();
		String toAddress = "venusdrogon@163.com";
		String textContent = "<html><body><hr/><div style='boder:1px #000 solid;color:red'>222222</div></body></html>";
		feiLongHtmlMailEntity.setMailServerHost("smtp.163.com");
		feiLongHtmlMailEntity.setMailServerPort("25");
		feiLongHtmlMailEntity.setUserName("sanguoxuhuang@163.com");
		feiLongHtmlMailEntity.setPassword("521000");
		feiLongHtmlMailEntity.setFromAddress("sanguoxuhuang@163.com");
		feiLongHtmlMailEntity.setPersonal("三国徐晃");
		feiLongHtmlMailEntity.setToAddress(toAddress);
		feiLongHtmlMailEntity.setSubject("测试");
		feiLongHtmlMailEntity.setContent(textContent);
		feiLongMailSenderUtil.sendHtmlMail(feiLongHtmlMailEntity);
	}

	/**
	 * Test method for {@link com.feilong.tools.mail.MailSenderUtil#sendHtmlMailWithFuJian(com.feilong.tools.mail.entity.FeiLongMailEntity)}.
	 */
	@Test
	public void testSendHtmlMailWithFuJian(){
		MailSenderUtil feiLongMailSenderUtil = new MailSenderUtil();
		AttachMailEntity feiLongAttachMailEntity = new AttachMailEntity();
		String toAddress = "venusdrogon@163.com";
		String textContent = "<html><body><hr/><div style='boder:1px #000 solid;color:red'>222222</div></body></html>";
		feiLongAttachMailEntity.setMailServerHost("smtp.163.com");
		feiLongAttachMailEntity.setMailServerPort("25");
		feiLongAttachMailEntity.setUserName("sanguoxuhuang@163.com");
		feiLongAttachMailEntity.setPassword("521000");
		feiLongAttachMailEntity.setFromAddress("sanguoxuhuang@163.com");
		feiLongAttachMailEntity.setPersonal("三国徐晃");
		feiLongAttachMailEntity.setToAddress(toAddress);
		feiLongAttachMailEntity.setSubject("1 喜讯传来乐开怀.mp3");
		feiLongAttachMailEntity.setContent(textContent);
		String fileString = "D:\\downloads\\妻子的欲望1-30.txt";
		String[] filenameString = { FileUtil.getFileName(fileString) };
		feiLongAttachMailEntity.setAttachFileNames(filenameString);
		LinkedList<byte[]> attachList = new LinkedList<byte[]>();
		attachList.add(IOUtil.convertFileToByteArray(new File(fileString)));
		feiLongAttachMailEntity.setAttachList(attachList);
		feiLongMailSenderUtil.sendHtmlMailWithFuJian(feiLongAttachMailEntity);
	}
}
