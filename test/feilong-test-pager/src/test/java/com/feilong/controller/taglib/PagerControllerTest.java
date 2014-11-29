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
package com.feilong.controller.taglib;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * The Class PagerControllerTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月23日 上午3:18:01
 * @see <a
 *      href="http://stackoverflow.com/questions/14026111/does-the-new-spring-mvc-test-framework-released-in-spring-3-2-test-the-web-xml-c">http://stackoverflow.com/questions/14026111/does-the-new-spring-mvc-test-framework-released-in-spring-3-2-test-the-web-xml-c</a>
 * @since 1.0.8
 */

//Spring-mvc-test does not read the web.xml file, but you can configure the filters this way:
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:springmvc/springmvc-servlet.xml" })
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的；value指定web应用的根；
@WebAppConfiguration(value = "src/main/webapp")
public class PagerControllerTest{

	/** The Constant log. */
	private static final Logger		log	= LoggerFactory.getLogger(PagerControllerTest.class);

	/** The web application context. */
	@Autowired
	private WebApplicationContext	webApplicationContext;

	/** The mock mvc. */
	private MockMvc					mockMvc;

	/**
	 * Before.
	 */
	@Before
	public void before(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * Test.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void test() throws Exception{
		String urlTemplate = "/pager";
		Object urlVariables = null;
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(urlTemplate, urlVariables);

		RequestBuilder requestBuilder = mockHttpServletRequestBuilder;
		ResultActions resultActions = mockMvc.perform(requestBuilder);

		ResultHandler resultHandler = MockMvcResultHandlers.print();
		ResultActions andDo = resultActions.andDo(resultHandler);
	}
}
