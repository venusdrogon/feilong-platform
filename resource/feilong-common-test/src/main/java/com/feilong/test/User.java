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
package com.feilong.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class User.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-11-28 下午03:05:56
 */
public class User{

	/** The name. */
	private String				name				= "feilong";

	/** The id. */
	private Long				id;

	/** 年龄. */
	private Integer				age;

	/** The loves. */
	private String[]			loves;

	/** The date. */
	private Date				date;

	/** The money. */
	private BigDecimal			money;

	/** 动态属性map. */
	private Map<String, String>	attrMap;

	/** 昵称. */
	private String[]			nickName;

	/** The user info. */
	private UserInfo			userInfo			= new UserInfo();

	/** The user addresses. */
	private UserAddress[]		userAddresses;

	/** The user addresse list. */
	private List<UserAddress>	userAddresseList	= new ArrayList<UserAddress>();

	/**
	 * The Constructor.
	 *
	 * @param name
	 *            the name
	 */
	public User(String name){
		super();
		this.name = name;
	}

	/**
	 * The Constructor.
	 *
	 * @param name
	 *            the name
	 * @param age
	 *            the age
	 */
	public User(String name, Integer age){
		super();
		this.name = name;
		this.age = age;
	}

	/**
	 * Instantiates a new user.
	 */
	public User(){
		super();
	}

	/**
	 * Instantiates a new user.
	 * 
	 * @param id
	 *            the id
	 */
	public User(Long id){
		super();
		this.id = id;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public Long getId(){
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * Gets the loves.
	 * 
	 * @return the loves
	 */
	public String[] getLoves(){
		return loves;
	}

	/**
	 * Sets the loves.
	 * 
	 * @param loves
	 *            the loves to set
	 */
	public void setLoves(String[] loves){
		this.loves = loves;
	}

	/**
	 * Gets the user info.
	 * 
	 * @return the userInfo
	 */
	public UserInfo getUserInfo(){
		return userInfo;
	}

	/**
	 * Sets the user info.
	 * 
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo){
		this.userInfo = userInfo;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date){
		this.date = date;
	}

	/**
	 * Gets the money.
	 * 
	 * @return the money
	 */
	public BigDecimal getMoney(){
		return money;
	}

	/**
	 * Sets the money.
	 * 
	 * @param money
	 *            the money to set
	 */
	public void setMoney(BigDecimal money){
		this.money = money;
	}

	/**
	 * 获得 昵称.
	 * 
	 * @return the nickName
	 */
	public String[] getNickName(){
		return nickName;
	}

	/**
	 * 设置 昵称.
	 * 
	 * @param nickName
	 *            the nickName to set
	 */
	public void setNickName(String[] nickName){
		this.nickName = nickName;
	}

	/**
	 * Gets the user addresses.
	 * 
	 * @return the userAddresses
	 */
	public UserAddress[] getUserAddresses(){
		return userAddresses;
	}

	/**
	 * Sets the user addresses.
	 * 
	 * @param userAddresses
	 *            the userAddresses to set
	 */
	public void setUserAddresses(UserAddress[] userAddresses){
		this.userAddresses = userAddresses;
	}

	/**
	 * Gets the user addresse list.
	 * 
	 * @return the userAddresseList
	 */
	public List<UserAddress> getUserAddresseList(){
		return userAddresseList;
	}

	/**
	 * Sets the user addresse list.
	 * 
	 * @param userAddresseList
	 *            the userAddresseList to set
	 */
	public void setUserAddresseList(List<UserAddress> userAddresseList){
		this.userAddresseList = userAddresseList;
	}

	/**
	 * Gets the attr map.
	 * 
	 * @return the attrMap
	 */
	public Map<String, String> getAttrMap(){
		return attrMap;
	}

	/**
	 * 设置 attr map.
	 * 
	 * @param attrMap
	 *            the attrMap to set
	 */
	public void setAttrMap(Map<String, String> attrMap){
		this.attrMap = attrMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString(){

		//		return "User [name=" + name + ", id=" + id + ", loves=" + Arrays.toString(loves) + ", date=" + date + ", money=" + money
		//				+ ", attrMap=" + attrMap + ", nickName=" + Arrays.toString(nickName) + ", userInfo=" + userInfo + ", userAddresses="
		//				+ Arrays.toString(userAddresses) + ", userAddresseList=" + userAddresseList + "]";
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		int reflectionHashCode = HashCodeBuilder.reflectionHashCode(this, "id");
		System.out.println(reflectionHashCode);
		return reflectionHashCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		boolean reflectionEquals = EqualsBuilder.reflectionEquals(this, obj, "id");
		System.out.println(reflectionEquals);
		return reflectionEquals;
	}

	/**
	 * 获得 年龄.
	 *
	 * @return the age
	 */
	public Integer getAge(){
		return age;
	}

	/**
	 * 设置 年龄.
	 *
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age){
		this.age = age;
	}
}