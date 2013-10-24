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
import java.util.Random;

/**
 * 飞龙 颜色工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-4-15 上午01:09:40
 * @since 1.0
 */
public class ColorUtil{

	/**
	 * 验证码用到的字体颜色,随机获取
	 */
	public static Color[]	colorsForValidateCode	= { new Color(44, 188, 17), new Color(55, 55, 55), new Color(251, 0, 254) };

	/**
	 * 获得验证码用的随机字体
	 * 
	 * @return
	 */
	public static Color getRandomColorsForValidateCode(){
		//创建随机类的实例
		Random random = new Random();
		//随机颜色长度
		int colorsLength = ColorUtil.colorsForValidateCode.length;
		return ColorUtil.colorsForValidateCode[random.nextInt(colorsLength)];
	}

	/**
	 * 通过16进制颜色字符串 获得颜色
	 * 
	 * @param hex
	 *            16进制颜色 字符串 <br>
	 *            比如:FF00FF
	 * @return
	 */
	public static Color getColor(String hex){
		return Color.decode("0x" + hex);
	}
}
