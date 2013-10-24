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

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jan 12, 2013 10:05:17 PM
 */
public class JsonFormatUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(JsonFormatUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.lang.Object)}.
	 */
	@Test
	public final void testFormatObject(){
		log.info(JsonFormatUtil.format(1));
	}

	@Test
	public final void testFormatObjectArray(){
		Integer[] integers = { 1, 2 };
		log.info(JsonFormatUtil.format(integers));
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Map)}.
	 */
	@Test
	public final void testFormatMapOfStringQextendsObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Map, java.lang.String)}.
	 */
	@Test
	public final void testFormatMapOfStringQextendsObjectString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Collection)}.
	 */
	@Test
	public final void testFormatCollectionOfQextendsObject(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.util.Collection, java.lang.String)}.
	 */
	@Test
	public final void testFormatCollectionOfQextendsObjectString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.commons.core.util.JsonFormatUtil#format(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public final void testFormatObjectString(){
		fail("Not yet implemented"); // TODO
	}
}
