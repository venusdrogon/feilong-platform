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

import loxia.support.cache.NullObject;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feilong.commons.core.enumeration.TimeInterval;

/**
 * 可以用spring 管理的 memcached,如果有刘总annotation处理不了的或者不方便的 memcached 可以使用这个.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 21, 2012 6:11:29 PM
 */
@Service("memCachedManager")
public class SpyMemCachedManagerImpl implements MemCachedManager{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(SpyMemCachedManagerImpl.class);

	/** The memcached client. */
	@Autowired(required = false)
	// 设置为 required=false 这样不需要的商城 启动不会报错
	private MemcachedClient		memcachedClient;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public Future<Boolean> set(String key,Object value){
		int expiredTime = TimeInterval.SECONDS_PER_DAY * 29;
		return set(key, expiredTime, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#incr(java.lang.String, int)
	 */
	public long incr(String key,int by){
		return memcachedClient.incr(key, by);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#decr(java.lang.String, int)
	 */
	public long decr(String key,int by){
		return memcachedClient.decr(key, by);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#set(java.lang.String, int, java.lang.Object)
	 */
	@Override
	public Future<Boolean> set(String key,int expiredTime,Object value){
		// 借鉴 CacheAspect
		if (null == value){
			NullObject NULL = new NullObject();
			value = NULL;
		}
		return memcachedClient.set(key, expiredTime, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#get(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String key){
		try{
			Object object = memcachedClient.get(key);

			if (null == object){
				log.debug("key :{} doesn't exists", key);
				return null;
			}

			if (object instanceof NullObject){
				log.debug("key :{} exists,value is NullObject", key);
				return null;
			}
			return (T) object;
		}catch (RuntimeException e){
			log.error(e.getClass().getName(), e);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#delete(java.lang.String)
	 */
	@Override
	public Future<Boolean> delete(String key){
		return memcachedClient.delete(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#shutDown()
	 */
	@Override
	public void shutDown(){
		memcachedClient.shutdown();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#flushAll()
	 */
	@Override
	public void flushAll(){
		throw new NotImplementedException("flushAll is not implemented!");
	}
}
