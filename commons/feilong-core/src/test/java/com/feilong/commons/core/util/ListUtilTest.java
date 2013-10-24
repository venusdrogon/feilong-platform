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
package com.feilong.commons.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.test.User;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-5-12 上午11:40:44
 * @since 1.0
 */
public class ListUtilTest{

	private static final Logger	log			= LoggerFactory.getLogger(ListUtilTest.class);

	private String[]			aStrings	= { "a", "b" };

	@Test
	public void remove(){
		List<String> list = new ArrayList<String>();
		list.add("xinge");
		list.add("feilong1");
		list.add("feilong2");
		list.add("feilong3");
		list.add("feilong4");
		list.add("feilong5");
		log.info(list.indexOf("xinge") + "");
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();){
			String string = (String) iterator.next();
			if (string.equals("feilong1")){
				iterator.remove();
			}
		}
		// for (int i = 0, j = list.size(); i < j; ++i){
		// // String string = list.get(i);
		//
		// }
		log.info("list:{}", JsonFormatUtil.format(list));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringReplaceBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringB(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

	@Test
	public final void getFirstItem(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.getFirstItem(testList));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toStringRemoveBrackets(java.util.List)} 的测试方法。
	 */
	@Test
	public final void testListToStringA(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringRemoveBrackets(testList));
	}

	@Test
	public final void toArray(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");

		String[] array = ListUtil.toArray(testList);

		log.info(JsonFormatUtil.format(array));
	}

	/**
	 * {@link com.feilong.commons.core.util.ListUtil#toString(java.util.List, boolean)} 的测试方法。
	 */
	@Test
	public final void testListToString(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toString(testList, true));
	}

	@Test
	public final void convertListToStringReplaceBrackets(){
		List<String> testList = new ArrayList<String>();
		testList.add("xinge");
		testList.add("feilong");
		log.info(ListUtil.toStringReplaceBrackets(testList));
	}

}
