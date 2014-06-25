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
package com.feilong.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * pinyin4j 工具类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-8 下午06:45:23
 */
public class PinYinUtil{

	/**
	 * 将中文转成拼音.
	 * 
	 * @param chinese
	 *            the chinese
	 * @return the string
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
