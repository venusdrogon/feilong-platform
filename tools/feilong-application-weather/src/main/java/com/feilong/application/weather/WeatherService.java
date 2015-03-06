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
package com.feilong.application.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.feilong.commons.core.io.CharsetType;

/**
 * The Class WeatherService.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @author 王景辉 QQ:421547295
 * @version 1.0 2014-5-7 10:39:37
 */
public class WeatherService{

    /** <code>{@value}</code>. */
    private static final String URL_WEATHER_SERVICE = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

    /** The Constant SOAP_ACTION. */
    private static final String SOAP_ACTION         = "http://WebXml.com.cn/getWeatherbyCityName";

    /** The Constant CHARSETNAME. */
    private static final String CHARSETNAME         = CharsetType.UTF8;

    /**
     * 对服务器端返回的XML进行解析
     * 
     * @param city
     *            用户输入的城市名称
     * @return 字符串 用,分割
     * @throws Exception
     *             the exception
     */
    public static String getWeatherContent(String city) throws Exception{
        Document document = getWeatherDocument(city);
        // *************************************************
        NodeList nodeList = document.getElementsByTagName("string");
        StringBuffer sb = new StringBuffer();
        // *******************************************

        for (int count = 0, j = nodeList.getLength(); count < j; count++){
            Node node = nodeList.item(count);
            String nodeValue = node.getFirstChild().getNodeValue();
            // ********************************************
            if ("查询结果为空！".equals(nodeValue)){
                sb = new StringBuffer("#");
                break;
            }
            sb.append(nodeValue + "#\n");
        }
        return sb.toString();
    }

    /**
     * 获得document.
     * 
     * @param city
     *            the city
     * @return the weather document
     * @throws SAXException
     *             the sAX exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ParserConfigurationException
     *             the parser configuration exception
     */
    private static Document getWeatherDocument(String city) throws SAXException,IOException,ParserConfigurationException{
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        InputStream inputStream = getWeatherSoapInputStream(city);
        Document document = documentBuilder.parse(inputStream);
        inputStream.close();
        return document;
    }

    /**
     * 用户把SOAP请求发送给服务器端，并返回服务器点返回的输入流 编写者：王景辉.
     * 
     * @param city
     *            用户输入的城市名称
     * @return 服务器端返回的输入流，供客户端读取
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static InputStream getWeatherSoapInputStream(String city) throws IOException{
        String soap = getWeatherSoapString(city);
        URL url = new URL(URL_WEATHER_SERVICE);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setUseCaches(false);
        // urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestProperty("Content-Length", Integer.toString(soap.length()));
        urlConnection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        urlConnection.setRequestProperty("SOAPAction", SOAP_ACTION);
        // ********************************************
        OutputStream outputStream = urlConnection.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, CHARSETNAME);
        outputStreamWriter.write(soap);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        InputStream inputStream = urlConnection.getInputStream();
        return inputStream;
    }

    /**
     * 获取SOAP的请求头，并替换其中的标志符号为用户输入的城市
     * 
     * @param city
     *            用户输入的城市名称
     * @return 客户将要发送给服务器的SOAP请求
     */
    private static String getWeatherSoapString(String city){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
        sb.append("xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" ");
        sb.append("xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        // ***********************************************
        sb.append("<soap:Body>");
        sb.append("<getWeatherbyCityName xmlns=\"http://WebXml.com.cn/\">");
        sb.append("<theCityName>");
        sb.append(city);
        sb.append("</theCityName>");
        sb.append("</getWeatherbyCityName>");
        sb.append("</soap:Body>");
        // ***********************************************
        sb.append("</soap:Envelope>");
        return sb.toString();
    }
}