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
package com.feilong.framework.netpay.advance.adaptor.doku.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.framework.bind.parse.varcommand.AbstractVarCommandXmlParse;
import com.feilong.framework.netpay.advance.adaptor.doku.command.DokuQueryResult;
import com.feilong.tools.dom4j.Dom4jUtil;

/**
 * The Class DokuQueryResultParse.<br>
 * 从支付网关查询得到的结果 可能是FAIL,也可能是 下面的XML结果
 * 
 * <pre>
 * 原始结果:
 * {@code
 * <?xml version="1.0"?><PAYMENT_STATUS>
 * <AMOUNT>7790000.00</AMOUNT>
 * <TRANSIDMERCHANT>010003660001</TRANSIDMERCHANT>
 * <WORDS>e9e6ed65c872f1646644001f1b67fc8bc5de8df6</WORDS>
 * <RESPONSECODE>0000</RESPONSECODE>
 * <APPROVALCODE>RB1234567890</APPROVALCODE>
 * <RESULTMSG>SUCCESS</RESULTMSG>
 * <PAYMENTCHANNEL>06</PAYMENTCHANNEL>
 * <PAYMENTCODE></PAYMENTCODE>
 * <SESSIONID>20140508105926</SESSIONID>
 * <BANK>BRI</BANK>
 * <MCN></MCN>
 * <PAYMENTDATETIME>20140508095526</PAYMENTDATETIME>
 * <VERIFYID></VERIFYID>
 * <VERIFYSCORE>-1</VERIFYSCORE>
 * <VERIFYSTATUS>NA</VERIFYSTATUS></PAYMENT_STATUS>
 * }
 * </pre>
 * 
 * <pre>
 * 格式化结果:
 * {@code
 * <PAYMENT_STATUS>
 * 	<AMOUNT>7790000.00</AMOUNT>
 * 	<TRANSIDMERCHANT>010003660001</TRANSIDMERCHANT>
 * 	<WORDS>e9e6ed65c872f1646644001f1b67fc8bc5de8df6</WORDS>
 * 	<RESPONSECODE>0000</RESPONSECODE>
 * 	<APPROVALCODE>RB1234567890</APPROVALCODE>
 * 	<RESULTMSG>SUCCESS</RESULTMSG>
 * 	<PAYMENTCHANNEL>06</PAYMENTCHANNEL>
 * 	<PAYMENTCODE/>
 * 	<SESSIONID>20140508105926</SESSIONID>
 * 	<BANK>BRI</BANK>
 * 	<MCN/>
 * 	<PAYMENTDATETIME>20140508095526</PAYMENTDATETIME>
 * 	<VERIFYID/>
 * 	<VERIFYSCORE>-1</VERIFYSCORE>
 * 	<VERIFYSTATUS>NA</VERIFYSTATUS>
 * </PAYMENT_STATUS>
 * }
 * </pre>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午10:07:48
 * @since 1.0.6
 */
public final class DokuQueryResultParse extends AbstractVarCommandXmlParse<DokuQueryResult>{

    /** The Constant log. */
    private static final Logger log                  = LoggerFactory.getLogger(DokuQueryResultParse.class);

    /** The Constant XPATH_EXPRESSION_VAR. */
    private static final String XPATH_EXPRESSION_VAR = "/PAYMENT_STATUS/*";

    /**
     * 解析 wddxPacketXML ,获得我们需要的 var name 和值.
     * 
     * @param xml
     *            the wddx packet xml
     * @return the var name and value map
     */
    @Override
    protected Map<String, String> getVarNameAndValueMap(String xml){
        Document document = Dom4jUtil.string2Document(xml);

        @SuppressWarnings("unchecked")
        List<Node> selectNodes = document.selectNodes(XPATH_EXPRESSION_VAR);

        Map<String, String> varNameAndValueMap = new HashMap<String, String>();

        for (Node node : selectNodes){
            String varName = node.getName();
            String stringValue = node.getStringValue();
            varNameAndValueMap.put(varName, stringValue);
        }

        if (log.isDebugEnabled()){
            log.debug("varNameAndValueMap:{}", JsonUtil.format(varNameAndValueMap));
        }
        return varNameAndValueMap;
    }
}
