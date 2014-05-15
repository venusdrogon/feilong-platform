package com.feilong.taglib.common.navigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.taglib.display.navigation.SiteMapEntity;
import com.feilong.taglib.display.navigation.SiteMapTag;
import com.feilong.tools.velocity.VelocityUtil;

public class SiteMapTagTest{

	private static final Logger					log				= LoggerFactory.getLogger(SiteMapTagTest.class);

	private static List<SiteMapEntity<Number>>	siteMapEntities	= new ArrayList<SiteMapEntity<Number>>();

	static{
		SiteMapEntity<Number> sme_1 = new SiteMapEntity<Number>(1, "Root1", "Root1_title", "/test1.htm", 0);
		SiteMapEntity<Number> sme_2 = new SiteMapEntity<Number>(2, "Root2", "Root2_title", "/test2.htm", 1);
		SiteMapEntity<Number> sme_3 = new SiteMapEntity<Number>(3, "Root3", "Root3_title", "/test3.htm", 1);
		SiteMapEntity<Number> sme_4 = new SiteMapEntity<Number>(4, "Root4", "Root4_title", "/test4.htm", 2);
		SiteMapEntity<Number> sme_5 = new SiteMapEntity<Number>(5, "Root5", "Root5_title", "/test5.htm", 2);
		SiteMapEntity<Number> sme_6 = new SiteMapEntity<Number>(6, "Root6", "Root6_title", "/test6.htm", 4);
		SiteMapEntity<Number> sme_7 = new SiteMapEntity<Number>(7, "Root7", "Root7_title", "/test7.htm", 6);
		SiteMapEntity<Number> sme_8 = new SiteMapEntity<Number>(8, "Root8", "Root8_title", "/test8.htm", 7);
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
	@Test
	public void getAllParentSiteMapEntityList(){
		String path = "/test82.htm";
		SiteMapTag siteMapTag = new SiteMapTag();
		List<SiteMapEntity<Number>> allParentSiteMapEntityList = siteMapTag.getAllParentSiteMapEntityList(path, siteMapEntities);
		log.info("show");
		if (null != allParentSiteMapEntityList){
			for (SiteMapEntity<Number> sme : allParentSiteMapEntityList){
				log.info(sme.getName());
			}
		}else{
			log.info("allParentSiteMapEntityList is null/empty");
		}
	}

	@Test
	public void getSiteMapEntityByPath(){
		String path = "/test8.htm";
		SiteMapTag siteMapTag = new SiteMapTag();
		SiteMapEntity<Number> siteMapEntity = siteMapTag.getSiteMapEntityByPath(path, siteMapEntities);
		log.info(siteMapEntity.getParentId() + "");
		log.info(siteMapEntity.getName());
	}

	@Test
	public void parse(){
		String path = "/test8.htm";
		SiteMapTag siteMapTag = new SiteMapTag();
		List<SiteMapEntity<Number>> allParentSiteMapEntityList = siteMapTag.getAllParentSiteMapEntityList(path, siteMapEntities);
		Map<String, Object> contextKeyValues = new HashMap<String, Object>();
		contextKeyValues.put("siteMapEntityList", allParentSiteMapEntityList);
		contextKeyValues.put("connector", ">");
		String siteMapString = VelocityUtil.parseTemplateWithClasspathResourceLoader("velocity/sitemap.vm", contextKeyValues);
		System.out.println(siteMapString);
	}
}
