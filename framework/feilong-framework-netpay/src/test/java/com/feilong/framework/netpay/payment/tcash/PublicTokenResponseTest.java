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
package com.feilong.framework.netpay.payment.tcash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.netpay.payment.adaptor.tcash.PublicTokenResponse;

/**
 * The Class PublicTokenResponseTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月21日 下午3:34:10
 * @since 1.0.8
 */
public class PublicTokenResponseTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(PublicTokenResponseTest.class);

	/**
	 * To bean12.
	 */
	@Test
	public void toBean12(){
		String json = "{'pgpToken':'-----BEGIN PGP MESSAGE-----\\nVersion: BCPG v1.47\\n\\nhQEOAyYtPQbS+5JtEAP/fI1MbXcrVa/83WlZS6y+76g','refNum':'2506141402007010','fastTime':'1403679721180'}";
		PublicTokenResponse publicTokenResponse = JsonUtil.toBean(json, PublicTokenResponse.class);

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(publicTokenResponse));
		}

	}
}
