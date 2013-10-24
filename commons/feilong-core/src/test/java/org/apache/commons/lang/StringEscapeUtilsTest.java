package org.apache.commons.lang;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringEscapeUtilsTest{

	private static final Logger	log	= LoggerFactory.getLogger(StringEscapeUtilsTest.class);

	@Test
	public void unescapeHtml(){
		String a = "m&eacute;n&nbsp;";
		String result = null;
		result = StringEscapeUtils.unescapeHtml3(a);
		//result = HtmlUtils.htmlUnescape(a);
		log.info(result);
	}
}
