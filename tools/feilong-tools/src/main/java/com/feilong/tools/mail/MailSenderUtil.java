package com.feilong.tools.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.tools.mail.entity.AttachMailEntity;
import com.feilong.tools.mail.entity.BaseMailEntity;
import com.feilong.tools.mail.entity.HtmlMailEntity;
import com.feilong.tools.mail.entity.TextMailEntity;

/**
 * 简单邮件发送器
 * 
 * @author 王伟
 * @author 金鑫 2010-1-23 下午04:22:24
 * @author 金鑫 2011-12-24 01:54
 */
public class MailSenderUtil{

	private Message	message	= null;

	/**
	 * 以纯文本格式发送邮件 （不带附件的邮件）
	 * 
	 * @param feiLongTextMailEntity
	 *            {@link TextMailEntity}
	 */
	public boolean sendTextMail(TextMailEntity feiLongTextMailEntity){
		// 根据session创建一个邮件消息
		message = this.getMessageAndSetAttribute(feiLongTextMailEntity);
		// 设置邮件消息的主要内容
		String mailContent = feiLongTextMailEntity.getContent();
		try{
			message.setText(mailContent);
			// 发送邮件
			Transport.send(message);
			return true;
		}catch (MessagingException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件 （不带附件的邮件）
	 * 
	 * @param feiLongHtmlMailEntity
	 *            mailSenderInfo
	 * @return 成功返回true
	 */
	public boolean sendHtmlMail(HtmlMailEntity feiLongHtmlMailEntity){
		// 根据session创建一个邮件消息
		message = this.getMessageAndSetAttribute(feiLongHtmlMailEntity);
		//******************************************************
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		Multipart multipart = new MimeMultipart();
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart bodyPart = new MimeBodyPart();
		try{
			// 设置HTML内容
			bodyPart.setContent(feiLongHtmlMailEntity.getContent(), "text/html; charset=utf-8");
			multipart.addBodyPart(bodyPart);
			// 将MiniMultipart对象设置为邮件内容
			message.setContent(multipart);
			// 发送邮件
			Transport.send(message);
			return true;
		}catch (MessagingException e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件 带附件的邮件图片
	 * 
	 * @param feiLongAttachMailEntity
	 *            mailInfo
	 * @return 成功返回true
	 */
	public boolean sendHtmlMailWithFuJian(AttachMailEntity feiLongAttachMailEntity){
		LinkedList<byte[]> linkedList = feiLongAttachMailEntity.getAttachList();
		int size = linkedList.size();
		String[] attachFileNames = feiLongAttachMailEntity.getAttachFileNames();
		// 根据session创建一个邮件消息
		message = getMessageAndSetAttribute(feiLongAttachMailEntity);
		// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
		MimeMultipart mimeMultipart = new MimeMultipart();
		System.setProperty("mail.mime.encodefilename", "true");
		// 创建一个包含HTML内容的MimeBodyPart
		BodyPart bodyPart = new MimeBodyPart();
		try{
			// 设置HTML内容
			bodyPart.setContent(feiLongAttachMailEntity.getContent(), "text/html; charset=utf-8");
			mimeMultipart.setSubType("related");
			mimeMultipart.addBodyPart(bodyPart);
			// 新建一个存放附件的BodyPart
			MimeBodyPart mimeBodyPart = null;
			DataHandler dataHandler = null;
			String type = "application/octet-stream";
			for (int i = 0; i < size; i++){
				// 新建一个存放附件的BodyPart
				mimeBodyPart = new MimeBodyPart();
				byte[] data = linkedList.get(i);
				dataHandler = new DataHandler(new javax.mail.util.ByteArrayDataSource(data, type));
				mimeBodyPart.setDataHandler(dataHandler);
				// 加上这句将作为附件发送,否则将作为信件的文本内容
				mimeBodyPart.setFileName(attachFileNames[i]);
				mimeBodyPart.setContentID(i + "");
				//	mimeBodyPart.setHeader("Content-ID", );
				// 将含有附件的BodyPart加入到MimeMultipart对象中
				mimeMultipart.addBodyPart(mimeBodyPart);
			}
			// 将MiniMultipart对象设置为邮件内容
			message.setContent(mimeMultipart);
			// 发送邮件
			Transport.send(message);
			return true;
		}catch (MessagingException e){
			e.printStackTrace();
		}
		return false;
	}

	//***********************************************************************
	/**
	 * 根据session创建一个邮件消息
	 * 
	 * @param feiLongBaseMailEntity
	 * @return Message
	 */
	private Message getMessageAndSetAttribute(BaseMailEntity feiLongBaseMailEntity){
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = createSession(feiLongBaseMailEntity);
		// 根据session创建一个邮件消息
		message = new MimeMessage(session);
		setMessageAttribute(feiLongBaseMailEntity);
		return message;
	}

	/**
	 * 设置message公共属性
	 * 
	 * @param message
	 *            message
	 * @param feiLongBaseMailEntity
	 *            属性
	 */
	private void setMessageAttribute(BaseMailEntity feiLongBaseMailEntity){
		String fromAddress = feiLongBaseMailEntity.getFromAddress();
		// 设置邮件消息的发送者
		String charset = CharsetType.GB2312;
		try{
			String encodeText = MimeUtility.encodeText(feiLongBaseMailEntity.getPersonal(), charset, "b");
			Address addressFrom = new InternetAddress(fromAddress, encodeText);
			message.setFrom(addressFrom);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address addressTo = new InternetAddress(feiLongBaseMailEntity.getToAddress());
			message.setRecipient(Message.RecipientType.TO, addressTo); // Message.RecipientType.TO属性表示接收者的类型为TO
			// 设置邮件消息的主题
			message.setSubject(feiLongBaseMailEntity.getSubject());
			// 设置邮件消息发送的时间
			message.setSentDate(new Date());
			//邮件的优先级
			message.addHeader("X-Priority", "1");
			//是否需要回执
			message.setHeader("Disposition-Notification-To", "1");
			//邮件客户端
			message.setHeader("X-mailer", "FeiLongMailSenderUtil Api");
		}catch (AddressException e){
			e.printStackTrace();
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (MessagingException e){
			e.printStackTrace();
		}
	}

	/**
	 * 根据邮件会话属性和密码验证器构造一个发送邮件的session
	 * 
	 * @param feiLongBaseMailEntity
	 *            mailSenderInfo
	 * @return Session
	 */
	private Session createSession(BaseMailEntity feiLongBaseMailEntity){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", feiLongBaseMailEntity.getMailServerHost());
		properties.put("mail.smtp.port", feiLongBaseMailEntity.getMailServerPort());
		properties.put("mail.smtp.auth", feiLongBaseMailEntity.isValidate() ? "true" : "false");
		// 判断是否需要身份认证
		Authenticator authenticator = null;
		// 如果需要身份认证，则创建一个密码验证器
		if (feiLongBaseMailEntity.isValidate()){
			final String userName = feiLongBaseMailEntity.getUserName();
			final String password = feiLongBaseMailEntity.getPassword();
			authenticator = new Authenticator(){

				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(userName, password);
				}
			};
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = Session.getDefaultInstance(properties, authenticator);
		session.setDebug(true);
		return session;
	}
}