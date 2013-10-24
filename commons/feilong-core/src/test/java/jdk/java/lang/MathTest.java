/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of FeiLong
 * Network Technology, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with FeiLong.
 * <p/>
 * FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * <p/>
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
