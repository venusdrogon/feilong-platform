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
package com.feilong.application.sina;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.NameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.model.WeiboException;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 11, 2013 12:23:54 AM
 */
@SuppressWarnings("all")
public class WeiboUtilTest{

	private static final Logger	log				= LoggerFactory.getLogger(WeiboUtilTest.class);

	private String				access_token	= "2.00eFxqECiedPvB83b474816527NTJB";

	@Test
	public final void sendWeibo_withPhoto() throws WeiboException,IOException{
		String imagePath = "F:\\Picture 图片\\maitan.gif";
		String statuses = "#鑫哥的微博小蜜# 发个图片玩玩";
		WeiboUtil.sendWeibo(access_token, statuses, imagePath);
	}

	@Test
	public void sendWeibo() throws WeiboException,IOException{
		Date date = new Date();
		String statuses = "#鑫哥的微博小蜜# 吃饭去了,好饿" + DateUtil.date2String(date, DatePattern.COMMON_DATE_AND_TIME);
		WeiboUtil.sendWeibo(access_token, statuses);
	}

	@Test
	public void weibo(){
		String uri = "https://api.weibo.com/2/statuses/update.json";

		String status = "先泽请我吃饭~~~~~@张先泽";
		// status = URIUtil.encode(status, CharsetType.UTF8);
		NameValuePair[] nameValuePairs = { //
		new NameValuePair("status", status),//
				new NameValuePair("access_token", access_token) };

		// %E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E
		// %E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E%E5%93%88%EF%BD%9E

		String responseBodyAsString = "";
		// = HttpClientUtil.getPostMethodResponseBodyAsString(uri, nameValuePairs);

		log.info(responseBodyAsString);
		// log.debug("responseBodyAsString:{}", responseBodyAsString);
		// Header[] headers = httpMethod.getResponseHeaders();
		// log.debug("print httpMethod.getResponseHeaders()=======================");
		// for (Header header : headers){
		// log.debug(header.getName() + ":" + header.getValue());
		// }
		// httpMethod.releaseConnection();
	}
}
