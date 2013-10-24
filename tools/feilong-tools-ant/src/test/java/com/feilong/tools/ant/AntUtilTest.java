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
package com.feilong.tools.ant;

import org.apache.tools.ant.Project;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Oct 31, 2012 12:36:05 AM
 */
public class AntUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(AntUtilTest.class);

	/**
	 * Test method for {@link com.feilong.tools.ant.AntUtil#executeTarget(java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public final void testExecuteTarget(){
		String antFilePath = "build.xml";
		String targetName = "test";
		int messageOutputLevel = Project.MSG_DEBUG;
		AntUtil.executeTarget(antFilePath, targetName, messageOutputLevel);
	}
}
