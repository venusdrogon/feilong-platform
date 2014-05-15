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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class VelocityUtilTest.
 */
public class VelocityUtilMacroTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(VelocityUtilMacroTest.class);

	/**
	 * Parses the vm template with classpath resource loader.
	 */
	@Test
	public void parseVMTemplateWithClasspathResourceLoader(){
		// Properties properties = new Properties();
		// //设置模板的路径
		// properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "target/test-classes/scripts");
		//

		String templateInClassPath = "velocity/test_macro.vm";
		String parseVMTemplate = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, null);
		log.info(parseVMTemplate);
	}

}
