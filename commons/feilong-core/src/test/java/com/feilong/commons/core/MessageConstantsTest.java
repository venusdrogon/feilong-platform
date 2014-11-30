package com.feilong.commons.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-6-1 上午11:50:45
 * @since 1.0
 */
public class MessageConstantsTest{

	private static final Logger	log	= LoggerFactory.getLogger(MessageConstantsTest.class);

	@Test
	public void test(){
		log.debug(MessageConstants.DATE_MINUTE);
	}
}