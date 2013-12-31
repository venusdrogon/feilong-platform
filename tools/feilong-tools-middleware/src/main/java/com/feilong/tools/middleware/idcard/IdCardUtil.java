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
package com.feilong.tools.middleware.idcard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.text.DateFormatUtil;
import com.feilong.commons.core.util.RegexUtil;
import com.feilong.commons.core.util.Validator;

/**
 * 飞龙身份证类,验证及读取相关信息
 * 
 * <pre>
 * 身份证前6位【ABCDEF】为行政区划数字代码（简称数字码）：该数字码的编制原则和结构分析，它采用三层六位层次码结构，按层次分别表示我国各省（自治区，直辖市，特别行政区）、市（地区，自治州，盟）、县（自治县、县级市、旗、自治旗、市辖区、林区、特区）。 
 *  
 *  数字码码位结构从左至右的含义是： 
 *  第一层为AB两位代码表示省、自治区、直辖市、特别行政区； 
 *  
 *  第二层为CD两位代码表示市、地区、自治州、盟、直辖市所辖市辖区、县汇总码、省（自治区）直辖县级行政区划汇总码，其中： 
 *  ——01&tilde;20、51&tilde;70表示市，01、02还用于表示直辖市所辖市辖区、县汇总码； 
 *  ——21&tilde;50表示地区、自治州、盟； 
 *  ——90表示省（自治区）直辖县级行政区划汇总码。 
 *  
 *  第三层为EF两位表示县、自治县、县级市、旗、自治旗、市辖区、林区、特区，其中： 
 *  ——01&tilde;20表示市辖区、地区（自治州、盟）辖县级市、市辖特区以及省（自治区）直辖县级行政区划中的县级市，01通常表示辖区汇总码； 
 *  ——21&tilde;80表示县、自治县、旗、自治旗、林区、地区辖特区； 
 *  ——81&tilde;99表示省（自治区）辖县级市。
 *  
 * -15位身份证号码：第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * -18位身份证号码：第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
 * </pre>
 * 
 * @author <a href="venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-9-14 下午02:30:25
 * @since 1.0
 */
public class IdCardUtil{

	/**
	 * 提取身份证信息
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 提取身份证信息
	 */
	public static IdCardEntity getIdCardEntity(String idCard){
		if (Validator.isNotNullOrEmpty(idCard)){
			// 长度
			int card_length = idCard.length();
			if (card_length == 15 || card_length == 18){
				if (isValidatedAllIdCard(idCard)){
					if (15 == card_length){
						// 15位转成18位
						idCard = convert15IdCarTo18(idCard);
					}
					// ****************************省份***************************************
					// 省份
					String province = "";
					// 获取省份
					String provinceId = idCard.substring(0, 2);
					for (Map.Entry<String, String> entry : provinceCodeMap.entrySet()){
						if (entry.getKey().equals(provinceId)){
							province = entry.getValue();
							break;
						}
					}
					// ****************************性别**********************************
					// 获取性别
					String id17 = idCard.substring(16, 17);
					String gender = "女";
					if (Integer.parseInt(id17) % 2 != 0){
						gender = "男";
					}
					// ******************************出生日期***********************************
					// 出生日期
					String birthday = idCard.substring(6, 14);
					Date birthdate = DateUtil.string2Date(birthday, "yyyyMMdd");
					GregorianCalendar gregorianCalendar = new GregorianCalendar();
					gregorianCalendar.setTime(birthdate);
					// ***********************************************************************************
					IdCardEntity idCardEntity = new IdCardEntity();
					idCardEntity.setProvince(province);
					idCardEntity.setBirthday(birthdate);
					idCardEntity.setYear(gregorianCalendar.get(Calendar.YEAR));
					idCardEntity.setMonth(gregorianCalendar.get(Calendar.MONTH) + 1);
					idCardEntity.setDay(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
					idCardEntity.setGender(gender);
					return idCardEntity;
				}
			}
		}
		return null;
	}

	/**
	 * 验证所有的身份证的合法性
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 通过返回true
	 */
	public static boolean isValidatedAllIdCard(String idCard){
		if (Validator.isNotNullOrEmpty(idCard)){
			if (idCard.length() == 15){
				idCard = convert15IdCarTo18(idCard);
			}
			return isValidate18IdCard(idCard);
		}
		return false;
	}

	/**
	 * 判断18位身份证的合法性<br>
	 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * <p>
	 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
	 * </p>
	 * <p>
	 * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码； 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
	 * 6.第17位数字表示性别：奇数表示男性，偶数表示女性； 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
	 * </p>
	 * <p>
	 * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
	 * </p>
	 * <p>
	 * 2.将这17位数字和系数相乘的结果相加。
	 * </p>
	 * <p>
	 * 3.用加出来和除以11，看余数是多少？
	 * </p>
	 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
	 * <p>
	 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
	 * </p>
	 * 
	 * @param idCard
	 */
	public static boolean isValidate18IdCard(String idCard){
		// 非18位为假
		if (18 != idCard.length()){
			return false;
		}
		// 获取前17位
		String idCard17 = idCard.substring(0, 17);
		// 获取第18位
		String idCard18Code = idCard.substring(17, 18);
		char c[] = null;
		// 是否都为数字
		if (RegexUtil.isNumber(idCard17)){
			c = idCard17.toCharArray();
		}else{
			return false;
		}
		if (null != c){
			int bit[] = converCharsToInts(c);
			int sum17 = getPowerSum(bit);
			// 将和值与11取模得到余数进行校验码判断
			String checkCode = getCheckCodeBySum(sum17);
			if (null == checkCode){
				return false;
			}
			// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
			if (!idCard18Code.equalsIgnoreCase(checkCode)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
	 * 
	 * @param idCard
	 * @return 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
	 * @deprecated
	 */
	@Deprecated
	public static boolean isValidate15IdCard(String idCard){
		// 非15位为假
		if (idCard.length() != 15){
			return false;
		}
		// 是否全都为数字
		if (RegexUtil.isNumber(idCard)){
			String provinceid = idCard.substring(0, 2);
			String birthday = idCard.substring(6, 12);
			int year = Integer.parseInt(idCard.substring(6, 8));
			int month = Integer.parseInt(idCard.substring(8, 10));
			int day = Integer.parseInt(idCard.substring(10, 12));
			// 判断是否为合法的省份
			boolean flag = false;
			for (String id : provinceCode){
				if (id.equals(provinceid)){
					flag = true;
					break;
				}
			}
			if (!flag){
				return false;
			}
			// 该身份证生出日期在当前日期之后时为假
			Date birthdate = DateUtil.string2Date(birthday, "yyMMdd");
			if (birthdate == null || new Date().before(birthdate)){
				return false;
			}
			// 判断是否为合法的年份
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			int curYear = gregorianCalendar.get(Calendar.YEAR);
			int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
			// 判断该年份的两位表示法，小于50的和大于当前年份的，为假
			if ((year < 50 && year > year2bit)){
				return false;
			}
			// 判断是否为合法的月份
			if (month < 1 || month > 12){
				return false;
			}
			// 判断是否为合法的日期
			boolean mflag = false;
			gregorianCalendar.setTime(birthdate); // 将该身份证的出生日期赋于对象curDay
			switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					mflag = (day >= 1 && day <= 31);
					break;
				case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
					if (gregorianCalendar.isLeapYear(gregorianCalendar.get(Calendar.YEAR))){
						mflag = (day >= 1 && day <= 29);
					}else{
						mflag = (day >= 1 && day <= 28);
					}
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					mflag = (day >= 1 && day <= 30);
					break;
			}
			if (!mflag){
				return false;
			}
		}else{
			return false;
		}
		return true;
	}

	/**
	 * 将15位的身份证转成18位身份证
	 * 
	 * @param idCard
	 *            身份证号码
	 * @return 将15位的身份证转成18位身份证
	 */
	public static String convert15IdCarTo18(String idCard){
		String idCard17 = null;
		// 非15位身份证
		if (idCard.length() != 15){
			return null;
		}
		if (RegexUtil.isNumber(idCard)){
			// 获取出生年月日
			String birthday = idCard.substring(6, 12);
			Date birthdate = DateFormatUtil.parse(birthday, "yyMMdd");
			Calendar cday = Calendar.getInstance();
			cday.setTime(birthdate);
			String year = String.valueOf(cday.get(Calendar.YEAR));
			idCard17 = idCard.substring(0, 6) + year + idCard.substring(8);
			char c[] = idCard17.toCharArray();
			String checkCode = "";
			if (null != c){
				int bit[] = new int[idCard17.length()];
				// 将字符数组转为整型数组
				bit = converCharsToInts(c);
				int sum17 = 0;
				sum17 = getPowerSum(bit);
				// 获取和值与11取模得到余数进行校验码
				checkCode = getCheckCodeBySum(sum17);
				// 获取不到校验位
				if (null == checkCode){
					return null;
				}
				// 将前17位与第18位校验码拼接
				idCard17 += checkCode;
			}
		}else{ // 身份证包含数字
			return null;
		}
		return idCard17;
	}

	/**
	 * 15位和18位身份证号码的基本数字和位数验校
	 * 
	 * @param idCard
	 */
	public static boolean isIdCard(String idCard){
		return idCard == null || "".equals(idCard) ? false : Pattern.matches("(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)", idCard);
	}

	/**
	 * 15位身份证号码的基本数字和位数验校
	 * 
	 * @param idCard
	 */
	public static boolean is15IdCard(String idCard){
		return idCard == null || "".equals(idCard) ? false : Pattern.matches(
				"^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$",
				idCard);
	}

	/**
	 * 18位身份证号码的基本数字和位数验校
	 * 
	 * @param idCard
	 */
	public static boolean is18IdCard(String idCard){
		return Pattern.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$", idCard);
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param bit
	 * @return 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 */
	private static int getPowerSum(int[] bit){
		int sum = 0;
		if (power.length != bit.length){
			return sum;
		}
		for (int i = 0; i < bit.length; ++i){
			for (int j = 0; j < power.length; ++j){
				if (i == j){
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}

	/**
	 * 将和值与11取模得到余数进行校验码判断
	 * 
	 * @param sum17
	 * @return 将和值与11取模得到余数进行校验码判断
	 */
	private static String getCheckCodeBySum(int sum17){
		String checkCode = null;
		switch (sum17 % 11) {
			case 10:
				checkCode = "2";
				break;
			case 9:
				checkCode = "3";
				break;
			case 8:
				checkCode = "4";
				break;
			case 7:
				checkCode = "5";
				break;
			case 6:
				checkCode = "6";
				break;
			case 5:
				checkCode = "7";
				break;
			case 4:
				checkCode = "8";
				break;
			case 3:
				checkCode = "9";
				break;
			case 2:
				checkCode = "x";
				break;
			case 1:
				checkCode = "0";
				break;
			case 0:
				checkCode = "1";
				break;
		}
		return checkCode;
	}

	/**
	 * 将字符数组转为整型数组
	 * 
	 * @param chars
	 *            字符数组
	 * @return 将字符数组转为整型数组
	 */
	private static int[] converCharsToInts(char[] chars){
		int[] ints = new int[chars.length];
		int k = 0;
		for (char char_temp : chars){
			ints[k++] = Integer.parseInt(String.valueOf(char_temp));
		}
		return ints;
	}

	/**
	 * 省,直辖市代码表：
	 * 
	 * <pre>
	 * { 
	 * 11:&quot;北京&quot;,12:&quot;天津&quot;,13:&quot;河北&quot;,14:&quot;山西&quot;,
	 * 15:&quot;内蒙古&quot;,21:&quot;辽宁&quot;,22:&quot;吉林&quot;,23:&quot;黑龙江&quot;,
	 * 31:&quot;上海&quot;,32:&quot;江苏&quot;,33:&quot;浙江&quot;,34:&quot;安徽&quot;,
	 * 35:&quot;福建&quot;,36:&quot;江西&quot;,37:&quot;山东&quot;,41:&quot;河南&quot;,
	 * 42:&quot;湖北&quot;,43:&quot;湖南&quot;,44:&quot;广东&quot;,45:&quot;广西&quot;,
	 * 46:&quot;海南&quot;,50:&quot;重庆&quot;,51:&quot;四川&quot;,52:&quot;贵州&quot;,
	 * 53:&quot;云南&quot;,54:&quot;西藏&quot;,61:&quot;陕西&quot;,62:&quot;甘肃&quot;,
	 * 63:&quot;青海&quot;,64:&quot;宁夏&quot;,65:&quot;新疆&quot;,71:&quot;台湾&quot;,
	 * 81:&quot;香港&quot;,82:&quot;澳门&quot;,91:&quot;国外&quot;
	 * }
	 * 
	 * </pre>
	 */
	private static String[][]			codeAndProvinces	= {
			{ "11", "北京" },
			{ "12", "天津" },
			{ "13", "河北" },
			{ "14", "山西" },
			{ "15", "内蒙古" },
			{ "21", "辽宁" },
			{ "22", "吉林" },
			{ "23", "黑龙江" },
			{ "31", "上海" },
			{ "32", "江苏" },
			{ "33", "浙江" },
			{ "34", "安徽" },
			{ "35", "福建" },
			{ "36", "江西" },
			{ "37", "山东" },
			{ "41", "河南" },
			{ "42", "湖北" },
			{ "43", "湖南" },
			{ "44", "广东" },
			{ "45", "广西" },
			{ "46", "海南" },
			{ "50", "重庆" },
			{ "51", "四川" },
			{ "52", "贵州" },
			{ "53", "云南" },
			{ "54", "西藏" },
			{ "61", "陕西" },
			{ "62", "甘肃" },
			{ "63", "青海" },
			{ "64", "宁夏" },
			{ "65", "新疆" },
			{ "71", "台湾" },
			{ "81", "香港" },
			{ "82", "澳门" },
			{ "91", "国外" }									};

	/**
	 * 省份,自治区 代码map
	 */
	private static Map<String, String>	provinceCodeMap;
	/**
	 * 初始化
	 */
	static{
		provinceCodeMap = new HashMap<String, String>();
		for (String[] codeAndProvince : codeAndProvinces){
			provinceCodeMap.put(codeAndProvince[0], codeAndProvince[1]);
		}
	}

	/**
	 * 省份 自治区 代码
	 */
	private static String[]				provinceCode		= {
			"11",
			"12",
			"13",
			"14",
			"15",
			"21",
			"22",
			"23",
			"31",
			"32",
			"33",
			"34",
			"35",
			"36",
			"37",
			"41",
			"42",
			"43",
			"44",
			"45",
			"46",
			"50",
			"51",
			"52",
			"53",
			"54",
			"61",
			"62",
			"63",
			"64",
			"65",
			"71",
			"81",
			"82",
			"91"											};

	// 每位加权因子
	private static int[]				power				= { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	// 第18位校检码
	// private static String[] verifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
}
