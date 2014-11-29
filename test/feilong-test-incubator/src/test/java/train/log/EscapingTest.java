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
package train.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class EscapingTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年11月4日 下午3:56:06
 * @since 1.0.8
 */
public class EscapingTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(EscapingTest.class);

	/**
	 * Test escaping2.
	 */
	@Test
	public void testEscaping2(){
		if (log.isDebugEnabled()){
			//			log.debug("Set {1,2} differs from {}", "3");
			//			log.debug("Set {1,2} differs from {{}}", "3");
			//			log.debug("Set \\{} differs from {}", "3");
			log.debug("File name is C:\\\\{}.", "file.zip");
		}
		
		
		if (log.isDebugEnabled()){
			//log.debug("the param localVar:{}", localVar);
			log.debug("the param xxxxxxxxxxxxxxxxxxxxxxxxxxxx ");
		}
	}
}
