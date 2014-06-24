package jdk.java.util;

import java.util.Enumeration;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 18, 2012 11:44:58 PM
 */
public class ListResourceBundleTest{

	private static final Logger	log	= LoggerFactory.getLogger(ListResourceBundleTest.class);

	@Test
	public void name(){
		SanguoListResourceBundle listResourceBundle = new SanguoListResourceBundle();

		Enumeration<String> keys = listResourceBundle.getKeys();

		while (keys.hasMoreElements()){
			String key = keys.nextElement();
			log.info(key + ":" + listResourceBundle.getObject(key));
		}

	}
}
