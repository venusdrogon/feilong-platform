package jdk.java.util;

import java.util.StringTokenizer;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Feb 14, 2013 11:33:28 PM
 */
public class StringTokenizerTest{

	private static final Logger	log	= LoggerFactory.getLogger(StringTokenizerTest.class);

	@Test
	public final void test(){
		StringTokenizer stringTokenizer = new StringTokenizer("a b");

		while (stringTokenizer.hasMoreElements()){
			Object object = stringTokenizer.nextElement();
			log.info(object.toString());
		}
	}
}
