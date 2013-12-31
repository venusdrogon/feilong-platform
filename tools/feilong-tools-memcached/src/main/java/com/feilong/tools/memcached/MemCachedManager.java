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
package com.feilong.tools.memcached;

import java.util.concurrent.Future;

import com.feilong.commons.core.date.TimeInterval;

/**
 * 可以用spring 管理的 memcached,如果有刘总annotation处理不了的或者不方便的 memcached 可以使用这个
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 21, 2012 6:10:40 PM
 */
public interface MemCachedManager{

	/**
	 * ++<br>
	 * 缓存数据中存储的是数字形式的字符串<br>
	 * <b>warn</b>:<br>
	 * incr 的key 的原始value 必须是<span style="color:red">数字型的字符串 </span>(强调:不能是数字) <br>
	 * 比如 :set("name","5") 不能是set("name",5),否则 incr/decr 都会异常 返回-1
	 * 
	 * @param key
	 * @param by
	 *            递增量
	 * @return the new value (如果key 不存在返回-1)<br>
	 *           对非数字的缓存操作会返回错误,CLIENT_ERROR cannot increment or decrement non-numeric value 
	 */
	long incr(String key,int by);

	/**
	 * --<br>
	 * 缓存数据中存储的是数字形式的字符串 <b>warn</b>:<br>
	 * incr 的key 的原始value 必须是<span style="color:red">数字型的字符串 </span>(强调:不能是数字) <br>
	 * 比如 :set("name","5") 不能是set("name",5),否则 incr/decr 都会异常 返回-1
	 * 
	 * @param key
	 * @param by
	 *            递减量
	 * @return the new value (如果key 不存在返回-1)<br>
	 *           对非数字的缓存操作会返回错误,CLIENT_ERROR cannot increment or decrement non-numeric value 
	 */
	long decr(String key,int by);

	/**
	 * 将key值,set到memcached中<br>
	 * 不需要提供时间参数,默认@see {@link TimeInterval#SECONDS_PER_DAY *29}<br>
	 * <b>和add 的区别:</b><br>
	 * add方法,当且仅当不存在才add(iff it does not exist already);<br>
	 * set方法,不管是否存在不存在都set(regardless of any existing value)
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 */
	Future<Boolean> set(String key,Object value);

	/**
	 * 将key值,set到memcached中<br>
	 * 需要提供时间参数,暴露出来,手工指定,你可以传入@see {@link TimeInterval#SECONDS_PER_DAY *29}<br>
	 * <b>和add 的区别:</b><br>
	 * add方法,当且仅当不存在才add(iff it does not exist already);<br>
	 * set方法,不管是否存在不存在都set(regardless of any existing value)
	 * 
	 * @param key
	 *            key
	 * @param expiredTime
	 *            过期时间(秒),<br>
	 *            值可以是两种类型:<br>
	 *            1.Unix time (number of seconds since January 1, 1970, as a 32-bit value), <br>
	 *            2.从现在开始的秒数 <br>
	 *            如果是2这种情况,不超过 60*60*24*30 (number of seconds in 30 days);<br>
	 *            如果大于这个值,服务器端会认为这是一个真正的Unix时间而不是基于当前时间的偏移.
	 * @param value
	 *            value
	 * @return
	 */
	Future<Boolean> set(String key,int expiredTime,Object value);

	/**
	 * 根据key值，过获取memcached中的值<br>
	 * Get with a single key and decode using the default transcoder.
	 * 
	 * @param key
	 * @return the result from the cache (不存在 返回null)
	 */
	<T> T get(String key);

	/**
	 * 根据key值，删除memcached中的值
	 * 
	 * @param key
	 * @return
	 */
	Future<Boolean> delete(String key);

	/**
	 * 立刻关闭
	 */
	void shutDown();

	/**
	 * 暂未提供实现
	 * 
	 * @deprecated
	 */
	void flushAll();
}
