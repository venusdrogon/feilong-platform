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
package com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.bind.parse.varcommand.AbstractVarCommandXmlParse;
import com.feilong.framework.netpay.advance.adaptor.sprintasia.creditcard.command.CreditCardQueryResult;
import com.feilong.tools.dom4j.Dom4jUtil;

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
public final class CreditCardQueryResultPaser extends AbstractVarCommandXmlParse<CreditCardQueryResult>{

	/** The Constant log. */
	private static final Logger	log						= LoggerFactory.getLogger(CreditCardQueryResultPaser.class);

	/** The Constant XPATH_EXPRESSION_VAR. */
	private static final String	XPATH_EXPRESSION_VAR	= "/wddxPacket/data/struct/var";

	/**
	 * 解析 wddxPacketXML ,获得我们需要的 var name 和值.
	 * 
	 * @param wddxPacketXML
	 *            the wddx packet xml
	 * @return the var name and value map
	 */
	@Override
	protected Map<String, String> getVarNameAndValueMap(String wddxPacketXML){
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
