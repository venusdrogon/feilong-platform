package com.feilong.tools;

import org.junit.Test;

public class MyEclipseCrackTest{

	@Test
	public void getSerial(){
		String id = "venusdrogon";
		String num = "999";
		String version = "100";
		String code = MyEclipseCrack.getSerial(id, version, num);
		System.out.println("Subscriber:" + id);
		System.out.println("Subscription Code:" + code);
	//	Assert.assertEquals("iLR8ZO-655444-66678656479643949", code);
	}
}
