package com.feilong.tools.net.httpclient;

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
import com.feilong.tools.net.httpclient.HttpClientUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-12-16 下午01:46:08
 */
public class CopyOfHttpClientUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(CopyOfHttpClientUtilTest.class);

	@Test
	public void getPostMethodResponseBodyAsString() throws HttpClientUtilException{
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
	 * @throws HttpClientUtilException
	 */
	private List<String> getUserOrderList(String account,String platform,String beginDate) throws HttpClientUtilException{
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
