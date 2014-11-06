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

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月16日 下午6:27:05
 * @since 1.0.8
 */
public class Test1{

	private static final Logger	log	= LoggerFactory.getLogger(Test.class);

	/**
	 * TestTest.
	 */
	@Test
	public void testTest(){
		Long memberId = 8L;
		Long orderId = 2L;
		String createTime = DateUtil.date2String(new Date(), 
						DatePattern.timestampWithMillisecond);

		String format = 
			"memberId:[{}],at createTime:[{}],create orderId:[{}] successfully~";

		log.info(format, memberId, orderId, createTime);

		Object[] logArgs = { memberId, createTime, orderId };
		log.info(format, logArgs);
	}
}
