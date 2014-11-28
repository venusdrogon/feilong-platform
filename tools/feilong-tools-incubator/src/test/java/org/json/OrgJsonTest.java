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
package org.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class OrgJsonTest.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 16:24:52
 */
public class OrgJsonTest{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(OrgJsonTest.class);

	/**
	 * Test.
	 */
	@Test
	public void test(){
		String a = "{user:{name:'张三'},user2:{name:'张三'}}";
		try{
			JSONObject jsonObject = new JSONObject(a);
			JSONObject jSONArray_user = jsonObject.getJSONObject("user");

			log.debug("jSONArray_user.length():{}", jSONArray_user.length());
			log.debug("jsonObject:{}", jsonObject);
			log.debug("jSONArray_user:{}", jSONArray_user);

		}catch (JSONException e){
			log.error(e.getClass().getName(), e);
		}
	}
}
