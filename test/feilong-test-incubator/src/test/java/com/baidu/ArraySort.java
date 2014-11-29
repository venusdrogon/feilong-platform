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
package com.baidu;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The Class ArraySort.
 */
public class ArraySort{

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		String[] arrays = { "almn", "fba", "cba", "aa", "ab", "bb", "sadfafda", "kadfafda", "cc" };

		Arrays.sort(arrays, new Comparator<String>(){

			public int compare(String s1,String s2){
				Integer length = s1.length();
				Integer length2 = s2.length();

				//先判断长度，长度比较
				int compareTo = length.compareTo(length2);

				//如果长度相等，那么比较自己本身的顺序
				if (0 == compareTo){
					compareTo = s1.compareTo(s2);
				}
				return compareTo;
			}
		});
		for (int i = 0; i < arrays.length; i++){
			System.out.println(arrays[i]);
		}
	}
}