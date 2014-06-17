package jdk.java.util;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-28 上午09:37:50
 */
public class UUIDTest{

	private static final Logger	log	= LoggerFactory.getLogger(UUIDTest.class);

	@Test
	public void test(){
		//82bcab61-a61e-451a-ae40-eeaa2ea54ba9
		//dc45acb3-ee78-4883-a4df-452c4064bc22
		log.info(UUID.randomUUID().toString());
	}
}
