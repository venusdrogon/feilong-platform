/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package com.feilong.commons.core.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;
import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-5 下午4:11:17
 */
public class URIUtilTest{

	private static final Logger	log		= LoggerFactory.getLogger(URIUtilTest.class);

	private String				result	= null;

	/**
	 * Test method for {@link com.feilong.commons.core.net.URIUtil#encode(java.lang.String, java.lang.Object[])}.
	 */
	@Test
	public void testEncode(){
		String value = "={}[]今天天气很不错今天天气很不错今天天气很不错今天天气很不错今天天气很不错";
		value = "http://xy2.cbg.163.com/cgi-bin/equipquery.py?server_name=风花雪月&query_order=selling_time DESC&search_page&areaid=2&server_id=63&act=search_browse&equip_type_ids&search_text=斩妖剑";
		value = "斩妖剑";
		value = "风花雪月";
		log.info(URIUtil.encode(value, CharsetType.GBK));
	}

	@Test
	public void encode(){
		String value = "白色/黑色/纹理浅麻灰";
		result = URIUtil.encode(value, CharsetType.UTF8);
		log.info(result);
		// Assert.assertEquals("%E5%8D%97%E6%9E%81%E4%BA%BA%E5%AE%98%E6%96%B9%E6%97%97%E8%88%B0", result);
		// value = "杜冲";
		log.info(URIUtil.encode(value, CharsetType.GBK));

		result = URIUtil.encode("Lifestyle / Graphic,", CharsetType.UTF8);
		log.info(result);
	}

	@Test
	public void testGetUnionUrl(){
		result = URIUtil.getUnionUrl("E:\\test", "sanguo");
		log.info(result);
	}

	@Test
	public void testGetUnionUrl2(){
		try{
			URL url = new URL("http://www.exiaoshuo.com/jinyiyexing/");
			result = URIUtil.getUnionUrl(url, "/jinyiyexing/1173348/");
			log.info(result);
		}catch (MalformedURLException e){
			e.printStackTrace();
		}
	}

	@Test
	public void decode(){
		result = URIUtil.decode("上海", CharsetType.GBK);
		log.info(result);
		result = URIUtil.decode("Lifestyle+%2F+Graphic,", CharsetType.UTF8);
		log.info(result);
		result = URIUtil.decode("%E6%88%91%E7%88%B1%E5%BC%A0%E5%85%88%E6%B3%BD%7E%7E%7E%40%E5%BC%A0%E5%85%88%E6%B3%BD", CharsetType.UTF8);
		log.info(result);

	}

	@Test
	public void specialCharToHexString(){
		result = URIUtil.specialCharToHexString(" ");
		log.info(result);
	}

	@Test
	public void create(){
		String url = "http://127.0.0.1/cmens/t-b-f-a-c-s-f-p-g-e-i-o.htm?a=1&a=2";
		// url = "/cmens/t-b-f-a-c-s-f-p400-600,0-200,200-400,600-up-gCold Gear-eBase Layer-i1-o.htm";

		String queryString = null;
		queryString = "'\"--></style></script><script>netsparker(0x0000E1)</script>=";
		// queryString = "'%22--%3E%3C/style%3E%3C/script%3E%3Cscript%3Enetsparker(0x0000E1)%3C/script%3E=";

		// url = url + "?" + queryString;
		log.info(url);
		// URIEditor uriEditor = new URIEditor();
		// uriEditor.setAsText(url);
		// log.info(URIEditor);
		// try{
		// URL url1 = new URL(url);
		// log.info(url1.toString());
		// }catch (MalformedURLException e){
		// e.printStackTrace();
		// }
		URI uri = URIUtil.create(url, CharsetType.UTF8);
		log.info(uri.toString());
	}

	@Test
	public void getEncodedUrlByValueMap(){
		String beforeUrl = "www.baidu.com";
		Map<String, String> keyAndValueMap = new HashMap<String, String>();
		keyAndValueMap.put("a", "aaaa");
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, keyAndValueMap, charsetType));
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, null, charsetType));
		log.info(URIUtil.getEncodedUrlByValueMap(beforeUrl, null, null));
	}

	@Test
	public void getEncodedUrlByArrayMap(){
		String beforeUrl = "www.baidu.com";
		Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
		keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, keyAndArrayMap, charsetType));
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, null, charsetType));
		log.info(URIUtil.getEncodedUrlByArrayMap(beforeUrl, null, null));
		log.info(URIUtil.getEncodedUrlByArrayMap(null, keyAndArrayMap, null));
	}

	@Test
	public void combineQueryString(){
		String beforeUrl = "www.baidu.com";
		Map<String, String[]> keyAndArrayMap = new HashMap<String, String[]>();
		keyAndArrayMap.put("a", new String[] { "aaaa", "bbbb" });
		String charsetType = CharsetType.UTF8;
		log.info(URIUtil.combineQueryString(keyAndArrayMap, charsetType));
		log.info(URIUtil.combineQueryString(null, charsetType));
		log.info(URIUtil.combineQueryString(null, null));
		log.info(URIUtil.combineQueryString(keyAndArrayMap, null));
	}

	@Test
	public void parseQueryToValueMap(){
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=1&b=2&a=3", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("a=1&", CharsetType.UTF8)));
		log.info(JsonUtil.format(URIUtil.parseQueryToValueMap("", CharsetType.UTF8)));

	}

	@Test
	public void parseQueryToValueMap1(){
		log.info(JsonUtil.format(URIUtil.parseQueryToArrayMap("a=1&b=2&a", CharsetType.UTF8)));
	}
}
