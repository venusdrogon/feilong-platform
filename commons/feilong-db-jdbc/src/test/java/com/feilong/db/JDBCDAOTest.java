/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
 */
package com.feilong.db;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 *测试数据库链接
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-28 下午04:45:26
 */
public class JDBCDAOTest{

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
				System.out.println("ID=" + id);
				System.out.println("CITYNAME=" + cityName);
			}
			rs.close();
			conn.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * {@link com.feilong.commons.db.JDBCDAO#getConnection(java.lang.String, java.lang.String)} 的测试方法。
	 */
	@Test
	public final void testGetConnectionStringString(){
		fail("尚未实现");
	}

	/**
	 * {@link com.feilong.commons.db.JDBCDAO#getConnection(java.lang.String, java.lang.String, java.lang.String, java.lang.String)} 的测试方法。
	 */
	@Test
	public final void testGetConnectionStringStringStringString(){
		fail("尚未实现");
	}
}
