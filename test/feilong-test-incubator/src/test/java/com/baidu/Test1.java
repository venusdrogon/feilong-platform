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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.8 2014年9月24日 上午1:18:53
 * @since 1.0.8
 */
public class Test1{

	private static final Logger	log	= LoggerFactory.getLogger(Test1.class);

	public static void main(String[] args){
		String source = "0123456789abcdefABCDEF";
		String target = "++==Z3A8bFxcd";

		final List<Character> asList = new ArrayList<Character>(Arrays.asList(ArrayUtils.toObject(source.toCharArray())));
		final List<Character> asList2 = new ArrayList<Character>(Arrays.asList(ArrayUtils.toObject(target.toCharArray())));
		asList.retainAll(asList2);
		System.out.println(asList);

	}
}
