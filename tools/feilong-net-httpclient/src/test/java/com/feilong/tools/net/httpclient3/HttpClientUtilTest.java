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
package com.feilong.tools.net.httpclient3;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * The Class HttpClientUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-16 下午01:46:08
 */
public class HttpClientUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(HttpClientUtilTest.class);

	/**
	 * Gets the http method with execute.
	 * 
	 * @throws HttpClientException
	 *             the http client util exception
	 */
	@Test
	public void getHttpMethodWithExecute() throws HttpClientException{
		String uri = "http://www.google.com.hk/search?client=aff-cs-360se&forid=1&ie=utf-8&oe=UTF-8&q=enumeration";
		uri = "http://www.d9cn.org/d9cnbook/50/50537/10967924.html";
		uri = "http://www.kenwen.com/egbk/31/31186/4395342.txt";
		uri = "http://pandavip.www.net.cn/cgi-bin/Check.cgi?queryType=0&domain=feihe&big5=n&sign=2&url=www.net.cn&com=yes&cn=no&mobi=no&net=no&comcn=no&image.x=19&image.y=10";
		// HttpMethodParams params = new HttpMethodParams();
		// params.setParameter(HttpMethodParams.USER_AGENT, "");
		// httpMethod.setParams(params);

		HttpClientConfig httpClientConfig = new HttpClientConfig();

		httpClientConfig.setUri(uri);
		httpClientConfig.setHttpMethodType(HttpMethodType.GET);

		String responseBodyAsString = HttpClientUtil.getResponseBodyAsString(httpClientConfig);

		log.debug("responseBodyAsString:{}", responseBodyAsString);
		log.debug("print httpMethod.getResponseHeaders()=======================");
	}

	/**
	 * Gets the fL logistics track.
	 * 
	 * @throws HttpClientException
	 *             the http client util exception
	 * @throws JSONException
	 *             the jSON exception
	 */
	@Test
	public void getFLLogisticsTrack() throws HttpClientException,JSONException{
		String uri = "http://firstlogistics.co.id/ws/demo/post/";

		Map<String, String> params = new HashMap<String, String>();

		params.put("APPID", "EBDEMO01");
		params.put("ACCOUNT", "1300000430");
		params.put("FUNCTION", "track");
		params.put("REF", "MPE100503");

		uri = "http://117.102.249.96/ws/mpe/";

		uri = "http://117.102.249.96/ws/ecom/";
		params = new HashMap<String, String>();
		params.put("APPID", "MP4PP01");
		params.put("ACCOUNT", "1300000430");
		params.put("FUNCTION", "track");
		params.put("REF", "81131531");

		// method = POST
		// Account = 1300000430
		// AppID = MP4PP01
		// Function = track
		//
		// And you can use this AWB to test, it's a real AWB 81129754

		// MPE100503 - SHIPPED
		// MPE100501 - PICKED UP
		// MPE100500 - FAILED
		// MPE100498 - DELIVERED

		HttpClientConfig httpClientConfig = new HttpClientConfig();

		httpClientConfig.setUri(uri);
		httpClientConfig.setHttpMethodType(HttpMethodType.POST);
		httpClientConfig.setParams(params);

		String responseBodyAsString = HttpClientUtil.getResponseBodyAsString(httpClientConfig);
		if (log.isInfoEnabled()){
			log.info(responseBodyAsString);
		}
		// JSONObject jsonObject = new JSONObject(responseBodyAsString);
		// log.debug(jsonObject.toString(4));
	}
}
