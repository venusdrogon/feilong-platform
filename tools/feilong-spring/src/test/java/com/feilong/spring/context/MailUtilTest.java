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
 * The Class MailUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 2:31:22 PM
 */
public class MailUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")private static final Logger	log	= LoggerFactory.getLogger(MailUtilTest.class);

	/** The context. */
	private ApplicationContext	context;

	/** The mail util. */
	private MailUtil			mailUtil;

	/**
	 * Inits the.
	 */
	@Before
	public void init(){
		context = new ClassPathXmlApplicationContext("classpath*:spring-mail.xml");
		// String[] array = context.getBeanDefinitionNames();
		// for (int i = 0; i < array.length; ++i){
		// log.debug(array[i]);
		// }
		mailUtil = context.getBean(MailUtil.class);
	}

	/**
	 * Send email.
	 */
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

	/**
	 * Send.
	 */
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

	/**
	 * Send mail native.
	 */
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
