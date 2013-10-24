/**
 * 
 */
package com.feilong.tools.middleware;

import org.junit.Test;

import com.feilong.tools.middleware.EarthUtil;

/**
 *测试 地球
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-2-6 下午11:21:21
 */
public class EarthTest{

	/**
	 * {@link com.feilong.tools.middleware.EarthUtil#getDistance(double, double, double, double)} 的测试方法。
	 */
	@Test
	public void testGetDistance(){
		System.out.println(EarthUtil.getDistance(120, 36, 121, 36));
	}
}
