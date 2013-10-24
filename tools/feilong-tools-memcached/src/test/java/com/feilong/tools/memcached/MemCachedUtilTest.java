package com.feilong.tools.memcached;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.danga.MemCached.MemCachedClient;

@ContextConfiguration(locations = { "classpath:feilong-memcached.xml" })
public class MemCachedUtilTest extends AbstractJUnit4SpringContextTests{

	private static final Logger	log	= LoggerFactory.getLogger(MemCachedUtilTest.class);

	private MemCachedUtil		memCachedUtil;

	@Autowired
	MemCachedClient				memCachedClient;

	// @Test
	// public void get(){
	// memCachedUtil.add("jinxin", "My name is jinxin ,feilong owner");
	// memCachedUtil.add("jinxin2", "My name is jinxin2 ,feilong owner");
	// log.debug(memCachedUtil.get("jinxin").toString());
	// }
	@Test
	public void name(){
		log.debug("the param localVar:{}", memCachedClient.keyExists("user"));
	}

	@Test
	public void add(){
		memCachedClient.add("user", "jinxin");
		// log.debug("the param localVar:{}", memCachedClient.keyExists("user"));
	}
}
