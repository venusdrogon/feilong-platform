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
package com.feilong.controller.chart.mp2.person;

/**
 * 护照状态.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 12, 2014 4:01:33 AM
 */
public enum PassportStatusEnum{
	/** The has passport. */
	hasPassport(1,"有护照","#04D215"),

	/** The chunjie. */
	chunjie(2,"春节在家办理了护照数","#0D52D1"),

	/** The no tongji. */
	noTongji(3,"未参与统计数","#B0DE09"),

	/** The banbuliao. */
	banbuliao(4,"明确办不了数","#FCD202"),

	/** The no feed. */
	noFeed(5,"未收到反馈","#666666"),

	/** The shanghaiban. */
	shanghaiban(6,"将到上海办理数","#4B0C25"),

	/** The lizhi. */
	lizhi(7,"离职人数","#000000");

	/**
	 * 通过 value 获得PassportStatusEnum.
	 * 
	 * @param value
	 *            the value
	 * @return the passport status enum
	 */
	public static PassportStatusEnum getPassportStatusEnum(String value){
		if (null != value){
			for (PassportStatusEnum passportStatusEnum : PassportStatusEnum.values()){
				if (value.equals(passportStatusEnum.getValue())){
					return passportStatusEnum;
				}
			}
		}
		return null;
	}

	/** 值. */
	private Integer	value;

	/** 名称. */
	private String	name;

	/** 代表色. */
	private String	color;

	/**
	 * Instantiates a new passport status enum.
	 * 
	 * @param value
	 *            the value
	 * @param name
	 *            the name
	 * @param color
	 *            the color
	 */
	private PassportStatusEnum(Integer value, String name, String color){
		this.value = value;
		this.name = name;
		this.color = color;
	}

	/**
	 * Gets the 值.
	 * 
	 * @return the value
	 */
	public Integer getValue(){
		return value;
	}

	/**
	 * Sets the 值.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setValue(Integer value){
		this.value = value;
	}

	/**
	 * Gets the 名称.
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the 名称.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获得 代表色.
	 * 
	 * @return the color
	 */
	public String getColor(){
		return color;
	}

	/**
	 * 设置 代表色.
	 * 
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color){
		this.color = color;
	}

}
