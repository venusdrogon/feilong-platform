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
package com.feilong.tools.domain;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-2-26 上午12:39:55
 */
@SuppressWarnings("all")public class DomainCrawlerTest{

	private static final Logger	log				= LoggerFactory.getLogger(DomainCrawlerTest.class);

	private DomainCrawler		domainCrawler	= new NetCnDomainCrawlerImpl();

	String						one				= "a b c d e f g h i j k l m n o p q r s t u v w x y z";

	String						two				= "ab ad ag ah am an as at au ax ay ba bb be by da do eh el em en ex fa gi go ha he hi ho id if in is it ko la lo ma me mi mr ms mu my no nu of oh oi ok om on or os ow ox pa pc pg ph pi po rx so ta ti io uh um up us we xi ye yo";

	// 3个字的单词
	String						three			= "act add age ago aid aim air all and ant any arm art ash ask awe bad bag ban bar bat bay bed bee bet bid bin bit bow box boy bud bug bus cab can cap car cat cop cow cry cue cup cut dam day eye ebb dog end dip dim dew dye era eat due ego dig dot ear eve fan far fee few flu fly fog fry fur gap gas gay got gum gut guy ham hay hen hip his hit hop hot how hum hut ice icy ill ink inn ion jam jar jaw jet job jug kid kin kit lad lag lap law lay let lid lie lip log lot low mad mat mix mob mud mug nap net nod Now nut oak oar odd off oil old one opt ore our out owe owl own pad pan pat paw pay pea pen per pet pie pig pin pit pop pot pub put rag rap rat raw ray red rib rid rim rip rob rod rot row rub rug run sad saw say sea see set sew sex she shy sin sip sir sit six ski sky sly sob son sow spy sue sum sun tag tan tap tar tax tea ten the tie tin tip toe ton too top tow toy try tub tug two use van via war wax way web wet who why win wit yes yet you zip zoo";

	/**
	 * 域名后缀
	 */
	private static String[]		domainPrefixes2	= {
			"feiI",
			"feia",
			"feiwe",
			"feiit",
			"feius",
			"feiif",
			"feiin",
			"feime",
			"feioh",
			"feimy",
			"feiof",
			"feido",
			"feiat",
			"feiis",
			"feigo",
			"feihe",
			"feibe",
			"feian",
			"feiup",
			"feior",
			"feias",
			"feiso",
			"feiby",
			"feino",
			"feiam",
			"feion",
			"feito",
			"feitoo",
			"feiare",
			"feiage",
			"feihim",
			"feilet",
			"feithe",
			"feirun",
			"feimap",
			"feiend",
			"feiand",
			"feisee",
			"feiher",
			"feisay",
			"feione",
			"feiwhy",
			"feiago",
			"feifew",
			"feidid",
			"feiyet",
			"feiten",
			"feisix",
			"feidry",
			"feilow",
			"feifly",
			"feiold",
			"feiact",
			"feicut",
			"feigot",
			"feinow",
			"feimay",
			"feiput",
			"feiall",
			"feiask",
			"feiran",
			"feiout",
			"feihot",
			"feihas",
			"feiset",
			"feisit",
			"feibox",
			"feiown",
			"feiyou",
			"feiair",
			"feitop",
			"feihow",
			"feitry",
			"feihis",
			"feiany",
			"feiour",
			"feidog",
			"feihot",
			"feiyes",
			"feieye",
			"feimen",
			"feiwho",
			"feisaw",
			"feibut",
			"feiwas",
			"feisun",
			"feitwo",
			"feilot",
			"feilay",
			"feihad",
			"feifor",
			"feican",
			"feioff",
			"feifar",
			"feiway",
			"feiboy",
			"feicry",
			"feiget",
			"feired",
			"feicar",
			"feiadd",
			"feibig",
			"feieat",
			"feiday",
			"feisea",
			"feiuse",
			"feiman",
			"feinew",
			"feiwar",
			"feishe",
			"feibed",
			"feihard",
			"feirest",
			"feigive",
			"feitook",
			"feifarm",
			"feimany",
			"feiblue",
			"feidoes",
			"feipart",
			"feigame",
			"feisuch",
			"feimost",
			"feibest",
			"feigold",
			"feitell",
			"feitail",
			"feilive",
			"feipass",
			"feithen",
			"feileft",
			"feiroad",
			"feimain",
			"feiwere",
			"feisnow",
			"feiself",
			"feilead",
			"feiplan",
			"feifire",
			"feisame",
			"feihelp",
			"feiable",
			"feiease",
			"feiread",
			"feiknew",
			"feithis",
			"feiinch",
			"feionce",
			"feiport",
			"feimust",
			"feigrow",
			"feimind",
			"feihand",
			"feitime",
			"feiwalk",
			"feithey",
			"feibusy",
			"feiheat",
			"feiturn",
			"feimile",
			"feiwarm",
			"feiopen",
			"feideep",
			"feimoon",
			"feihear",
			"feikind",
			"feiwork",
			"feiname",
			"feifree",
			"feifull",
			"feidoor",
			"feiplay",
			"feionly",
			"feimiss",
			"feieast",
			"feimake",
			"feisome",
			"feifact",
			"feisaid",
			"feilove",
			"feilast",
			"feilate",
			"feidark",
			"feinoun",
			"feiwood",
			"feilong",
			"feihold",
			"feipage",
			"feiking",
			"feiwind",
			"feiroom",
			"feitown",
			"feifrom",
			"feikeep",
			"feisure",
			"feifive",
			"feifine",
			"feithat",
			"feifeel",
			"feilist",
			"feiwhat",
			"feiover",
			"feidown",
			"feislow",
			"feihome",
			"feieven",
			"feipull",
			"feihour",
			"feisoon",
			"feinext",
			"feimean",
			"feiidea",
			"feijust",
			"feigood",
			"feimore",
			"feitest",
			"feicall",
			"feipose",
			"feinear",
			"feishow",
			"feifood",
			"feiside",
			"feieach",
			"feifind",
			"feising",
			"feistop",
			"feifall",
			"feiwill",
			"feigave",
			"feialso",
			"feiknow",
			"feicity",
			"feihere",
			"feibeen",
			"feiship",
			"feitalk",
			"feihead",
			"feiface",
			"feidone",
			"feifour",
			"feiweek",
			"feilook",
			"feifast",
			"feiwant",
			"feibody",
			"feidraw",
			"feihalf",
			"feiword",
			"feitree",
			"feisong",
			"feibook",
			"feimove",
			"feifeet",
			"feibird",
			"feivery",
			"feilike",
			"feicame",
			"feithem",
			"feirule",
			"feiever",
			"feiarea",
			"feitold",
			"feimark",
			"feiwell",
			"feiseem",
			"feibase",
			"feiwhen",
			"feistay",
			"feiland",
			"feiback",
			"feigirl",
			"feiline",
			"feilife",
			"feimuch",
			"feicare",
			"feireal",
			"feineed",
			"feiunit",
			"feinote",
			"feiless",
			"feithan",
			"feimade",
			"feiwent",
			"feiyear",
			"feiwith",
			"feiyour",
			"feifoot",
			"feiform",
			"feirock",
			"feisize",
			"feitake",
			"feihigh",
			"feihave",
			"feirain",
			"feifish",
			"feicome",
			"feiboat",
			"feicold",
			"feiwest",
			"feifill",
			"feistep",
			"feiwait",
			"feistar",
			"feiboth",							};

	/**
	 * Test method for {@link com.feilong.tools.domain.NetCnDomainCrawlerImpl#hasRegister(java.lang.String)}.
	 */
	@Test
	public void testHasRegister(){
		String domain = "feihome.com";
		domain = "yifei.com";
		domain = "feilon.com";
		boolean hasRegister = domainCrawler.hasRegister(domain);
		log.info(hasRegister + "");
	}

	@Test
	public void hasRegisterBy(){
		String domainBody = "ifeit";
		domainBody = "360feilong";
		String domainSuffix = "com";
		boolean hasRegister = domainCrawler.hasRegister(domainBody, domainSuffix);
		log.info(hasRegister + "");
	}

	@Test
	public void hasRegister(){
		// String[] domains = { "ifeitian.cn", "ifei.com", "ifeilong.com", "ifeilong.cn" };
		String[] domains = { "ifeitian.cn", "ifei.com", "ifeilong.com", "ifeilong.cn" };
		List<String> list = domainCrawler.hasRegister(domains);
		printList(list);
	}

	@Test
	public void hasRegister3(){
		/**
		 * 想要的固定前缀
		 */
		String domainBodyPrefix = "fei";
		/**
		 * 域名后缀
		 */
		String[] domainBodySuffixes = { "home", "liu", "niao", "he", "chun", "mei", "jia", "niao" };
		String domainSuffix = "com";
		List<String> list = domainCrawler.hasRegister(domainBodyPrefix, domainBodySuffixes, domainSuffix);
		printList(list);
	}

	@Test
	public void testname() throws Exception{
		three = "feitian";
		log.info(three);
		String[] domainBodySuffixes = three.split(" ");
		for (String aString2 : domainBodySuffixes){
			log.info(aString2);
		}
		String domainBodyPrefix = "yi";
		String domainSuffix = "com";
		List<String> list = domainCrawler.hasRegister(domainBodyPrefix, domainBodySuffixes, domainSuffix);
		printList(list);
	}

	@Test
	public void hasRegister5() throws Exception{
		String[] domainBodyPrefix = { "yi", "i", "ai", "ya", "ao" };
		three = "feitian feilong feifeng";
		String[] domainBodySuffixes = three.split(" ");
		String domainSuffix = "com";
		List<String> list = domainCrawler.hasRegister(domainBodyPrefix, domainBodySuffixes, domainSuffix);
		printList(list);
	}

	@Test
	public void hasRegister6() throws Exception{
		String[] domainBodyPrefix = { "i", "yi", "ai" };// , "ao" "ya" , ""
		// // "feiyan","diqi","feihe", "feidu", // "flong",
		// "ftian",
		// "ftao",
		// "tbao" "feiteng", "feiyo","feike","feiei", "feihu",
		String[] domainBodySuffixes = {
				"feitian",
				"feilong",
				"feima",
				"feimao",
				"feizhu",
				"feiya",
				"feitao",
				"feili",
				"feibao",
				"feile",
				"feiye",
				"feio",
				"feila",
				"feiai",
				"feiba" };
		// String[] domainSuffixes = { "com" };
		String[] domainSuffixes = { "com" };
		//, "cn", "com.cn"
		List<String> list = domainCrawler.hasRegister(domainBodyPrefix, domainBodySuffixes, domainSuffixes);
		printList(list);
	}

	@Test
	public void hasRegisterChineseConvert(){
		String _chinese = "飞天奔月,坑爹";
		String[] chineseDomainBodys = _chinese.split(",");
		String domainSuffix = "com";
		List<String> list = domainCrawler.hasRegisterChineseConvert(chineseDomainBodys, domainSuffix);
		printList(list);
	}

	private void printList(List<String> list){
		if (Validator.isNullOrEmpty(list)){
			log.info("all domains is be register");
		}else{
			for (String string : list){
				log.info(string);
			}
		}
	}

	// *************************************************************
	@Test
	public void test3(){
		String[] a = { "tian", "ben", "yue" };
		for (String string : a){
			String[] split = string.split("");
			for (String string2 : split){
				log.info(string2);
			}
		}
	}
}
