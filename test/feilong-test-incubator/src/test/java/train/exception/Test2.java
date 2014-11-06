package train.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Sep 4, 2014 11:55:35 AM
 */
public class Test2{

	private static final Logger	log	= LoggerFactory.getLogger(Test2.class);

	private String test(String a,String b){
		try{
			if (a.equals(b)){
				throw new MyEqualsException("a can not equals b");
			}
			return "1";
		}catch (MyEqualsException e){
			e.printStackTrace();
			return "2";
		}finally{
			return "3";
		}
	}

	/**
	 * TestMyEqualsExceptionTest.
	 */
	@Test
	public void testMyEqualsExceptionTest(){
		log.info(test("JAVA", "JAVA"));
		log.info(test("JAVA", "JAVA1"));
	}
}
