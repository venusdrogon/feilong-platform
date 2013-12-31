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
package com.feilong.tools.mail;

/**
 * 邮件发送级别.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 24, 2011 1:05:45 AM
 */
public enum Priority{

	/** 最高. */
	highest("1"),

	/** 高. */
	high("2"),

	/** 一般. */
	normal("3"),

	/** 低. */
	low("4"),

	/** 最低. */
	lowest("5");

	/** 级别. */
	private String	levelValue;

	/**
	 * Instantiates a new priority.
	 * 
	 * @param levelValue
	 *            the level value
	 */
	private Priority(String levelValue){
		this.levelValue = levelValue;
	}

	/**
	 * Gets the 级别.
	 * 
	 * @return the levelValue
	 */
	public String getLevelValue(){
		return levelValue;
	}

	/**
	 * Sets the 级别.
	 * 
	 * @param levelValue
	 *            the levelValue to set
	 */
	public void setLevelValue(String levelValue){
		this.levelValue = levelValue;
	}

}
