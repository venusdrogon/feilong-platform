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
package com.feilong.commons.core.util;

import java.util.Random;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-7 下午02:41:08
 */
public class StringUtilTest{

	private static final Logger	log		= LoggerFactory.getLogger(StringUtilTest.class);

	private String				text	= "jinxin.feilong";

	@Test
	public void searchCount(){
		String source = "jiiiiiinxin.feilong";
		log.info(StringUtil.searchTimes(source, "i") + "");
		log.info(StringUtil.searchTimes(source, "in") + "");
		log.info(StringUtil.searchTimes(source, "ii") + "");
		log.info(StringUtil.searchTimes(source, "xin") + "");
		Assert.assertEquals(1, StringUtil.searchTimes("xin", "xin"));
		Assert.assertEquals(1, StringUtil.searchTimes("xin", "i"));
		Assert.assertEquals(2, StringUtil.searchTimes("xiin", "i"));
		Assert.assertEquals(2, StringUtil.searchTimes("xiiiin", "ii"));

	}

	@Test
	public void addDoubleQuotes(){
		String text = "jinxin.feilong";
		log.info(StringUtil.addDoubleQuotes(text));
	}

	@Test
	public void isContainIgnoreCase(){
		String text = "jinxin.feilong";
		log.info(StringUtil.isContainIgnoreCase(null, "") + "");
		log.info(StringUtil.isContainIgnoreCase(text, null) + "");
		log.info(StringUtil.isContainIgnoreCase(text, "") + "");
		log.info(StringUtil.isContainIgnoreCase(text, "feilong") + "");
		log.info(StringUtil.isContainIgnoreCase(text, "feilong1") + "");
		log.info(StringUtil.isContainIgnoreCase(text, "feiLong") + "");
	}

	@Test
	public void toHexStringUpperCase(){
		log.info(StringUtil.toHexStringUpperCase(text));
	}

	@Test
	public void toOriginal(){
		String hexStringUpperCase = "5B7B2264617465223A313333343037323035323038312C2273696D706C65536B75436F6D6D616E64223A7B22636F6465223A223331373830392D313030222C22666F625069726365223A323139392C226964223A353636372C226C6973745072696365223A323139392C226E616D65223A2241495220464F52434520312048494748204C5558204D4158204149522027303820515320E7A9BAE5869BE4B880E58FB7EFBC88E99990E9878FE58F91E594AEEFBC89227D7D5D";
		hexStringUpperCase = "5B7B22636F6465223A224B3034383031222C226964223A3730302C226E616D65223A22E697B6E5B09AE6ACBEE992A5E58C99E689A3227D2C7B22636F6465223A2231333433363143222C226964223A35362C226E616D65223A22E58AB2E985B7E688B7E5A496436875636B205461796C6F7220416C6C2053746172204261636B205A6970227D5D";
		byte[] hexBytesToBytes = ByteUtil.hexBytesToBytes(hexStringUpperCase.getBytes());
		String msg = new String(hexBytesToBytes);
		log.info(msg);
		msg = StringUtil.toOriginal(hexStringUpperCase);
		log.info(msg);
	}

	@Test
	public void length(){
		String string = "我的新浪微博:http://weibo.com/venusdrogon,关注我哦[url=http://bbs.guqu.net/Query.asp?keyword=%B6%C5%B4%CF%D7%A8%BC%AD&boardid=0&sType=2]sssss[/url][url=http://weibo.com/venusdrogon][img]http://service.t.sina.com.cn/widget/qmd/1903991210/1c853142/5.png[/img][/url]";
		log.info(string.length() + "");

		log.info("572367774882343".length() + "");

		// 运单号
		log.info("1900681807840".length() + "");
	}

	@Test
	public void format(){
		System.out.println(StringUtil.format("%s%n%s%h", 1.2d, 2, "feilong"));
		System.out.println(StringUtil.format("%+d", -5));
		System.out.println(StringUtil.format("%-5d", -5));
		System.out.println(StringUtil.format("%05d", -5));
		System.out.println(StringUtil.format("% 5d", -5));
		System.out.println(StringUtil.format("%,f", 5585458.254f));
		System.out.println(StringUtil.format("%(f", -5585458.254f));
		System.out.println(StringUtil.format("%#f", -5585458.254f));
		System.out.println(StringUtil.format("%f和%<3.3f", 9.45));
		System.out.println(StringUtil.format("%2$s,%1$s", 99, "abc"));
		System.out.println(StringUtil.format("%1$s,%1$s", 99));
	}

	@Test
	public void replace(){
		Object content = "黑色/黄色/蓝色";
		String target = "/";
		Object replacement = "_";
		log.info(StringUtil.replace(content, target, replacement));
	}

	@Test
	public void join(){
		Assert.assertEquals("a_2", StringUtil.join(true, "_", "a", "2"));
		Assert.assertEquals("a_", StringUtil.join(true, "_", "a", ""));
		Assert.assertEquals("a", StringUtil.join(false, "_", "a", ""));
		Assert.assertEquals("a_2", StringUtil.join(false, "_", "a", "2"));

		Assert.assertEquals("a__bb", StringUtil.join(true, "_", "a", "", "bb"));
		Assert.assertEquals("a_bb", StringUtil.join(false, "_", "a", "", "bb"));

		Assert.assertEquals("a_bb_", StringUtil.join(true, "_", "a", "bb", ""));
		Assert.assertEquals("a_bb", StringUtil.join(false, "_", "a", "bb", ""));

		Assert.assertEquals("a_bb", StringUtil.join(false, "_", "a", "bb", ""));

		Assert.assertEquals("bb", StringUtil.join(false, "_", "", "bb", ""));

	}

	@Test
	public void replaceAll(){
		Object content = "黑色/黄色/蓝色";
		String target = "/";
		String replacement = "_";
		log.info(StringUtil.replaceAll(content, target, replacement));
	}

	@Test
	public void substring1(){
		log.info("3999e85461ce7271dd5292c88f18567e".length() + "");
		System.out.println(StringUtil.substring(text, 6));
	}

	@Test
	public void substring2(){
		System.out.println(StringUtil.substring(null, 6, 8));
		System.out.println(StringUtil.substring(text, text.length(), 8));
		System.out.println(StringUtil.substring(text, text.length() - 1, 8));
		System.out.println(StringUtil.substring(text, 1, 0));
		System.out.println(StringUtil.substring(text, 0, 5));
		System.out.println(StringUtil.substring(text, 6, 2));
		System.out.println(StringUtil.substring(text, 6, 20));
	}

	@Test
	public void substring3(){
		System.out.println(StringUtil.substring(null, "in", 8));
		System.out.println(StringUtil.substring(text, null, 8));
		System.out.println(StringUtil.substring(text, "sin", 8));
		System.out.println(StringUtil.substring(text, "in", 0));
		System.out.println(StringUtil.substring(text, "in", 5));
		// System.out.println(StringUtil.substring(text, "in", -2));
		System.out.println(StringUtil.substring(text, "in", 20));
		System.out.println(StringUtil.substring(text, "j", text.length() - 1));
	}

	@Test
	public void substring(){
		log.info(StringUtil.substring(text, "jinxin".length()));
		String text = "Index: src/main/java/com/jumbo/shop/web/command/PageCacheCommand.java";
		log.info(StringUtil.substring(text, "Index: ".length()));
	}

	@Test
	public void substring6(){
		log.info(StringUtil.substring(text, "jinxin.", 1));
	}

	// @Test
	public void testA(){
		String a = "SH1265,SH5951,SH6766,SH7235,SH1265,SH5951,SH6766,SH7235";
		System.out.println(a.replaceAll("([a-zA-Z]+[0-9]+)", "'$1'"));
	}

	/**
	 * 分隔字符串并添加引号
	 */
	@Test
	@Ignore
	public void splitAndAddYinHao(){
		String a = "12345,56789,1123456";
		String[] aStrings = a.split(",");
		StringBuilder stringBuilder = new StringBuilder();
		int size = aStrings.length;
		for (int i = 0; i < size; i++){
			stringBuilder.append("'" + aStrings[i] + "'");
			if (i != size - 1){
				stringBuilder.append(",");
			}
		}
		System.out.println(stringBuilder.toString());
	}

	/**
	 * 返回一个随机的字符串。150是基于该程序使用场景的抽样得到的长度。
	 */
	private static String getRandomString(){
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int length = 150 + r.nextInt(50);
		for (int i = 0; i < length; i++){
			sb.append('a' + r.nextInt(26));
		}
		return sb.toString();
	}

	@Test
	@Ignore
	public void test(){
		String aString = null;
		// String inputString="金鑫爱feilong";
		for (int i = 0; i < 10000; i++){
			String inputString = getRandomString();
			// 7.225s
			aString = StringUtil.toHexStringUpperCase(inputString);
			// 0.235
			// aString = FeiLongString.str2HexString_5(inputString);
		}
	}
}
