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
package com.feilong.ssh.dialect;

import java.sql.Types;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * 基于utf-8 编码集 mysql数据库定义的Dialect.
 * 
 * <h3>varchar长度说明</h3>
 * 
 * <blockquote>
 * <p>
 * mysql varchar 最大长度 65535,但是这里是指字节,并且 65535中包含了所有字段的长度、变长字段长度标示位、NULL标示位的累计 由于我们数据库字符集使用的是UTF-8, <br>
 * mysql utf-8 每个字符占3个字节,并且null标识位占用一个一个字节 (参考:关于 mysql varchar 字段的长度)
 * 
 * 所以 mysql varchar最大字符是 (65535-1)/3=21844
 * </p>
 * </blockquote>
 * 
 * <h3>如果不使用这个类</h3>
 * 
 * <blockquote>
 * <p>
 * 如果UTF-8 数据库,不使用这个类,column length>21844将创建不了
 * <p>
 * </blockquote>
 * 
 * 
 * <h3>关于 TypeNames</h3>
 * 
 * <blockquote>
 * <p>
 * {@link org.hibernate.dialect.TypeNames#put(int, long, String)}的实现是基于 treemap,是有排序的,因此可以放心的覆盖
 * </p>
 * </blockquote>
 * 
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月29日 下午7:16:23
 * @since 1.0.7
 * @see org.hibernate.dialect.TypeNames
 * @see org.hibernate.dialect.TypeNames#put(int, long, String)
 * @see org.hibernate.dialect.MySQL5Dialect
 * @see <a href="http://www.oschina.net/question/54100_25242">关于 mysql varchar 字段的长度</a>
 */
public class MySQL5UTF8Dialect extends MySQL5Dialect{

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.dialect.MySQL5Dialect#registerVarcharTypes()
	 */
	protected void registerVarcharTypes(){
		super.registerVarcharTypes();
		registerColumnType(Types.VARCHAR, 21844, "varchar($l)");

		//将MySQL5Dialect中的给覆盖掉
		registerColumnType(Types.VARCHAR, 65535, "longtext");
	}
}
