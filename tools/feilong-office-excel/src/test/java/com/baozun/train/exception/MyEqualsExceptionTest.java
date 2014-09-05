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
package com.baozun.train.exception;

import org.junit.Test;

/**
 * 测试
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月4日 上午10:33:34
 * @since 1.0.8
 */
public class MyEqualsExceptionTest{

	/**
	 * 只是做测试用的
	 * 
	 * @param a
	 * @param b
	 */
	private void test(String a,String b){
		if (a.equals(b)){
			try{
				throw new MyEqualsException("a can not equals b");
			}catch (MyEqualsException e){
				e.printStackTrace();
			}
		}
		//do something
	}

	/**
	 * TestMyEqualsExceptionTest.
	 */
	@Test
	public void testMyEqualsExceptionTest(){
		test("JAVA", "JAVA");
		//test2(null, "JAVA");
	}

	private void test2(String a,String b){
		if (null == a){
			throw new NullPointerException("a can not null!!!");
		}
	}
}
