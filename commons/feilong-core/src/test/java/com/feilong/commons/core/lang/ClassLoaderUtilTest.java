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
package com.feilong.commons.core.lang;

import org.junit.Test;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-27 上午12:42:55
 */
public class ClassLoaderUtilTest{

	/**
	 * {@link com.feilong.commons.core.lang.ClassLoaderUtil#getResource(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testGetResource(){
		System.out.println(ClassLoaderUtil.getResource(""));
		System.out.println(ClassLoaderUtil.getResource("com"));
	}

	@Test
	public void getClassPath(){
		System.out.println(ClassLoaderUtil.getClassPath());
	}

	@Test
	public void printClassLoader(){
		ClassLoaderUtil.printClassLoader();
	}

	@Test
	public void getResource(){
		ClassLoaderUtil.getResource("jstl-1.2", this.getClass());
	}
}
