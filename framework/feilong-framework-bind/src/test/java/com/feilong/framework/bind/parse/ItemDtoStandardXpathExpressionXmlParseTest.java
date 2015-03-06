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
package com.feilong.framework.bind.parse;

import org.junit.Test;

import com.feilong.framework.bind.parse.base.StandardXpathExpressionXmlParse;

/**
 * The Class ItemDtoStandardXpathExpressionXmlParseTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年7月18日 下午6:16:18
 * @since 1.0.8
 */
public class ItemDtoStandardXpathExpressionXmlParseTest{

    /**
     * Test parse xml.
     */
    @Test
    public final void testParseXML(){
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<item>" + "<categoryId></categoryId>"
                        + "<title><![CDATA[test pro<duct name]]></title>" + "<price>0</price>" + "<listPrice>200000</listPrice>"
                        + "<state>10</state>" + "<city>1010</city>" + "<district>101004</district>" + "<weight>1.2</weight>"
                        + "<length>23</length>" + "<width>56</width>" + "<height>5</height>" + "<buyerObtainPoint>20</buyerObtainPoint>"
                        + "<freightPayer>buyer</freightPayer>" + "<postageId>-1</postageId>" + "<expressFee>5</expressFee>"
                        + "<description>test product</description>" + "<properties>1004:2586,12536:15248,58746:2546</properties>"
                        + "<supportPOD>false</supportPOD>" + "<podFee></podFee>" + "<timing></timing>" + "<listTime></listTime>"
                        + "</item>";

        String xpathExpression = "/item/*";
        XmlParse<ItemDto> queryResultXmlParse = new StandardXpathExpressionXmlParse<ItemDto>(ItemDto.class, xpathExpression);
        ItemDto itemDto = queryResultXmlParse.parseXML(xml);
    }
}