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
package com.feilong.tools.dom4j;

import java.io.InputStream;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOUtil;
import com.feilong.commons.core.util.Validator;

/**
 * dom4j 相关
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 下午04:49:45
 * @since 1.0
 */
public class Dom4jUtil{

	private static final Logger	log	= LoggerFactory.getLogger(Dom4jUtil.class);

	/**
	 * 将字符串转为Document
	 * 
	 * @param xmlString
	 *            xml格式的字符串
	 * @return
	 */
	public static Document string2Document(String xmlString){
		try{
			Document document = DocumentHelper.parseText(xmlString);
			return document;
		}catch (DocumentException e){
			e.printStackTrace();
		}
		return null;
	}

	public static Document getDocument(String file){
		InputStream inputStream = IOUtil.getFileInputStream(file);
		return getDocument(inputStream);
	}

	public static Document getDocument(InputStream inputStream){
		if (null == inputStream){
			throw new IllegalArgumentException("inputStream can't be null");
		}
		SAXReader reader = new SAXReader();
		try{
			Document document = reader.read(inputStream);
			return document;
		}catch (DocumentException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得element的文字
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
		//log.debug("elementName:{}, text is :{}", element.getName(), text);
		return text;
	}

	/**
	 * 根据parent element name,查找最接近该element 的parent
	 * 
	 * @param element
	 *            current element
	 * @param parentName
	 *            parentName
	 * @return
	 */
	public static Element getParent(Element element,String parentName){
		if (Validator.isNullOrEmpty(parentName)){
			throw new IllegalArgumentException("parentName can't be null/empty!");
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
	 * 获得元素 子元素的text
	 * 
	 * @param element
	 * @param childElementName
	 *            子元素的name
	 * @return
	 */
	public static String getChildElementText(Element element,String childElementName){
		if (Validator.isNullOrEmpty(element)){
			return null;
		}
		Element childElement = element.element(childElementName);
		return getElementText(childElement);
	}

	/**
	 * 获得elemenet 属性值
	 * 
	 * @param element
	 *            element
	 * @param attributeName
	 *            属性名称
	 * @return
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
}
