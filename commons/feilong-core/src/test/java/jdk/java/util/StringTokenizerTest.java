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
package jdk.java.util;

import static org.junit.Assert.*;

import java.util.StringTokenizer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 14, 2013 11:33:28 PM
 */
public class StringTokenizerTest{

	private static final Logger	log	= LoggerFactory.getLogger(StringTokenizerTest.class);

	@Test
	public final void test(){
		StringTokenizer stringTokenizer = new StringTokenizer("a b");

		while (stringTokenizer.hasMoreElements()){
			Object object = (Object) stringTokenizer.nextElement();
			log.info(object.toString());
		}
	}
	// @Test
	// public final void test(){
	// String str = "a b";
	//
	// //StringTokenizer stringTokenizer = new StringTokenizer(str);
	//
	//
	// }
}
