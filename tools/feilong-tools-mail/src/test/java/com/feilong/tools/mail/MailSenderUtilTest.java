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
package com.feilong.tools.mail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.io.FileInfoEntity;
import com.feilong.commons.core.io.FileType;
import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class MailSenderUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2011 11:57:48 PM
 */
@SuppressWarnings("all")public class MailSenderUtilTest{

	/** The Constant log. */
	private static final Logger	log			= LoggerFactory.getLogger(MailSenderUtilTest.class);

	/** 发送. */
	private String[]			tos			= { "xin.jin@baozun.com" };

	/** cc. */
	private String[]			ccs			= { "venusdrogon@163.com" };

	/** bcc. */
	private String[]			bccs		= { "190600641@qq.com", "1151889455@qq.com" };

	private String				userName	= "sanguoxuhuang@163.com";

	private String				password	= "521000";

	private String				personal	= "三国徐晃";

	private MailEntity			mailEntity;

	@Before
	public void before(){
		mailEntity = new MailEntity();
		mailEntity.setMailServerHost("smtp.163.com");
		mailEntity.setMailServerPort("25");

		mailEntity.setUserName(userName);
		mailEntity.setPassword(password);

		mailEntity.setFromAddress(userName);
		mailEntity.setPersonal(personal);

		mailEntity.setTos(tos);
		mailEntity.setCcs(ccs);
		mailEntity.setBccs(bccs);

		mailEntity.setSubject("feilong mail test");// + DateUtil.date2String(new Date())

		mailEntity.setIsDebug(true);
		mailEntity.setIsNeedReturnReceipt(false);
	}

	@Test
	public void testSendTextMail(){
		String textContent = "测试回执";
		mailEntity.setContent(textContent);
	}

	@Test
	public void sendMail(){
		String textContent = "<html><body><hr/><div style='boder:1px #000 solid;color:red'>222222</div></body></html>";
		mailEntity.setContent(textContent);
	}

	@Test
	public void sendMailWithAttach() throws IOException{
		String templateInClassPath = "velocity/mailtest.vm";
		// ******************************************************************************************
		FileInfoEntity fileInfoEntity = new FileInfoEntity();

		fileInfoEntity.setFileType(FileType.FILE);
		fileInfoEntity.setLastModified(new Date().getTime());
		fileInfoEntity.setName("nikestore_china_cancel20130910.csv");
		fileInfoEntity.setSize(25655L);
		// ******************************************************************************************
		FileInfoEntity fileInfoEntity2 = new FileInfoEntity();

		fileInfoEntity2.setFileType(FileType.FILE);
		fileInfoEntity2.setLastModified(new Date().getTime());
		fileInfoEntity2.setName("nikestore_china_revenue20131022.csv");
		fileInfoEntity2.setSize(25655L);
		// ******************************************************************************************
		FileInfoEntity fileInfoEntity3 = new FileInfoEntity();
		fileInfoEntity3.setFileType(FileType.FILE);
		fileInfoEntity3.setLastModified(new Date().getTime());
		fileInfoEntity3.setName("nikestore_china_return20131022.csv");
		fileInfoEntity3.setSize(25655L);
		// ******************************************************************************************
		FileInfoEntity fileInfoEntity4 = new FileInfoEntity();
		fileInfoEntity4.setFileType(FileType.FILE);
		fileInfoEntity4.setLastModified(new Date().getTime());
		fileInfoEntity4.setName("nikestore_china_demand20130910.csv");
		fileInfoEntity4.setSize(25655L);
		// ******************************************************************************************
		List<FileInfoEntity> fileInfoEntityList = new ArrayList<FileInfoEntity>();
		fileInfoEntityList.add(fileInfoEntity);
		fileInfoEntityList.add(fileInfoEntity2);
		fileInfoEntityList.add(fileInfoEntity3);
		fileInfoEntityList.add(fileInfoEntity4);

		// ******************************************************************************************
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();
		contextKeyValues.put("PREFIX_CONTENTID", MailSenderUtil.PREFIX_CONTENTID);
		contextKeyValues.put("fileInfoEntityList", fileInfoEntityList);
		// ******************************************************************************************

		String textContent = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);
		mailEntity.setContent(textContent);

		String fileString = "F:\\Picture 图片\\美女\\1.jpg";
		String[] filenameString = { FileUtil.getFileName(fileString) };
		mailEntity.setAttachFileNames(filenameString);

		LinkedList<byte[]> attachList = new LinkedList<byte[]>();
		attachList.add(IOUtil.convertFileToByteArray(new File(fileString)));
		mailEntity.setAttachList(attachList);
	}

	@After
	public void after() throws MessagingException,UnsupportedEncodingException{
		MailSenderUtil mailSenderUtil = new MailSenderUtil();
		mailSenderUtil.sendMail(mailEntity);
	}
}
