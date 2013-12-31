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

import java.util.ListResourceBundle;

import com.feilong.test.User;


/**
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Nov 18, 2012 11:45:42 PM
 */
public class SanguoListResourceBundle extends ListResourceBundle{

	/*
	 * (non-Javadoc)
	 * @see java.util.ListResourceBundle#getContents()
	 */
	@Override
	protected Object[][] getContents(){

		return new Object[][] {
				{ "关羽", "云长" },
				{ "张飞", "翼德" },
				{ "赵云", "子龙" },
				{ "马超", "孟起" },
				{ "黄忠", "汉升" },
				{ "刘备", "玄德" },
				{ "诸葛亮", "孔明" },
				{ "姜维", "伯约" },
				{ "魏延", "文长" },
				{ "庞统", "士元" },
				{ "法正", new User() } };
	}
}
