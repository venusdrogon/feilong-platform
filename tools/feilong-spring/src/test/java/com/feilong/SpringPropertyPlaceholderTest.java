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
package com.feilong;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * 
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月8日 下午4:03:31
 * @since 1.0.8
 */
@ContextConfiguration(locations = { "classpath:spring-property-placeholder.xml" })
public class SpringPropertyPlaceholderTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(SpringPropertyPlaceholderTest.class);

	@Value("#{p_testProperties['name']}")
	private String				name;

	@Value("#{p_testProperties['skills']}")
	private String				skills;

	@Value("#{p_testProperties['skills']}")
	private String[]			skillsArray;

	@Value("${skills}")
	private String[]			skillsArray2;

	/**
	 * Test.
	 */
	@Test
	public void testDIUserArray(){
		log.info(name);
		log.info(skills);
		log.info(JsonUtil.format(skillsArray));
		log.info(JsonUtil.format(skillsArray2));
	}
}
