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
package com.feilong.tools.xstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.bca.klikbca.OutputDetailPayment;
import com.feilong.netpay.adaptor.bca.klikbca.OutputListTransactionPGW;
import com.feilong.netpay.adaptor.bca.klikbca.OutputPaymentPGW;
import com.feilong.test.User;
import com.feilong.tools.json.JsonUtil;
import com.feilong.tools.xstream.ToXmlConfig;
import com.feilong.tools.xstream.XStreamUtil;
import com.thoughtworks.xstream.XStream;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 11, 2013 12:57:10 AM
 */
public class XStreamTest implements Serializable{

	private static final long	serialVersionUID	= 288232184048495608L;

	private static final Logger	log					= LoggerFactory.getLogger(XStreamTest.class);

	@Test
	public void name(){
		User user = new User(1L);

		XStream xStream = new XStream();
		xStream.alias("user", User.class);
		log.info(xStream.toXML(user));
	}

	@Test
	public final void testList(){

		String additionalData = "additionalData";
		String transactionDate = DateUtil.date2String(new Date(), DatePattern.ddMMyyyyHHmmss);
		String transactionNo = "010000130002";
		String userID = "</userID>123456";

		// ********************************************************************

		OutputListTransactionPGW outputListTransactionPGW = new OutputListTransactionPGW();
		outputListTransactionPGW.setUserID(userID);
		outputListTransactionPGW.setAdditionalData(additionalData);

		List<OutputDetailPayment> outputDetailPayments = new ArrayList<OutputDetailPayment>();
		OutputDetailPayment outputDetailPayment1 = new OutputDetailPayment();
		outputDetailPayment1.setAmount("IDR3500000.00");
		outputDetailPayment1.setDescription("");
		outputDetailPayment1.setTransactionDate(transactionDate);
		outputDetailPayment1.setTransactionNo(transactionNo);
		outputDetailPayments.add(outputDetailPayment1);

		OutputDetailPayment outputDetailPayment2 = new OutputDetailPayment();
		outputDetailPayment2.setAmount("IDR3500000.00");
		outputDetailPayment2.setDescription("");
		outputDetailPayment2.setTransactionDate(transactionDate);
		outputDetailPayment2.setTransactionNo(transactionNo);
		outputDetailPayments.add(outputDetailPayment2);

		outputListTransactionPGW.setOutputDetailPayments(outputDetailPayments);

		// OutputDetailPayment[] outputDetailPaymentArray = new OutputDetailPayment[2];
		// outputDetailPaymentArray[0] = outputDetailPayment1;
		// outputDetailPaymentArray[1] = outputDetailPayment2;
		// outputListTransactionPGW.setOutputDetailPaymentArray(outputDetailPaymentArray);

		// ***********************************************************************
		// String objectToXML = JsonUtil.objectToXML(outputListTransactionPGW);
		// log.info(objectToXML);

		Map<String, Class<?>> aliasMap = new HashMap<String, Class<?>>();
		aliasMap.put("OutputListTransactionPGW", OutputListTransactionPGW.class);
		aliasMap.put("OutputDetailPayment", OutputDetailPayment.class);

		Map<String, Class<?>> implicitCollectionMap = new HashMap<String, Class<?>>();
		implicitCollectionMap.put("outputDetailPayments", OutputListTransactionPGW.class);

		ToXmlConfig toXmlConfig = new ToXmlConfig();
		toXmlConfig.setAliasMap(aliasMap);
		toXmlConfig.setImplicitCollectionMap(implicitCollectionMap);

		log.info("\n" + XStreamUtil.toXML(outputListTransactionPGW, toXmlConfig));
		// log.info("\n" + toXML(outputListTransactionPGW, toXmlConfig));

		// <OutputListTransactionPGW>
		// <userID>user01</userID>
		// <additionalData> </additionalData>
		// <OutputDetailPayment>
		// <transactionNo>1234ABC5678EFG4321</transactionNo >
		// <transactionDate>20/10/2010 10:08:09</transactionDate>
		// <amount>IDR4500000.00</amount>
		// <description>Computer</description>
		// </ OutputDetailPayment >
		// <OutputDetailPayment>
		// <transactionNo>3456ABC5678EFG6543</transactionNo >
		// <transactionDate>20/10/2010 10:27:12</transactionDate>
		// <amount>IDR2500000.00</amount>
		// <description>HandPhone</description>
		// </ OutputDetailPayment >
		// </ OutputListTransactionPGW >
	}

	
	/**
	 * To xml.
	 * 
	 * @param obj
	 *            the obj
	 * @param toXmlConfig
	 *            the to xml config
	 * @return the string
	 */
	public static String toXML(Object obj,ToXmlConfig toXmlConfig){
		XStream xstream = new XStream();

		Map<String, Class<?>> aliasMap = toXmlConfig.getAliasMap();

		// *******************alias********************************************
		if (Validator.isNotNullOrEmpty(aliasMap)){
			for (Map.Entry<String, Class<?>> entry : aliasMap.entrySet()){
				String key = entry.getKey();
				Class<?> value = entry.getValue();
				// 类重命名
				xstream.alias(key, value);
			}
		}

		// Adds a default implicit collection which is used for any unmapped XML tag.
		xstream.addImplicitCollection(OutputListTransactionPGW.class, "outputDetailPayments");
		xstream.setMode(XStream.XPATH_ABSOLUTE_REFERENCES);

		// 将name设置为父类（Student）的元素的属性
		// xstream.useAttributeFor(OutputDetailPayment.class, "transactionNo");
		// xstream.useAttributeFor("transactionNo", String.class);
		// xstream.useAttributeFor("amount", String.class);

		xstream.useAttributeFor(String.class);
		// 属性重命名
		// xstream.aliasField(alias, definedIn, fieldName);
		// 包重命名
		// xstream.aliasPackage(name, pkgName);
		String xml = xstream.toXML(obj);
		return xml;
	}
}
