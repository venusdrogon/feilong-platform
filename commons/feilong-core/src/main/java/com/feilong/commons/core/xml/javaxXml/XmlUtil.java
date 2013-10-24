/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core.xml.javaxXml;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.feilong.commons.core.date.DateUtil;

/**
 * xml 相关,使用原始的 javax.xml. 标准的jdk api
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-11-19 下午04:59:04
 * @since 1.0
 */
public class XmlUtil{

	/**
	 * QName 表示 XML 规范中定义的限定名称 <br>
	 * QName 的值包含名称空间 URI、本地部分和前缀。
	 * 
	 * <pre>
	 * 
	 * 指定名称空间 URI 和本地部分的 QName 构造方法 
	 * 如果名称空间 URI 为 null，则将它设置为 XMLConstants.NULL_NS_URI。此值表示非显式定义的名称空间，在 Namespaces in XML 规范中定义。此操作保持了与 QName 1.0 兼容的行为。显式提供 XMLConstants.NULL_NS_URI 值是首选的编码风格。
	 * 
	 * 如果本地部分为 null，则抛出 IllegalArgumentException。允许 &quot;&quot; 的本地部分保持与 QName 1.0 的兼容行为。 
	 * 当使用此构造方法时，将前缀设置为 XMLConstants.DEFAULT_NS_PREFIX。 
	 * 名称空间 URI 不根据 URI 参考验证。没有按 Namespaces in XML 中的指定将本地部分作为 NCName 来验证。
	 * 
	 * </pre>
	 * 
	 * @param namespaceURI
	 *            QName 的名称空间 URI
	 * @param localPart
	 *            QName 的本地部分
	 * @return
	 */
	public static QName getQName(String namespaceURI,String localPart){
		QName name = new QName(namespaceURI, localPart);
		return name;
	}

	// *********************************************************************************
	/**
	 * Xml 元素的 JAXB 表示形式。
	 * 
	 * @param namespaceURI
	 *            QName 的名称空间 URI
	 * @param localPart
	 *            QName 的本地部分
	 * @param value
	 *            值
	 * @return Xml 元素的 JAXB 表示形式。
	 */
	public static JAXBElement<String> getJAXBElement(String namespaceURI,String localPart,String value){
		QName name = XmlUtil.getQName(namespaceURI, localPart);
		return getJAXBElement(name, value);
	}

	/**
	 * Timestamp类型的传值方式
	 * 
	 * @param name
	 *            QName
	 * @param date
	 *            日期
	 * @return Timestamp类型的传值方式
	 */
	public static JAXBElement<XMLGregorianCalendar> getJAXBElement(QName name,Date date){
		XMLGregorianCalendar xml_gregorianCalendar = convertDateToXMLGregorianCalendar(date);
		JAXBElement<XMLGregorianCalendar> element = new JAXBElement<XMLGregorianCalendar>(name, XMLGregorianCalendar.class, xml_gregorianCalendar);
		return element;
	}

	/**
	 * 将date 转成XMLGregorianCalendar
	 * 
	 * @param date
	 *            date
	 * @return 将date 转成XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date date){
		XMLGregorianCalendar xml_gregorianCalendar = null;
		DatatypeFactory datatypeFactory = null;
		try{
			datatypeFactory = DatatypeFactory.newInstance();
		}catch (DatatypeConfigurationException e){}
		if (null != datatypeFactory){
			GregorianCalendar gregorianCalendar = (GregorianCalendar) DateUtil.toCalendar(date);
			xml_gregorianCalendar = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
		}
		return xml_gregorianCalendar;
	}

	/**
	 * Double类型的传值方式
	 * 
	 * @param name
	 *            QName
	 * @param value
	 *            值
	 * @return Double类型的传值方式
	 */
	public static JAXBElement<Double> getJAXBElement(QName name,Double value){
		return new JAXBElement<Double>(name, Double.class, value);
	}

	/**
	 * Integer类型的传值方式
	 * 
	 * @param name
	 *            QName
	 * @param value
	 *            值
	 * @return Integer类型的传值方式
	 */
	public static JAXBElement<Integer> getJAXBElement(QName name,Integer value){
		return new JAXBElement<Integer>(name, Integer.class, value);
	}

	/**
	 * 字符串传值
	 * 
	 * @param name
	 *            QName
	 * @param str
	 *            字符串
	 * @return 字符串传值
	 */
	public static JAXBElement<String> getJAXBElement(QName name,String str){
		return new JAXBElement<String>(name, String.class, str);
	}
}
