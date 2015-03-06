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
package com.feilong.tools.jsoup.jinbaowang;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.StringUtil;

/**
 * The Class FeiLongJinBaoWangCrawlerTest.
 */
@SuppressWarnings("all")
public class FeiLongJinBaoWangCrawlerTest{

    /** The Constant log. */
    private static final Logger log           = LoggerFactory.getLogger(FeiLongJinBaoWangCrawlerTest.class);

    /** The search page. */
    private static String       searchPage    = "http://www.jinbaowang.cn/gallery--n,%s-grid.html";

    /** The directory name. */
    private static String       directoryName = "C:\\Users\\venusdrogon\\Desktop\\feilong";

    /** The sku code path. */
    private static String       skuCodePath   = "F:\\Life 生活\\Job 工作\\淘宝开店\\找不到的补充.txt";

    /**
     * 获得 search page.
     */
    @Test
    public void getSearchPage(){
        String code = "HB1523";
        String urlString = StringUtil.format(searchPage, code);
        log.info(urlString);
    }

    /**
     * 获得 sku details real url.
     */
    @Test
    public void getSkuDetailsRealUrl(){
        String code = "1001";
        log.info(FeiLongJinBaoWangCrawler.getSkuDetailsRealUrl(code));
    }

    /**
     * 获得 code list.
     *
     * @throws IOException
     *             the IO exception
     */
    @Test
    public void getCodeList() throws IOException{
        List<String> codeStrings = FeiLongJinBaoWangCrawler.getCodeList(skuCodePath);
        for (String string : codeStrings){
            log.info(string);
        }
    }

    /**
     * 获得 sku code and images map.
     *
     * @throws IOException
     *             the IO exception
     */
    @org.junit.Test
    public void getSkuCodeAndImagesMap() throws IOException{
        List<String> codeStrings = FeiLongJinBaoWangCrawler.getCodeList(skuCodePath);
        Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.getSkuCodeAndImagesMap(codeStrings);
        FeiLongJinBaoWangCrawler.convertSkuCodeImagesToFile(skuCodeAndImagesMap, directoryName);
    }

    /**
     * Down sku images.
     *
     * @throws IOException
     *             the IO exception
     */
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

    /**
     * Convert object to map.
     */
    @org.junit.Test
    public void convertObjectToMap(){
        String objPath = directoryName + "/20111022215702.obj";
        Map<String, List<String>> skuCodeAndImagesMap = FeiLongJinBaoWangCrawler.convertObjectToMap(objPath);
        log.info("" + skuCodeAndImagesMap.size());
    }
}
