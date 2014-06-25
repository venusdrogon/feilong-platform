/*
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
package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 装箱 拆箱 测试
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月23日 下午11:19:19
 * @since 1.0.7
 */
public class BoxUnboxTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(BoxUnboxTest.class);

	/**
	 * If the value p being boxed is true, false, a byte, a char in the range /u0000 to
	 * /u007f, or an int or short number between -128 and 127, then let r1 and r2 be
	 * the results of any two boxing conversions of p. It is always the case that r1 ==
	 * r2.
	 * 
	 * @see Integer#valueOf(int)
	 */
	@Test
	public void name(){

		Integer i = 1024;
		Integer j = 1024;
		assertEquals(false, i == j);

		i = 100;
		j = 100;
		assertEquals(true, i == j);

		i = 200;
		j = 200;
		assertEquals(false, i == j);

		i = 128;
		j = 128;
		assertEquals(false, i == j);

		//		 Integer a=127;这种定义形式比较特殊。原因是编译过程中，编译器做了点小动作。
		//它会自动调用Integer.valueOf(int)方法将整型常量127 打包 (autoboxing)成包装器类。我们叫做自动包装机制 。
		//		 也就是说JVM运行的是Integer a=Integer.valueOf(127)；这条语句。
		i = 127;
		j = 127;
		assertEquals(true, i == j);

		Long l1 = 127L;
		Long l2 = 127L;
		assertEquals(true, l1 == l2);

		l1 = 1270L;
		l2 = 1270L;
		assertEquals(false, l1 == l2);

		l1 = 1L;
		l2 = 1L;
		assertEquals(true, l1 == l2);
	}
}
