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
package com.feilong.tools.chart.index;

/**
 * 图片指数(基本的).
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 19, 2012 12:43:36 AM
 */
public final class ChartIndex{

	/** 编码. */
	private String	code;

	/** 名称. */
	private String	name;

	/** 值. */
	private Integer	value;

	/** 颜色 比如柱状图 线条颜色. */
	private String	color;

	/**
	 * The Constructor.
	 *
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public ChartIndex(String code, String name, Integer value){
		super();
		this.code = code;
		this.name = name;
		this.value = value;
	}

	/**
	 * The Constructor.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public ChartIndex(String name, Integer value){
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * The Constructor.
	 *
	 * @param code
	 *            the code
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @param color
	 *            the color
	 */
	public ChartIndex(String code, String name, Integer value, String color){
		super();
		this.code = code;
		this.name = name;
		this.value = value;
		this.color = color;
	}

	/**
	 * The Constructor.
	 */
	public ChartIndex(){
		super();
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
	 * Gets the 编码.
	 * 
	 * @return the code
	 */
	public String getCode(){
		return code;
	}

	/**
	 * Sets the 编码.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code){
		this.code = code;
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
	 * 获得 颜色 比如柱状图 线条颜色.
	 *
	 * @return the color
	 */
	public String getColor(){
		return color;
	}

	/**
	 * 设置 颜色 比如柱状图 线条颜色.
	 *
	 * @param color
	 *            the color to set
	 */
	public void setColor(String color){
		this.color = color;
	}

}
