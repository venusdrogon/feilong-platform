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
package elsepackage.code;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 3, 2013 5:14:45 PM
 */
public class CodeGeneratorTest{

	private static final Logger	log	= LoggerFactory.getLogger(CodeGeneratorTest.class);

	/**
	 * Test method for {@link elsepackage.code.CodeGenerator.shop.utils.SalesOrderCodeGenerator#createOrderCode(java.lang.Long)}.
	 */
	@Test
	public void createOrderCode(){
		// log.info(CodeGenerator.createOrderCode(111121L));
		// log.info(CodeGenerator.createOrderCode(1L));
		// log.info(CodeGenerator.createOrderCode(10L));
		// log.info(CodeGenerator.createOrderCode(101L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2013-01-01 00:08:02", DatePattern.commonWithTime), 1L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2020-11-11 12:31:23", DatePattern.commonWithTime), 111141L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2035-04-22 08:31:35", DatePattern.commonWithTime), 203881L));
		// log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L));
		log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L, 555L));
		log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2099-12-31 23:59:45", DatePattern.commonWithTime), 1161L, 5555555L));
	}

	@Test
	public void createOrderCode1(){
		for (int i = 0, j = 100; i < j; ++i){
			log.info(CodeGenerator.createOrderCode(DateUtil.string2Date("2044-12-31 10:21:46", DatePattern.commonWithTime), 35191L, 555L));
		}
	}

	@Test
	public void createReturnOrderCode(){
		log.info(CodeGenerator.createReturnOrderCode(111121L, 5555555L));
	}

	@Test
	public void createTradeNo(){
		log.info(CodeGenerator.createTradeNo(5545L, 88));
		log.info(CodeGenerator.createTradeNo(5545L, 1));
	}
}
