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
package com.feilong.spring.context;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 2:30:50 PM
 */
public class MailUtil{

	private static final Logger	log	= LoggerFactory.getLogger(MailUtil.class);

	@Resource
	private JavaMailSender		javaMailSender;

	// @Autowired
	private VelocityEngine		velocityEngine;

	@Autowired
	private TaskExecutor		taskExecutor;

	public void init(){
		log.debug("init...");
	}

	/**
	 * 替换velocity模板的变量和值对，邮件主题，velocity模板文件的路径，接收方email地址，附件<br>
	 * 简单说明，如果您要群发，可以在接收方email地址中多传入几个email地址，附件可以一次发送多个
	 */
	public void sendEmail(final Map<String, Object> model,final String subject,final String vmfile,final String[] mailTo,final String[] files){
		// 注意MimeMessagePreparator接口只有这一个回调函数
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator(){

			public void prepare(MimeMessage mimeMessage) throws Exception{
				// 这是一个生成Mime邮件简单工具，如果不使用GBK这个，中文会出现乱码
				// 如果您使用的都是英文，那么可以使用MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				String encoding = CharsetType.GBK;
				MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, encoding);
				// 设置接收方的email地址
				mimeMessageHelper.setTo(mailTo);
				// 设置邮件主题
				mimeMessageHelper.setSubject(subject);
				// 设置发送方地址
				String from = "sanguoxuhuang@163.com";
				mimeMessageHelper.setFrom(from);
				String text = null;
				text = "asdasdas";
				// VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmfile, CharsetType.GBK, model);
				// 从模板中加载要发送的内容，vmfile就是模板文件的名字
				// 注意模板中有中文要加GBK，model中存放的是要替换模板中字段的值
				mimeMessageHelper.setText(text, true);
				// 将发送的内容赋值给MimeMessageHelper,后面的true表示内容解析成html
				// 如果您不想解析文本内容，可以使用false或者不添加这项
				FileSystemResource file = null;
				if (null != files){
					for (String s : files){
						// 读取附件
						file = new FileSystemResource(new File(s));
						// 向email中添加附件
						mimeMessageHelper.addAttachment(s, file);
					}
				}
			}
		};
		// 发送邮件
		javaMailSender.send(mimeMessagePreparator);
	}

	public MimeMessage send(String[] to,String subject,String text,Map<String, Object> params){
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message);
		try{
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			String encodeText = MimeUtility.encodeText("三国徐晃", CharsetType.GBK, "B");
			mimeMessageHelper.setFrom(new InternetAddress("sanguoxuhuang@163.com", encodeText));
			mimeMessageHelper.setText(text, true);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}catch (MessagingException e){
			e.printStackTrace();
		}
		// add attachments
		// for (Entry<String, InputStreamSource> entry : getAttachments(mi).entrySet()){
		// helper.addAttachment(entry.getKey(), entry.getValue());
		// }
		return message;
	}

	public void sendMailNative(String[] to,String subject,String text,Map<String, Object> params){
		final MimeMessage message = send(to, subject, text, params);
		taskExecutor.execute(new Runnable(){

			@Override
			public void run(){
				try{
					javaMailSender.send(message);
				}catch (MailException e){
					log.error("Error occurs while sending mail", e);
				}finally{}
			}
		});
	}
}
