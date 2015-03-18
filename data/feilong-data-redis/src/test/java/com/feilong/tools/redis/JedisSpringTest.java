package com.feilong.tools.redis;

import org.junit.Test;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * @author 梁栋
 * @version 1.0
 * @since 1.0
 */
public class JedisSpringTest{

    //  private ApplicationContext app;

    private ShardedJedisPool pool;

    //    @Before
    //    public void before() throws Exception{
    //        app = new ClassPathXmlApplicationContext("applicationContext.xml");
    //        pool = (ShardedJedisPool) app.getBean("shardedJedisPool");
    //    }

    @Test
    public void test(){

        // 从池中获取一个Jedis对象
        ShardedJedis jedis = pool.getResource();
        String keys = "name";
        String value = "snowolf";
        // 删数据
        jedis.del(keys);
        // 存数据
        jedis.set(keys, value);
        // 取数据
        String v = jedis.get(keys);

        System.out.println(v);

        // 释放对象池
        pool.returnResource(jedis);

    }
}
