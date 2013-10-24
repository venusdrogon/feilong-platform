/**
 * Copyright (c) 2008-2013 FeiLong, Inc. All Rights Reserved.
 * <p>
 * 	This software is the confidential and proprietary information of FeiLong Network Technology, Inc. ("Confidential Information").  <br>
 * 	You shall not disclose such Confidential Information and shall use it 
 *  only in accordance with the terms of the license agreement you entered into with FeiLong.
 * </p>
 * <p>
 * 	FeiLong MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, 
 * 	INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * 	PURPOSE, OR NON-INFRINGEMENT. <br> 
 * 	FeiLong SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * 	THIS SOFTWARE OR ITS DERIVATIVES.
 * </p>
 */
package jdk.java.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeSet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2012-4-6 下午7:49:54
 */
public class TreeSetTest{

	private static final Logger	log	= LoggerFactory.getLogger(TreeSetTest.class);

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
