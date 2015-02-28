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
package com.feilong.commons.core.awt;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.feilong.commons.core.ConfigConstants;
import com.feilong.commons.core.enumeration.FontType;

/**
 * 验证码工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-14 下午01:43:06
 * @since 1.0.0
 * @deprecated 不推荐使用,可能调整
 */
@Deprecated
public final class ValidateCodeUtil{

	/** 验证码用到的字体 //\u534e\u6587\u884c\u6977 华文行楷 //\u6977\u4f53 楷体. */
	private static String[]	fontNamesForValidateCode	= { FontType.VERDANA, FontType.VERDANA };

	/** 验证码用到的字体颜色,随机获取. */
	private static Color[]	colorsForValidateCode		= { new Color(44, 188, 17), new Color(55, 55, 55), new Color(251, 0, 254) };

	/** Don't let anyone instantiate this class. */
	private ValidateCodeUtil(){
		//AssertionError不是必须的。但它可以避免不小心在类的内部调用构造器。保证该类在任何情况下都不会被实例化。
		//see 《Effective Java》 2nd
		throw new AssertionError("No " + getClass().getName() + " instances for you!");
	}

	/**
	 * 生成验证码字符串.
	 * 
	 * @param showCodeCount
	 *            显示字符数量
	 * @return 生成验证码字符串
	 */
	public static String generateValidateCode(int showCodeCount){
		// 备选字符的长度
		int length = ConfigConstants.VALIDATECODE_NUMBERSANDLITTLELETTERS.length();
		// 创建随机类的实例
		Random random = new Random();
		// 保存生成的汉字字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < showCodeCount; ++i){
			int start = random.nextInt(length);
			String rand = ConfigConstants.VALIDATECODE_NUMBERSANDLITTLELETTERS.substring(start, start + 1);
			stringBuilder.append(rand);
		}
		return stringBuilder.toString();
	}

	/**
	 * 绘制文字,并返回BufferedImage.
	 * 
	 * @param sRand
	 *            要绘制的文字
	 * @param width
	 *            设置图片的长宽
	 * @param height
	 *            设置图片的长宽
	 * @return the buffered image after graphics
	 */
	public static BufferedImage getBufferedImageAfterGraphics(String sRand,int width,int height){
		// ----------------------------------------------------------------------------------------
		// 创建内存图像
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics2D graphics2d = bufferedImage.createGraphics();
		// 设定图像背景色
		graphics2d.setColor(Color.white);
		graphics2d.setStroke(new BasicStroke(1));
		graphics2d.fillRect(0, 0, width, height);
		// ----------------------------------------------------------------------------------------
		// 在图片背景上增加噪点
		// graphics2d.setColor(colors[random.nextInt(colors.length)]);
		// graphics2d.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		// for (int i = 0; i < 6; ++i){
		// graphics2d.drawString("*********************************************", 0, 5 * (i + 2));
		// }
		// ----------------------------------------------------------------------------------------
		char[] chars = sRand.toCharArray();
		for (int i = 0; i < chars.length; ++i){
			// 设置字体的颜色
			graphics2d.setColor(getRandomColorsForValidateCode());
			// 设置字体
			graphics2d.setFont(new Font(getRandomFontNameForValidateCode(), Font.TRUETYPE_FONT, 20));
			// 将此汉字画到图片上
			// 要绘制的 string x 坐标 y 坐标
			graphics2d.drawString(String.valueOf(chars[i]), 18 * i + 12, 18);
		}
		// ----------------------------------------------------------------------------------------
		graphics2d.dispose();
		return bufferedImage;
	}

	/**
	 * 获得随机的验证码用到的字体.
	 * 
	 * @return 获得随机的验证码用到的字体
	 */
	private final static String getRandomFontNameForValidateCode(){
		// 随机字体长度
		int fontTypesLength = fontNamesForValidateCode.length;
		// 创建随机类的实例
		Random random = new Random();
		return fontNamesForValidateCode[random.nextInt(fontTypesLength)];
	}

	/**
	 * 获得验证码用的随机字体.
	 * 
	 * @return the random colors for validate code
	 */
	private static Color getRandomColorsForValidateCode(){
		// 创建随机类的实例
		Random random = new Random();
		// 随机颜色长度
		int colorsLength = colorsForValidateCode.length;
		return colorsForValidateCode[random.nextInt(colorsLength)];
	}
}