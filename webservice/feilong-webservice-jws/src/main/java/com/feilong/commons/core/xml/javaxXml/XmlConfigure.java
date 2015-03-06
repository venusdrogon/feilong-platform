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
package com.feilong.commons.core.xml.javaxXml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.feilong.commons.core.configure.BaseConfigure;

/**
 * xml配置文件.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-3-22 上午10:08:30
 * @since 1.0.0
 */
public final class XmlConfigure implements BaseConfigure{

    private static final Logger log = LoggerFactory.getLogger(XmlConfigure.class);

    /**
     * XmlConfigure.
     * 
     * @param xmlPath
     *            the xml path
     */
    public XmlConfigure(String xmlPath){
        createDocument(xmlPath);
    }

    /**
     * Instantiates a new xml configure.
     * 
     * @param xmlFile
     *            the xml file
     */
    public XmlConfigure(File xmlFile){
        createDocument(xmlFile);
    }

    /**
     * Instantiates a new xml configure.
     * 
     * @param inputStream
     *            the input stream
     */
    public XmlConfigure(InputStream inputStream){
        createDocument(inputStream);
    }

    /**
     * Instantiates a new xml configure.
     * 
     * @param inputSource
     *            the input source
     */
    public XmlConfigure(InputSource inputSource){
        createDocument(inputSource);
    }

    // ************************************************************************
    /**
     * 获得document对象.
     * 
     * @param xmlPath
     *            the xml path
     * @return the document
     */
    public Document createDocument(Object xmlPath){
        DocumentBuilder documentBuilder = createDocumentBuilder();
        try{
            if (xmlPath instanceof String){
                document = documentBuilder.parse((String) xmlPath);
            }else if (xmlPath instanceof File){
                document = documentBuilder.parse((File) xmlPath);
            }else if (xmlPath instanceof InputStream){
                document = documentBuilder.parse((InputStream) xmlPath);
            }else if (xmlPath instanceof InputSource){
                document = documentBuilder.parse((InputSource) xmlPath);
            }else{
                throw new IllegalArgumentException("文档解析错误,参数类型不正确,文档必须是String,file,InputStream,InputSource四种类型之一");
            }
        }catch (SAXException e){
            log.error(e.getClass().getName(), e);
        }catch (IOException e){
            log.error(e.getClass().getName(), e);
        }
        return document;
    }

    /**
     * Creates the document builder.
     * 
     * @return the document builder
     */
    public DocumentBuilder createDocumentBuilder(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder;
        }catch (ParserConfigurationException e){
            log.error(e.getClass().getName(), e);
        }
        return null;
    }

    // **************************************************************************
    /**
     * 获取xml node对象.
     * 
     * @param nodeTagName
     *            节点名称
     * @param tagIndex
     *            标签下标
     * @return node对象
     */
    private Node getNodeByTagName(String nodeTagName,int tagIndex){
        try{
            node = document.getElementsByTagName(nodeTagName).item(tagIndex);
        }catch (Exception e){
            log.error(e.getClass().getName(), e);
        }
        return node;
    }

    /**
     * 获取xml node对象.
     * 
     * @param expression
     *            xpath表达式
     * @return node对象
     */
    public Node getNodeByXPath(String expression){
        return (Node) getObjectByXPath(expression, XPathConstants.NODE);
    }

    /**
     * 通过xpath 获取对象.
     * 
     * @param expression
     *            xpath表达式
     * @param qName
     *            qname 定义的数据类型
     * @return 通过xpath 获取对象
     */
    public Object getObjectByXPath(String expression,QName qName){
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xpath = pathFactory.newXPath();
        try{
            return xpath.evaluate(expression, document, qName);
        }catch (XPathExpressionException e){
            log.error(e.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 获得xml节点.
     * 
     * @param nodeTagName
     *            节点名称
     * @return 节点
     */
    private Node getNodeByTagName(String nodeTagName){
        return getNodeByTagName(nodeTagName, 0);
    }

    /**
     * 获取xml对象文档节点里面的属性值.
     * 
     * @param nodeTagName
     *            节点名称
     * @param tagIndex
     *            标签下标
     * @param attributeName
     *            属性名称
     * @return 对应的属性值
     */
    public String getAttributeValue(String nodeTagName,int tagIndex,String attributeName){
        node = getNodeByTagName(nodeTagName, tagIndex);
        return getAttributeValue(node, attributeName);
    }

    /**
     * xpath 获得node 属性.
     * 
     * @param expression
     *            xpath表达式
     * @param attributeName
     *            属性名称
     * @return 获得node 属性
     */
    public String getAttributeValueByXPath(String expression,String attributeName){
        node = getNodeByXPath(expression);
        return getAttributeValue(node, attributeName);
    }

    /**
     * 获得node属性值.
     * 
     * @param node
     *            节点
     * @param attributeName
     *            属性名称
     * @return 获得node属性值
     */
    public String getAttributeValue(@SuppressWarnings("hiding") Node node,String attributeName){
        if (null != node){
            namedNodeMap = node.getAttributes();
            Node node_current = namedNodeMap.getNamedItem(attributeName);
            if (null != node_current){
                return node_current.getNodeValue();
            }
        }
        return "";
    }

    /**
     * 获取xml对象文档节点里面的属性值.
     * 
     * @param nodeTagName
     *            节点名称
     * @param attributeName
     *            属性名称
     * @return 对应的属性值
     */
    public String getAttributeValue(String nodeTagName,String attributeName){
        return getAttributeValue(nodeTagName, 0, attributeName);
    }

    /**
     * 返回节点及其后代的文本内容.
     * 
     * @param nodeTagName
     *            节点名称
     * @return 节点及其后代的文本内容
     */
    public String getNodeTextContent(String nodeTagName){
        node = getNodeByTagName(nodeTagName);
        return getNodeTextContent(node);
    }

    /**
     * 获得节点内容.
     * 
     * @param node
     *            节点
     * @return the node text content
     */
    private String getNodeTextContent(@SuppressWarnings("hiding") Node node){
        if (null == node){
            return "";
        }
        return node.getTextContent();
    }

    // [start]
    /** document. */
    private Document     document     = null;

    /** node. */
    private Node         node         = null;

    /** namedNodeMap. */
    private NamedNodeMap namedNodeMap = null;
    // [end]
}
