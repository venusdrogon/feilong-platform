/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package com.feilong.commons.core.awt;

import org.junit.Test;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-5 下午05:15:42
 * @since 1.0
 */
public class DesktopUtilTest{

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
