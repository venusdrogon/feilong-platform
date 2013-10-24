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
package com.feilong.commons.core.awt;

import java.awt.image.BufferedImage;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 27 00:24:59
 */
public class ImageUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(ImageUtilTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.awt.ImageUtil#getBufferedImage(java.lang.String)}.
	 */
	@Test
	public void getBufferedImage(){
		String targetImg = "C:\\Users\\venusdrogon\\Desktop\\c\\饼状图pie.png";
		// 原始图片
		BufferedImage image_target = ImageUtil.getBufferedImage(targetImg);
	}
}
