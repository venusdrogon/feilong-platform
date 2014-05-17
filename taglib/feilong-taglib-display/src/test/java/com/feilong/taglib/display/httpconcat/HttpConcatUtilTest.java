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
package com.feilong.taglib.display.httpconcat;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.taglib.display.httpconcat.command.HttpConcatParam;

/**
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014年5月16日 下午11:42:49
 * @since 1.0.7
 */
public class HttpConcatUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(HttpConcatUtilTest.class);

	/**
	 * Test method for
	 * {@link com.feilong.taglib.display.httpconcat.HttpConcatUtil#getWriteContent(com.feilong.taglib.display.httpconcat.command.HttpConcatParam)}
	 * .
	 */
	@Test
	public final void testGetWriteContent(){
		HttpConcatParam httpConcatParam = new HttpConcatParam();
		httpConcatParam.setType("js");
		//httpConcatParam.setDomain("http://www.feilong.com");
		httpConcatParam.setRoot("/js/");
		httpConcatParam.setHttpConcatSupport(true);
		List<String> itemSrcList = new ArrayList<String>();
		itemSrcList.add("a.js");
		//itemSrcList.add("/b.js");
		httpConcatParam.setItemSrcList(itemSrcList);
		httpConcatParam.setVersion("20140517");
		log.info(HttpConcatUtil.getWriteContent(httpConcatParam));
	}
}
