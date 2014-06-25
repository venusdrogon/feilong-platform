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
package org.springframework.util;

import java.net.URI;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriTemplate;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class AntPathMatcherTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-3-29 下午4:14:43
 */
public class AntPathMatcherTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(AntPathMatcherTest.class);

	/**
	 * Test match.
	 */
	@Test
	public void testMatch(){
		PathMatcher matcher = new AntPathMatcher();
		// 完全路径url方式路径匹配
		// String requestPath="http://localhost:8080/pub/login.jsp";//请求路径
		// String patternPath="**/login.jsp";//路径匹配模式
		// 不完整路径uri方式路径匹配
		// String requestPath="/app/pub/login.do";//请求路径
		// String patternPath="/**/login.do";//路径匹配模式
		// 模糊路径方式匹配
		// String requestPath="/app/pub/login.do";//请求路径
		// String patternPath="/**/*.do";//路径匹配模式
		// 包含模糊单字符路径匹配
		String requestPath = "/2-5-3-13-1.htm";// 请求路径
		String patternPath = "/{c1}-{c2}-{c3}-{c4}.htm";// 路径匹配模式
		requestPath = "/c2-5-3-11-pige-黑-52-chuck taylor all star-vintage.htm";// 请求路径
		patternPath = "/c{categoryCode}-{material}-{color}-{size}-{kind}-{style}.htm";// 路径匹配模式
		requestPath = "/s/c-m-c-s-k-s-o.htm";// 请求路径
		patternPath = "/s/c{categoryCode}-m{material}-c{color}-s{size}-k{kind}-s{style}-o{order}.htm";// 路径匹配模式
		boolean result = matcher.match(patternPath, requestPath);
		log.info(result + "");
		Map<String, String> map = matcher.extractUriTemplateVariables(patternPath, requestPath);
		log.info("map:{}", JsonUtil.format(map));
		map.put("color", "XL");
		UriTemplate uriTemplate = new UriTemplate(patternPath);
		URI uri = uriTemplate.expand(map);
		log.info(uri.toString());
		// UriComponents uriComponents = UriComponentsBuilder.fromPath(patternPath).buildAndExpand(map);
		// log.info(uriComponents.toString());
	}
}
