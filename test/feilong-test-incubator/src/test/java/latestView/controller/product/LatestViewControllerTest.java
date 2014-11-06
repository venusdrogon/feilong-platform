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
package latestView.controller.product;

import java.math.BigDecimal;
import java.util.TreeSet;

import latestView.shop.web.command.BrowsingHistoryCommand;
import latestView.shop.web.command.SimpleSkuCommand;
import net.sf.json.JSONArray;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.util.StringUtil;

/**
 * The Class LatestViewControllerTest.
 *
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午3:00:29
 */
@ContextConfiguration(locations = { "classpath*:feilong-security.xml" })
public class LatestViewControllerTest extends AbstractJUnit4SpringContextTests{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(LatestViewControllerTest.class);

	// @Autowired
	// private SymmetricEncryption symmetricEncryption;
	/**
	 * Encrypt to hex string.
	 */
	@Test
	public void encryptToHexString(){
		BrowsingHistoryCommand browsingHistoryCommand = new BrowsingHistoryCommand();
		browsingHistoryCommand.setDate(1334072052081L);
		SimpleSkuCommand simpleSkuCommand = new SimpleSkuCommand();
		simpleSkuCommand.setCode("317809-100");
		simpleSkuCommand.setFobPirce(new BigDecimal("2199"));
		simpleSkuCommand.setId(5667L);
		simpleSkuCommand.setListPrice(new BigDecimal("2199"));
		simpleSkuCommand.setName("AIR FORCE 1 HIGH LUX MAX AIR '08 QS 空军一号（限量发售）");
		browsingHistoryCommand.setSimpleSkuCommand(simpleSkuCommand);
		TreeSet<BrowsingHistoryCommand> treeSet = new TreeSet<BrowsingHistoryCommand>();
		treeSet.add(browsingHistoryCommand);
		JSONArray jsonArray = JSONArray.fromObject(treeSet);
		String json = jsonArray.toString();
		log.info(json);
		log.info("toHexStringUpperCase:{}", StringUtil.toHexStringUpperCase(json));
		// 5B7B2264617465223A313333343037323035323038312C2273696D706C65536B75436F6D6D616E64223A7B22636F6465223A223331373830392D313030222C22666F625069726365223A323139392C226964223A353636372C226C6973745072696365223A323139392C226E616D65223A2241495220464F52434520312048494748204C5558204D4158204149522027303820515320BFD5BEFCD2BBBAC5A3A8CFDEC1BFB7A2CADBA3A9227D7D5D
		// 5B7B2264617465223A313333343037323035323038312C2273696D706C65536B75436F6D6D616E64223A7B22636F6465223A223331373830392D313030222C22666F625069726365223A323139392C226964223A353636372C226C6973745072696365223A323139392C226E616D65223A2241495220464F52434520312048494748204C5558204D41582041495220273038205153203F3F3F3F3F3F3F3F3F3F227D7D5D
		// 5B7B2264617465223A313333343037323035323038312C2273696D706C65536B75436F6D6D616E64223A7B22636F6465223A223331373830392D313030222C22666F625069726365223A323139392C226964223A353636372C226C6973745072696365223A323139392C226E616D65223A2241495220464F52434520312048494748204C5558204D4158204149522027303820515320E7A9BAE5869BE4B880E58FB7EFBC88E99990E9878FE58F91E594AEEFBC89227D7D5D
		// 因为cookie 的name 和value 不能出现特殊字符 (空白字符 以及下列字符 ： @ : ;? , " / [ ] ( ) =),所以加密
		// String encryptToHexString = symmetricEncryption.encryptToHexString(json);
		// String aString = "{"simpleSkuCommand":{"code":"317809-100","fobPirce":2199,"id":5667,"listPrice":2199,"name":"AIR FORCE 1 HIGH LUX MAX AIR '08 QS
		// 空军一号（限量发售）"}}";
		// log.info(encryptToHexString);
	}

	// 23:34:12 DEBUG (SymmetricEncryption.java:166) [encryptToHexString()] -
	// algorithm:Blowfish,keyString:keyFor@BrowsingHistory,original:[{"date":1334072052081,"simpleSkuCommand":{"code":"317809-100","fobPirce":2199,"id":5667,"listPrice":2199,"name":"AIR FORCE 1 HIGH LUX MAX AIR '08 QS 空军一号（限量发售）"}}],hexStringUpperCase:4B91A40422C2448664799AEF2984A12636072B3B65B056685B05594713AD41F778053E523D478C763BB4C29FA5783C020F2E3CFB15AE82E594858EF2DF180D7CCE81F74246E68E7B971F38094BF35B08122AB537400E68EFDC63B286669FE57524E1C713988EB86DBAD42DE329A90C8CD75E288B423183B2E47BA57728FAD0F18ABA3D060F959F0336FC3BDD3F88A222251EEFA0AE086779012BCEB5345CE52F602926E97DA86D328E19D8ABE0C6F4C69DBF7FF4C696B879B6371207028A0B89
	/**
	 * Test.
	 */
	@Test
	public void test(){
		TreeSet<BrowsingHistoryCommand> treeSet = new TreeSet<BrowsingHistoryCommand>();
		/*****************************************************/
		BrowsingHistoryCommand historyCommand1 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand1 = new SimpleSkuCommand();
		simpleSkuCommand1.setCode("25871111");
		historyCommand1.setSimpleSkuCommand(simpleSkuCommand1);
		historyCommand1.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-01 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand1);
		/*****************************************************/
		BrowsingHistoryCommand historyCommand2 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand2 = new SimpleSkuCommand();
		simpleSkuCommand2.setCode("25872222");
		historyCommand2.setSimpleSkuCommand(simpleSkuCommand2);
		historyCommand2.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-02 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand2);
		/*****************************************************/
		BrowsingHistoryCommand historyCommand3 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand3 = new SimpleSkuCommand();
		simpleSkuCommand3.setCode("25873333");
		historyCommand3.setSimpleSkuCommand(simpleSkuCommand3);
		historyCommand3.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-03 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand2);
		/*****************************************************/
		BrowsingHistoryCommand historyCommand4 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand4 = new SimpleSkuCommand();
		simpleSkuCommand4.setCode("25874444");
		historyCommand4.setSimpleSkuCommand(simpleSkuCommand4);
		historyCommand4.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-04 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand2);
		/*****************************************************/
		BrowsingHistoryCommand historyCommand5 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand5 = new SimpleSkuCommand();
		simpleSkuCommand5.setCode("25875555");
		historyCommand5.setSimpleSkuCommand(simpleSkuCommand5);
		historyCommand5.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-05 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand2);
		/*****************************************************/
		BrowsingHistoryCommand historyCommand6 = new BrowsingHistoryCommand();
		SimpleSkuCommand simpleSkuCommand6 = new SimpleSkuCommand();
		simpleSkuCommand6.setCode("25876666");
		historyCommand6.setSimpleSkuCommand(simpleSkuCommand6);
		historyCommand6.setDate(DateUtil.getTime(DateUtil.string2Date("2012-05-06 12:00:00", DatePattern.commonWithTime)));
		treeSet.add(historyCommand2);
		/*****************************************************/
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(treeSet);
		String string = jsonArray.toString();
		log.info(string);
		// log.info(symmetricEncryption.encryptToBase64String(string));
		// String encryptToHexString = symmetricEncryption.encryptToHexString(string);
		// log.info(encryptToHexString);
		// log.info(encryptToHexString.length() + "");
		// TreeSet<BrowsingHistoryCommand> browsingHistoryCommands = new TreeSet<BrowsingHistoryCommand>(net.sf.json.JSONArray.toCollection(
		// jsonArray,
		// BrowsingHistoryCommand.class));
		/*****************************************************/
		// JSONArray jsonArray = new JSONArray(browsingHistoryCommands, "*,simpleSkuCommand");
		// String json = jsonArray.toString();
		// log.info(json);
		// for (BrowsingHistoryCommand browsingHistoryCommand : treeSet){
		// log.info(browsingHistoryCommand.getSimpleSkuCommand().getCode());
		// }
	}
}
