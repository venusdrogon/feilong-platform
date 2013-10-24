/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong Network Technology, Inc. 
 * ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.commons.core.date;

/**
 *飞龙星座枚举,占星学,黄道12星座是宇宙方位的代名词，代表了12种基本性格原型，
 *<p>
 * 一个人出生时，各星体落入黄道上的位置，正是说明著一个人的先天性格及天赋。 <br>
 * 黄道12星座象征心理层面，反映出一个人行为的表现的方式。 <br>
 * <br>
 * 于是将黄道分成12个星座，称为黄道12星座。<br>
 * <ul>
 * <li>摩羯座 12月22日-1月19日</li>
 * <li>水瓶座 1月20日-2月18日</li>
 * <li>双鱼座 2月19日-3月20日</li>
 * <li>白羊座 3月21日-4月19日</li>
 * <li>金牛座 4月20日-5月20日</li>
 * <li>双子座 5月21日-6月21日</li>
 * <li>巨蟹座 6月22日-7月22日</li>
 * <li>狮子座 7月23日-8月22日</li>
 * <li>处女座 8月23日-9月22日</li>
 * <li>天秤座 9月23日-10月23日</li>
 * <li>天蝎座 10月24日-11月22日</li>
 * <li>射手座 11月23日-12月21日</li>
 * </ul>
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-5 上午11:16:41
 * @since 1.0
 */
public enum ConstellationType{
	/**
	 * 摩羯座 12月22日-1月19日
	 */
	Capricorn(1,"摩羯座","12月22日","1月19日"),
	/**
	 * 水瓶座 1月20日-2月18日
	 */
	Aquarius(2,"水瓶座","1月20日","2月18日"),
	/**
	 * 双鱼座 2月19日-3月20日
	 */
	Pisces(3,"双鱼座"," 2月19日","3月20日"),
	/**
	 * 白羊座 3月21日-4月19日
	 */
	Aries(4,"白羊座","3月21日","4月19日"),
	/**
	 * 金牛座 4月20日-5月20日
	 */
	Taurus(5,"金牛座","4月20日","5月20日"),
	/**
	 * 双子座 5月21日-6月21日
	 */
	Gemini(6,"双子座"," 5月21日","6月21日"),
	/**
	 * 巨蟹座 6月22日-7月22日
	 */
	Cancer(7,"巨蟹座","6月22日","7月22日"),
	/**
	 * 狮子座 7月23日-8月22日
	 */
	Leo(8,"狮子座","7月23日","8月22日"),
	/**
	 * 处女座 8月23日-9月22日
	 */
	Virgo(9,"处女座"," 8月23日","9月22日"),
	/**
	 * 天秤座 9月23日-10月23日
	 */
	Libra(10,"天秤座","9月23日","10月23日"),
	/**
	 * 天蝎座 10月24日-11月22日
	 */
	Scorpio(11,"天蝎座","10月24日","11月22日"),
	/**
	 * 射手座 11月23日-12月21日
	 */
	Sagittarius(12,"射手座","11月23日","12月21日");

	// *****************************************************************************************
	private int		code;

	private String	chineseName;

	private String	beginDateString;

	private String	endDateString;

	// *****************************************************************************************
	private ConstellationType(int code, String chineseName, String beginDateString, String endDateString){
		this.code = code;
		this.chineseName = chineseName;
		this.beginDateString = beginDateString;
		this.endDateString = endDateString;
	}

	public int getCode(){
		return this.code;
	}

	public String getChineseName(){
		return this.chineseName;
	}

	public String getBeginDateString(){
		return beginDateString;
	}

	public String getEndDateString(){
		return endDateString;
	}
}
