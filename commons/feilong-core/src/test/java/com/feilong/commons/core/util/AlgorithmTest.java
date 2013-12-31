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
package com.feilong.commons.core.util;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 2011-1-21 上午09:51:47
 */
public class AlgorithmTest{

	/**
	 * 测试选择排序
	 */
	@Test
	public void testSelectSort(){
		Random ran = new Random();
		int[] sort = new int[10];
		for (int i = 0; i < 10; i++){
			sort[i] = ran.nextInt(50);
		}
		System.out.println("排序前的数组为");
		for (int i : sort){
			System.out.print(i + " ");
		}
		Algorithm.selectSort(sort);
		System.out.println();
		System.out.print("排序后的数组为");
		for (int i : sort){
			System.out.print(i + " ");
		}
	}

	/**
	 * 测试快速排序
	 */
	@Test
	public void testQuickSort(){
		Integer[] sort = { 54, 31, 89, 33, 66, 12, 68, 20 };
		System.out.println("排序前的数组为：");
		for (int data : sort){
			System.out.print(data + " ");
		}
		System.out.println();
		Algorithm.quickSort(sort, 0, sort.length - 1);
		System.out.print("排序后的数组为：");
		for (int data : sort){
			System.out.print(data + " ");
		}
	}

	/**
	 * 测试快速排序
	 */
	@Test
	public void testQuickSort1(){
		String[] sort = { "14", "6", "12", "8.5", "10", "X", "L", "XL", "M", "3XL", "L/XL", "XXL/XXXL" };
		System.out.println("排序前的数组为：");
		for (String data : sort){
			System.out.print(data + " ");
		}
		System.out.println();
		Arrays.sort(sort);// 排序 10 12 14 3XL 6 8.5 L M X XL
		// Algorithm.quickSort(sort, 0, sort.length - 1);
		System.out.print("排序后的数组为：");
		for (String data : sort){
			System.out.print(data + " ");
		}
	}

	/**
	 * 测试快速排序
	 */
	@Test
	public void quickSort(){
		Number[] sort = { 54.8, 31.2, 89D, 33, 66.08888888, 12.36555565656, 68, 20, 20.000 };
		System.out.println("排序前的数组为：");
		for (Number data : sort){
			System.out.print(data + " ");
		}
		System.out.println();
		Algorithm.quickSort(sort, 0, sort.length - 1);
		System.out.print("排序后的数组为：");
		for (Number data : sort){
			System.out.print(data + " ");
		}
	}

	/**
	 * 冒泡排序
	 */
	@Test
	public void bubbleSort(){
		Number[] sort = { 54.8, 31.2, 89D, 33, 66.08888888, 12.36555565656, 68, 20, 20.000 };
		System.out.println("排序前的数组为：");
		for (Number data : sort){
			System.out.print(data + " ");
		}
		System.out.println();
		Algorithm.bubbleSort(sort, false);
		System.out.print("排序后的数组为：");
		for (Number data : sort){
			System.out.println(data + " ");
		}
	}

	/**
	 * 测试直接插入排序
	 */
	@Test
	public void testDirectInsertSort(){
		Random ran = new Random();
		int[] sort = new int[10];
		for (int i = 0; i < 10; i++){
			sort[i] = ran.nextInt(50);
		}
		System.out.print("排序前的数组为");
		for (int i : sort){
			System.out.print(i + " ");
		}
		Algorithm.directInsertSort(sort);
		System.out.println();
		System.out.print("排序后的数组为");
		for (int i : sort){
			System.out.print(i + " ");
		}
	}

	/**
	 * 测试直接插入排序
	 */
	@Test
	public void testBinarySearch(){
		int[] sort = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int mask = Algorithm.binarySearch(sort, 6);
		System.out.println(mask);
	}
}
