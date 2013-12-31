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
package elsepackage;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 5:14:45 PM
 */
public class SalesOrderCodeGeneratorTest{

	private static final Logger	log	= LoggerFactory.getLogger(SalesOrderCodeGeneratorTest.class);

	/**
	 * Test method for {@link com.baozun.shop.utils.SalesOrderCodeGenerator#create(java.lang.Long)}.
	 */
	@Test
	public void testCreate(){
		log.info(SalesOrderCodeGenerator.create(111121L));
		log.info(SalesOrderCodeGenerator.create(1L));
		log.info(SalesOrderCodeGenerator.create(10L));
		log.info(SalesOrderCodeGenerator.create(101L));
		log.info(SalesOrderCodeGenerator.create(DateUtil.string2Date("2013-01-01 00:08:02", DatePattern.commonWithTime), 1L));
		log.info(SalesOrderCodeGenerator.create(DateUtil.string2Date("2020-11-11 12:31:23", DatePattern.commonWithTime), 111141L));
		log.info(SalesOrderCodeGenerator.create(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L));
		log.info(SalesOrderCodeGenerator.create(DateUtil.string2Date("2035-04-22 08:31:35", DatePattern.commonWithTime), 203881L));
		log.info(SalesOrderCodeGenerator.create(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L));
	}
}
