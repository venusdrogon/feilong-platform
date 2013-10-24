package org.springframework.web.util;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtilsTest{

	private static final Logger	log	= LoggerFactory.getLogger(HtmlUtilsTest.class);

	String						a	= "m&eacute;n&nbsp;";

	@Test
	public void testHtmlUtils(){
		String specialStr = "<div id=\"testDiv\">test1;test2</div>";
		String str1 = HtmlUtils.htmlEscape(specialStr); // ①将 HTML 特殊字符转义为 HTML 通用转义序列；
		System.out.println(str1);

		String str2 = HtmlUtils.htmlEscapeDecimal(specialStr);// 将 HTML 特殊字符转义为带 # 的十进制数据转义序列；
		System.out.println(str2);

		String str3 = HtmlUtils.htmlEscapeHex(specialStr);// 将 HTML 特殊字符转义为带 # 的十六进制数据转义序列；
		System.out.println(str3);

		// ④下面对转义后字符串进行反向操作
		System.out.println(HtmlUtils.htmlUnescape(str1));
		System.out.println(HtmlUtils.htmlUnescape(str2));
		System.out.println(HtmlUtils.htmlUnescape(str3));

		log.info(StringEscapeUtils.unescapeHtml4(str1));
		log.info(StringEscapeUtils.unescapeHtml4(str2));
		log.info(StringEscapeUtils.unescapeHtml4(str3));

		log.info(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(str1));
		log.info(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(str2));
		log.info(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(str3));
	}

	@Test
	public void htmlUnescape(){
		log.info(HtmlUtils.htmlUnescape(a));
	}

	@Test
	public void stringEscapeUtils(){
		log.info(StringEscapeUtils.unescapeHtml4(a));
	}

	@Test
	public void stringEscapeUtils2(){
		log.info(org.apache.commons.lang.StringEscapeUtils.unescapeHtml(a));
	}
}
