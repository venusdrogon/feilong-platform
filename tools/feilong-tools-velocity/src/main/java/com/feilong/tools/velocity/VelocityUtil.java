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
package com.feilong.tools.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.configure.PropertiesUtil;
import com.feilong.commons.core.io.CharsetType;
import com.feilong.commons.core.log.Slf4jUtil;
import com.feilong.commons.core.tools.json.JsonUtil;
import com.feilong.commons.core.util.Validator;

/**
 * Velocity 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-8 下午02:01:59
 * 
 * @see org.apache.velocity.VelocityContext
 * @see org.apache.velocity.app.VelocityEngine
 * @see org.apache.velocity.Template
 * @see org.apache.velocity.runtime.resource.loader.ResourceLoader
 */
// Properties properties = new Properties();
// properties.put(Velocity.RESOURCE_LOADER, resource_loader_string);
// properties.put(resource_loader_string + ".resource.loader.class", StringResourceLoader.class.getName());
//
// properties.put(Velocity.INPUT_ENCODING, default_CharsetType);
// properties.put(Velocity.OUTPUT_ENCODING, default_CharsetType);

// 分离实例
// VelocityEngine velocityEngine = new VelocityEngine();
// velocityEngine.init(properties);

// 单列模式
// Velocity.init(properties);
// *****************************************************************************
// String templateName = feilongStringVelocity;
// StringResourceRepository stringResourceRepository = StringResourceLoader.getRepository();
// stringResourceRepository.putStringResource(templateName, vmContent);
// String parseVMTemplateAfterInitVelocity = parseVMTemplateAfterInitVelocity(templateName, contextKeyValues);
public final class VelocityUtil{

	/** The Constant log. */
	private static final Logger		log						= LoggerFactory.getLogger(VelocityUtil.class);

	/** The feilong string velocity. */
	private static String			feilongStringVelocity	= "feilongStringVelocity";

	/** The properties path. */
	private static String			PROPERTIES_PATH			= "config/feilong-velocity.properties";

	// 分离实例 避免影响其他的 项目
	/** The velocity engine. */
	private static VelocityEngine	velocityEngine			= null;

	static{
		Properties properties = PropertiesUtil.getPropertiesWithClassLoader(VelocityUtil.class, PROPERTIES_PATH);

		if (Validator.isNullOrEmpty(properties)){
			String messagePattern = "can't load [{}],this properties is use for init velocityEngine,Please make sure that the location of the file path";
			String formatMessage = Slf4jUtil.formatMessage(messagePattern, PROPERTIES_PATH);
			log.error(formatMessage);
			throw new IllegalArgumentException(formatMessage);
		}

		if (log.isInfoEnabled()){
			log.info("velocity init, properties:{}", JsonUtil.format(properties));
		}

		// log.info(RuntimeSingleton.isInitialized() + "");
		// 单列模式
		// Velocity.init(properties);

		// 分离实例 避免影响其他的 项目
		velocityEngine = new VelocityEngine();
		velocityEngine.init(properties);

		// log.info(RuntimeSingleton.isInitialized() + "");
		// XMLToolboxManager box = new XMLToolboxManager();
		// box.load(ClassLoaderUtils.getResourceAsStream("toolbox.xml", this.getClass()));
	}

	/**
	 * 解析vm模板文件.
	 *
	 * @param templateInClassPath
	 *            vm文件,模版classpath 下面的路径,比如 \\loxia\\excel\\template\\trainReport.html
	 * @param contextKeyValues
	 *            参数
	 * @return the string
	 * @throws VelocityException
	 *             the velocity exception
	 * @see Template
	 * @see org.apache.velocity.runtime.resource.ResourceManager#RESOURCE_TEMPLATE
	 * @see org.apache.velocity.runtime.resource.ResourceManager#RESOURCE_CONTENT
	 * @see VelocityEngine#getTemplate(String)
	 * @see VelocityEngine#getTemplate(String, String)
	 * @see Template#merge(org.apache.velocity.context.Context, Writer)
	 */
	public static String parseTemplateWithClasspathResourceLoader(String templateInClassPath,Map<String, Object> contextKeyValues)
					throws VelocityException{
		String encoding = CharsetType.UTF8;

		VelocityContext velocityContext = new VelocityContext(contextKeyValues);
		Writer writer = new StringWriter();

		Template template = velocityEngine.getTemplate(templateInClassPath, encoding);
		template.merge(velocityContext, writer);
		try{
			writer.flush();
		}catch (IOException e){
			log.error(e.getClass().getName(), e);
			throw new VelocityException(e);
		}
		return writer.toString();
	}

	/**
	 * 解析vm文件内容字符串.
	 * 
	 * @param vmContent
	 *            vm字符串
	 * @param contextKeyValues
	 *            vm参数
	 * @return the string
	 * @see org.apache.velocity.runtime.resource.ResourceManager#RESOURCE_TEMPLATE
	 * @see org.apache.velocity.runtime.resource.ResourceManager#RESOURCE_CONTENT
	 */
	public static String parseString(String vmContent,Map<String, Object> contextKeyValues){
		VelocityContext context = new VelocityContext(contextKeyValues);

		Writer writer = new StringWriter();
		velocityEngine.evaluate(context, writer, feilongStringVelocity, vmContent);

		return writer.toString();
	}
}
