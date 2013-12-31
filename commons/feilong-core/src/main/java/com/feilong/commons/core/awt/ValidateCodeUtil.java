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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.feilong.commons.core.PropertiesConstants;

/**
 *验证码工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-14 下午01:43:06
 * @since 1.0
 */
public class ValidateCodeUtil{

	/**
	 * 生成验证码字符串
	 * 
	 * @param showCodeCount
	 *            显示字符数量
	 * @return 生成验证码字符串
	 */
	public static String generateValidateCode(int showCodeCount){
		//备选字符的长度
		int length = PropertiesConstants.config_validateCode_numbersAndLittleLetters.length();
		//创建随机类的实例
		Random random = new Random();
		//保存生成的汉字字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < showCodeCount; ++i){
			int start = random.nextInt(length);
			String rand = PropertiesConstants.config_validateCode_numbersAndLittleLetters.substring(start, start + 1);
			stringBuilder.append(rand);
		}
		return stringBuilder.toString();
	}

	/**
	 * 绘制文字,并返回BufferedImage
	 * 
	 * @param sRand
	 *            要绘制的文字
	 * @param width
	 *            设置图片的长宽
	 * @param height
	 *            设置图片的长宽
	 * @return
	 */
	public static BufferedImage getBufferedImageAfterGraphics(String sRand,int width,int height){
		//----------------------------------------------------------------------------------------
		//创建内存图像
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics2D graphics2d = bufferedImage.createGraphics();
		//设定图像背景色	
		graphics2d.setColor(Color.white);
		graphics2d.setStroke(new BasicStroke(1));
		graphics2d.fillRect(0, 0, width, height);
		//----------------------------------------------------------------------------------------
		//在图片背景上增加噪点
		//		graphics2d.setColor(colors[random.nextInt(colors.length)]);
		//		graphics2d.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		//		for (int i = 0; i < 6; ++i){
		//			graphics2d.drawString("*********************************************", 0, 5 * (i + 2));
		//		}
		//----------------------------------------------------------------------------------------
		char[] chars = sRand.toCharArray();
		for (int i = 0; i < chars.length; ++i){
			//设置字体的颜色
			graphics2d.setColor(ColorUtil.getRandomColorsForValidateCode());
			//设置字体  
			graphics2d.setFont(new Font(FontUtil.getRandomFontNameForValidateCode(), Font.TRUETYPE_FONT, 20));
			//将此汉字画到图片上
			//要绘制的 string x 坐标 y 坐标
			graphics2d.drawString(String.valueOf(chars[i]), 18 * i + 12, 18);
		}
		//----------------------------------------------------------------------------------------
		graphics2d.dispose();
		return bufferedImage;
	}
}
