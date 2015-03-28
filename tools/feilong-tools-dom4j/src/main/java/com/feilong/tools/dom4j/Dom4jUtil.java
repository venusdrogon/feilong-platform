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
package com.feilong.tools.dom4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.FileUtil;
import com.feilong.commons.core.io.UncheckedIOException;
import com.feilong.commons.core.util.Validator;

/**
 * dom4j 相关.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 下午04:49:45
 * @since 1.0
 */
public final class Dom4jUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(Dom4jUtil.class);

    /**
     * 将字符串转为Document.
     *
     * @param xmlString
     *            xml格式的字符串
     * @return the document
     * @throws Dom4jException
     *             the dom4j exception
     */
    public static Document string2Document(String xmlString) throws Dom4jException{
        try{
            Document document = DocumentHelper.parseText(xmlString);
            return document;
        }catch (DocumentException e){
            throw new Dom4jException(e);
        }
    }

    /**
     * Gets the document.
     *
     * @param file
     *            the file
     * @return the document
     * @throws Dom4jException
     *             the dom4j exception
     * @throws UncheckedIOException
     *             the unchecked io exception
     */
    public static Document getDocument(String file) throws Dom4jException,UncheckedIOException{
        InputStream inputStream = FileUtil.getFileInputStream(file);
        return getDocument(inputStream);
    }

    /**
     * Gets the document.
     *
     * @param inputStream
     *            the input stream
     * @return the document
     * @throws Dom4jException
     *             the dom4j exception
     */
    public static Document getDocument(InputStream inputStream) throws Dom4jException{
        if (null == inputStream){
            throw new IllegalArgumentException("inputStream can't be null");
        }
        SAXReader reader = new SAXReader();
        try{
            Document document = reader.read(inputStream);
            return document;
        }catch (DocumentException e){
            throw new Dom4jException(e);
        }
    }

    /**
     * 获得element的文字.
     * 
     * @param element
     *            element
     * @return 如果null==element,则返回null,否则返回element.getText()
     */
    public static String getElementText(Element element){
        if (Validator.isNullOrEmpty(element)){
            return null;
        }
        String text = element.getText();
        // log.debug("elementName:{}, text is :{}", element.getName(), text);
        return text;
    }

    /**
     * 根据parent element name,查找最接近该element 的parent.
     * 
     * @param element
     *            current element
     * @param parentName
     *            parentName
     * @return the parent
     */
    public static Element getParent(Element element,String parentName){
        if (Validator.isNullOrEmpty(element)){
            throw new NullPointerException("element can't be null/empty!");
        }
        if (Validator.isNullOrEmpty(parentName)){
            throw new NullPointerException("parentName can't be null/empty!");
        }
        Element parentElement = element.getParent();
        if (null == parentElement){
            log.warn("current element don't has parentElement");
            return null;
        }else if (parentElement.getName().equals(parentName)){
            return parentElement;
        }else{
            return getParent(parentElement, parentName);
        }
    }

    /**
     * 获得元素 子元素的text.
     * 
     * @param element
     *            the element
     * @param childElementName
     *            子元素的name
     * @return the child element text
     */
    public static String getChildElementText(Element element,String childElementName){
        if (Validator.isNullOrEmpty(element)){
            return null;
        }
        Element childElement = element.element(childElementName);
        return getElementText(childElement);
    }

    // ****************************************************************************

    // [start] AttributeValue

    /**
     * 获得elemenet 属性值.
     * 
     * @param element
     *            element
     * @param attributeName
     *            属性名称
     * @return the attribute value
     */
    public static String getAttributeValue(Element element,String attributeName){
        if (Validator.isNullOrEmpty(element)){
            log.warn("element is null");
            return null;
        }
        Attribute attribute = element.attribute(attributeName);
        if (Validator.isNullOrEmpty(attribute)){
            log.warn("element has no attribute which named:{}", attributeName);
            return null;
        }
        String text = attribute.getText();
        return text;
    }

    /**
     * 获得node某个属性值.
     * 
     * @param node
     *            the node
     * @param attributeName
     *            属性名称
     * @return the attribute value
     */
    public static String getAttributeValue(Node node,String attributeName){
        if (Validator.isNullOrEmpty(node)){
            log.warn("node is null or empty!");
            return null;
        }

        // node 转element 强转
        Element element = (Element) node;
        return getAttributeValue(element, attributeName);
    }

    // [end]
    /**
     * 格式化输出 xml.<br>
     * 
     * <pre>
     * {@code
     * <wddxPacket version='1.0'><header/><data><struct><var name='TRANSACTIONID'><string></string></var><var name='ACQUIRERRESPONSECODE'><string></string></var><var name='SCRUBMESSAGE'><string>Invalid siteID</string></var><var name='AMOUNT'><string></string></var><var name='SERVICEVERSION'><string>2.0</string></var><var name='TRANSACTIONSCRUBCODE'><string></string></var><var name='MERCHANTTRANSACTIONID'><string>010003170001</string></var><var name='CURRENCY'><string></string></var><var name='TRANSACTIONSTATUS'><string></string></var><var name='SITEID'><string>Blanja2</string></var><var name='TRANSACTIONDATE'><string></string></var><var name='ACQUIRERCODE'><string></string></var><var name='SCRUBCODE'><string>50007</string></var><var name='TRANSACTIONSCRUBMESSAGE'><string></string></var><var name='ACQUIRERAPPROVALCODE'><string></string></var><var name='TRANSACTIONTYPE'><string>AUTHORIZATION</string></var></struct></data></wddxPacket>
     * }
     * </pre>
     * 
     * 将会转成：
     * 
     * <pre>
     * {@code
     * <wddxPacket version="1.0">
     * 	<header/>
     * 	<data>
     * 		<struct>
     * 			<var name="TRANSACTIONID">
     * 				<string/>
     * 			</var>
     * 			<var name="ACQUIRERRESPONSECODE">
     * 				<string/>
     * 			</var>
     * 			<var name="SCRUBMESSAGE">
     * 				<string>Invalid siteID</string>
     * 			</var>
     * 			<var name="AMOUNT">
     * 				<string/>
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
     * 				<string/>
     * 			</var>
     * 			<var name="TRANSACTIONSTATUS">
     * 				<string/>
     * 			</var>
     * 			<var name="SITEID">
     * 				<string>Blanja2</string>
     * 			</var>
     * 			<var name="TRANSACTIONDATE">
     * 				<string/>
     * 			</var>
     * 			<var name="ACQUIRERCODE">
     * 				<string/>
     * 			</var>
     * 			<var name="SCRUBCODE">
     * 				<string>50007</string>
     * 			</var>
     * 			<var name="TRANSACTIONSCRUBMESSAGE">
     * 				<string/>
     * 			</var>
     * 			<var name="ACQUIRERAPPROVALCODE">
     * 				<string/>
     * 			</var>
     * 			<var name="TRANSACTIONTYPE">
     * 				<string>AUTHORIZATION</string>
     * 			</var>
     * 		</struct>
     * 	</data>
     * </wddxPacket>
     * 
     * }
     * </pre>
     *
     * @param xmlString
     *            the xml string
     * @param encoding
     *            the encoding
     * @param writer
     *            the writer
     * @return the string
     * @throws Dom4jException
     *             the dom4j exception
     * @since 1.0.8
     */
    public static String format(String xmlString,String encoding,Writer writer) throws Dom4jException{

        OutputFormat outputFormat = OutputFormat.createPrettyPrint(); // 设置XML文档输出格式
        outputFormat.setEncoding(encoding); // 设置XML文档的编码类型
        outputFormat.setSuppressDeclaration(true);
        outputFormat.setIndent(true); // 设置是否缩进
        outputFormat.setIndent("	"); // 以tab方式实现缩进
        outputFormat.setNewlines(true); // 设置是否换行

        XMLWriter xmlWriter = new XMLWriter(writer, outputFormat);
        xmlWriter.setEscapeText(false);

        Document document = string2Document(xmlString);
        try{
            xmlWriter.write(document);
            xmlWriter.close();

            return writer.toString();
        }catch (IOException e){
            throw new Dom4jException(e);
        }
    }
}
