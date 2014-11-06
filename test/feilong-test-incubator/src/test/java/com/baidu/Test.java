package com.baidu;
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

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年8月30日 下午9:00:16
 * @since 1.0.8
 */
public class Test{

	private static final Logger	log	= LoggerFactory.getLogger(Test.class);

	public static void main(String[] args){

		Integer[] numbers = new Integer[5];

		int i = 0;
		do{
			System.out.print("请输入第" + (i + 1) + "个数:");

			Scanner scanner = new Scanner(System.in);
			scanner.useDelimiter("\n");

			String next = scanner.nextLine();

			numbers[i] = Integer.parseInt(next);

			i++;
		}while (i < 5);

		Arrays.sort(numbers);

		System.out.print("您输入的5个数字，排序后的结果是:");
		System.out.println(Arrays.toString(numbers));
	}
}
