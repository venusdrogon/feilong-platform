/**
 * 
 */
package com.feilong.tools.middleware;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.middleware.EarthUtil;

/**
 * 测试 地球
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-6 下午11:21:21
 */
public class EarthTest{

	private static final Logger	log	= LoggerFactory.getLogger(EarthTest.class);

	/**
	 * {@link com.feilong.tools.middleware.EarthUtil#getDistance(double, double, double, double)} 的测试方法。
	 */
	@Test
	public void testGetDistance(){
		log.info("" + EarthUtil.getDistance(120, 36, 121, 36));
	}
}
