package com.feilong.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.junit.Test;

import com.feilong.tools.PinYinUtil;

public class PinYinUtilTest{

	@Test
	public void convertChineseToPinYin(){
		String chinese = "金鑫 ai feilong  奔";
		System.out.println(PinYinUtil.convertChineseToPinYin(chinese));
	}

	@Test
	public void convertChineseToPinYin1(){
		String chinese = "ào";
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
		System.out.println(buffer.toString());
	}
}
