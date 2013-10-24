package com.feilong.tools.middleware.idcard;

import java.io.Serializable;
import java.util.Date;

/**
 * 身份证信息类.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-9-14 下午02:11:52
 * @since 1.0
 */
public class IdCardEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** 省份. */
	private String				province;

	/** 城市. */
	private String				city;

	/** 区县. */
	private String				region;

	/** 年份. */
	private int					year;

	/** 月份. */
	private int					month;

	/** 日期. */
	private int					day;

	/** 性别. */
	private String				gender;

	/** 出生日期. */
	private Date				birthday;

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "省份：" + province + ",城市:" + city + "区县:" + region + "年份:" + year + "月份:" + month + "" + "日期:" + day + "性别：" + gender
				+ ",出生日期：" + birthday;
	}

	/**
	 * Gets the 省份.
	 * 
	 * @return the province
	 */
	public String getProvince(){
		return province;
	}

	/**
	 * Sets the 省份.
	 * 
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province){
		this.province = province;
	}

	/**
	 * Gets the 城市.
	 * 
	 * @return the city
	 */
	public String getCity(){
		return city;
	}

	/**
	 * Sets the 城市.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city){
		this.city = city;
	}

	/**
	 * Gets the 区县.
	 * 
	 * @return the region
	 */
	public String getRegion(){
		return region;
	}

	/**
	 * Sets the 区县.
	 * 
	 * @param region
	 *            the region to set
	 */
	public void setRegion(String region){
		this.region = region;
	}

	/**
	 * Gets the 年份.
	 * 
	 * @return the year
	 */
	public int getYear(){
		return year;
	}

	/**
	 * Sets the 年份.
	 * 
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year){
		this.year = year;
	}

	/**
	 * Gets the 月份.
	 * 
	 * @return the month
	 */
	public int getMonth(){
		return month;
	}

	/**
	 * Sets the 月份.
	 * 
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month){
		this.month = month;
	}

	/**
	 * Gets the 日期.
	 * 
	 * @return the day
	 */
	public int getDay(){
		return day;
	}

	/**
	 * Sets the 日期.
	 * 
	 * @param day
	 *            the day to set
	 */
	public void setDay(int day){
		this.day = day;
	}

	/**
	 * Gets the 性别.
	 * 
	 * @return the gender
	 */
	public String getGender(){
		return gender;
	}

	/**
	 * Sets the 性别.
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender){
		this.gender = gender;
	}

	/**
	 * Gets the 出生日期.
	 * 
	 * @return the birthday
	 */
	public Date getBirthday(){
		return birthday;
	}

	/**
	 * Sets the 出生日期.
	 * 
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

}
