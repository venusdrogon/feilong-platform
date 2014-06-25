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
package com.feilong.tools.guqu;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.DesktopUtil;

/**
 * The Class FeiLongGuquUtilTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:27:48
 */
public class FeiLongGuquUtilTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(FeiLongGuquUtilTest.class);

	/**
	 * Gets the search url.
	 * 
	 */
	@Test
	public void testGetSearchUrl(){
		String keyword = "杜聪专辑";
		keyword = "康美之恋";
		keyword = "四维广大";
		String result = FeiLongGuquUtil.getSearchUrl(keyword, 0, 2);
		log.info(result);
		DesktopUtil.browse(result);
	}
}
