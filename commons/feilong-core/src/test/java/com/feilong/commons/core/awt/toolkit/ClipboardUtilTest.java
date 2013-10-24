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
package com.feilong.commons.core.awt.toolkit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-23 下午02:43:35
 * @since 1.0
 */
public class ClipboardUtilTest{

	private final static Logger	log	= LoggerFactory.getLogger(ClipboardUtilTest.class);

	/**
	 * {@link com.feilong.commons.core.awt.toolkit.ClipboardUtil#setClipboardContent(java.lang.String)} 的测试方法。
	 */
	@Test
	public final void testSetClipboardContents(){
		// FeiLongToolkit.setClipboardContents("金鑫data,2011-05-23");
		System.out.println(ClipboardUtil.getClipboardContent());
	}

	@Test
	public void test(){
		String aString = "L,M,S,XL,XS,XXL";
		StringBuilder stringBuilder = new StringBuilder();
		String[] strings = aString.split(",");
		stringBuilder.append("<enum type=\"string\">");
		for (String string : strings){
			stringBuilder.append("<item>" + string + "</item>");
		}
		stringBuilder.append("</enum>");
		ClipboardUtil.setClipboardContent(stringBuilder.toString());
		log.info(stringBuilder.toString());
	}
}
