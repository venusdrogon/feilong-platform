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
package com.feilong.commons.core.net;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-15 下午3:48:51
 */
@SuppressWarnings("all")public class ParamUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(ParamUtilTest.class);

	private String				uri	= "http://www.feilong.com:8888/esprit-frontend/search.htm?keyword=%E6%81%A4&page=";

	@Test
	public void name(){

		String[] parameters = {
				"service=create_salesorder",
				"partner=3088101011913539",
				"_input_charset=gbk",
				"code=137214341849121",
				"memberID=325465",
				"createTime=20130912150636",
				"paymentType=unionpay_mobile",
				"isNeededInvoice=true",
				"invoiceTitle=上海宝尊电子商务有限公司",
				"totalActual=210.00",
				"receiver=王小二",
				"receiverPhone=15001241318",
				"receiverMobile=0513-86651522",
				"zipCode=216000",
				"province=江苏省",
				"city=南通市",
				"district=通州区",
				"address=江苏南通市通州区平东镇甸北村1组188号",
				"lines_data=[{\"extentionCode\":\"00887224869169\",\"count\":\"2\",\"unitPrice\":\"400.00\"},{\"extentionCode\":\"00887224869170\",\"count\":\"1\",\"unitPrice\":\"500.00\"}]" };
		Map<String, String> object = new HashMap<String, String>();
		for (String string : parameters){

			String[] keyAndValue = string.split("=");
			object.put(keyAndValue[0], keyAndValue[1]);
		}

		log.info(ParamUtil.getToBeSignedString(object));
	}

	@Test
	public void addParameter1(){
		String pageParamName = "page";
		Object prePageNo = "";
		String addParameter = ParamUtil.addParameter(uri, pageParamName, prePageNo, CharsetType.UTF8);
		log.info(addParameter);
	}

	@Test
	public void addParameter(){
		String pageParamName = "label";
		Object prePageNo = "2-5-8-12";
		String addParameter = ParamUtil.addParameter(uri, pageParamName, prePageNo, CharsetType.UTF8);
		log.info(addParameter);
	}

	@Test
	public void removeParameter(){
		uri = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
		String pageParamName = "label";
		String removeParameter = ParamUtil.removeParameter(uri, pageParamName, CharsetType.ISO_8859_1);
		log.info(removeParameter);
	}

	@Test
	public void removeParameterList(){
		uri = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
		String pageParamName = "label";
		List<String> paramNameList = new ArrayList<String>();
		paramNameList.add(pageParamName);
		paramNameList.add("keyword");

		String charsetType = CharsetType.UTF8;
		String removeParameter = ParamUtil.removeParameterList(uri, paramNameList, charsetType);
		log.info(removeParameter);
	}

	@Test
	public void retentionParamList(){
		uri = "http://www.feilong.com:8888/search.htm?keyword=中国&page=&categoryCode=2-5-3-11&label=TopSeller";
		String pageParamName = "label";
		List<String> paramNameList = new ArrayList<String>();
		paramNameList.add(pageParamName);
		paramNameList.add("keyword");

		String charsetType = CharsetType.UTF8;
		String removeParameter = ParamUtil.retentionParamList(uri, paramNameList, charsetType);
		log.info(removeParameter);
	}
}
