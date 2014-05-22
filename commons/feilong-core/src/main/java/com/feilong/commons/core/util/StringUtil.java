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
package com.feilong.commons.core.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.bean.BeanUtil;
import com.feilong.commons.core.enumeration.CharsetType;

/**
 * {@link String}工具类,可以 查询,截取,format,转成16进制码
 * 
 * @author 金鑫 2010-2-9 上午09:53:37
 * @since 1.0.0
 */
public final class StringUtil{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(StringUtil.class);

	/** Don't let anyone instantiate this class. */
	private StringUtil(){}

	/**
	 * 查找子字符串在 字符串中出现的 次数
	 * 
	 * <pre>
	 *  StringUtil.searchTimes("xin", "xin")
	 *  return  1
	 *  
	 * StringUtil.searchTimes("xiiiin", "ii")
	 *  return  2
	 * 
	 * </pre>
	 * 
	 * @param source
	 *            查找的源字符串
	 * @param target
	 *            目标子串
	 * @return count of target string in source
	 * @since 1.0.2
	 */
	public static int searchTimes(String source,String target){
		if (null == source){
			throw new IllegalArgumentException("source can't be null!");
		}
		if (null == target){
			throw new IllegalArgumentException("target can't be null!");
		}

		// times 计数器
		int count = 0;

		// while 循环 点
		int j = 0;

		// 开始搜索的索引位置
		int fromIndex = 0;

		int sourceLength = source.length();
		int targetLength = target.length();

		// 刚开始从 0的地方查找起
		while (j != sourceLength){

			// 从指定的索引开始 返回索引位置
			int i = source.indexOf(target, fromIndex);
			if (i != -1){

				// 一旦发现 查找到,下次 循环从找到的地方开始循环
				// 查找 从 找到的地方 开始查找
				j = i + targetLength;

				fromIndex = i + targetLength;

				// 总数 ++
				count++;
			}else{
				// 如果发现找不到了 就退出循环
				break;
			}
		}
		return count;
	}

	/**
	 * 给一串字符串前后增加两个引号<br>
	 * 
	 * <pre>
	 * String text = &quot;jinxin.feilong&quot;;
	 * log.info(StringUtil.addDoubleQuotes(text));
	 * 
	 * 结果:  "jinxin.feilong"
	 * </pre>
	 * 
	 * @param text
	 *            任意的字符串
	 * @return "\"" + text + "\""
	 */
	public static String addDoubleQuotes(String text){
		return "\"" + text + "\"";
	}

	/**
	 * 拼接任意字符串<br>
	 * 
	 * <pre>
	 * StringUtil.join(true, &quot;_&quot;, &quot;a&quot;, &quot;2&quot;)
	 * 
	 * return  a_2
	 * </pre>
	 * 
	 * @param isJoinBlankOrNull
	 *            是否拼接空白和null,如果true 则拼接
	 * @param separator
	 *            分隔符
	 * @param elements
	 *            任意字符串
	 * @return the string
	 */
	public final static String join(boolean isJoinBlankOrNull,String separator,String...elements){
		if (Validator.isNullOrEmpty(elements)){
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		int length = elements.length;
		for (int i = 0; i < length; ++i){
			String string = elements[i];
			boolean isCanJoin = true;
			// 如果 是null 不忽略 那么就不拼接
			if (Validator.isNullOrEmpty(string) && !isJoinBlankOrNull){
				isCanJoin = false;
			}
			if (isCanJoin){
				// 前面已经品结果字符串了
				if (stringBuilder.length() > 0){
					stringBuilder.append(separator);
				}
				stringBuilder.append(string);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 获得字段 getter 方法名称.
	 * 
	 * @param fieldName
	 *            the field name
	 * @return the getter method name
	 */
	// XXX boolean -->is这种方法
	public static String getGetterMethodName(String fieldName){
		return "get" + StringUtil.firstCharToUpperCase(fieldName);
	}

	/**
	 * 单词首字母大写 比如jinxin---->Jinxin
	 * 
	 * <pre>
	 * StringUtils.capitalize(null)  = null
	 * StringUtils.capitalize(&quot;&quot;)    = &quot;&quot;
	 * StringUtils.capitalize(&quot;cat&quot;) = &quot;Cat&quot;
	 * StringUtils.capitalize(&quot;cAt&quot;) = &quot;CAt&quot;
	 * </pre>
	 * 
	 * @param word
	 *            单词
	 * @return 单词首字母大写
	 */
	public final static String firstCharToUpperCase(String word){
		return StringUtils.capitalize(word);
	}

	/**
	 * 单词首字母小写 比如Jinxin---->jinxin
	 * 
	 * <pre>
	 * StringUtils.capitalize(null)  = null
	 * StringUtils.capitalize(&quot;&quot;)    = &quot;&quot;
	 * StringUtils.capitalize(&quot;Jinxin&quot;) = &quot;jinxin&quot;
	 * StringUtils.capitalize(&quot;CAt&quot;) = &quot;cAt&quot;
	 * </pre>
	 * 
	 * @param word
	 *            单词
	 * @return 单词首字母小写
	 */
	public final static String firstCharToLowerCase(String word){
		return StringUtils.uncapitalize(word);
	}

	/**
	 * 判断字符串是否包含小数点.
	 * 
	 * @param text
	 *            字符串
	 * @return 有返回true
	 */
	public final static boolean isContainPoint(String text){
		return isContain(text, ".");
	}

	/**
	 * 判断一个字符串内是否包含另外的字符串.
	 * 
	 * @param text
	 *            原始字符串 jinxin,自动转成string
	 * @param beIncludedString
	 *            被包含的字符串 in
	 * @return 包含返回true,如果text 为null 返回false
	 */
	public final static boolean isContain(Object text,String beIncludedString){
		if (null == text){
			log.warn("the param \"text\" is null,default return false");
			return false;
		}
		int indexOf = text.toString().indexOf(beIncludedString);
		return indexOf != -1;
	}

	/**
	 * 忽略 大小写 是否包含<br>
	 * 
	 * <pre>
	 * StringUtil.isContainIgnoreCase(null, &quot;&quot;)  return false
	 * StringUtil.isContainIgnoreCase(text, null) return false
	 * StringUtil.isContainIgnoreCase(text, &quot;&quot;) return true
	 * StringUtil.isContainIgnoreCase(text, &quot;feilong&quot;) return true
	 * StringUtil.isContainIgnoreCase(text, &quot;feilong1&quot;)  return false
	 * StringUtil.isContainIgnoreCase(text, &quot;feiLong&quot;)  return true
	 * 
	 * </pre>
	 * 
	 * @param text
	 *            the text
	 * @param beIncludedString
	 *            the be included string
	 * @return <ul>
	 *         <li>如果 null==text, return false</li>
	 *         <li>如果 null==beIncludedString, return false</li>
	 *         <li>两个值 转成 小写 ,判断是否包含</li>
	 *         </ul>
	 */
	public final static boolean isContainIgnoreCase(Object text,String beIncludedString){
		if (null == text){
			log.warn("the param \"text\" is null,default return false");
			return false;
		}
		if (null == beIncludedString){
			log.warn("the param \"beIncludedString\" is null,default return false");
			return false;
		}
		return isContain(text.toString().toLowerCase(), beIncludedString.toLowerCase());
	}

	// ********************************replace************************************************
	/**
	 * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。.
	 * 
	 * @param content
	 *            需要被替换的字符串
	 * @param regex
	 *            用来匹配此字符串的正则表达式
	 * @param replacement
	 *            用来替换每个匹配项的字符串
	 * @return 所得String,如果传过来的内容是空,则返回""
	 */
	public final static String replaceAll(Object content,String regex,String replacement){
		if (null == content){
			return "";
		}
		return content.toString().replaceAll(regex, replacement);
	}

	/**
	 * 使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。<br>
	 * 该替换从字符串的开头朝末尾执行，例如，用 "b" 替换字符串 "aaa" 中的 "aa" 将生成 "ba" 而不是 "ab"。
	 * 
	 * <pre>
	 * 处理了replacement为空的情况
	 * </pre>
	 * 
	 * .
	 * 
	 * @param content
	 *            内容
	 * @param target
	 *            要被替换的 char 值序列
	 * @param replacement
	 *            char 值的替换序列
	 * @return 所有匹配字面值目标序列的子字符串
	 */
	public final static String replace(Object content,String target,Object replacement){
		if (null == content){
			return "";
		}
		// 替换序列是null
		if (Validator.isNullOrEmpty(replacement)){
			replacement = "";
		}
		return content.toString().replace(target, replacement.toString());
	}

	/**
	 * 替换,将内容content 中的需要被替换的内容target 替换成bean里面的filedName属性值.
	 * 
	 * @param content
	 *            内容
	 * @param target
	 *            需要被替换的内容
	 * @param bean
	 *            bean
	 * @param filedName
	 *            字段名称
	 * @return 替换,将内容content 中的需要被替换的内容target 替换成bean里面的filedName属性值
	 */
	public final static String replace(Object content,String target,Object bean,String filedName){
		String replacement = "";
		// 替换序列是null
		if (Validator.isNotNullOrEmpty(bean)){
			Object filedValue = BeanUtil.getProperty(bean, filedName);
			if (null != filedValue){
				replacement = filedValue.toString();
			}
		}
		return replace(content, target, replacement);
	}

	// ********************************replace************************************************
	/**
	 * 测试此字符串是否以指定的前缀开始。.
	 * 
	 * @param value
	 *            value
	 * @param prefix
	 *            前缀
	 * @return 如果参数表示的字符序列是此字符串表示的字符序列的前缀，则返回 true；否则返回 false。还要注意，如果参数是空字符串，或者等于此 String 对象（用 equals(Object) 方法确定），则返回 true。
	 */
	public final static boolean startsWith(Object value,String prefix){
		return ObjectUtil.toString(value).startsWith(prefix);
	}

	/**
	 * 字符串和数字相加(一般生成流水号使用).
	 * 
	 * @param str
	 *            字符串
	 * @param i
	 *            数字
	 * @return 字符串和数字相加(一般生成流水号使用)
	 */
	public final static String stringAddInt(String str,int i){
		int length = str.length();
		String pattern = "";
		for (int j = 0; j < length; ++j){
			pattern += "0";
		}
		return NumberUtil.toString(Integer.parseInt(str) + i, pattern);
	}

	// ********************************substring************************************************
	/**
	 * [截取]从指定索引处(beginIndex)的字符开始，直到此字符串末尾。 <br>
	 * 调用text.substring(beginIndex)
	 * 
	 * <pre>
	 * substring("jinxin.feilong",6)=====>.feilong
	 * </pre>
	 * 
	 * @param text
	 *            内容
	 * @param beginIndex
	 *            从指定索引处
	 * @return <ul>
	 *         <li>如果 Validator.isNull(t),return null</li>
	 *         <li>else,return text.substring(beginIndex)</li>
	 *         </ul>
	 */
	public final static String substring(Object text,int beginIndex){
		String t = ObjectUtil.toString(text);
		if (Validator.isNullOrEmpty(t)){
			return null;
		}
		return t.substring(beginIndex);
	}

	/**
	 * [截取]从开始位置(startIndex),截取固定长度(length)字符串<br>
	 * 
	 * <pre>
	 * StringUtil.substring("jinxin.feilong", 6, 2)=====>.m
	 * </pre>
	 * 
	 * @param textObject
	 *            被截取文字
	 * @param startIndex
	 *            索引开始位置,0开始
	 * @param length
	 *            长度 >=1 1个 即本身 <br>
	 *            正常情况下,即返回出来的字符串长度
	 * @return <pre>
	 * Validator.isNull(textValue),	return null
	 * startIndex>textLength - 1,			return null
	 * startIndex==textLength - 1,			return substringLast(textString, 1)
	 * length<1,							return null
	 * 1 == length,							return textString.substring(startIndex, startIndex + 1)
	 * remainLength > length,				return textString.substring(startIndex, startIndex + length)
	 * remainLength <= length,				return textString.substring(startIndex)
	 * </pre>
	 */
	public final static String substring(Object textObject,int startIndex,int length){
		String returnValue = null;
		if (Validator.isNullOrEmpty(textObject)){
			return null;
		}
		String textString = ObjectUtil.toString(textObject);
		int textLength = textString.length();
		// 索引位置必须小于长度
		if (startIndex > textLength - 1){
			return null;
		}else if (startIndex == textLength - 1){// 最后一位
			return substringLast(textString, 1);
		}else if (length < 1){// 截取长度必须>=1
			return null;
		}else if (1 == length){// 截取1个 即本身
			// 截取本身索引的位置
			return textString.substring(startIndex, startIndex + 1);
		}else{
			// 剩余可以被截取的字符串长度
			int remainLength = textLength - startIndex;
			// 剩余字符长长度比截取的长度长
			if (remainLength > length){
				// 结束的索引
				int endIndex = startIndex + length;
				// 此方法最后一个不包含
				returnValue = textString.substring(startIndex, endIndex);
			}else{
				// 没有需要被截取的长
				returnValue = textString.substring(startIndex);
			}
		}
		return returnValue;
	}

	/**
	 * [截取]:调用{@link #substring(String, String, int)}, 默认 shift=0
	 * 
	 * <pre>
	 * substring(&quot;jinxin.feilong&quot;,&quot;.&quot;)======&gt;&quot;.feilong&quot;
	 * </pre>
	 * 
	 * @param text
	 *            text
	 * @param beginString
	 *            beginString开始截取的字符串
	 * @return 调用{@link #substring(String, String, int)}, 默认 shift=0
	 */
	public final static String substring(String text,String beginString){
		return substring(text, beginString, 0);
	}

	/**
	 * [截取]:从第一次出现字符串位置开始(包含)截取到最后,shift表示向前或者向后挪动位数,<br>
	 * beginIndex= text.indexOf(beginString) + shift;<br>
	 * return text.substring(beginIndex);
	 * 
	 * <pre>
	 * substring(&quot;jinxin.feilong&quot;,&quot;.&quot;,0)======&gt;&quot;.feilong&quot;
	 * substring(&quot;jinxin.feilong&quot;,&quot;.&quot;,1)======&gt;&quot;feilong&quot;
	 * </pre>
	 * 
	 * @param text
	 *            text
	 * @param beginString
	 *            beginString
	 * @param shift
	 *            负数表示向前,整数表示向后,0表示依旧从自己的位置开始算起
	 * @return <pre>
	 * Validator.isNull(text),return null
	 * Validator.isNull(beginString),return null
	 * text.indexOf(beginString)==-1,return null
	 * beginIndex + shift<0,throw new IllegalArgumentException
	 * beginIndex + shift > text.length(),return null 
	 * else,return text.substring(beginIndex + shift)
	 * </pre>
	 */
	public final static String substring(String text,String beginString,int shift){
		if (Validator.isNullOrEmpty(text)){
			return null;
		}else if (Validator.isNullOrEmpty(beginString)){
			return null;
		}
		int beginIndex = text.indexOf(beginString);
		// 查不到指定的字符串
		if (beginIndex == -1){
			return null;
		}
		int startIndex = beginIndex + shift;
		if (startIndex < 0){

			String logInfo = StringBuilderUtil.append("beginIndex + shift <0,", "beginIndex:", beginIndex, ",shift:" + shift, ",text:"
					+ text, ",text.length:", text.length());
			throw new IllegalArgumentException(logInfo);
		}else if (startIndex > text.length()){
			String logInfo = StringBuilderUtil.append(
					"beginIndex + shift > text.length(),",
					"beginIndex:",
					beginIndex,
					",shift:" + shift,
					",text:" + text,
					",text.length:",
					text.length());
			log.info(logInfo);
			return null;
		}
		// 索引从0 开始
		return text.substring(startIndex);
	}

	/**
	 * [截取]:从开始的字符串到结束的字符串中间的字符串(包括开始的字符串startString),不包含结束的endString.
	 * 
	 * @param text
	 *            文字
	 * @param startString
	 *            开始的字符串,null表示从开头开始截取
	 * @param endString
	 *            结束的字符串
	 * @return <pre>
	 * Validator.isNull(text),return null;
	 * Validator.isNull(startString),return text.substring(0, text.indexOf(endString))
	 * 
	 * </pre>
	 */
	public final static String substring(String text,String startString,String endString){
		if (Validator.isNullOrEmpty(text)){
			return null;
		}else if (Validator.isNullOrEmpty(startString)){
			return text.substring(0, text.indexOf(endString));
		}
		int beginIndex = text.indexOf(startString);
		int endIndex = text.indexOf(endString);
		return text.substring(beginIndex, endIndex);
	}

	/**
	 * [截取]:截取文字最后几个字符串.
	 * 
	 * @param text
	 *            文字
	 * @param lastLenth
	 *            最后的位数
	 * @return 截取文字最后几个字符串
	 */
	public final static String substringLast(String text,int lastLenth){
		return text.substring(text.length() - lastLenth);
	}

	/**
	 * [截取]:去除最后几位.
	 * 
	 * @param text
	 *            文字
	 * @param lastLenth
	 *            最后的位数
	 * @return 去除最后几位,如果text是空,则返回""
	 */
	public final static String substringWithoutLast(String text,int lastLenth){
		if (Validator.isNullOrEmpty(text)){
			return null;
		}
		return text.substring(0, text.length() - lastLenth);
	}

	// ********************************************************************************
	/**
	 * 字符串转换成byte数组.
	 * 
	 * @param value
	 *            字符串
	 * @return byte数组
	 */
	public final static byte[] toBytes(String value){
		return value.getBytes();
	}

	/**
	 * 字符串转换成byte数组.
	 * 
	 * @param value
	 *            字符串
	 * @param charsetName
	 *            受支持的 charset 名称,比如 utf-8
	 * @return 所得 byte 数组
	 */
	public final static byte[] toBytes(String value,String charsetName){
		try{
			return value.getBytes(charsetName);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将string 类型值转成泛型,一般用于配置文件读取数据.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param value
	 *            值
	 * @param class1
	 *            the class1
	 * @return <pre>
	 * if (class1 == String.class){
	 * 	return (T) value;
	 * }else if (class1 == Boolean.class){
	 * 	return (T) ObjectUtil.objectToBoolean(value);
	 * }else if (class1 == Integer.class){
	 * 	return (T) ObjectUtil.objectToInteger(value);
	 * }
	 * </pre>
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T convertStringToT(String value,Class<?> class1){
		return (T) ObjectUtil.toT(value, class1);
	}

	/**
	 * 将字符串分隔成 字符串数组.
	 * 
	 * @param value
	 *            value
	 * @param spliter
	 *            分隔符
	 * @return 如果value 是null,返回null
	 */
	public final static String[] splitToStrings(String value,String spliter){
		if (null != value){
			String[] strings = value.split(spliter);
			return strings;
		}
		return null;
	}

	/**
	 * 将字符串分隔成 字符串数组.
	 * 
	 * @param value
	 *            value
	 * @param spliter
	 *            分隔符
	 * @return 如果value 是null,返回null
	 */
	public final static Integer[] splitToIntegers(String value,String spliter){
		if (null != value){
			String[] strings = value.split(spliter);
			return ArrayUtil.toIntegers(strings);
		}
		return null;
	}

	/**
	 * 转成T.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param value
	 *            字符串
	 * @param spliter
	 *            分隔符
	 * @param typeClass
	 *            类型,指明 T 类型<br>
	 *            Temp support only:String.class and Integer.class
	 * @return 泛型数组
	 */
	@SuppressWarnings("unchecked")
	public final static <T> T[] splitToT(String value,String spliter,Class<?> typeClass){
		if (typeClass == String.class){
			return (T[]) splitToStrings(value, spliter);
		}else if (typeClass == Integer.class){
			return (T[]) splitToIntegers(value, spliter);
		}
		throw new IllegalArgumentException("Param typeClass don't support,Temp support only:String.class and Integer.class");
	}

	/**
	 * 格式化字符串
	 * <ul>
	 * <li>StringUtil.format("%03d", 1)不能写成 StringUtil.format("%03d", "1")</li>
	 * </ul>
	 * %index$开头，index从1开始取值，表示将第index个参数拿进来进行格式化。<br>
	 * 对整数进行格式化:格式化字符串由4部分组成:%[index$][标识][最小宽度]转换方式<br>
	 * 对浮点数进行格式化:%[index$][标识][最少宽度][.精度]转换方式<br>
	 * 转换方式 转换符和标志的说明<br>
	 * 
	 * <h4>转换符</h4>
	 * 
	 * <pre>
	 * %s	字符串类型	"mingrisoft"
	 * %c	字符类型	'm'
	 * %b	布尔类型	true
	 * %d	整数类型（十进制）	99
	 * %x	整数类型（十六进制）	FF
	 * %o	整数类型（八进制）	77
	 * %f	浮点类型	99.99
	 * %a	十六进制浮点类型	FF.35AE
	 * %e	指数类型	9.38e+5
	 * %g	通用浮点类型（f和e类型中较短的）
	 * %h	散列码
	 * %%	百分比类型	％
	 * %n	换行符
	 * %tx	日期与时间类型（x代表不同的日期与时间转换符
	 * </pre>
	 * 
	 * <h4>标志</h4>
	 * 
	 * <pre>
	 * +	为正数或者负数添加符号	("%+d",15)	+15
	 * -	左对齐	("%-5d",15)	|15   |  不可以与“用0填充”同时使用
	 * 0	数字前面补0	("%04d", 99)	0099
	 * 空格	在整数之前添加指定数量的空格	("% 4d", 99)	|  99|
	 * ,	以“,”对数字分组	("%,f", 9999.99)	9,999.990000
	 * (	使用括号包含负数	("%(f", -99.99)	(99.990000)
	 * #	如果是浮点数则包含小数点，如果是16进制或8进制则添加0x或0
	 * <	格式化前一个转换符所描述的参数	("%f和%<3.2f", 99.45)	99.450000和99.45
	 * $	被格式化的参数索引	("%1$d,%2$s", 99,"abc")	99,abc
	 * </pre>
	 * 
	 * @param format
	 *            the format
	 * @param args
	 *            the args
	 * @return A formatted string
	 * @see java.util.Formatter
	 * @see String#format(String, Object...)
	 * @since JDK 1.5
	 */
	public static String format(String format,Object...args){
		return String.format(format, args);
	}

	/**
	 * 将原始字符串 转成 大写的HexString 网友gdpglc的思路.
	 * 
	 * @param original
	 *            原始字符串
	 * @return the string
	 */
	public final static String toHexStringUpperCase(String original){
		// 先 Charset.defaultCharset() 如果有异常 用 ISO-8859-1
		String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(original.getBytes());
		log.debug("original:{},hexStringUpperCase:{}", original, hexStringUpperCase);
		return hexStringUpperCase;
	}

	/**
	 * 将原始字符串 转成 大写的HexString 网友gdpglc的思路.
	 * 
	 * @param original
	 *            原始字符串
	 * @param charsetName
	 *            字符集 {@link CharsetType}
	 * @return the string
	 */
	public final static String toHexStringUpperCase(String original,String charsetName){
		try{
			String hexStringUpperCase = ByteUtil.bytesToHexStringUpperCase(original.getBytes(charsetName));
			log.debug("original:{},hexStringUpperCase:{}", original, hexStringUpperCase);
			return hexStringUpperCase;
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将 转成 大写的HexString原始字符串.
	 * 
	 * @param hexStringUpperCase
	 *            大写的HexString
	 * @return the string
	 */
	public final static String toOriginal(String hexStringUpperCase){
		byte[] hexBytesToBytes = ByteUtil.hexBytesToBytes(hexStringUpperCase.getBytes());
		String original = new String(hexBytesToBytes);
		log.debug("hexStringUpperCase:{},original:{}", hexStringUpperCase, original);
		return original;
	}

	/**
	 * 将 转成 大写的HexString原始字符串.
	 * 
	 * @param hexStringUpperCase
	 *            the hex string upper case
	 * @param charsetName
	 *            指定字符集
	 * @return the string
	 */
	public final static String toOriginal(String hexStringUpperCase,String charsetName){
		byte[] hexBytesToBytes = ByteUtil.hexBytesToBytes(hexStringUpperCase.getBytes());
		String original = null;
		try{
			original = new String(hexBytesToBytes, charsetName);
		}catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		log.debug("hexStringUpperCase:{},original:{}", hexStringUpperCase, original);
		return original;
	}
}
