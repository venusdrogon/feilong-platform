/**
 * Copyright (c) 2008-2014 FeiLong, Inc. All Rights Reserved.
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
package com.feilong.tools.jexl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.tools.JexlUtil;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 25, 2011 2:31:24 PM
 */
public class JexlUtilTest{

	@SuppressWarnings("unused")
	private static final Logger	log	= LoggerFactory.getLogger(JexlUtilTest.class);

	@Test
	public void name(){
		/*
		 * 初始化一个Cat对象
		 */
		Cat cat = new Cat();
		cat.setAge(2);
		cat.setName("Tom");
		cat.setOwner(new People(){

			{
				this.setAge(24);
				this.setName("yang");
			}
		});
		/*
		 * 初始化一个List对象，列表里存入两个元素 第一个元素是一个字符串 第二个元素是一个整数
		 */
		List list = new ArrayList();
		list.add("Hello world !");
		list.add(11);
		Map map = new HashMap();
		map.put("cat", cat);
		map.put("people", cat.getOwner());
		Map<String, Object> mapContext = new HashMap<String, Object>();
		/*
		 * 向执行JEXL表达式的上下文环境的变量字典中存入两个变量 键值 "tom" 对应变量 cat 键值 "array" 对应变量 list
		 */
		mapContext.put("tom", cat);
		mapContext.put("array", list);
		mapContext.put("map", map);
		/*
		 * 定义要被求值的所有表达式
		 */
		String[] expressions = new String[] {
				//
				"tom.age==2 and 8==7",
				// 嵌套属性
				"tom.owner",
				// 嵌套属性
				"tom.owner.name",
				// 嵌套属性的方法调用，表达式求值结果为方法返回值
				"tom.owner.name.length()",
				"array[0].toUpperCase()",
				// 内置通用方法size()，返回String，Map和List的长度
				"size(tom.owner.name)",
				// 返回列表中第一个元素
				"array[0]",
				// + 操作符 可用于字符串的连接
				"array[0].toUpperCase()+array[0]",
				// 内置通用方法empty()，如果为空返回true，否则返回false
				"empty(array[0])",
				"empty(array[2])",
				// 通过键值 'cat' 获取字典中相应的值
				"map['cat']",
				// 嵌套属性
				"map['people'].name.length()" };
		for (String expression : expressions){
			Object obj = JexlUtil.evaluate(expression, mapContext);
			System.out.println(expression + " = " + obj);
		}
	}

	@Test
	public void name1(){
		System.out.println(JexlUtil.evaluate("5+7", null));
		Map<String, Object> mapContext = new HashMap<String, Object>();
		/*
		 * 向执行JEXL表达式的上下文环境的变量字典中存入两个变量 键值 "tom" 对应变量 cat 键值 "array" 对应变量 list
		 */
		mapContext.put("a", 56);
		mapContext.put("b", 45);
		System.out.println(JexlUtil.evaluate("a==56 && b<10", mapContext));
		System.out.println(JexlUtil.evaluate("a==56 || b<10", mapContext));
		// System.out.println(FeiLongJexlUtil.evaluate("", mapContext));
	}

	@Test
	public void test(){
		System.out.println(JexlUtil.evaluate("1+1+1+1+1+1+1+1+1+1+1+1*0+1", null));
	}
}
