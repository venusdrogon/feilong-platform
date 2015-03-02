package com.feilong.commons.core.enumeration;

import static org.junit.Assert.assertEquals;

import java.nio.charset.Charset;

import org.junit.Test;

import com.feilong.commons.core.io.CharsetType;

/**
 * The Class CharsetTypeTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-5 下午5:27:11
 */
public class CharsetTypeTest{

	/**
	 * Checks if is supported.
	 */
	@Test
	public void isSupported(){
		assertEquals(true, Charset.isSupported(CharsetType.ISO_8859_1));
	}
}