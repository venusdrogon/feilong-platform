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
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-5-23 15:58:19
 */
public class PagerUtilTest{

	/** The Constant log. */
	private static final Logger	log					= LoggerFactory.getLogger(PagerUtilTest.class);

	/** The en loc. */
	private Locale				enLoc				= new Locale("en", "US");						// 表示美国地区

	/** The fr loc. */
	private Locale				frLoc				= new Locale("fr", "FR");						// 表示法国地区

	/** The zh loc. */
	private Locale				zhLoc				= new Locale("zh", "CN");						// 表示中国地区

	// Locale.ENGLISH;
	/** The locale. */
	private Locale				locale				= Locale.SIMPLIFIED_CHINESE;

	/** The debug is not parse vm. */
	boolean						debugIsNotParseVM	= false;

	/**
	 * Name.
	 */
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

	/**
	 * Gets the pager content.
	 * 
	 * @return the pager content
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
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
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
