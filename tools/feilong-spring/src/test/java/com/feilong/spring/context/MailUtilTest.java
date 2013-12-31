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
package com.feilong.spring.context;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 2:31:22 PM
 */
public class MailUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(MailUtilTest.class);

	private ApplicationContext	context;

	private MailUtil			mailUtil;

	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("classpath*:spring-mail.xml");
		// String[] array = context.getBeanDefinitionNames();
		// for (int i = 0; i < array.length; ++i){
		// log.debug(array[i]);
		// }
		mailUtil = context.getBean(MailUtil.class);
	}

	@Test
	public void sendEmail(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", "大关");
		model.put("email" + "Address", "woshidaguan@126.com");
		String[] mailTo = new String[] { "venusdrogon@163.com" };
		// String[] files = new String[] { "F:/Photo 照片/2012/else/120120A011.jpg", "F:/Photo 照片/2012/else/120127A001.jpg" };
		String vmfile = "com/guan/chapter19/email/welcome.vm";
		String subject = "欢迎您的加入";
		mailUtil.sendEmail(null, subject, vmfile, mailTo, null);
	}

	@Test
	public void send(){
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("userName", "大关");
		// model.put("email" + "Address", "woshidaguan@126.com");
		// String[] files = new String[] { "F:/Photo 照片/2012/else/120120A011.jpg", "F:/Photo 照片/2012/else/120127A001.jpg" };
		// String vmfile = "com/guan/chapter19/email/welcome.vm";
		String[] to = new String[] { "venusdrogon@163.com" };
		String subject = "欢迎您的加入暗示的发射点法";
		String text = "<span style='color:red'><br>鑫哥艾莉梅阿斯达萨</br></span>";
		mailUtil.send(to, subject, text, null);
	}

	@Test
	public void sendMailNative(){
		// Map<String, Object> model = new HashMap<String, Object>();
		// model.put("userName", "大关");
		// model.put("email" + "Address", "woshidaguan@126.com");
		// String[] files = new String[] { "F:/Photo 照片/2012/else/120120A011.jpg", "F:/Photo 照片/2012/else/120127A001.jpg" };
		// String vmfile = "com/guan/chapter19/email/welcome.vm";
		String[] to = new String[] { "venusdrogon@163.com" };
		String subject = "你好";
		String text = "<span style='color:red'><br>鑫哥 </br></span>";
		mailUtil.sendMailNative(to, subject, text, null);
	}
}
