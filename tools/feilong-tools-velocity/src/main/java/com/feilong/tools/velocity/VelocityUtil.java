/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.log.Log4JLogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

/**
 * Velocity 工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-8 下午02:01:59
 */
public final class VelocityUtil{

	private static final Logger		log								= LoggerFactory.getLogger(VelocityUtil.class);

	private static String			RUNTIME_LOG_LOG4J_LOGGER		= "feilongVelocityLogger";

	private static String			feilongStringVelocity			= "feilongStringVelocity";

	private static String			RUNTIME_LOG_LOG4J_LOGGER_LEVEL	= Level.DEBUG.toString();

	private static String			default_CharsetType				= CharsetType.UTF8;

	private static String			resource_loader_string			= "string";

	private static String			resource_loader_class			= "class";

	// 分离实例 避免影响其他的 项目
	private static VelocityEngine	velocityEngine					= null;

	static{
		Properties properties = new Properties();

		properties.put(Velocity.RESOURCE_LOADER, resource_loader_class);
		properties.put(resource_loader_class + ".resource.loader.class", ClasspathResourceLoader.class.getName());
		// log
		properties.put(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, Log4JLogChute.class.getName());
		properties.put(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER, RUNTIME_LOG_LOG4J_LOGGER);
		properties.put(RuntimeConstants.RUNTIME_LOG, "E://velocity.log");
		properties.put(Log4JLogChute.RUNTIME_LOG_LOG4J_LOGGER_LEVEL, RUNTIME_LOG_LOG4J_LOGGER_LEVEL);

		// ENCODING
		properties.put(Velocity.INPUT_ENCODING, default_CharsetType);
		properties.put(Velocity.OUTPUT_ENCODING, default_CharsetType);

		properties.put("cache", "true");

		try{

			// log.info(RuntimeSingleton.isInitialized() + "");
			// 单列模式
			// Velocity.init(properties);

			// 分离实例 避免影响其他的 项目
			velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);

			// log.info(RuntimeSingleton.isInitialized() + "");
			// XMLToolboxManager box = new XMLToolboxManager();
			// box.load(ClassLoaderUtils.getResourceAsStream("toolbox.xml", this.getClass()));
		}catch (Exception e){
			log.error("velocityEngine init error");
			e.printStackTrace();
		}
	}

	/**
	 * 解析vm模板文件
	 * 
	 * @param templateInClassPath
	 *            vm文件,模版classpath 下面的路径
	 * @param contextKeyValues
	 *            参数
	 * @return
	 */
	public static String parseTemplateWithClasspathResourceLoader(String templateInClassPath,Map<String, Object> contextKeyValues){
		return parseVMTemplateAfterInitVelocity(templateInClassPath, contextKeyValues);
	}

	/**
	 * 解析vm文件内容字符串
	 * 
	 * @param vmContent
	 *            vm字符串
	 * @param contextKeyValues
	 *            vm参数
	 * @return
	 */
	public static String parseString(String vmContent,Map<String, Object> contextKeyValues){
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

		try{
			VelocityContext context = new VelocityContext(contextKeyValues);

			Writer writer = new StringWriter();
			velocityEngine.evaluate(context, writer, feilongStringVelocity, vmContent);

			String parseVMTemplateAfterInitVelocity = writer.toString();
			return parseVMTemplateAfterInitVelocity;
		}catch (ParseErrorException e){
			e.printStackTrace();
		}catch (MethodInvocationException e){
			e.printStackTrace();
		}catch (ResourceNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	// ***************************************************************************************************************
	/**
	 * Velocity 初始化之后 调用,解析并获得模板内容
	 * 
	 * @param templateName
	 *            模板名称
	 * @param contextKeyValues
	 *            模板参数k/v
	 * @return 解析并获得模板内容
	 */
	private static String parseVMTemplateAfterInitVelocity(String templateName,Map<String, Object> contextKeyValues){
		try{
			VelocityContext velocityContext = new VelocityContext(contextKeyValues);
			Writer writer = new StringWriter();
			Template template = velocityEngine.getTemplate(templateName, default_CharsetType);
			template.merge(velocityContext, writer);
			writer.flush();
			return writer.toString();
		}catch (IOException e){
			log.error("Parse Velocity Template Error");
			e.printStackTrace();
			// throw new RuntimeException("Parse Velocity Template Error");
		}
		return null;
	}
}
