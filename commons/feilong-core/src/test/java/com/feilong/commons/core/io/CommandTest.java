/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.feilong.commons.core.io;

import java.io.IOException;

import org.junit.Test;

/**
 * The Class CommandTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-19 下午03:51:40
 * @since 1.0
 */
@SuppressWarnings("all")
public class CommandTest{

	/**
	 * Assoc.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void assoc() throws IOException{
		Command.assoc(".txt", "txtfile");
	}

	@Test
	public void assocList() throws IOException{
		Command.callCmd(Command.assocList());
	}

	/**
	 * Exec shutdown stop.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void execShutdownStop() throws IOException{
		Command.execShutdownStop();
	}

	/**
	 * Exec shutdown at.
	 */
	@Test
	public void execShutdownAt(){
		// execShutdownAt("04:30");
	}

	/**
	 * Exec shutdown.
	 */
	@Test
	public void execShutdown(){
		// execShutdown(14440);
	}
}
