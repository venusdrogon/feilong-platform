package com.feilong.commons.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a> 
 * @version 1.0 Aug 12, 2013 1:22:08 AM  
 */
public class FeiLongVersionTest{

	private static final Logger	log	= LoggerFactory.getLogger(FeiLongVersionTest.class);

	/**
	 * Test method for {@link com.feilong.commons.core.FeiLongVersion#getVersion()}.
	 */
	@Test
	public final void testGetVersion(){
		log.info(FeiLongVersion.getVersion());
	}
}
