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
package com.feilong.commons.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ResultSetUtil.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-8-2 下午3:16:45
 */
public class ResultSetUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ResultSetUtil.class);

    /**
     * 将 resultSet值 安装顺序 转成 Object[]数组.
     * 
     * @param resultSet
     *            the result set
     * @return the object[]
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
            log.error(e.getClass().getName(), e);
        }
        return null;
    }

    /**
     * 获得 resultSet 列名 list.
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
            log.error(e.getClass().getName(), e);
        }
        return columnNameList;
    }
}
