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
package com.feilong.tools.ant;

import org.apache.tools.ant.Project;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class AntUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 31, 2012 12:36:05 AM
 */
public class AntUtilTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(AntUtilTest.class);

	/**
	 * Test method for {@link com.feilong.tools.ant.AntUtil#executeTarget(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public final void testExecuteTarget(){
		String antFilePath = "build.xml";
		String targetName = "test";
		int messageOutputLevel = Project.MSG_DEBUG;
		AntUtil.executeTarget(antFilePath, targetName, messageOutputLevel);
	}
}
