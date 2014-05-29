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
package com.feilong.tools.ant.plugin.jpa.command;

import com.feilong.commons.core.log.Slf4jUtil;

/**
 * The Interface JpaConstants.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月27日 下午7:55:20
 * @since 1.0.7
 */
public interface JpaConstants{

	/** \@Table 捕获的正则表达式 <code>{@value}</code>. */
	String	REGEX_PATTERN_TABLE				= ".*@Table.*name.*\"(.*?)\".*";

	/** \@Index 捕获的正则表达式<code>{@value}</code>. */
	String	REGEX_PATTERN_INDEX				= ".*@Index.*name.*\"(.*?)\".*";

	/**
	 * \@COLUMN 捕获的正则表达式<code>{@value}</code>.
	 * 
	 * @Column(name = \"PROPERTIES\", length=200)
	 */
	String	REGEX_PATTERN_COLUMN			= ".*@Column.*name.*\"(.*?)\".*length\\s*=\\s*(\\d+).*";

	/**
	 * \@COLUMN 捕获的正则表达式,只有名称<code>{@value}</code>.
	 * 
	 * @Column(name = \"PROPERTIES\")
	 */
	String	REGEX_PATTERN_COLUMN_ONLYNAME	= ".*@Column.*name.*\"(.*?)\".*";

	/**
	 * 修改列类型<code>{@value}</code>
	 * <ul>
	 * <li>第一个参数 是表名,比如 t_product_item</li>
	 * <li>第二个参数是列名 PROPERTIES</li>
	 * <li>第三个参数是类型 比如 varchar</li>
	 * <li>第四个参数是 长度,比如 1000</li>
	 * </ul>
	 * 
	 * 返回:
	 * <blockquote>ALTER TABLE t_product_item MODIFY COLUMN PROPERTIES varchar(512);</blockquote>.
	 * 
	 * @see Slf4jUtil#formatMessage(String, Object...)
	 */
	String	TEMPLATE_MODIFY_COLUMN			= "ALTER TABLE {} MODIFY COLUMN {} {}({});";

	/** mysql varchar 类型 最大字符长度. */
	int		MYSQL5_MAXLENGTH_VARCHAR_UTF8	= 21844;

}
