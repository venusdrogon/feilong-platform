package com.feilong.tools.jsoup;

import org.jsoup.nodes.Document;
import org.junit.Test;

@SuppressWarnings("all")
public class JsoupUtilTest{

	@Test
	public void getDocument() throws JsoupUtilException{
		String urlString = "http://www.d9cn.org/d9cnbook/50/50537/10967924.html";
		Document document = JsoupUtil.getDocument(urlString);
	}
}