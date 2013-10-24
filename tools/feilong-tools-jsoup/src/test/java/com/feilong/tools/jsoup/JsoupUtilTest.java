package com.feilong.tools.jsoup;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class JsoupUtilTest{

	@Test
	public void getDocument(){
		String urlString = "http://www.d9cn.org/d9cnbook/50/50537/10967924.html";
		Document document = JsoupUtil.getDocument(urlString);
	}
}
