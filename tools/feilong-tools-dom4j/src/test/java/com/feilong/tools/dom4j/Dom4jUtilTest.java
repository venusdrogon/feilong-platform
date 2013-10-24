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
package com.feilong.tools.dom4j;

import static org.junit.Assert.*;

import org.dom4j.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a> 
 * @version 1.0 Oct 23, 2013 1:32:14 PM  
 */
public class Dom4jUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(Dom4jUtilTest.class);

	/**
	 * Test method for {@link com.feilong.tools.dom4j.Dom4jUtil#getDocument(java.lang.String)}.
	 */
	@Test
	public final void testGetDocumentString(){
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link com.feilong.tools.dom4j.Dom4jUtil#getDocument(java.io.InputStream)}.
	 */
	@Test
	public final void testGetDocumentInputStream(){
		Document document = Dom4jUtil.getDocument("");
		
		 
	}
}
