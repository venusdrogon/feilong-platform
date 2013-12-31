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
