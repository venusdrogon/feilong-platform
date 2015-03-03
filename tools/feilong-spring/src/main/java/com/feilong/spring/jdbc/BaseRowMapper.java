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
package com.feilong.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * The Class BaseRowMapper.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:29:26
 * @param <T>
 *            the generic type
 */
//TODO 感觉 这么写不好
public abstract class BaseRowMapper<T> implements RowMapper<T>{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(BaseRowMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public abstract T mapRow(ResultSet rs,int index) throws SQLException;

	/**
	 * Gets the result from rs.
	 * 
	 * @param <K>
	 *            the key type
	 * @param rs
	 *            the rs
	 * @param alias
	 *            the alias
	 * @param clazz
	 *            the clazz
	 * @return the result from rs
	 * @throws SQLException
	 *             the SQL exception
	 */
	@SuppressWarnings("unchecked")
	protected <K> K getResultFromRs(ResultSet rs,String alias,Class<K> clazz) throws SQLException{
		log.debug("[RowMapper]--[" + alias + "]: {" + clazz + "}");
		if (clazz == null){
			return (K) rs.getObject(alias);
		}
		if (rs.getObject(alias) == null){
			return null;
		}
		if (clazz.equals(String.class)){
			return (K) rs.getString(alias);
		}else if (clazz.equals(java.math.BigDecimal.class)){
			return (K) rs.getBigDecimal(alias);
		}else if (clazz.equals(java.io.InputStream.class)){
			return (K) rs.getBinaryStream(alias);
		}else if (clazz.equals(java.sql.Blob.class)){
			return (K) rs.getBlob(alias);
		}else if (clazz.equals(Boolean.class)){
			return (K) new Boolean(rs.getBoolean(alias));
		}else if (clazz.equals(Byte.class)){
			return (K) new Byte(rs.getByte(alias));
		}else if (clazz.equals(Byte[].class)){
			return (K) rs.getBytes(alias);
		}else if (clazz.equals(java.io.Reader.class)){
			return (K) rs.getCharacterStream(alias);
		}else if (clazz.equals(java.sql.Clob.class)){
			return (K) rs.getClob(alias);
		}else if (clazz.equals(java.sql.Date.class)){
			return (K) rs.getDate(alias);
		}else if (clazz.equals(Double.class)){
			return (K) new Double(rs.getDouble(alias));
		}else if (clazz.equals(Float.class)){
			return (K) new Float(rs.getFloat(alias));
		}else if (clazz.equals(Integer.class)){
			return (K) new Integer(rs.getInt(alias));
		}else if (clazz.equals(Long.class)){
			return (K) new Long(rs.getLong(alias));
		}else if (clazz.equals(Short.class)){
			return (K) new Short(rs.getShort(alias));
		}else if (clazz.equals(java.sql.Time.class)){
			return (K) rs.getTime(alias);
		}else if (clazz.equals(java.sql.Timestamp.class)){
			return (K) rs.getTimestamp(alias);
		}else if (clazz.equals(java.util.Date.class)){
			return (K) (rs.getTimestamp(alias) == null ? null : new java.util.Date(rs.getTimestamp(alias).getTime()));
		}else{
			return (K) rs.getObject(alias);
		}
	}

	/**
	 * Gets the result from rs.
	 * 
	 * @param <K>
	 *            the key type
	 * @param rs
	 *            the rs
	 * @param icolumn
	 *            the icolumn
	 * @param clazz
	 *            the clazz
	 * @return the result from rs
	 * @throws SQLException
	 *             the SQL exception
	 */
	@SuppressWarnings("unchecked")
	protected <K> K getResultFromRs(ResultSet rs,int icolumn,Class<K> clazz) throws SQLException{
		log.debug("[RowMapper]--[Column {" + icolumn + "}]: {" + clazz + "}");
		if (clazz == null){
			return (K) rs.getObject(icolumn);
		}
		if (rs.getObject(icolumn) == null){
			return null;
		}
		if (clazz.equals(String.class)){
			return (K) rs.getString(icolumn);
		}else if (clazz.equals(java.math.BigDecimal.class)){
			return (K) rs.getBigDecimal(icolumn);
		}else if (clazz.equals(java.io.InputStream.class)){
			return (K) rs.getBinaryStream(icolumn);
		}else if (clazz.equals(java.sql.Blob.class)){
			return (K) rs.getBlob(icolumn);
		}else if (clazz.equals(Boolean.class)){
			return (K) new Boolean(rs.getBoolean(icolumn));
		}else if (clazz.equals(Byte.class)){
			return (K) new Byte(rs.getByte(icolumn));
		}else if (clazz.equals(Byte[].class)){
			return (K) rs.getBytes(icolumn);
		}else if (clazz.equals(java.io.Reader.class)){
			return (K) rs.getCharacterStream(icolumn);
		}else if (clazz.equals(java.sql.Clob.class)){
			return (K) rs.getClob(icolumn);
		}else if (clazz.equals(java.sql.Date.class)){
			return (K) rs.getDate(icolumn);
		}else if (clazz.equals(Double.class)){
			return (K) new Double(rs.getDouble(icolumn));
		}else if (clazz.equals(Float.class)){
			return (K) new Float(rs.getFloat(icolumn));
		}else if (clazz.equals(Integer.class)){
			return (K) new Integer(rs.getInt(icolumn));
		}else if (clazz.equals(Long.class)){
			return (K) new Long(rs.getLong(icolumn));
		}else if (clazz.equals(Short.class)){
			return (K) new Short(rs.getShort(icolumn));
		}else if (clazz.equals(java.sql.Time.class)){
			return (K) rs.getTime(icolumn);
		}else if (clazz.equals(java.sql.Timestamp.class)){
			return (K) rs.getTimestamp(icolumn);
		}else if (clazz.equals(java.util.Date.class)){
			return (K) (rs.getTimestamp(icolumn) == null ? null : new java.util.Date(rs.getTimestamp(icolumn).getTime()));
		}else{
			return (K) rs.getObject(icolumn);
		}
	}
}
