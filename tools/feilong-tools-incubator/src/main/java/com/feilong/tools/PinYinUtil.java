/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 *pinyin4j 工具类
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-8 下午06:45:23
 */
public class PinYinUtil{

	/**
	 * 将中文转成拼音
	 * 
	 * @param chinese
	 * @return
	 */
	public static String convertChineseToPinYin(String chinese){
		StringBuffer buffer = new StringBuffer();
		HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
		hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE); // 转化为小写
		hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE); // 不带声调
		hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V); // ü用v带替
		// *******************************************************************
		char[] c = chinese.toCharArray();
		try{
			for (int i = 0; i < c.length; i++){
				String[] s = PinyinHelper.toHanyuPinyinStringArray(c[i], hanyuPinyinOutputFormat);
				if (s != null){
					buffer.append(s[0]); // 这里一般数组长度为1，大于1是因为汉字可能会有多音字
				}else{
					buffer.append(c[i]); // 不是汉字的情况
				}
			}
		}catch (BadHanyuPinyinOutputFormatCombination e){
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
