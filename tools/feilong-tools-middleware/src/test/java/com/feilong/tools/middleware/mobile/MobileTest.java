/**
 * 
 */
package com.feilong.tools.middleware.mobile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feilong.tools.middleware.mobile.MobileUtil;

/**
 * 手机相关测试
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-3-1 下午10:43:20
 */
public class MobileTest{

	String	mobileNumber	= "15001841318";

	/**
	 * {@link com.feilong.tools.middleware.mobile.MobileUtil#getMobileNumberHided(java.lang.String)} 的测试方法。
	 */
	@Test
	public void testGetMobileNumberHided(){
		assertEquals("150****1318", MobileUtil.getMobileNumberHided(mobileNumber));
	}

	// @Test
	public void testGetMobileNumberNumberSegment(){
		assertEquals("150", MobileUtil.getMobileNumberNumberSegment(mobileNumber));
	}

	@Test
	public void getMobileNumberHided(){
		System.out.println(MobileUtil.getMobileNumberHided(mobileNumber, 15));
		// assertEquals(mobileNumber, FeiLongMobile.getMobileNumberHided(mobileNumber,4));
	}
}
