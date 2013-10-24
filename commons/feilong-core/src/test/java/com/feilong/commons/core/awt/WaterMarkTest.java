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

import java.awt.Color;
import java.awt.Font;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012 1 26 18:48:11
 */
public class WaterMarkTest{

	@SuppressWarnings("unused")
	private static final Logger	log			= LoggerFactory.getLogger(WaterMarkTest.class);

	private String				targetImg	= "E:\\DataCommon\\test\\background.png";

	private String				pressText	= "鑫哥爱feilong";

	@Test
	public void pressImage(){
		String pressImg = "E:\\DataFixed\\Material\\avatar\\1.印章 32 74.png";
		int x = 0;
		int y = 0;
		String fileName = "E:\\DataCommon\\test\\b.png";
		WaterMark.pressImage(targetImg, pressImg, x, y, fileName);
	}

	@Test
	public void pressText(){

		int x = 200;
		int y = 30;

		// 默认 雅黑 12 黑色
		Font font = FontUtil.getFont_YaHei_Plain_12();
		Color color = ColorUtil.getColor("000000");

		String fileName = "E:\\DataCommon\\test\\b.png";
		WaterMark.pressText(targetImg, pressText, font, color, x, y, fileName);
	}
}
