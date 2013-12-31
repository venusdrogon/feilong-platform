/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package jdk.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午8:29:55
 */
public class CollectionsTest{

	private static final Logger	log	= LoggerFactory.getLogger(CollectionsTest.class);

	@Test
	public void sort(){
		List<BrowsingHistoryCommand> browsingHistoryCommands = new ArrayList<BrowsingHistoryCommand>();
		browsingHistoryCommands.add(new BrowsingHistoryCommand("2048", 10000L));
		browsingHistoryCommands.add(new BrowsingHistoryCommand("20481", 10001L));
		browsingHistoryCommands.add(new BrowsingHistoryCommand("20488", 10008L));
		browsingHistoryCommands.add(new BrowsingHistoryCommand("20483333", 1000033333L));
		browsingHistoryCommands.add(new BrowsingHistoryCommand("20482222", 10000222L));
		browsingHistoryCommands.add(new BrowsingHistoryCommand("20483333333", 10200033333L));
		
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
		// BigDecimal, BigInteger, Byte, ByteBuffer, Character, CharBuffer, Charset, CollationKey, Date, Double, DoubleBuffer, File, Float, FloatBuffer,
		// IntBuffer, Integer, Long, LongBuffer, ObjectStreamField, Short, ShortBuffer, String, URI
		//
		//
		// java.util.Arrays的sort方法是一样的，不过它对数组排序
		Collections.sort(browsingHistoryCommands, new Comparator<BrowsingHistoryCommand>(){

			@Override
			public int compare(BrowsingHistoryCommand o1,BrowsingHistoryCommand o2){
				if (o1.getDate() > o2.getDate()){
					return 1;
				}else if (o1.getDate() < o2.getDate()){
					return -1;
				}
				return 0;
			}
		});
		for (BrowsingHistoryCommand browsingHistoryCommand : browsingHistoryCommands){
			log.info("date:{},SimpleSkuCommand:{}", browsingHistoryCommand.getDate(), browsingHistoryCommand.getSimpleSkuCommand());
		}
	}
}
