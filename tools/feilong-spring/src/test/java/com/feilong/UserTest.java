package com.feilong;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.feilong.entity.User;
import com.feilong.tools.json.JsonUtil;

public class UserTest implements ApplicationContextAware{

	private static final Logger			log		= LoggerFactory.getLogger(UserTest.class);

	private static ApplicationContext	context	= null;

	// @Value("#{testProperties['name']}")
	// private String aString;
	@Test
	public void test(){
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:spring.xml");
		Properties props = context.getBean("testProperties", Properties.class);
		// System.out.println(aString);

		log.info("props:{}", JsonUtil.format(props));
		// Locale locale = new Locale("zh", "CN");
		// log.info("================" + context.getMessage("name", null, locale));
		User user = (User) context.getBean("feitian@");
		System.out.println(user.getUserName());
		// -----------------------------
		List<String> list = user.getList();
		log.info("list:{}", JsonUtil.format(list));
		// --------------------------------
		Map<String, Object> map = user.getMap();
		map.put(null, null);
		map.put(null, 222);
		log.info("map:{}", JsonUtil.format(map));
		List<String> list2 = (List<String>) map.get("五子良将");
		log.info("list2:{}", JsonUtil.format(list2));
		// -----------------------------------------
		List<String> list3 = (List<String>) map.get("八虎骑");
		log.info("list3:{}", JsonUtil.format(list3));
		// -----------------------------------------
		Properties properties = user.getProperties();
		log.info("properties:{}", JsonUtil.format(properties));
		// *********************************
		Set<String> set = user.getSet();
		set.add("1");
		set.add("1");
		log.info("set:{}", JsonUtil.format(set));
		// *******************************
		Vector<Integer> vector = new Vector<Integer>();
		vector.add(1);
		vector.add(2222);
		vector.add(3333);
		vector.add(55555);
		log.info("vector:{}", JsonUtil.format(vector));
		System.out.println(vector.get(0));
		// /********************************************
		Hashtable<String, Object> hashtable = new Hashtable<String, Object>();
		hashtable.put("a", "a");
		// hashtable.put("a", null);
		log.info("hashtable:{}", JsonUtil.format(hashtable));
	}

	@Test
	public void hashSet(){
		// **********************************************************
		HashSet<String> hashSet = new HashSet<String>();
		hashSet.add("今天");
		log.info("hashSet:{}", JsonUtil.format(hashSet));
		// **********************************************************
		HashSet<com.feilong.entity.user.User> hashSet1 = new HashSet<com.feilong.entity.user.User>();
		com.feilong.entity.user.User user = new com.feilong.entity.user.User();
		user.setId(555);
		hashSet1.add(user);
		log.info("hashSet1:{}", JsonUtil.format(hashSet1));
	}

	@Test
	public void test1(){
		User user = (User) context.getBean("feitian@");
		log.info("user:{}", JsonUtil.format(user));
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException{
		context = applicationContext;
	}
}
