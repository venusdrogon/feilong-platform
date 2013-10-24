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
package com.feilong.commons.core.configure;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.Test;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午03:41:14
 * @since 1.0
 */
public class PropertiesUtilTest{

	@Test
	public void getPropertiesValue(){
		String propertiesPath = "I:/Ebook/book.properties";
		InputStream inputStream = IOUtil.getFileInputStream(propertiesPath);
		Properties properties = PropertiesUtil.getProperties(inputStream);
		String key = "锦衣夜行";
		String value = properties.getProperty(key);
		try{
			for (Object iterable_element : properties.keySet()){
				System.out.println(new String(iterable_element.toString().getBytes(CharsetType.ISO_8859_1), CharsetType.GBK));
			}
		}catch (UnsupportedEncodingException e1){
			e1.printStackTrace();
		}
		// = PropertiesUtil.getPropertiesValue(FeiLongPropertiesUtilTest.class, propertiesPath, "锦衣夜行");
		try{
			inputStream.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
}
