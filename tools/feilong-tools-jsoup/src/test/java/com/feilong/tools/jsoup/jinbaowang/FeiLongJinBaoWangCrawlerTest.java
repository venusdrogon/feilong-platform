package com.feilong.tools.jsoup.jinbaowang;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;

@SuppressWarnings("all")
public class FeiLongJinBaoWangCrawlerTest{

	private static final Logger	log				= LoggerFactory.getLogger(FeiLongJinBaoWangCrawlerTest.class);

	private static String		searchPage		= "http://www.jinbaowang.cn/gallery--n,%s-grid.html";

	private static String		directoryName	= "C:\\Users\\venusdrogon\\Desktop\\feilong";

	private static String		skuCodePath		= "F:\\Life 生活\\Job 工作\\淘宝开店\\找不到的补充.txt";

	@Test
	public void getSearchPage(){
		String code = "HB1523";
		String urlString = StringUtil.format(searchPage, code);
		log.info(urlString);
	}

	@Test
	public void getSkuDetailsRealUrl(){
		String code = "1001";
		log.info(FeiLongJinBaoWangCrawler.getSkuDetailsRealUrl(code));
	}

	@Test
	public void getCodeList() throws IOException{
		List<String> codeStrings = FeiLongJinBaoWangCrawler.getCodeList(skuCodePath);
		for (String string : codeStrings){
			log.info(string);
		}
	}

	@org.junit.Test
	public void getSkuCodeAndImagesMap() throws IOException{
		List<String> codeStrings = FeiLongJinBaoWangCrawler.getCodeList(skuCodePath);
		Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.getSkuCodeAndImagesMap(codeStrings);
		FeiLongJinBaoWangCrawler.convertSkuCodeImagesToFile(skuCodeAndImagesMap, directoryName);
	}

	@org.junit.Test
	public void downSkuImages() throws IOException{
		//		List<String> codeStrings = FeiLongJinBaoWangCrawler.getCodeList(skuCodePath);
		//		Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.getSkuCodeAndImagesMap(codeStrings);
		String objPath = directoryName + "/20111025223851.obj";
		Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.convertObjectToMap(objPath);
		skuCodeAndImagesMap.remove("RD1987");
		skuCodeAndImagesMap.remove("YD5817");
		skuCodeAndImagesMap.remove("HB1523");
		skuCodeAndImagesMap.remove("HB8108");
		skuCodeAndImagesMap.remove("HB8091");
		skuCodeAndImagesMap.remove("HB8031");
		skuCodeAndImagesMap.remove("HB8021");
		skuCodeAndImagesMap.remove("HB602");
		skuCodeAndImagesMap.remove("HB623");
		skuCodeAndImagesMap.remove("HB614");
		skuCodeAndImagesMap.remove("HB611");
		skuCodeAndImagesMap.remove("HB612");
		skuCodeAndImagesMap.remove("HB3011");
		FeiLongJinBaoWangCrawler.downSkuImages(skuCodeAndImagesMap, directoryName);
	}

	@org.junit.Test
	public void convertObjectToMap(){
		String objPath = directoryName + "/20111022215702.obj";
		Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.convertObjectToMap(objPath);
		log.info("" + skuCodeAndImagesMap.size());
	}
}
