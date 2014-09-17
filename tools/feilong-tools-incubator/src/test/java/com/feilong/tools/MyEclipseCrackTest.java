package com.feilong.tools;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class MyEclipseCrackTest{

	private static final Logger	log	= LoggerFactory.getLogger(MyEclipseCrackTest.class);

	@Test
	public void getSerial(){
		String id = "venusdrogon";
		String num = "999";
		String version = "100";
		String code = MyEclipseCrack.getSerial(id, version, num);
		log.info("Subscriber:" + id);
		log.info("Subscription Code:" + code);
		//	Assert.assertEquals("iLR8ZO-655444-66678656479643949", code);
	}
}
