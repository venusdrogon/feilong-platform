/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package jdk.java.lang;

import java.awt.Point;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-12 上午09:44:38
 */
public class MathTest{

	private static final Logger	log	= LoggerFactory.getLogger(MathTest.class);

	@Test
	public void atan2(){
		Point b = new Point(3, 3);
		Point d = new Point(5, 5);

		Point a = new Point(639, 348);
		b = new Point(747, 417);
		Point c = new Point(543, 501);
		d = new Point(652, 570);

		double angle = Math.atan2(d.y - b.y, b.x - d.x);

		log.info(Math.atan(1) + "");
		log.info(180 * Math.atan(1) / Math.PI + "");
		log.info("angle:" + angle);
		// x=180*x/Math.PI//转换为角度值

		double du = 180 * Math.atan2(d.y - b.y, b.x - d.x) / Math.PI;
		log.info(du + "");
	}

}
