package com.feilong.commons.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;
import com.feilong.commons.core.entity.JoinStringEntity;
import com.feilong.test.User;

public class CollectionUtilTest{

	private static final Logger	log	= LoggerFactory.getLogger(CollectionUtilTest.class);

	@Test
	public void testTreeSet(){
		TreeSet<Date> treeSet = new TreeSet<Date>();
		treeSet.add(DateUtil.string2Date("2012-05-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-03-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-01-02", DatePattern.onlyDate));
		treeSet.add(DateUtil.string2Date("2012-01-02", DatePattern.onlyDate));
		log.info(treeSet + "");
	}

	@Test
	public void testMap(){
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put("jinxin", 1);
		map.put(2, 2);
		map.put("甲", 3);
		map.put(4, 4);
		map = null;
		StringBuilder builder = new StringBuilder();
		builder.append(map);
		System.out.println(builder.toString());
	}

	public void testMapToEnumeration(){
		// Enumeration
		final Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put("jinxin", 1);
		map.put(2, 2);
		map.put("甲", 3);
		map.put(4, 4);
		map.put("jinxin1", 1);
		map.put(21, 2);
		map.put("甲1", 3);
		map.put(41, 4);
		Enumeration<Object> enumeration = CollectionUtil.toEnumeration(map.keySet());
		while (enumeration.hasMoreElements()){
			System.out.println(enumeration.nextElement());
		}
	}

	/**
	 * 集合转成字符串
	 */
	@Test
	public void testCollectionToString(){
		List<String> list = new ArrayList<String>();
		list.add("2548");
		list.add("2549");
		JoinStringEntity joinStringEntity = new JoinStringEntity();
		joinStringEntity.setConnector("|");
		System.out.println(CollectionUtil.toString(list, joinStringEntity));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void test(){
		try{
			Class clz = User.class;
			Collection collection = CollectionUtils.typedCollection(new ArrayList(), clz);
			collection.add(clz.newInstance());

			log.info(collection.size() + "");
			for (Object object : collection){
				User user = (User) object;
				log.info(user.getName());
			}

			log.info("hahahah");

			Collection<User> collection2 = collection;
			log.info(collection2.size() + "");
			for (Object object : collection2){
				User user = (User) object;
				log.info(user.getName());
			}

		}catch (InstantiationException e){
			e.printStackTrace();
		}catch (IllegalAccessException e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		test();
	}
}
