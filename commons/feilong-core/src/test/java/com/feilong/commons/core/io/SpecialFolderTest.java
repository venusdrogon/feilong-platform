package com.feilong.commons.core.io;

import org.junit.Test;

public class SpecialFolderTest{

	@Test
	public void testGetDesktop(){
		System.out.println("桌面地址:" + SpecialFolder.getDesktop());
	}

	@Test
	public void testGetMyDocuments(){
		System.out.println("我的文档:" + SpecialFolder.getMyDocuments());
	}

	@Test
	public void testGetTemp(){
		System.out.println("临时文件夹:" + SpecialFolder.getTemp());
	}
}
