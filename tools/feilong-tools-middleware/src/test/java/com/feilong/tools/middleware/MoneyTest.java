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
package com.feilong.tools.middleware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.middleware.MoneyUtil;

/**
 * The Class MoneyTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:24:56
 */
public class MoneyTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(MoneyTest.class);

	/**
	 * 人民币转成大写测试 Debug: hangeToBig(100203.04)=壹拾零贰佰零叁圆零角肆分
	 */
	@Test
	public void testtoRMB(){
		double value = 100203.04;
		log.info(MoneyUtil.convertMoneyToChineseMoney(value));
	}
}
