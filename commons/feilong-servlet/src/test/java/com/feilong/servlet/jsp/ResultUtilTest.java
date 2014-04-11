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
package com.feilong.servlet.jsp;

import static org.junit.Assert.*;

import java.beans.Introspector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Apr 10, 2014 11:24:38 PM
 */
public class ResultUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(ResultUtilTest.class);

	@Test
	public final void test(){
		
		// 获得一个字符串并将它转换成普通 Java 变量名称大写形式的实用工具方法。这通常意味着将首字符从大写转换成小写，但在（不平常的）特殊情况下，当有多个字符且第一个和第二个字符都是大写字符时，不执行任何操作。
		// 因此 "FooBah" 变成 "fooBah"，"X" 变成 "x"，但 "URL" 仍然是 "URL"。



		log.info(Introspector.decapitalize("Jinxin"));
	}
}
