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

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.xml.ws.handler.MessageContext;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.taglib.display.pager.command.PagerConstants;
import com.feilong.taglib.display.pager.command.PagerParams;

/**
 * The Class PagerUtilTest.
 */
public class PagerUtilTest{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(PagerUtilTest.class);

	private Locale				enLoc				= new Locale("en", "US");						// 表示美国地区

	private Locale				frLoc				= new Locale("fr", "FR");						// 表示法国地区

	private Locale				zhLoc				= new Locale("zh", "CN");						// 表示中国地区

	// Locale.ENGLISH;
	private Locale				locale				= Locale.SIMPLIFIED_CHINESE;

	boolean						debugIsNotParseVM	= false;

	@Test
	public void name(){
		if (log.isInfoEnabled()){
			log.info(new Locale("en", "US").toString());
			log.info(Locale.ENGLISH.toString());
			log.info(Locale.US.toString());
			log.info(Locale.CHINA.toString());
			log.info(Locale.CHINESE.toString());
			log.info(Locale.SIMPLIFIED_CHINESE.toString());
		}
	}

	@SuppressWarnings({ "javadoc", "unused" })
	@Test
	public void getPagerContent() throws IOException{
		int count = 1024;
		int currentPageNo = -1;
		int pageSize = 10;
		int maxIndexPages = 8;
		String skin = PagerConstants.DEFAULT_SKIN;
		String pageUrl = "http://localhost:8888/pager.htm";

		// pageUrl =
		// "http://www.underarmour.cn/cmens-bottoms-pant/t-b-f-a-c-s-fLoose-p-g-e-i-o.htm?'\"--></style></script><script>netsparker(0x0000E1)</script>=";

		String pageParamName = "pageNo";
		String vmPath = PagerConstants.DEFAULT_TEMPLATE_IN_CLASSPATH;

		// log.debug("===================================================");

		PagerParams pagerParams = new PagerParams(count, pageUrl);

		pagerParams.setCurrentPageNo(currentPageNo);
		pagerParams.setPageSize(pageSize);
		pagerParams.setMaxIndexPages(maxIndexPages);
		pagerParams.setSkin(skin);
		pagerParams.setPageParamName(pageParamName);
		pagerParams.setVmPath(vmPath);
		pagerParams.setCharsetType(CharsetType.UTF8);

		pagerParams.setDebugIsNotParseVM(debugIsNotParseVM);

		pagerParams.setLocale(locale);

		String content = PagerUtil.getPagerContent(pagerParams);

		log.info("the param content:\n\n{}", content);

		if (false){
			String filePath = "F://pagerTest.html";

			IOWriteUtil.write(filePath, content, CharsetType.UTF8);
			DesktopUtil.browse(filePath);
		}

	}

	/**
	 * Test method for.
	 * {@link com.feilong.taglib.display.pager.PagerUtil#getPagerContent(int, int, int, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 * @throws IOException 
	 */
	@Test
	public void testGetPagerContent() throws IOException{
		Date beginDate = new Date();
		int j = 1;// 80000
		j = 80000;
		j = 100;
		j = 800;
		for (int i = 0; i < j; ++i){
			// log.debug("===================================================");
			getPagerContent();
			// log.info("the param content:\n\n{}", content);
			// log.debug("{} ", i);
		}
		Date endDate = new Date();
		log.info("{}次\t{}", j, DateUtil.getIntervalForView(beginDate, endDate));
	}

}
