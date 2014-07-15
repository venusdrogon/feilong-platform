/**
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
package com.feilong.framework.netpay.advance;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.lang.reflect.FieldUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.netpay.advance.command.QueryRequest;
import com.feilong.framework.netpay.advance.command.QueryResult;
import com.feilong.framework.netpay.advance.command.TradeRole;
import com.feilong.tools.net.httpclient3.HttpClientException;

/**
 * PaymentAdvanceAdaptor接口的默认实现,抽象,目前所有实现的方法均 throw {@link NotImplementedException}.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月9日 上午1:15:39
 * @since 1.0.6
 */
public abstract class AbstractPaymentAdvanceAdaptor implements PaymentAdvanceAdaptor{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AbstractPaymentAdvanceAdaptor.class);

	/**
	 * Post construct.
	 * 
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	@PostConstruct
	protected void postConstruct() throws IllegalArgumentException,IllegalAccessException{
		if (log.isDebugEnabled()){
			// FieldCallback fc;
			// ReflectionUtils.doWithFields(getClass(), fc);
			// ReflectUtils.
			Map<String, Object> map = FieldUtil.getFieldValueMap(this);
			Class<? extends AbstractPaymentAdvanceAdaptor> clz = getClass();
			log.debug("\n{}\n{}", clz.getCanonicalName(), JsonUtil.format(map));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.netpay.advanceadaptor.PaymentAdvanceAdaptor#getQueryResult(com.feilong.netpay.advanceadaptor.command.QueryRequest)
	 */
	public QueryResult getQueryResult(QueryRequest queryRequest) throws Exception{
		// 抛出表示代码块尚未实施。这一例外补充UnsupportedOperationException异常提供更加丰富的语义描述的问题。
		// NotImplementedException代表作者已经实现的逻辑在程序中的情况。这可以作为基础的TODO标记一个例外。因为这个逻辑可能在catch块，该异常等异常链。
		throw new NotImplementedException("getQueryResult is not implemented!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.netpay.advanceadaptor.PaymentAdvanceAdaptor#isSupportCloseTrade()
	 */
	public boolean isSupportCloseTrade(){
		throw new NotImplementedException("isSupportCloseTrade is not implemented!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.feilong.netpay.advanceadaptor.PaymentAdvanceAdaptor#closeTrade(java.lang.String, com.feilong.netpay.command.TradeRole)
	 */
	public boolean closeTrade(String orderNo,TradeRole tradeRole) throws HttpClientException{
		throw new NotImplementedException("closeTrade is not implemented!");
	}
}