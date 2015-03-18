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
package com.feilong.tools.memcached;

import java.util.concurrent.Future;

import loxia.support.cache.NullObject;
import net.spy.memcached.MemcachedClient;

import org.apache.commons.lang.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feilong.commons.core.date.TimeInterval;

/**
 * 可以用spring 管理的 memcached,如果有刘总annotation处理不了的或者不方便的 memcached 可以使用这个.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 21, 2012 6:11:29 PM
 */
@Service("memCachedManager")
public class SpyMemCachedManagerImpl implements MemCachedManager{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SpyMemCachedManagerImpl.class);

    /** The memcached client. */
    @Autowired(required = false)
    // 设置为 required=false 这样不需要的商城 启动不会报错
    private MemcachedClient     memcachedClient;

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
    @Override
    public long incr(String key,int by){
        return memcachedClient.incr(key, by);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jumbo.brandstore.manager.memcached.MemCachedManager#decr(java.lang.String, int)
     */
    @Override
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
