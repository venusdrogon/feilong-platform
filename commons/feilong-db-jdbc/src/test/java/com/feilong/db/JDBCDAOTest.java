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
package com.feilong.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试数据库链接.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-28 下午04:45:26
 */
public class JDBCDAOTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(JDBCDAOTest.class);

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args){
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();// 加载驱动
			Connection conn = DriverManager.getConnection("jdbc:derby:myfeilongdb;create=true");// 连接数据库
			Statement st = conn.createStatement();
			//			st.execute("create table CITY (ID INT NOT NULL,CITYNAME VARCHAR(10) NOT NULL)");// 建表
			//			st.executeUpdate("insert into CITY(ID,CITYNAME) values (1,'北京')");// 插入数据
			//			st.executeUpdate("insert into CITY(ID,CITYNAME) values (2,'上海')");// 插入数据
			ResultSet rs = st.executeQuery("select * from CITY");// 读取刚插入的数据
			while (rs.next()){
				int id = rs.getInt(1);
				String cityName = rs.getString(2);
				log.info("ID=" + id);
				log.info("CITYNAME=" + cityName);
			}
			rs.close();
			conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
