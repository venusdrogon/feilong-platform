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
	public void getPropertiesValue() throws IOException{
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
