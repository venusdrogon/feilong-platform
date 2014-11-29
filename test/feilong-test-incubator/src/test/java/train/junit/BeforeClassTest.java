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
package train.junit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class BeforeClassTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年10月30日 下午6:35:14
 * @since 1.0.8
 */
public class BeforeClassTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BeforeClassTest.class);

	/**
	 * Before class1.
	 */
	@BeforeClass
	public static void beforeClass1(){
		log.info("beforeClass1");
	}

	/**
	 * Before class2.
	 */
	@BeforeClass
	public static void beforeClass2(){
		log.info("beforeClass2");
	}

	/**
	 * Test.
	 */
	@Test
	public final void test(){
		log.info("test");
	}
}
