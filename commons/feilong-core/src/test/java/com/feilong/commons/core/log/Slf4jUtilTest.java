package com.feilong.commons.core.log;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 30, 2013 2:26:38 AM
 */
public class Slf4jUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(Slf4jUtilTest.class);

	@Test
	public final void test(){
		System.out.println(Slf4jUtil.formatMessage("{},{}", "今天", "aaaa"));
	}
}
