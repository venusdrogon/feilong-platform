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

import java.util.TreeSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class TreeSetTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午7:49:54
 */
public class TreeSetTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(TreeSetTest.class);

	/**
	 * Name.
	 */
	@Test
	public void name(){

		TreeSet<BrowsingHistoryCommand> treeSet = new TreeSet<BrowsingHistoryCommand>();
		for (int i = 0; i < 10; ++i){
			treeSet.add(new BrowsingHistoryCommand("21500" + i, 17800L + i));
		}
		for (BrowsingHistoryCommand browsingHistoryCommand : treeSet){
			log.info("{}:{}", browsingHistoryCommand.getDate(), browsingHistoryCommand.getSimpleSkuCommand());
		}
		log.info("**********************", treeSet.last().getDate() + "");
		treeSet.last().setDate(1780088888L);
		// Arrays.sort(treeSet.toArray());
		TreeSet<BrowsingHistoryCommand> treeSet1 = new TreeSet<BrowsingHistoryCommand>();
		treeSet1.addAll(treeSet);
		// Collections.sort()
		for (BrowsingHistoryCommand browsingHistoryCommand : treeSet1){
			log.info("{}:{}", browsingHistoryCommand.getDate(), browsingHistoryCommand.getSimpleSkuCommand());
		}
	}
}
