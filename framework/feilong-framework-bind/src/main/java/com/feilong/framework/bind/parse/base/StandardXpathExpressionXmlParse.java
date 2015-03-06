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
package com.feilong.framework.bind.parse.base;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.tools.dom4j.Dom4jUtil;

/**
 * The Class StandardXpathExpressionXmlParse. <br>
 * key /value 取自 每个 {@link org.dom4j.Node} 的 {@link Node#getName()} / {@link Node#getStringValue()} ;<br>
 * 
 * <h3>about xpathExpression</h3>
 * 
 * Example 1:
 * <blockquote>
 * 
 * <pre>
 * {@code
 * <item>
 * 	<categoryId/>
 * 	<title><![CDATA[test pro<duct name]]></title>
 * 	<price>0</price>
 * 	<listPrice>200000</listPrice>
 * 	<state>10</state>
 * 	<city>1010</city>
 * 	<district>101004</district>
 * 	<weight>1.2</weight>
 * 	<length>23</length>
 * 	<width>56</width>
 * 	<height>5</height>
 * 	<buyerObtainPoint>20</buyerObtainPoint>
 * 	<freightPayer>buyer</freightPayer>
 * 	<postageId>-1</postageId>
 * 	<expressFee>5</expressFee>
 * 	<description>test product</description>
 * 	<properties>1004:2586,12536:15248,58746:2546</properties>
 * 	<supportPOD>false</supportPOD>
 * 	<podFee/>
 * 	<timing/>
 * 	<listTime/>
 * </item>
 * }
 * </pre>
 * 
 * if String xpathExpression = "/item/*";
 * 
 * 会取到 下面所有的元素节点
 * 
 * </blockquote>
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014-7-18 17:59:16
 * @param <T>
 *            the generic type
 */
public class StandardXpathExpressionXmlParse<T> extends AbstractBaseXmlParse<T>{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(StandardXpathExpressionXmlParse.class);

    /** The xpath expression . */
    private String              xpathExpression;

    /** The t class. */
    private Class<T>            tClass;

    /**
     * Instantiates a new standard xpath expression xml parse.
     * 
     * @param tClass
     *            the t class
     * @param xpathExpression
     *            the xpath expression
     */
    public StandardXpathExpressionXmlParse(Class<T> tClass, String xpathExpression){
        super();
        if (Validator.isNullOrEmpty(xpathExpression)){
            throw new IllegalArgumentException("xpathExpression can't be null/empty!");
        }
        this.xpathExpression = xpathExpression;
        this.tClass = tClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.bind.parse.AbstractXmlParse#parseModelClass()
     */
    @Override
    protected Class<T> parseModelClass(){
        return tClass;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.framework.bind.parse.AbstractXmlParse#getVarNameAndValueMap(java.lang.String)
     */
    @Override
    protected Map<String, String> getVarNameAndValueMap(String xml){
        if (Validator.isNullOrEmpty(xml)){
            throw new IllegalArgumentException("xml can't be null/empty!");
        }

        Document document = Dom4jUtil.string2Document(xml);

        @SuppressWarnings("unchecked")
        List<Node> selectNodes = document.selectNodes(xpathExpression);

        Map<String, String> varNameAndValueMap = new TreeMap<String, String>();

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
