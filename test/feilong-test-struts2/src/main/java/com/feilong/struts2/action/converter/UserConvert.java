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
package com.feilong.struts2.action.converter;

import java.util.Map;
import java.util.StringTokenizer;

import ognl.DefaultTypeConverter;

import com.feilong.struts2.bean.User;

/**
 * The Class UserConvert.
 */
public class UserConvert extends DefaultTypeConverter{

	/* (non-Javadoc)
	 * @see ognl.DefaultTypeConverter#convertValue(java.util.Map, java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object convertValue(Map context,Object value,Class toType){
		if (toType == User.class){
			String[] userInfo = (String[]) value;
			String userInfoString = userInfo[0];
			StringTokenizer stringTokenizer = new StringTokenizer(userInfoString, ";");
			String userName = stringTokenizer.nextToken();
			String password = stringTokenizer.nextToken();
			User user = new User();
			user.setPassword(password);
			user.setUserName(userName);
			return user;
		}
		return null;
	}
}
