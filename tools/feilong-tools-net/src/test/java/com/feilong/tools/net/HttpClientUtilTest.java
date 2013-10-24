package com.feilong.tools.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.HttpMethodType;
import com.feilong.commons.core.util.Validator;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-16 下午01:46:08
 */
public class HttpClientUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(HttpClientUtilTest.class);

	@Test
	// @Ignore
	public void getHttpMethodWithExecute(){
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
	public void getPostMethodResponseBodyAsString(){
		String account = "123";
		String platform = "1";
		String beginDate = "2007-9-10";
		List<String> soCodeList = getUserOrderList(account, platform, beginDate);
		for (String string : soCodeList){
			System.out.println(string);
		}
	}

	/**
	 * @param account
	 * @param platform
	 * @param beginDate
	 */
	private List<String> getUserOrderList(String account,String platform,String beginDate){
		List<String> soCodeList = null;
		if (Validator.isNullOrEmpty(account) || Validator.isNullOrEmpty(platform) || Validator.isNullOrEmpty(beginDate)){
			throw new IllegalArgumentException("param error");
		}else{
			String uri = "http://10.8.10.77:8080/jumbomart/findUcOrder.do";
			NameValuePair[] nameValuePairs = {
					new NameValuePair("type", "1"),
					new NameValuePair("account", account),
					new NameValuePair("platform", platform),
					new NameValuePair("beginDate", beginDate), };
			String result = HttpClientUtil.getPostMethodResponseBodyAsString(uri, nameValuePairs);
			if (Validator.isNullOrEmpty(result)){
				// nothing to do ,log has be printed in FeiLongHttpClientUtil
			}else{
				soCodeList = new ArrayList<String>();
				try{
					JSONObject jsonObject = new JSONObject(result);
					// 1:成功； 0:失败；-1:参数不符合要求
					Integer resultCode = jsonObject.getInt("resultCode");
					switch (resultCode) {
						case 1:// 1:成功；
							JSONArray jsonArray = jsonObject.getJSONArray("orderCodes");
							int length = jsonArray.length();
							JSONObject codeJsonObject = null;
							String soCode = null;
							for (int i = 0; i < length; i++){
								codeJsonObject = jsonArray.getJSONObject(i);
								soCode = codeJsonObject.getJSONObject("code").getString("CODE");
								soCodeList.add(soCode);
							}
							break;
						case 0:// 0:失败；
							throw new RuntimeException("resultCode is :0,The interface is called an exception occurs");
						case -1:// -1:参数不符合要求
							throw new RuntimeException("resultCode is :-1,参数不符合要求");
						default:
							throw new RuntimeException("resultCode is :" + resultCode);
					}
				}catch (JSONException e){
					throw new RuntimeException("result is :" + result + " " + e.getMessage());
				}
			}
		}
		return soCodeList;
	}
}
