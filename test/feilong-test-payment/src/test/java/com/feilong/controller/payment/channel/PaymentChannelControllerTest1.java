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
package com.feilong.controller.payment.channel;

import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.spring.context.ApplicationContextUtil;

/**
 * The Class PaymentChannelControllerTest1.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月29日 上午1:48:41
 * @since 1.0.8
 */
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class PaymentChannelControllerTest1 extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PaymentChannelControllerTest1.class);

	/**
	 * Test.
	 */
	@Test
	public final void test(){
		Map<String, Object> applicationContextForLogMap = ApplicationContextUtil.getApplicationContextForLogMap(applicationContext);
		log.debug("ApplicationContextForLogMap:{}", JsonUtil.format(applicationContextForLogMap));
		log.info("" + applicationContext.containsBean("paymentAdaptorFactory"));
	}
}
