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
package com.feilong.framework.netpay.payment.adaptor.doku.util;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * doku 支付工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月12日 上午10:53:28
 * @since 1.0.6
 */
public class DokuAdaptorUtil{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(DokuAdaptorUtil.class);

	/**
	 * 生成session id 参数<br>
	 * 
	 * @param buyer
	 *            目前取的是 交易的buyerId
	 * @return "" + buyer
	 * @throws IllegalArgumentException
	 *             如果 payRequest.getBuyer() isNullOrEmpty
	 * @see {@link <a href="http://180.168.119.194:8005/redmine/issues/22170">Blanja.com "SESSIONID" field now put what value?</a>} <br>
	 *      blanja原来设置的是 (the sessionid use md5 of the order id),由于 新的网站支持合并订单付款,所以不使用. 此处设置buyerId 已经可以满足开发需求<br>
	 *      关闭交易的时候 需要使用这里的sessionId值
	 */
	public static String generateSessionId(Serializable buyer){
		if (Validator.isNullOrEmpty(buyer)){
			StringBuilder sb = new StringBuilder();
			sb.append("In all DOKU payAdaptor,buyer can't be null/empty!buyer is useful for DOKU,");
			sb.append("this value will set to sessionID param,");
			sb.append("and sessionID value will usefor DOKU advance adaptor");
			throw new IllegalArgumentException(sb.toString());
		}

		// 目前设置的是 buyer
		String SESSIONID = "" + buyer;
		return SESSIONID;
	}
}
