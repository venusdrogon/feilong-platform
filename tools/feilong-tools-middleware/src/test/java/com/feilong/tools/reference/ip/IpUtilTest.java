package com.feilong.tools.reference.ip;

import org.junit.Test;

import com.feilong.tools.reference.ip.IpUtil;


public class IpUtilTest{

	@Test
	public void testIpToAddress(){
		System.out.println(IpUtil.ipToAddress("58.25.165.250", "all"));
	}
}
