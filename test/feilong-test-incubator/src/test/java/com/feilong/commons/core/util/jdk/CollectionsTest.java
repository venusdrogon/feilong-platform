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
package com.feilong.commons.core.util.jdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.tools.json.JsonUtil;

/**
 * The Class CollectionsTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午8:29:55
 */
public class CollectionsTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CollectionsTest.class);

	/**
	 * TestHelloWorld.
	 */
	@Test
	public void testRetainAll(){

		String[] food = { "eggs", "bacon", "lettuce", "beef", "pork", "chicken" };
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list3 = new ArrayList<String>();

		for (String x : food){
			list1.add(x);
			list3.add(x);
		}
		list3.add("feilong");
		System.out.println("retainall  " + list3.retainAll(list1));

		if (log.isDebugEnabled()){
			log.debug(JsonUtil.format(list3));
		}

	}

	/**
	 * Test.
	 */
	@Test
	public void test(){

		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "1");
		map.put("a", "2");
		log.info(JsonUtil.format(map));

		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("a");
		log.info(JsonUtil.format(set));
	}

	/**
	 * Sort.
	 */
	@Test
	public void sort(){
		List<BrowsingHistoryCommandJDKTest> browsingHistoryCommands = new ArrayList<BrowsingHistoryCommandJDKTest>();
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("2048", 10000L));
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("20481", 10001L));
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("20488", 10008L));
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("20483333", 1000033333L));
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("20482222", 10000222L));
		browsingHistoryCommands.add(new BrowsingHistoryCommandJDKTest("20483333333", 10200033333L));

		// 只要你给出List中对象的比较大小的办法
		// java.util.Collections.sort(List,Comparator)
		// 方法就能帮你排序List,第一个参数是你要排序的List，第二个参数是你的比较器，他告诉sort方法如何比较大小
		// java.util.Comparator是个接口，它的主要方法是
		// int compare(Object o1, Object o2)
		// Compares its two arguments for order.
		// 你应该实现这个接口，将实例作为第二个参数传给sort方法
		//
		// sort还有另外一种形势
		// java.util.Collections.sort(List)
		// 前提条件是List中的对象都实现了Comparable，比如里面都是String
		// java.lang
		// Interface Comparable
		// All Known Implementing Classes:
		// BigDecimal, BigInteger, Byte, ByteBuffer, Character, CharBuffer, Charset, CollationKey, Date, Double, DoubleBuffer, File, Float,
		// FloatBuffer,
		// IntBuffer, Integer, Long, LongBuffer, ObjectStreamField, Short, ShortBuffer, String, URI
		//
		//
		// java.util.Arrays的sort方法是一样的，不过它对数组排序
		Collections.sort(browsingHistoryCommands, new Comparator<BrowsingHistoryCommandJDKTest>(){

			@Override
			public int compare(BrowsingHistoryCommandJDKTest o1,BrowsingHistoryCommandJDKTest o2){
				if (o1.getDate() > o2.getDate()){
					return 1;
				}else if (o1.getDate() < o2.getDate()){
					return -1;
				}
				return 0;
			}
		});
		for (BrowsingHistoryCommandJDKTest browsingHistoryCommand : browsingHistoryCommands){
			log.info("date:{},SimpleSkuCommand:{}", browsingHistoryCommand.getDate(), browsingHistoryCommand.getSimpleSkuCommand());
		}
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		List l = new ArrayList();
		l.add(100);
		l.add(-66);
		l.add(0);
		l.add(88);

		log.debug("list:" + l);

		Collections.reverse(l);
		log.debug("反转后的list:" + l);

		Collections.shuffle(l); //随机排序，洗牌  
		log.debug("乱序后的list:" + l);

		Collections.swap(l, 1, 3);
		log.debug("互换序号是1，3元素后的list:" + l);

		Collections.rotate(l, 1);
		log.debug("循环排序后的list:" + l);

		Collections.sort(l); //这里是个自然排序，更多排序内容请参见本博客中的《JAVA应用 之 排序》  
		log.debug("排序后的list:" + l);

		Collections.binarySearch(l, 88); //二分查找，必须保证list处于有序状态，查询成功返回序号，查不到返回负数  

		log.debug("list中最小的元素是：" + Collections.min(l));
		log.debug("list中最大的元素是：" + Collections.max(l));
		//上面两个方法 是自然排序，当然您可以自己实现一个Comparator的实现类作为第二个参数，具体见《JAVA应用 之 排序》  

		l.add(88);
		//88（第二个参数）在list（第一个参数）出现过多少次  
		log.debug("88在list中出现了：" + Collections.frequency(l, 88) + "次");

		Collections.replaceAll(l, 88, 66); //将88用66去替代  
		log.debug("替代后的list:" + l);

		Collections.fill(l, 66); //使用66（第二个参数）替换list中的所有元素。  
		log.debug("list所有元素都被替换成为66：" + l);

		//下面写法的意思是创建一个l这么大的l1，不这么写会报异常，因为copy的时候不会自动扩容  
		List l1 = new ArrayList(Arrays.asList(new Object[l.size()]));
		Collections.copy(l1, l);
		log.debug("拷贝l的l1：" + l1);
		log.debug("l和l1是否相等？" + l.equals(l1));
		log.debug("l和l1是否是一个引用？" + (l == l1));
		l1 = l;
		log.debug("l和l1是否相等？" + l.equals(l1));
		log.debug("l和l1是否是一个引用？" + (l == l1));
		l1 = new ArrayList(l); //这个是一个浅拷贝，l和l1的引用虽然不同，但是l和l1内部的元素引用还是一样的  
		log.debug("l和l1是否相等？" + l.equals(l1));
		log.debug("l和l1是否是一个引用？" + (l == l1));
		//上面的问题有些复杂 以后形成一个专题讲解  

		//创建一个类型安全的集合，下面的意思是这个集合只能是再添加Integer类型数据  
		l.add("abc"); //现在还没事~  
		try{
			Collections.checkedList(l, Integer.class).add("abc");
		}catch (Exception e){
			log.debug("类型安全操作后，当你再试图添加非Integer类型数据时 发生了异常");
		}
		log.debug("======================");

		/*
		 * 注意常用的这些集合（HashSet、HashMap、ArrayList、TreeMap、TreeSet、LinkedList等），
		 * 都不是线程安全的，如果您的程序是在多线程环境下 并且有可能会同时修改同一个集合，那么
		 * 您就需要使用Collections.synchronizedXxx 方法来保证线程安全
		 */
		List sl = Collections.synchronizedList(new ArrayList());
		Map sm = Collections.synchronizedMap(new HashMap());
		Set ss = Collections.synchronizedSet(new HashSet());
		Set sts = Collections.synchronizedSortedSet(new TreeSet<Object>());

		/*
		 * 不可变集合（空集合、指定元素集合、不可变状态）
		 * 1.使用Collections.emptyXxx 方法来创建一个不可变化的空集合
		 * 2.空不可变集合的意义：不会因为赋值null那样带来不可预期的异常，个人理解就是初始化的最佳实践。
		 * 3.指定元素集合：返回一个只包含指定元素的集合,同时也是不可变化的集合
		 * 应用：创建一个不可变化的特殊对象集合，例如：管理员集合,这样这个管理员对象就可以拥有一些集合的方法了
		 * 例如：我判断一些这个用户对象是否是管理员（adminList.contains(user)）
		 * 4.不可变状态：获得这个集合的不可变试图（只读）
		 * 3.这里之用list举例，其他集合类似
		 */
		List<String> el = Collections.emptyList();
		log.debug("" + el.isEmpty());

		try{
			el.add("1"); //如果试图改变它 那么就会抛出UnsupportedOperationException异常  
		}catch (Exception e){
			log.debug("UnsupportedOperationException");
		}
		List<String> el1 = null; //通常我们可能习惯这样定义一个list  
		try{
			el1.contains("abc"); //可能在某个地方我们不小心的这样用了（假设这个存在于一个低概率发生的地方）  
		}catch (Exception e){
			log.debug("null异常");
		}
		List<String> singletonL = Collections.singletonList("abcd");
		log.debug(singletonL.toString());

		//通过下面的方式就能得到一个不可变化的（只读）集合（视图的感觉，官方其实也是这么介绍的）  
		List<String> listView = Collections.unmodifiableList(l);
	}

	/**
	 * TestCollectionsTest.
	 */
	@Test
	public void testCollectionsTest(){
		List<String> el1 = null;
		//assertEquals(expected, actual);
	}
}