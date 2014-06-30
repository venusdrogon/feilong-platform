/**
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
package com.feilong.tools.xstream;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;
import com.thoughtworks.xstream.XStream;

/**
 * The Class XStreamUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Mar 31, 2014 9:58:34 AM
 */
public class XStreamUtil{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(XStreamUtil.class);

	/**
	 * 将object转成xml字符串.
	 * 
	 * @param obj
	 *            the obj
	 * @param toXmlConfig
	 *            the to xml config
	 * @return the string
	 * @see com.thoughtworks.xstream.XStream#toXML(Object)
	 * @see com.thoughtworks.xstream.XStream#alias(String, Class)
	 * @see com.thoughtworks.xstream.XStream#addImplicitCollection(Class, String)
	 */
	public static String toXML(Object obj,ToXmlConfig toXmlConfig){
		XStream xstream = new XStream();

		// *******************alias********************************************
		Map<String, Class<?>> aliasMap = toXmlConfig.getAliasMap();
		if (Validator.isNotNullOrEmpty(aliasMap)){
			for (Map.Entry<String, Class<?>> entry : aliasMap.entrySet()){
				String key = entry.getKey();
				Class<?> value = entry.getValue();
				// 类重命名
				xstream.alias(key, value);
			}
		}
		// *******************implicitCollectionMap********************************************
		Map<String, Class<?>> implicitCollectionMap = toXmlConfig.getImplicitCollectionMap();
		if (Validator.isNotNullOrEmpty(implicitCollectionMap)){
			for (Map.Entry<String, Class<?>> entry : implicitCollectionMap.entrySet()){
				String key = entry.getKey();
				Class<?> value = entry.getValue();
				xstream.addImplicitCollection(value, key);
			}
		}

		// 属性重命名
		// xstream.aliasField(alias, definedIn, fieldName);
		// 包重命名
		// xstream.aliasPackage(name, pkgName);
		String xml = xstream.toXML(obj);
		return xml;
	}
}
