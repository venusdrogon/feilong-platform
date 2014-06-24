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
package com.feilong.commons.core.bean.command;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年6月24日 下午1:36:29
 * @since 1.0.7
 */
public class MemberAddress implements Serializable{

	private static final long	serialVersionUID	= 288232184048495608L;

	private Long				id;

	private String				address;

	private Long				memberId;

	private Date				addTime;

	/**
	 * @return the id
	 */
	public Long getId(){
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id){
		this.id = id;
	}

	/**
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address){
		this.address = address;
	}

	/**
	 * @return the memberId
	 */
	public Long getMemberId(){
		return memberId;
	}

	/**
	 * @param memberId
	 *            the memberId to set
	 */
	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}

	/**
	 * @return the addTime
	 */
	public Date getAddTime(){
		return addTime;
	}

	/**
	 * @param addTime
	 *            the addTime to set
	 */
	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}

}
