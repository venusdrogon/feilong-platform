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
package temple.io;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class FeLongIoTemple.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-30 0:24:58
 */
public class IoTemple{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(IoTemple.class);

	/**
	 * Prints the.
	 */
	@Test
	public void Print(){
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/
		String a = this.getClass().getClassLoader().getResource(".").getPath();
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/temple/io/
		String b = this.getClass().getResource("").getPath();
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/temple/io/%20
		String c = this.getClass().getResource(" ").getPath();
		// 获得编译类根目录
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/
		String d = this.getClass().getResource("/").getPath();
		// 获得应用程序完整路径
		// E:\Workspaces\eclipse3.5\feilong-platform\feilong-common
		String e = System.getProperty("user.dir");
		log.info(a);
		log.info(b);
		log.info(c);
		log.info(d);
		Thread thread = new Thread();
		thread.suspend();
		log.info(e);
	}
}
