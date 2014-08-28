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
package com.feilong.mock;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.feilong.controller.UriTemplateUtilController;

// @ContextConfiguration(locations = { "classpath*:spring.xml" })
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:springmvc-servlet.xml" })
@SuppressWarnings("all")public class UriTemplateUtilControllerTest{

	// extends AbstractJUnit4SpringContextTests{
	private static final Logger		log	= LoggerFactory.getLogger(UriTemplateUtilControllerTest.class);

	// @Inject
	@Resource
	private ApplicationContext		applicationContext;

	private MockHttpServletRequest	request;

	private MockHttpServletResponse	response;

	private HandlerAdapter			handlerAdapter;

	// @Autowired
	// private PagerController pagerController;
	@Before
	public void setUp() throws Exception{
		this.request = new MockHttpServletRequest();
		this.response = new MockHttpServletResponse();
		// this.handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
	}

	@Test
	public void test() throws Exception{
		UriTemplateUtilController uriTemplateUtilController = new UriTemplateUtilController();
		request.setRequestURI("/c2-5-3-11/mpige-c黑-s52-kchuck taylor all star-svintage.htm");
		request.setParameter("a", "aaaa");
		
		// request.setMethod("GET");
		// request.setParameter("users.username", "zhangfei");
		handlerAdapter = new AnnotationMethodHandlerAdapter();
		ModelAndView modelAndView = handlerAdapter.handle(request, response, uriTemplateUtilController);
		String viewName = modelAndView.getViewName();
		Map<String, Object> model = modelAndView.getModel();
		log.info("viewName:{}", viewName);
		log.info("model attribute:{}", model.get("isSuccess").toString());
	}

	ModelAndView handle(HttpServletRequest request,HttpServletResponse response) throws Exception{
		final HandlerMapping handlerMapping = applicationContext.getBean(HandlerMapping.class);
		final HandlerExecutionChain handler = handlerMapping.getHandler(request);
		// assertNotNull("No handler found for request, check you request mapping", handler);
		final Object controller = handler.getHandler();
		// if you want to override any injected attributes do it here
		final HandlerInterceptor[] interceptors = handlerMapping.getHandler(request).getInterceptors();
		for (HandlerInterceptor interceptor : interceptors){
			final boolean carryOn = interceptor.preHandle(request, response, controller);
			if (!carryOn){
				return null;
			}
		}
		final ModelAndView mav = handlerAdapter.handle(request, response, controller);
		return mav;
	}
}
