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
package com.feilong.ssh.db;

import java.util.Iterator;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Table;

/**
 * 数据库相关工具类
 * 
 * @author 金鑫 2010-2-9 上午10:19:57
 */
public class HibernateUtil{

	/**
	 * 配置文件路径
	 */
	private static String			configLocationPath	= "/hibernate.cfg.xml";

	private static Configuration	configuration		= null;

	/**
	 * 获得org.hibernate.cfg.Configuration
	 * 
	 * @return Configuration
	 * @author 金鑫
	 * @version 1.0 2010-8-9 上午11:45:51
	 */
	private static Configuration getHibernateConfiguration(){
		if (configuration == null){
			// return new Configuration().configure();
			return new Configuration();
		}
		return configuration;
	}

	/**
	 * 该对象包含相关实体映射的全部信息。特别地，对PersistentClass对象的处理允许在运行时修改实体类的属性设置。
	 * 
	 * @param clazz
	 * @return
	 * @author 金鑫
	 * @version 1.0 2010-8-9 下午01:49:26
	 */
	private static PersistentClass getPersistentClass(Class clazz){
		synchronized (HibernateUtil.class){
			PersistentClass persistentClass = getHibernateConfiguration().getClassMapping(clazz.getName());
			if (persistentClass == null){
				configuration = getHibernateConfiguration().addClass(clazz);
				persistentClass = getHibernateConfiguration().getClassMapping(clazz.getName());
			}
			return persistentClass;
		}
	}

	/**
	 * 获取实体对应的表名
	 * 
	 * @param clazz
	 *            实体类
	 * @return 表名
	 */
	public static String getTableName(Class clazz){
		return getPersistentClass(clazz).getTable().getName();
	}

	/**
	 * 获取实体对应表的主键字段名称
	 * 
	 * @param clazz
	 *            实体类
	 * @return 主键字段名称
	 */
	public static String getPkColumnName(Class clazz){
		Table table = getPersistentClass(clazz).getTable();
		return table.getPrimaryKey().getColumn(0).getName();
	}

	/**
	 * 通过实体类和属性，获取实体类属性对应的表字段名称
	 * 
	 * @param clazz
	 *            实体类
	 * @param propertyName
	 *            属性名称
	 * @return 字段名称
	 */
	public static String getColumnName(Class clazz,String propertyName){
		PersistentClass persistentClass = getPersistentClass(clazz);
		Property property = persistentClass.getProperty(propertyName);
		Iterator it = property.getColumnIterator();
		if (it.hasNext()){
			Column column = (Column) it.next();
			return column.getName();
		}
		return null;
	}

	/**
	 * 获得表的主键
	 * 
	 * @param pojoClass
	 *            pojo实体类
	 * @return 获得表的主键
	 * @author 金鑫
	 * @version 1.0 2010-3-26 上午10:31:21
	 */
	public static String getPrimaryKeyName(Class pojoClass){
		Table table = getTable(pojoClass);
		PrimaryKey primaryKey = table.getPrimaryKey();
		String primaryKeyName = "";
		// primaryKeyName = primaryKey.getName();// JOBPK
		primaryKeyName = primaryKey.getColumn(0).getName();// JOB_ID
		return primaryKeyName;
	}

	/**
	 * 获得pojoClass对应的关系表
	 * 
	 * @param pojoClass
	 *            pojo实体类
	 * @return 获得pojoClass对应的关系表
	 * @author 金鑫
	 * @version 1.0 2010-8-9 上午11:41:14
	 */
	public static Table getTable(Class pojoClass){
		String entityName = pojoClass.getName();
		Configuration configuration = getHibernateConfiguration();
		configuration.configure(configLocationPath);
		PersistentClass persistentClass = configuration.getClassMapping(entityName);
		Table table = persistentClass.getTable();
		return table;
	}
}
