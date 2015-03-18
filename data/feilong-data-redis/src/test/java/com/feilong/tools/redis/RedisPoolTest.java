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
package com.feilong.tools.redis;

import java.util.ResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.9 2015年3月19日 上午12:28:51
 * @since 1.0.9
 */
public class RedisPoolTest{

    private static final Logger log = LoggerFactory.getLogger(RedisPoolTest.class);

    private static JedisPool    jedisPool;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("redis");
        if (bundle == null){
            throw new IllegalArgumentException("[redis.properties] is not found!");
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //  jedisPoolConfig.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));

        jedisPoolConfig.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));

        //  jedisPoolConfig.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));

        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
        jedisPoolConfig.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

        jedisPool = new JedisPool(jedisPoolConfig, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
    }

    @Test
    public final void test(){
        // 从池中获取一个Jedis对象  
        Jedis jedis = jedisPool.getResource();
        String keys = "name";

        // 删数据  
        jedis.del(keys);
        // 存数据  
        jedis.set(keys, "飞龙");
        // 取数据  
        String value = jedis.get(keys);

        // 释放对象池  
        jedisPool.returnResource(jedis);

        if (log.isDebugEnabled()){
            log.debug("the param value:{}", value);
        }
    }
}
