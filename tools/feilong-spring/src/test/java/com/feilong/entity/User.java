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
package com.feilong.entity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * The Class User.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-16 0:42:08
 */
@Deprecated
public class User implements InitializingBean,DisposableBean{

	/** The Constant log. */
	private final static Logger	log	= LoggerFactory.getLogger(User.class);

	/** The user name. */
	private String				userName;

	/** The array. */
	private String[]			array;

	/** The list. */
	private List<String>		list;

	/** The map. */
	private Map<String, Object>	map;

	/** The properties. */
	private Properties			properties;

	/** The set. */
	private Set<String>			set;

	/** The vector. */
	private Vector<Object>		vector;

	/**
	 * Inits the.
	 */
	public void init(){
		log.info("init.....");
		this.userName = "feilong";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception{
		log.info("afterPropertiesSet.....");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.DisposableBean#destroy()
	 */
	@Override
	public void destroy() throws Exception{
		log.info("destroy.....");
		this.userName = null;
	}

	/**
	 * Gets the list.
	 * 
	 * @return the list
	 */
	public List<String> getList(){
		return list;
	}

	/**
	 * Sets the list.
	 * 
	 * @param list
	 *            the new list
	 */
	public void setList(List<String> list){
		this.list = list;
	}

	/**
	 * Gets the map.
	 * 
	 * @return the map
	 */
	public Map<String, Object> getMap(){
		return map;
	}

	/**
	 * 设置 map.
	 * 
	 * @param map
	 *            the map
	 */
	public void setMap(Map<String, Object> map){
		this.map = map;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties(){
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties
	 *            the new properties
	 */
	public void setProperties(Properties properties){
		this.properties = properties;
	}

	/**
	 * Gets the 设置.
	 * 
	 * @return the 设置
	 */
	public Set<String> getSet(){
		return set;
	}

	/**
	 * Sets the 设置.
	 * 
	 * @param set
	 *            the new 设置
	 */
	public void setSet(Set<String> set){
		this.set = set;
	}

	/**
	 * Gets the vector.
	 * 
	 * @return the vector
	 */
	public Vector<Object> getVector(){
		return vector;
	}

	/**
	 * Sets the vector.
	 * 
	 * @param vector
	 *            the new vector
	 */
	public void setVector(Vector<Object> vector){
		this.vector = vector;
	}

	/**
	 * Gets the array.
	 * 
	 * @return the array
	 */
	public String[] getArray(){
		return array;
	}

	/**
	 * Sets the array.
	 * 
	 * @param array
	 *            the array to set
	 */
	public void setArray(String[] array){
		this.array = array;
	}

	/**
	 * Gets the user name.
	 * 
	 * @return the user name
	 */
	public String getUserName(){
		return userName;
	}

	/**
	 * Sets the user name.
	 * 
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}
}
