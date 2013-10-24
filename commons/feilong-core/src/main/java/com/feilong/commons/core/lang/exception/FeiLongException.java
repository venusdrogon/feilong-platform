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
package com.feilong.commons.core.lang.exception;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 飞龙异常
 * 
 * @author 金鑫 2010-7-8 下午03:33:41
 */
@Deprecated
public class FeiLongException{

	private final static Logger			log	= LoggerFactory.getLogger(FeiLongException.class);

	/**
	 * 数据错误代码
	 */
	public static Map<String, String>	sqlExceptionMap;
	/**
	 * 
	 */
	static{
		sqlExceptionMap = new HashMap<String, String>();
		sqlExceptionMap.put("2627", "主键或者唯一约束重复");
		sqlExceptionMap.put("8152", "字符过长,超出数据库设定限制");
	}

	/**
	 * SQLException异常的处理方法
	 * 
	 * @param e
	 */
	public static void printStackTrace(SQLException e){
		int errorCode = e.getErrorCode();
		if (sqlExceptionMap.containsKey(errorCode)){
			log.debug(sqlExceptionMap.get(errorCode));
		}else{
			e.printStackTrace();
		}
	}

	/**
	 * 错误代码转成说明
	 * 
	 * @param e
	 *            SQLException
	 * @return 错误代码转成说明
	 */
	public static String errorCodeToString(SQLException e){
		int errorCode = e.getErrorCode();
		if (sqlExceptionMap.containsKey(errorCode)){
			return sqlExceptionMap.get(errorCode);
		}
		return errorCode + e.getMessage();
	}

	/**
	 * 错误代码转成说明
	 * 
	 * @param errorCode
	 *            错误代号
	 * @return 错误代码转成说明
	 * @deprecated (2010-07-08) {@link #errorCodeToString(SQLException)}
	 */
	@Deprecated
	public static String errorCodeToString(int errorCode){
		if (sqlExceptionMap.containsKey(errorCode)){
			return sqlExceptionMap.get(errorCode);
		}
		return errorCode + "";
	}
}
