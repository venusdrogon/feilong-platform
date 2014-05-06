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
package com.feilong.netpay.adaptor.bca.creditcard;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.feilong.netpay.adaptor.bca.creditcard.command.CreditCardQueryResult;
import com.feilong.netpay.adaptor.bca.creditcard.command.VarName;
import com.feilong.tools.dom4j.Dom4jUtil;
import com.feilong.tools.json.JsonUtil;

/**
 * The Class QueryResult.
 * 
 * <pre>
 *  {@code
 *  	
 * 	<wddxPacket version="1.0">
 * 	<header/>
 * 	<data>
 * 		<struct>
 * 			<var name="TRANSACTIONID">
 * 				<string>868BBC35-A5D1-FCBF-0453F134C99B5553</string>
 * 			</var>
 * 			<var name="ACQUIRERRESPONSECODE">
 * 				<string>000</string>
 * 			</var>
 * 			<var name="SCRUBMESSAGE">
 * 				<string/>
 * 			</var>
 * 			<var name="AMOUNT">
 * 				<number>9011999.0</number>
 * 			</var>
 * 			<var name="SERVICEVERSION">
 * 				<string>2.0</string>
 * 			</var>
 * 			<var name="TRANSACTIONSCRUBCODE">
 * 				<string/>
 * 			</var>
 * 			<var name="MERCHANTTRANSACTIONID">
 * 				<string>010003170001</string>
 * 			</var>
 * 			<var name="CURRENCY">
 * 				<string>IDR</string>
 * 			</var>
 * 			<var name="TRANSACTIONSTATUS">
 * 				<string>APPROVED</string>
 * 			</var>
 * 			<var name="SITEID">
 * 				<string>Blanja2</string>
 * 			</var>
 * 			<var name="TRANSACTIONDATE">
 * 				<string>2014-04-23 15:19:27</string>
 * 			</var>
 * 			<var name="ACQUIRERCODE">
 * 				<string>AUTH20140423152019PM</string>
 * 			</var>
 * 			<var name="SCRUBCODE">
 * 				<string/>
 * 			</var>
 * 			<var name="TRANSACTIONSCRUBMESSAGE">
 * 				<string/>
 * 			</var>
 * 			<var name="ACQUIRERAPPROVALCODE">
 * 				<string>298883</string>
 * 			</var>
 * 			<var name="TRANSACTIONTYPE">
 * 				<string>AUTHORIZATION</string>
 * 			</var>
 * 		</struct>
 * 	</data>
 * </wddxPacket>
 *  }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2014年5月5日 下午6:47:34
 */
public final class CreditCardQueryResultPaser{

	/** The Constant log. */
	private static final Logger	log						= LoggerFactory.getLogger(CreditCardQueryResultPaser.class);

	/** The Constant XPATH_EXPRESSION_VAR. */
	private static final String	XPATH_EXPRESSION_VAR	= "/wddxPacket/data/struct/var";

	/**
	 * Parses the wddx packet.
	 * 
	 * @param wddxPacketXML
	 *            the xml string
	 * @return the query result
	 */
	public static CreditCardQueryResult parseWddxPacket(String wddxPacketXML){
		if (Validator.isNullOrEmpty(wddxPacketXML)){
			throw new IllegalArgumentException("wddxPacketXML can't be null/empty!");
		}

		// Writer writer = new StringWriter();
		// log.info(Dom4jUtil.format(wddxPacketXML, CharsetType.UTF8, writer));

		// Dom4jUtil.getElementText(element, "TRANSACTIONSTATUS");

		// XStream xstream = new XStream();
		// String xml = xstream.toXML(wddxPacketXML);
		// log.info(xml);

		// 解析 wddxPacketXML ,获得我们需要的 var name 和值.
		Map<String, String> varNameAndValueMap = getVarNameAndValueMap(wddxPacketXML);

		try{
			CreditCardQueryResult creditCardQueryResult = buildQueryResult(varNameAndValueMap);
			return creditCardQueryResult;
		}catch (IllegalArgumentException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}
		throw new IllegalArgumentException("build buildQueryResult exception");
	}

	/**
	 * Builds the query result.
	 * 
	 * @param varNameAndValueMap
	 *            the var name and value map
	 * @return the query result
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	private static CreditCardQueryResult buildQueryResult(Map<String, String> varNameAndValueMap) throws IllegalArgumentException,
			IllegalAccessException{

		// 通过反射机制 省却一堆的 set
		CreditCardQueryResult creditCardQueryResult = new CreditCardQueryResult();
		Field[] fields = CreditCardQueryResult.class.getDeclaredFields();
		for (Field field : fields){
			if (field.isAnnotationPresent(VarName.class)){
				VarName varName = field.getAnnotation(VarName.class);
				if (log.isInfoEnabled()){
					String varNameName = varName.value();

					if (log.isDebugEnabled()){
						String fieldName = field.getName();
						log.debug("{}:{}", fieldName, varNameName);
					}

					String value = varNameAndValueMap.get(varNameName);
					field.setAccessible(true);

					// 将指定对象变量上此 Field 对象表示的字段设置为指定的新值。如果底层字段的类型为基本类型，则对新值进行自动解包
					field.set(creditCardQueryResult, value);
				}
			}
		}
		if (log.isInfoEnabled()){
			log.info(JsonUtil.format(creditCardQueryResult));
		}
		return creditCardQueryResult;
	}

	/**
	 * 解析 wddxPacketXML ,获得我们需要的 var name 和值.
	 * 
	 * @param wddxPacketXML
	 *            the wddx packet xml
	 * @return the var name and value map
	 */
	private static Map<String, String> getVarNameAndValueMap(String wddxPacketXML){
		Document document = Dom4jUtil.string2Document(wddxPacketXML);

		@SuppressWarnings("unchecked")
		List<Node> selectNodes = document.selectNodes(XPATH_EXPRESSION_VAR);

		Map<String, String> varNameAndValueMap = new HashMap<String, String>();

		for (Node node : selectNodes){
			String varName = Dom4jUtil.getAttributeValue(node, "name");
			String stringValue = node.getStringValue();
			varNameAndValueMap.put(varName, stringValue);
		}

		if (log.isInfoEnabled()){
			log.info("varNameAndValueMap:{}", JsonUtil.format(varNameAndValueMap));
		}
		return varNameAndValueMap;
	}
}
