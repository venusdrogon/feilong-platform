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

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.lang.reflect.ReflectUtil;
import com.feilong.commons.core.util.Validator;
import com.feilong.framework.bind.VarCommand;
import com.feilong.tools.dom4j.Dom4jUtil;

/**
 * xml格式结果的解析,目的将xml字符串转成 自定义的对象,这个对象必须 实现 {@link VarCommand} 接口.
 * 
 * @param <T>
 *            自定义的对象,这个对象必须 实现 {@link VarCommand} 接口
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.6 2014年5月8日 上午10:42:49
 * @since 1.0.6
 */
public abstract class AbstractXmlParse<T extends VarCommand> implements CommandXmlParse<T>{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AbstractXmlParse.class);

	/*
	 * (non-Javadoc)
	 * @see com.feilong.netpay.parse.QueryResultXmlParse#parseXML(java.lang.String)
	 */
	public T parseXML(String xml){
		if (Validator.isNullOrEmpty(xml)){
			throw new IllegalArgumentException("xml can't be null/empty!");
		}

		if (log.isDebugEnabled()){
			// Writer writer = new StringWriter();
			// log.info(Dom4jUtil.format(wddxPacketXML, CharsetType.UTF8, writer));

			// Dom4jUtil.getElementText(element, "TRANSACTIONSTATUS");

			// XStream xstream = new XStream();
			// String xml = xstream.toXML(wddxPacketXML);
			// log.info(xml);
			try{
				// 不要和下面的log 合并, 下面的log 可能会有异常
				log.debug("xml:{}", xml);

				Writer writer = new StringWriter();
				log.debug("formatXML:{}", Dom4jUtil.format(xml, CharsetType.UTF8, writer));
			}catch (IOException e){
				e.printStackTrace();
			}
		}

		// 解析 wddxPacketXML ,获得我们需要的 var name 和值.
		Map<String, String> varNameAndValueMap = getVarNameAndValueMap(xml);

		Class<T> modelClass = ReflectUtil.getGenericModelClass(getClass());
		T t = VarClassUtil.buildCommand(modelClass, varNameAndValueMap);
		return t;
	}

	/**
	 * Gets the var name and value map.
	 * 
	 * @param xml
	 *            the xml
	 * @return the var name and value map
	 */
	protected abstract Map<String, String> getVarNameAndValueMap(String xml);
}
