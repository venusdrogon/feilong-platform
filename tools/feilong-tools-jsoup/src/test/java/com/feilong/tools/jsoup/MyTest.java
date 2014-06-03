package com.feilong.tools.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTest{

	private static final Logger	log	= LoggerFactory.getLogger(MyTest.class);

	public static void main(String[] args){
		// 直接从字符串中输入 HTML 文档
		String html = "<html><head><title> 开源中国社区 </title></head>" + "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
		Document doc = Jsoup.parse(html);
		//	log.info(doc.getElementsByClass(""));
		String unsafe = "<p><a href='http://www.oschina.net/' onclick='stealCookies()'>  开源中国社区 </a></p>";
		String safe = Jsoup.clean(unsafe, Whitelist.basic());
		log.info(safe);
		// 输出 : 
		// <p><a href="http://www.oschina.net/" rel="nofollow"> 开源中国社区 </a></p> 
	}
}