/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 8, 2012 8:55:30 PM
 */
public class MapUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(MapUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.util.MapUtil#getMinValue(java.util.Map, java.lang.String[])}.
	 */
	@Test
	public void testGetMinValue(){

		Map<String, Integer> object = new HashMap<String, Integer>();

		object.put("a", 3007);
		object.put("b", 3001);
		object.put("c", 3002);
		object.put("d", 3003);
		object.put("e", 3004);
		object.put("f", 3005);
		object.put("g", -1005);

		String[] keys = { "a", "b", "d", "g", "m" };
		Integer minValue = MapUtil.getMinValue(object, keys);

		log.info(minValue + "");
	}
}
