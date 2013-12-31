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
package com.feilong.servlet.http;

import java.io.Serializable;

/**
 * cookie实体.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2010-6-24 上午08:07:11
 * @since 1.0
 */
public class CookieEntity implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 1L;

	/** name名称,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号. */
	private String				name;

	/** value,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号. */
	private String				value;

	/** The expiry,Sets the maximum age of the cookie in seconds. */
	private int					expiry				= 60 * 60 * 24 * 30;

	/**
	 * The Constructor.
	 */
	public CookieEntity(){
		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	public CookieEntity(String name, String value){
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
	 * @param expiry
	 *            the expiry
	 */
	public CookieEntity(String name, String value, int expiry){
		this.name = name;
		this.value = value;
		this.expiry = expiry;
	}

	/**
	 * Gets the name名称,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号.
	 * 
	 * @return the name名称,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the name名称,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号.
	 * 
	 * @param name
	 *            the new name名称,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Gets the value,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号.
	 * 
	 * @return the value,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号
	 */
	public String getValue(){
		return value;
	}

	/**
	 * Sets the value,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号.
	 * 
	 * @param value
	 *            the new value,名字和值都不能包含空白字符以及下列字符： @ : ;? , " / [ ] ( ) = 这些符号
	 */
	public void setValue(String value){
		this.value = value;
	}

	/**
	 * Gets the expiry.
	 * 
	 * @return the expiry
	 */
	public int getExpiry(){
		return expiry;
	}

	/**
	 * Sets the expiry.
	 * 
	 * @param expiry
	 *            the new expiry
	 */
	public void setExpiry(int expiry){
		this.expiry = expiry;
	}
}
