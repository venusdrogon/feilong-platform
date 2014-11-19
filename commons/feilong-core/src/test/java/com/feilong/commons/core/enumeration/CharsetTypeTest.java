package com.feilong.commons.core.enumeration;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class CharsetTypeTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-5 下午5:27:11
 */
public class CharsetTypeTest{

	/** The Constant log. */
	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(CharsetTypeTest.class);

	/**
	 * Checks if is supported.
	 */
	@Test
	public void isSupported(){
		assertEquals(true, Charset.isSupported(CharsetType.ISO_8859_1));
	}
}
