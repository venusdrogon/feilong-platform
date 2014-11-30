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
package com.feilong.taglib.display.sitemap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.io.IOWriteUtil;
import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class Test1.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Jun 26, 2014 4:09:19 PM
 */
public class SiteMapURLTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(Test.class);

	/**
	 * Test.
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test() throws IllegalArgumentException,IOException{
		String templateInClassPath = "velocity/sitemap.vm";
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();

		List<SiteMapURL> siteMapURLList = new ArrayList<SiteMapURL>();
		SiteMapURL siteMapURL = new SiteMapURL();
		siteMapURL.setChangefreq(ChangeFreq.daily);
		siteMapURL.setLastmod(new Date());
		siteMapURL.setLoc("http://www.example.com/?>>> >>>>>>>>>>>><<<<<<<<<<<<<<<<<&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&'''''''''''''''''''''");
		siteMapURL.setPriority(0.5f);
		siteMapURLList.add(siteMapURL);

		siteMapURL = new SiteMapURL();
		siteMapURL.setChangefreq(ChangeFreq.monthly);
		//siteMapURL.setLastmod("2005-01-01");
		siteMapURL.setLoc("http://www.example.com/2");
		siteMapURL.setPriority(0.5f);
		siteMapURLList.add(siteMapURL);

		siteMapURL = new SiteMapURL();
		//siteMapURL.setChangefreq(ChangeFreq.monthly);
		//siteMapURL.setLastmod("2005-01-01");
		siteMapURL.setLoc("http://www.example.com/1");
		siteMapURL.setPriority(0.5f);
		siteMapURLList.add(siteMapURL);

		siteMapURL = new SiteMapURL();
		//siteMapURL.setChangefreq(ChangeFreq.monthly);
		//siteMapURL.setLastmod("2005-01-01");
		siteMapURL.setLoc("http://www.example.com/1");
		siteMapURL.setPriority(0.5f);
		siteMapURLList.add(siteMapURL);

		//siteMapURL = new SiteMapURL();
		//siteMapURL.setLoc("http://www.example.com/1");
		//siteMapURLList.add(siteMapURL);

		siteMapURLList.add(null);

		contextKeyValues.put("siteMapURLList", siteMapURLList);
		String aString = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, contextKeyValues);
		//xstre
		String filePath = SystemUtils.USER_HOME + "/feilong/sitemap.xml";

		if (log.isInfoEnabled()){
			log.info(aString);
		}
		//		Document document = Dom4jUtil.getDocument(filePath);
		//		
		//		//document.
		IOWriteUtil.write(filePath, aString);
	}
}