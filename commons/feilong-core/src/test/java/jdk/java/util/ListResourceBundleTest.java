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

import java.util.Enumeration;

import javax.accessibility.AccessibleResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 18, 2012 11:44:58 PM
 */
public class ListResourceBundleTest{

	private static final Logger	log	= LoggerFactory.getLogger(ListResourceBundleTest.class);

	@Test
	public void name(){
		SanguoListResourceBundle listResourceBundle = new SanguoListResourceBundle();

		Enumeration<String> keys = listResourceBundle.getKeys();

		while (keys.hasMoreElements()){
			String key = (String) keys.nextElement();
			log.info(key + ":" + listResourceBundle.getObject(key));
		}

	}
}
