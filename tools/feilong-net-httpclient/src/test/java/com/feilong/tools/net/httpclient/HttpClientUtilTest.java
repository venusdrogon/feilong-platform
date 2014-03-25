package com.feilong.tools.net.httpclient;

import loxia.support.json.JSONException;
import loxia.support.json.JSONObject;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-16 下午01:46:08
 */
public class HttpClientUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(HttpClientUtilTest.class);

	@Test
	public void getHttpMethodWithExecute() throws HttpClientUtilException{
		String uri = "http://www.google.com.hk/search?client=aff-cs-360se&forid=1&ie=utf-8&oe=UTF-8&q=enumeration";
		uri = "http://www.d9cn.org/d9cnbook/50/50537/10967924.html";
		uri = "http://www.kenwen.com/egbk/31/31186/4395342.txt";
		uri = "http://pandavip.www.net.cn/cgi-bin/Check.cgi?queryType=0&domain=feihe&big5=n&sign=2&url=www.net.cn&com=yes&cn=no&mobi=no&net=no&comcn=no&image.x=19&image.y=10";
		HttpMethod httpMethod = HttpClientUtil.getHttpMethodWithExecute(uri, HttpMethodType.GET);
		// HttpMethodParams params = new HttpMethodParams();
		// params.setParameter(HttpMethodParams.USER_AGENT, "");
		// httpMethod.setParams(params);
		String responseBodyAsString = HttpClientUtil.getHttpMethodResponseBodyAsString(uri, HttpMethodType.GET);
		log.debug("responseBodyAsString:{}", responseBodyAsString);
		Header[] headers = httpMethod.getResponseHeaders();
		log.debug("print httpMethod.getResponseHeaders()=======================");
		for (Header header : headers){
			log.debug(header.getName() + ":" + header.getValue());
		}
		httpMethod.releaseConnection();
	}

	@Test
	public void getFLLogisticsTrack() throws HttpClientUtilException,JSONException{
		String uri = "http://firstlogistics.co.id/ws/demo/post/";
		
		NameValuePair[] nameValuePairs = new NameValuePair[] {
				new NameValuePair("APPID", "EBDEMO01"),
				new NameValuePair("ACCOUNT", "1300000430"),
				new NameValuePair("FUNCTION", "track"),
				new NameValuePair("REF", "MPE100503") };

		uri = "http://117.102.249.96/ws/mpe/";
		uri = "http://117.102.249.96/ws/ecom/";
		nameValuePairs = new NameValuePair[] {
				new NameValuePair("APPID", "MP4PP01"),
				new NameValuePair("ACCOUNT", "1300000430"),
				new NameValuePair("FUNCTION", "track"),
				new NameValuePair("REF", "81131531") };

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
		String responseBodyAsString = HttpClientUtil.getPostMethodResponseBodyAsString(uri, nameValuePairs);
		JSONObject jsonObject = new JSONObject(responseBodyAsString);
		log.debug(jsonObject.toString(4, 4));

	}

}
