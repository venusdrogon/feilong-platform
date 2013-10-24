/**
 * Copyright (c) 2008-2013 FeiLong Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	feilong makes no representations or warranties about the suitability of the software, either express or implied, 
 * 	including but not limited to the implied warranties of merchantability, fitness for a particular
 * 	purpose, or non-infringement.  
 * 	feilong shall not be liable for any damages suffered by licensee as a result of using, modifying or distributing
 * 	this software or its derivatives.
 * </p>
 */
package com.feilong.commons.core.util;

import java.util.Random;

/**
 * 飞龙随机工具类
 * 
 * @author 金鑫 2010-4-5 下午10:55:19
 * @since 1.0
 */
public final class RandomUtil{

	/** Don't let anyone instantiate this class. */
	private RandomUtil(){}

	/**
	 * 创建0-最大值之间的随机数
	 * 
	 * @param number
	 *            随机数最大值
	 * @return 创建0-最大值之间的随机数
	 */
	public static long createRandom(Number number){
		return (long) Math.floor(Math.random() * number.longValue());
	}

	/**
	 * 创建最小值和最大值之间的随机数
	 * 
	 * @param min
	 *            最小值
	 * @param max
	 *            最大值
	 * @return 创建最小值和最大值之间的随机数
	 */
	public static long createRandom(Number min,Number max){
		long cha = max.longValue() - min.longValue();
		return min.longValue() + createRandom(cha);
	}

	// ********************************************************************

	/**
	 * 生成一个指定长度大小的随机正整数
	 * 
	 * @param length
	 *            设定所取出随机数的长度。length小于11
	 * @return 返回生成的随机数
	 */
	public static long createRandomWithLength(int length){

		// 该值大于等于 0.0 且小于 1.0 正号的 double 值
		double random = Math.random();
		if (random < 0.1){// 可能出现 0.09346924349151808
			random = random + 0.1;
		}

		//
		long num = 1;
		for (int i = 0; i < length; ++i){
			num = num * 10;
		}
		//
		return (long) ((random * num));
	}

	// ****************************************************************

	/**
	 * 随机抽取字符串char,拼接成随机字符串
	 * 
	 * @param minLength
	 *            最小长度
	 * @param maxLength
	 *            最大长度
	 * @param str
	 *            被抽取的字符串
	 * @return 得到随机字符串
	 */
	public static String createRandomFromString(int minLength,int maxLength,String str){
		long length = createRandom(minLength, maxLength);
		return createRandomFromString((int) length, str);
	}

	/**
	 * 随机抽取字符串char,拼接成随机字符串
	 * 
	 * @param length
	 *            随机字符串长度
	 * @param str
	 *            被抽取的字符串
	 * @return 得到随机字符串
	 */
	public static String createRandomFromString(int length,String str){
		char ch[] = new char[length];
		Random random = null;
		int l = str.length();
		for (int i = 0; i < length; ++i){
			random = new Random();
			int index = random.nextInt(l);
			ch[i] = str.charAt(index);
		}
		return new String(ch);
	}

}
