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
package com.feilong.commons.core.net;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.enumeration.CharsetType;

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
		System.out.println(URIUtil.encode(value, CharsetType.GBK));
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
		System.out.println(result);
	}

	@Test
	public void testGetUnionUrl2(){
		try{
			URL url = new URL("http://www.exiaoshuo.com/jinyiyexing/");
			result = URIUtil.getUnionUrl(url, "/jinyiyexing/1173348/");
			System.out.println(result);
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
		String url = "http://127.0.0.1/cmens/t-b-f-a-c-s-f-p-g-e-i-o.htm";
		url = "/cmens/t-b-f-a-c-s-f-p400-600,0-200,200-400,600-up-gCold Gear-eBase Layer-i1-o.htm";

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
}
