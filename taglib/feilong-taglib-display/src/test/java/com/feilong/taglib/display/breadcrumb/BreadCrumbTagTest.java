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
package com.feilong.taglib.display.breadcrumb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.velocity.VelocityUtil;

/**
 * The Class SiteMapTagTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:22:39
 */
public class BreadCrumbTagTest{

    /** The Constant log. */
    private static final Logger                   log             = LoggerFactory.getLogger(BreadCrumbTagTest.class);

    /** The site map entities. */
    private static List<BreadCrumbEntity<Number>> siteMapEntities = new ArrayList<BreadCrumbEntity<Number>>();

    static{
        BreadCrumbEntity<Number> sme_1 = new BreadCrumbEntity<Number>(1, "Root1", "Root1_title", "/test1.htm", 0);
        BreadCrumbEntity<Number> sme_2 = new BreadCrumbEntity<Number>(2, "Root2", "Root2_title", "/test2.htm", 1);
        BreadCrumbEntity<Number> sme_3 = new BreadCrumbEntity<Number>(3, "Root3", "Root3_title", "/test3.htm", 1);
        BreadCrumbEntity<Number> sme_4 = new BreadCrumbEntity<Number>(4, "Root4", "Root4_title", "/test4.htm", 2);
        BreadCrumbEntity<Number> sme_5 = new BreadCrumbEntity<Number>(5, "Root5", "Root5_title", "/test5.htm", 2);
        BreadCrumbEntity<Number> sme_6 = new BreadCrumbEntity<Number>(6, "Root6", "Root6_title", "/test6.htm", 4);
        BreadCrumbEntity<Number> sme_7 = new BreadCrumbEntity<Number>(7, "Root7", "Root7_title", "/test7.htm", 6);
        BreadCrumbEntity<Number> sme_8 = new BreadCrumbEntity<Number>(8, "Root8", "Root8_title", "/test8.htm", 7);
        siteMapEntities.add(sme_1);
        siteMapEntities.add(sme_2);
        siteMapEntities.add(sme_3);
        siteMapEntities.add(sme_4);
        siteMapEntities.add(sme_5);
        siteMapEntities.add(sme_6);
        siteMapEntities.add(sme_7);
        siteMapEntities.add(sme_8);
    }

    // ******************************************************
    /**
     * Gets the all parent site map entity list.
     * 
     */
    @Test
    public void testGetAllParentSiteMapEntityList(){
        String path = "/test8.htm";
        BreadCrumbTag siteMapTag = new BreadCrumbTag();
        List<BreadCrumbEntity<Number>> allParentSiteMapEntityList = siteMapTag.getAllParentSiteMapEntityList(path, siteMapEntities);
        log.info("show");
        if (null != allParentSiteMapEntityList){
            for (BreadCrumbEntity<Number> sme : allParentSiteMapEntityList){
                log.info(sme.getName());
            }
        }else{
            log.info("allParentSiteMapEntityList is null/empty");
        }
    }

    /**
     * Gets the site map entity by path.
     * 
     */
    @Test
    public void testGetSiteMapEntityByPath(){
        String path = "/test8.htm";
        BreadCrumbTag siteMapTag = new BreadCrumbTag();
        BreadCrumbEntity<Number> siteMapEntity = siteMapTag.getSiteMapEntityByPath(path, siteMapEntities);
        log.info(siteMapEntity.getParentId() + "");
        log.info(siteMapEntity.getName());
    }

    /**
     * Parses the.
     */
    @Test
    public void parse(){
        String path = "/test8.htm";
        BreadCrumbTag siteMapTag = new BreadCrumbTag();
        List<BreadCrumbEntity<Number>> allParentSiteMapEntityList = siteMapTag.getAllParentSiteMapEntityList(path, siteMapEntities);
        Map<String, Object> contextKeyValues = new HashMap<String, Object>();
        contextKeyValues.put("siteMapEntityList", allParentSiteMapEntityList);
        contextKeyValues.put("connector", ">");
        String siteMapString = VelocityUtil.parseTemplateWithClasspathResourceLoader("velocity/sitemap.vm", contextKeyValues);
        log.info(siteMapString);
    }
}
