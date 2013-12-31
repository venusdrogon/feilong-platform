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

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.DataHandler;
import javax.activation.MailcapCommandMap;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.util.Validator;

/**
 * 邮件发送器
 * 
 * @version 金鑫 2010-1-23 下午04:22:24
 * @version 金鑫 2011-12-24 01:54
 * @version 1.0 Dec 7, 2013 9:04:22 PM
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 */
public final class MailSenderUtil{

	private static final Logger	log					= LoggerFactory.getLogger(MailSenderUtil.class);

	private Message				message				= null;

	/**
	 * 版本
	 */
	private static final String	VERSION				= "1.0.3";

	/**
	 * contentId前缀
	 */
	public static final String	PREFIX_CONTENTID	= "image";

	// 以纯文本格式发送邮件 （不带附件的邮件）
	/**
	 * 发送邮件
	 * 
	 * @param mailEntity
	 * @return
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendMail(MailEntity mailEntity) throws MessagingException,UnsupportedEncodingException{
		// ************************validator*****************************************************************
		if (Validator.isNullOrEmpty(mailEntity.getTos())){
			throw new IllegalArgumentException("mailEntity tos can't be null/empty!");
		}
		if (Validator.isNullOrEmpty(mailEntity.getFromAddress())){
			throw new IllegalArgumentException("mailEntity getFromAddress can't be null/empty!");
		}

		// *****************************************************************************************
		// 根据session创建一个邮件消息
		message = this.getMessageAndSetAttribute(mailEntity);

		// 设置邮件消息的主要内容
		String mailContent = mailEntity.getContent();
		log.debug("mailContent:{}", mailContent);

		// if txt
		// message.setText(mailContent);

		// ***********************************************************************
		// 以HTML格式发送邮件 （不带附件的邮件）

		// ******************************************************
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		MimeMultipart mimeMultipart = new MimeMultipart();

		String mimeType = "text/html; charset=utf-8";
		String[] attachFileNames = mailEntity.getAttachFileNames();

		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart bodyPart = new MimeBodyPart();
		// 设置HTML内容
		bodyPart.setContent(mailContent, mimeType);
		mimeMultipart.addBodyPart(bodyPart);

		// html
		if (Validator.isNullOrEmpty(attachFileNames)){
			// nothing to do
		}
		// ***************以HTML格式发送邮件 带附件的邮件图片********************************************************
		else{
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			// ************************************************
			System.setProperty("mail.mime.encodefilename", "true");

			// 用于组合文本和图片，"related"型的MimeMultipart对象  
			mimeMultipart.setSubType("related");

			LinkedList<byte[]> linkedList = mailEntity.getAttachList();
			if (Validator.isNotNullOrEmpty(linkedList)){
				String type = "application/octet-stream";

				int size = linkedList.size();
				for (int i = 0; i < size; i++){
					// 新建一个存放附件的BodyPart
					MimeBodyPart mimeBodyPart = new MimeBodyPart();

					byte[] data = linkedList.get(i);
					ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(data, type);
					DataHandler dataHandler = new DataHandler(byteArrayDataSource);
					mimeBodyPart.setDataHandler(dataHandler);

					// 加上这句将作为附件发送,否则将作为信件的文本内容
					mimeBodyPart.setFileName(attachFileNames[i]);

					mimeBodyPart.setContentID(PREFIX_CONTENTID + (i));
					// mimeBodyPart.setHeader("Content-ID", );

					// 将含有附件的BodyPart加入到MimeMultipart对象中
					mimeMultipart.addBodyPart(mimeBodyPart);
				}
			}
		}
		// ***********************************************************************
		// 将MiniMultipart对象设置为邮件内容
		message.setContent(mimeMultipart);

		// 解决 bug
		// **** javax.activation.UnsupportedDataTypeException: no object DCH for MIME type
		// multipart/related;******************************************************
		MailcapCommandMap mailcapCommandMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mailcapCommandMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mailcapCommandMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mailcapCommandMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mailcapCommandMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mailcapCommandMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mailcapCommandMap);

		// ***********************************************************************
		// 发送邮件
		Transport.send(message);
	}

	// ****************************************************************************************
	/**
	 * 根据session创建一个邮件消息
	 * 
	 * @param mailEntity
	 * @return Message
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	private Message getMessageAndSetAttribute(MailEntity mailEntity) throws UnsupportedEncodingException,MessagingException{
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = createSession(mailEntity);
		// 根据session创建一个邮件消息
		message = new MimeMessage(session);
		setMessageAttribute(mailEntity);
		return message;
	}

	/**
	 * 设置message公共属性
	 * 
	 * @param message
	 *            message
	 * @param mailEntity
	 *            属性
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	private void setMessageAttribute(MailEntity mailEntity) throws UnsupportedEncodingException,MessagingException{
		// ***************************************************************************
		String fromAddress = mailEntity.getFromAddress();
		// 设置邮件消息的发送者
		String charset = CharsetType.GB2312;

		String encodeText = MimeUtility.encodeText(mailEntity.getPersonal(), charset, "b");
		Address addressFrom = new InternetAddress(fromAddress, encodeText);
		message.setFrom(addressFrom);
		// ***************************************************************************
		// 设置邮件接受人群
		// 支持 to cc bcc
		setRecipients(mailEntity);
		// ***************************************************************************
		// 设置邮件消息的主题
		message.setSubject(mailEntity.getSubject());

		// ***************************************************************************
		Priority priority = mailEntity.getPriority();
		if (null != priority){
			// 邮件的优先级
			message.addHeader("X-Priority", priority.getLevelValue());
		}
		// ***************************************************************************
		// 是否需要回执
		if (mailEntity.getIsNeedReturnReceipt()){
			message.setHeader("Disposition-Notification-To", "1");
		}

		// 邮件客户端
		message.setHeader("X-mailer", "FeiLong MailSender Api " + VERSION);

		// 设置邮件消息发送的时间
		message.setSentDate(new Date());
	}

	/**
	 * 设置邮件接受人群<br>
	 * 支持 to cc bcc
	 * 
	 * @param mailEntity
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private void setRecipients(MailEntity mailEntity) throws AddressException,MessagingException{
		// *********************to******************************************************
		String[] tos = mailEntity.getTos();
		// 创建邮件的接收者地址，并设置到邮件消息中
		Address[] toAddress = new InternetAddress[tos.length];
		for (int i = 0; i < tos.length; ++i){
			toAddress[i] = new InternetAddress(tos[i]);
		}
		// Message.RecipientType.TO属性表示接收者的类型为TO
		message.setRecipients(Message.RecipientType.TO, toAddress);

		// ************************cc***************************************************
		String[] ccs = mailEntity.getCcs();
		if (Validator.isNotNullOrEmpty(ccs)){
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address[] ccAddress = new InternetAddress[ccs.length];

			for (int i = 0; i < tos.length; ++i){
				ccAddress[i] = new InternetAddress(ccs[i]);
			}
			message.setRecipients(Message.RecipientType.CC, ccAddress);
		}
		// **********************bcc*****************************************************
		String[] bccs = mailEntity.getBccs();
		if (Validator.isNotNullOrEmpty(bccs)){
			Address[] bccAddress = new InternetAddress[bccs.length];
			for (int i = 0; i < bccs.length; ++i){
				bccAddress[i] = new InternetAddress(bccs[i]);
			}
			message.setRecipients(Message.RecipientType.BCC, bccAddress);
		}
	}

	/**
	 * 根据邮件会话属性和密码验证器构造一个发送邮件的session
	 * 
	 * @param mailEntity
	 *            mailSenderInfo
	 * @return Session
	 */
	private Session createSession(MailEntity mailEntity){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", mailEntity.getMailServerHost());
		properties.put("mail.smtp.port", mailEntity.getMailServerPort());
		properties.put("mail.smtp.auth", mailEntity.isValidate() ? "true" : "false");
		// 判断是否需要身份认证
		Authenticator authenticator = null;
		// 如果需要身份认证，则创建一个密码验证器
		if (mailEntity.isValidate()){
			final String userName = mailEntity.getUserName();
			final String password = mailEntity.getPassword();
			authenticator = new Authenticator(){

				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(userName, password);
				}
			};
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = Session.getDefaultInstance(properties, authenticator);
		session.setDebug(mailEntity.getIsDebug());
		return session;
	}

}