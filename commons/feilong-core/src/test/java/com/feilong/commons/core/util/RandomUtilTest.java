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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.PropertiesConstants;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-7-25 下午04:24:50
 * @since 1.0
 */
public class RandomUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(RandomUtilTest.class);

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#createRandom(double)} 的测试方法。
	 */
	@Test
	public final void testCreateRandom(){
		log.info(RandomUtil.createRandom(8) + "");
	}

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#createRandomWithLength(int)} 的测试方法。
	 */
	@Test
	public final void createRandomWithLength(){
		log.info(RandomUtil.createRandomWithLength(6) + "");
	}

	/**
	 * {@link com.feilong.commons.core.util.RandomUtil#getRandomPassword(int)} 的测试方法。
	 */
	@Test
	public final void testgetRandomFromString(){
		log.info(RandomUtil.createRandomFromString(5, PropertiesConstants.config_numbersAndAllLetters));
		log.info(RandomUtil.createRandomFromString(200, PropertiesConstants.config_numbers));
	}

	@Test
	public final void createRandomFromString(){
		log.info(RandomUtil.createRandomFromString(8, 20, PropertiesConstants.config_numbers));
	}

	@Test
	public final void createRandom(){
		log.info(RandomUtil.createRandom(10, 12) + "");
	}
}
