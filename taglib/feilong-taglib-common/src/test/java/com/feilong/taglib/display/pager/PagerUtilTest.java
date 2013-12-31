/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.taglib.display.pager;

import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;

public class PagerUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(PagerUtilTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.taglib.display.pager.PagerUtil#getPagerContent(int, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetPagerContent(){
		Date beginDate = new Date();

		int count = 1024;
		int currentPageNo = 1;
		int pageSize = 10;
		int maxIndexPages = 8;
		String skin = PagerConstant.default_skin;
		String pageUrl = "http://localhost:8888/pager.htm";

		pageUrl = "http://www.underarmour.cn/cmens-bottoms-pant/t-b-f-a-c-s-fLoose-p-g-e-i-o.htm?'\"--></style></script><script>netsparker(0x0000E1)</script>=";

		String pageParamName = "pageNo";
		String vmPath = PagerConstant.default_templateInClassPath;

		int j = 1;// 80000
		j = 80000;
		j = 100;
		j = 80000;
		for (int i = 0; i < j; ++i){
			// log.debug("===================================================");

			PagerParams pagerParams = new PagerParams(count, pageUrl);

			pagerParams.setCurrentPageNo(currentPageNo);
			pagerParams.setPageSize(pageSize);
			pagerParams.setMaxIndexPages(maxIndexPages);
			pagerParams.setSkin(skin);
			pagerParams.setPageParamName(pageParamName);
			pagerParams.setVmPath(vmPath);
			pagerParams.setCharsetType(CharsetType.UTF8);
			pagerParams.setDebugIsNotParseVM(false);

			String content = PagerUtil.getPagerContent(pagerParams);
			// log.info("the param content:\n\n{}", content);
			// log.debug("{} ", i);
		}

		Date endDate = new Date();
		log.info("{}次\t{}", j, DateUtil.getIntervalForView(beginDate, endDate));
	}
}
