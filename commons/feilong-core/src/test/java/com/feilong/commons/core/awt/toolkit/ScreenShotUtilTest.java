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
package com.feilong.commons.core.awt.toolkit;

import java.util.Date;

import org.junit.Test;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.ImageType;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-30 下午01:36:20
 * @since 1.0
 */
public class ScreenShotUtilTest{

	/**
	 * {@link com.feilong.commons.core.awt.toolkit.ScreenShotUtil#screenshot(java.lang.String, java.lang.String)} 的测试方法。
	 */
	@Test
	public final void testScreenshot(){
		// 根据文件前缀变量和文件格式变量，自动生成文件名
		String name = "e:/" + DateUtil.date2String(new Date(), DatePattern.timestamp) + "." + "png"; //"png"
		ScreenShotUtil.screenshot(name, ImageType.PNG);
	}
}
