/**
 * Copyright (c) 2012 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
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
