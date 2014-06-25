
package com.feilong.commons.core.io;

import java.io.IOException;

import org.junit.Test;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午03:51:40
 * @since 1.0
 */
@SuppressWarnings("all")public class CommandTest{

	@Test
	public void assoc() throws IOException{
		Command.assoc(".txt", "txtfile");
	}

	@Test
	public void execShutdownStop() throws IOException{
		Command.execShutdownStop();
	}

	@Test
	public void execShutdownAt(){
		// execShutdownAt("04:30");
	}

	@Test
	public void execShutdown(){
		// execShutdown(14440);
	}
}
