package com.feilong.tools.velocity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.ObjectUtil;
import com.feilong.commons.core.util.StringUtil;

public class VelocityUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(VelocityUtilTest.class);

	@Test
	public void parseVMTemplateWithClasspathResourceLoader(){
		// Properties properties = new Properties();
		// //设置模板的路径
		// properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "target/test-classes/scripts");
		//
		Map<String, Object> map = new HashMap<String, Object>();
		List<Long> longs = new ArrayList<Long>();
		for (int i = 0; i < 10; i++){
			longs.add(ObjectUtil.toLong(i));
		}
		map.put("memberId", "5");
		map.put("list", longs);
		List<Long> channelIds = new ArrayList<Long>();
		channelIds.add(1L);
		channelIds.add(2L);
		channelIds.add(8L);
		channelIds.add(3L);
		map.put("channelIds", channelIds);
		String templateInClassPath = "velocity/test.vm";
		String parseVMTemplate = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, map);
		log.info(parseVMTemplate);
	}

	@Test
	public void stringResourceLoader1(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", "5");
		String vmContent = "${memberId},金鑫";
		String parseVMTemplate = VelocityUtil.parseString(vmContent, map);
		log.info(parseVMTemplate);
	}

	@Test
	public void stringResourceLoader(){
		Properties properties = new Properties();
		properties.put("name", "jinxin");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("global", properties);
		String vmContent = "${global.get('name')}";
		String parseVMTemplate = VelocityUtil.parseString(vmContent, map);
		log.info(parseVMTemplate);
	}

	@Test
	public void parseVMTemplateWithClasspathResourceLoader1(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("StringUtil", StringUtil.class);
		map.put("code", "橘黄色/米黄色");
		String templateInClassPath = "velocity/test_stringutil.vm";
		String parseVMTemplate = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, map);
		log.info(parseVMTemplate);
	}

	@Test
	public void testNull(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", null);
		String templateInClassPath = "velocity/test_null.vm";
		String parseVMTemplate = VelocityUtil.parseTemplateWithClasspathResourceLoader(templateInClassPath, map);
		log.info(parseVMTemplate);
	}
}
