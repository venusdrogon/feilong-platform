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
package com.feilong.commons.core.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResultSetUtil
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-2 下午3:16:45
 */
public class ResultSetUtil{

	private static final Logger	log	= LoggerFactory.getLogger(ResultSetUtil.class);

	/**
	 * 将 resultSet值 安装顺序 转成 Object[]数组
	 * 
	 * @param resultSet
	 * @return
	 */
	public static Object[] toObjects(ResultSet resultSet){
		try{
			// ResultSet 对象中列的类型和属性信息的对象
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// 返回此 ResultSet 对象中的列数。
			int columnCount = resultSetMetaData.getColumnCount();
			Object[] objects = new Object[columnCount];
			for (int i = 0; i < columnCount; ++i){
				objects[i] = resultSet.getObject(i + 1);
			}
			return objects;
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获得 resultSet 列名 list
	 * 
	 * @param resultSet
	 *            resultSet
	 * @return List<String>
	 */
	public static List<String> getColumnNameList(ResultSet resultSet){
		List<String> columnNameList = null;
		try{
			// ResultSet 对象中列的类型和属性信息的对象
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			// 返回此 ResultSet 对象中的列数。
			int columnCount = resultSetMetaData.getColumnCount();
			columnNameList = new ArrayList<String>(columnCount);
			for (int i = 0; i < columnCount; ++i){
				String columnName = resultSetMetaData.getColumnName(i + 1);
				columnNameList.add(columnName);
			}
		}catch (SQLException e){
			log.debug(e.getMessage());
			e.printStackTrace();
		}
		return columnNameList;
	}
}
