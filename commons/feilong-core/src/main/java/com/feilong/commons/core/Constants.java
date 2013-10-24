/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.commons.core;

/**
 * 存放通用的参数.
 * 
 * @author 金鑫 2010-3-19 下午05:37:33
 * @since 1.0
 * @version 1.0
 */
public final class Constants{

	/** Don't let anyone instantiate this class. */
	private Constants(){}

	/** 本地ip <code>{@value}</code>. */
	public final static String	localhostIp		= "127.0.0.1";

	/** 初始值空格 oracle空格需要空1个实在的空格 ""在oracle自动转换为null. <code>{@value}</code> */
	public final static String	space			= " ";

	/** 生成换行标识 Line separator ("\n" on UNIX). */
	public final static String	lineSeparator	= System.getProperty("line.separator");

	/**
	 * ***************************** Session 常量,内部类 ***************************************.
	 */
	/**
	 * Session 常量,内部类,不建议直接调用Session类,请使用FeiLongConstants.Session
	 * 
	 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
	 * @version 1.0 2011-4-14 下午02:20:15
	 * @since 1.0
	 */
	public static final class Session{

		/**
		 * private.
		 */
		private Session(){}

		/** 验证码<code>{@value}</code>. */
		public final static String	validateCode	= "feilong.validateCode";
	}
}
