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
package com.feilong.framework.netpay.advance.sprintasia.creditcard;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.feilong.framework.netpay.advance.BaseAdvanceAdaptorTest;
import com.feilong.framework.netpay.advance.PaymentAdvanceAdaptor;
import com.feilong.framework.netpay.advance.command.QueryRequest;

/**
 * The Class SprintAsiaCreditCardAdvanceAdaptorTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午2:01:52
 * @since 1.0.6
 */
public class SprintAsiaCreditCardAdvanceAdaptorTest extends BaseAdvanceAdaptorTest{

	/** The Constant log. */
	@SuppressWarnings({ "unused" })
	private static final Logger		log	= LoggerFactory.getLogger(SprintAsiaCreditCardAdvanceAdaptorTest.class);

	/** The payment adaptor. */
	@Autowired
	@Qualifier("sprintAsiaCreditCardAdvanceAdaptor")
	private PaymentAdvanceAdaptor	paymentAdvanceAdaptor;

	/**
	 * Gets the query result.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void getQueryResult() throws Exception{
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setTradeNo("010003170001");
		getQueryResult(paymentAdvanceAdaptor, queryRequest);
	}

	/**
	 * Gets the query result1.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public final void getQueryResult1() throws Exception{
		QueryRequest queryRequest = new QueryRequest();
		queryRequest.setTradeNo("010003420004");
		getQueryResult(paymentAdvanceAdaptor, queryRequest);
	}
}
