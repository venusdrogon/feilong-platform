package com.feilong.commons.core.awt;

import org.junit.Test;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-5 下午05:15:42
 * @since 1.0
 */
@SuppressWarnings("all")public class DesktopUtilTest{

	@Test
	public final void testBrowse1(){
		int id = 14;
		String s = "";
		DesktopUtil.browse("http://101.95.128.146/payment/paymentChannel?s=" + s + "&id=" + id);
	}

	/**
	 * {@link com.feilong.commons.core.awt.DesktopUtil#browse(java.lang.String)} 的测试方法。
	 */
	@Test
	public final void testBrowse(){
		String[] strings = {
				"2RMD217-4",
				"2RMD249-1",
				"2RMD679-4",
				"2RMD679-4",
				"2RMD697-3",
				"2RMD697-3",
				"ARHE027-2",
				"ARHE041-1",
				"CRDF013-3" };
		for (String string : strings){
			DesktopUtil.browse("http://list.tmall.com/search_product.htm?q=" + string + "&type=p&cat=all&userBucket=5&userBucketCell=25");
		}
	}
}
