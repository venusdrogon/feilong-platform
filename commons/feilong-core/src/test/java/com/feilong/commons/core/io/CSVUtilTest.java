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
package com.feilong.commons.core.io;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.feilong.commons.core.date.DatePattern;
import com.feilong.commons.core.date.DateUtil;

public class CSVUtilTest{

	@Test
	public void testWrite(){
		String path = "/home/webuser/nike_int/johnData/${date}/nikeid_pix_demand.csv";
		path = path.replace("${date}", DateUtil.date2String(DateUtil.getYesterday(new Date()), DatePattern.onlyDate));
		System.out.println(path);
		String[] columnTitles = { "a", "b" };
		List<Object[]> linkedList = new ArrayList<Object[]>();
		for (int i = 0; i < 20; i++){
			Object[] object = { i + "金,鑫", i + "jin'\"xin" };
			linkedList.add(object);
		}
		CSVUtil.write(path, columnTitles, linkedList);
	}
}
