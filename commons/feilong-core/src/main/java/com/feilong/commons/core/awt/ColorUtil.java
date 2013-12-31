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
